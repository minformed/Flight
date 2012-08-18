package net.umc.flight;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

public class FlightListener implements Listener {

    private final Flight plugin;

    public FlightListener(Flight plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onWorldChange(PlayerChangedWorldEvent event) {
        Player player = event.getPlayer();
        if (plugin.getFlyers().contains(player.getName())) {
            player.setAllowFlight(true);
        }
    }

    @EventHandler
    public void onRespawn(PlayerRespawnEvent event) {
        Player player = event.getPlayer();
        if (plugin.getFlyers().contains(player.getName())) {
            player.setAllowFlight(true);
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        if (plugin.getFlyers().contains(player.getName())) {
            plugin.getFlyers().remove(player.getName());
        }
    }
}
