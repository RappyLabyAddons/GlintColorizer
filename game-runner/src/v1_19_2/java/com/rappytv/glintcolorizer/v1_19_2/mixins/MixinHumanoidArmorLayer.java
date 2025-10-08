package com.rappytv.glintcolorizer.v1_19_2.mixins;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.rappytv.glintcolorizer.core.GlintColorizerAddon;
import com.rappytv.glintcolorizer.v1_19_2.ColoredVertexConsumer;
import net.labymod.api.util.Color;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(HumanoidArmorLayer.class)
public class MixinHumanoidArmorLayer {

  @WrapOperation(
      method = "renderModel(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;ILnet/minecraft/world/item/ArmorItem;ZLnet/minecraft/client/model/HumanoidModel;ZFFFLjava/lang/String;)V",
      at = @At(
          value = "INVOKE",
          target = "Lnet/minecraft/client/renderer/entity/ItemRenderer;getArmorFoilBuffer(Lnet/minecraft/client/renderer/MultiBufferSource;Lnet/minecraft/client/renderer/RenderType;ZZ)Lcom/mojang/blaze3d/vertex/VertexConsumer;"
      )
  )
  private VertexConsumer redirectFoilBuffer(
      MultiBufferSource source,
      RenderType type,
      boolean noEntity,
      boolean hasGlint,
      Operation<VertexConsumer> original
  ) {
    VertexConsumer buffer = original.call(source, type, noEntity, hasGlint);

    if (hasGlint) {
      Color color = GlintColorizerAddon.getArmorGlintColor();
      if (color != null) {
        return new ColoredVertexConsumer(buffer, color);
      }
    }

    return buffer;
  }
}
