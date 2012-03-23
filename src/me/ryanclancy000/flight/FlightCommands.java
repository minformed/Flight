package me.ryanclancy000.flight;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class FlightCommands {

    String pre = ChatColor.GOLD + "[Flight] ";
    ChatColor green = ChatColor.GREEN;
    ChatColor red = ChatColor.RED;
    public Flight plugin;

    public FlightCommands(Flight instance) {
        plugin = instance;
    }

    public void helpCommand(CommandSender sender, String[] args) {
    }

    public void toggleCommand(CommandSender sender, String[] args) {
    }
}
