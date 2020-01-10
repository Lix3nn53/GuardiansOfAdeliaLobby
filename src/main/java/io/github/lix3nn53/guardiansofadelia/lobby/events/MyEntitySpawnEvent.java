package io.github.lix3nn53.guardiansofadelia.lobby.events;

import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;

public class MyEntitySpawnEvent implements Listener {

    @EventHandler
    public void onEntitySpawn(EntitySpawnEvent e) {
        EntityType entityType = e.getEntityType();
        if(!(entityType.equals(EntityType.PLAYER) || entityType.equals(EntityType.ARMOR_STAND))){
            e.setCancelled(true);
        }
    }
}
