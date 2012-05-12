package me.ryanclancy000.flight;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;

public class Util {

    public void enableFly(Player player) {
        player.setAllowFlight(true);
        player.setFlying(true);
    }

    public void disableFly(Player player) {
        player.setAllowFlight(false);
        player.setFlying(false);
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
