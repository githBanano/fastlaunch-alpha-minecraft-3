package dev.fastlaunch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientLifecycleEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.GameOptions;

public class FastLaunchClient implements ClientModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger("fastlaunch");

    @Override
    public void onInitializeClient() {
        FastLaunchConfig.load();
        ClientLifecycleEvents.CLIENT_STARTED.register(FastLaunchClient::applyOptions);
        LOGGER.info("FastLaunch carregado");
    }

    private static void applyOptions(MinecraftClient client) {
        FastLaunchConfig cfg = FastLaunchConfig.get();
        if (!cfg.tuneVideoOptions) {
            return;
        }
        GameOptions o = client.options;
        o.getBiomeBlendRadius().setValue(cfg.biomeBlendRadius);
        o.getEntityShadows().setValue(cfg.entityShadows);
        LOGGER.info("Opcoes de video leves aplicadas");
    }
}
