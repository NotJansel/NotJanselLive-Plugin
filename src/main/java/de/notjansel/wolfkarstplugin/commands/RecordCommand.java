package de.notjansel.wolfkarstplugin.commands;

import de.notjansel.wolfkarstplugin.Main;
import dev.jcsoftware.jscoreboards.JGlobalScoreboard;
import dev.jcsoftware.jscoreboards.JScoreboardTeam;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class RecordCommand implements CommandExecutor {
    private JScoreboardTeam team;
    private JGlobalScoreboard scoreboard;

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        scoreboard = Main.getInstance().getScoreBoard();

        Bukkit.getOnlinePlayers().forEach(this::addToScoreboard);

        team = scoreboard.getTeams().get(1);

        if(team==null){
            team = scoreboard.createTeam("record", "[Record] ", ChatColor.DARK_RED);
        }

        team.addPlayer((Player) sender);



        sender.sendMessage(ChatColor.DARK_PURPLE + "Du bist nun im Aufnahme-Team!");


        return true;
    }

    private void addToScoreboard(Player player) {
        scoreboard.addPlayer(player);
        scoreboard.updateScoreboard();
    }
}
