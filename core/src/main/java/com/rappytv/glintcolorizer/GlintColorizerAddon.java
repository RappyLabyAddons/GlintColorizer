package com.rappytv.glintcolorizer;

import net.labymod.api.addon.LabyAddon;
import net.labymod.api.models.addon.annotation.AddonMain;
import net.labymod.api.util.Color;
import org.jetbrains.annotations.Nullable;

@AddonMain
public class GlintColorizerAddon extends LabyAddon<GlintColorizerConfig> {

  private static GlintColorizerAddon instance;

  @Override
  protected void enable() {
    instance = this;

    this.registerSettingCategory();
  }

  @Override
  protected Class<? extends GlintColorizerConfig> configurationClass() {
    return GlintColorizerConfig.class;
  }

  public static boolean isItemGlowEnabled() {
    return instance.configuration().enabled().get() && instance.configuration().itemGlowEffect().get();
  }

  @Nullable
  public static Color getItemGlintColor() {
    if(!instance.configuration().enabled().get() || !instance.configuration().itemGlintEffect().get()) {
      return null;
    }
    return instance.configuration().itemGlintColor().get();
  }

  @Nullable
  public static Color getArmorGlintColor() {
    if(!instance.configuration().enabled().get() || !instance.configuration().armorGlintEffect().get()) {
      return null;
    }
    return instance.configuration().armorGlintColor().get();
  }
}
