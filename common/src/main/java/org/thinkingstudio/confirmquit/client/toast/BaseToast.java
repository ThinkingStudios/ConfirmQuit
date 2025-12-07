package org.thinkingstudio.confirmquit.client.toast;

import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.toast.Toast;
import net.minecraft.client.toast.ToastManager;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.thinkingstudio.confirmquit.client.ConfirmQuit;

public abstract class BaseToast implements Toast {
    protected static final Identifier texture = Identifier.of(ConfirmQuit.MOD_ID, "textures/gui/toasts.png");
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
