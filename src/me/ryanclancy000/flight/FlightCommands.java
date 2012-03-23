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
    ChatColor red = ChatColor.RED;
    public Flight plugin;

    public FlightCommands(Flight instance) {
        plugin = instance;
    }

    // Help Command
    public void helpCommand(CommandSender sender, String[] args) {

        if (args.length > 1) {
            sender.sendMessage(pre + red + "Too many arguments!");
            return;
        }

        sender.sendMessage(pre + green + "Help Menu!");
        sender.sendMessage(yellow + "/flight " + green + "toggle [player] - " + yellow + "Toggles flight.");
        sender.sendMessage(yellow + "/flight " + green + "check [player] - " + yellow + "Checks flight status.");

    }

    // Toggle Command
    public void toggleCommand(CommandSender sender, String[] args) {

        if (args.length == 1) {

            Player player = (Player) sender;

            if (isCreative(player)) {
                player.sendMessage(pre + ChatColor.RED + "Cannot change flight mode while in creative!");
                return;
            }

            if (player.getAllowFlight()) {
                disableFly(player);
                player.sendMessage(pre + red + "Flying disabled!");
                return;
            } else {
                enableFly(player);
                player.sendMessage(pre + green + "Flying enabled!");
                return;
            }

        }

        if (args.length == 2) {

            Player target = Bukkit.getServer().getPlayer(args[1]);

            try {

                if (isCreative(target)) {
                    sender.sendMessage(pre + red + "Can't edit flight mode for " + target.getName() + ", they are in creative mode!");
                    return;
                }

                if (target.getAllowFlight()) {
                    disableFly(target);

                    if (target.getName().equalsIgnoreCase(sender.getName())) {
                        sender.sendMessage(pre + red + "Flying disabled!");
                        return;
                    }

                    sender.sendMessage(pre + red + "Flying disabled for " + target.getName());
                    target.sendMessage(pre + red + "Flying disabled by " + sender.getName());
                    return;
                } else {
                    enableFly(target);

                    if (target.getName().equalsIgnoreCase(sender.getName())) {
                        sender.sendMessage(pre + green + "Flying enabled!");
                        return;
                    }

                    sender.sendMessage(pre + green + "Flying enabled for " + target.getName());
                    target.sendMessage(pre + green + "Flying enabled by " + sender.getName());
                    return;
                }

            } catch (Exception e) {
                sender.sendMessage(pre + red + "Player not online!");
                return;
            }

        }

        sender.sendMessage(pre + red + "Too many arguments");
        return;

    }

    public void flyOn(CommandSender sender, String[] args) {

        if (args.length == 1) {

            Player player = (Player) sender;

            if (isCreative(player)) {
                player.sendMessage(pre + ChatColor.RED + "Cannot change flight mode while in creative!");
                return;
            }

            if (flyModeEnabled(player)) {
                player.sendMessage(pre + red + "Fly mode already on!");
                return;
            }

            enableFly(player);
            sender.sendMessage(pre + green + "Flying enabled!");
            return;

        }

        if (args.length == 2) {

            Player target = Bukkit.getServer().getPlayer(args[1]);

            try {

                if (isCreative(target)) {
                    sender.sendMessage(pre + red + "Can't edit flight mode for " + target.getName() + ", they are in creative mode!");
                    return;
                }

                if (flyModeEnabled(target)) {
                    target.sendMessage(pre + red + "Fly mode already on!");
                    return;
                }

                enableFly(target);

                if (target.getName().equalsIgnoreCase(sender.getName())) {
                    sender.sendMessage(pre + green + "Flying enabled!");
                    return;
                }

                sender.sendMessage(pre + green + "Flying enabled for " + target.getName());
                target.sendMessage(pre + green + "Flying enabled by " + sender.getName());
                return;

            } catch (Exception e) {
                sender.sendMessage(pre + red + "Player not online!");
                return;
            }
        }
        
        sender.sendMessage(pre + red + "Too many arguments!");
        return;

    }

    public void flyOff(CommandSender sender, String[] args) {
        
        if (args.length == 1) {

            Player player = (Player) sender;

            if (isCreative(player)) {
                player.sendMessage(pre + ChatColor.RED + "Cannot change flight mode while in creative!");
                return;
            }

            if (!flyModeEnabled(player)) {
                player.sendMessage(pre + red + "Fly mode already off!");
                return;
            }

            disableFly(player);
            sender.sendMessage(pre + green + "Flying disabled!");
            return;

        }

        if (args.length == 2) {

            Player target = Bukkit.getServer().getPlayer(args[1]);

            try {

                if (isCreative(target)) {
                    sender.sendMessage(pre + red + "Can't edit flight mode for " + target.getName() + ", they are in creative mode!");
                    return;
                }

                if (!flyModeEnabled(target)) {
                    target.sendMessage(pre + red + "Fly mode already off!");
                    return;
                }

                disableFly(target);

                if (target.getName().equalsIgnoreCase(sender.getName())) {
                    sender.sendMessage(pre + green + "Flying disabled!");
                    return;
                }

                sender.sendMessage(pre + green + "Flying disabled for " + target.getName());
                target.sendMessage(pre + green + "Flying disabled by " + sender.getName());
                return;

            } catch (Exception e) {
                sender.sendMessage(pre + red + "Player not online!");
                return;
            }
        }
        
        sender.sendMessage(pre + red + "Too many arguments!");
        return;

        
    }

    public void checkCommand(CommandSender sender, String[] args) {

        Player player = (Player) sender;

        if (args.length == 1) {

            if (flyModeEnabled(player) && !currentlyFlying(player)) {
                sender.sendMessage(pre + green + "Fly mode is enabled, but you are not in the air!");
                return;
            }

            if (currentlyFlying(player)) {
                sender.sendMessage(pre + green + "You are currently in flight!");
                return;
            }

            sender.sendMessage(pre + green + "Fly mode is disabled!");
            return;

        }

        if (args.length == 2) {

            Player target = Bukkit.getServer().getPlayer(args[1]);

            try {
            } catch (Exception e) {
                sender.sendMessage(pre + red + "Player not online!");
                return;
            }

        }

        sender.sendMessage(pre + red + "Too many arguments!");
        return;

    }

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
