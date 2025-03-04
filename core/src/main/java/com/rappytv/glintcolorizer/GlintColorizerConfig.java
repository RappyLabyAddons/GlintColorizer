package com.rappytv.glintcolorizer;

import net.labymod.api.addon.AddonConfig;
import net.labymod.api.client.component.format.NamedTextColor;
import net.labymod.api.client.gui.screen.widget.widgets.input.SwitchWidget.SwitchSetting;
import net.labymod.api.client.gui.screen.widget.widgets.input.color.ColorPickerWidget.ColorPickerSetting;
import net.labymod.api.configuration.loader.annotation.SpriteSlot;
import net.labymod.api.configuration.loader.annotation.SpriteTexture;
import net.labymod.api.configuration.loader.property.ConfigProperty;
import net.labymod.api.configuration.settings.annotation.SettingSection;
import net.labymod.api.util.Color;

@SpriteTexture("settings.png")
public class GlintColorizerConfig extends AddonConfig {

  @SpriteSlot
  @SwitchSetting
  private final ConfigProperty<Boolean> enabled = new ConfigProperty<>(true);

  @SettingSection("items")
  @SpriteSlot(x = 1)
  @SwitchSetting
  private final ConfigProperty<Boolean> itemGlowEffect = new ConfigProperty<>(false);
  @SpriteSlot(size = 32, x = 2)
  @SwitchSetting
  private final ConfigProperty<Boolean> itemGlintEffect = new ConfigProperty<>(true);
  @SpriteSlot(size = 32, x = 3)
  @ColorPickerSetting(chroma = true)
  private final ConfigProperty<Color> itemGlintColor = new ConfigProperty<>(Color.WHITE);

  @SettingSection("armor")
  @SpriteSlot(x = 2)
  @SwitchSetting
  private final ConfigProperty<Boolean> armorGlintEffect = new ConfigProperty<>(true);
  @SpriteSlot(size = 32, x = 3)
  @ColorPickerSetting(chroma = true)
  private final ConfigProperty<Color> armorGlintColor = new ConfigProperty<>(NamedTextColor.AQUA.color());

  @Override
  public ConfigProperty<Boolean> enabled() {
    return this.enabled;
  }

  public ConfigProperty<Boolean> itemGlowEffect() {
    return this.itemGlowEffect;
  }
  public ConfigProperty<Boolean> itemGlintEffect() {
    return this.itemGlintEffect;
  }
  public ConfigProperty<Color> itemGlintColor() {
    return this.itemGlintColor;
  }

  public ConfigProperty<Boolean> armorGlintEffect() {
    return this.armorGlintEffect;
  }
  public ConfigProperty<Color> armorGlintColor() {
    return this.armorGlintColor;
  }
}
