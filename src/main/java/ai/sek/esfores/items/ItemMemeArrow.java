package ai.sek.esfores.items;

import org.bukkit.NamespacedKey;
import java.util.ArrayList;
import java.util.UUID;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.entity.EntityType;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.entity.Arrow;
import ai.sek.esfores.lib.InventoryUtil;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.Bukkit;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import java.util.List;
import org.bukkit.ChatColor;
import org.bukkit.inventory.ItemStack;
import org.bukkit.Material;
import org.bukkit.plugin.java.JavaPlugin;
import ai.sek.esfores.lib.CustomItem;

public class ItemMemeArrow extends CustomItem {
    public ItemMemeArrow(final JavaPlugin basePlugin) {
        super(basePlugin, new ItemStack(Material.ARROW));
        ItemMeta meta = this.baseItem.getItemMeta();
        assert meta != null;
        meta.setDisplayName(ChatColor.AQUA + "Meme Arrow");
        List<String> lore = new ArrayList<String>();
        lore.add(ChatColor.DARK_GREEN + ">implying");
        meta.setLore(lore);
        this.baseItem.setItemMeta(meta);
    }
    
    @Override
    public void initialize() {
        NamespacedKey key = new NamespacedKey(this.plugin, "meme_arrow");
        ShapelessRecipe recipe = new ShapelessRecipe(key, this.baseItem);
        recipe.addIngredient(Material.ENDER_PEARL);
        recipe.addIngredient(Material.ARROW);
        Bukkit.addRecipe(recipe);
    }
    
    @EventHandler
    public void throwArrow(final PlayerInteractEvent event) {
        if (!event.getAction().equals(Action.LEFT_CLICK_AIR)) {
            return;
        }
        if (event.getItem() == null) {
            return;
        }
        final Player player = event.getPlayer();
        if (!this.isItem(event.getItem())) {
            return;
        }
        InventoryUtil.useItemInHand(player, 1);
        final Arrow arrow = (Arrow)player.launchProjectile((Class)Arrow.class, player.getLocation().getDirection().multiply(4.0));
        arrow.setMetadata("meme_arrow", new FixedMetadataValue(this.plugin, player.getUniqueId().toString()));
    }
    
    @EventHandler
    public void cancelDamage(final EntityDamageByEntityEvent event) {
        if (!event.getDamager().getType().equals(EntityType.ARROW)) {
            return;
        }
        final Arrow a = (Arrow)event.getDamager();
        if (!a.hasMetadata("meme_arrow")) {
            return;
        }
        event.setCancelled(true);
    }
    
    @EventHandler
    public void onProjectileHit(final ProjectileHitEvent event) {
        if (!event.getEntity().getType().equals(EntityType.ARROW)) {
            return;
        }
        final Arrow ar = (Arrow)event.getEntity();
        if (!ar.hasMetadata("meme_arrow")) {
            return;
        }
        final UUID uuid = UUID.fromString(ar.getMetadata("meme_arrow").get(0).asString());
        ar.removeMetadata("meme_arrow", this.plugin);
        final Player p = Bukkit.getPlayer(uuid);
        if (p == null) {
            return;
        }
        p.teleport(ar);
    }
}
