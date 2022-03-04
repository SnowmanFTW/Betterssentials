package me.snowman.betterssentials.commands;

import me.snowman.betterssentials.files.LangManager;
import me.snowman.betterssentials.player.User;
import me.snowman.betterssentials.player.UserManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Message implements CommandExecutor {
    private final UserManager userManager;
    private final LangManager langManager;

    public Message(UserManager userManager, LangManager langManager){
        this.langManager = langManager;
        this.userManager = userManager;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)){
            sender.sendMessage(langManager.getMessage(null, "NoConsole"));
            return true;
        }
        if(args.length < 2){
            sender.sendMessage(langManager.getUsage("message", label));
            return true;
        }
        User user = userManager.getUser(sender);
        User receiver = userManager.getUser(args[0]);
        if(receiver.getName().equalsIgnoreCase(user.getName())){
            user.sendMessage(langManager.getMessage(user, "MessageYourself"));
            return true;
        }
        if(receiver.getPlayer() == null){
            user.sendMessage(langManager.getMessage(user, "PlayerNotOnline"));
            return true;
        }
        user.sendMessage(langManager.getMessage(user, "MessageSent").replace("%message%", String.join(" ", args)));
        receiver.sendMessage(langManager.getMessage(receiver, "MessageReceived").replace("%message%", String.join(" ", args)));
        return true;
    }
}
