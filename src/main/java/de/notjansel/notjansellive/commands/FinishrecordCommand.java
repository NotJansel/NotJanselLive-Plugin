package de.notjansel.notjansellive.commands;

import de.notjansel.notjansellive.Main;
import dev.jcsoftware.jscoreboards.JGlobalScoreboard;
import dev.jcsoftware.jscoreboards.JScoreboardTeam;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class FinishrecordCommand implements CommandExecutor {
    private JScoreboardTeam team;
    private JGlobalScoreboard scoreboard = Main.getInstance().getScoreBoard();

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        //Get the Record Team
        team = scoreboard.getTeams().get(1);
        //Remove Player from the recording team
        team.removePlayer((Player) sender);
        sender.sendMessage("You left the 'Record'-Team!");
        return true;
    }
}
