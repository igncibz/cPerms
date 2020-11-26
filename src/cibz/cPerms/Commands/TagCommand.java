package cibz.cPerms.Commands;

import cibz.cPerms.Listeners.TagGUIListener;
import cibz.cPerms.Main.Tags;
import cibz.cPerms.Main.cPerms;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TagCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player p = (Player) sender;

        if(args.length == 0){
            if(cPerms.plugin.getConfig().getConfigurationSection("Tag").getKeys(false) != null){
                TagGUIListener.tagGUI(p);
            } else {
                p.sendMessage("§cNo tags available.");
            }
        }
        if(args.length == 1){
            if(p.isOp()){
                if(args[0].equalsIgnoreCase("help")){
                    p.sendMessage("§8§m---------------------------");
                    p.sendMessage("§d§lTag Help");
                    p.sendMessage("§d§l* §f/tag create §d<tag>");
                    p.sendMessage("§d§l* §f/tag delete §d<tag>");
                    p.sendMessage("§d§l* §f/tag prefix §d<tag> <prefix>");
                    p.sendMessage("§d§l* §f/tag set §d<player> <tag>");
                    p.sendMessage("§8§m---------------------------");
                }
            } else {
                if(args[0].equalsIgnoreCase("clear")){
                    Tags.removePlayerPrefix(p);
                    p.sendMessage("§cTag removed.");
                }
            }
        }
        if(args.length == 2){
            if(p.isOp()){
                if(args[0].equalsIgnoreCase("create")){
                    String tag = args[1];
                    if(cPerms.plugin.getConfig().getString("Tag." + tag) == null){
                        Tags.createTag(tag);
                        p.sendMessage("§dSuccessfully created the tag §f" + tag);
                    } else {
                        p.sendMessage("§cThis is already a prefix.");
                    }
                }
                if(args[0].equalsIgnoreCase("delete")){
                    String tag = args[1];
                    if(cPerms.plugin.getConfig().getString("Tag." + tag) != null){
                        Tags.deleteTag(tag);
                        p.sendMessage("§dSuccessfully deleted the tag " + tag);
                    } else {
                        p.sendMessage("§cThis is not a prefix.");
                    }
                }
            }
        }
        if(args.length == 3){
            if(p.isOp()){
                if(args[0].equalsIgnoreCase("set")) {
                    String player = args[1];
                    String tag = args[2];
                    if (player != null) {
                        Player pl = Bukkit.getPlayer(player);
                        if (pl != null) {
                            if (pl.isOnline()) {
                                if (cPerms.plugin.getConfig().getString("Tag." + tag) != null) {
                                    Tags.setPlayerPrefix(pl, tag);
                                    p.sendMessage("§dTag §f" + tag + " §dhas been set on §f" + pl.getName());
                                } else {
                                    p.sendMessage("§cThis is not a tag");
                                }
                            } else {
                                p.sendMessage("§cThis player is not online");
                            }
                        } else {
                            p.sendMessage("§cThis is not a player");
                        }
                    } else {
                        p.sendMessage("§cThis is not a player");
                    }
                }
                if(args[0].equalsIgnoreCase("prefix")){
                    String tag = args[1];
                    String prefix = args[2];
                    if(cPerms.plugin.getConfig().getString("Tag." + tag) != null){
                        Tags.setTagPrefix(tag, prefix);
                        p.sendMessage("§dPrefix has been set on §f" + tag);
                    } else {
                        p.sendMessage("§cThis is not a prefix");
                    }
                }
            }
        }
        return true;
    }
}
