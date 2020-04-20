package sll.coding.stonks.Data;

import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import sll.coding.stonks.API.Objects.Company;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class DataUtils {

    private static List<Company> companies = new ArrayList<>();
    public static Economy econ = null;

    public static Company createCompany(String name, String symbol, int baseStonkPrice, Player owner, String icon) {
        if (!companyExists(name)) {
            int id = new Random().nextInt(999999);
            symbol = symbol.toUpperCase();
            symbol = symbol.replaceAll(" ", "");
            FileConfiguration dataFile = DataFile.get();
            dataFile.set("companies." + name + ".symbol", symbol);
            DataFile.save();
            dataFile.set("companies." + name + ".baseStonkPrice" , baseStonkPrice);
            DataFile.save();
            dataFile.set("companies." + name + ".owner", owner.getUniqueId().toString());
            DataFile.save();
            dataFile.set("companies." + name + ".id", id);
            DataFile.save();
            dataFile.set("companies." + name + ".icon", icon);
            if (!dataFile.contains("players." + owner.getUniqueId().toString() + ".companies")) {
                dataFile.set("players." + owner.getUniqueId().toString() + ".companies", new ArrayList<String>());
                DataFile.save();
            }
            List<String> playerCompanies = dataFile.getStringList("players." + owner.getUniqueId().toString() + ".companies");
            playerCompanies.add(name);
            dataFile.set("players." + owner.getUniqueId().toString() + ".companies", playerCompanies);

            DataFile.save();

            Company com = new Company(name, symbol, baseStonkPrice, owner, id, icon);
            companies.add(com);
            return com;
        }
        else {
            return null;
        }
    }

    public static boolean companyExists(String name) {
        for (Company company : companies) {
            if (company.getName().equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }

    public static Company getCompany(String name) {
        for (Company company : companies) {
            if (company.getName().equalsIgnoreCase(name)) {
                return company;
            }
        }
        return null;
    }

    public static List<Company> getCompanies(Player player) {
        List<Company> companies = new ArrayList<>();
        for (String com : DataFile.get().getStringList("players." + player.getUniqueId().toString() + ".companies")) {
            companies.add(getCompany(com));
        }
        return companies;
    }

    public static List<Company> getCompanies() {
        List<Company> companies = new ArrayList<>();
        if (DataFile.get().getConfigurationSection("companies") != null) {
            for (String com : DataFile.get().getConfigurationSection("companies").getKeys(false)) {
                companies.add(getCompany(com));
            }
        }

        return companies;
    }

    public static void reloadFiles() {
        DataFile.reload();
    }

    public static void reloadCompanies() {
        FileConfiguration data = DataFile.get();
        companies.clear();
        if (data.getConfigurationSection("companies") != null) {
            for (String name : data.getConfigurationSection("companies").getKeys(false)) {
                String base = "companies." + name;
                companies.add(new Company(name, data.getString(base + ".symbol"), data.getInt(base + ".baseStonkPrice"), Bukkit.getPlayer(UUID.fromString(data.getString(base + ".owner"))), data.getInt(base + "id"), data.getString(base + ".icon")));
            }
        }
    }

    public static void setStonks(Player player, List<Integer> stonks) {
        DataFile.get().set("players." + player.getUniqueId() + ".stonks", stonks);
    }

    public static void addStonk(Player player, int stonk) {
        DataFile.get().set("players." + player.getUniqueId() + ".stonks", DataFile.get().getIntegerList("players." + player.getUniqueId() + ".stonks").add(stonk));
    }

    public static void removeStonk(Player player, int stonk) {
        DataFile.get().set("players." + player.getUniqueId() + ".stonks", DataFile.get().getIntegerList("players." + player.getUniqueId() + ".stonks").remove(stonk));
    }

    public static List<Integer> getStonks(Player player) {
        return DataFile.get().getIntegerList("players." + player.getUniqueId() + ".stonks");
    }

}
