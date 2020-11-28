package cibz.cPerms.Commands;

import cibz.cPerms.Main.Ranks;
import cibz.cPerms.Main.cPerms;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

public class ListCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player p = (Player) sender;

        ConfigurationSection rank = cPerms.plugin.getConfig().getConfigurationSection("Ranks");
        StringBuilder b = new StringBuilder();
        for (String s : rank.getKeys(false)){
            String color = Ranks.getColor(s);
            b.append(color + s);
            b.append(", ");
        }
        String ranks = b.toString().substring(0, b.toString().length() -2);

        StringBuilder pl = new StringBuilder();
        for (Player o : Bukkit.getServer().getOnlinePlayers()){
            String pr = Ranks.getRank(o);
            String color2 = Ranks.getColor(pr);
            pl.append(color2 + o.getName());
            pl.append(", ");
        }
        String players = pl.toString().substring(0, pl.toString().length() -2);
        int online = 0;
        for(Player op : Bukkit.getServer().getOnlinePlayers()){
            online++;
        }

        p.sendMessage(ranks.replaceAll("&", "§"));
        p.sendMessage(" ");
        p.sendMessage("§7[§f" + online + "/" + Bukkit.getServer().getMaxPlayers() + "§7] §f" + players.replaceAll("&", "§"));
        return true;
    }
}
