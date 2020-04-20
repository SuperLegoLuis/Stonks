package sll.coding.stonks.GUIs.Companies;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import sll.coding.stonks.API.GUI;
import sll.coding.stonks.API.Objects.Company;
import sll.coding.stonks.API.YesNo.YesNoInput;
import sll.coding.stonks.API.YesNo.YesNoInputCallback;
import sll.coding.stonks.API.YesNo.YesNoReturn;

public class ManageCompanyGUI extends GUI {

    public ManageCompanyGUI(Company company) {
        super(ChatColor.AQUA + company.getName());
    }

    @Override
    public Inventory get(Player player) {
        Inventory i = guicreator.createInventory(title, 54);

        i.setItem(33, guicreator.createItem(ChatColor.RED + "Delete", Material.BEDROCK));

        return i;
    }

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        if (event.getView().getTitle().equals(title)) {
            event.setCancelled(true);
            if (event.getSlot() == 33) {
                YesNoInput.getInput((Player) event.getWhoClicked(), new YesNoInputCallback() {
                    @Override
                    public void response(Player player, YesNoReturn yesNoReturn) {
                        player.openInventory(get(player));
                        if (yesNoReturn.equals(YesNoReturn.YES)) {
                            // Delete company.
                        }
                        else {
                            // Cancel (Tell them that the action was cancelled)
                        }
                    }
                });
            }
        }
    }

}
