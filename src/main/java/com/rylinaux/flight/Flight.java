package com.rylinaux.flight;

import java.io.IOException;
import java.util.logging.Level;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public class Flight extends JavaPlugin {

    /* The command handler */
    private final FlightCommands commandHandler = new FlightCommands(this);

    /* The plugin's prefix */
    public static final String pre = ChatColor.GRAY + "[" + ChatColor.GREEN + "Flight" + ChatColor.GRAY + "] ";

    /* The no permission message */
    public static final String noPerms = pre + ChatColor.RED + "You don't have permission to do this...";

    @Override
    public void onEnable() {
        getCommand("flight").setExecutor(this);
        getServer().getPluginManager().registerEvents(new FlightListener(), this);
        initMetricsLite();
    }

    private void initMetricsLite() {
        try {
            final MetricsLite metrics = new MetricsLite(this);
            metrics.start();
        } catch (final IOException e) {
            getLogger().log(Level.WARNING, "MetricsLite failed to start! {0}", e);
        }
    }

    @Override
    public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {

        if (args.length == 0) {
            if (sender.hasPermission("flight.toggle")) {
                commandHandler.toggleCommand(sender, args);
            } else {
                sender.sendMessage(noPerms);
            }
        } else if ("help".equalsIgnoreCase(args[0])) {
            if (sender.hasPermission("flight.help")) {
                commandHandler.helpCommand(sender, label);
            } else {
                sender.sendMessage(noPerms);
            }
        } else if ("toggle".equalsIgnoreCase(args[0])) {
            if (sender.hasPermission("flight.toggle")) {
                commandHandler.toggleCommand(sender, args);
            } else {
                sender.sendMessage(noPerms);
            }
        } else if ("on".equalsIgnoreCase(args[0])) {
            if (sender.hasPermission("flight.on")) {
                commandHandler.flightOnCommand(sender, args);
            } else {
                sender.sendMessage(noPerms);
            }
        } else if ("off".equalsIgnoreCase(args[0])) {
            if (sender.hasPermission("flight.off")) {
                commandHandler.flightOffCommand(sender, args);
            } else {
                sender.sendMessage(noPerms);
            }
        } else if ("check".equalsIgnoreCase(args[0])) {
            if (sender.hasPermission("flight.check")) {
                commandHandler.checkCommand(sender, args);
            } else {
                sender.sendMessage(noPerms);
            }
        } else if ("list".equalsIgnoreCase(args[0])) {
            if (sender.hasPermission("flight.list")) {
                commandHandler.listCommand(sender);
            } else {
                sender.sendMessage(noPerms);
            }
        } else {
            commandHandler.helpCommand(sender, label);
        }

        return true;
    }
}