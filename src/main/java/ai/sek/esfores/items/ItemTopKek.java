package ai.sek.esfores.items;

import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentWrapper;
import org.bukkit.event.EventHandler;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.Bukkit;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import java.util.ArrayList;
import java.util.List;
import org.bukkit.ChatColor;
import org.bukkit.inventory.ItemStack;
import org.bukkit.Material;
import org.bukkit.plugin.java.JavaPlugin;
import ai.sek.esfores.lib.CustomItem;

public class ItemTopKek extends CustomItem {
    public ItemTopKek(final JavaPlugin basePlugin) {
        super(basePlugin, new ItemStack(Material.COOKIE));
        this.baseItem.setAmount(5);
        ItemMeta meta = this.baseItem.getItemMeta();
        assert meta != null;
        meta.setDisplayName(ChatColor.AQUA + "Top Kek");
        meta.addEnchant(Enchantment.LUCK, 0, true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        List<String> lore = new ArrayList<String>();
        lore.add(ChatColor.GOLD + "Tip Top Kek, straight from the Himalayas");
        meta.setLore(lore);
        this.baseItem.setItemMeta(meta);
    }
    
    @Override
    public void initialize() {
        NamespacedKey key = new NamespacedKey(this.plugin, "cookie_topkek");
        ShapedRecipe recipe = new ShapedRecipe(key, this.baseItem);
        recipe.shape(" * ", "*C*", " * ");
        recipe.setIngredient('*', Material.GOLD_BLOCK);
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
        player.setFoodLevel(Math.min(oldFood + 16, 20));
        float oldSaturation = player.getSaturation();
        player.setSaturation(Math.min(oldSaturation + 6, 12));
        player.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 1200, 3));
        player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 1200, 5));
        player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 1200, 1));
        player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 100, 10));
    }
}
