package io.github.lix3nn53.guardiansofadelia.lobby.events;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerResourcePackStatusEvent;

public class MyPlayerResourcePackStatusEvent implements Listener {

    @EventHandler
    public void onPlayerResourcePackStatusEvent(PlayerResourcePackStatusEvent e){
        if(e.getStatus().equals(PlayerResourcePackStatusEvent.Status.SUCCESSFULLY_LOADED)) {
            Player p = e.getPlayer();
            p.sendMessage(ChatColor.GREEN + "Resource pack loaded!");
        }
    }
}
