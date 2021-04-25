package me.snowman.betterssentials.player;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class UserManager {
    private final List<User> users = new ArrayList<>();

    private final me.snowman.betterssentials.files.UserManager userManager;

    public UserManager(me.snowman.betterssentials.files.UserManager userManager){
        this.userManager = userManager;
    }

    public User getUser(Player player){
        for(User user: users){
            if(player.getUniqueId().equals(user.getUniqueId())){
                return user;
            }
        }
        return createUser(player);
    }

    public User getUser(String playerName){
        Player player = Bukkit.getPlayer(playerName);
        if(player == null) return null;
        for(User user: users){
            if(player.getUniqueId().equals(user.getUniqueId())){
                return user;
            }
        }
        return createUser(player);
    }

    public User createUser(Player player){
        User user = new User(player);
        users.add(user);
        if(!userManager.existsPlayer(player)) {
            userManager.setupPlayer(user);
            userManager.getPlayer(user).set("balance", 1);
            userManager.savePlayer();
        }
        user.setBalance(userManager.getPlayer(user).getInt("balance"));
        return user;
    }

    public void removeUser(User user){
        users.remove(user);
    }

    public boolean existsUser(Player player){
        return userManager.existsPlayer(player);
    }
}
