package de.notjansel.notjansellive.commands;

import de.notjansel.notjansellive.Main;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class ReloadConfigCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (!sender.hasPermission("notjansellive.reload")) {
            sender.sendMessage("§c§lYou have no permission to use this command.");
            return true;
        }

        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Main.getInstance().getConfig().getString("plugin.reload-message")));
        Main.getInstance().reloadConfig();

        return true;
    }
}
