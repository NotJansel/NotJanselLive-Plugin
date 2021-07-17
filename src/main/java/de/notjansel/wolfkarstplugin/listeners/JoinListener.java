package de.notjansel.wolfkarstplugin.listeners;

import de.notjansel.wolfkarstplugin.Main;
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
        event.setJoinMessage(">>> " + ChatColor.AQUA + player.getName() + " hat den Server betreten");
        Bukkit.getConsoleSender().sendMessage(">>> " + player.getName() + " hat den Server betreten");
        Bukkit.getOnlinePlayers().forEach(this::addToScoreboard);
        player.setScoreboard(Bukkit.getScoreboardManager().getMainScoreboard());
    }

    private void addToScoreboard(Player player) {
        scoreboard.addPlayer(player);
        scoreboard.updateScoreboard();
    }
}
