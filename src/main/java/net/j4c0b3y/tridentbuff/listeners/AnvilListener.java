package net.j4c0b3y.tridentbuff.listeners;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.inventory.ItemStack;

public class AnvilListener implements Listener {

    @EventHandler
    public void onPrepare(PrepareAnvilEvent event) {
        ItemStack trident = event.getInventory().getItem(0);
        ItemStack book = event.getInventory().getItem(1);

        if (trident == null || book == null) return;
        if (trident.getItemMeta() == null || book.getItemMeta() == null) return;
        if (!trident.getType().equals(Material.TRIDENT) || !book.getType().equals(Material.ENCHANTED_BOOK)) return;

        int tridentLevel = trident.getEnchantmentLevel(Enchantment.DAMAGE_ALL);
        int bookLevel = book.getEnchantmentLevel(Enchantment.DAMAGE_ALL);

        if (bookLevel == 0) return;

        int level = Math.max(bookLevel, tridentLevel);
        if (tridentLevel == bookLevel && level < 5) level++;

        trident.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, level);

        event.setResult(trident);
    }
}
