package fr.tweikow.economie.Listener;

import fr.tweikow.economie.Data.FileConfig;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class Join implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        FileConfig.createFile(event.getPlayer());
        event.setJoinMessage("§a+ §e" + event.getPlayer().getName());
    }
    @EventHandler
    public void onQuit(PlayerQuitEvent event){
        event.setQuitMessage("§c- §e" + event.getPlayer().getName());
    }


}
