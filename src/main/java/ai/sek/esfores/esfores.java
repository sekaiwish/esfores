package ai.sek.esfores;

import ai.sek.esfores.items.ItemFeedingTube;
import ai.sek.esfores.items.ItemKek;
import ai.sek.esfores.items.ItemMemeArrow;
import ai.sek.esfores.items.ItemMountingStick;
import ai.sek.esfores.items.ItemTopKek;
import org.bukkit.plugin.java.JavaPlugin;

public final class esfores extends JavaPlugin {
    public void onEnable() {
        new ItemFeedingTube(this).initialize();
        new ItemKek(this).initialize();
        new ItemMemeArrow(this).initialize();
        new ItemMountingStick(this).initialize();
        new ItemTopKek(this).initialize();
    }

    public void onDisable() {}
}