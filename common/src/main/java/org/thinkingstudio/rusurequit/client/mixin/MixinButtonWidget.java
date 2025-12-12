package org.thinkingstudio.rusurequit.client.mixin;

import org.thinkingstudio.rusurequit.client.event.ButtonPressEvent;
import org.thinkingstudio.rusurequit.client.event.base.api.EventResult;
import org.thinkingstudio.rusurequit.client.screen.ConfirmScreen;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.widget.ButtonWidget;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ButtonWidget.class)
public class MixinButtonWidget {
    @Inject(method = "onPress", at = @At("HEAD"), cancellable = true)
    private void onOnPress(CallbackInfo ci) {
        if (MinecraftClient.getInstance().currentScreen instanceof ConfirmScreen){
            if (((ConfirmScreen) MinecraftClient.getInstance().currentScreen).isConfirmed()) {
                return;
            }
        }
        EventResult result = ButtonPressEvent.BUTTON_PRESS.invoker().onPress((ButtonWidget) (Object) this);
        if(result == EventResult.CANCEL){
            ci.cancel();
        }
    }
}
