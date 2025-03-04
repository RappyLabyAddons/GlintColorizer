package com.rappytv.glintcolorizer;

import net.labymod.api.addon.AddonConfig;
import net.labymod.api.client.component.format.NamedTextColor;
import net.labymod.api.client.gui.screen.widget.widgets.input.SwitchWidget.SwitchSetting;
import net.labymod.api.client.gui.screen.widget.widgets.input.color.ColorPickerWidget.ColorPickerSetting;
import net.labymod.api.client.gui.screen.widget.widgets.input.dropdown.DropdownWidget.DropdownSetting;
import net.labymod.api.configuration.loader.annotation.SpriteSlot;
import net.labymod.api.configuration.loader.annotation.SpriteTexture;
import net.labymod.api.configuration.loader.property.ConfigProperty;
import net.labymod.api.configuration.settings.annotation.SettingRequires;
import net.labymod.api.configuration.settings.annotation.SettingSection;
import net.labymod.api.util.Color;

@SpriteTexture("settings.png")
public class GlintColorizerConfig extends AddonConfig {

  @SpriteSlot
  @SwitchSetting
  private final ConfigProperty<Boolean> enabled = new ConfigProperty<>(true);

  @SettingSection("items")
  @SpriteSlot(x = 1)
  @DropdownSetting
  private final ConfigProperty<ItemEffect> itemEffect = new ConfigProperty<>(ItemEffect.DEFAULT);
  @SpriteSlot(size = 32, x = 2)
  @SwitchSetting
  private final ConfigProperty<Boolean> enableCustomItemGlintColor = new ConfigProperty<>(true);
  @SettingRequires("enableCustomItemGlintColor")
  @SpriteSlot(size = 32, x = 3)
  @ColorPickerSetting(chroma = true)
  private final ConfigProperty<Color> customItemGlintColor = new ConfigProperty<>(Color.WHITE);

  @SettingSection("armor")
  @SpriteSlot(x = 2)
  @SwitchSetting
  private final ConfigProperty<Boolean> enableCustomArmorGlintColor = new ConfigProperty<>(true);
  @SettingRequires("enableCustomArmorGlintColor")
  @SpriteSlot(size = 32, x = 3)
  @ColorPickerSetting(chroma = true)
  private final ConfigProperty<Color> customArmorGlintColor = new ConfigProperty<>(NamedTextColor.AQUA.color());

  @Override
  public ConfigProperty<Boolean> enabled() {
    return this.enabled;
  }

  public ConfigProperty<ItemEffect> itemEffect() {
    return this.itemEffect;
  }
  public ConfigProperty<Boolean> enableCustomItemGlintColor() {
    return this.enableCustomItemGlintColor;
  }
  public ConfigProperty<Color> customItemGlintColor() {
    return this.customItemGlintColor;
  }

  public ConfigProperty<Boolean> enableCustomArmorGlintColor() {
    return this.enableCustomArmorGlintColor;
  }
  public ConfigProperty<Color> customArmorGlintColor() {
    return this.customArmorGlintColor;
  }

  public enum ItemEffect {
    NONE,
    DEFAULT,
    GLOW
  }
}
