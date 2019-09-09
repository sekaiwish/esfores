package ai.sek.esfores.items;

import ai.sek.esfores.lib.CustomItem;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class ItemPoisonApple extends CustomItem {
    public ItemPoisonApple(final JavaPlugin basePlugin) {
        super(basePlugin, new ItemStack(Material.APPLE));
        ItemMeta meta = this.baseItem.getItemMeta();
        assert meta != null;
        meta.setDisplayName(ChatColor.GRAY + "Apple");
        this.baseItem.setItemMeta(meta);
    }

    @Override
    public void initialize() {
        NamespacedKey key = new NamespacedKey(this.plugin, "poison_apple");
        ShapelessRecipe recipe = new ShapelessRecipe(key, this.baseItem);
        recipe.addIngredient(Material.APPLE);
        recipe.addIngredient(Material.PUFFERFISH);
        Bukkit.addRecipe(recipe);
    }

    @EventHandler
    public void onRightClick(final PlayerItemConsumeEvent event) {
        if (!this.isItem(event.getItem())) {
            return;
        }
        Player player = event.getPlayer();
        int oldFood = player.getFoodLevel();
        player.setFoodLevel(Math.min(oldFood + 2, 20));
        player.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 300, 1));
        player.addPotionEffect(new PotionEffect(PotionEffectType.HUNGER, 300, 2));
        player.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 1200, 3));
        player.sendMessage("bruh moment");
    }
}
