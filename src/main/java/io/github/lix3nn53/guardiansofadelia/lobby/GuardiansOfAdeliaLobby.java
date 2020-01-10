package io.github.lix3nn53.guardiansofadelia.lobby;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import io.github.lix3nn53.guardiansofadelia.lobby.events.*;
import org.bukkit.Bukkit;
import org.bukkit.Difficulty;
import org.bukkit.GameRule;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.messaging.PluginMessageListener;

import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class GuardiansOfAdeliaLobby extends JavaPlugin implements PluginMessageListener {

    public static HashMap<String, Integer> serverToPlayerCount = new HashMap<>();
    public static HashMap<String, Integer> serverToPort = new HashMap<>();

    static {
        serverToPlayerCount.put("rpg1", 0);
        serverToPort.put("rpg1", 25567);
    }

    private static GuardiansOfAdeliaLobby instance;

    public static GuardiansOfAdeliaLobby getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;
        Bukkit.getPluginManager().registerEvents(new MyEntitySpawnEvent(), this);
        Bukkit.getPluginManager().registerEvents(new MyFoodLevelChangeEvent(), this);
        Bukkit.getPluginManager().registerEvents(new MyInventoryClickEvent(), this);
        Bukkit.getPluginManager().registerEvents(new MyLoginEvent(), this);
        Bukkit.getPluginManager().registerEvents(new MyPlayerDeathEvent(), this);
        Bukkit.getPluginManager().registerEvents(new MyPlayerDropItemEvent(), this);
        Bukkit.getPluginManager().registerEvents(new MyPlayerInteractEvent(), this);
        Bukkit.getPluginManager().registerEvents(new MyPlayerJoinEvent(), this);
        Bukkit.getPluginManager().registerEvents(new MyPlayerPortalEvent(), this);
        Bukkit.getPluginManager().registerEvents(new MyPlayerQuitEvent(), this);
        Bukkit.getPluginManager().registerEvents(new MyPlayerResourcePackStatusEvent(), this);

        for (World w : Bukkit.getServer().getWorlds()) {
            w.setDifficulty(Difficulty.HARD);
            w.setGameRule(GameRule.ANNOUNCE_ADVANCEMENTS, false);
            w.setGameRule(GameRule.DISABLE_ELYTRA_MOVEMENT_CHECK, true);
            w.setGameRule(GameRule.DO_ENTITY_DROPS, false);
            w.setGameRule(GameRule.DO_FIRE_TICK, false);
            w.setGameRule(GameRule.DO_MOB_LOOT, false);
            w.setGameRule(GameRule.DO_MOB_SPAWNING, false);
            w.setGameRule(GameRule.DO_TILE_DROPS, false);
            w.setGameRule(GameRule.DO_WEATHER_CYCLE, false);
            w.setGameRule(GameRule.KEEP_INVENTORY, true);
            w.setGameRule(GameRule.MOB_GRIEFING, false);
            w.setGameRule(GameRule.NATURAL_REGENERATION, false);
            w.setGameRule(GameRule.SHOW_DEATH_MESSAGES, false);
            w.setGameRule(GameRule.SPECTATORS_GENERATE_CHUNKS, true);
            w.setGameRule(GameRule.DISABLE_RAIDS, true);
            w.setTime(3000);
            w.setPVP(false);
            w.setGameRule(GameRule.DO_DAYLIGHT_CYCLE, true);
            getLogger().info("World(" + w.getName() + ") options set");
            getLogger().info("World(" + w.getName() + ") view distance: " + w.getViewDistance());
        }

        //register bungee channels
        this.getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
        this.getServer().getMessenger().registerIncomingPluginChannel(this, "BungeeCord", this);
    }

    public static Set<Player> joinCooldown = new HashSet<Player>();
    public static Set<Player> rocketCooldown = new HashSet<Player>();

    @Override
    public void onPluginMessageReceived(String channel, Player player, byte[] message) {
        if (!channel.equals("BungeeCord")) {
            return;
        }

        Bukkit.getLogger().info("BUNGEE MESSAGE");

        ByteArrayDataInput in = ByteStreams.newDataInput(message);
        String subchannel = in.readUTF();
        if (subchannel.equals("PlayerCount")) {
            String server = in.readUTF();
            if(server.equalsIgnoreCase("ALL")) return;

            int playerCount = in.readInt();

            serverToPlayerCount.put(server, playerCount);
        }
    }

    public static void updatePlayerCount(Player player, String server) {
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("PlayerCount");
        out.writeUTF(server);

        player.sendPluginMessage(instance, "BungeeCord", out.toByteArray());
    }

    public static boolean isServerOnline(int port) {
        try {
            Socket s = new Socket();
            s.connect(new InetSocketAddress("127.0.0.1", port), 15); //good timeout is 10-20
            s.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
}
