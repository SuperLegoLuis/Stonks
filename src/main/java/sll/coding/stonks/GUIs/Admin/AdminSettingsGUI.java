package sll.coding.stonks.GUIs.Admin;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import sll.coding.stonks.API.GUI;
import sll.coding.stonks.Data.DataUtils;

public class AdminSettingsGUI extends GUI {

    public AdminSettingsGUI() {
        super(ChatColor.DARK_AQUA + "Admin Settings");
    }

    @Override
    public Inventory get(Player player) {
        Inventory i = guicreator.createInventory(title, 27);

        i.setItem(10, guicreator.createItem(ChatColor.DARK_AQUA + "Reload", Material.YELLOW_CONCRETE));
        i.setItem(12, guicreator.createItem(ChatColor.DARK_AQUA + "View all Companies", Material.COMPASS));

        return i;
    }

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        if (event.getView().getTitle().equals(title)) {
            event.setCancelled(true);
            if (event.getSlot() == 10) {
                DataUtils.reloadFiles();
                DataUtils.reloadCompanies();
                event.getWhoClicked().closeInventory();
            }
            else if (event.getSlot() == 12) {
                event.getWhoClicked().sendMessage(ChatColor.RED + "This is coming soon!");
            }
        }
    }

}
