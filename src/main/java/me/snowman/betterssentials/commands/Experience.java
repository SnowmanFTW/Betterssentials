package me.snowman.betterssentials.commands;

import me.snowman.betterssentials.files.LangManager;
import me.snowman.betterssentials.player.User;
import me.snowman.betterssentials.player.UserManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class Experience implements CommandExecutor {
    private final UserManager userManager;
    private final LangManager langManager;

    public Experience(UserManager userManager, LangManager langManager){
        this.userManager = userManager;
        this.langManager = langManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(args.length < 3){ sender.sendMessage(langManager.getUsage("experience", label)); return true;}
        User user = userManager.userCheck(sender, args, 0);
        if(user == null) return true;

        String action = args[1];
        if(!langManager.checkInt(args[2]) && !args[2].contains("L")){
            sender.sendMessage(langManager.getMessage(null, "NumberNotValid"));
            return true;
        }
        int amount = Integer.parseInt(args[2].replace("L", ""));
        switch (action){
            case "set":
                user.resetXp();
                if(args[2].contains("L")){
                    user.getPlayer().giveExp(userManager.levelsToXp(amount));
                    user.getPlayer().setLevel(amount);
                }else {
                    user.getPlayer().giveExp(amount);
                }
                user.sendMessage(langManager.getMessage(user, "XpNow"));
                break;
            case "give":
                if(args[2].contains("L")){
                    user.getPlayer().giveExp(userManager.levelsToXp(amount));
                    user.getPlayer().setLevel(user.getPlayer().getLevel() + amount);
                }else {
                    user.getPlayer().giveExp(amount);
                }
                user.sendMessage(langManager.getMessage(user, "XpNow"));
                break;
            default:
                sender.sendMessage(langManager.getMessage(null, "ExperienceUsage").replace("%command%", label));
                break;
        }
        return true;
    }
}
