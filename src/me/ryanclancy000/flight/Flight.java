package me.ryanclancy000.flight;

import java.io.IOException;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class Flight extends JavaPlugin {

    private FlightCommands cHandler = new FlightCommands(this);

    @Override
    public void onDisable() {
    }

    @Override
    public void onEnable() {
        getCommand("flight").setExecutor(this);

        try {
            Metrics metrics = new Metrics(this);
            metrics.start();
        } catch (IOException e) {
            this.getLogger().severe("Could not enable Metrics tracking!");
        }

    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if (cmd.getName().equalsIgnoreCase("flight")) {
            return doCommand(sender, args);
        }

        return onCommand(sender, cmd, commandLabel, args);
    }

    private boolean doCommand(CommandSender sender, String[] args) {

        Player player = (Player) sender;

        if (args.length == 0) {
        }

        // Help Command
        
        if ("help".equalsIgnoreCase(args[0])) {

            isCreative(player);

            if (!sender.hasPermission("flight.help")) {
                noPerms(sender);
                return true;
            }

            this.cHandler.helpCommand(sender, args);
            return true;

        }

        // Toggle Command
        
        if ("toggle".equalsIgnoreCase(args[0])) {

            isCreative(player);

            if (!sender.hasPermission("flight.toggle")) {
                noPerms(sender);
                return true;
            }

            this.cHandler.toggleCommand(sender, args);
            return true;
        }

        // Check Command
        
        if ("check".equalsIgnoreCase(args[0])) {

            isCreative(player);

            if (!sender.hasPermission("flight.check")) {
                noPerms(sender);
                return true;
            }

            this.cHandler.checkCommand(sender, args);
            return true;
        }

        return false;
    }

    public void noPerms(CommandSender sender) {
        sender.sendMessage(ChatColor.RED + "You do not have permission for that command...");
    }

    public boolean isCreative(Player player) {

        if (player.getGameMode() == GameMode.CREATIVE) {
            return true;
        } else {
            return false;
        }
    }
}