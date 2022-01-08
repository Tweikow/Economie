package fr.tweikow.economie.Data;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;

import static fr.tweikow.economie.Main.main;

public class FileConfig {

    static File file;
    static YamlConfiguration config;


    public static void createFile(Player player) {
        file = new File(main.getDataFolder() + "/playerdata/" + player.getUniqueId() + ".yml");
        config = YamlConfiguration.loadConfiguration(file);

        if(!file.exists()) {
            config.set("player_name", player.getName());
            config.set("money", 0);
        }
        try {
            config.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
