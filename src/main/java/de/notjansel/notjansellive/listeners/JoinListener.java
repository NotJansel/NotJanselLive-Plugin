package de.notjansel.notjansellive.listeners;

import de.notjansel.notjansellive.Main;
import dev.jcsoftware.jscoreboards.JGlobalScoreboard;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinListener implements Listener {
    private JGlobalScoreboard scoreboard = Main.getInstance().getScoreBoard();

    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();
        String orig = Main.getInstance().getConfig().getString("plugin.join-message");
        String joinmessage = String.format(orig, player.getName());
        if (Main.getInstance().getConfig().getBoolean("plugin.join-quit-enabled")) {
            event.setJoinMessage(ChatColor.translateAlternateColorCodes('&', joinmessage));
        }
        Bukkit.getOnlinePlayers().forEach(this::addToScoreboard);
        player.setScoreboard(Bukkit.getScoreboardManager().getMainScoreboard());
    }


    private void addToScoreboard(Player player) {
        scoreboard.addPlayer(player);
        scoreboard.updateScoreboard();
    }
}
