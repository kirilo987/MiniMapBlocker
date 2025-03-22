# MiniMapBlocker 🚫🗺️

**MiniMapBlocker** is a Minecraft plugin designed to block the use of mini-maps on your server. It detects and prevents players from using specific mods that provide mini-map functionality, ensuring a fair gameplay experience for everyone.

## Features ✨

- Detects and blocks players using mini-map mods
- Configurable list of banned mods
- Customizable kick messages
- Commands to reload the plugin configuration and check player mods
- Supports Paper and ProtocolLib

## Commands 📜

- `/mmreload` - Reloads the plugin configuration
- `/mmcheck <player>` - Checks the mods used by a specific player

## Permissions 🔑

- `minimapblock.reload` - Permission to reload the plugin configuration
- `minimapblock.check` - Permission to check the mods used by a player

## Installation 📦

1. Download the latest release of MiniMapBlocker.
2. Place the `MiniMapBlocker.jar` file in your server's `plugins` directory.
3. Start or restart your server to load the plugin.
4. Configure the plugin by editing the `config.yml` file in the `plugins/MiniMapBlocker` directory.

## Configuration ⚙️

The `config.yml` file allows you to specify the list of banned mods and customize the kick message. Example configuration:

```yaml
banned-mods:
  - Xaero's Minimap
  - JourneyMap
kick-message: "Mini-maps are not allowed on this server!"
```

## Contributing 🤝

Contributions are welcome! Please fork the repository and submit a pull request with your changes.

## License 📄

This project is licensed under the MIT License. See the [LICENSE](MIT%20License.txt) file for details.

## Authors 👥

- **Kxysl1k** - [kxysl1k.netlify.app](https://kxysl1k.netlify.app/)

Enjoy a fair and mini-map-free Minecraft experience with MiniMapBlocker! 🎮
```
