package com.rappytv.glintcolorizer.v1_12_2.mixins;

import com.rappytv.glintcolorizer.GlintColorizerAddon;
import net.labymod.api.util.Color;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderItem;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(RenderItem.class)
public class MixinRenderItem {

  @Unique
  private boolean sandbox$firstDepthCall = true;

  @Redirect(method = "renderEffect", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/GlStateManager;depthFunc(I)V"))
  private void modifyDepthFunc(int i) {
    if(this.sandbox$firstDepthCall && GlintColorizerAddon.isItemGlowEnabled()) {
      GlStateManager.depthFunc(GL11.GL_EQUAL);
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
