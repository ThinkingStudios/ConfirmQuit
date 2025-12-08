package org.thinkingstudio.confirmquit.client;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import net.minecraft.client.input.MouseInput;
import net.minecraft.client.util.InputUtil;
import org.thinkingstudio.confirmquit.client.config.ConfigHelper;
import org.thinkingstudio.confirmquit.client.config.ConfirmType;
import org.thinkingstudio.confirmquit.client.config.ModConfig;
import org.thinkingstudio.confirmquit.client.event.ButtonPressEvent;
import org.thinkingstudio.confirmquit.client.event.ClientScheduleStopEvent;
import org.thinkingstudio.confirmquit.client.event.base.api.EventResult;
import org.thinkingstudio.confirmquit.client.handle.ToastQuitHandler;
import org.thinkingstudio.confirmquit.client.screen.ConfirmScreen;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.GameMenuScreen;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableTextContent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConfirmQuit {
    public static final String MOD_ID = "confirmquit";
    public static final Logger LOGGER = LoggerFactory.getLogger("ConfirmQuit");
    private static final ToastQuitHandler toastInFinalQuitHandler = new ToastQuitHandler(Text.translatable("toast.confirmquit.confirm.content.infinal"));
    private static final ToastQuitHandler toastInSinglePlayerQuitHandle = new ToastQuitHandler(Text.translatable("toast.confirmquit.confirm.content.insingleplay"));
    private static final ToastQuitHandler toastInMultiplayerQuitHandle = new ToastQuitHandler(Text.translatable("toast.confirmquit.confirm.content.inmultiplay"));

    public static void onInitClient() {
        AutoConfig.register(ModConfig.class, GsonConfigSerializer::new);
        ClientScheduleStopEvent.CLIENT_SCHEDULE_STOP.register(() -> {
            if (ConfigHelper.getConfig().confirmTypeInFinalQuit == ConfirmType.TOAST) {
                return toastInFinalQuitHandler.trigger();
            }
            if (ConfigHelper.getConfig().confirmTypeInFinalQuit == ConfirmType.SCREEN) {
                MinecraftClient.getInstance().setScreen(new ConfirmScreen(MinecraftClient.getInstance().currentScreen, Text.translatable("screen.confirmquit.confirm.content.infinal"), () -> MinecraftClient.getInstance().scheduleStop()));
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
                    MinecraftClient.getInstance().setScreen(new ConfirmScreen(MinecraftClient.getInstance().currentScreen, Text.translatable("screen.confirmquit.confirm.content.insingleplay"), () -> button.onPress(new MouseInput(InputUtil.GLFW_MOUSE_BUTTON_LEFT, 0))));
                    return EventResult.CANCEL;
                }
                return EventResult.PASS;
            }
            if ("menu.disconnect".equals(key)) {
                if (ConfigHelper.getConfig().confirmTypeInMultiplayer == ConfirmType.TOAST) {
                    return toastInMultiplayerQuitHandle.trigger();
                }
                if (ConfigHelper.getConfig().confirmTypeInMultiplayer == ConfirmType.SCREEN) {
                    MinecraftClient.getInstance().setScreen(new ConfirmScreen(MinecraftClient.getInstance().currentScreen, Text.translatable("screen.confirmquit.confirm.content.inmultiplay"), () -> button.onPress(new MouseInput(InputUtil.GLFW_MOUSE_BUTTON_LEFT, 0))));
                    return EventResult.CANCEL;
                }
                return EventResult.PASS;
            }
            return EventResult.PASS;
        });
    }
}