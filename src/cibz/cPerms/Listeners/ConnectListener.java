package cibz.cPerms.Listeners;

import cibz.cPerms.Main.Ranks;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class ConnectListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        Player p = e.getPlayer();
        String r = Ranks.getRank(p);
        String color = Ranks.getColor(r);

        p.setPlayerListName(color.replaceAll("&", "§") + p.getName());
    }
}
