package cool.rtz.serverjoinmessage.listener;

import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.player.ServerPostConnectEvent;
import com.velocitypowered.api.proxy.Player;
import com.velocitypowered.api.proxy.ProxyServer;
import cool.rtz.serverjoinmessage.config.PluginConfig;
import net.kyori.adventure.text.minimessage.MiniMessage;

public class ServerListener {

    private final ProxyServer server;
    private final PluginConfig config;
    private final MiniMessage miniMessage = MiniMessage.miniMessage();

    public ServerListener(ProxyServer server, PluginConfig config) {
        this.server = server;
        this.config = config;
    }

    @Subscribe
    public void onServerJoin(ServerPostConnectEvent event) {
        Player player = event.getPlayer();

        String rawServer = player.getCurrentServer()
                .map(s -> s.getServerInfo().getName())
                .orElse("unknown");

        String serverPlaceholderDisplay = config.getServerDisplay(rawServer);

        String playerName = player.getUsername();

        String message = config.getMessage()
                .replace("%server%", serverPlaceholderDisplay)
                .replace("%player%", playerName);

        player.sendMessage(miniMessage.deserialize(message));
    }
}