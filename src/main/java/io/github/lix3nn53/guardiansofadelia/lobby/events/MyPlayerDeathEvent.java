package io.github.lix3nn53.guardiansofadelia.lobby.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class MyPlayerDeathEvent implements Listener {

    @EventHandler
    public void onPlayerDeathEvent(PlayerDeathEvent e) {
        Player p = e.getEntity();
        p.spigot().respawn();
    }
}
