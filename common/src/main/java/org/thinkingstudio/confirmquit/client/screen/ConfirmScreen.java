package org.thinkingstudio.confirmquit.client.screen;

import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.input.KeyInput;
import net.minecraft.client.util.InputUtil;
import org.thinkingstudio.confirmquit.client.config.ConfigHelper;
import org.thinkingstudio.confirmquit.client.screen.style.BaseStyle;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;

public class ConfirmScreen extends Screen {
    private final Text message;
    private final Runnable onCancel;
    private final Runnable onConfirm;
    private final long openTime;
    private final BaseStyle style = ConfigHelper.getConfig().confirmScreenStyle.baseStyleSupplier.get();
    private ButtonWidget cancel;
    private ButtonWidget confirm;
    private TextFieldWidget confirmTextField;

    private boolean confirmed = false;
    public static boolean confirmTextError = false;

    public boolean isConfirmed() {
        return confirmed;
    }

    public ConfirmScreen(Screen parentScreen, Text message, Runnable confirm) {
        super(Text.translatable("screen.confirmquit.confirm.title"));
        this.openTime = System.currentTimeMillis();
        this.message = message;
        this.onCancel = () -> this.client.setScreen(parentScreen);
        this.onConfirm = () -> {
            confirmed = true;
            confirm.run();
        };
    }

    @Override
    protected void init() {
        initButton();
    }

    @Override
    public void tick() {
        if (openTime + ConfigHelper.getConfig().buttonWaitTime < System.currentTimeMillis()) {
            cancel.active = true;
            confirm.active = true;
        }
    }

    private void initButton() {
        confirmTextField = style.generateConfirmTextField(textRenderer, this);

        confirm = style.generateConfirmButtons(this, button -> {
            if (ConfigHelper.getConfig().enableTextFieldConfirm) {
                if (confirmTextField.getText() != null && confirmTextField.getText().equals(ConfigHelper.getConfig().textFieldConfirmText)) {
                    confirmTextError = false;
                    confirmed = true;
                    onConfirm.run();
                } else {
                    confirmTextError = true;
                    confirmed = false;
                }
            } else {
                confirmed = true;
                onConfirm.run();
            }
        });

        cancel = style.generateCancelButtons(this, button -> onCancel.run());

        confirm.active = false;
        cancel.active = false;
        this.addSelectableChild(confirmTextField);
        this.addDrawableChild(confirm);
        this.addDrawableChild(cancel);
    }

    @Override
    public void render(DrawContext drawContext, int mouseX, int mouseY, float delta) {
        style.render(this.client, this.textRenderer, this, title, message, drawContext);
        super.render(drawContext, mouseX, mouseY, delta);

        if (ConfigHelper.getConfig().enableTextFieldConfirm) {
            confirmTextField.render(drawContext, mouseX, mouseY, delta);
            if (confirmTextError) {
                style.drawConfirmTextError(drawContext, textRenderer, this);
            }
        }
    }

    @Override
    public boolean keyPressed(KeyInput keyInput) {
        if (ConfigHelper.getConfig().enableScreenShortcutKey && keyInput.getKeycode() == InputUtil.GLFW_KEY_ENTER) {
            onConfirm.run();
        }
        return super.keyPressed(keyInput);
    }

    @Override
    public boolean shouldCloseOnEsc() {
        return ConfigHelper.getConfig().enableScreenShortcutKey;
    }

    @Override
    public void close() {
        onCancel.run();
    }
}
