package cibz.cPerms.Main;

import org.bukkit.entity.Player;

import java.util.List;

public class Tags {

    public static boolean hasPrefix(Player p){
        if(cPerms.plugin.getConfig().getStringList("Players." + p.getName() + ".prefix") != null){
            return true;
        }
        return false;
    }
    public static String getPlayerPrefix(Player p){
        return cPerms.plugin.getConfig().getString("Players." + p.getName() + ".prefix");
    }
    public static void setPlayerPrefix(Player p, String tag){
        cPerms.plugin.getConfig().set("Players." + p.getName() + ".prefix", tag);
        cPerms.plugin.saveConfig();
    }
    public static void removePlayerPrefix(Player p){
        cPerms.plugin.getConfig().set("Players." + p.getName() + ".prefix", "default");
        cPerms.plugin.saveConfig();
    }

    public static void createTag(String tag){
        List<String> tags = cPerms.plugin.getConfig().getStringList("Tag.");
        tags.add(tag);
        cPerms.plugin.getConfig().set("Tag." + tag + ".prefix", "");
        cPerms.plugin.saveConfig();
    }
    public static void deleteTag(String tag){
        List<String> tags = cPerms.plugin.getConfig().getStringList("Tag.");
        tags.remove(tag);
        cPerms.plugin.getConfig().set("Tag." + tag, null);
        cPerms.plugin.saveConfig();
    }
    public static void setTagPrefix(String tag, String prefix){
        cPerms.plugin.getConfig().set("Tag." + tag + ".prefix", prefix);
        cPerms.plugin.saveConfig();
    }
    public static String getTagPrefix(String tag){
        return cPerms.plugin.getConfig().getString("Tag." + tag + ".prefix");
    }
}
