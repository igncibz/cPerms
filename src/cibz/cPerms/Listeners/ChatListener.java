package cibz.cPerms.Listeners;

import cibz.cPerms.Main.Ranks;
import cibz.cPerms.Main.Tags;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatListener implements Listener {

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e){
        Player p = e.getPlayer();
        if(Tags.hasPrefix(p)){
            String tag = Tags.getPlayerPrefix(p);
            String prefix = Tags.getTagPrefix(tag);

            String rank = Ranks.getRank(p);
            String color = Ranks.getColor(rank);

            if(color != null) {
                if(tag.equalsIgnoreCase("default")){
                    if (color.contains("&")) {
                        //DEFAULT + COLOR
                        e.setFormat(ChatColor.translateAlternateColorCodes('&', color)
                                + p.getName() + "§7: §f" + e.getMessage());
                    }
                }
                if(prefix != null){
                    if (prefix.contains("&")) {
                        if (color.contains("&")) {
                            //NEITHER ARE NULL
                            e.setFormat(ChatColor.translateAlternateColorCodes('&', prefix) + " " + ChatColor.translateAlternateColorCodes('&', color)
                                    + p.getName() + "§7: §f" + e.getMessage());
                        }
                    }
                } else {
                    if(color.contains("&")){
                        //PREFIX IS NULL
                        e.setFormat(ChatColor.translateAlternateColorCodes('&', color) + " §f" + p.getName() + "§7: §f" + e.getMessage());
                    }
                }
            } else {
                if(prefix != null){
                    if(tag.equalsIgnoreCase("default")){
                        //DEFAULT ONLY
                        e.setFormat(p.getName() + "§7: §f" + e.getMessage());
                    }
                    if (prefix.contains("&")) {
                        //COLOR IS NULL BUT PREFIX ISNT
                        e.setFormat(ChatColor.translateAlternateColorCodes('&', prefix) + " §f" + p.getName() + "§7: §f" + e.getMessage());
                    }
                } else {
                    //BOTH NULL
                    e.setFormat(p.getName() + "§7: §f" + e.getMessage());
                }
            }
        }
    }
}
