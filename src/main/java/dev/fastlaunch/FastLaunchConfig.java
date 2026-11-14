package dev.fastlaunch;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

import net.fabricmc.loader.api.FabricLoader;

/** Configuracao simples em config/fastlaunch.properties. */
public final class FastLaunchConfig {
    private static FastLaunchConfig instance = new FastLaunchConfig();

    /** Remove a animacao de fade-in da tela de titulo. */
    public boolean skipTitleFade = true;

    /** Se true, aplica os ajustes leves de video abaixo ao iniciar (opt-in). */
    public boolean tuneVideoOptions = false;
    public int biomeBlendRadius = 1;
    public boolean entityShadows = false;

    private FastLaunchConfig() {}

    public static FastLaunchConfig get() {
        return instance;
    }

    public static void load() {
        Path file = FabricLoader.getInstance().getConfigDir().resolve("fastlaunch.properties");
        FastLaunchConfig cfg = new FastLaunchConfig();
        Properties p = new Properties();

        if (Files.exists(file)) {
            try (InputStream in = Files.newInputStream(file)) {
                p.load(in);
            } catch (IOException e) {
                FastLaunchClient.LOGGER.warn("Nao foi possivel ler a config, usando padroes", e);
            }
        }

        cfg.skipTitleFade = Boolean.parseBoolean(p.getProperty("skipTitleFade", "true"));
        cfg.tuneVideoOptions = Boolean.parseBoolean(p.getProperty("tuneVideoOptions", "false"));
        cfg.entityShadows = Boolean.parseBoolean(p.getProperty("entityShadows", "false"));
        try {
            cfg.biomeBlendRadius = Math.max(0, Math.min(7, Integer.parseInt(p.getProperty("biomeBlendRadius", "1").trim())));
        } catch (NumberFormatException e) {
            cfg.biomeBlendRadius = 1;
        }

        instance = cfg;
        save(file, cfg);
    }

    private static void save(Path file, FastLaunchConfig cfg) {
        Properties p = new Properties();
        p.setProperty("skipTitleFade", Boolean.toString(cfg.skipTitleFade));
        p.setProperty("tuneVideoOptions", Boolean.toString(cfg.tuneVideoOptions));
        p.setProperty("biomeBlendRadius", Integer.toString(cfg.biomeBlendRadius));
        p.setProperty("entityShadows", Boolean.toString(cfg.entityShadows));
        try (OutputStream out = Files.newOutputStream(file)) {
            p.store(out, "FastLaunch - opcoes");
        } catch (IOException e) {
            FastLaunchClient.LOGGER.warn("Nao foi possivel salvar a config", e);
        }
    }
}
