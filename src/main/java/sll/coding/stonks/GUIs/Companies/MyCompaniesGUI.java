package sll.coding.stonks.GUIs.Companies;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import sll.coding.stonks.API.GUI;
import sll.coding.stonks.API.GUICreator;
import sll.coding.stonks.API.Objects.Company;
import sll.coding.stonks.Data.DataUtils;

import java.util.Arrays;
import java.util.HashMap;

public class MyCompaniesGUI extends GUI {

    private static GUICreator guicreator = new GUICreator();

    private static HashMap<Integer, ItemStack> items = new HashMap<>();

    public MyCompaniesGUI() {
        super(ChatColor.DARK_AQUA + "My Companies");
    }

    public Inventory get(Player player) {
        Inventory i = guicreator.createInventory(title, 27);

        for (Company company : DataUtils.getCompanies(player)) {
            i.addItem(guicreator.createItem(ChatColor.GRAY + company.getName(), Arrays.asList(ChatColor.DARK_GRAY + "Symbol: " + ChatColor.GRAY + company.getSymbol()) , Material.valueOf(company.getIcon())));
        }

        return i;
    }

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        if (event.getView().getTitle().equals(title)) {
            event.setCancelled(true);
            ItemStack item = event.getClickedInventory().getItem(event.getSlot());
            if (item != null) {
                event.getWhoClicked().openInventory(new ManageCompanyGUI(DataUtils.getCompany(ChatColor.stripColor(item.getItemMeta().getDisplayName()))).get((Player) event.getWhoClicked()));
            }
        }
    }

    @EventHandler
    public void onClose(InventoryCloseEvent event) {
        if (event.getView().getTitle().equals(title)) {
            HandlerList.unregisterAll(this);
        }
    }

}
