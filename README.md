Adds customisable messages when a player joins a server. Supports MiniMessage tags.

Configurable as follows:

```
message: "<green>You joined %server%! Type /server to switch."
server-aliases:
  survival: "<green>Survival</green>"
  lobby: "<light_purple>Lobby</light_purple>"
```

The `message` field represents the base message, and you can use `%server%` as a placeholder to display the server the player has joined. In `server-aliases`, you can create replacement text for the server using the server name specified in your Velocity config.
