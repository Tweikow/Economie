package fr.tweikow.economie.Commands;

import fr.tweikow.economie.Data.Economie;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;

import static fr.tweikow.economie.Main.main;

public class PayCommand implements CommandExecutor {

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

        if(args.length == 2) {
            Player target = Bukkit.getPlayer(args[0]);
            if(target != null){
                filetarget = new File(main.getDataFolder() + "/playerdata/" + target.getUniqueId() + ".yml");
                configtarget = YamlConfiguration.loadConfiguration(filetarget);
                double amount = Double.parseDouble(args[1]);

                if(!(amount > config.getDouble("money"))){
                    Economie.setMoney(player, config.getDouble("money") - Double.parseDouble(String.valueOf(amount)));
                    Economie.setMoney(target, configtarget.getDouble("money") + Double.parseDouble(String.valueOf(amount)));

                    player.sendMessage("§eVous venez d'envoyé §f" + amount + "§e$ a §f" + target.getName());
                    target.sendMessage("§eVous avez reçu §f" + amount + "§e$ de §f" + player.getName());

                } else player.sendMessage("§cVous n'avez assez d'argent pour l'envoyé");
            } else player.sendMessage("§cLe joueur saisi n'est pas en ligne !");
            return false;
        } else player.sendMessage("§cUtilisation: /pay <player> <amount>");
        return false;
    }
}
