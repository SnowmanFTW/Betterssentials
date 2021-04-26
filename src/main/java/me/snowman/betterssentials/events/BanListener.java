package me.snowman.betterssentials.events;

import me.snowman.betterssentials.player.User;
import me.snowman.betterssentials.player.UserManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class BanListener implements Listener {
    private final UserManager userManager;

    public BanListener(UserManager userManager){
        this.userManager = userManager;
    }

    @EventHandler
    public void banJoin(AsyncPlayerPreLoginEvent event){
        User user = userManager.getUser(event.getName());
        if(user.isBanned()){
            event.disallow(AsyncPlayerPreLoginEvent.Result.KICK_BANNED, user.getBanMessage());
        }
    }
}
