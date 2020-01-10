package io.github.lix3nn53.guardiansofadelia.lobby.events;

import io.github.lix3nn53.guardiansofadelia.lobby.GuardiansOfAdeliaLobby;
import io.github.lix3nn53.guardiansofadelia.lobby.PersistentDataContainerUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;

public class MyPlayerInteractEvent implements Listener {

    @EventHandler
    public void onPlayerInteractEvent(PlayerInteractEvent e) {
        if(!e.getPlayer().isOp()){
            e.setCancelled(true);
        }
        Player p = e.getPlayer();
        if(e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)){
            if(p.getInventory().getItemInMainHand() == null)return;
            if(p.getInventory().getItemInMainHand().getType().equals(Material.FIREWORK_ROCKET)){
                if(GuardiansOfAdeliaLobby.rocketCooldown.contains(p)){
                    p.sendMessage(ChatColor.RED + "Rocket is on cooldown.");
                }else{
                    Vector dir = p.getLocation().getDirection();
                    if(dir.getY() < 0.5){
                        dir.setY(1D);
                    }
                    p.setVelocity(dir.multiply(5));
                    GuardiansOfAdeliaLobby.rocketCooldown.add(p);
                    new BukkitRunnable() {

                        @Override
                        public void run() {
                            cancel();
                            if(GuardiansOfAdeliaLobby.rocketCooldown.contains(p)){
                                GuardiansOfAdeliaLobby.rocketCooldown.remove(p);
                            }
                        }
                    }.runTaskTimerAsynchronously(GuardiansOfAdeliaLobby.getInstance(), 60L, 60L);
                }
            }else if(p.getInventory().getItemInMainHand().getType().equals(Material.LIGHT_BLUE_WOOL)){
                if(p.getInventory().getChestplate() == null){
                    p.getInventory().setChestplate(new ItemStack(Material.ELYTRA));
                }else{
                    p.getInventory().setChestplate(new ItemStack(Material.AIR));
                }
            }else if(p.getInventory().getItemInMainHand().getType().equals(Material.COMPASS)){
                //openInv
                openGameServerMenu(p);

            }else if(p.getInventory().getItemInMainHand().getType().equals(Material.NETHER_STAR)){
                //openInv
            }
        }
    }

    private void openGameServerMenu(Player p) {
        new BukkitRunnable() {

            @Override
            public void run() {
                for (String server : GuardiansOfAdeliaLobby.serverToPort.keySet()) {
                    GuardiansOfAdeliaLobby.updatePlayerCount(p, server);
                }
            }
        }.runTaskAsynchronously(GuardiansOfAdeliaLobby.getInstance());

        Inventory serverSelectionGui = Bukkit.createInventory(null, 18, ChatColor.GREEN + "Game Servers");

        int serverNo = 1;
        for (String server : GuardiansOfAdeliaLobby.serverToPort.keySet()) {
            Integer port = GuardiansOfAdeliaLobby.serverToPort.get(server);
            boolean online = GuardiansOfAdeliaLobby.isServerOnline(port);

            ItemStack itemStack = new ItemStack(Material.RED_WOOL, 1);
            List<String> lore = new ArrayList<>();

            if(online){
                PersistentDataContainerUtil.putString("joinServer", server, itemStack);
                ItemMeta im = itemStack.getItemMeta();
                Integer onlineCount = GuardiansOfAdeliaLobby.serverToPlayerCount.get(server);

                itemStack.setType(Material.GREEN_WOOL);
                if (onlineCount > 1) {
                    itemStack.setAmount(onlineCount);
                }

                im.setDisplayName(ChatColor.GREEN + "Game Server " + serverNo);

                lore.add("");
                lore.add(ChatColor.DARK_GREEN + "Online: " + ChatColor.GREEN + onlineCount + "/60");
                im.setLore(lore);
                itemStack.setItemMeta(im);
            }else{
                ItemMeta im = itemStack.getItemMeta();
                im.setDisplayName(ChatColor.RED + "Game Server " + serverNo);
                lore.add("");
                lore.add(ChatColor.DARK_GRAY + "Inactive");
                im.setLore(lore);
                itemStack.setItemMeta(im);
            }
            serverSelectionGui.addItem(itemStack);
            serverNo++;
        }

        p.openInventory(serverSelectionGui);
    }
}
