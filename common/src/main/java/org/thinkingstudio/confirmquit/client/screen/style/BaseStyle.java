package org.thinkingstudio.confirmquit.client.screen.style;

import com.mojang.blaze3d.systems.RenderSystem;
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

    public abstract ButtonWidget generateConfirmButtons(Screen screen, ButtonWidget.PressAction onConfirm);

    public abstract ButtonWidget generateCancelButtons(Screen screen, ButtonWidget.PressAction onCancel);

    public abstract TextFieldWidget generateConfirmTextField(TextRenderer textRenderer, Screen screen);

    public abstract void drawConfirmTextError(DrawContext drawContext, TextRenderer textRenderer, Screen screen);

    public abstract void render(MinecraftClient client, TextRenderer textRenderer, Screen screen, Text title, Text message,
                                DrawContext drawContext, int mouseX, int mouseY, float delta);

    protected void renderBackground(int startX, int startY, int endX, int endY, Identifier identifier) {
        int width = endX - startX;
        int height = endY - startY;
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferBuilder = tessellator.begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION_TEXTURE_COLOR);
        RenderSystem.setShader(GameRenderer::getPositionTexColorProgram);
        RenderSystem.setShaderTexture(0, identifier);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        bufferBuilder.vertex(startX, endY, 0.0F).texture(0.0F, height / 32.0F).color(64, 64, 64, 255);
        bufferBuilder.vertex(endX, endY, 0.0F).texture(width / 32.0F, height / 32.0F).color(64, 64, 64, 255);
        bufferBuilder.vertex(endX, startY, 0.0F).texture(width / 32.0F, 0).color(64, 64, 64, 255);
        bufferBuilder.vertex(startX, startY, 0.0F).texture(0.0F, 0).color(64, 64, 64, 255);
        BufferRenderer.drawWithGlobalProgram(bufferBuilder.end());
    }
}