package cibz.cPerms.Main;

import cibz.cPerms.Commands.GrantCommand;
import cibz.cPerms.Commands.RankCommand;
import cibz.cPerms.Commands.TagCommand;
import cibz.cPerms.Listeners.ChatListener;
import cibz.cPerms.Listeners.GrantGUIListener;
import cibz.cPerms.Listeners.TagGUIListener;
import org.bukkit.plugin.java.JavaPlugin;

public class cPerms extends JavaPlugin {

    public static cPerms plugin;

    @Override
    public void onDisable() {
        saveConfig();
    }

    @Override
    public void onEnable() {
        plugin = this;
        getConfig().options().copyDefaults(true);

        getCommand("Tag").setExecutor(new TagCommand());
        getCommand("Rank").setExecutor(new RankCommand());
        getCommand("Grant").setExecutor(new GrantCommand());

        getServer().getPluginManager().registerEvents(new ChatListener(), this);
        getServer().getPluginManager().registerEvents(new GrantGUIListener(), this);
        getServer().getPluginManager().registerEvents(new TagGUIListener(), this);
    }
}
