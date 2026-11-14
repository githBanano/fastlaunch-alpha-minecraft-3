package dev.fastlaunch.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import dev.fastlaunch.FastLaunchConfig;
import net.minecraft.client.gui.screen.TitleScreen;

@Mixin(TitleScreen.class)
public abstract class TitleScreenMixin {
    /** Desliga o fade-in da tela de titulo. Se a assinatura nao existir, o mixin e ignorado (require = 0). */
    @ModifyVariable(
        method = "<init>(ZLnet/minecraft/client/gui/LogoDrawer;)V",
        at = @At("HEAD"),
        argsOnly = true,
        ordinal = 0,
        require = 0
    )
    private static boolean fastlaunch$noFade(boolean doBackgroundFade) {
        return doBackgroundFade && !FastLaunchConfig.get().skipTitleFade;
    }
}
