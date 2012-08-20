package net.umc.flight;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FlightCommands {

    private final Flight plugin;

    public FlightCommands(Flight plugin) {
        this.plugin = plugin;
    }

    public void versionCommand(CommandSender sender, String[] args) {
        sender.sendMessage(Flight.pre + Flight.green + "v" + plugin.getDescription().getVersion() + Flight.gray + " by " + Flight.green + "ryanclancy000");
    }

    public void helpCommand(CommandSender sender, String label, String[] args) {

        if (args.length > 1) {
            sender.sendMessage(Flight.pre + Flight.red + "Too many arguments!");
            return;
        }

        sender.sendMessage(Flight.white + "--------------------- " + Flight.gray + "[" + Flight.green + " Flight " + Flight.gray + "]" + Flight.white + " ---------------------");
        if (sender.hasPermission("flight.on")) {
            sender.sendMessage(Flight.green + "/flight on [player]" + Flight.white + " - " + Flight.gray + "Enable flight.");
        }
        if (sender.hasPermission("flight.off")) {
            sender.sendMessage(Flight.green + "/flight off [player]" + Flight.white + " - " + Flight.gray + "Disable flight.");
        }
        if (sender.hasPermission("flight.toggle")) {
            sender.sendMessage(Flight.green + "/flight toggle [player]" + Flight.white + " - " + Flight.gray + "Toggle flight.");
        }
        if (sender.hasPermission("flight.list")) {
            sender.sendMessage(Flight.green + "/flight list" + Flight.white + " - " + Flight.gray + "List flying players flight.");
        }
        if (sender.hasPermission("flight.check")) {
            sender.sendMessage(Flight.green + "/flight check [player]" + Flight.white + " - " + Flight.gray + "Check flight status.");
        }
        if (sender.hasPermission("flight.version")) {
            sender.sendMessage(Flight.green + "/flight version" + Flight.white + " - " + Flight.gray + "Give plugin info.");
        }
    }

    public void quickToggle(CommandSender sender) {

        Player player = (Player) sender;

        if (isCreative(player)) {
            player.sendMessage(Flight.pre + Flight.red + "Cannot change flight while in creative!");
            return;
        }

        if (flyModeEnabled(player)) {
            disableFly(sender, player);
        } else {
            enableFly(sender, player);
        }
    }

    public void toggleCommand(CommandSender sender, String[] args) {

        if (args.length == 1) {

            if (!(sender instanceof Player)) {
                sender.sendMessage(Flight.pre + Flight.red + "Console can't fly!");
                return;
            }

            Player player = (Player) sender;

            if (isCreative(player)) {
                player.sendMessage(Flight.pre + Flight.red + "Cannot change flight while in creative!");
                return;
            }

            if (flyModeEnabled(player)) {
                disableFly(sender, player);
            } else {
                enableFly(sender, player);
            }
        } else if (args.length == 2 && sender.hasPermission("flight.toggle.other")) {

            Player target = plugin.getServer().getPlayer(args[1]);

            if (target == null) {
                sender.sendMessage(Flight.pre + Flight.red + "Player not online!");
                return;
            }

            if (isCreative(target)) {
                sender.sendMessage(Flight.pre + Flight.red + "Can't edit flight for " + target.getName() + ", they are in creative mode!");
                return;
            }

            if (target.getAllowFlight()) {
                disableFly(sender, target);
            } else {
                enableFly(sender, target);
            }
        } else if (args.length > 2) {
            sender.sendMessage(Flight.pre + Flight.red + "Too many arguments!");
        } else {
            plugin.noPerms(sender);
        }
    }

    public void flightOnCommand(CommandSender sender, String[] args) {

        if (args.length == 1) {

            if (!(sender instanceof Player)) {
                sender.sendMessage(Flight.pre + Flight.red + "Console can't fly!");
                return;
            }

            Player player = (Player) sender;

            if (isCreative(player)) {
                player.sendMessage(Flight.pre + Flight.red + "Cannot change flight while in creative!");
                return;
            }

            if (flyModeEnabled(player)) {
                player.sendMessage(Flight.pre + Flight.red + "Flight already on!");
                return;
            }
            enableFly(sender, player);
        } else if (args.length == 2 && sender.hasPermission("flight.on.other")) {

            Player target = plugin.getServer().getPlayer(args[1]);

            if (target == null) {
                sender.sendMessage(Flight.pre + Flight.red + "Player not online!");
                return;
            }

            if (isCreative(target)) {
                sender.sendMessage(Flight.pre + Flight.red + "Can't edit flight for " + target.getName() + ", they are in creative mode!");
                return;
            }

            if (flyModeEnabled(target)) {
                sender.sendMessage(Flight.pre + Flight.red + "Flight already on for " + target.getName());
                return;
            }
            enableFly(sender, target);
        } else if (args.length > 2) {
            sender.sendMessage(Flight.pre + Flight.red + "Too many arguments!");
        } else {
            plugin.noPerms(sender);
        }
    }

    public void flightOffCommand(CommandSender sender, String[] args) {

        if (args.length == 1) {

            if (!(sender instanceof Player)) {
                sender.sendMessage(Flight.pre + Flight.red + "Console can't fly!");
                return;
            }

            Player player = (Player) sender;

            if (isCreative(player)) {
                player.sendMessage(Flight.pre + Flight.red + "Cannot change flight while in creative!");
                return;
            }

            if (!flyModeEnabled(player)) {
                player.sendMessage(Flight.pre + Flight.red + "Flight already off!");
                return;
            }

            disableFly(sender, player);
        } else if (args.length == 2 && sender.hasPermission("flight.off.other")) {

            Player target = plugin.getServer().getPlayer(args[1]);

            if (target == null) {
                sender.sendMessage(Flight.pre + Flight.red + "Player not online!");
                return;
            }

            if (isCreative(target)) {
                sender.sendMessage(Flight.pre + Flight.red + "Can't edit flight for " + target.getName() + ", they are in creative mode!");
                return;
            }

            if (!flyModeEnabled(target)) {
                sender.sendMessage(Flight.pre + Flight.red + "Flight already off for " + target.getName());
                return;
            }

            disableFly(sender, target);
        } else if (args.length > 2) {
            sender.sendMessage(Flight.pre + Flight.red + "Too many arguments!");
        } else {
            plugin.noPerms(sender);
        }
    }

    public void checkCommand(CommandSender sender, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage(Flight.pre + Flight.red + "Console can't fly!");
            return;
        }

        Player player = (Player) sender;

        if (args.length == 1) {

            if (isCreative(player)) {
                sender.sendMessage(Flight.pre + Flight.red + "You are in creative, of course mode is enabled!");
                return;
            }

            if (flyModeEnabled(player)) {
                sender.sendMessage(Flight.pre + Flight.green + "Your flight is enabled!");
            } else {
                sender.sendMessage(Flight.pre + Flight.red + "Your flight is disabled!");
            }
        } else if (args.length == 2 && sender.hasPermission("flight.check.other")) {

            Player target = plugin.getServer().getPlayer(args[1]);

            if (target == null) {
                sender.sendMessage(Flight.pre + Flight.red + "Player not online!");
                return;
            }

            if (isCreative(target)) {
                sender.sendMessage(Flight.pre + Flight.red + target.getName() + " is in creative, of course flight is enabled!");
                return;
            }

            if (flyModeEnabled(target)) {
                sender.sendMessage(Flight.pre + Flight.green + target.getName() + " has flight enabled!");
            } else {
                sender.sendMessage(Flight.pre + Flight.red + target.getName() + " has flight disabled!");
            }
        } else if (args.length > 2) {
            sender.sendMessage(Flight.pre + Flight.red + "Too many arguments!");
        } else {
            plugin.noPerms(sender);
        }
    }

    public void listCommand(CommandSender sender, String[] args) {

        if (args.length != 1) {
            sender.sendMessage(Flight.pre + Flight.red + "Too many arguments!");
            return;
        }

        StringBuilder sb = new StringBuilder();
        List<String> list = new ArrayList<String>();

        for (Player p : Bukkit.getOnlinePlayers()) {
            String pName = "";
            if (flyModeEnabled(p) && !isCreative(p)) {
                pName = p.getName();
                list.add(pName);
            }
        }
        Collections.sort(list, String.CASE_INSENSITIVE_ORDER);
        for (String pName : list) {
            if (sb.length() > 0) {
                sb.append(Flight.white + ", ");
            }
            sb.append(pName);
        }
        sender.sendMessage(Flight.pre + Flight.gray + "Flight Mode Enabled: " + list);
    }

    public void enableFly(CommandSender sender, Player player) {
        player.setAllowFlight(true);
        if (!plugin.getFlyers().contains(player.getName())) {
            plugin.getFlyers().add(player.getName());
        }
        if (sender.getName().equalsIgnoreCase(player.getName())) {
            player.sendMessage(Flight.pre + Flight.green + "Flight enabled!");
        } else {
            player.sendMessage(Flight.pre + Flight.green + "Flight enabled by " + sender.getName());
            sender.sendMessage(Flight.pre + Flight.green + "Flight enabled for " + player.getName());
        }
    }

    public void disableFly(CommandSender sender, Player player) {
        player.setAllowFlight(false);
        player.setFlying(false);
        if (plugin.getFlyers().contains(player.getName())) {
            plugin.getFlyers().remove(player.getName());
        }
        if (sender.getName().equalsIgnoreCase(player.getName())) {
            player.sendMessage(Flight.pre + Flight.red + "Flight disabled!");
        } else {
            player.sendMessage(Flight.pre + Flight.red + "Flight disabled by " + sender.getName());
            sender.sendMessage(Flight.pre + Flight.red + "Flight disabled for " + player.getName());
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