package me.ryanclancy000.flight;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

public class FlightListener implements Listener {

    public Flight plugin;
    public FlightCommands cHandler;

    public FlightListener(Flight instance) {
        this.plugin = instance;
    }

    @EventHandler
    public void playerDamage(EntityDamageEvent event) {
        
        if (!plugin.godMode) {
            return;
        }

        Entity entity = event.getEntity();
        if (event.getEntity() instanceof Player) {
            Player player = (Player) entity;
            if (player.getAllowFlight()) {
                event.setCancelled(true);
            }
        }

    }
    
    @EventHandler
    public void pvpCheck(EntityDamageByEntityEvent event) {
        Entity attacker = event.getDamager();
        Entity receiver = event.getEntity();
        if ((attacker instanceof Player) && (receiver instanceof Player) && plugin.antiPVP) {
            Player patt = (Player) attacker;
            Player prec = (Player) receiver;
            if (patt.getAllowFlight()) {
                event.setCancelled(true);
            }
        }
    }
}
