package me.snowman.betterssentials.events;

import me.snowman.betterssentials.files.LangManager;
import me.snowman.betterssentials.player.User;
import me.snowman.betterssentials.player.UserManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class MuteListener implements Listener {

    private final UserManager userManager;
    private final LangManager langManager;

    public MuteListener(UserManager userManager, LangManager langManager){
        this.userManager = userManager;
        this.langManager = langManager;
    }

    @EventHandler
    public void checkMute(AsyncPlayerChatEvent event){
        User user = userManager.getUser(event.getPlayer());
        if(user.getPlayer() == null) return;
        if(!user.isMuted()) return;
        event.setCancelled(true);
        user.sendMessage(langManager.getMessage(user, "YouMuted").replace("%mreason%", user.getMuteMessage()));
    }
}
