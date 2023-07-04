package me.snowman.betterssentials.commands;

import me.snowman.betterssentials.files.LangManager;
import me.snowman.betterssentials.player.User;
import me.snowman.betterssentials.player.UserManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.Arrays;

public class Mute implements CommandExecutor {
    private final UserManager userManager;
    private final LangManager langManager;

    public Mute(UserManager userManager, LangManager langManager){
        this.userManager = userManager;
        this.langManager = langManager;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(args.length < 1){ sender.sendMessage(langManager.getUsage("mute", label)); return true;}
        User user = userManager.userCheck(sender, args, 0);
        if(user == null) return true;

        if(args.length == 1){
            if(user.getMuteMessage() == null) user.setMuteMessage("");
            user.setMuteMessage(langManager.getPlainMessage("DefaultMute"));
        }else{
            String reason = String.join(" ", Arrays.stream(args).skip(1).toArray(String[]::new));
            System.out.println(langManager.getPlainMessage("YouMuted"));
            user.setMuteMessage(langManager.getPlainMessage("YouMuted").replace("%mreason%", reason));
        }
        if(user.getPlayer() != null) user.setMuted(true);
        userManager.announceMessage(langManager.getMessage(user, "MuteMessage"));
        userManager.saveUser(user);
        return true;
    }
}
