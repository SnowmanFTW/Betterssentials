package me.snowman.betterssentials.commands;

import me.snowman.betterssentials.files.LangManager;
import me.snowman.betterssentials.player.User;
import me.snowman.betterssentials.player.UserManager;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Gamemode implements CommandExecutor {
    private final UserManager userManager;
    private final LangManager langManager;
    public Gamemode(UserManager userManager, LangManager langManager){
        this.userManager = userManager;
        this.langManager = langManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(args.length < 1){ sender.sendMessage(langManager.getUsage("gamemode", label)); return true;}
        User user = userManager.userCheck(sender, args);
        if(user == null) return true;

        switch (args[0]){
            case "0":
            case "survival":
            case "s":
                user.getPlayer().setGameMode(GameMode.SURVIVAL);
                break;
        }
        user.sendMessage(langManager.getMessage(user, "GmSet"));
        return true;
    }
}
