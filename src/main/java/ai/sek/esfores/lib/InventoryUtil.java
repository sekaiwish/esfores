package ai.sek.esfores.lib;

import org.bukkit.inventory.ItemStack;
import org.bukkit.entity.Player;

public final class InventoryUtil {
    public static void useItemInHand(final Player player, final int count) {
        final ItemStack held = player.getInventory().getItemInMainHand();
        if (held.getAmount() > count) {
            held.setAmount(held.getAmount() - count);
        } else {
            player.getInventory().setItemInMainHand(null);
        }
    }
}
