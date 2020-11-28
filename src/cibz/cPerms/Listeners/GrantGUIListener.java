package cibz.cPerms.Listeners;

import cibz.cPerms.Main.Ranks;
import cibz.cPerms.Main.cPerms;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class GrantGUIListener implements Listener {

    private static Player t;

    public static void grantGUI(Player p, Player target){
        t = target;

        Inventory inv = Bukkit.createInventory(null, 18, "§dRank for " + t.getName());
        for (String names : cPerms.plugin.getConfig().getConfigurationSection("Ranks").getKeys(false)) {
            String colors = Ranks.getColor(names);
            if(colors == null){
                colors = "&f";
            }
            int color = 0;

            if(colors.contains("&1")){
                color = 11;
            }
            if(colors.contains("&2")){
                color = 13;
            }
            if(colors.contains("&3")){
                color = 9;
            }
            if(colors.contains("&4")){
                color = 14;
            }
            if(colors.contains("&5")){
                color = 10;
            }
            if(colors.contains("&6")){
                color = 1;
            }
            if(colors.contains("&7")){
                color = 8;
            }
            if(colors.contains("&8")){
                color = 7;
            }
            if(colors.contains("&9")){
                color = 11;
            }
            if(colors.contains("&a")){
                color = 5;
            }
            if(colors.contains("&b")){
                color = 3;
            }
            if(colors.contains("&c")){
                color = 1;
            }
            if(colors.contains("&d")){
                color = 2;
            }
            if(colors.contains("&e")){
                color = 4;
            }
            if(colors.contains("&f")){
                color = 0;
            }

            ItemStack rank = new ItemStack(Material.WOOL);
            rank.setDurability((short) color);
            ItemMeta ranks = rank.getItemMeta();
            ranks.setDisplayName(ChatColor.translateAlternateColorCodes('&', colors) + names);
            rank.setItemMeta(ranks);
            inv.addItem(rank);
        }
        p.openInventory(inv);
    }

    @EventHandler
    public void grantGUI(InventoryClickEvent e){
        Player p = (Player) e.getWhoClicked();
        if(e.getCurrentItem() != null) {
            if (e.getInventory().getTitle().contains("§dRank for ")) {
                if (e.getCurrentItem().getItemMeta() != null) {
                    if (e.getCurrentItem().getItemMeta().getDisplayName() != null) {
                        String rank = e.getCurrentItem().getItemMeta().getDisplayName();
                        String r = rank.substring(2);
                        Ranks.setRank(t, r);

                        p.closeInventory();
                        e.setCancelled(true);

                        p.sendMessage("§dSuccessfully set §f" + t.getName() + "'s §drank to §f" + rank + "§d.");
                    }
                }
            }
        }
    }
}
