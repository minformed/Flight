package com.rylinaux.flight;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerChangedWorldEvent;

public class FlightListener implements Listener {

    @EventHandler
    public void onWorldChange(PlayerChangedWorldEvent event) {
        Player player = event.getPlayer();
        if (player.getAllowFlight() && !(player.getGameMode() == GameMode.CREATIVE)) {
            player.setAllowFlight(true);
        }
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();
        if (player.getAllowFlight() && !(player.getGameMode() == GameMode.CREATIVE)) {
            player.setAllowFlight(true);
        }
    }
}