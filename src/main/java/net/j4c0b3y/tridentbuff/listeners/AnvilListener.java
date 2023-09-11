package net.j4c0b3y.tridentbuff.listeners;

import net.j4c0b3y.tridentbuff.TridentBuff;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;

public class AnvilListener implements Listener {

    TridentBuff plugin = TridentBuff.getInstance();

    @EventHandler
    public void onPrepare(PrepareAnvilEvent event) {
        ItemStack first = event.getInventory().getItem(0);
        ItemStack second = event.getInventory().getItem(1);

        if (!isType(first, Material.TRIDENT)) return;
        if (!isType(second, Material.TRIDENT) && !isType(second, Material.ENCHANTED_BOOK)) return;

        ItemStack result = first.clone();

        int level = getLevel(first, second, Enchantment.DAMAGE_ALL);
        if (level > 0) result.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, level);

        if (isType(second, Material.TRIDENT)) {
            Damageable trident = (Damageable) result.getItemMeta();
            Damageable meta = (Damageable) result.getItemMeta();

            if (trident != null && meta != null) {
                meta.setDamage(trident.getDamage() + meta.getDamage());
            }
        }

        if (first.isSimilar(result)) return;

        Bukkit.getScheduler().runTask(plugin, () -> event.getInventory().setRepairCost(level));
        event.setResult(result);
    }

    @SuppressWarnings("SameParameterValue")
    private int getLevel(ItemStack first, ItemStack second, Enchantment enchantment) {
        int firstLevel = first.getEnchantmentLevel(enchantment);
        int secondLevel = second.getEnchantmentLevel(enchantment);

        if (isType(second, Material.ENCHANTED_BOOK)) {
            EnchantmentStorageMeta meta = (EnchantmentStorageMeta) second.getItemMeta();
            if (meta != null) secondLevel = meta.getStoredEnchantLevel(enchantment);
        }

        int level = Math.max(firstLevel, secondLevel);
        if (firstLevel == secondLevel && level < enchantment.getMaxLevel() && level > 0) level++;

        return level;
    }

    private boolean isType(ItemStack item, Material material) {
        return item != null && item.getItemMeta() != null && item.getType().equals(material);
    }
}
