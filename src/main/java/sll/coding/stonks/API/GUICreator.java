package sll.coding.stonks.API;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GUICreator {
    private HashMap<String, String> methods = new HashMap<>();

    private HashMap<String, Integer> slots = new HashMap<>();

    private HashMap<String, String> Rmethods = new HashMap<>();

    private HashMap<String, Integer> Rslots = new HashMap<>();

    private HashMap<String, String> Mmethods = new HashMap<>();

    private HashMap<String, Integer> Mslots = new HashMap<>();

    private HashMap<String, String> SLmethods = new HashMap<>();

    private HashMap<String, Integer> SLslots = new HashMap<>();

    private HashMap<String, String> SRmethods = new HashMap<>();

    private HashMap<String, Integer> SRslots = new HashMap<>();

    private List<Inventory> invs = new ArrayList<>();

    public Inventory createInventory(String name, int slots) {
        Inventory i = Bukkit.getServer().createInventory(null, slots, name);
        this.invs.add(i);
        return i;
    }

    public ItemStack createItem(String name, Material material, int amount) {
        ItemStack item = new ItemStack(material, amount);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.RESET + name);
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack createItem(String name, Material material) {
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.RESET + name);
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack createItem(String name, List<String> lores, Material material, int amount) {
        ItemStack item = new ItemStack(material, amount);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.RESET + name);
        meta.setLore(lores);
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack createItem(String name, List<String> lores, Material material) {
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.RESET + name);
        meta.setLore(lores);
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack createSkull(String name, String owner, List<String> lores, int amount) {
        ItemStack item = new ItemStack(Material.PLAYER_HEAD, amount);
        SkullMeta meta = (SkullMeta)item.getItemMeta();
        meta.setDisplayName(ChatColor.RESET + name);
        meta.setOwner(owner);
        meta.setLore(lores);
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack createSkull(String name, String owner, List<String> lores) {
        ItemStack item = new ItemStack(Material.PLAYER_HEAD, 1);
        SkullMeta meta = (SkullMeta)item.getItemMeta();
        meta.setDisplayName(ChatColor.RESET + name);
        meta.setOwner(owner);
        meta.setLore(lores);
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack createSkull(String name, String owner) {
        ItemStack item = new ItemStack(Material.PLAYER_HEAD, 1);
        SkullMeta meta = (SkullMeta)item.getItemMeta();
        meta.setDisplayName(ChatColor.RESET + name);
        meta.setOwner(owner);
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack createSkull(String name, String owner, int amount) {
        ItemStack item = new ItemStack(Material.PLAYER_HEAD, amount);
        SkullMeta meta = (SkullMeta)item.getItemMeta();
        meta.setDisplayName(ChatColor.RESET + name);
        meta.setOwner(owner);
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack changeName(ItemStack item, String name) {
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.RESET + name);
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack changeLores(ItemStack item, List<String> lores) {
        ItemMeta meta = item.getItemMeta();
        meta.setLore(lores);
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack changeSkullOwner(ItemStack item, String owner) {
        if (item.getType().equals(Material.PLAYER_HEAD)) {
            SkullMeta meta = (SkullMeta)item.getItemMeta();
            meta.setOwner(owner);
            item.setItemMeta(meta);
        }
        return item;
    }

    /*public void addLeftClickEvent(Inventory i, int slot, String command) {
        this.methods.put(i.getName(), command);
        this.slots.put(command, Integer.valueOf(slot));
    }

    public void addRightClickEvent(Inventory i, int slot, String command) {
        this.Rmethods.put(i.getName(), command);
        this.Rslots.put(command, Integer.valueOf(slot));
    }

    public void addMiddleClickEvent(Inventory i, int slot, String command) {
        this.Mmethods.put(i.getName(), command);
        this.Mslots.put(command, Integer.valueOf(slot));
    }

    public void addShiftLeftClickEvent(Inventory i, int slot, String command) {
        this.SLmethods.put(i.getName(), command);
        this.SLslots.put(command, Integer.valueOf(slot));
    }

    public void addShiftRightClickEvent(Inventory i, int slot, String command) {
        this.SRmethods.put(i.getName(), command);
        this.SRslots.put(command, Integer.valueOf(slot));
    }

    @EventHandler
    public void pleaseIgnoreMe(InventoryClickEvent event) {
        if (event.getClick().equals(ClickType.LEFT)) {
            String command = this.methods.get(event.getClickedInventory().getName());
            int slot = event.getSlot();
            int neededslot = ((Integer)this.slots.get(command)).intValue();
            command = command.replace("%player%", event.getWhoClicked().getName());
            command = command.replace("%location%", String.valueOf(Integer.toString(event.getWhoClicked().getLocation().getBlockX())) + " " + Integer.toString(event.getWhoClicked().getLocation().getBlockY()) + " " + Integer.toString(event.getWhoClicked().getLocation().getBlockZ()));
            if (slot == neededslot)
                if (command.startsWith("-console ")) {
                    Bukkit.getServer().dispatchCommand((CommandSender)Bukkit.getConsoleSender(), command.replace("-console ", ""));
                } else {
                    Player player = (Player)event.getWhoClicked();
                    player.performCommand(command);
                }
        } else if (event.getClick().equals(ClickType.RIGHT)) {
            String command = this.Rmethods.get(event.getClickedInventory().getName());
            int slot = event.getSlot();
            int neededslot = ((Integer)this.Rslots.get(command)).intValue();
            command = command.replace("%player%", event.getWhoClicked().getName());
            command = command.replace("%location%", String.valueOf(Integer.toString(event.getWhoClicked().getLocation().getBlockX())) + " " + Integer.toString(event.getWhoClicked().getLocation().getBlockY()) + " " + Integer.toString(event.getWhoClicked().getLocation().getBlockZ()));
            if (slot == neededslot)
                if (command.startsWith("-console ")) {
                    Bukkit.getServer().dispatchCommand((CommandSender)Bukkit.getConsoleSender(), command.replace("-console ", ""));
                } else {
                    Player player = (Player)event.getWhoClicked();
                    player.performCommand(command);
                }
        } else if (event.getClick().equals(ClickType.MIDDLE)) {
            String command = this.Mmethods.get(event.getClickedInventory().getName());
            int slot = event.getSlot();
            int neededslot = ((Integer)this.Mslots.get(command)).intValue();
            command = command.replace("%player%", event.getWhoClicked().getName());
            command = command.replace("%location%", String.valueOf(Integer.toString(event.getWhoClicked().getLocation().getBlockX())) + " " + Integer.toString(event.getWhoClicked().getLocation().getBlockY()) + " " + Integer.toString(event.getWhoClicked().getLocation().getBlockZ()));
            if (slot == neededslot)
                if (command.startsWith("-console ")) {
                    Bukkit.getServer().dispatchCommand((CommandSender)Bukkit.getConsoleSender(), command.replace("-console ", ""));
                } else {
                    Player player = (Player)event.getWhoClicked();
                    player.performCommand(command);
                }
        } else if (event.getClick().equals(ClickType.SHIFT_LEFT)) {
            String command = this.SLmethods.get(event.getClickedInventory().getName());
            int slot = event.getSlot();
            int neededslot = ((Integer)this.SLslots.get(command)).intValue();
            command = command.replace("%player%", event.getWhoClicked().getName());
            command = command.replace("%location%", String.valueOf(Integer.toString(event.getWhoClicked().getLocation().getBlockX())) + " " + Integer.toString(event.getWhoClicked().getLocation().getBlockY()) + " " + Integer.toString(event.getWhoClicked().getLocation().getBlockZ()));
            if (slot == neededslot)
                if (command.startsWith("-console ")) {
                    Bukkit.getServer().dispatchCommand((CommandSender)Bukkit.getConsoleSender(), command.replace("-console ", ""));
                } else {
                    Player player = (Player)event.getWhoClicked();
                    player.performCommand(command);
                }
        } else if (event.getClick().equals(ClickType.SHIFT_RIGHT)) {
            String command = this.SRmethods.get(event.getClickedInventory().getName());
            int slot = event.getSlot();
            int neededslot = ((Integer)this.SRslots.get(command)).intValue();
            command = command.replace("%player%", event.getWhoClicked().getName());
            command = command.replace("%location%", String.valueOf(Integer.toString(event.getWhoClicked().getLocation().getBlockX())) + " " + Integer.toString(event.getWhoClicked().getLocation().getBlockY()) + " " + Integer.toString(event.getWhoClicked().getLocation().getBlockZ()));
            if (slot == neededslot)
                if (command.startsWith("-console ")) {
                    Bukkit.getServer().dispatchCommand((CommandSender)Bukkit.getConsoleSender(), command.replace("-console ", ""));
                } else {
                    Player player = (Player)event.getWhoClicked();
                    player.performCommand(command);
                }
        }
    }*/
}
