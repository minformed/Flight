package me.ryanclancy000.flight.listeners;

import me.ryanclancy000.flight.Flight;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class GodListener implements Listener {
    
    private final Flight plugin;
    
    public GodListener(Flight plugin) {
        this.plugin = plugin;
    }
    
    @EventHandler
    public void playerDamage(EntityDamageEvent event) {
        
        Entity e = event.getEntity();
        
        if (e instanceof Player) {
            Player player = (Player) e;
            if (plugin.flyers.contains(player) && player.hasPermission("flight.god")) {
                event.setCancelled(true);
            }
        }
        
    }

}
