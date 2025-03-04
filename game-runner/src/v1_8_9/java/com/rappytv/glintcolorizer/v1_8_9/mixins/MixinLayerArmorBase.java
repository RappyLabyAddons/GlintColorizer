package com.rappytv.glintcolorizer.v1_8_9.mixins;

import com.rappytv.glintcolorizer.GlintColorizerAddon;
import net.labymod.api.util.Color;
import net.minecraft.client.renderer.entity.layers.LayerArmorBase;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(LayerArmorBase.class)
public class MixinLayerArmorBase {

  @Unique
  private boolean glintcolorizer$firstPointFiceOcurrence = true;

  @ModifyConstant(method = "renderGlint", constant = @Constant(floatValue = 0.76F))
  private float modifyColorMultiplier(float original) {
    float color = glintcolorizer$getGlintColor(0, original);
    if(color == original) {
      return original;
    }
    return 1F;
  }

  @ModifyConstant(method = "renderGlint", constant = @Constant(floatValue = 0.5F))
  private float modifyGlintColor1(float original) {
    if(this.glintcolorizer$firstPointFiceOcurrence) {
      this.glintcolorizer$firstPointFiceOcurrence = false;
      return original;
    }
    this.glintcolorizer$firstPointFiceOcurrence = true;
    return glintcolorizer$getGlintColor(0, original);
  }

  @ModifyConstant(method = "renderGlint", constant = @Constant(floatValue = 0.25f))
  private float modifyGlintColor2(float original) {
    return glintcolorizer$getGlintColor(1, original);
  }

  @ModifyConstant(method = "renderGlint", constant = @Constant(floatValue = 0.8f))
  private float modifyGlintColor3(float original) {
    return glintcolorizer$getGlintColor(2, original);
  }

  @Unique
  private static float glintcolorizer$getGlintColor(int index, float original) {
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
