package fr.tweikow.economie.Commands;

import fr.tweikow.economie.Data.FileConfig;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;

import static fr.tweikow.economie.Main.main;

public class MoneyCommand implements CommandExecutor {

    File file;
    File filetarget;
    YamlConfiguration config;
    YamlConfiguration configtarget;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)) return false;

        Player player = (Player) sender;

        file = new File(main.getDataFolder() + "/playerdata/" + player.getUniqueId() + ".yml");
        config = YamlConfiguration.loadConfiguration(file);
        FileConfig.createFile(player);

        if(args.length == 0) player.sendMessage("§eBalance: §f" + config.getDouble("money")+ " §e$");

        if(args.length == 1) {
            Player target = Bukkit.getPlayer(args[0]);
            if (target != null) {
                filetarget = new File(main.getDataFolder() + "/playerdata/" + target.getUniqueId() + ".yml");
                configtarget = YamlConfiguration.loadConfiguration(filetarget);
                FileConfig.createFile(target);
                player.sendMessage("§eBalance de §f" + target.getName() + "§e: §f" + configtarget.getDouble("money") + " §e$");
            } else player.sendMessage("§cLe joueur saisi n'est pas en ligne !");
        }
        return false;
    }
}
