package org.thinkingstudio.rusurequit.client.screen.style;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.text.Text;

public interface BaseStyle {
    ButtonWidget generateConfirmButtons(Screen screen, ButtonWidget.PressAction onConfirm);

    ButtonWidget generateCancelButtons(Screen screen, ButtonWidget.PressAction onCancel);

    TextFieldWidget generateConfirmTextField(TextRenderer textRenderer, Screen screen);

    void drawConfirmTextError(DrawContext context, TextRenderer textRenderer, Screen screen);

    void render(MinecraftClient client, TextRenderer textRenderer, Screen screen, Text title, Text message, DrawContext context, int mouseX, int mouseY, float delta);
}