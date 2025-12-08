package org.thinkingstudio.rusurequit.client.toast;

import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.toast.Toast;
import net.minecraft.client.toast.ToastManager;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.thinkingstudio.rusurequit.client.RuSureQuit;

public abstract class BaseToast implements Toast {
    protected static final Identifier TEXTURE_DARK = Identifier.of(RuSureQuit.MOD_ID, "textures/gui/toast/background_dark.png");
    protected static final Identifier TEXTURE_LIGHT = Identifier.of(RuSureQuit.MOD_ID, "textures/gui/toast/background_light.png");
    protected static final Identifier ICON = Identifier.of(RuSureQuit.MOD_ID, "textures/gui/toast/icon.png");

    protected final Text title;
    protected final Text message;
    protected final long keepTime;
    protected long startTime;
    protected Visibility visibility;

    protected BaseToast(Text title, Text message, long keepTime) {
        this.title = title;
        this.message = message;
        this.keepTime = keepTime;
    }

    @Override
    public void draw(DrawContext drawContext, TextRenderer textRenderer, long startTime) {
        drawToast(drawContext, textRenderer);
        this.startTime = startTime;
    }

    @Override
    public Visibility getVisibility() {
        return this.visibility;
    }

    @Override
    public void update(ToastManager manager, long time) {
        this.visibility = startTime >= keepTime ? Visibility.HIDE : Visibility.SHOW;
    }

    protected abstract void drawToast(DrawContext drawContext, TextRenderer textRenderer);
}