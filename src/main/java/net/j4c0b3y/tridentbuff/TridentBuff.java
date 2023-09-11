package net.j4c0b3y.tridentbuff;

import lombok.Getter;
import net.j4c0b3y.tridentbuff.listeners.AnvilListener;
import net.j4c0b3y.tridentbuff.listeners.DamageListener;
import org.bukkit.plugin.java.JavaPlugin;

public class TridentBuff extends JavaPlugin {
    @Getter private static TridentBuff instance;

    @Override
    public void onEnable() {
        instance = this;

        getServer().getPluginManager().registerEvents(new AnvilListener(), this);
        getServer().getPluginManager().registerEvents(new DamageListener(), this);
    }
}
