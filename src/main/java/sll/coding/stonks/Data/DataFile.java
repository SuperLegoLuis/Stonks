package sll.coding.stonks.Data;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import sll.coding.stonks.Main;

import java.io.File;
import java.io.IOException;

public class DataFile {

    private static File file;
    private static FileConfiguration config;

    public static void setup() {
        Main plugin = Main.getPlugin(Main.class);
        File folder = plugin.getDataFolder();
        file = new File(folder, "data.yml");

        if (!folder.exists()) {
            folder.mkdirs();
        }

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        reload();
    }

    public static FileConfiguration get() {
        reload();
        return config;
    }

    public static void reload() {
        config = YamlConfiguration.loadConfiguration(file);
    }

    public static void save() {
        try {
            config.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
