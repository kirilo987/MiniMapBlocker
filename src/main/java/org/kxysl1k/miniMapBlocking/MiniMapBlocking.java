package org.kxysl1k.miniMapBlocking;

import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketEvent;
import com.comphenix.protocol.events.PacketListener;
import com.comphenix.protocol.PacketType;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;
import java.util.Set;

public final class MiniMapBlocking extends JavaPlugin implements Listener {

    private List<String> запрещенныеМоды;
    private String сообщениеОтключения;
    private ProtocolManager protocolManager;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        loadConfig();

        // Регистрация событий
        getServer().getPluginManager().registerEvents(this, this);

        protocolManager = ProtocolLibrary.getProtocolManager();
        registerPacketListener();

        // Регистрация команд
        MiniMapCommands commands = new MiniMapCommands(this);
        getCommand("mmreload").setExecutor(commands);
        getCommand("mmcheck").setExecutor(commands);

        getLogger().info("MiniMapBlocking включен!");
    }

    @Override
    public void onDisable() {
        getLogger().info("MiniMapBlocking отключен!");
    }

    public void reloadPluginConfig() {
        reloadConfig();
        loadConfig();
    }

    private void loadConfig() {
        FileConfiguration config = getConfig();
        запрещенныеМоды = config.getStringList("banned-mods");
        сообщениеОтключения = config.getString("kick-message", "Мини-карты не разрешены на этом сервере!");
    }

    private void registerPacketListener() {
        PacketListener packetListener = new PacketAdapter(this, PacketType.Handshake.Client.SET_PROTOCOL) {
            @Override
            public void onPacketReceiving(PacketEvent event) {
                Player player = event.getPlayer();
                String brand = event.getPacket().getStrings().read(0);

                if (brand.contains("Forge")) {
                    getLogger().info(player.getName() + " использует Forge.");
                } else if (brand.contains("Fabric")) {
                    getLogger().info(player.getName() + " использует Fabric.");
                } else {
                    getLogger().info(player.getName() + " использует Vanilla.");
                }
            }
        };
        protocolManager.addPacketListener(packetListener);
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        Set<String> модыИгрока = player.getListeningPluginChannels();

        getLogger().info("Игрок: " + player.getName());
        if (!модыИгрока.isEmpty()) {
            getLogger().info("Моды игрока: " + String.join(", ", модыИгрока));
        } else {
            getLogger().info("Игрок без модов.");
        }

        // Проверка на запрещенные моды
        for (String mod : запрещенныеМоды) {
            if (модыИгрока.contains(mod)) {
                player.kickPlayer(сообщениеОтключения);
                getLogger().warning(player.getName() + " был отключен за использование мини-карты (" + mod + ")");
                return;
            }
        }
    }
}