package com.rappytv.glintcolorizer.core;

import com.rappytv.glintcolorizer.api.ItemEffect;
import net.labymod.api.addon.LabyAddon;
import net.labymod.api.models.addon.annotation.AddonMain;
import net.labymod.api.util.Color;
import org.jetbrains.annotations.Nullable;

@AddonMain
public class GlintColorizerAddon extends LabyAddon<GlintColorizerConfig> {

  private static GlintColorizerAddon INSTANCE;

  @Override
  protected void enable() {
    INSTANCE = this;

    this.registerSettingCategory();
  }

  @Override
  protected Class<? extends GlintColorizerConfig> configurationClass() {
    return GlintColorizerConfig.class;
  }

  public static ItemEffect getItemEffect() {
    return INSTANCE.configuration().enabled().get()
        ? INSTANCE.configuration().itemEffect().get()
        : ItemEffect.DEFAULT;
  }

  @Nullable
  public static Color getItemGlintColor() {
    if (!INSTANCE.configuration().enabled().get()
        || !INSTANCE.configuration().enableCustomItemGlintColor().get()) {
      return null;
    }
    return INSTANCE.configuration().customItemGlintColor().get();
  }

  @Nullable
  public static Color getArmorGlintColor() {
    if (!INSTANCE.configuration().enabled().get()
        || !INSTANCE.configuration().enableCustomArmorGlintColor().get()) {
      return null;
    }
    return INSTANCE.configuration().customArmorGlintColor().get();
  }
}
