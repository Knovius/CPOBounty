package me.knovius.cpobounty.commands;

import me.knovius.cpobounty.CPOBounty;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.text.DecimalFormat;
import java.util.*;

public class bountyTopCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (command.getName().equalsIgnoreCase("bountytop")) {
            if (CPOBounty.getInstance().getPlayerManager().getBountyMap() == null || CPOBounty.getInstance().getPlayerManager().getBountyMap().isEmpty()) {
                commandSender.sendMessage("Error! No bounties found...");
                return true;
            }

            List<Map.Entry<UUID, Double>> topBountyList = CPOBounty.getInstance().getPlayerManager().getTopBounties(CPOBounty.getInstance().getConfig().getInt("bounty-leaderboard"));
            commandSender.sendMessage(ChatColor.translateAlternateColorCodes('&', CPOBounty.getInstance().getConfig().getString("bounty-top-header")));

            for (int x= 0; x < topBountyList.size(); x++) {
                Map.Entry<UUID, Double> entry = topBountyList.get(x);
                String playerName = Bukkit.getOfflinePlayer((entry.getKey())).getName();
                double bountyValue = entry.getValue();
                String message = CPOBounty.getInstance().getConfig().getString("bounty-top-footer");
                commandSender.sendMessage(ChatColor.translateAlternateColorCodes('&', message.replaceAll("%POS%", String.valueOf(x+1))
                        .replaceAll("%PLAYER%", playerName)
                        .replaceAll("%BOUNTY%", CPOBounty.getInstance().getPlayerManager().format(bountyValue))));
            }

        }

        return false;
    }

}
