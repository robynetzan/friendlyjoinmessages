package cool.rtz.serverjoinmessage;

import com.google.inject.Inject;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.plugin.Plugin;
import com.velocitypowered.api.proxy.ProxyServer;
import cool.rtz.serverjoinmessage.config.PluginConfig;
import cool.rtz.serverjoinmessage.listener.ServerListener;
import org.slf4j.Logger;
import com.velocitypowered.api.command.CommandManager;
import com.velocitypowered.api.command.CommandMeta;
import cool.rtz.serverjoinmessage.command.ReloadCommand;

public class ServerJoinMessagePlugin {

    private final ProxyServer server;
    private final Logger logger;

    private final PluginConfig config;

    // ✅ @Inject goes HERE (constructor injection)
    @Inject
    public ServerJoinMessagePlugin(ProxyServer server, Logger logger, PluginConfig config) {
        this.server = server;
        this.logger = logger;
        this.config = config;
    }

    // ✅ @Subscribe goes on METHODS (not class, not constructor)
    @Subscribe
    public void onInit(ProxyInitializeEvent event) {
        logger.info("ServerJoinMessage starting...");

        config.load();

        server.getEventManager().register(this, new ServerListener(server, config));

        CommandManager manager = server.getCommandManager();

        CommandMeta meta = manager.metaBuilder("sjm")
                .aliases("serverjoinmessage")
                .build();

        manager.register(
                meta,
                new ReloadCommand(config, logger)
        );

        logger.info("ServerJoinMessage enabled.");
    }


}