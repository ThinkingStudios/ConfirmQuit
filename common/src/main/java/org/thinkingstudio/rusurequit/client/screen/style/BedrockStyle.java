package org.thinkingstudio.rusurequit.client.screen.style;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.screen.ScreenTexts;
import net.minecraft.text.Text;
import net.minecraft.util.Colors;
import net.minecraft.util.Identifier;
import org.thinkingstudio.rusurequit.client.RuSureQuit;
import org.thinkingstudio.rusurequit.client.config.ConfigHelper;

public class BedrockStyle extends BaseStyle {
    private static final Identifier WINDOW_TEXTURE = Identifier.of(RuSureQuit.MOD_ID, "textures/gui/style/bedrock/window.png");
    private static final Identifier BACKGROUND = Identifier.of(RuSureQuit.MOD_ID, "textures/gui/style/bedrock/background.png");

    // 窗口宽度
    private static final int windowWidth = 252;
    // 窗口长度
    private static final int windowHeight = 140;
    // 按钮旁边距
    private static final int buttonLRMargin = 20;
    // 按钮下边距
    private static final int buttonBMargin = 20;
    // 按钮宽度
    private static final int buttonWidth = 100;
    // 按钮长度
    private static final int buttonHeight = 20;
    // 信息文本下边距
    private static final int messageBMargin = 100;

    private Text textFieldMessage;

    @Override
    public ButtonWidget generateConfirmButtons(Screen screen, ButtonWidget.PressAction onConfirm) {
        return ButtonWidget.builder(ScreenTexts.YES, onConfirm)
                .dimensions((screen.width - windowWidth) / 2 + windowWidth - buttonWidth - buttonLRMargin,
                        (screen.height - windowHeight) / 2 + windowHeight - buttonBMargin - buttonHeight,
                        buttonWidth, buttonHeight).build();
    }

    @Override
    public ButtonWidget generateCancelButtons(Screen screen, ButtonWidget.PressAction onCancel) {
        return ButtonWidget.builder(ScreenTexts.NO, onCancel)
                .dimensions((screen.width - windowWidth) / 2 + buttonLRMargin,
                        (screen.height - windowHeight) / 2 + windowHeight - buttonBMargin - buttonHeight,
                        buttonWidth, buttonHeight).build();
    }

    @Override
    public TextFieldWidget generateConfirmTextField(TextRenderer textRenderer, Screen screen) {
        this.textFieldMessage = Text.translatable("screen.rusurequit.confirm.textfield", ConfigHelper.getConfig().textFieldConfirmText);
        TextFieldWidget confirmTextField = new TextFieldWidget(textRenderer, screen.width / 2 - 100, (screen.height - windowHeight) / 2 + windowHeight - messageBMargin + 15, 200, 20, textFieldMessage);
        confirmTextField.setVisible(ConfigHelper.getConfig().enableTextFieldConfirm);

        return confirmTextField;
    }

    @Override
    public void drawConfirmTextError(DrawContext drawContext, TextRenderer textRenderer, Screen screen) {
        drawContext.drawCenteredTextWithShadow(textRenderer, Text.translatable("screen.rusurequit.confirm.textfield.error"),
                screen.width / 2,
                (screen.height - windowHeight) / 2 + windowHeight - messageBMargin + 45,
                Colors.RED);
    }

    @Override
    public void render(MinecraftClient client, TextRenderer textRenderer, Screen screen, Text title, Text message,
                       DrawContext drawContext, int mouseX, int mouseY, float delta) {
        drawBackground(client, screen, drawContext, mouseX, mouseY, delta);
        drawWindow(textRenderer, title, drawContext, (screen.width - windowWidth) / 2, (screen.height - windowHeight) / 2);
        drawMessage(textRenderer, screen, message, drawContext);
    }

    private void drawBackground(MinecraftClient client, Screen screen, DrawContext drawContext, int mouseX, int mouseY, float delta) {
        if (client.world != null) {
            drawContext.fillGradient(0, 0, screen.width, screen.height, -1072689136, -804253680);
        } else {
            screen.renderBackground(drawContext, mouseX, mouseY, delta);
        }
    }

    private void drawWindow(TextRenderer textRenderer, Text title, DrawContext drawContext, int x, int y) {
        renderBackground(x, y, x + windowWidth, y + windowHeight, BACKGROUND, drawContext);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.enableBlend();
        RenderSystem.setShaderTexture(0, WINDOW_TEXTURE);
        drawContext.drawTexture(RenderLayer::getGuiTextured, WINDOW_TEXTURE, x, y, 0.0F, 0.0F, 252, 140, 252, 140);
        drawContext.drawText(textRenderer, title, x + 8, y + 6, 4210752, false);
    }


    private void drawMessage(TextRenderer textRenderer, Screen screen, Text message, DrawContext drawContext) {
        drawContext.drawCenteredTextWithShadow(textRenderer, message,
                screen.width / 2,
                (screen.height - windowHeight) / 2 + windowHeight - messageBMargin,
                Colors.WHITE);
        if (ConfigHelper.getConfig().enableTextFieldConfirm) {
            drawContext.drawCenteredTextWithShadow(textRenderer, textFieldMessage,
                    screen.width / 2,
                    (screen.height - windowHeight) / 2 + windowHeight - messageBMargin - 15,
                    Colors.WHITE);
        }
    }
}