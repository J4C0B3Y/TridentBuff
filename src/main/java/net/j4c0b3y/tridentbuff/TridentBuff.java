package net.j4c0b3y.tridentbuff;

import net.j4c0b3y.tridentbuff.listeners.AnvilListener;
import net.j4c0b3y.tridentbuff.listeners.DamageListener;
import org.bukkit.plugin.java.JavaPlugin;

@SuppressWarnings("UnusedDeclaration")
public class TridentBuff extends JavaPlugin {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new AnvilListener(), this);
        getServer().getPluginManager().registerEvents(new DamageListener(), this);
    }
}
