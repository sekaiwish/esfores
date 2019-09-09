package ai.sek.esfores;

import ai.sek.esfores.items.ItemFeedingTube;
import ai.sek.esfores.items.ItemKek;
import ai.sek.esfores.items.ItemMemeArrow;
import ai.sek.esfores.items.ItemMountingStick;
import ai.sek.esfores.items.ItemPoisonApple;
import ai.sek.esfores.items.ItemTopKek;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.java.JavaPlugin;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public final class esfores extends JavaPlugin implements Listener {
    private File configFile;
    private FileConfiguration config;
    List<String> fortunes = new ArrayList<String>();

    public void onEnable() {
        configFile = new File(getDataFolder(), "config.yml");
        try {
            firstRun();
        } catch (Exception e) {
            e.printStackTrace();
        }
        config = new YamlConfiguration();
        loadConfig();
        this.getServer().getPluginManager().registerEvents(this, this);
        new ItemFeedingTube(this).initialize();
        new ItemKek(this).initialize();
        new ItemMemeArrow(this).initialize();
        new ItemMountingStick(this).initialize();
        new ItemPoisonApple(this).initialize();
        new ItemTopKek(this).initialize();
    }

    public void onDisable() {
        saveConfig();
    }

    private void firstRun() throws Exception {
        if(!configFile.exists()){
            configFile.getParentFile().mkdirs();
            copy(getResource("config.yml"), configFile);
            config.createSection("message-id");
            config.set("message-id", 0);
        }
    }

    private void copy(InputStream in, File file) {
        try {
            OutputStream out = new FileOutputStream(file);
            byte[] buf = new byte[1024];
            int len;
            while((len = in.read(buf)) > 0){
                out.write(buf,0, len);
            }
            out.close();
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadConfig() {
        try {
            config.load(configFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveConfig() {
        try {
            config.save(configFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @EventHandler
    public void onPlayerChat(final AsyncPlayerChatEvent e) {
        config.set("message-id", config.getInt("message-id") + 1);
        if (e.getMessage().startsWith(">")) {
            e.setMessage(ChatColor.GREEN + e.getMessage());
        }
        e.setFormat(ChatColor.DARK_GREEN + "%s" + ChatColor.RESET + " No. " + config.getString("message-id") + ": %s");
    }
}