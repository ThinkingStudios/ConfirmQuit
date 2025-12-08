package org.thinkingstudio.rusurequit.client.screen.style;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public abstract class BaseStyle {
    public static final Identifier MENU_BACKGROUND_TEXTURE = Identifier.ofVanilla("textures/gui/menu_background.png");
    public static final Identifier INWORLD_MENU_BACKGROUND_TEXTURE = Identifier.ofVanilla("textures/gui/inworld_menu_background.png");

    public abstract ButtonWidget generateConfirmButtons(Screen screen, ButtonWidget.PressAction onConfirm);

    public abstract ButtonWidget generateCancelButtons(Screen screen, ButtonWidget.PressAction onCancel);

    public abstract TextFieldWidget generateConfirmTextField(TextRenderer textRenderer, Screen screen);

    public abstract void drawConfirmTextError(DrawContext drawContext, TextRenderer textRenderer, Screen screen);

    public abstract void render(MinecraftClient client, TextRenderer textRenderer, Screen screen, Text title, Text message,
                                DrawContext drawContext);
}