package net.umc.flight;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Utilities {
    
    public static void enableFlight(CommandSender sender, Player player) {
        player.setAllowFlight(true);
        if (sender.getName().equalsIgnoreCase(player.getName())) {
            player.sendMessage(Flight.pre + ChatColor.GREEN + "Flight enabled!");
        } else {
            player.sendMessage(Flight.pre + ChatColor.GREEN + "Flight enabled by " + sender.getName());
            sender.sendMessage(Flight.pre + ChatColor.GREEN + "Flight enabled for " + player.getName());
        }
    }

    public static void disableFlight(CommandSender sender, Player player) {
        player.setAllowFlight(false);
        player.setFlying(false);
        if (sender.getName().equalsIgnoreCase(player.getName())) {
            player.sendMessage(Flight.pre + ChatColor.RED + "Flight disabled!");
        } else {
            player.sendMessage(Flight.pre + ChatColor.RED + "Flight disabled by " + sender.getName());
            sender.sendMessage(Flight.pre + ChatColor.RED + "Flight disabled for " + player.getName());
        }
    }

    public static boolean isFlightEnabled(Player player) {
        if (player.getAllowFlight()) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean currentlyInFlight(Player player) {
        if (player.isFlying()) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isCreative(Player player) {
        if (player.getGameMode() == GameMode.CREATIVE) {
            return true;
        } else {
            return false;
        }
    }
}