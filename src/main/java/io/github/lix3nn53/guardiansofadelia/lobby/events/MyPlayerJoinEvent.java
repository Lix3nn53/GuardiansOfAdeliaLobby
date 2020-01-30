package io.github.lix3nn53.guardiansofadelia.lobby.events;

import io.github.lix3nn53.guardiansofadelia.lobby.GuardiansOfAdeliaLobby;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;

public class MyPlayerJoinEvent implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e)
    {
        Player p = e.getPlayer();
        e.setJoinMessage(ChatColor.GREEN + "Welcome " + ChatColor.WHITE + p.getName() + ChatColor.GREEN + "!");

        p.getInventory().clear();
        p.teleport(p.getLocation().getWorld().getSpawnLocation());
        p.setGameMode(GameMode.ADVENTURE);
        for (PotionEffect effect : p.getActivePotionEffects()){
            p.removePotionEffect(effect.getType());
        }

        p.getInventory().setChestplate(new ItemStack(Material.ELYTRA));

        ItemStack compass = new ItemStack(Material.COMPASS);
        ItemMeta im3 = compass.getItemMeta();
        im3.setDisplayName(ChatColor.GREEN + "Connect to servers");
        List<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(ChatColor.GRAY + "Right click while holding to use");
        im3.setLore(lore);
        compass.setItemMeta(im3);

        p.getInventory().setItem(0, compass);

        GuardiansOfAdeliaLobby.joinCooldown.add(p);

        new BukkitRunnable() {

            @Override
            public void run() {
                cancel();
                GuardiansOfAdeliaLobby.joinCooldown.remove(p);
            }
        }.runTaskLater(GuardiansOfAdeliaLobby.getInstance(), 40);

        new BukkitRunnable() {

            @Override
            public void run() {
                p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 1));
                p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, Integer.MAX_VALUE, 1));
                p.sendTitle(net.md_5.bungee.api.ChatColor.GREEN + "Welcome!", net.md_5.bungee.api.ChatColor.YELLOW + "[ " + net.md_5.bungee.api.ChatColor.GOLD +"Guardians " +
                        net.md_5.bungee.api.ChatColor.YELLOW +"of " + net.md_5.bungee.api.ChatColor.GREEN + "Adelia " + net.md_5.bungee.api.ChatColor.YELLOW + "]", 20, 80, 20);
                p.setFoodLevel(20);
            }
        }.runTaskLater(GuardiansOfAdeliaLobby.getInstance(), 10L);
    }
}
