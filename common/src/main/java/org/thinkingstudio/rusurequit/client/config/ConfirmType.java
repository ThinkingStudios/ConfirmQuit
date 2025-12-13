package org.thinkingstudio.rusurequit.client.config;

import net.minecraft.text.Text;

public enum ConfirmType {
    TOAST(Text.translatable("config.rusurequit.confirmtype.toast")),
    SCREEN(Text.translatable("config.rusurequit.confirmtype.screen")),
    NONE(Text.translatable("config.rusurequit.confirmtype.none"));
    public final Text displayName;

    ConfirmType(Text displayName) {
        this.displayName = displayName;
    }

    @Override
    public String toString() {
        return displayName.getString();
    }
}
