package com.rappytv.glintcolorizer.v1_12_2.mixins;

import com.rappytv.glintcolorizer.GlintColorizerAddon;
import net.labymod.api.util.Color;
import net.minecraft.client.renderer.entity.layers.LayerArmorBase;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(LayerArmorBase.class)
public class MixinLayerArmorBase {

  @ModifyConstant(method = "renderEnchantedGlint", constant = @Constant(floatValue = 0.38f))
  private static float modifyGlintColor1(float original) {
    return sandbox$getGlintColor(0, original);
  }

  @ModifyConstant(method = "renderEnchantedGlint", constant = @Constant(floatValue = 0.19f))
  private static float modifyGlintColor2(float original) {
    return sandbox$getGlintColor(1, original);
  }

  @ModifyConstant(method = "renderEnchantedGlint", constant = @Constant(floatValue = 0.608f))
  private static float modifyGlintColor3(float original) {
    return sandbox$getGlintColor(2, original);
  }

  @Unique
  private static float sandbox$getGlintColor(int index, float original) {
    Color color = GlintColorizerAddon.getArmorGlintColor();
    if(color == null) {
      return original;
    }
    return switch (index) {
      case 0 -> color.getRed();
      case 1 -> color.getGreen();
      case 2 -> color.getBlue();
      default -> original;
    };
  }
}
