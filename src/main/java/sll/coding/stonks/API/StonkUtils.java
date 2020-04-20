package sll.coding.stonks.API;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import sll.coding.stonks.API.Objects.Company;

import java.util.Arrays;

public class StonkUtils {

    private static GUICreator guicreator = new GUICreator();

    public static ItemStack getStonk(Company company) {
        ItemStack item = guicreator.createItem("Stonk", Material.PAPER);
        guicreator.changeLores(item, Arrays.asList(ChatColor.DARK_GRAY + "Company: " + ChatColor.GRAY + company.getName() + ChatColor.DARK_GRAY + " (" + ChatColor.GRAY + company.getSymbol() + ChatColor.DARK_GRAY + ")", ChatColor.DARK_GRAY + "Bought for: " + ChatColor.GRAY + company.getBaseStockPrice()));
        ItemMeta meta = item.getItemMeta();
        meta.setCustomModelData(company.getId());
        meta.addEnchant(Enchantment.DURABILITY, 1, false);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(meta);
        return item;
    }

}
