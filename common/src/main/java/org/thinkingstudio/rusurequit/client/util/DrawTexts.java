package org.thinkingstudio.rusurequit.client.util;

import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.text.OrderedText;
import net.minecraft.text.Text;

public class DrawTexts {

    public static void drawCenteredText(DrawContext drawContext, TextRenderer textRenderer, Text text, int centerX, int y, int color) {
        OrderedText orderedText = text.asOrderedText();
        drawText(drawContext, textRenderer, orderedText, centerX - textRenderer.getWidth(orderedText) / 2, y, color);
    }

    public static void drawText(DrawContext drawContext, TextRenderer textRenderer, OrderedText text, int x, int y, int color) {
        drawContext.drawText(textRenderer, text, x, y, color, false);
    }
}
