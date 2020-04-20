package sll.coding.stonks.API;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import sll.coding.stonks.Main;

public abstract class GUI implements Listener {

    protected String title;
    protected static GUICreator guicreator = new GUICreator();

    public GUI(String title) {
        Bukkit.getPluginManager().registerEvents(this, Main.getPlugin(Main.class));

        this.title = title;
    }

    public abstract Inventory get(Player player);

    @EventHandler
    public void onClose(InventoryCloseEvent event) {
        if (event.getView().getTitle().equals(title)) {
            HandlerList.unregisterAll(this);
        }
    }

}
