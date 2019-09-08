package ai.sek.esfores.items;

import org.bukkit.NamespacedKey;
import org.bukkit.event.EventHandler;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.Bukkit;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import java.util.ArrayList;
import java.util.List;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;
import ai.sek.esfores.lib.CustomItem;

public class ItemKek extends CustomItem {
    public ItemKek(final JavaPlugin basePlugin) {
        super(basePlugin, new ItemStack(Material.COOKIE));
        ItemMeta meta = this.baseItem.getItemMeta();
        assert meta != null;
        meta.setDisplayName(ChatColor.LIGHT_PURPLE + "Kek");
        List<String> lore = new ArrayList<String>();
        lore.add(ChatColor.GOLD + "Sugar rush!");
        meta.setLore(lore);
        this.baseItem.setItemMeta(meta);
    }
    
    @Override
    public void initialize() {
        NamespacedKey key = new NamespacedKey(this.plugin, "cookie_kek");
        ShapedRecipe recipe = new ShapedRecipe(key, this.baseItem);
        recipe.shape("***", "*C*", "***");
        recipe.setIngredient('*', Material.GOLD_NUGGET);
        recipe.setIngredient('C', Material.COOKIE);
        Bukkit.addRecipe(recipe);
    }
    
    @EventHandler
    public void onRightClick(final PlayerItemConsumeEvent event) {
        if (!this.isItem(event.getItem())) {
            return;
        }
        Player player = event.getPlayer();
        int oldFood = player.getFoodLevel();
        player.setFoodLevel(Math.min(oldFood + 6, 20));
        player.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 600, 3));
        player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 600, 3));
    }
}
