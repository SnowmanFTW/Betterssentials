package me.snowman.betterssentials.commands;

import me.snowman.betterssentials.Betterssentials;
import me.snowman.betterssentials.files.LangManager;
import me.snowman.betterssentials.player.User;
import me.snowman.betterssentials.player.UserManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ClearChat implements CommandExecutor {
    private final UserManager userManager;
    private final LangManager langManager;
    private final Betterssentials betterssentials;

    public ClearChat(UserManager userManager, LangManager langManager, Betterssentials betterssentials){
        this.userManager = userManager;
        this.langManager = langManager;
        this.betterssentials = betterssentials;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        User user = userManager.getUser(sender);
        int lines = betterssentials.getConfig().getInt("clear-chat-lines");
        if(args.length != 0){
            lines = Integer.parseInt(args[0]);
            if(!langManager.checkInt(args[0])){
                sender.sendMessage(langManager.getMessage(null, "NumberNotValid"));
                return true;
            }
        }
        for(int i = 0; i < lines; i++){
            userManager.announceUsersMessage(" ");
        }
        userManager.announceMessage(langManager.getMessage(user, "ClearedChat"));
        return true;
    }
}
