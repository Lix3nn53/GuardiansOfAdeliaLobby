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

        ItemStack firework = new ItemStack(Material.FIREWORK_ROCKET);
        ItemMeta im = firework.getItemMeta();
        im.setDisplayName(ChatColor.GOLD + "UP!!");
        firework.setItemMeta(im);

        ItemStack elytra = new ItemStack(Material.LIGHT_BLUE_WOOL, 1);
        ItemMeta im2 = elytra.getItemMeta();
        im2.setDisplayName(ChatColor.AQUA + "Elytra equip/unequip!");
        elytra.setItemMeta(im2);

        ItemStack compass = new ItemStack(Material.COMPASS);
        ItemMeta im3 = compass.getItemMeta();
        im3.setDisplayName(ChatColor.GREEN + "Server Compass");
        compass.setItemMeta(im3);

        ItemStack clock = new ItemStack(Material.NETHER_STAR);
        ItemMeta im4 = clock.getItemMeta();
        im4.setDisplayName(ChatColor.YELLOW + "Lobby Star");
        clock.setItemMeta(im4);

        p.getInventory().setItem(0, compass);
        p.getInventory().setItem(1, clock);
        p.getInventory().setItem(2, firework);
        p.getInventory().setItem(3, elytra);

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
                cancel();
                p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 1));
                p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, Integer.MAX_VALUE, 1));
            }
        }.runTaskTimer(GuardiansOfAdeliaLobby.getInstance(), 30L, 30L);
    }
}
