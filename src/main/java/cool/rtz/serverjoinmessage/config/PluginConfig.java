package cool.rtz.serverjoinmessage.config;

import org.spongepowered.configurate.CommentedConfigurationNode;
import org.spongepowered.configurate.yaml.YamlConfigurationLoader;
import org.slf4j.Logger;

import java.io.File;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;

public class PluginConfig {

    private final Logger logger;

    private String message;
    private final Map<String, String> serverAliases = new HashMap<>();

    public PluginConfig(Logger logger) {
        this.logger = logger;
    }

    public void load() {
        try {
            File file = new File("plugins/serverjoinmessage/config.yml");

            if (!file.exists()) {
                file.getParentFile().mkdirs();
                Files.copy(
                        getClass().getClassLoader().getResourceAsStream("config.yml"),
                        file.toPath()
                );
            }

            YamlConfigurationLoader loader = YamlConfigurationLoader.builder()
                    .path(file.toPath())
                    .build();

            CommentedConfigurationNode root = loader.load();

            this.message = root.node("message").getString(
                    "<green>You joined %server%! Type /server to switch."
            );

            serverAliases.clear();

            CommentedConfigurationNode aliases = root.node("server-aliases");

            for (var entry : aliases.childrenMap().entrySet()) {
                String key = entry.getKey().toString();
                String value = entry.getValue().getString(key);

                serverAliases.put(key.toLowerCase(), value);
            }

        } catch (Exception e) {
            logger.error("Failed to load config", e);
            this.message = "<red>Config error";
        }
    }

    public String getMessage() {
        return message;
    }

    public String getServerDisplay(String serverName) {
        return serverAliases.getOrDefault(serverName.toLowerCase(), serverName);
    }
}