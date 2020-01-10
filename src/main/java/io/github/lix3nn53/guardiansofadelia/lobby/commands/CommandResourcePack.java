package io.github.lix3nn53.guardiansofadelia.lobby.commands;

import io.github.lix3nn53.guardiansofadelia.lobby.GuardiansOfAdeliaLobby;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.UUID;

public class CommandResourcePack implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (!command.getName().equals("rp")) {
            return false;
        }
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (args.length < 1) {
                player.sendMessage(ChatColor.YELLOW + "/rp load");
            } else if (args[0].equals("load")) {
                player.setResourcePack(GuardiansOfAdeliaLobby.ResourcePackAddress);
            }
            // If the player (or console) uses our command correct, we can return true
            return true;
        }
        return false;
    }
}
