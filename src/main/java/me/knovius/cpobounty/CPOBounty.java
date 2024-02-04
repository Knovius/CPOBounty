package me.knovius.cpobounty;

import me.knovius.cpobounty.commands.adminBountyCommand;
import me.knovius.cpobounty.commands.bountyTopCommand;
import me.knovius.cpobounty.data.PlayerManager;
import me.knovius.cpobounty.listener.PlayerQuitListener;
import me.knovius.cpobounty.placeholder.BountyPlaceHolder;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class CPOBounty extends JavaPlugin {

    public static CPOBounty instance;

    private PlayerManager playerManager;

    @Override
    public void onEnable() {
        // Plugin startup logic
        playerManager = new PlayerManager(this);
        instance = this;

        if(Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            new BountyPlaceHolder(this).register();
        }

        this.getCommand("bounty").setExecutor(new adminBountyCommand());
        this.getCommand("bountytop").setExecutor(new bountyTopCommand());

        this.getServer().getPluginManager().registerEvents(new PlayerQuitListener(), this);

        saveDefaultConfig();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static CPOBounty getInstance() {
        return instance;
    }

    public PlayerManager getPlayerManager() {
        return playerManager;
    }
}
