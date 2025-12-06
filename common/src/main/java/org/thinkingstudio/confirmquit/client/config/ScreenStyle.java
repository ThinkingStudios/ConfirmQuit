package org.thinkingstudio.confirmquit.client.config;

import org.thinkingstudio.confirmquit.client.screen.style.BaseStyle;
import org.thinkingstudio.confirmquit.client.screen.style.BedrockStyle;
import org.thinkingstudio.confirmquit.client.screen.style.ClassicStyle;
import net.minecraft.text.Text;

import java.util.function.Supplier;

public enum ScreenStyle {
    CLASSIC(Text.translatable("config.confirmquit.screentype.classic"), ClassicStyle::new),
    BEDROCK(Text.translatable("config.confirmquit.screentype.bedrock"), BedrockStyle::new);
    public final Text displayName;
    public final Supplier<BaseStyle> baseStyleSupplier;

    ScreenStyle(Text displayName, Supplier<BaseStyle> baseStyleSupplier) {
        this.displayName = displayName;
        this.baseStyleSupplier = baseStyleSupplier;
    }

    @Override
    public String toString() {
        return displayName.getString();
    }
}
