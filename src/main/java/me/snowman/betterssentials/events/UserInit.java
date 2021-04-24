package me.snowman.betterssentials.events;

import me.snowman.betterssentials.player.User;
import me.snowman.betterssentials.player.UserManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class UserInit implements Listener {
    private final UserManager userManager;

    public UserInit(UserManager userManager){
        this.userManager = userManager;
    }

    @EventHandler
    public void initUser(PlayerJoinEvent event){
        userManager.createUser(event.getPlayer());
    }

    @EventHandler
    public void removeUser(PlayerQuitEvent event){
        User user = userManager.getUser(event.getPlayer());
        userManager.removeUser(user);
    }
}
