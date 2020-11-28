package cibz.cPerms.Main;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import java.util.List;

public class Ranks {

    public static String getRank(Player p){
        return cPerms.plugin.getConfig().getString("Players." + p.getName() + ".rank");
    }
    public static boolean isRank(String rank){
      if(cPerms.plugin.getConfig().getString("Ranks." + rank) != null){
            return true;
        }
        return false;
    }
    public static boolean hasRank(Player p){
        if(cPerms.plugin.getConfig().getStringList("Players." + p.getName() + ".rank") != null){
            return true;
        }
        return false;
    }
    public static void setRank(Player p, String rank){
        cPerms.plugin.getConfig().set("Players." + p.getName() + ".rank", rank);
        cPerms.plugin.saveConfig();
    }
    public static void createRank(String rank, String color){
        if(!isRank(rank)){
            cPerms.plugin.getConfig().set("Ranks." + rank + ".permissions", "");
            setColor(rank, color);
            cPerms.plugin.getConfig().set("Tag." + rank + ".prefix", "");
            List<String> tags = cPerms.plugin.getConfig().getStringList("Tag.");
            tags.add(rank);
            cPerms.plugin.saveConfig();
        }
    }
    public static void deleteRank(String rank){
        if(isRank(rank)){
            cPerms.plugin.getConfig().set("Ranks." + rank, null);
            cPerms.plugin.getConfig().set("Tag." + rank, null);
            cPerms.plugin.saveConfig();
        }
    }
    public static void setColor(String rank, String color){
        cPerms.plugin.getConfig().set("Ranks." + rank + ".color", color);
        cPerms.plugin.saveConfig();
    }
    public static String getColor(String rank){
        return cPerms.plugin.getConfig().getString("Ranks." + rank + ".color");
    }
}
