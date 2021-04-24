package me.snowman.betterssentials.events;

import me.snowman.betterssentials.player.User;
import me.snowman.betterssentials.player.UserManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class AfkListener implements Listener {
    private final UserManager userManager;

    public AfkListener(UserManager userManager){
        this.userManager = userManager;
    }

    @EventHandler
    public void afkCheck(PlayerMoveEvent event){
        User user = userManager.getUser(event.getPlayer());
        if(event.getTo() != null) {
            int movX = event.getFrom().getBlockX() - event.getTo().getBlockX();
            int movZ = event.getFrom().getBlockZ() - event.getTo().getBlockZ();
            if (Math.abs(movX) > 0 || Math.abs(movZ) > 0) {
                if (user.isAfk()) {
                    user.setAfk(false);
                    user.sendMessage("&4AFK activatedn't");
                }
            }
        }
    }
}
