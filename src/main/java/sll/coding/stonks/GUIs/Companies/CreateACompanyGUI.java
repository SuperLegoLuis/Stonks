package sll.coding.stonks.GUIs.Companies;

import com.google.common.collect.Lists;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import sll.coding.stonks.API.ChatInput.ChatInput;
import sll.coding.stonks.API.ChatInput.ChatInputCallback;
import sll.coding.stonks.API.GUICreator;
import sll.coding.stonks.API.Objects.Company;
import sll.coding.stonks.API.StonkUtils;
import sll.coding.stonks.Data.DataUtils;
import sll.coding.stonks.Main;

import java.util.HashMap;

public class CreateACompanyGUI implements Listener {

    private static GUICreator guicreator = new GUICreator();

    private static HashMap<Player, String> name = new HashMap<>();
    private static HashMap<Player, String> symbol = new HashMap<>();
    private static HashMap<Player, String> price = new HashMap<>();
    private static HashMap<Player, String> icon = new HashMap<>();
    private static HashMap<Player, Boolean> answering = new HashMap<>();
    private static String title = ChatColor.DARK_AQUA + "Create a company";

    private Inventory i;

    public CreateACompanyGUI() {
        Bukkit.getPluginManager().registerEvents(this, Main.getPlugin(Main.class));
    }

    public Inventory get(Player player) {
        i = guicreator.createInventory(title, 27);

        i.setItem(10, guicreator.createItem(ChatColor.DARK_AQUA + "Set Name", Material.OAK_SIGN));
        i.setItem(12, guicreator.createItem(ChatColor.DARK_AQUA + "Set Symbol", Material.ANVIL));
        i.setItem(14, guicreator.createItem(ChatColor.DARK_AQUA + "Set Base Stock Price", Material.WRITABLE_BOOK));
        i.setItem(16, guicreator.createItem(ChatColor.DARK_AQUA + "Set Icon", Material.BLUE_CONCRETE));

        i.setItem(22, guicreator.createItem(ChatColor.DARK_AQUA + "Create", Material.LIME_STAINED_GLASS));

        return i;
    }

