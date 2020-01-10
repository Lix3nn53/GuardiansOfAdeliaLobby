package io.github.lix3nn53.guardiansofadelia.lobby.events;

import fr.xephi.authme.events.RegisterEvent;
import io.github.lix3nn53.guardiansofadelia.lobby.GuardiansOfAdeliaLobby;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class MyRegisterEvent implements Listener {

    @EventHandler
    public void onRegister(RegisterEvent event) {
        Player p = event.getPlayer();
        p.sendTitle(ChatColor.GREEN + "Welcome!", ChatColor.YELLOW + "[ " + ChatColor.GOLD +"Guardians " +
                ChatColor.YELLOW +"of " + ChatColor.GREEN + "Adelia " + ChatColor.YELLOW + "]", 20, 80, 20);
        p.setFoodLevel(20);

        p.setResourcePack(GuardiansOfAdeliaLobby.ResourcePackAddress);
    }
}
