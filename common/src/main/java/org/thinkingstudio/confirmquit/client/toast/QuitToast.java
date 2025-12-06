package org.thinkingstudio.confirmquit.client.toast;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.toast.ToastManager;
import net.minecraft.text.Text;

import java.awt.*;

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
    protected void drawToast(DrawContext drawContext, ToastManager manager) {
        RenderSystem.setShaderTexture(0, texture);
        RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
        drawContext.drawTexture(texture, 0, 0, 0, 0, getWidth(), getHeight());
        drawContext.drawTexture(texture, 8, 0, 242, 0, 15, 30);
        drawContext.drawTextWithShadow(manager.getClient().textRenderer, title, 35, 7, Color.WHITE.getRGB());
        drawContext.drawTextWithShadow(manager.getClient().textRenderer,  message, 35, 18, Color.WHITE.getRGB());
    }
}