    public Inventory getNew(Player player) {
        return i;
    }

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        if (event.getView().getTitle().equals(title)) {
            event.setCancelled(true);
            if (event.getSlot() == 10) {
                answering.put((Player) event.getWhoClicked(), true);
                event.getWhoClicked().closeInventory();
                ((Player) event.getWhoClicked()).sendTitle(ChatColor.GREEN + "Please type your company's name in chat", "");
                ChatInput.getInput((Player) event.getWhoClicked(), new ChatInputCallback() {
                    @Override
                    public void message(Player player, String message) {
                        name.put(player, message);
                        answering.put((Player) event.getWhoClicked(), false);
                        ItemStack nameItem = i.getItem(10);
                        ItemMeta nameMeta = nameItem.getItemMeta();
                        nameMeta.setLore(Lists.newArrayList(ChatColor.RESET + "Current name: " + message));
                        nameItem.setItemMeta(nameMeta);
                        i.setItem(10, nameItem);
                        Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getPlugin(Main.class), () -> event.getWhoClicked().openInventory(getNew((Player) event.getWhoClicked())));
                    }
                });
            }
            else if (event.getSlot() == 12) {
                answering.put((Player) event.getWhoClicked(), true);
                event.getWhoClicked().closeInventory();
                ((Player) event.getWhoClicked()).sendTitle(ChatColor.GREEN + "Please type your company's symbol in chat", "");
                ChatInput.getInput((Player) event.getWhoClicked(), new ChatInputCallback() {
                    @Override
                    public void message(Player player, String message) {
                        symbol.put(player, message);
                        answering.put((Player) event.getWhoClicked(), false);
                        ItemStack nameItem = i.getItem(12);
                        ItemMeta nameMeta = nameItem.getItemMeta();
                        nameMeta.setLore(Lists.newArrayList(ChatColor.RESET + "Current symbol: " + message.toUpperCase()));
                        nameItem.setItemMeta(nameMeta);
                        i.setItem(12, nameItem);
                        Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getPlugin(Main.class), () -> event.getWhoClicked().openInventory(getNew((Player) event.getWhoClicked())));
                    }
                });
            }
            else if (event.getSlot() == 14) {
                answering.put((Player) event.getWhoClicked(), true);
                event.getWhoClicked().closeInventory();
                ((Player) event.getWhoClicked()).sendTitle(ChatColor.GREEN + "Please type your company's base stock price in chat", "");
                ChatInput.getInput((Player) event.getWhoClicked(), new ChatInputCallback() {
                    @Override
                    public void message(Player player, String message) {
                        price.put(player, message);
                        answering.put((Player) event.getWhoClicked(), false);
                        ItemStack nameItem = i.getItem(14);
                        ItemMeta nameMeta = nameItem.getItemMeta();
                        nameMeta.setLore(Lists.newArrayList(ChatColor.RESET + "Current price: " + message));
                        nameItem.setItemMeta(nameMeta);
                        i.setItem(14, nameItem);
                        Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getPlugin(Main.class), () -> event.getWhoClicked().openInventory(getNew((Player) event.getWhoClicked())));
                    }
                });
            }
            else if (event.getSlot() == 16) {
                answering.put((Player) event.getWhoClicked(), true);
                event.getWhoClicked().closeInventory();
                try {
                    ((Player) event.getWhoClicked()).sendTitle(ChatColor.GREEN + "Please type your company's icon in chat", ChatColor.GOLD + "Example: BEDROCK");
                    ChatInput.getInput((Player) event.getWhoClicked(), new ChatInputCallback() {
                        @Override
                        public void message(Player player, String message) {
                            icon.put((Player) event.getWhoClicked(), message.toUpperCase());
                            answering.put((Player) event.getWhoClicked(), false);
                            ItemStack nameItem = i.getItem(16);
                            ItemMeta nameMeta = nameItem.getItemMeta();
                            nameMeta.setLore(Lists.newArrayList(ChatColor.RESET + "Current icon: " + message.toUpperCase()));
                            nameItem.setItemMeta(nameMeta);
                            i.setItem(16, nameItem);
                            Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getPlugin(Main.class), () -> event.getWhoClicked().openInventory(getNew((Player) event.getWhoClicked())));
                        }
                    });
                }
                catch (Exception e) {
                    ((Player) event.getWhoClicked()).sendTitle(ChatColor.GREEN + "Please type your company's icon in chat", ChatColor.GOLD + "Example: BEDROCK");
                    ChatInput.getInput((Player) event.getWhoClicked(), new ChatInputCallback() {
                        @Override
                        public void message(Player player, String message) {
                            icon.put((Player) event.getWhoClicked(), message.toUpperCase());
                            answering.put((Player) event.getWhoClicked(), false);
                            ItemStack nameItem = i.getItem(16);
                            ItemMeta nameMeta = nameItem.getItemMeta();
                            nameMeta.setLore(Lists.newArrayList(ChatColor.RESET + "Current icon: " + message.toUpperCase()));
                            nameItem.setItemMeta(nameMeta);
                            i.setItem(16, nameItem);
                            Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getPlugin(Main.class), () -> event.getWhoClicked().openInventory(getNew((Player) event.getWhoClicked())));
                        }
                    });
                }
            }
            else if (event.getSlot() == 22) {
                if (name.containsKey(event.getWhoClicked()) && symbol.containsKey(event.getWhoClicked()) && price.containsKey(event.getWhoClicked()) && icon.containsKey(event.getWhoClicked())) {
                    int p = Integer.parseInt(price.get(event.getWhoClicked()));
                    if (DataUtils.econ.getBalance((OfflinePlayer) event.getWhoClicked()) >= 25000 + p) {
                        DataUtils.econ.withdrawPlayer((OfflinePlayer) event.getWhoClicked(), 25000 + p);
                        Company com = DataUtils.createCompany(name.get(event.getWhoClicked()), symbol.get(event.getWhoClicked()), p, (Player) event.getWhoClicked(), icon.get(event.getWhoClicked()));
                        name.remove(event.getWhoClicked());
                        symbol.remove(event.getWhoClicked());
                        price.remove(event.getWhoClicked());
                        event.getWhoClicked().closeInventory();
                        event.getWhoClicked().sendMessage(ChatColor.GREEN + "Company successfully created.");
                        event.getWhoClicked().getInventory().addItem(StonkUtils.getStonk(com));
                        HandlerList.unregisterAll(this);
                    }
                    else {
                        event.getWhoClicked().closeInventory();
                        HandlerList.unregisterAll(this);
                        int required = 25000 + p;
                        event.getWhoClicked().sendMessage(ChatColor.RED + "You do not have enough money to do this. You need " + required);
                    }
                }
            }
        }
    }

    @EventHandler
    public void onClose(InventoryCloseEvent event) {
        if (event.getView().getTitle().equals(title) && !answering.get(event.getPlayer())) {
            HandlerList.unregisterAll(this);
        }
    }

}
