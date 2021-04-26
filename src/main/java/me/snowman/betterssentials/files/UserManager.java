package me.snowman.betterssentials.files;

import me.snowman.betterssentials.Betterssentials;
import me.snowman.betterssentials.player.User;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class UserManager {
    private final Betterssentials betterssentials;

    private File file;
    private YamlConfiguration fileCfg;

    public UserManager(Betterssentials betterssentials){
        this.betterssentials = betterssentials;
    }

    public void setupPlayer(User user){
        File playersFolder = new File(betterssentials.getDataFolder(), "players" + File.separator);
        UUID uuid = user.getUniqueId();
        file = new File(betterssentials.getDataFolder(), "players" + File.separator + uuid + ".yml");
        if(!betterssentials.getDataFolder().exists()){
            boolean wasSuccessful = betterssentials.getDataFolder().mkdir();
            if(wasSuccessful) betterssentials.getServer().getConsoleSender().sendMessage("Betterssentials folder created!");
            else betterssentials.getServer().getConsoleSender().sendMessage("Betterssentials folder could not be created!");
        }
        if(!playersFolder.exists()){
            boolean wasSuccessful = playersFolder.mkdir();
            if(wasSuccessful) betterssentials.getServer().getConsoleSender().sendMessage("Players folder created!");
            else betterssentials.getServer().getConsoleSender().sendMessage("Players folder could not be created!");
        }

        if(!file.exists()){
            try{
                boolean wasSuccessful = file.createNewFile();
                if(wasSuccessful) betterssentials.getServer().getConsoleSender().sendMessage("Player file for " + user.getName() + " created!");
                else betterssentials.getServer().getConsoleSender().sendMessage("Player file for " + user.getName() + " could not be created!");
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        fileCfg = YamlConfiguration.loadConfiguration(file);
    }

    public FileConfiguration getPlayer(User user) {
        UUID uuid = user.getUniqueId();
        if (file == null || !file.getName().equalsIgnoreCase(uuid + ".yml") || fileCfg == null) {
            file = new File(betterssentials.getDataFolder(), "players" + File.separator + uuid + ".yml");
            fileCfg = YamlConfiguration.loadConfiguration(file);
        }
        return fileCfg;
    }

    public boolean existsPlayer(Player player){
        UUID uuid = player.getUniqueId();
        file = new File(betterssentials.getDataFolder(), "players" + File.separator + uuid + ".yml");
        return file.exists();
    }

    public boolean existsPlayer(UUID uuid){
        file = new File(betterssentials.getDataFolder(), "players" + File.separator + uuid + ".yml");
        return file.exists();
    }

    public void savePlayer() {
        try {
            fileCfg.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
