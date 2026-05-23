Adds customisable messages when a player joins a server. Supports MiniMessage tags.

Configurable as follows:

```
message: "<green>You joined %server%! Type /server to switch."
server-aliases:
  survival: "<green>Survival</green>"
  lobby: "<light_purple>Lobby</light_purple>"
```

The `message` field represents the base message template. In `server-aliases`, you can create replacement text for the server using the server name specified in your Velocity config.

**Placeholders**

- `%server%` - The name of the server the player has joined
- `%player%` - The username of the player

**Commands**

You can reload the config by typing `/sjm` in the Velocity console.

**Coming soon**

- More placeholders
- Specific messages for each server
- Rename command to /fjm (it was a typo smh)
- Permissions
- Reload config from backend server
