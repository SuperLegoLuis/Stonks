package sll.coding.stonks.API.ChatInput;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.HashMap;

public class ChatInput implements Listener {

    private static HashMap<Player, ChatInputCallback> playerChatInputCallbackHashMap = new HashMap<>();

    public static void getInput(Player player, ChatInputCallback callback) {
        playerChatInputCallbackHashMap.put(player, callback);
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        if (playerChatInputCallbackHashMap.containsKey(event.getPlayer())) {
            event.setCancelled(true);
            ChatInputCallback callback = playerChatInputCallbackHashMap.get(event.getPlayer());
            playerChatInputCallbackHashMap.remove(event.getPlayer());
            callback.message(event.getPlayer(), event.getMessage());
        }
    }

}
