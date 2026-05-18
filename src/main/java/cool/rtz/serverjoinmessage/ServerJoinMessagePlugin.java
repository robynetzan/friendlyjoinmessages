package cool.rtz.serverjoinmessage;

import com.google.inject.Inject;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.player.ServerPostConnectEvent;
import com.velocitypowered.api.plugin.Plugin;
import com.velocitypowered.api.proxy.Player;
import org.slf4j.Logger;
import net.kyori.adventure.text.Component;

public class ServerJoinMessagePlugin {

    private final Logger logger;

    @Inject
    public ServerJoinMessagePlugin(Logger logger) {
        this.logger = logger;
    }

    @Subscribe
    public void onServerConnect(ServerPostConnectEvent event) {
        Player player = event.getPlayer();

        player.getCurrentServer().ifPresent(currentServer -> {
            String serverName = currentServer
                    .getServerInfo()
                    .getName();

            player.sendMessage(Component.text(
                    "You joined the "
                    + serverName
                    + " server. Type /server to choose another server."
            ));
        });
    }
}