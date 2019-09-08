package ai.sek.esfores.lib;

import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.Bukkit;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.event.Listener;

public abstract class CustomItem implements Listener {
    protected final JavaPlugin plugin;
    protected final ItemStack baseItem;

    public CustomItem(final JavaPlugin basePlugin, final ItemStack item) {
        this.plugin = basePlugin;
        this.baseItem = item;
        Bukkit.getPluginManager().registerEvents(this, basePlugin);
    }

    public abstract void initialize();

    public boolean isItem(final ItemStack stack) {
        if (stack == null) {
            return false;
        }
        final ItemMeta ourMeta = this.baseItem.getItemMeta();
        final ItemMeta stackMeta = stack.getItemMeta();
        assert ourMeta != null;
        assert stackMeta != null;
        return ourMeta.getDisplayName().equals(stackMeta.getDisplayName()) && this.baseItem.getType().equals(stack.getType());
    }
}
