package me.snowman.betterssentials.files;

import me.snowman.betterssentials.Betterssentials;
import me.snowman.betterssentials.player.User;
import me.snowman.betterssentials.player.UserManager;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.Objects;

public class LangManager {
    private final Betterssentials betterssentials;

    private YamlConfiguration fileCfg;
    public LangManager(Betterssentials betterssentials){
        this.betterssentials = betterssentials;
    }

    public void setupMessages(){
        final String lang = betterssentials.getConfig().getString("lang");
        if(!betterssentials.getDataFolder().exists()){
            boolean wasSuccessful = betterssentials.getDataFolder().mkdir();
            if(wasSuccessful) betterssentials.getServer().getConsoleSender().sendMessage("Betterssentials folder created!");
            else betterssentials.getServer().getConsoleSender().sendMessage("Betterssentials folder could not be created!");
        }

        File file = new File(betterssentials.getDataFolder(), "messages-" + lang + ".yml");
        if(!file.exists()){
            betterssentials.saveResource("messages-" + lang + ".yml", true);
            fileCfg = YamlConfiguration.loadConfiguration(file);
        }
        if(fileCfg == null) {
            fileCfg = YamlConfiguration.loadConfiguration(file);
        }
    }

    public YamlConfiguration getMessages(){
        return fileCfg;
    }

    public String getMessage(User user, String key){
        return replacePlaceholders(user ,getMessages().getString("Prefix") + getMessages().getString(key));
    }

    public String replacePlaceholders(User user, String message){
        //Player related placeholders
        if(user != null) {
            message = message.replace("%player%", user.getName());
            message = message.replace("%money%", String.valueOf(user.getBalance()));
            message = message.replace("%reason%", user.getBanMessage());
        }

        //Other placeholders
        message = message.replace("%money_sign%", Objects.requireNonNull(betterssentials.getConfig().getString("money-sign")));
        return ChatColor.translateAlternateColorCodes('&', message);
    }
}
