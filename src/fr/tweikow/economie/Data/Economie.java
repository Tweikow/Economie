package fr.tweikow.economie.Data;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;

import static fr.tweikow.economie.Main.main;

public class Economie {

    static File file;
    static YamlConfiguration config;

    public static void setMoney(Player target, Double amount){
        file = new File(main.getDataFolder() + "/playerdata/" + target.getUniqueId() + ".yml");
        config = YamlConfiguration.loadConfiguration(file);
        FileConfig.createFile(target);

        config.set("money", amount);
        try {
            config.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void resetMoney(Player target){
        file = new File(main.getDataFolder() + "/playerdata/" + target.getUniqueId() + ".yml");
        config = YamlConfiguration.loadConfiguration(file);
        FileConfig.createFile(target);

        config.set("money", 0.0);
        try {
            config.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
