package com.rappytv.glintcolorizer.v1_21_3.mixins;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.blaze3d.vertex.VertexMultiConsumer;
import com.rappytv.glintcolorizer.api.ItemEffect;
import com.rappytv.glintcolorizer.core.GlintColorizerAddon;
import com.rappytv.glintcolorizer.v1_21_3.ColoredVertexConsumer;
import com.rappytv.glintcolorizer.v1_21_3.CustomRenderTypes;
import net.labymod.api.util.Color;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.ItemRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(ItemRenderer.class)
public class MixinItemRenderer {

  @WrapOperation(
      method = "renderItem",
      at = @At(
          value = "INVOKE",
          target = "Lnet/minecraft/client/renderer/entity/ItemRenderer;getFoilBuffer(Lnet/minecraft/client/renderer/MultiBufferSource;Lnet/minecraft/client/renderer/RenderType;ZZ)Lcom/mojang/blaze3d/vertex/VertexConsumer;"
      )
  )
  private VertexConsumer modifyFoilBuffer(
      MultiBufferSource buffer,
      RenderType renderType,
      boolean isItem,
      boolean hasFoil,
      Operation<VertexConsumer> original
  ) {
    if (!hasFoil) {
      return ItemRenderer.getFoilBuffer(buffer, renderType, isItem, false);
    }

    ItemEffect effect = GlintColorizerAddon.getItemEffect();
    if (effect == ItemEffect.NONE) {
      return buffer.getBuffer(renderType);
    }

    Color color = GlintColorizerAddon.getItemGlintColor();

    RenderType glintType;
    if (effect == ItemEffect.GLOW) {
      glintType = isItem
          ? CustomRenderTypes.getGlowGlint()
          : CustomRenderTypes.getGlowEntityGlint();
    } else {
      glintType = isItem ? RenderType.glint() : RenderType.entityGlint();
    }

    VertexConsumer baseBuffer = buffer.getBuffer(renderType);
    VertexConsumer glintBuffer = buffer.getBuffer(glintType);

    if (color != null) {
      return new ColoredVertexConsumer(
          VertexMultiConsumer.create(glintBuffer, baseBuffer),
          color
      );
    }

    return VertexMultiConsumer.create(glintBuffer, baseBuffer);
  }
}

