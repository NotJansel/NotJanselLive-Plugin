package de.notjansel.notjansellive;

import de.notjansel.notjansellive.commands.*;
import de.notjansel.notjansellive.listeners.DeathListener;
import de.notjansel.notjansellive.listeners.JoinListener;
import de.notjansel.notjansellive.listeners.QuitListener;
import dev.jcsoftware.jscoreboards.JGlobalScoreboard;
import dev.jcsoftware.jscoreboards.JScoreboardTeam;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {


    public FileConfiguration config;

    private static Main instance;
    private JGlobalScoreboard scoreboard;
    private JScoreboardTeam team;
    private Runtime.Version version = Runtime.version();

    @Override
    public void onLoad(){
        instance = this;
        Bukkit.getConsoleSender().sendMessage(ChatColor.LIGHT_PURPLE + "Loading NotJanselLive...");
    }

    @Override
    public void onEnable() {
        Bukkit.getConsoleSender().sendMessage(ChatColor.BLUE + "Creating the necessary Scoreboard, Teams and Commands...");
        //Create Scoreboard
        scoreboard = new JGlobalScoreboard(
                () -> {
                    return null;
                },
                () -> {
                    return null;
                }
        );
        //Add Players to Scoreboard
        Bukkit.getOnlinePlayers().forEach(this::addToScoreboard);
        //Create the Plugin Manager and add the Events
        PluginManager manager = Bukkit.getPluginManager();
        manager.registerEvents( new JoinListener(), this);
        manager.registerEvents( new QuitListener(), this);
        manager.registerEvents( new DeathListener(), this);
        //Create Teams
        team = scoreboard.createTeam("live", "[Live] ", ChatColor.DARK_PURPLE);
        team = scoreboard.createTeam("record", "[Record] ", ChatColor.DARK_RED);
        //Get Command CLasses and add them
        getCommand("live").setExecutor(new LiveCommand());
        getCommand("offline").setExecutor(new OfflineCommand());
        getCommand("record").setExecutor(new RecordCommand());
        getCommand("finishrecord").setExecutor(new FinishrecordCommand());
        getCommand("servertps").setExecutor(new ServerTpsCommand());
        getCommand("configreload").setExecutor(new ReloadConfigCommand());
        Bukkit.getConsoleSender().sendMessage(ChatColor.BLUE + "Created and loaded everything. Plugin loaded successfully.\n\n\n\n\n");
        //Final message when plugin loaded successfully
        Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW + "====================================================");
        Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW + "|                                                  |");
        Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW + "|This plugin is still in work!                     |");
        Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW + "|Usage under own §4Caution§e or with my Permission     |");
        Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW + "|                                                  |");
        Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW + "|If you have questions, ask NotJansel#0727         |");
        Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW + "|on Discord.                                       |");
        Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW + "|Java 16 is required, aswell as Paper 1.17.1!      |");
        Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW + "|                                                  |");
        Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW + "====================================================");
        this.saveDefaultConfig();
    }

    @Override
    public void onDisable() {
        scoreboard.destroy();
    }

    private void addToScoreboard(Player player) {
        scoreboard.addPlayer(player);
        scoreboard.updateScoreboard();
    }

    public static Main getInstance() { return instance; }


    public JGlobalScoreboard getScoreBoard() { return scoreboard; }
}
