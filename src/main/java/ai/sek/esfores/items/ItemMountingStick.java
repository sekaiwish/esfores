package ai.sek.esfores.items;

import org.bukkit.NamespacedKey;
import org.bukkit.event.EventHandler;
import org.bukkit.entity.Player;
import org.bukkit.entity.EntityType;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.Bukkit;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import java.util.ArrayList;
import java.util.List;
import org.bukkit.ChatColor;
import org.bukkit.inventory.ItemStack;
import org.bukkit.Material;
import org.bukkit.plugin.java.JavaPlugin;
import ai.sek.esfores.lib.CustomItem;

public class ItemMountingStick extends CustomItem {
    public ItemMountingStick(final JavaPlugin basePlugin) {
        super(basePlugin, new ItemStack(Material.STICK));
        ItemMeta meta = this.baseItem.getItemMeta();
        assert meta != null;
        meta.setDisplayName(ChatColor.AQUA + "Mounting" + ChatColor.RESET + " " + ChatColor.LIGHT_PURPLE + "Stick");
        List<String> lore = new ArrayList<String>();
        lore.add(ChatColor.LIGHT_PURPLE + "It's not gay unless the");
        lore.add(ChatColor.LIGHT_PURPLE + "balls touch!");
        meta.setLore(lore);
        this.baseItem.setItemMeta(meta);
    }
    
    @Override
    public void initialize() {
        NamespacedKey key = new NamespacedKey(this.plugin, "mounting_stick");
        ShapelessRecipe recipe = new ShapelessRecipe(key, this.baseItem);
        recipe.addIngredient(Material.SADDLE);
        recipe.addIngredient(Material.STICK);
        Bukkit.addRecipe(recipe);
    }
    
    @EventHandler
    public void onEntityInteract(final PlayerInteractEntityEvent event) {
        if (!event.getRightClicked().getType().equals(EntityType.PLAYER)) {
            return;
        }
        final Player clicked = (Player)event.getRightClicked();
        final Player clicker = event.getPlayer();
        if (!this.isItem(clicker.getInventory().getItemInMainHand())) {
            return;
        }
        clicked.addPassenger(clicker);
    }
}
