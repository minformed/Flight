package net.umc.flight.utils;

import net.umc.flight.Flight;
import org.bukkit.GameMode;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Utilities {
    
    public void enableFlight(CommandSender sender, Player player) {
        player.setAllowFlight(true);
        if (sender.getName().equalsIgnoreCase(player.getName())) {
            player.sendMessage(Flight.pre + Flight.green + "Flight enabled!");
        } else {
            player.sendMessage(Flight.pre + Flight.green + "Flight enabled by " + sender.getName());
            sender.sendMessage(Flight.pre + Flight.green + "Flight enabled for " + player.getName());
        }
    }

    public void disableFlight(CommandSender sender, Player player) {
        player.setAllowFlight(false);
        player.setFlying(false);
        if (sender.getName().equalsIgnoreCase(player.getName())) {
            player.sendMessage(Flight.pre + Flight.red + "Flight disabled!");
        } else {
            player.sendMessage(Flight.pre + Flight.red + "Flight disabled by " + sender.getName());
            sender.sendMessage(Flight.pre + Flight.red + "Flight disabled for " + player.getName());
        }
    }

    public boolean isFlightEnabled(Player player) {
        if (player.getAllowFlight()) {
            return true;
        } else {
            return false;
        }
    }

    public boolean currentlyInFlight(Player player) {
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
