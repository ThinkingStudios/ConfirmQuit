package org.thinkingstudio.confirmquit.client.screen.style;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.screen.ScreenTexts;
import net.minecraft.text.Text;
import net.minecraft.text.TextColor;
import net.minecraft.util.Formatting;
import org.thinkingstudio.confirmquit.client.config.ConfigHelper;

public class ClassicStyle extends BaseStyle {
    // 按钮宽度
    private static final int buttonWidth = 150;
    // 按钮长度
    private static final int buttonHeight = 20;
    // 按钮间隔
    private static final int buttonFMargin = 10;
    // 按钮下边距
    private static final int buttonBMargin = 40;
    // 标题上边距
    private static final int titleTMargin = 30;

    private Text textFieldMessage;

    @Override
    public ButtonWidget generateConfirmButtons(Screen screen, ButtonWidget.PressAction onConfirm) {
        return ButtonWidget.builder(ScreenTexts.YES, onConfirm)
                .dimensions(screen.width / 2 - buttonWidth - buttonFMargin,
                        screen.height - buttonHeight - buttonBMargin,
                        buttonWidth, buttonHeight).build();
    }

    @Override
    public ButtonWidget generateCancelButtons(Screen screen, ButtonWidget.PressAction onCancel) {
        return ButtonWidget.builder(ScreenTexts.NO, onCancel)
                .dimensions(screen.width / 2 + buttonFMargin,
                        screen.height - buttonHeight - buttonBMargin,
                        buttonWidth, buttonHeight).build();
    }

    @Override
    public TextFieldWidget generateConfirmTextField(TextRenderer textRenderer, Screen screen) {
        this.textFieldMessage = Text.translatable("screen.confirmquit.confirm.textfield", ConfigHelper.getConfig().textFieldConfirmText);
        TextFieldWidget confirmTextField = new TextFieldWidget(textRenderer, screen.width / 2 - 100, screen.height / 2 - 10, 200, 20, textFieldMessage);
        confirmTextField.setVisible(ConfigHelper.getConfig().enableTextFieldConfirm);

        return confirmTextField;
    }

    @Override
    public void drawConfirmTextError(DrawContext drawContext, TextRenderer textRenderer, Screen screen) {
        drawContext.drawCenteredTextWithShadow(textRenderer, Text.translatable("screen.confirmquit.confirm.textfield.error"),
                screen.width / 2,
                screen.height / 2 - 70,
                TextColor.fromFormatting(Formatting.RED).getRgb());
    }

    @Override
    public void render(MinecraftClient client, TextRenderer textRenderer, Screen screen, Text title, Text message,
                       DrawContext drawContext, int mouseX, int mouseY, float delta) {
        renderBackground(drawContext, screen, mouseX, mouseY, delta);
        drawTextAndMessage(textRenderer, screen, title, message, drawContext);
    }

    public void renderBackground(DrawContext drawContext, Screen screen, int mouseX, int mouseY, float delta) {
        if (MinecraftClient.getInstance().world != null) {
            drawContext.fillGradient(0, 0, screen.width, screen.height, -1072689136, -804253680);
        } else {
            screen.renderBackground(drawContext, mouseX, mouseY, delta);
        }
    }

    private void drawTextAndMessage(TextRenderer textRenderer, Screen screen, Text title, Text message, DrawContext drawContext) {
        drawContext.drawCenteredTextWithShadow(textRenderer, title,
                screen.width / 2,
                titleTMargin,
                16777215);
        drawContext.drawCenteredTextWithShadow(textRenderer, message,
                screen.width / 2,
                screen.height / 2 - 30,
                10526880);
        if (ConfigHelper.getConfig().enableTextFieldConfirm) {
            drawContext.drawCenteredTextWithShadow(textRenderer, textFieldMessage,
                    screen.width / 2,
                    screen.height / 2 - 50,
                    10526880);
        }
    }
}