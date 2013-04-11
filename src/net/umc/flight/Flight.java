package net.umc.flight;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class Flight extends JavaPlugin {

    private final FlightCommands cHandler = new FlightCommands(this);
    public static final String pre = ChatColor.GRAY + "[" + ChatColor.GREEN + "Flight" + ChatColor.GRAY + "] ";
    public static final String noPerms = pre + ChatColor.RED + "You don't have permission to do this...";

    @Override
    public void onEnable() {
        getCommand("flight").setExecutor(this);
        getServer().getPluginManager().registerEvents(new FlightListener(), this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (sender instanceof Player) {
            sender = (Player) sender;
        }

        if (args.length == 0) {
            if (sender.hasPermission("flight.toggle")) {
                cHandler.toggleCommand(sender, args);
            } else {
                sender.sendMessage(noPerms);
            }
        } else if ("version".equalsIgnoreCase(args[0])) {
            if (sender.hasPermission("flight.version")) {
                cHandler.versionCommand(sender);
            } else {
                sender.sendMessage(noPerms);
            }
        } else if ("help".equalsIgnoreCase(args[0])) {
            if (sender.hasPermission("flight.help")) {
                cHandler.helpCommand(sender, label, args);
            } else {
                sender.sendMessage(noPerms);
            }
        } else if ("toggle".equalsIgnoreCase(args[0])) {
            if (sender.hasPermission("flight.toggle")) {
                cHandler.toggleCommand(sender, args);
            } else {
                sender.sendMessage(noPerms);
            }
        } else if ("on".equalsIgnoreCase(args[0])) {
            if (sender.hasPermission("flight.on")) {
                cHandler.flightOnCommand(sender, args);
            } else {
                sender.sendMessage(noPerms);
            }
        } else if ("off".equalsIgnoreCase(args[0])) {
            if (sender.hasPermission("flight.off")) {
                cHandler.flightOffCommand(sender, args);
            } else {
                sender.sendMessage(noPerms);
            }
        } else if ("check".equalsIgnoreCase(args[0])) {
            if (sender.hasPermission("flight.check")) {
                cHandler.checkCommand(sender, args);
            } else {
                sender.sendMessage(noPerms);
            }
        } else if ("list".equalsIgnoreCase(args[0])) {
            if (sender.hasPermission("flight.list")) {
                cHandler.listCommand(sender, args);
            } else {
                sender.sendMessage(noPerms);
            }
        } else {
            cHandler.helpCommand(sender, label, args);
        }
        
        return true;
    }
}