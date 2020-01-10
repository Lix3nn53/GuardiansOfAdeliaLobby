package io.github.lix3nn53.guardiansofadelia.lobby.events;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPortalEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class MyPlayerQuitEvent implements Listener {

    @EventHandler
    public void OnLeave(PlayerQuitEvent e)
    {
        Player p = e.getPlayer();
        e.setQuitMessage(ChatColor.RED + "Goodbye " + ChatColor.WHITE + p.getName());
        p.teleport(p.getLocation().getWorld().getSpawnLocation());
    }
}
