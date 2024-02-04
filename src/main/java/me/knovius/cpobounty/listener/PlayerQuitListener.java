package me.knovius.cpobounty.listener;

import me.knovius.cpobounty.CPOBounty;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuitListener implements Listener {

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        CPOBounty.getInstance().getPlayerManager().saveData(player);
    }
}