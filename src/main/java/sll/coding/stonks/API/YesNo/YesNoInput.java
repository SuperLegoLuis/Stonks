package sll.coding.stonks.API.YesNo;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import sll.coding.stonks.API.GUICreator;

import java.util.HashMap;

public class YesNoInput implements Listener {

    private static HashMap<Player, YesNoInputCallback> playerYesNoInputCallbackHashMap = new HashMap<>();
    private static String title = ChatColor.DARK_AQUA + "Are you sure?";
    private static GUICreator guicreator = new GUICreator();

    public static void getInput(Player player, YesNoInputCallback callback) {
        playerYesNoInputCallbackHashMap.put(player, callback);
    }

    private static Inventory get() {
        Inventory i = guicreator.createInventory(title, 27);

        i.setItem(11, guicreator.createItem(ChatColor.GREEN + "Yes", Material.GREEN_CONCRETE));
        i.setItem(15, guicreator.createItem(ChatColor.RED + "No", Material.RED_CONCRETE));

        return i;
    }

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        if (event.getView().getTitle().equals(title)) {
            event.setCancelled(true);
            if (playerYesNoInputCallbackHashMap.containsKey(event.getWhoClicked())) {
                if (event.getSlot() == 11) {
                    playerYesNoInputCallbackHashMap.get(event.getWhoClicked()).response((Player) event.getWhoClicked(), YesNoReturn.YES);
                    playerYesNoInputCallbackHashMap.remove(event.getWhoClicked());
                }
                else if (event.getSlot() == 15) {
                    playerYesNoInputCallbackHashMap.get(event.getWhoClicked()).response((Player) event.getWhoClicked(), YesNoReturn.NO);
                    playerYesNoInputCallbackHashMap.remove(event.getWhoClicked());
                }
            }
        }
    }

    @EventHandler
    public void onClose(InventoryCloseEvent event) {
        if (event.getView().getTitle().equals(title)) {
            if (playerYesNoInputCallbackHashMap.containsKey(event.getPlayer())) {
                playerYesNoInputCallbackHashMap.get(event.getPlayer()).response((Player) event.getPlayer(), YesNoReturn.NO);
                playerYesNoInputCallbackHashMap.remove(event.getPlayer());
            }
        }
    }

}
