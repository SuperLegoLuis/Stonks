package sll.coding.stonks;

import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import sll.coding.stonks.API.ChatInput.ChatInput;
import sll.coding.stonks.API.YesNo.YesNoInput;
import sll.coding.stonks.Commands.StonkMarketCommand;
import sll.coding.stonks.Commands.StonksCommand;
import sll.coding.stonks.Data.DataFile;
import sll.coding.stonks.Data.DataUtils;
import sll.coding.stonks.Events.StonkCheckEvents;

public final class Main extends JavaPlugin {

    private static Economy econ = null;

    @Override
    public void onEnable() {
        getLogger().info("Thanks for installing STONKS!");

        int steps = 4;

        setupFiles(1, steps);
        setupCommands(2, steps);
        setupEvents(3, steps);
        if (!setupEconomy(4, steps)) {
            getLogger().severe("Vault is not installed. Vault is required to work with your economy plugin.");
            getLogger().severe("Please download Vault at https://www.spigotmc.org/resources/vault.34315");
            Bukkit.getPluginManager().disablePlugin(this);
        }

        DataFile.get().set("version", "InDev");
        DataFile.save();

        getLogger().info("Startup procedure completed.");
    }

    public void setupFiles(int place, int steps) {
        getLogger().info("Setting up files. (" + place + "/" + steps + ")");
        DataFile.setup();
        DataUtils.reloadCompanies();
    }

    public void saveFiles(int place, int steps) {
        getLogger().info("Saving all files. (" + place + "/" + steps + ")");
        DataFile.save();
    }

    public void setupCommands(int place, int steps) {
        getLogger().info("Setting up commands. (" + place + "/" + steps + ")");
        getCommand("stonks").setExecutor(new StonksCommand());
        getCommand("stonkmarket").setExecutor(new StonkMarketCommand());
    }

    public void setupEvents(int place, int steps) {
        getLogger().info("Setting up events. (" + place + "/" + steps + ")");
        Bukkit.getPluginManager().registerEvents(new ChatInput(), this);
        Bukkit.getPluginManager().registerEvents(new YesNoInput(), this);
        Bukkit.getPluginManager().registerEvents(new StonkCheckEvents(), this);
    }

    private boolean setupEconomy(int place, int steps) {
        getLogger().info("Setting up economy. (" + place + "/" + steps + ")");
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        DataUtils.econ = rsp.getProvider();
        return DataUtils.econ != null;
    }

    @Override
    public void onDisable() {
        int steps = 1;

        getLogger().info("Thanks for using STONKS!");

        saveFiles(1, steps);

        getLogger().info("Shutdown procedure completed.");
    }
}
