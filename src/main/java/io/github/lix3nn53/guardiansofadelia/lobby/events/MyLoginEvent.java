package io.github.lix3nn53.guardiansofadelia.lobby.events;

import fr.xephi.authme.events.LoginEvent;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class MyLoginEvent implements Listener {

    @EventHandler
    public void onAuth(LoginEvent event) {
        Player p = event.getPlayer();
        p.sendTitle(ChatColor.GREEN + "Welcome!", ChatColor.YELLOW + "[ " + ChatColor.GOLD +"Guardians " +
                ChatColor.YELLOW +"of " + ChatColor.GREEN + "Adelia " + ChatColor.YELLOW + "]", 20, 80, 20);
        p.setFoodLevel(20);
    }
}
