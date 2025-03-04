package com.rappytv.glintcolorizer.v1_8_9.mixins;

import com.rappytv.glintcolorizer.GlintColorizerAddon;
import com.rappytv.glintcolorizer.GlintColorizerConfig.ItemEffect;
import net.labymod.api.util.Color;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.resources.model.IBakedModel;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(RenderItem.class)
public class MixinRenderItem {

  @Unique
  private boolean sandbox$firstDepthCall = true;

  @Inject(method = "renderEffect", at = @At("HEAD"), cancellable = true)
  private void renderEffect(IBakedModel model, CallbackInfo ci) {
    if(GlintColorizerAddon.getItemEffect() == ItemEffect.NONE) {
      ci.cancel();
    }
  }

  @Redirect(method = "renderEffect", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/GlStateManager;depthFunc(I)V"))
  private void modifyDepthFunc(int i) {
    if(this.sandbox$firstDepthCall) {
      if(GlintColorizerAddon.getItemEffect() != ItemEffect.GLOW) {
        GlStateManager.depthFunc(GL11.GL_EQUAL);
      }
    } else {
      GlStateManager.depthFunc(i);
    }
    this.sandbox$firstDepthCall = !this.sandbox$firstDepthCall;
  }

  @ModifyConstant(method = "renderEffect", constant = @Constant(intValue = -8372020))
  private int modifyGlintColor(int original) {
    Color color = GlintColorizerAddon.getItemGlintColor();
    return color != null ? color.get() : original;
  }
}
