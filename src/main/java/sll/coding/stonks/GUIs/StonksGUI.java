package sll.coding.stonks.GUIs;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import sll.coding.stonks.API.GUI;
import sll.coding.stonks.GUIs.Admin.AdminSettingsGUI;
import sll.coding.stonks.GUIs.Companies.CreateACompanyGUI;
import sll.coding.stonks.GUIs.Companies.MyCompaniesGUI;

public class StonksGUI extends GUI {

    public StonksGUI() {
        super(ChatColor.DARK_AQUA + "Stonks");
    }

    public Inventory get(Player player) {
        Inventory i = guicreator.createInventory(title, 27);

        i.setItem(10, guicreator.createItem(ChatColor.DARK_AQUA + "Create a company", Material.PAPER));
        i.setItem(13, guicreator.createItem(ChatColor.DARK_AQUA + "My Companies", Material.COMPASS));
        i.setItem(16, guicreator.createItem(ChatColor.DARK_AQUA + "Buy a company", Material.FEATHER));

        if (player.hasPermission("stonks.admin"))   i.setItem(26, guicreator.createItem(ChatColor.DARK_AQUA + "Admin Settings", Material.BEDROCK));

        return i;
    }

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        if (event.getView().getTitle().equals(title)) {
            event.setCancelled(true);
            if (event.getSlot() == 10) {
                event.getWhoClicked().openInventory(new CreateACompanyGUI().get((Player) event.getWhoClicked()));
            }
            else if (event.getSlot() == 13) {
                event.getWhoClicked().openInventory(new MyCompaniesGUI().get((Player) event.getWhoClicked()));
            }
            else if (event.getSlot() == 16) {
                event.getWhoClicked().sendMessage(ChatColor.RED + "This is coming soon!");
            }
            else if (event.getSlot() == 26) {
                if (event.getWhoClicked().hasPermission("stonks.admin")) {
                    event.getWhoClicked().openInventory(new AdminSettingsGUI().get((Player) event.getWhoClicked()));
                }
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
