package cool.rtz.serverjoinmessage.command;

import com.velocitypowered.api.command.SimpleCommand;
import cool.rtz.serverjoinmessage.config.PluginConfig;
import net.kyori.adventure.text.Component;
import org.slf4j.Logger;

public class ReloadCommand implements SimpleCommand {

    private final PluginConfig config;
    private final Logger logger;

    public ReloadCommand(PluginConfig config, Logger logger) {
        this.config = config;
        this.logger = logger;
    }

    @Override
    public void execute(Invocation invocation) {

        try {
            config.load();

            invocation.source().sendMessage(
                    Component.text("ServerJoinMessage config reloaded.")
            );

            logger.info("Config reloaded via command.");

        } catch (Exception e) {

            invocation.source().sendMessage(
                    Component.text("Failed to reload config.")
            );

            logger.error("Config reload failed", e);
        }
    }

    @Override
    public boolean hasPermission(Invocation invocation) {
        return invocation.source()
                .hasPermission("serverjoinmessage.reload");
    }
}