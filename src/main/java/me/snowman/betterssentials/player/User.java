package me.snowman.betterssentials.player;

import org.bukkit.OfflinePlayer;
import org.bukkit.conversations.Conversable;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.messaging.PluginMessageRecipient;

import java.util.UUID;

public class User {
    private final Player player;
    private boolean isAfk = false;

    public User(Player player){
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    public UUID getUniqueId(){
        return player.getUniqueId();
    }

    public boolean isAfk() {
        return isAfk;
    }

    public void setAfk(boolean afk) {
        isAfk = afk;
    }
}
