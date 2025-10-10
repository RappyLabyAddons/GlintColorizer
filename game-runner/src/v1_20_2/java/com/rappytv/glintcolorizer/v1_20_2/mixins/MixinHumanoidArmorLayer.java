package com.rappytv.glintcolorizer.v1_20_2.mixins;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.rappytv.glintcolorizer.core.GlintColorizerAddon;
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
  private VertexConsumer redirectFoilBuffer(
      MultiBufferSource instance,
      RenderType renderType,
      Operation<VertexConsumer> original
  ) {
    Color color = GlintColorizerAddon.getArmorGlintColor();
    if (color != null) {
      float r = color.getRed() / 255.0f;
      float g = color.getGreen() / 255.0f;
      float b = color.getBlue() / 255.0f;
      RenderSystem.setShaderColor(r, g, b, 1.0f);
    }

    VertexConsumer buffer = original.call(instance, renderType);

    if (color != null) {
      // Reset to default white so later rendering isn’t tinted
      RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
    }

    return buffer;
  }
}
