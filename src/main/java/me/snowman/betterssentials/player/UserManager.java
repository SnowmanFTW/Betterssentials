package me.snowman.betterssentials.player;

import me.snowman.betterssentials.files.LangManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.bukkit.Bukkit.getServer;

public class UserManager {
    private final List<User> users = new ArrayList<>();

    private final me.snowman.betterssentials.files.UserManager userManager;
    private final LangManager langManager;

    public UserManager(me.snowman.betterssentials.files.UserManager userManager, LangManager langManager){
        this.userManager = userManager;
        this.langManager = langManager;
    }

    public User getUser(Player player){
        for(User user: users){
            if(player.getUniqueId().equals(user.getUniqueId())){
                return user;
            }
        }
        return createUser(player);
    }

    public User getUser(String playerName){
        Player player = Bukkit.getPlayer(playerName);
        if(player == null) return createOfflineUser(Bukkit.getOfflinePlayer(playerName));
        for(User user: users){
            if(player.getUniqueId().equals(user.getUniqueId())){
                return user;
            }
        }
        return createUser(player);
    }

    public User getUser(CommandSender sender){
        if(sender instanceof Player){
            return getUser((Player) sender);
        }else{
            return getUser(sender.getName());
        }
    }

    public User createUser(Player player){
        User user = new User(player);
        if(!userManager.existsPlayer(player)) {
            userManager.setupPlayer(user);
            userManager.getPlayer(user).set("balance", 0);
            userManager.getPlayer(user).set("banned", false);
            userManager.savePlayer();
        }
        user.setBalance(userManager.getPlayer(user).getInt("balance"));
        user.setBanned(userManager.getPlayer(user).getBoolean("banned"));
        user.setBanMessage(userManager.getPlayer(user).getString("ban-message"));
        users.add(user);
        return user;
    }

    public User createOfflineUser(OfflinePlayer player){
        User user = new User(player.getName());
        if(!userManager.existsPlayer(player.getUniqueId())) {
            user.setBalance(0);
            user.setBanned(false);
            user.setBanMessage(" ");
        }else{
            user.setBalance(userManager.getPlayer(user).getInt("balance"));
            user.setBanned(userManager.getPlayer(user).getBoolean("banned"));
            user.setBanMessage(userManager.getPlayer(user).getString("ban-message"));
        }
        return user;
    }

    public void removeUser(User user){
        users.remove(user);
    }

    public void saveUser(User user){
        userManager.getPlayer(user).set("balance", user.getBalance());
        userManager.getPlayer(user).set("banned", user.isBanned());
        userManager.getPlayer(user).set("ban-message", user.getBanMessage());
        userManager.savePlayer();
    }

    public boolean existsUser(Player player){
        return userManager.existsPlayer(player);
    }

    public boolean existsUser(UUID uuid){ return userManager.existsPlayer(uuid); }

    public void announceMessage(String message){
        String finalMessage = ChatColor.translateAlternateColorCodes('&', message);
        getServer().getOnlinePlayers().forEach(player -> player.sendMessage(finalMessage));
        getServer().getConsoleSender().sendMessage(finalMessage);
    }

    public void announceUsersMessage(String message){
        String finalMessage = ChatColor.translateAlternateColorCodes('&', message);
        getServer().getOnlinePlayers().forEach(player -> player.sendMessage(finalMessage));
    }

    //Thanks to https://www.spigotmc.org/threads/converting-between-xp-and-levels.156592/ :)
    public int levelsToXp(int levels) {
        if (levels <= 16) {
            return (int) (Math.pow(levels, 2)+6*levels);
        } else if (levels >= 17 && levels <= 31) {
            return (int) (2.5*Math.pow(levels, 2)-40.5*levels+360);
        } else if (levels >= 32) {
            return (int) (4.5*Math.pow(levels, 2)-162.5*levels+2220);
        }
        return -1;
    }

    public User userCheck(CommandSender sender, String[] args, int argsIndex){
        User user = getUser(sender);
        if(args.length > argsIndex){
            user = getUser(args[argsIndex]);
        }
        if(user.getPlayer() == null){
            sender.sendMessage(langManager.getMessage(null, "PlayerNotOnline"));
            return null;
        }
        return user;
    }

}
