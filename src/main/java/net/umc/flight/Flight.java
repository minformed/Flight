package net.umc.flight;

import java.util.ArrayList;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public class Flight extends JavaPlugin {

    public boolean useGod;
    public PluginDescriptionFile PDF;
    public ArrayList<String> flyers = new ArrayList<String>();
    public final FlightCommands cHandler = new FlightCommands(this);
    // Chat
    public final ChatColor red = ChatColor.RED;
    public final ChatColor green = ChatColor.GREEN;
    public final ChatColor white = ChatColor.WHITE;
    public final ChatColor yellow = ChatColor.YELLOW;
    public final static String pre = "§7[§aFlight§7] ";
    
    @Override
    public void onEnable() {
        setupConfig();
        PDF = this.getDescription();
        getCommand("flight").setExecutor(this);
    }
    
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (sender instanceof Player) {
            sender = (Player) sender;
        }

        if (args.length == 0) {
            if(!sender.hasPermission("flight.toggle")) {
                noPerms(sender);
                return true;
            }
            this.cHandler.quickToggle(sender);
            return true;
        }
        
        // Version Command
        if ("version".equalsIgnoreCase(args[0])) {
            if (!sender.hasPermission("flight.version")) {
                noPerms(sender);
                return true;
            }
            this.cHandler.versionCommand(sender, args);
            return true;
        }

        // Help Command
        if ("help".equalsIgnoreCase(args[0])) {
            if (!sender.hasPermission("flight.help")) {
                noPerms(sender);
                return true;
            }
            this.cHandler.helpCommand(sender, label, args);
            return true;
        }

        // Toggle Command
        if ("toggle".equalsIgnoreCase(args[0])) {
            if (!sender.hasPermission("flight.toggle")) {
                noPerms(sender);
                return true;
            }
            this.cHandler.toggleCommand(sender, args);
            return true;
        }

        // On Command
        if ("on".equalsIgnoreCase(args[0])) {
            if (!sender.hasPermission("flight.on")) {
                noPerms(sender);
                return true;
            }
            this.cHandler.flyOn(sender, args);
            return true;
        }

        // Off Command
        if ("off".equalsIgnoreCase(args[0])) {
            if (!sender.hasPermission("flight.off")) {
                noPerms(sender);
                return true;
            }
            this.cHandler.flyOff(sender, args);
            return true;
        }

        // Check Command
        if ("check".equalsIgnoreCase(args[0])) {
            if (!sender.hasPermission("flight.check")) {
                noPerms(sender);
                return true;
            }
            this.cHandler.checkCommand(sender, args);
            return true;
        }

        // List Command
        if ("list".equalsIgnoreCase(args[0])) {
            if (!sender.hasPermission("flight.list")) {
                noPerms(sender);
                return true;
            }
            this.cHandler.listCommand(sender, args);
            return true;
        }

        return false;
    }
    
    public void setupConfig() {
        this.getConfig().options().copyDefaults(true);
        useGod = this.getConfig().getBoolean("use-god");
        this.saveConfig();
    }
    
    public void noPerms(CommandSender sender) {
        sender.sendMessage(pre + red + "You do not have permission for that command...");
    }
}