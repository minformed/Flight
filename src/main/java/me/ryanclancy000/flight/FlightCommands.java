package me.ryanclancy000.flight;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FlightCommands {

    private final Flight plugin;

    public FlightCommands(Flight instance) {
        this.plugin = instance;
    }

    // Version Command
    public void versionCommand(CommandSender sender, String[] args) {
        sender.sendMessage(plugin.pre + plugin.green + "version " + plugin.PDF.getVersion() + " by ryanclancy000");
    }

    // Help Command
    public void helpCommand(CommandSender sender, String label, String[] args) {

        if (args.length > 1) {
            sender.sendMessage(plugin.pre + plugin.red + "Too many arguments!");
            return;
        }

        sender.sendMessage(plugin.pre + plugin.green + "Help Menu!");
        sender.sendMessage(plugin.yellow + "/" + label + " " + plugin.green + "on [player] - " + plugin.yellow + "Enable flight.");
        sender.sendMessage(plugin.yellow + "/" + label + " " + plugin.green + "off [player] - " + plugin.yellow + "Disabled flight.");
        sender.sendMessage(plugin.yellow + "/" + label + " " + plugin.green + "toggle [player] - " + plugin.yellow + "Toggles flight.");
        sender.sendMessage(plugin.yellow + "/" + label + " " + plugin.green + "list - " + plugin.yellow + "List players who have flight enabled.");
        sender.sendMessage(plugin.yellow + "/" + label + " " + plugin.green + "check [player] - " + plugin.yellow + "Checks flight status.");
        sender.sendMessage(plugin.yellow + "/" + label + " " + plugin.green + "version - " + plugin.yellow + "Give info on this plugin.");
    }

    // Quick Toggle Command
    public void quickToggle(CommandSender sender) {

        Player player = (Player) sender;

        if (isCreative(player)) {
            player.sendMessage(plugin.pre + plugin.red + "Cannot change flight while in creative!");
            return;
        }

        if (plugin.flyers.contains(player.getName())) {
            disableFly(sender, player);
        } else {
            enableFly(sender, player);
        }

    }

    // Toggle Command
    public void toggleCommand(CommandSender sender, String[] args) {

        if (args.length == 1) {

            if (!(sender instanceof Player)) {
                sender.sendMessage(plugin.pre + plugin.red + "Console can't fly!");
                return;
            }

            Player player = (Player) sender;

            if (isCreative(player)) {
                player.sendMessage(plugin.pre + plugin.red + "Cannot change flight while in creative!");
                return;
            }

            if (plugin.flyers.contains(player.getName())) {
                disableFly(sender, player);
                return;
            } else {
                enableFly(sender, player);
                return;
            }

        }

        if (args.length == 2 && sender.hasPermission("flight.toggle.other")) {

            Player target = Bukkit.getServer().getPlayer(args[1]);

            if (target == null) {
                sender.sendMessage(plugin.pre + plugin.red + "Player not online!");
                return;
            }

            if (isCreative(target)) {
                sender.sendMessage(plugin.pre + plugin.red + "Can't edit flight for " + target.getName() + ", they are in creative mode!");
                return;
            }

            if (target.getAllowFlight()) {
                disableFly(sender, target);
                return;
            } else {
                enableFly(sender, target);
                return;
            }

        }

        if (args.length > 2) {
            sender.sendMessage(plugin.pre + plugin.red + "Too many arguments!");
            return;
        }

        sender.sendMessage(plugin.red + "You do not have permission to do that...");
    }

    public void flyOn(CommandSender sender, String[] args) {

        if (args.length == 1) {

            if (!(sender instanceof Player)) {
                sender.sendMessage(plugin.pre + plugin.red + "Console can't fly!");
                return;
            }

            Player player = (Player) sender;

            if (isCreative(player)) {
                player.sendMessage(plugin.pre + plugin.red + "Cannot change flight while in creative!");
                return;
            }

            if (flyModeEnabled(player)) {
                player.sendMessage(plugin.pre + plugin.red + "Flight already on!");
                return;
            }

            enableFly(sender, player);
            return;

        }

        if (args.length == 2 && sender.hasPermission("flight.on.other")) {

            Player target = Bukkit.getServer().getPlayer(args[1]);

            if (target == null) {
                sender.sendMessage(plugin.pre + plugin.red + "Player not online!");
                return;
            }

            if (isCreative(target)) {
                sender.sendMessage(plugin.pre + plugin.red + "Can't edit flight for " + target.getName() + ", they are in creative mode!");
                return;
            }

            if (flyModeEnabled(target)) {
                sender.sendMessage(plugin.pre + plugin.red + "Flight already on for " + target.getName());
                return;
            }

            enableFly(sender, target);
            return;
        }

        if (args.length > 2) {
            sender.sendMessage(plugin.pre + plugin.red + "Too many arguments!");
            return;
        }

        sender.sendMessage(plugin.red + "You do not have permission to do that...");
    }

    public void flyOff(CommandSender sender, String[] args) {

        if (args.length == 1) {

            if (!(sender instanceof Player)) {
                sender.sendMessage(plugin.pre + plugin.red + "Console can't fly!");
                return;
            }

            Player player = (Player) sender;

            if (isCreative(player)) {
                player.sendMessage(plugin.pre + plugin.red + "Cannot change flight while in creative!");
                return;
            }

            if (!flyModeEnabled(player)) {
                player.sendMessage(plugin.pre + plugin.red + "Flight already off!");
                return;
            }

            disableFly(sender, player);
            return;
        }

        if (args.length == 2 && sender.hasPermission("flight.off.other")) {

            Player target = Bukkit.getServer().getPlayer(args[1]);

            if (target == null) {
                sender.sendMessage(plugin.pre + plugin.red + "Player not online!");
                return;
            }

            if (isCreative(target)) {
                sender.sendMessage(plugin.pre + plugin.red + "Can't edit flight for " + target.getName() + ", they are in creative mode!");
                return;
            }

            if (!flyModeEnabled(target)) {
                sender.sendMessage(plugin.pre + plugin.red + "Flight already off for " + target.getName());
                return;
            }

            disableFly(sender, target);
            return;
        }

        if (args.length > 2) {
            sender.sendMessage(plugin.pre + plugin.red + "Too many arguments!");
            return;
        }

        sender.sendMessage(plugin.red + "You do not have permission to do that...");
    }

    public void checkCommand(CommandSender sender, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage(plugin.pre + plugin.red + "Console can't fly!");
            return;
        }

        Player player = (Player) sender;

        if (args.length == 1) {

            if (isCreative(player)) {
                sender.sendMessage(plugin.pre + plugin.red + "You are in creative, of course mode is enabled!");
                return;
            }

            if (flyModeEnabled(player)) {
                sender.sendMessage(plugin.pre + plugin.green + "Your flight is enabled!");
                return;
            } else {
                sender.sendMessage(plugin.pre + plugin.red + "Your flight is disabled!");
                return;
            }

        }

        if (args.length == 2 && sender.hasPermission("flight.check.other")) {

            Player target = Bukkit.getServer().getPlayer(args[1]);

            if (target == null) {
                sender.sendMessage(plugin.pre + plugin.red + "Player not online!");
                return;
            }

            if (isCreative(target)) {
                sender.sendMessage(plugin.pre + plugin.red + target.getName() + " is in creative, of course flight is enabled!");
                return;
            }

            if (flyModeEnabled(target)) {
                sender.sendMessage(plugin.pre + plugin.green + target.getName() + " has flight enabled!");
                return;
            } else {
                sender.sendMessage(plugin.pre + plugin.red + target.getName() + " has flight disabled!");
                return;
            }

        }

        if (args.length > 2) {
            sender.sendMessage(plugin.pre + plugin.red + "Too many arguments!");
            return;
        }

        sender.sendMessage(plugin.red + "You do not have permission to do that...");

    }

    public void listCommand(CommandSender sender, String[] args) {

        StringBuilder sb = new StringBuilder();

        if (args.length != 1) {
            sender.sendMessage(plugin.pre + plugin.red + "Too many arguments!");
            return;
        }

        for (String s : plugin.flyers) {

            if (s.length() > 0) {
                sb.append(plugin.white + ", ");
            }
            sb.append(plugin.green + s);
        }
        sender.sendMessage(plugin.pre + plugin.green + "Flight Mode Enabled: " + sb);

    }

    public void enableFly(CommandSender sender, Player player) {
        player.setAllowFlight(true);
        player.setFlying(true);
        plugin.flyers.add(player.getName());
        if (sender.getName().equalsIgnoreCase(player.getName())) {
            player.sendMessage(plugin.pre + plugin.green + "Flight enabled!");
        } else {
            player.sendMessage(plugin.pre + plugin.green + "Flight enabled by " + sender.getName());
            sender.sendMessage(plugin.pre + plugin.green + "Flight enabled for " + player.getName());
        }
    }

    public void disableFly(CommandSender sender, Player player) {
        player.setAllowFlight(false);
        player.setFlying(false);
        plugin.flyers.remove(player.getName());
        if (sender.getName().equalsIgnoreCase(player.getName())) {
            player.sendMessage(plugin.pre + plugin.red + "Flight disabled!");
        } else {
            player.sendMessage(plugin.pre + plugin.red + "Flight disabled by " + sender.getName());
            sender.sendMessage(plugin.pre + plugin.red + "Flight disabled for " + player.getName());
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