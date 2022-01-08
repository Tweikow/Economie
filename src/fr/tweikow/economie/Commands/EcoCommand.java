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

public class EcoCommand implements CommandExecutor {

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

        if(!(player.hasPermission("eco.use"))){
            player.sendMessage("§cVous n'avez pas la permission requise !");
            return false;
        }

        if(args.length == 0){
            player.sendMessage("§cUtilisation: /eco <add/remove/set> <player> <amount>");
            return false;
        }

        if(args[0].equalsIgnoreCase("add")) {
            if (args.length == 3) {
                Player target = Bukkit.getPlayer(args[1]);
                if (target != null) {
                    filetarget = new File(main.getDataFolder() + "/playerdata/" + target.getUniqueId() + ".yml");
                    configtarget = YamlConfiguration.loadConfiguration(filetarget);
                    double amount = Double.parseDouble(args[2]);
                    Economie.setMoney(target, configtarget.getDouble("money") + amount);
                    player.sendMessage("§eVous avez ajouté §f" + amount + "§e$ à §f" + target.getName());
                    target.sendMessage("§eVous avez reçu §f" + amount + "§e$ de §f" + player.getName());
                } else player.sendMessage("§cLe joueur saisi n'est pas en ligne !");
            } else player.sendMessage("§cUtilisation: /eco add <player> <amount>");
            return false;
        }
        if(args[0].equalsIgnoreCase("remove")) {
            if (args.length == 3) {
                Player target = Bukkit.getPlayer(args[1]);
                if (target != null) {
                    filetarget = new File(main.getDataFolder() + "/playerdata/" + target.getUniqueId() + ".yml");
                    configtarget = YamlConfiguration.loadConfiguration(filetarget);
                    double amount = Double.parseDouble(args[2]);
                    Economie.setMoney(target, configtarget.getDouble("money") - amount);
                    player.sendMessage("§eVous avez retiré §f" + amount + "§e$ de §f" + target.getName());
                    target.sendMessage(player.getName() + " §evous a retiré §f" + amount + "§e$");
                } else player.sendMessage("§cLe joueur saisi n'est pas en ligne !");
            } else player.sendMessage("§cUtilisation: /eco remove <player> <amount>");
            return false;
        }
        if(args[0].equalsIgnoreCase("set")) {
            if (args.length == 3) {
                Player target = Bukkit.getPlayer(args[1]);
                if (target != null) {
                    double amount = Double.parseDouble(args[2]);
                    Economie.setMoney(target, amount);
                    player.sendMessage("§eVous avez défini §f" + amount + "§e$ à §f" + target.getName());
                    target.sendMessage(player.getName() + " §ea défini votre solde à §f" + amount + "§e$");
                } else player.sendMessage("§cLe joueur saisi n'est pas en ligne !");
            } else player.sendMessage("§cUtilisation: /eco set <player> <amount>");
            return false;
        }
        if(args[0].equalsIgnoreCase("reset")) {
            if (args.length == 2) {
                Player target = Bukkit.getPlayer(args[1]);
                if (target != null) {
                    Economie.resetMoney(target);
                    player.sendMessage("§eVous avez réinitialisé l'argent de §f" + target.getName() + " §eà §f0§e$");
                    target.sendMessage(player.getName() + " §ea réinitialisé votre solde à §f0§e$");
                } else player.sendMessage("§cLe joueur saisi n'est pas en ligne !");
            } else player.sendMessage("§cUtilisation: /eco reset <player> ");
            return false;
        } else player.sendMessage("§cUtilisation: /eco <add/remove/set/reset> <player> [<amount>]");
     return false;
    }
}
