package de.notjansel.notjansellive.commands;

import de.notjansel.notjansellive.Main;
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
    private JGlobalScoreboard scoreboard = Main.getInstance().getScoreBoard();

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        //Update Scoreboard
        Bukkit.getOnlinePlayers().forEach(this::addToScoreboard);
        //Get Teams (and create them if isnt already)
        team = scoreboard.getTeams().get(1);
        if(team==null){
            team = scoreboard.createTeam("record", "[Record] ", ChatColor.DARK_RED);
        }
        //Add Players to the Team
        team.addPlayer((Player) sender);
        sender.sendMessage(ChatColor.DARK_RED + "You joined the 'Record'-Team!");
        return true;
    }

    //Add Players and update the scoreboard
    private void addToScoreboard(Player player) {
        scoreboard.addPlayer(player);
        scoreboard.updateScoreboard();
    }
}
