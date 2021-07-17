package de.notjansel.wolfkarstplugin.commands;

import de.notjansel.wolfkarstplugin.Main;
import dev.jcsoftware.jscoreboards.JGlobalScoreboard;
import dev.jcsoftware.jscoreboards.JScoreboardTeam;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class OfflineCommand implements CommandExecutor {

    private JScoreboardTeam team;
    private JGlobalScoreboard scoreboard;

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        scoreboard = Main.getInstance().getScoreBoard();

        team = scoreboard.getTeams().get(0);

        team.removePlayer((Player) sender);

        sender.sendMessage("Du bist nun nicht mehr im Live-Team!");


        return true;
    }
}
