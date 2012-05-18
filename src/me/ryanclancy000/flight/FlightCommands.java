package me.ryanclancy000.flight;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FlightCommands {

    String pre = ChatColor.YELLOW + "[Flight] ";
    ChatColor yellow = ChatColor.YELLOW;
    ChatColor green = ChatColor.GREEN;
    ChatColor white = ChatColor.WHITE;
    ChatColor red = ChatColor.RED;
    public Flight plugin;

    public FlightCommands(Flight instance) {
        this.plugin = instance;
    }

    // Help Command
    public void helpCommand(CommandSender sender, String[] args) {

        if (args.length > 1) {
            sender.sendMessage(pre + red + "Too many arguments!");
            return;
        }

        sender.sendMessage(pre + green + "Help Menu!");
        sender.sendMessage(yellow + "/flight " + green + "list - " + yellow + "List players who have flight enabled.");
        sender.sendMessage(yellow + "/flight " + green + "toggle [player] - " + yellow + "Toggles flight.");
        sender.sendMessage(yellow + "/flight " + green + "check [player] - " + yellow + "Checks flight status.");
        sender.sendMessage(yellow + "/flight " + green + "on [player] - " + yellow + "Enable flight.");
        sender.sendMessage(yellow + "/flight " + green + "off [player] - " + yellow + "Disabled flight.");

    }

    // Toggle Command
    public void toggleCommand(CommandSender sender, String[] args) {

        if (args.length == 1) {

            if (!(sender instanceof Player)) {
                sender.sendMessage(pre + red + "Console can't fly!");
                return;
            }

            Player player = (Player) sender;

            if (isCreative(player)) {
                player.sendMessage(pre + red + "Cannot change flight while in creative!");
                return;
            }

            if (player.getAllowFlight()) {
                disableFly(sender, player);
                return;
            } else {
                enableFly(sender, player);
                return;
            }

        }

        if (args.length == 2 && sender.hasPermission("flight.toggle.other")) {

            Player target = Bukkit.getServer().getPlayer(args[1]);

            try {

                if (isCreative(target)) {
                    sender.sendMessage(pre + red + "Can't edit flight for " + target.getName() + ", they are in creative mode!");
                    return;
                }

                if (target.getAllowFlight()) {
                    disableFly(sender, target);
                    return;
                } else {
                    enableFly(sender, target);
                    return;
                }

            } catch (Exception e) {
                sender.sendMessage(pre + red + "Player not online!");
                return;
            }

        }

        if (args.length > 2) {
            sender.sendMessage(pre + red + "Too many arguments!");
        }

        sender.sendMessage(red + "You do not have permission to do that...");

    }

    public void flyOn(CommandSender sender, String[] args) {

        if (args.length == 1) {

            if (!(sender instanceof Player)) {
                sender.sendMessage(pre + red + "Console can't fly!");
                return;
            }

            Player player = (Player) sender;

            if (isCreative(player)) {
                player.sendMessage(pre + red + "Cannot change flight while in creative!");
                return;
            }

            if (flyModeEnabled(player)) {
                player.sendMessage(pre + red + "Flight already on!");
                return;
            }

            enableFly(sender, player);
            return;

        }

        if (args.length == 2 && sender.hasPermission("flight.on.other")) {

            Player target = Bukkit.getServer().getPlayer(args[1]);

            try {
                if (isCreative(target)) {
                    sender.sendMessage(pre + red + "Can't edit flight for " + target.getName() + ", they are in creative mode!");
                    return;
                }

                if (flyModeEnabled(target)) {
                    sender.sendMessage(pre + red + "Flight already on for " + target.getName());
                    return;
                }

                enableFly(sender, target);
                return;

            } catch (Exception e) {
                sender.sendMessage(pre + red + "Player not online!");
                return;
            }
        }

        if (args.length > 2) {
            sender.sendMessage(pre + red + "Too many arguments!");
        }

        sender.sendMessage(red + "You do not have permission to do that...");

    }

    public void flyOff(CommandSender sender, String[] args) {

        if (args.length == 1) {

            if (!(sender instanceof Player)) {
                sender.sendMessage(pre + red + "Console can't fly!");
                return;
            }

            Player player = (Player) sender;

            if (isCreative(player)) {
                player.sendMessage(pre + red + "Cannot change flight while in creative!");
                return;
            }

            if (!flyModeEnabled(player)) {
                player.sendMessage(pre + red + "Flight already off!");
                return;
            }

            disableFly(sender, player);
            return;

        }

        if (args.length == 2 && sender.hasPermission("flight.off.other")) {

            Player target = Bukkit.getServer().getPlayer(args[1]);

            try {

                if (isCreative(target)) {
                    sender.sendMessage(pre + red + "Can't edit flight for " + target.getName() + ", they are in creative mode!");
                    return;
                }

                if (!flyModeEnabled(target)) {
                    sender.sendMessage(pre + red + "Flight already off for " + target.getName());
                    return;
                }

                disableFly(sender, target);
                return;

            } catch (Exception e) {
                sender.sendMessage(pre + red + "Player not online!");
                return;
            }
        }

        if (args.length > 2) {
            sender.sendMessage(pre + red + "Too many arguments!");
        }

        sender.sendMessage(red + "You do not have permission to do that...");

    }

    public void checkCommand(CommandSender sender, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage(pre + red + "Console can't fly!");
            return;
        }

        Player player = (Player) sender;

        if (args.length == 1) {

            if (isCreative(player)) {
                sender.sendMessage(pre + red + "You are in creative, of course mode is enabled!");
                return;
            }

            if (flyModeEnabled(player)) {
                sender.sendMessage(pre + green + "Your flight is enabled!");
                return;
            }

            sender.sendMessage(pre + red + "Your flight is disabled!");
            return;

        }

        if (args.length == 2 && sender.hasPermission("flight.check.other")) {

            Player target = Bukkit.getServer().getPlayer(args[1]);

            try {

                if (isCreative(target)) {
                    sender.sendMessage(pre + red + target.getName() + " is in creative, of course flight is enabled!");
                    return;
                }

                if (flyModeEnabled(target)) {
                    sender.sendMessage(pre + green + target.getName() + " has flight enabled!");
                    return;
                } else {
                    sender.sendMessage(pre + red + target.getName() + " has flight disabled!");
                    return;
                }

            } catch (Exception e) {
                sender.sendMessage(pre + red + "Player not online!");
                return;
            }

        }

        if (args.length > 2) {
            sender.sendMessage(pre + red + "Too many arguments!");
        }

        sender.sendMessage(red + "You do not have permission to do that...");

    }

    public void listCommand(CommandSender sender, String[] args) {

        StringBuilder s = new StringBuilder();

        if (args.length != 1) {
            sender.sendMessage(pre + red + "Too many arguments!");
            return;
        }

        for (Player p : Bukkit.getOnlinePlayers()) {

            if (!flyModeEnabled(p)) {
                return;
            }

            if (s.length() > 0) {
                s.append(white + ", ");
            }

            if (p.getAllowFlight()) {
                s.append(green + p.getName());
            }
        }
        sender.sendMessage(pre + green + "Flight Mode Enabled: " + s);

    }

    public void enableFly(CommandSender sender, Player player) {
        player.setAllowFlight(true);
        player.setFlying(true);
        plugin.flyers.add(player);
        if (sender.getName().equalsIgnoreCase(player.getName())) {
            player.sendMessage(pre + green + "Flight enabled!");
        } else {
            player.sendMessage(pre + green + "Flight enabled by " + sender.getName());
            sender.sendMessage(pre + green + "Flight enabled for " + player.getName());
        }
    }

    public void disableFly(CommandSender sender, Player player) {
        player.setAllowFlight(false);
        player.setFlying(false);
        plugin.flyers.remove(player);
        if (sender.getName().equalsIgnoreCase(player.getName())) {
            player.sendMessage(pre + red + "Flight disabled!");
        } else {
            player.sendMessage(pre + red + "Flight disabled by " + sender.getName());
            sender.sendMessage(pre + red + "Flight disabled for " + player.getName());
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
