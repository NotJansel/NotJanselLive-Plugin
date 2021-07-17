package de.notjansel.wolfkarstplugin;

import de.notjansel.wolfkarstplugin.commands.FinishrecordCommand;
import de.notjansel.wolfkarstplugin.commands.LiveCommand;
import de.notjansel.wolfkarstplugin.commands.OfflineCommand;
import de.notjansel.wolfkarstplugin.commands.RecordCommand;
import de.notjansel.wolfkarstplugin.listeners.JoinListener;
import de.notjansel.wolfkarstplugin.listeners.QuitListener;
import dev.jcsoftware.jscoreboards.JGlobalScoreboard;
import dev.jcsoftware.jscoreboards.JScoreboardTeam;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    private static Main instance;
    private JGlobalScoreboard scoreboard;
    private JScoreboardTeam team;

    @Override
    public void onLoad(){
        instance = this;
        Bukkit.getConsoleSender().sendMessage(ChatColor.LIGHT_PURPLE + "Loading Live Plugin...");
    }

    @Override
    public void onEnable() {

        scoreboard = new JGlobalScoreboard(
                () -> {
                    return null;
                },
                () -> {

                    return null;
                }
        );

        Bukkit.getOnlinePlayers().forEach(this::addToScoreboard);

        PluginManager manager = Bukkit.getPluginManager();
        manager.registerEvents( new JoinListener(), this);
        manager.registerEvents( new QuitListener(), this);

        team = scoreboard.createTeam("live", "[Live] ", ChatColor.DARK_PURPLE);
        team = scoreboard.createTeam("record", "[Record] ", ChatColor.DARK_RED);

        getCommand("live").setExecutor(new LiveCommand());
        getCommand("offline").setExecutor(new OfflineCommand());
        getCommand("record").setExecutor(new RecordCommand());
        getCommand("finishrecord").setExecutor(new FinishrecordCommand());



        Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW + "====================================================");
        Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW + "|                                                  |");
        Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW + "|This Plugin is under development.                 |");
        Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW + "|Usage under own §4caution§e!                          |");
        Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW + "|                                                  |");
        Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW + "|For Questions, ask NotJansel#0727 in Discord.     |");
        Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW + "|                                                  |");
        Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW + "|Java 16 and Paper are required to use this Plugin.|");
        Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW + "====================================================");



    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    private void addToScoreboard(Player player) {
        scoreboard.addPlayer(player);
        scoreboard.updateScoreboard();
    }

    public static Main getInstance() { return instance; }

    public JGlobalScoreboard getScoreBoard() { return scoreboard; }
}
