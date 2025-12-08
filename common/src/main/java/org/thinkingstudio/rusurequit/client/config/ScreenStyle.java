package org.thinkingstudio.rusurequit.client.config;

import org.thinkingstudio.rusurequit.client.screen.style.BaseStyle;
import org.thinkingstudio.rusurequit.client.screen.style.BedrockStyle;
import org.thinkingstudio.rusurequit.client.screen.style.ClassicStyle;
import net.minecraft.text.Text;

import java.util.function.Supplier;

public enum ScreenStyle {
    CLASSIC(Text.translatable("config.rusurequit.screentype.classic"), ClassicStyle::new),
    BEDROCK(Text.translatable("config.rusurequit.screentype.bedrock"), BedrockStyle::new);
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
