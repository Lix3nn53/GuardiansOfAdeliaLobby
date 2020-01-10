package io.github.lix3nn53.guardiansofadelia.lobby.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

public class MyPlayerDropItemEvent implements Listener {

    @EventHandler
    public void onPlayerDropItemEvent(PlayerDropItemEvent e) {
        e.setCancelled(true);
    }
}
