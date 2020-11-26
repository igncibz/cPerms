package cibz.cPerms.Listeners;

import cibz.cPerms.Main.Ranks;
import cibz.cPerms.Main.Tags;
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

public class TagGUIListener implements Listener {

    public static void tagGUI(Player p) {
        Inventory inv = Bukkit.createInventory(null, 27, "§dAvailable Tags");

        for (String prefixes : cPerms.plugin.getConfig().getConfigurationSection("Tag").getKeys(false)) {

            ItemStack tag = new ItemStack(Material.NAME_TAG);
            ItemMeta tags = tag.getItemMeta();
            tags.setDisplayName(prefixes);
            ArrayList<String> gLore = new ArrayList<String>();
            if(Tags.getTagPrefix(prefixes) != null){
                String prefix = Tags.getTagPrefix(prefixes);
                if(Ranks.hasRank(p)){
                    String rank = Ranks.getRank(p);
                    String color = Ranks.getColor(rank);
                    gLore.add("§f - " + ChatColor.translateAlternateColorCodes('&', prefix) + " " + ChatColor.translateAlternateColorCodes('&', color) + p.getName());
                } else {
                    gLore.add("§f - " + ChatColor.translateAlternateColorCodes('&', prefix) + " §f" + p.getName());
                }
            }
            tags.setLore(gLore);
            tag.setItemMeta(tags);
            inv.addItem(tag);

            ItemStack remove = new ItemStack(Material.REDSTONE_TORCH_ON);
            ItemMeta removeM = remove.getItemMeta();
            removeM.setDisplayName("§cClear Tag");
            remove.setItemMeta(removeM);
            inv.setItem(22, remove);
        }
        p.openInventory(inv);

    }

    @EventHandler
    public void grantGUI(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();
        if (e.getCurrentItem() != null) {
            if (e.getInventory().getTitle().contains("§dAvailable Tags")) {
                if (e.getCurrentItem().getItemMeta() != null) {
                    if (e.getCurrentItem().getItemMeta().getDisplayName() != null) {
                        if(e.getCurrentItem().getType() == Material.NAME_TAG){
                            String tag = e.getCurrentItem().getItemMeta().getDisplayName();
                            if(p.hasPermission("cPerms." + tag) || Ranks.getRank(p).equalsIgnoreCase(tag)){
                                Tags.setPlayerPrefix(p, tag);
                                p.sendMessage("§dSuccessfully set your tag to " + ChatColor.translateAlternateColorCodes('&', tag));
                                e.setCancelled(true);
                                p.closeInventory();
                            } else {
                                p.sendMessage("§cNo permission");
                                e.setCancelled(true);
                                p.closeInventory();
                            }
                        }
                        if(e.getCurrentItem().getType() == Material.REDSTONE_TORCH_ON){
                            p.sendMessage("§cTag removed.");
                            Tags.removePlayerPrefix(p);
                            p.closeInventory();
                            e.setCancelled(true);
                        }
                    }
                }
            }
        }
    }
}
