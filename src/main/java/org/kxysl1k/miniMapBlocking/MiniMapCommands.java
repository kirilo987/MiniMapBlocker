package org.kxysl1k.miniMapBlocking;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.kxysl1k.miniMapBlocking.MiniMapBlocking;

import java.util.Set;

public class MiniMapCommands implements CommandExecutor {

    private final MiniMapBlocking plugin;

    public MiniMapCommands(MiniMapBlocking plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("mmreload")) {
            return handleReloadCommand(sender);
        }

        if (command.getName().equalsIgnoreCase("mmcheck")) {
            return handleCheckCommand(sender, args);
        }

        return false;
    }

    private boolean handleReloadCommand(CommandSender sender) {
        if (!sender.hasPermission("minimapblock.reload")) {
            sender.sendMessage("§cУ вас нет прав!");
            return true;
        }

        plugin.reloadPluginConfig();
        sender.sendMessage("§aКонфиг плагина перезагружен!");
        return true;
    }

    private boolean handleCheckCommand(CommandSender sender, String[] args) {
        if (!sender.hasPermission("minimapblock.check")) {
            sender.sendMessage("§cУ вас нет прав!");
            return true;
        }

        if (args.length < 1) {
            sender.sendMessage("§cИспользование: /mmcheck <игрок>");
            return true;
        }

        Player target = Bukkit.getPlayer(args[0]);
        if (target == null) {
            sender.sendMessage("§cИгрок не найден.");
            return true;
        }

        Set<String> playerMods = target.getListeningPluginChannels();
        sender.sendMessage("§aИгрок: " + target.getName());
        if (!playerMods.isEmpty()) {
            sender.sendMessage("§eМоды: " + String.join(", ", playerMods));
        } else {
            sender.sendMessage("§eИгрок без модов.");
        }
        return true;
    }
}