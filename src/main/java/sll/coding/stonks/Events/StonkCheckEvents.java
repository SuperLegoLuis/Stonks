package sll.coding.stonks.Events;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.ItemStack;
import sll.coding.stonks.Data.DataFile;
import sll.coding.stonks.Data.DataUtils;

import java.util.ArrayList;
import java.util.List;

public class StonkCheckEvents implements Listener {

    public static void check(Player player) {
        List<Integer> stonks = new ArrayList<>();
        for (ItemStack item : player.getInventory().getContents()) {
            if (item.getItemMeta().hasDisplayName()) {
                player.sendMessage("Display Name: " + item.getItemMeta().getDisplayName());
            }
            else {
                player.sendMessage("No custom display name!");
            }
            if (item.getItemMeta().hasCustomModelData()) {
                player.sendMessage("Custom Model Data: " + item.getItemMeta().getCustomModelData());
            } else {
                player.sendMessage("No custom model data!");
            }
            check(item, stonks);
        }
        for (ItemStack item : player.getEnderChest().getContents()) {
            if (item.getItemMeta().hasDisplayName()) {
                player.sendMessage("Display Name: " + item.getItemMeta().getDisplayName());
            }
            else {
                player.sendMessage("No custom display name!");
            }
            if (item.getItemMeta().hasCustomModelData()) {
                player.sendMessage("Custom Model Data: " + item.getItemMeta().getCustomModelData());
            } else {
                player.sendMessage("No custom model data!");
            }
            check(item, stonks);
        }
        DataFile.reload();
        DataUtils.setStonks(player, stonks);
        DataFile.save();
    }

    public static void check(Player player, ItemStack... extraItems) {
        List<Integer> stonks = new ArrayList<>();
        for (ItemStack item : player.getInventory().getContents()) {
            if (item.getItemMeta().hasDisplayName()) {
                player.sendMessage("Display Name: " + item.getItemMeta().getDisplayName());
            }
            else {
                player.sendMessage("No custom display name!");
            }
            if (item.getItemMeta().hasCustomModelData()) {
                player.sendMessage("Custom Model Data: " + item.getItemMeta().getCustomModelData());
            } else {
                player.sendMessage("No custom model data!");
            }
            check(item, stonks);
        }
        for (ItemStack item : player.getEnderChest().getContents()) {
            if (item.getItemMeta().hasDisplayName()) {
                player.sendMessage("Display Name: " + item.getItemMeta().getDisplayName());
            }
            else {
                player.sendMessage("No custom display name!");
            }
            if (item.getItemMeta().hasCustomModelData()) {
                player.sendMessage("Custom Model Data: " + item.getItemMeta().getCustomModelData());
            } else {
                player.sendMessage("No custom model data!");
            }
            check(item, stonks);
        }
        for (ItemStack item : extraItems) {
            player.sendMessage("===== Extra Item =====");
            if (item.getItemMeta().hasDisplayName()) {
                player.sendMessage("Display Name: " + item.getItemMeta().getDisplayName());
            }
            else {
                player.sendMessage("No custom display name!");
            }
            if (item.getItemMeta().hasCustomModelData()) {
                player.sendMessage("Custom Model Data: " + item.getItemMeta().getCustomModelData());
            } else {
                player.sendMessage("No custom model data!");
            }
            check(item, stonks);
        }
        DataFile.reload();
        DataUtils.setStonks(player, stonks);
        DataFile.save();
    }

    public static void check(ItemStack item, List<Integer> stonks) {
        if (item.getItemMeta().getDisplayName().equals(ChatColor.RESET + "Stonk")) {
            stonks.add(item.getItemMeta().getCustomModelData());
        }
    }

    @EventHandler
    public void onInventoryInteract(InventoryClickEvent event) {
        check((Player) event.getWhoClicked());
    }

    /*@EventHandler
    public void onPickup(PlayerPickupItemEvent event) {
        check(event.getPlayer(), event.getItem().getItemStack());
        event.getPlayer().sendMessage(event.getItem().getItemStack().getItemMeta().getDisplayName());
    }*/

    @EventHandler
    public void onPickup(EntityPickupItemEvent event) {
        if (event.getEntity() instanceof Player) {
            check((Player) event.getEntity());
        }
    }

    @EventHandler
    public void onDrop(PlayerDropItemEvent event) {
        check(event.getPlayer());
    }

}
