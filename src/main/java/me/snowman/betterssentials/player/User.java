package me.snowman.betterssentials.player;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.conversations.Conversable;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.messaging.PluginMessageRecipient;

import java.util.Locale;
import java.util.UUID;

public class User {
    private final Player player;
    private final String name;
    private final UUID uuid;
    private boolean isAfk = false;

    private User lastMessage;
    private int balance = 0;
    private boolean banned = false;
    private String banMessage = "";

    public User(Player player){
        this.player = player;
        this.uuid = player.getUniqueId();
        this.name = player.getName();
    }

    public User(String playerName){
        this.player = Bukkit.getPlayer(playerName);
        this.uuid = Bukkit.getOfflinePlayer(playerName).getUniqueId();
        this.name = playerName;
    }

    public User(CommandSender sender){
        if(sender instanceof Player){
            this.player = (Player) sender;
            this.uuid = player.getUniqueId();
        }else{
            this.player = null;
            this.uuid = null;
        }
        this.name = sender.getName();
    }

    public Player getPlayer() {
        return player;
    }

    public UUID getUniqueId(){
        return uuid;
    }

    public String getName(){
        return name;
    }

    public boolean isAfk() {
        return isAfk;
    }

    public void sendMessage(String message){
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
    }

    public void setAfk(boolean afk) {
        isAfk = afk;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public boolean isBanned() {
        return banned;
    }

    public void setBanned(boolean banned) {
        this.banned = banned;
    }

    public String getBanMessage() {
        return banMessage;
    }

    public void setBanMessage(String banMessage) {
        this.banMessage = banMessage;
    }

    public void resetXp(){
        player.setExp(0);
        player.setLevel(0);
        player.setTotalExperience(0);
    }

    public String getGamemode(){
        return player.getGameMode().name().toLowerCase();
    }

    public User getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(User lastMessage) {
        this.lastMessage = lastMessage;
    }
}
