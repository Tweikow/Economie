package fr.tweikow.economie;

import fr.tweikow.economie.Commands.EcoCommand;
import fr.tweikow.economie.Commands.MoneyCommand;
import fr.tweikow.economie.Commands.PayCommand;
import fr.tweikow.economie.Listener.Join;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    public static Main main;

    @Override
    public void onEnable() {
        main = this;

        getCommand("money").setExecutor(new MoneyCommand());
        getCommand("eco").setExecutor(new EcoCommand());
        getCommand("pay").setExecutor(new PayCommand());

        Bukkit.getPluginManager().registerEvents(new Join(), this);
    }

    @Override
    public void onDisable() {



    }
}
