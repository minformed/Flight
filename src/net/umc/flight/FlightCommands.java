package net.umc.flight;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FlightCommands {

    private final Flight plugin;

    public FlightCommands(Flight plugin) {
        this.plugin = plugin;
    }

    public void versionCommand(CommandSender sender, String[] args) {
        sender.sendMessage(Flight.pre + ChatColor.GREEN + "v" + plugin.getDescription().getVersion() + ChatColor.GRAY + " by " + ChatColor.GREEN + "ryanclancy000");
    }

    public void helpCommand(CommandSender sender, String label, String[] args) {

        if (args.length > 1) {
            sender.sendMessage(Flight.pre + ChatColor.RED + "Too many arguments!");
        } else {
            sender.sendMessage(ChatColor.WHITE + "--------------------- " + ChatColor.GRAY + "[" + ChatColor.GREEN + " Flight " + ChatColor.GRAY + "]" + ChatColor.WHITE + " ---------------------");
            if (sender.hasPermission("flight.on")) {
                sender.sendMessage(ChatColor.GREEN + "/" + label + " on [player]" + ChatColor.WHITE + " - " + ChatColor.GRAY + "Enable flight.");
            }
            if (sender.hasPermission("flight.off")) {
                sender.sendMessage(ChatColor.GREEN + "/" + label + " off [player]" + ChatColor.WHITE + " - " + ChatColor.GRAY + "Disable flight.");
            }
            if (sender.hasPermission("flight.toggle")) {
                sender.sendMessage(ChatColor.GREEN + "/" + label + " toggle [player]" + ChatColor.WHITE + " - " + ChatColor.GRAY + "Toggle flight.");
            }
            if (sender.hasPermission("flight.list")) {
                sender.sendMessage(ChatColor.GREEN + "/" + label + " list" + ChatColor.WHITE + " - " + ChatColor.GRAY + "List flying players flight.");
            }
            if (sender.hasPermission("flight.check")) {
                sender.sendMessage(ChatColor.GREEN + "/" + label + " check [player]" + ChatColor.WHITE + " - " + ChatColor.GRAY + "Check flight status.");
            }
            if (sender.hasPermission("flight.version")) {
                sender.sendMessage(ChatColor.GREEN + "/" + label + " version" + ChatColor.WHITE + " - " + ChatColor.GRAY + "Give plugin info.");
            }
        }
    }

    public void quickToggle(CommandSender sender) {

        Player player = (Player) sender;

        if (Utilities.isCreative(player)) {
            player.sendMessage(Flight.pre + ChatColor.RED + "Cannot change flight while in creative!");
            return;
        }

        if (Utilities.isFlightEnabled(player)) {
            Utilities.disableFlight(sender, player);
        } else {
            Utilities.enableFlight(sender, player);
        }
    }

    public void toggleCommand(CommandSender sender, String[] args) {

        if (args.length == 1) {

            if (!(sender instanceof Player)) {
                sender.sendMessage(Flight.pre + ChatColor.RED + "Console can't fly!");
                return;
            }

            Player player = (Player) sender;

            if (Utilities.isCreative(player)) {
                player.sendMessage(Flight.pre + ChatColor.RED + "Cannot change flight while in creative!");
                return;
            }

            if (Utilities.isFlightEnabled(player)) {
                Utilities.disableFlight(sender, player);
            } else {
                Utilities.enableFlight(sender, player);
            }
        } else if (args.length == 2 && sender.hasPermission("flight.toggle.other")) {

            Player target = plugin.getServer().getPlayer(args[1]);

            if (target == null) {
                sender.sendMessage(Flight.pre + ChatColor.RED + "Player not online!");
                return;
            }

            if (Utilities.isCreative(target)) {
                sender.sendMessage(Flight.pre + ChatColor.RED + "Can't edit flight for " + target.getName() + ", they are in creative mode!");
                return;
            }

            if (target.getAllowFlight()) {
                Utilities.disableFlight(sender, target);
            } else {
                Utilities.enableFlight(sender, target);
            }
        } else if (args.length > 2) {
            sender.sendMessage(Flight.pre + ChatColor.RED + "Too many arguments!");
        } else {
            sender.sendMessage(Flight.noPerms);
        }
    }

    public void flightOnCommand(CommandSender sender, String[] args) {

        if (args.length == 1) {

            if (!(sender instanceof Player)) {
                sender.sendMessage(Flight.pre + ChatColor.RED + "Console can't fly!");
                return;
            }

            Player player = (Player) sender;

            if (Utilities.isCreative(player)) {
                player.sendMessage(Flight.pre + ChatColor.RED + "Cannot change flight while in creative!");
                return;
            }

            if (Utilities.isFlightEnabled(player)) {
                player.sendMessage(Flight.pre + ChatColor.RED + "Flight already on!");
                return;
            }
            Utilities.enableFlight(sender, player);
        } else if (args.length == 2 && sender.hasPermission("flight.on.other")) {

            Player target = plugin.getServer().getPlayer(args[1]);

            if (target == null) {
                sender.sendMessage(Flight.pre + ChatColor.RED + "Player not online!");
                return;
            }

            if (Utilities.isCreative(target)) {
                sender.sendMessage(Flight.pre + ChatColor.RED + "Can't edit flight for " + target.getName() + ", they are in creative mode!");
                return;
            }

            if (Utilities.isFlightEnabled(target)) {
                sender.sendMessage(Flight.pre + ChatColor.RED + "Flight already on for " + target.getName());
                return;
            }
            Utilities.enableFlight(sender, target);
        } else if (args.length > 2) {
            sender.sendMessage(Flight.pre + ChatColor.RED + "Too many arguments!");
        } else {
            sender.sendMessage(Flight.noPerms);
        }
    }

    public void flightOffCommand(CommandSender sender, String[] args) {

        if (args.length == 1) {

            if (!(sender instanceof Player)) {
                sender.sendMessage(Flight.pre + ChatColor.RED + "Console can't fly!");
                return;
            }

            Player player = (Player) sender;

            if (Utilities.isCreative(player)) {
                player.sendMessage(Flight.pre + ChatColor.RED + "Cannot change flight while in creative!");
                return;
            }

            if (!Utilities.isFlightEnabled(player)) {
                player.sendMessage(Flight.pre + ChatColor.RED + "Flight already off!");
                return;
            }

            Utilities.disableFlight(sender, player);
        } else if (args.length == 2 && sender.hasPermission("flight.off.other")) {

            Player target = plugin.getServer().getPlayer(args[1]);

            if (target == null) {
                sender.sendMessage(Flight.pre + ChatColor.RED + "Player not online!");
                return;
            }

            if (Utilities.isCreative(target)) {
                sender.sendMessage(Flight.pre + ChatColor.RED + "Can't edit flight for " + target.getName() + ", they are in creative mode!");
                return;
            }

            if (!Utilities.isFlightEnabled(target)) {
                sender.sendMessage(Flight.pre + ChatColor.RED + "Flight already off for " + target.getName());
                return;
            }

            Utilities.disableFlight(sender, target);
        } else if (args.length > 2) {
            sender.sendMessage(Flight.pre + ChatColor.RED + "Too many arguments!");
        } else {
            sender.sendMessage(Flight.noPerms);
        }
    }

    public void checkCommand(CommandSender sender, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage(Flight.pre + ChatColor.RED + "Console can't fly!");
            return;
        }

        Player player = (Player) sender;

        if (args.length == 1) {

            if (Utilities.isCreative(player)) {
                sender.sendMessage(Flight.pre + ChatColor.RED + "You are in creative, of course mode is enabled!");
                return;
            }

            if (Utilities.isFlightEnabled(player)) {
                sender.sendMessage(Flight.pre + ChatColor.GREEN + "Your flight is enabled!");
            } else {
                sender.sendMessage(Flight.pre + ChatColor.RED + "Your flight is disabled!");
            }
        } else if (args.length == 2 && sender.hasPermission("flight.check.other")) {

            Player target = plugin.getServer().getPlayer(args[1]);

            if (target == null) {
                sender.sendMessage(Flight.pre + ChatColor.RED + "Player not online!");
                return;
            }

            if (Utilities.isCreative(target)) {
                sender.sendMessage(Flight.pre + ChatColor.RED + target.getName() + " is in creative, of course flight is enabled!");
                return;
            }

            if (Utilities.isFlightEnabled(target)) {
                sender.sendMessage(Flight.pre + ChatColor.GREEN + target.getName() + " has flight enabled!");
            } else {
                sender.sendMessage(Flight.pre + ChatColor.RED + target.getName() + " has flight disabled!");
            }
        } else if (args.length > 2) {
            sender.sendMessage(Flight.pre + ChatColor.RED + "Too many arguments!");
        } else {
            sender.sendMessage(Flight.noPerms);
        }
    }

    public void listCommand(CommandSender sender, String[] args) {

        if (args.length != 1) {
            sender.sendMessage(Flight.pre + ChatColor.RED + "Too many arguments!");
            return;
        }

        StringBuilder sb = new StringBuilder();
        List<String> list = new ArrayList<String>();

        for (Player p : Bukkit.getOnlinePlayers()) {
            String pName = "";
            if (Utilities.isFlightEnabled(p) && !Utilities.isCreative(p)) {
                pName = p.getName();
                list.add(pName);
            }
        }
        Collections.sort(list, String.CASE_INSENSITIVE_ORDER);
        for (String pName : list) {
            if (sb.length() > 0) {
                sb.append(ChatColor.WHITE + ", ");
            }
            sb.append(pName);
        }
        sender.sendMessage(Flight.pre + ChatColor.GRAY + "Flight Mode Enabled: " + list);
    }
}