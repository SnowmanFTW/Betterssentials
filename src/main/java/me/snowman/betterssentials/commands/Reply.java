package me.snowman.betterssentials.commands;

import me.snowman.betterssentials.files.LangManager;
import me.snowman.betterssentials.player.User;
import me.snowman.betterssentials.player.UserManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;

public class Reply implements CommandExecutor {
    private final UserManager userManager;
    private final LangManager langManager;

    public Reply(UserManager userManager, LangManager langManager){
        this.userManager = userManager;
        this.langManager = langManager;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)){
            sender.sendMessage(langManager.getMessage(null, "NoConsole"));
            return true;
        }
        if(args.length < 1){
            sender.sendMessage(langManager.getUsage("reply", label));
            return true;
        }
        User user = userManager.getUser(sender);
        User receiver = user.getLastMessage();
        if(receiver == null){
            user.sendMessage(langManager.getMessage(user, "NoReply"));
            return true;
        }
        if(receiver.getPlayer() == null){
            user.sendMessage(langManager.getMessage(user, "PlayerNotOnline"));
            return true;
        }
        user.sendMessage(langManager.getMessage(receiver, "MessageSent").replace("%message%", String.join(" ", args)));
        receiver.sendMessage(langManager.getMessage(user, "MessageReceived").replace("%message%", String.join(" ", args)));
        return true;
    }
}
