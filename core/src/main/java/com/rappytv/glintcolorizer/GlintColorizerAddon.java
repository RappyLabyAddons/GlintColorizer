package com.rappytv.glintcolorizer;

import com.rappytv.glintcolorizer.GlintColorizerConfig.ItemEffect;
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

  public static ItemEffect getItemEffect() {
    return instance.configuration().enabled().get()
        ? instance.configuration().itemEffect().get()
        : ItemEffect.DEFAULT;
  }

  @Nullable
  public static Color getItemGlintColor() {
    if(!instance.configuration().enabled().get()
        || !instance.configuration().enableCustomItemGlintColor().get()) {
      return null;
    }
    return instance.configuration().customItemGlintColor().get();
  }

  @Nullable
  public static Color getArmorGlintColor() {
    if(!instance.configuration().enabled().get()
        || !instance.configuration().enableCustomArmorGlintColor().get()) {
      return null;
    }
    return instance.configuration().customArmorGlintColor().get();
  }
}
