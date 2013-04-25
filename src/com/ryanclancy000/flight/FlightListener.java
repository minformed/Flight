package com.ryanclancy000.flight;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerChangedWorldEvent;

public class FlightListener implements Listener {

    @EventHandler
    public void onWorldChange(final PlayerChangedWorldEvent event) {
        final Player player = event.getPlayer();
        if (player.getAllowFlight() && !(player.getGameMode() == GameMode.CREATIVE)) {
            player.setAllowFlight(true);
        }
    }

    @EventHandler
    public void onDeath(final PlayerDeathEvent event) {
        final Player player = event.getEntity();
        if (player.getAllowFlight() && !(player.getGameMode() == GameMode.CREATIVE)) {
            player.setAllowFlight(true);
        }
    }
}