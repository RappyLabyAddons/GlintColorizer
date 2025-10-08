package com.rappytv.glintcolorizer.v1_20_6.mixins;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.rappytv.glintcolorizer.core.GlintColorizerAddon;
import com.rappytv.glintcolorizer.v1_20_6.ColoredVertexConsumer;
import net.labymod.api.util.Color;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(HumanoidArmorLayer.class)
public class MixinHumanoidArmorLayer {

  @WrapOperation(
      method = "renderGlint",
      at = @At(
          value = "INVOKE",
          target = "Lnet/minecraft/client/renderer/MultiBufferSource;getBuffer(Lnet/minecraft/client/renderer/RenderType;)Lcom/mojang/blaze3d/vertex/VertexConsumer;"
      )
  )
  private VertexConsumer redirectFoilBuffer( // TODO: Fix color not changing
      MultiBufferSource instance,
      RenderType renderType,
      Operation<VertexConsumer> original
  ) {
    VertexConsumer buffer = original.call(instance, renderType);

    Color color = GlintColorizerAddon.getArmorGlintColor();
    if (color != null) {
      return new ColoredVertexConsumer(buffer, color);
    }

    return buffer;
  }
}
