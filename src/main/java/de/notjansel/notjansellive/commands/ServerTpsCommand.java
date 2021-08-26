package de.notjansel.notjansellive.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class ServerTpsCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        //Build the string for the TPS, so everyone can recieve the TPS-Count, and not just admins only
        //Thanks to the Bukkit Sourcecode for this
        StringBuilder sb = new StringBuilder(ChatColor.AQUA + "TPS the last: 1m, 5m, 15m: ");
        for (double tps : Bukkit.getTPS()) {
            sb.append(format(tps));
            sb.append(", ");
        }
        commandSender.sendMessage(sb.substring(0, sb.length() - 2));
        return true;
    }

    //Format the String
    private String format(double tps) {
        return ((tps > 18.0) ? ChatColor.GREEN : (tps > 16.0) ? ChatColor.YELLOW : ChatColor.RED).toString()
        + ((tps > 20.0) ? "*" : "") + Math.min(Math.round(tps * 100.0) / 100.0, 20.0);
    }
}
