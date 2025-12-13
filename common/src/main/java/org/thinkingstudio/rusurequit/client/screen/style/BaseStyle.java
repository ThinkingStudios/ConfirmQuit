package org.thinkingstudio.rusurequit.client.screen.style;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.render.*;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public abstract class BaseStyle {
    public static final Identifier INWORLD_MENU_BACKGROUND_TEXTURE = new Identifier("textures/gui/inworld_menu_background.png");

    public abstract ButtonWidget generateConfirmButtons(Screen screen, ButtonWidget.PressAction onConfirm);

    public abstract ButtonWidget generateCancelButtons(Screen screen, ButtonWidget.PressAction onCancel);

    public abstract TextFieldWidget generateConfirmTextField(TextRenderer textRenderer, Screen screen);

    public abstract void drawConfirmTextError(DrawContext drawContext, TextRenderer textRenderer, Screen screen);

    public abstract void render(MinecraftClient client, TextRenderer textRenderer, Screen screen, Text title, Text message,
                                DrawContext drawContext, int mouseX, int mouseY, float delta);

    protected void renderBackground(int startX, int startY, int endX, int endY, Identifier identifier, DrawContext drawContext) {
        int width = endX - startX;
        int height = endY - startY;
        drawContext.drawTexture(identifier, startX + 4, startY + 4, 0, 0, width - 4, height - 4, 32, 32);
        drawContext.fillGradient(startX, startY, endX, endY, -1072689136, -804253680);
    }
}