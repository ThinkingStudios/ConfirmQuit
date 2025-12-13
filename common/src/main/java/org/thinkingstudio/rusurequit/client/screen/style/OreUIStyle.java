package org.thinkingstudio.rusurequit.client.screen.style;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ButtonTextures;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.screen.ScreenTexts;
import net.minecraft.text.Text;
import net.minecraft.util.Colors;
import net.minecraft.util.Identifier;
import org.thinkingstudio.rusurequit.client.RuSureQuit;
import org.thinkingstudio.rusurequit.client.config.ConfigHelper;
import org.thinkingstudio.rusurequit.client.screen.widget.SimpleTexturedButtonWidget;
import org.thinkingstudio.rusurequit.client.util.DrawTexts;

public class OreUIStyle extends BaseStyle {
    private static final Identifier WINDOW_TEXTURE = Identifier.of(RuSureQuit.MOD_ID, "textures/gui/bedrock/oreui/window_background.png");
    private static final ButtonTextures BUTTON_TEXTURES = new ButtonTextures(Identifier.of(RuSureQuit.MOD_ID, "widget/oreui/button"), Identifier.of(RuSureQuit.MOD_ID, "widget/oreui/button_disabled"), Identifier.of(RuSureQuit.MOD_ID, "widget/oreui/button_highlighted"));

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
    public ButtonWidget generateConfirmButtons(TextRenderer textRenderer, Screen screen, ButtonWidget.PressAction onConfirm) {
        return SimpleTexturedButtonWidget.create(
                (screen.width - windowWidth) / 2 + windowWidth - buttonWidth - buttonLRMargin,
                (screen.height - windowHeight) / 2 + windowHeight - buttonBMargin - buttonHeight + 22,
                buttonWidth,
                buttonHeight,
                BUTTON_TEXTURES,
                onConfirm,
                ScreenTexts.YES
        );
    }

    @Override
    public ButtonWidget generateCancelButtons(Screen screen, ButtonWidget.PressAction onCancel) {
        return SimpleTexturedButtonWidget.create(
                (screen.width - windowWidth) / 2 + buttonLRMargin,
                (screen.height - windowHeight) / 2 + windowHeight - buttonBMargin - buttonHeight + 22,
                buttonWidth,
                buttonHeight,
                BUTTON_TEXTURES,
                onCancel,
                ScreenTexts.NO
        );
    }

    @Override
    public TextFieldWidget generateConfirmTextField(TextRenderer textRenderer, Screen screen) {
        this.textFieldMessage = Text.translatable("screen.rusurequit.confirm.textfield", ConfigHelper.getConfig().textFieldConfirmText);
        TextFieldWidget confirmTextField = new TextFieldWidget(textRenderer, screen.width / 2 - 100, (screen.height - windowHeight) / 2 + windowHeight - messageBMargin + 20, 200, 20, textFieldMessage);
        confirmTextField.setVisible(ConfigHelper.getConfig().enableTextFieldConfirm);

        return confirmTextField;
    }

    @Override
    public void drawConfirmTextError(DrawContext context, TextRenderer textRenderer, Screen screen) {
        DrawTexts.drawCenteredText(context, textRenderer, Text.translatable("screen.rusurequit.confirm.textfield.error"),
                screen.width / 2,
                (screen.height - windowHeight) / 2 + windowHeight - messageBMargin + 50,
                Colors.RED);
    }

    @Override
    public void render(MinecraftClient client, TextRenderer textRenderer, Screen screen, Text title, Text message,
                       DrawContext context, int mouseX, int mouseY, float delta) {
        screen.renderBackground(context, mouseX, mouseY, delta);
        drawWindow(textRenderer, title, context, (screen.width - windowWidth) / 2, (screen.height - windowHeight) / 2);
        drawMessage(textRenderer, screen, message, context);
    }

    private void drawWindow(TextRenderer textRenderer, Text title, DrawContext context, int x, int y) {
        context.drawTexture(WINDOW_TEXTURE, x - 16, y - 10, 0, 0, 280, 166, 280, 166);
        DrawTexts.drawCenteredText(context, textRenderer, title, x + windowWidth / 2, y + 2, Colors.WHITE);
    }

    private void drawMessage(TextRenderer textRenderer, Screen screen, Text message, DrawContext context) {
        DrawTexts.drawCenteredText(context, textRenderer, message,
                screen.width / 2,
                (screen.height - windowHeight) / 2 + windowHeight - messageBMargin,
                Colors.WHITE);
        if (ConfigHelper.getConfig().enableTextFieldConfirm) {
            DrawTexts.drawCenteredText(context, textRenderer, textFieldMessage,
                    screen.width / 2,
                    (screen.height - windowHeight) / 2 + windowHeight - messageBMargin - 15,
                    Colors.WHITE);
        }
    }
}