package me.snowman.betterssentials.utils;

import me.snowman.betterssentials.Betterssentials;
import me.snowman.betterssentials.player.User;
import me.snowman.betterssentials.player.UserManager;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.plugin.ServicePriority;

import java.util.List;

public class EconomyUtils implements Economy {
    private final Betterssentials betterssentials;
    private final UserManager userManager;

    public EconomyUtils(Betterssentials betterssentials, UserManager userManager){
        this.betterssentials = betterssentials;
        this.userManager = userManager;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String getName() {
        return "Betterssentials";
    }

    @Override
    public boolean hasBankSupport() {
        return false;
    }

    @Override
    public int fractionalDigits() {
        return 1;
    }

    @Override
    public String format(double amount) {
        return String.valueOf(amount);
    }

    @Override
    public String currencyNamePlural() {
        return betterssentials.getConfig().getString("money-sign");
    }

    @Override
    public String currencyNameSingular() {
        return betterssentials.getConfig().getString("money-sign");
    }

    @Override
    public boolean hasAccount(String playerName) {
        User user = userManager.getUser(playerName);
        return userManager.existsUser(user.getPlayer());
    }

    @Override
    public boolean hasAccount(OfflinePlayer player) {
        return userManager.existsUser(player.getUniqueId());
    }

    @Override
    public boolean hasAccount(String playerName, String worldName) {
        return false;
    }

    @Override
    public boolean hasAccount(OfflinePlayer player, String worldName) {
        return false;
    }

    @Override
    public double getBalance(String playerName) {
        User user = userManager.getUser(playerName);
        return user.getBalance();
    }

    @Override
    public double getBalance(OfflinePlayer player) {
        return userManager.createOfflineUser(player).getBalance();
    }

    @Override
    public double getBalance(String playerName, String world) {
        return 0;
    }

    @Override
    public double getBalance(OfflinePlayer player, String world) {
        return 0;
    }

    @Override
    public boolean has(String playerName, double amount) {
        User user = userManager.getUser(playerName);
        int balance = user.getBalance();
        return balance >= amount;
    }

    @Override
    public boolean has(OfflinePlayer player, double amount) {
        User user = userManager.createOfflineUser(player);
        int balance = user.getBalance();
        return balance >= amount;
    }

    @Override
    public boolean has(String playerName, String worldName, double amount) {
        return false;
    }

    @Override
    public boolean has(OfflinePlayer player, String worldName, double amount) {
        return false;
    }

    @Override
    public EconomyResponse withdrawPlayer(String playerName, double amount) {
        User user = userManager.getUser(playerName);
        int balance = (int) (user.getBalance() - amount);
        EconomyResponse response;
        if(balance < 0){
            response = new EconomyResponse(amount, user.getBalance(), EconomyResponse.ResponseType.FAILURE, "Not enough money");
        }else{
            response = new EconomyResponse(amount, user.getBalance(), EconomyResponse.ResponseType.SUCCESS, "Success");
            user.setBalance(balance);
            response.transactionSuccess();
        }
        return response;
    }

    @Override
    public EconomyResponse withdrawPlayer(OfflinePlayer player, double amount) {
        User user = userManager.createOfflineUser(player);
        int balance = (int) (user.getBalance() - amount);
        EconomyResponse response;
        if(balance < 0){
            response = new EconomyResponse(amount, user.getBalance(), EconomyResponse.ResponseType.FAILURE, "Not enough money");
        }else{
            response = new EconomyResponse(amount, user.getBalance(), EconomyResponse.ResponseType.SUCCESS, "Success");
            user.setBalance(balance);
            response.transactionSuccess();
        }
        return response;
    }

    @Override
    public EconomyResponse withdrawPlayer(String playerName, String worldName, double amount) {
        return new EconomyResponse(amount, 0, EconomyResponse.ResponseType.NOT_IMPLEMENTED, "Not implemented");
    }

    @Override
    public EconomyResponse withdrawPlayer(OfflinePlayer player, String worldName, double amount) {
        return new EconomyResponse(amount, 0, EconomyResponse.ResponseType.NOT_IMPLEMENTED, "Not implemented");
    }

    @Override
    public EconomyResponse depositPlayer(String playerName, double amount) {
        User user = userManager.getUser(playerName);
        int balance = (int) (user.getBalance() + amount);
        EconomyResponse response;
        response = new EconomyResponse(amount, user.getBalance(), EconomyResponse.ResponseType.SUCCESS, "Success");
        user.setBalance(balance);
        response.transactionSuccess();
        return response;
    }

    @Override
    public EconomyResponse depositPlayer(OfflinePlayer player, double amount) {
        User user = userManager.createOfflineUser(player);
        int balance = (int) (user.getBalance() + amount);
        EconomyResponse response;
        response = new EconomyResponse(amount, user.getBalance(), EconomyResponse.ResponseType.SUCCESS, "Success");
        user.setBalance(balance);
        response.transactionSuccess();
        return response;
    }

    @Override
    public EconomyResponse depositPlayer(String playerName, String worldName, double amount) {
        return null;
    }

    @Override
    public EconomyResponse depositPlayer(OfflinePlayer player, String worldName, double amount) {
        return null;
    }

    @Override
    public EconomyResponse createBank(String name, String player) {
        return null;
    }

    @Override
    public EconomyResponse createBank(String name, OfflinePlayer player) {
        return null;
    }

    @Override
    public EconomyResponse deleteBank(String name) {
        return null;
    }

    @Override
    public EconomyResponse bankBalance(String name) {
        return null;
    }

    @Override
    public EconomyResponse bankHas(String name, double amount) {
        return null;
    }

    @Override
    public EconomyResponse bankWithdraw(String name, double amount) {
        return null;
    }

    @Override
    public EconomyResponse bankDeposit(String name, double amount) {
        return null;
    }

    @Override
    public EconomyResponse isBankOwner(String name, String playerName) {
        return null;
    }

    @Override
    public EconomyResponse isBankOwner(String name, OfflinePlayer player) {
        return null;
    }

    @Override
    public EconomyResponse isBankMember(String name, String playerName) {
        return null;
    }

    @Override
    public EconomyResponse isBankMember(String name, OfflinePlayer player) {
        return null;
    }

    @Override
    public List<String> getBanks() {
        return null;
    }

    @Override
    public boolean createPlayerAccount(String playerName) {
        return false;
    }

    @Override
    public boolean createPlayerAccount(OfflinePlayer player) {
        return false;
    }

    @Override
    public boolean createPlayerAccount(String playerName, String worldName) {
        return false;
    }

    @Override
    public boolean createPlayerAccount(OfflinePlayer player, String worldName) {
        return false;
    }

    public void hook() {
        betterssentials.getServer().getServicesManager().register(Economy.class, this, betterssentials, ServicePriority.Highest);
        betterssentials.getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&6Vault hooked into &cBetterssentials"));

    }

    public void unhook() {
        betterssentials.getServer().getServicesManager().unregister(Economy.class, this);
        betterssentials.getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&6Vault unhooked into &cBetterssentials"));
    }
}
