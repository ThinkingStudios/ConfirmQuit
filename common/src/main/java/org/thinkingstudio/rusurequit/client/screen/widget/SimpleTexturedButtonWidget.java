package org.thinkingstudio.rusurequit.client.screen.widget;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gl.RenderPipelines;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ButtonTextures;
import net.minecraft.client.gui.widget.TexturedButtonWidget;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;

public class SimpleTexturedButtonWidget extends TexturedButtonWidget {
    public SimpleTexturedButtonWidget(int x, int y, int width, int height, ButtonTextures textures, PressAction pressAction) {
        super(x, y, width, height, textures, pressAction);
    }

    public SimpleTexturedButtonWidget(int x, int y, int width, int height, ButtonTextures textures, PressAction pressAction, Text text) {
        super(x, y, width, height, textures, pressAction, text);
    }

    public SimpleTexturedButtonWidget(int width, int height, ButtonTextures textures, PressAction pressAction, Text text) {
        super(width, height, textures, pressAction, text);
    }

    public static SimpleTexturedButtonWidget create(int x, int y, int width, int height, ButtonTextures textures, PressAction pressAction, Text text) {
        return new SimpleTexturedButtonWidget(x, y, width, height, textures, pressAction, text);
    }

    public static SimpleTexturedButtonWidget create(int x, int y, int width, int height, ButtonTextures textures, PressAction pressAction) {
        return new SimpleTexturedButtonWidget(x, y, width, height, textures, pressAction);
    }

    public static SimpleTexturedButtonWidget create(int width, int height, ButtonTextures textures, PressAction pressAction, Text text) {
        return new SimpleTexturedButtonWidget(width, height, textures, pressAction, text);
    }

    @Override
    public void renderWidget(DrawContext context, int mouseX, int mouseY, float delta) {
        MinecraftClient client = MinecraftClient.getInstance();
        Identifier identifier = this.textures.get(this.isNarratable(), this.isSelected());
        context.drawGuiTexture(RenderPipelines.GUI_TEXTURED, identifier, this.getX(), this.getY(), this.width, this.height);
        this.drawMessage(context, client.textRenderer, this.active ? 16777215 : 10526880 | MathHelper.ceil(this.alpha * 255.0F) << 24);
    }
}
