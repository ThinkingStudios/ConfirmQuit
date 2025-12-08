package org.thinkingstudio.confirmquit.client.toast;

import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.text.Text;
import net.minecraft.util.Colors;

public class QuitToast extends BaseToast {
    public QuitToast(Text message, long keepTime) {
        super(Text.translatable("toast.confirmquit.confirm.title"), message, keepTime);
    }

    @Override
    public int getWidth() {
        return 241;
    }

    @Override
    public int getHeight() {
        return 32;
    }

    @Override
    protected void drawToast(DrawContext drawContext, TextRenderer textRenderer) {
        drawContext.drawTexture(RenderLayer::getGuiTextured, TEXTURE_DARK, 0, 0, 0, 0, getWidth(), getHeight(), getWidth(), getHeight());
        drawContext.drawTexture(RenderLayer::getGuiTextured, ICON, 8, 6, 0, 0, 15, 20, 15, 20);
        drawContext.drawTextWithShadow(textRenderer, title, 35, 7, Colors.WHITE);
        drawContext.drawTextWithShadow(textRenderer, message, 35, 18, Colors.WHITE);
    }
}