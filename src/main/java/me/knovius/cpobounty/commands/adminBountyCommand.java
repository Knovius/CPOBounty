package me.knovius.cpobounty.commands;

import me.knovius.cpobounty.CPOBounty;
import org.apache.commons.lang.math.NumberUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class adminBountyCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (command.getName().equalsIgnoreCase("bounty")) {
            if (!(sender.hasPermission("cpobounty.admin"))) {
                sender.sendMessage("No permission!");
                return true;
            }
            // /bounty <give,set,remove> player <amount>
            if (args.length < 1 || args[0].length() < 1) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&2/&abounty &2<&agive&2, &aset&2, &aremove&2&2> <&aplayer&2> <&aamount&2>"));
                return true;
            }
            //Check reload first no other args than 0
            if (args.length < 2 || args[1].length() < 1) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&2/&abounty &2<&agive&2, &aset&2, &aremove&2&2> <&aplayer&2> <&aamount&2>"));
                return true;
            }
            if (args.length < 3  || args[2].length() < 1 || !(NumberUtils.isNumber(args[2]))) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&2/&abounty &2<&agive&2, &aset&2, &aremove&2&2> <&aplayer&2> <&aamount&2>"));
                return true;
            }
            Player target = Bukkit.getServer().getPlayer(args[1]);
            if (target == null) {
                sender.sendMessage("Player Null");
                return true;
            }
            if (args[0].equalsIgnoreCase("give")) {
                CPOBounty.getInstance().getPlayerManager().addBounty(target, Double.valueOf(args[2]));
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aSuccess!"));
                return true;
            }
            if (args[0].equalsIgnoreCase("set")) {
                CPOBounty.getInstance().getPlayerManager().setBounty(target, Double.valueOf(args[2]));
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aSuccess!"));

                return true;
            }
            if (args[0].equalsIgnoreCase("remove")) {
                CPOBounty.getInstance().getPlayerManager().removeBounty(target, Double.valueOf(args[2]));
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aSuccess!"));

                return true;
            }
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&2/&abounty &2<&agive&2, &aset&2, &aremove&2&2> <&aplayer&2> <&aamount&2>"));
            return true;

        }
        return false;
    }
}