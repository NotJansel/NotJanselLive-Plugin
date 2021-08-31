package de.notjansel.notjansellive.listeners;

import de.notjansel.notjansellive.Main;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.util.Random;

public class DeathListener implements Listener {

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {

        if (!Main.getInstance().getConfig().getBoolean("plugin.deathmsg-enabled")) { return; }

        Random r = new Random();
        int randomitem = r.nextInt(Main.getInstance().getConfig().getList("plugin.death-messages").size());
        String randomdeathmessage = Main.getInstance().getConfig().getList("plugin.death-messages").get(randomitem).toString();

        String death = String.format(ChatColor.translateAlternateColorCodes('&', randomdeathmessage), event.getEntity().getName());

        event.setDeathMessage(death);
    }

}
