package me.snowman.betterssentials.player;

import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class UserManager {
    private final List<User> users = new ArrayList<>();

    public User getUser(Player player){
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
        return user;
    }
}
