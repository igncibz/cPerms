package cibz.cPerms.Commands;

import cibz.cPerms.Main.Ranks;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RankCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player p = (Player) sender;

        if (p.isOp()) {
            if(args.length == 0){
                p.sendMessage("§8§m---------------------------");
                p.sendMessage("§d§lRank Help");
                p.sendMessage("§d§l* §f/rank create §d<color> <rank>");
                p.sendMessage("§d§l* §f/rank delete §d<rank>");
                p.sendMessage("§8§m---------------------------");
            }
            if(args.length == 1){
                p.sendMessage("§8§m---------------------------");
                p.sendMessage("§d§lRank Help");
                p.sendMessage("§d§l* §f/rank create §d<color> <rank>");
                p.sendMessage("§d§l* §f/rank delete §d<rank>");
                p.sendMessage("§8§m---------------------------");
            }
            if(args.length == 2){
                if(args[0].equalsIgnoreCase("delete")){
                    String rank = args[1];
                    if(Ranks.isRank(rank)){
                        Ranks.deleteRank(rank);
                        p.sendMessage("§dSuccessfully deleted §f" + rank + " §drank.");
                    } else {
                        p.sendMessage("§cThis isn't a rank.");
                    }
                }
            }
            if(args.length == 3){
                if(args[0].equalsIgnoreCase("create")){
                    String color = args[1];
                    String rank = args[2];
                    if(!Ranks.isRank(rank)){
                        Ranks.createRank(rank, color);
                        Ranks.setColor(rank, color);
                        p.sendMessage("§dSuccessfully created " + ChatColor.translateAlternateColorCodes('&', color) + rank + " §drank.");
                    } else {
                        p.sendMessage("§cAlready a rank.");
                    }
                }
            }
            if(args.length == 4){
            }
        }
        return true;
    }
}
