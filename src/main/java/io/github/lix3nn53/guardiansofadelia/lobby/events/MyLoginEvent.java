package io.github.lix3nn53.guardiansofadelia.lobby.events;

import fr.xephi.authme.events.LoginEvent;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class MyLoginEvent implements Listener {

    final String ResourcePackAddress = "https://drive.google.com/uc?export=download&id=1ubOvoMmHT-6biMURNiKav9K9jXSX3cgx";

    @EventHandler
    public void onAuth(LoginEvent event) {
        Player p = event.getPlayer();
        p.sendTitle(ChatColor.GREEN + "Welcome!", ChatColor.YELLOW + "[ " + ChatColor.GOLD +"Guardians " +
                ChatColor.YELLOW +"of " + ChatColor.GREEN + "Adelia " + ChatColor.YELLOW + "]", 20, 80, 20);
        p.setFoodLevel(20);
        p.setResourcePack(ResourcePackAddress);
    }
}
