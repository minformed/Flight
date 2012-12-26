package net.umc.flight;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerChangedWorldEvent;

public class FlightListener implements Listener {
    
    private final Flight plugin;
    
    public FlightListener(Flight plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onWorldChange(PlayerChangedWorldEvent event) {
        Player player = event.getPlayer();
        if (player.getAllowFlight() && !plugin.getUtils().isCreative(player)) {
            player.setAllowFlight(true);
        }
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();
        if (player.getAllowFlight() && !plugin.getUtils().isCreative(player)) {
            player.setAllowFlight(true);
        }
    }
}
