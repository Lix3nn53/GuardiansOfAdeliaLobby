package io.github.lix3nn53.guardiansofadelia.lobby.events;

import io.github.lix3nn53.guardiansofadelia.lobby.GuardiansOfAdeliaLobby;
import io.github.lix3nn53.guardiansofadelia.lobby.PersistentDataContainerUtil;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class MyInventoryClickEvent implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = false)
    public void onInventoryClick(InventoryClickEvent e) {
        if(!e.getWhoClicked().isOp()){
            e.setCancelled(true);
        }
        Player p = (Player) e.getWhoClicked();
        ItemStack currentItem = e.getCurrentItem();
        if(currentItem == null)return;
        if(currentItem.getType().equals(Material.AIR))return;
        if(!(currentItem.hasItemMeta()))return;
        if(!(currentItem.getItemMeta().hasDisplayName()))return;

        if (PersistentDataContainerUtil.hasString(currentItem, "joinServer")) {
            String serverName = PersistentDataContainerUtil.getString(currentItem, "joinServer");

            if (!GuardiansOfAdeliaLobby.joinCooldown.contains(p)) {
                GuardiansOfAdeliaLobby.joinCooldown.add(p);
                new BukkitRunnable() {

                    @Override
                    public void run() {
                        cancel();
                        GuardiansOfAdeliaLobby.joinCooldown.remove(p);
                    }
                }.runTaskLaterAsynchronously(GuardiansOfAdeliaLobby.getInstance(), 20L);

                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                DataOutputStream dos = new DataOutputStream(baos);
                try {
                    dos.writeUTF("Connect");
                    dos.writeUTF(serverName);
                    p.sendPluginMessage(GuardiansOfAdeliaLobby.getInstance(), "BungeeCord", baos.toByteArray());
                    baos.close();
                    dos.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            } else {
                p.sendMessage(ChatColor.RED + "You should wait a little while to join a server.");
            }
        }
    }
}
