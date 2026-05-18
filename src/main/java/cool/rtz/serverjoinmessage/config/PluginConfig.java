package cool.rtz.serverjoinmessage.config;

import com.google.inject.Inject;
import com.velocitypowered.api.plugin.annotation.DataDirectory;
import org.slf4j.Logger;
import org.spongepowered.configurate.CommentedConfigurationNode;
import org.spongepowered.configurate.yaml.YamlConfigurationLoader;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class PluginConfig {

    private final Logger logger;
    private final Path dataDirectory;

    private String message;

    @Inject
    public PluginConfig(Logger logger, @DataDirectory Path dataDirectory) {
        this.logger = logger;
        this.dataDirectory = dataDirectory;
    }

    public void load() {
        try {
            Path file = dataDirectory.resolve("config.yml");

            if (!Files.exists(file)) {
                Files.createDirectories(dataDirectory);

                try (InputStream in = getClass().getClassLoader().getResourceAsStream("config.yml")) {
                    if (in == null) {
                        throw new IllegalStateException("config.yml missing from jar resources!");
                    }
                    Files.copy(in, file);
                }
            }

            YamlConfigurationLoader loader = YamlConfigurationLoader.builder()
                    .path(file)
                    .build();

            CommentedConfigurationNode root = loader.load();

            message = root.node("message").getString(
                    "<green>You joined %server%! Type /server to switch."
            );

            logger.info("Config loaded successfully.");

        } catch (Exception e) {
            logger.error("Failed to load config", e);
            message = "<red>Config error";
        }
    }

    public String getMessage() {
        return message;
    }
}