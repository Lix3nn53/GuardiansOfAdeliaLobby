package io.github.lix3nn53.guardiansofadelia.lobby.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPortalEvent;

public class MyPlayerPortalEvent implements Listener {

    @EventHandler
    public void onPlayerPortal(PlayerPortalEvent e)
    {
        e.setCancelled(true);
    }
}
