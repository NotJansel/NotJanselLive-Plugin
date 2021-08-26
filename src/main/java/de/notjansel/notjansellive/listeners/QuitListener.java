package de.notjansel.notjansellive.listeners;

import de.notjansel.notjansellive.Main;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class QuitListener implements Listener {
    @EventHandler
    public void onQuit(PlayerQuitEvent event){
        Player player = event.getPlayer();
        String orig = Main.getInstance().getConfig().getString("plugin.quit-message");
        String quitmessage = String.format(orig, player.getName());
        if (Main.getInstance().getConfig().getBoolean("plugin.join-quit-enabled")) {
            event.setQuitMessage(ChatColor.translateAlternateColorCodes('&', quitmessage));
        }
    }
}
