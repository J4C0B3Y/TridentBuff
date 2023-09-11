package net.j4c0b3y.tridentbuff.listeners;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Trident;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class DamageListener implements Listener {

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent event) {
        Trident trident = event.getDamager().getType().equals(EntityType.TRIDENT) ? (Trident) event.getDamager() : null;
        if (trident == null) return;

        event.getEntity().sendMessage(String.valueOf(event.getDamage()));

        int level = trident.getItem().getEnchantmentLevel(Enchantment.DAMAGE_ALL);
        double damage = event.getDamage() + (0.5 * (level - 1) + 1);
        if (level != 0) event.setDamage(damage);

        event.getEntity().sendMessage(String.valueOf(event.getDamage()));
    }
}
