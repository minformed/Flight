package net.umc.flight;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class Flight extends JavaPlugin {

    private List<String> flyingPlayers = new ArrayList<String>();
    private final FlightCommands cHandler = new FlightCommands(this);
    private final FlightListener listener = new FlightListener(this);
    //
    public static final ChatColor red = ChatColor.RED;
    public static final ChatColor gray = ChatColor.GRAY;
    public static final ChatColor green = ChatColor.GREEN;
    public static final ChatColor white = ChatColor.WHITE;
    public static final ChatColor yellow = ChatColor.YELLOW;
    public static final String pre = gray + "[" + green + "Flight" + gray + "] ";

    @Override
    public void onEnable() {
        this.getCommand("flight").setExecutor(this);
        this.getServer().getPluginManager().registerEvents(listener, this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (sender instanceof Player) {
            sender = (Player) sender;
        }

        if (args.length == 0) {
            if (sender.hasPermission("flight.toggle")) {
                this.cHandler.quickToggle(sender);
            } else {
                noPerms(sender);
            }
        } else if ("version".equalsIgnoreCase(args[0])) {
            if (sender.hasPermission("flight.version")) {
                this.cHandler.versionCommand(sender, args);
            } else {
                noPerms(sender);
            }
        } else if ("help".equalsIgnoreCase(args[0])) {
            if (sender.hasPermission("flight.help")) {
                this.cHandler.helpCommand(sender, label, args);
            } else {
                noPerms(sender);
            }
        } else if ("toggle".equalsIgnoreCase(args[0])) {
            if (sender.hasPermission("flight.toggle")) {
                this.cHandler.toggleCommand(sender, args);
            } else {
                noPerms(sender);
            }
        } else if ("on".equalsIgnoreCase(args[0])) {
            if (sender.hasPermission("flight.on")) {
                this.cHandler.flyOn(sender, args);
            } else {
                noPerms(sender);
            }
        } else if ("off".equalsIgnoreCase(args[0])) {
            if (sender.hasPermission("flight.off")) {
                this.cHandler.flyOff(sender, args);
            } else {
                noPerms(sender);
            }
        } else if ("check".equalsIgnoreCase(args[0])) {
            if (sender.hasPermission("flight.check")) {
                this.cHandler.checkCommand(sender, args);
            } else {
                noPerms(sender);
            }
        } else if ("list".equalsIgnoreCase(args[0])) {
            if (sender.hasPermission("flight.list")) {
                this.cHandler.listCommand(sender, args);
            } else {
                noPerms(sender);
            }
        } else {
            this.cHandler.helpCommand(sender, label, args);
        }
        
        return true;
    }

    private void noPerms(CommandSender sender) {
        sender.sendMessage(pre + red + "You do not have permission for that command...");
    }

    /* Get list of players with flight enable */
    public List<String> getFlyers() {
        return flyingPlayers;
    }
}