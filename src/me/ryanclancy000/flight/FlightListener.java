package me.ryanclancy000.flight;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class FlightListener implements Listener {

    public Flight plugin;
    public FlightCommands cHandler;

    public FlightListener(Flight instance) {
        this.plugin = instance;
    }

    public void playerDamage(EntityDamageEvent event) {

        Entity entity = event.getEntity();
        if (event.getEntity() instanceof Player) {
           
            Player player = (Player) entity;
            
            if (cHandler.flyModeEnabled(player)) {
                event.setCancelled(true);
            }

        }

    }
}
