package org.thinkingstudio.rusurequit.client;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import org.thinkingstudio.rusurequit.client.config.ConfigHelper;
import org.thinkingstudio.rusurequit.client.config.ConfirmType;
import org.thinkingstudio.rusurequit.client.config.ModConfig;
import org.thinkingstudio.rusurequit.client.event.ButtonPressEvent;
import org.thinkingstudio.rusurequit.client.event.ClientScheduleStopEvent;
import org.thinkingstudio.rusurequit.client.event.base.api.EventResult;
import org.thinkingstudio.rusurequit.client.handle.ToastQuitHandler;
import org.thinkingstudio.rusurequit.client.screen.ConfirmScreen;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.GameMenuScreen;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableTextContent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RuSureQuit {
    public static final String MOD_ID = "rusurequit";
    public static final Logger LOGGER = LoggerFactory.getLogger("RuSureQuit");
    private static final ToastQuitHandler toastInFinalQuitHandler = new ToastQuitHandler(Text.translatable("toast.rusurequit.confirm.content.infinal"));
    private static final ToastQuitHandler toastInSinglePlayerQuitHandle = new ToastQuitHandler(Text.translatable("toast.rusurequit.confirm.content.insingleplay"));
    private static final ToastQuitHandler toastInMultiplayerQuitHandle = new ToastQuitHandler(Text.translatable("toast.rusurequit.confirm.content.inmultiplay"));

    public static void onInitClient() {
        AutoConfig.register(ModConfig.class, GsonConfigSerializer::new);
        ClientScheduleStopEvent.CLIENT_SCHEDULE_STOP.register(() -> {
            if (ConfigHelper.getConfig().confirmTypeInFinalQuit == ConfirmType.TOAST) {
                return toastInFinalQuitHandler.trigger();
            }
            if (ConfigHelper.getConfig().confirmTypeInFinalQuit == ConfirmType.SCREEN) {
                MinecraftClient.getInstance().setScreen(new ConfirmScreen(MinecraftClient.getInstance().currentScreen, Text.translatable("screen.rusurequit.confirm.content.infinal"), () -> MinecraftClient.getInstance().scheduleStop()));
                return EventResult.CANCEL;
            }
            return EventResult.PASS;
        });

        ButtonPressEvent.BUTTON_PRESS.register((button) -> {
            if (!(MinecraftClient.getInstance().currentScreen instanceof GameMenuScreen)) {
                return EventResult.PASS;
            }

            String key = null;
            if (button.getMessage() instanceof TranslatableTextContent) {
                key = ((TranslatableTextContent) button.getMessage()).getKey();
            } else if (button.getMessage().getContent() instanceof TranslatableTextContent) {
                key = ((TranslatableTextContent) button.getMessage().getContent()).getKey();
            }
            if ("menu.returnToMenu".equals(key)) {
                if (ConfigHelper.getConfig().confirmTypeInSingleplayer == ConfirmType.TOAST) {
                    return toastInSinglePlayerQuitHandle.trigger();
                }
                if (ConfigHelper.getConfig().confirmTypeInSingleplayer == ConfirmType.SCREEN) {
                    MinecraftClient.getInstance().setScreen(new ConfirmScreen(MinecraftClient.getInstance().currentScreen, Text.translatable("screen.rusurequit.confirm.content.insingleplay"), button::onPress));
                    return EventResult.CANCEL;
                }
                return EventResult.PASS;
            }
            if ("menu.disconnect".equals(key)) {
                if (ConfigHelper.getConfig().confirmTypeInMultiplayer == ConfirmType.TOAST) {
                    return toastInMultiplayerQuitHandle.trigger();
                }
                if (ConfigHelper.getConfig().confirmTypeInMultiplayer == ConfirmType.SCREEN) {
                    MinecraftClient.getInstance().setScreen(new ConfirmScreen(MinecraftClient.getInstance().currentScreen, Text.translatable("screen.rusurequit.confirm.content.inmultiplay"), button::onPress));
                    return EventResult.CANCEL;
                }
                return EventResult.PASS;
            }
            return EventResult.PASS;
        });
    }
}
