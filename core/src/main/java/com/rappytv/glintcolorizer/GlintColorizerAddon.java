package com.rappytv.glintcolorizer;

import net.labymod.api.addon.LabyAddon;
import net.labymod.api.models.addon.annotation.AddonMain;

@AddonMain
public class GlintColorizerAddon extends LabyAddon<GlintColorizerConfig> {

  @Override
  protected void enable() {
    this.registerSettingCategory();
  }

  @Override
  protected Class<? extends GlintColorizerConfig> configurationClass() {
    return GlintColorizerConfig.class;
  }
}
