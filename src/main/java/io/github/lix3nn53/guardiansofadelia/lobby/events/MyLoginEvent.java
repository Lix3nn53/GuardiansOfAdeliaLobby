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

        p.sendMessage(ChatColor.YELLOW + "If server resource pack is not loaded: ");
        TextComponent messageMain = new TextComponent("Click to load resource pack!");
        messageMain.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/rp load"));
        messageMain.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("Load server RP").color(ChatColor.GREEN).create()));
        messageMain.setBold(true);
        messageMain.setColor(ChatColor.GREEN);

        TextComponent messageReject = new TextComponent("Help!");
        messageReject.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://guardiansofadelia.herokuapp.com/"));
        messageReject.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("Go to website").color(ChatColor.YELLOW).create()));
        messageReject.setBold(true);
        messageReject.setColor(ChatColor.YELLOW);

        messageMain.addExtra("    ");
        messageMain.addExtra(messageReject);
        p.spigot().sendMessage(messageMain);
    }
}
