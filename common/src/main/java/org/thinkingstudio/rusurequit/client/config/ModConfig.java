package org.thinkingstudio.rusurequit.client.config;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import org.thinkingstudio.rusurequit.client.RuSureQuit;

@Config(name = RuSureQuit.MOD_ID)
public class ModConfig implements ConfigData {
    @ConfigEntry.Gui.EnumHandler(option = ConfigEntry.Gui.EnumHandler.EnumDisplayOption.BUTTON)
    public ConfirmType confirmTypeInFinalQuit = ConfirmType.SCREEN;
    @ConfigEntry.Gui.EnumHandler(option = ConfigEntry.Gui.EnumHandler.EnumDisplayOption.BUTTON)
    public ConfirmType confirmTypeInSingleplayer = ConfirmType.TOAST;
    @ConfigEntry.Gui.EnumHandler(option = ConfigEntry.Gui.EnumHandler.EnumDisplayOption.BUTTON)
    public ConfirmType confirmTypeInMultiplayer = ConfirmType.TOAST;
    @ConfigEntry.Gui.Tooltip
    public boolean enableScreenShortcutKey = true;
    @ConfigEntry.Gui.Tooltip
    @ConfigEntry.BoundedDiscrete(min = 0, max = 10000)
    public long buttonWaitTime = 1000L;
    public boolean enableTextFieldConfirm = true;
    public String textFieldConfirmText = "BocchiChan";
    @ConfigEntry.Gui.EnumHandler(option = ConfigEntry.Gui.EnumHandler.EnumDisplayOption.BUTTON)
    public ScreenStyle confirmScreenStyle = ScreenStyle.CLASSIC;
    @ConfigEntry.Gui.Tooltip
    @ConfigEntry.BoundedDiscrete(min = 0, max = 10000)
    public long toastConfirmDisplayTime = 5000L;
    @ConfigEntry.Gui.Tooltip
    @ConfigEntry.BoundedDiscrete(min = 0, max = 10000)
    public long toastConfirmDelayTime = 500L;
}
