package me.ryanclancy000.flight;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class Flight extends JavaPlugin {
    
    String pre = ChatColor.GOLD + "[Flight] ";
    ChatColor green = ChatColor.GREEN;
    
    @Override
    public void onDisable() {
    }
    
    @Override
    public void onEnable() {
        getCommand("fly").setExecutor(this);
    }
    
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        
        Player player = (Player) sender;
        
        if (args.length == 0) {
            player.setFlying(true);
            sender.sendMessage(pre + green + "Flying enabled!");
            return true;
        }
        
        return true;
    }

}
