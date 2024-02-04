package me.knovius.cpobounty.placeholder;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import me.knovius.cpobounty.CPOBounty;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public class BountyPlaceHolder extends PlaceholderExpansion {

    private final CPOBounty plugin;

    public BountyPlaceHolder(CPOBounty plugin) {
        this.plugin = plugin;
    }

    @Override
    public @NotNull String getIdentifier() {
        return "cpobounty";
    }

    @Override
    public @NotNull String getAuthor() {
        return "knovius";
    }

    @Override
    public @NotNull String getVersion() {
        return "1.0";
    }

    @Override
    public boolean persist() {
        return true; // This is required or else PlaceholderAPI will unregister the Expansion on reload
    }

    @Override
    public String onRequest(OfflinePlayer player, String params) {

        List<Map.Entry<UUID, Double>> list = plugin.getPlayerManager().getTopBounties(5);

        //First Player Bounty
        if(params.equalsIgnoreCase("firstPlayerName")){

            return Bukkit.getOfflinePlayer(list.get(0).getKey()).getName();
        }

        if(params.equalsIgnoreCase("firstPlayerBounty")){
            return plugin.getPlayerManager().format(list.get(0).getValue());
        }
        //Second
        if(params.equalsIgnoreCase("secondPlayerName")){

            return Bukkit.getOfflinePlayer(list.get(1).getKey()).getName();
        }

        if(params.equalsIgnoreCase("secondPlayerBounty")){
            return plugin.getPlayerManager().format(list.get(1).getValue());
        }
        //third
        if(params.equalsIgnoreCase("thirdPlayerName")){

            return Bukkit.getOfflinePlayer(list.get(2).getKey()).getName();
        }

        if(params.equalsIgnoreCase("thirdPlayerBounty")){
            return plugin.getPlayerManager().format(list.get(2).getValue());
        }
        //Fourth
        if(params.equalsIgnoreCase("fourthPlayerName")){

            return Bukkit.getOfflinePlayer(list.get(3).getKey()).getName();
        }

        if(params.equalsIgnoreCase("fourthPlayerBounty")){
            return plugin.getPlayerManager().format(list.get(3).getValue());
        }
        //fifth
        if(params.equalsIgnoreCase("fifthPlayerName")){

            return Bukkit.getOfflinePlayer(list.get(4).getKey()).getName();
        }

        if(params.equalsIgnoreCase("fifthPlayerBounty")){
            return plugin.getPlayerManager().format(list.get(4).getValue());
        }
        return null; // Placeholder is unknown by the expansion
    }

}
