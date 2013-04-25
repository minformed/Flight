package com.ryanclancy000.flight;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FlightCommands {

    private final Flight plugin;

    public FlightCommands(final Flight instance) {
        plugin = instance;
    }

    public void versionCommand(final CommandSender sender) {
        sender.sendMessage(Flight.pre + ChatColor.GREEN + "v" + plugin.getDescription().getVersion() + ChatColor.GRAY + " by " + ChatColor.GREEN + "ryanclancy000");
    }

    public void helpCommand(final CommandSender sender, final String label) {
        sender.sendMessage(ChatColor.WHITE + "--------------------- " + ChatColor.GRAY + "[" + ChatColor.GREEN + " Flight " + ChatColor.GRAY + "]" + ChatColor.WHITE + " ---------------------");
        if (sender.hasPermission("flight.on"))
            sender.sendMessage(ChatColor.GREEN + "/" + label + " on [player]" + ChatColor.WHITE + " - " + ChatColor.GRAY + "Enable flight.");
        if (sender.hasPermission("flight.off"))
            sender.sendMessage(ChatColor.GREEN + "/" + label + " off [player]" + ChatColor.WHITE + " - " + ChatColor.GRAY + "Disable flight.");
        if (sender.hasPermission("flight.toggle"))
            sender.sendMessage(ChatColor.GREEN + "/" + label + " toggle [player]" + ChatColor.WHITE + " - " + ChatColor.GRAY + "Toggle flight.");
        if (sender.hasPermission("flight.list"))
            sender.sendMessage(ChatColor.GREEN + "/" + label + " list" + ChatColor.WHITE + " - " + ChatColor.GRAY + "List flying players flight.");
        if (sender.hasPermission("flight.check"))
            sender.sendMessage(ChatColor.GREEN + "/" + label + " check [player]" + ChatColor.WHITE + " - " + ChatColor.GRAY + "Check flight status.");
        if (sender.hasPermission("flight.version"))
            sender.sendMessage(ChatColor.GREEN + "/" + label + " version" + ChatColor.WHITE + " - " + ChatColor.GRAY + "Give plugin info.");
    }

    public void toggleCommand(final CommandSender sender, final String[] args) {

        if (args.length == 0) {
            if (sender instanceof Player) {
                final Player player = (Player) sender;
                if (!isCreative(player)) {
                    if (player.getAllowFlight()) {
                        disableFlight(sender, player);
                    } else {
                        enableFlight(sender, player);
                    }
                } else {
                    player.sendMessage(Flight.pre + ChatColor.RED + "Cannot change flight while in creative!");
                }
            } else {
                sender.sendMessage(Flight.pre + ChatColor.RED + "Console can't fly!");
            }
        } else if (args.length == 1) {
            if (sender instanceof Player) {
                final Player player = (Player) sender;
                if (!isCreative(player)) {
                    if (player.getAllowFlight()) {
                        disableFlight(sender, player);
                    } else {
                        enableFlight(sender, player);
                    }
                } else {
                    player.sendMessage(Flight.pre + ChatColor.RED + "Cannot change flight while in creative!");
                }
            } else {
                sender.sendMessage(Flight.pre + ChatColor.RED + "Console can't fly!");
            }
        } else if (args.length == 2) {
            if (sender.hasPermission("flight.toggle.other")) {
                final Player target = plugin.getServer().getPlayer(args[1]);
                if (target != null) {
                    if (!isCreative(target)) {
                        if (target.getAllowFlight()) {
                            disableFlight(sender, target);
                        } else {
                            enableFlight(sender, target);
                        }
                    } else {
                        sender.sendMessage(Flight.pre + ChatColor.RED + "Can't edit flight for " + target.getName() + ", they are in creative mode!");
                    }
                } else {
                    sender.sendMessage(Flight.pre + ChatColor.RED + "Player not online!");
                }
            } else {
                sender.sendMessage(Flight.noPerms);
            }
        } else {
            sender.sendMessage(Flight.pre + ChatColor.RED + "Too many arguments!");
        }
    }

    public void flightOnCommand(final CommandSender sender, final String[] args) {

        if (args.length == 1) {
            if (sender instanceof Player) {
                final Player player = (Player) sender;
                if (!isCreative(player)) {
                    if (!player.getAllowFlight()) {
                        enableFlight(sender, player);
                    } else {
                        player.sendMessage(Flight.pre + ChatColor.RED + "Flight already on!");
                    }
                } else {
                    sender.sendMessage(Flight.pre + ChatColor.RED + "Cannot change flight while in creative!");
                }
            } else {
                sender.sendMessage(Flight.pre + ChatColor.RED + "Console can't fly!");
            }
        } else if (args.length == 2) {
            if (sender.hasPermission("flight.on.other")) {
                final Player target = plugin.getServer().getPlayer(args[1]);
                if (target != null) {
                    if (!isCreative(target)) {
                        if (!target.getAllowFlight()) {
                            enableFlight(sender, target);
                        } else {
                            sender.sendMessage(Flight.pre + ChatColor.RED + "Flight already on for " + target.getName());
                        }
                    } else {
                        sender.sendMessage(Flight.pre + ChatColor.RED + "Can't edit flight for " + target.getName() + ", they are in creative mode!");
                    }
                } else {
                    sender.sendMessage(Flight.pre + ChatColor.RED + "Player not online!");
                }
            } else {
                sender.sendMessage(Flight.noPerms);
            }
        } else {
            sender.sendMessage(Flight.pre + ChatColor.RED + "Too many arguments!");
        }
    }

    public void flightOffCommand(final CommandSender sender, final String[] args) {

        if (args.length == 1) {
            if (sender instanceof Player) {
                final Player player = (Player) sender;
                if (!isCreative(player)) {
                    if (player.getAllowFlight()) {
                        disableFlight(sender, player);
                    } else {
                        player.sendMessage(Flight.pre + ChatColor.RED + "Flight already off!");
                    }
                } else {
                    sender.sendMessage(Flight.pre + ChatColor.RED + "Cannot change flight while in creative!");
                }
            } else {
                sender.sendMessage(Flight.pre + ChatColor.RED + "Console can't fly!");
            }
        } else if (args.length == 2) {
            if (sender.hasPermission("flight.off.other")) {
                final Player target = plugin.getServer().getPlayer(args[1]);
                if (target != null) {
                    if (!isCreative(target)) {
                        if (target.getAllowFlight()) {
                            disableFlight(sender, target);
                        } else {
                            sender.sendMessage(Flight.pre + ChatColor.RED + "Flight already off for " + target.getName());
                        }
                    } else {
                        sender.sendMessage(Flight.pre + ChatColor.RED + "Can't edit flight for " + target.getName() + ", they are in creative mode!");
                    }
                } else {
                    sender.sendMessage(Flight.pre + ChatColor.RED + "Player not online!");
                }
            } else {
                sender.sendMessage(Flight.noPerms);
            }
        } else {
            sender.sendMessage(Flight.pre + ChatColor.RED + "Too many arguments!");
        }
    }

    public void checkCommand(final CommandSender sender, final String[] args) {

        if (args.length == 1) {
            if (sender instanceof Player) {
                final Player player = (Player) sender;
                if (!isCreative(player)) {
                    if (player.getAllowFlight()) {
                        sender.sendMessage(Flight.pre + ChatColor.GREEN + "Your flight is enabled!");
                    } else {
                        sender.sendMessage(Flight.pre + ChatColor.RED + "Your flight is disabled!");
                    }
                } else {
                    sender.sendMessage(Flight.pre + ChatColor.RED + "You are in creative, of course mode is enabled!");
                }
            } else {
                sender.sendMessage(Flight.pre + ChatColor.RED + "Console can't fly!");
            }
        } else if (args.length == 2) {
            if (sender.hasPermission("flight.check.other")) {
                final Player target = plugin.getServer().getPlayer(args[1]);
                if (target != null) {
                    if (!isCreative(target)) {
                        if (target.getAllowFlight()) {
                            sender.sendMessage(Flight.pre + ChatColor.GREEN + target.getName() + " has flight enabled!");
                        } else {
                            sender.sendMessage(Flight.pre + ChatColor.RED + target.getName() + " has flight disabled!");
                        }
                    } else {
                        sender.sendMessage(Flight.pre + ChatColor.RED + target.getName() + " is in creative, of course flight is enabled!");
                    }
                } else {
                    sender.sendMessage(Flight.pre + ChatColor.RED + "Player not online!");
                }
            } else {
                sender.sendMessage(Flight.noPerms);
            }
        } else {
            sender.sendMessage(Flight.pre + ChatColor.RED + "Too many arguments!");
        }
    }

    public void listCommand(final CommandSender sender) {

        final StringBuilder sb = new StringBuilder();
        final List<String> list = new ArrayList<>();

        for (final Player p : plugin.getServer().getOnlinePlayers()) {
            if (p.getAllowFlight() && !isCreative(p)) {
                list.add(p.getName());
            }
        }

        Collections.sort(list, String.CASE_INSENSITIVE_ORDER);

        for (final String pName : list) {
            sb.append(pName + " ");
        }

        sender.sendMessage(Flight.pre + ChatColor.GRAY + "Flight Mode Enabled: " + sb.toString());
    }

    private boolean isCreative(final Player player) {
        if (player.getGameMode() == GameMode.CREATIVE) {
            return true;
        } else {
            return false;
        }
    }

    private void enableFlight(final CommandSender sender, final Player player) {
        player.setAllowFlight(true);
        if (sender.getName().equalsIgnoreCase(player.getName())) {
            player.sendMessage(Flight.pre + ChatColor.GREEN + "Flight enabled!");
        } else {
            player.sendMessage(Flight.pre + ChatColor.GREEN + "Flight enabled by " + sender.getName());
            sender.sendMessage(Flight.pre + ChatColor.GREEN + "Flight enabled for " + player.getName());
        }
    }

    private void disableFlight(final CommandSender sender, final Player player) {
        player.setAllowFlight(false);
        player.setFlying(false);
        if (sender.getName().equalsIgnoreCase(player.getName())) {
            player.sendMessage(Flight.pre + ChatColor.RED + "Flight disabled!");
        } else {
            player.sendMessage(Flight.pre + ChatColor.RED + "Flight disabled by " + sender.getName());
            sender.sendMessage(Flight.pre + ChatColor.RED + "Flight disabled for " + player.getName());
        }
    }
}