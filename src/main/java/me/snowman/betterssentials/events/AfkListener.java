package me.snowman.betterssentials.events;

import me.snowman.betterssentials.files.LangManager;
import me.snowman.betterssentials.player.User;
import me.snowman.betterssentials.player.UserManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class AfkListener implements Listener {
    private final UserManager userManager;
    private final LangManager langManager;

    public AfkListener(UserManager userManager, LangManager langManager){
        this.userManager = userManager;
        this.langManager = langManager;
    }

    @EventHandler
    public void afkCheck(PlayerMoveEvent event){
        if(event.getTo() != null) {
            int movX = event.getFrom().getBlockX() - event.getTo().getBlockX();
            int movZ = event.getFrom().getBlockZ() - event.getTo().getBlockZ();
            if (Math.abs(movX) > 0 || Math.abs(movZ) > 0) {
                User user = userManager.getUser(event.getPlayer());
                if (user.isAfk()) {
                    user.setAfk(false);
                    user.sendMessage(langManager.getMessage(user, "IsNotAFK"));
                }
            }
        }
    }
}
