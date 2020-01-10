package io.github.lix3nn53.guardiansofadelia.lobby.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;

public class MyFoodLevelChangeEvent implements Listener {

    @EventHandler
    public void onFoodLevelChangeEvent(FoodLevelChangeEvent e) {
        e.setCancelled(true);
    }
}
