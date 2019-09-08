package ai.sek.esfores.items;

import org.bukkit.NamespacedKey;
import org.bukkit.event.EventHandler;
import org.bukkit.entity.Player;
import org.bukkit.entity.EntityType;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.Bukkit;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import java.util.Arrays;
import org.bukkit.ChatColor;
import org.bukkit.inventory.ItemStack;
import org.bukkit.Material;
import org.bukkit.plugin.java.JavaPlugin;
import ai.sek.esfores.lib.CustomItem;

public class ItemFeedingTube extends CustomItem {
    public ItemFeedingTube(final JavaPlugin basePlugin) {
        super(basePlugin, new ItemStack(Material.DIAMOND_HELMET));
        ItemMeta meta = this.baseItem.getItemMeta();
        assert meta != null;
        meta.setDisplayName(ChatColor.AQUA + "Feeding Tube");
        meta.setLore(Arrays.asList(ChatColor.LIGHT_PURPLE + "Contains an infinite supply of",
                ChatColor.LIGHT_PURPLE + "hot pockets"));
        this.baseItem.setItemMeta(meta);
    }
    
    @Override
    public void initialize() {
        NamespacedKey key = new NamespacedKey(this.plugin, "feeding_tube");
        ShapedRecipe recipe = new ShapedRecipe(key, this.baseItem);
        recipe.shape("***", "*H*", "***");
        recipe.setIngredient('*', Material.COOKED_BEEF);
        recipe.setIngredient('H', Material.DIAMOND_HELMET);
        Bukkit.addRecipe(recipe);
    }
    
    @EventHandler
    public void onFeed(final FoodLevelChangeEvent event) {
        if (!event.getEntity().getType().equals(EntityType.PLAYER)) {
            return;
        }
        Player p = (Player)event.getEntity();
        if (!this.isItem(p.getInventory().getHelmet())) {
            return;
        }
        p.setFoodLevel(20);
        p.setSaturation(1);
    }
}
