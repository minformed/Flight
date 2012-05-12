package me.ryanclancy000.flight;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;

public class Util {
    
    public Flight plugin;

    public Util(Flight instance) {
        this.plugin = instance;
    }

    public void enableFly(Player player) {
        player.setAllowFlight(true);
        player.setFlying(true);
        if (!plugin.enablePlayers.contains(player.getName()) && plugin.useEnabledPlayers) {
            plugin.enablePlayers.add(player.getName());
        }
    }

    public void disableFly(Player player) {
        player.setAllowFlight(false);
        player.setFlying(false);
        if (plugin.enablePlayers.contains(player.getName()) && plugin.useEnabledPlayers) {
            plugin.enablePlayers.remove(player.getName());
        }
    }

    public boolean flyModeEnabled(Player player) {

        if (player.getAllowFlight()) {
            return true;
        } else {
            return false;
        }

    }

    public boolean currentlyFlying(Player player) {

        if (player.isFlying()) {
            return true;
        } else {
            return false;
        }

    }

    public boolean isCreative(Player player) {

        if (player.getGameMode() == GameMode.CREATIVE) {
            return true;
        } else {
            return false;
        }
        
    }
    
}
