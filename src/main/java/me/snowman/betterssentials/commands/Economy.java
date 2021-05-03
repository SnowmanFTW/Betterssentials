package me.snowman.betterssentials.commands;

import me.snowman.betterssentials.files.LangManager;
import me.snowman.betterssentials.player.User;
import me.snowman.betterssentials.player.UserManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class Economy implements CommandExecutor {
    private final UserManager userManager;
    private final LangManager langManager;

    public Economy(UserManager userManager, LangManager langManager){
        this.userManager = userManager;
        this.langManager = langManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(args.length < 3){ sender.sendMessage(langManager.getUsage("economy", label)); return true;}
        User user = userManager.userCheck(sender, args);
        if(user == null) return true;

        String action = args[1];
        if(!langManager.checkInt(args[2])){
            sender.sendMessage(langManager.getMessage(null, "NumberNotValid"));
            return true;
        }
        int amount = Integer.parseInt(args[2]);
        switch (action){
            case "set":
                user.setBalance(amount);
                sender.sendMessage(langManager.getMessage(user, "EconomySetP"));
                user.sendMessage(langManager.getMessage(user, "EconomySetT"));
                break;
            case "give":
                user.setBalance(user.getBalance() + amount);
                sender.sendMessage(langManager.getMessage(user, "EconomyGiveP").replace("%added%", String.valueOf(amount)));
                user.sendMessage(langManager.getMessage(user, "EconomyGiveT").replace("%added%", String.valueOf(amount)));
                break;
            case "remove":
                user.setBalance(user.getBalance() - amount);
                sender.sendMessage(langManager.getMessage(user, "EconomyTakeP").replace("%removed%", String.valueOf(amount)));
                user.sendMessage(langManager.getMessage(user, "EconomyTakeT").replace("%removed%", String.valueOf(amount)));
                break;
            default:
                sender.sendMessage(langManager.getMessage(null, "EconomyUsage").replace("%command%", label));
                break;
        }
        return true;
    }
}
