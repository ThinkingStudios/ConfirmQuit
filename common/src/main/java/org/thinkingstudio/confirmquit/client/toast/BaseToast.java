package org.thinkingstudio.confirmquit.client.toast;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.toast.Toast;
import net.minecraft.client.toast.ToastManager;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.thinkingstudio.confirmquit.client.ConfirmQuit;

public abstract class BaseToast implements Toast {
    protected static final Identifier texture = new Identifier(ConfirmQuit.MOD_ID, "textures/gui/toasts.png");
    protected final Text title;
    protected final Text message;
    protected final long keepTime;

    protected BaseToast(Text title, Text message, long keepTime) {
        this.title = title;
        this.message = message;
        this.keepTime = keepTime;
    }

    @Override
    public Visibility draw(DrawContext drawContext, ToastManager manager, long startTime) {
        drawToast(drawContext, manager);
        if (startTime >= keepTime) return Visibility.HIDE;
        return Visibility.SHOW;
    }

    protected abstract void drawToast(DrawContext drawContext, ToastManager manager);
}
