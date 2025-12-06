package org.thinkingstudio.confirmquit.client.config;

import net.minecraft.text.Text;

public enum ConfirmType {
    TOAST(Text.translatable("config.confirmquit.confirmtype.toast")),
    SCREEN(Text.translatable("config.confirmquit.confirmtype.screen")),
    NONE(Text.translatable("config.confirmquit.confirmtype.none"));
    public final Text displayName;

    ConfirmType(Text displayName) {
        this.displayName = displayName;
    }

    @Override
    public String toString() {
        return displayName.getString();
    }
}
