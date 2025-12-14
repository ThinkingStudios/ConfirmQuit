package org.thinkingstudio.rusurequit.client.screen.style;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gl.RenderPipelines;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.screen.ScreenTexts;
import net.minecraft.text.Text;
import net.minecraft.util.Colors;
import net.minecraft.util.Identifier;
import org.thinkingstudio.rusurequit.client.RuSureQuit;
import org.thinkingstudio.rusurequit.client.config.ConfigHelper;

public class BedrockStyle implements BaseStyle {
    private static final Identifier WINDOW_TEXTURE = Identifier.of(RuSureQuit.MOD_ID, "textures/gui/styles/bedrock/legacyui/window.png");

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
    public void drawConfirmTextError(DrawContext context, TextRenderer textRenderer, Screen screen) {
        context.drawCenteredTextWithShadow(textRenderer, Text.translatable("screen.rusurequit.confirm.textfield.error"),
                screen.width / 2,
                (screen.height - windowHeight) / 2 + windowHeight - messageBMargin + 45,
                Colors.RED);
    }

    @Override
    public void render(MinecraftClient client, TextRenderer textRenderer, Screen screen, Text title, Text message,
                       DrawContext context) {
        drawWindow(context, textRenderer, title, (screen.width - windowWidth) / 2, (screen.height - windowHeight) / 2);
        drawMessage(context, textRenderer, screen, message);
    }

    private void drawWindow(DrawContext context, TextRenderer textRenderer, Text title, int x, int y) {
        int endX = x + windowWidth;
        int endY = y + windowHeight;

        context.fillGradient(x + 4, y + 4, endX - 4, endY - 4, -1072689136, -804253680);
        context.drawTexture(RenderPipelines.GUI_TEXTURED, WINDOW_TEXTURE, x, y, 0, 0, 252, 140, 252, 140);
        context.drawText(textRenderer, title, x + 8, y + 6, Colors.BLACK, false);
    }

    private void drawMessage(DrawContext context, TextRenderer textRenderer, Screen screen, Text message) {
        context.drawCenteredTextWithShadow(textRenderer, message,
                screen.width / 2,
                (screen.height - windowHeight) / 2 + windowHeight - messageBMargin,
                Colors.WHITE);
        if (ConfigHelper.getConfig().enableTextFieldConfirm) {
            context.drawCenteredTextWithShadow(textRenderer, textFieldMessage,
                    screen.width / 2,
                    (screen.height - windowHeight) / 2 + windowHeight - messageBMargin - 15,
                    Colors.WHITE);
        }
    }
}