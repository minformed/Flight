package me.ryanclancy000.flight;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FlightCommands {

    String pre = ChatColor.GOLD + "[Flight] ";
    ChatColor green = ChatColor.GREEN;
    ChatColor red = ChatColor.RED;
    public Flight plugin;
    

    public FlightCommands(Flight instance) {
        plugin = instance;
    }

    public void helpCommand(CommandSender sender, String[] args) {
        
        if (args.length > 1) {
            sender.sendMessage(pre + red + "Too many arguments!");
            return;
        }
        
        sender.sendMessage(pre + green + "Help Menu!");
        sender.sendMessage(green + "/flight toggle");
    }

    public void toggleCommand(CommandSender sender, String[] args) {
        
        if (args.length == 1) {
            
            Player player = (Player) sender;
            
            if (plugin.isCreative(player)) {
                player.sendMessage(pre + ChatColor.RED + "Cannot change flight mode while in creative!");
                return;
            }
            
            if (player.getAllowFlight()) {
                disableFly(player);
                player.sendMessage(pre + red + "Flying disabled!");
            } else {
                enableFly(player);
                player.sendMessage(pre + green + "Flying enabled!");
            }
            
        }
        
    }
    
    public void checkCommand(CommandSender sender, String[] args) {
        
        Player player = (Player) sender;
        
        Player target = Bukkit.getServer().getPlayer(args[1]);
        
        if (args.length == 1) {
         
            if (flyModeEnabled(player)) {
                player.sendMessage(pre + green + "Fly mode enabled, but not currently in the air!");
                return;
            }
            
            if (currentlyFlying(player)) {
                player.sendMessage(pre + green + "You are currently in flight!");
            }
            
        }
                
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
}
