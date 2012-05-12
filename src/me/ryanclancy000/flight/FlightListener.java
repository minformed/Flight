package me.ryanclancy000.flight;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class FlightListener implements Listener {

    public Flight plugin;
    public Util u;

    public FlightListener(Flight instance) {
        this.plugin = instance;
    }

    @EventHandler
    public void playerDamage(EntityDamageEvent event) {

        Entity entity = event.getEntity();
        if (event.getEntity() instanceof Player) {

            Player player = (Player) entity;

            if (u.flyModeEnabled(player)) {
                event.setCancelled(true);
            }

        }

    }
    
    @EventHandler
    public void playerLogin(PlayerJoinEvent event) {
        
        Player player = event.getPlayer();
        
        if (plugin.enablePlayers.contains(event.getPlayer().getName())) {
            u.enableFly(player);
        }
        
    }
}
