package me.velz.shops;

import java.util.ArrayList;
import lombok.Getter;
import lombok.Setter;
import me.velz.shops.commands.ShopCommand;
import me.velz.shops.listener.InventoryClickListener;
import me.velz.shops.listener.PlayerInteractListener;
import me.velz.shops.objects.Shop;
import me.velz.shops.utils.FileManager;
import me.velz.shops.version.Version;
import me.velz.shops.version.VersionMatcher;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Shops extends JavaPlugin {
    
    @Getter
    private static Shops plugin;
    
    @Getter
    private final FileManager fileManager = new FileManager(this);
    
    @Getter
    private final ArrayList<Shop> shops = new ArrayList<>();
    
    @Getter @Setter
    private boolean disabled = false;
    
    private final VersionMatcher versionMatcher = new VersionMatcher();
    
    @Getter
    private Version version;
    
    @Override
    public void onEnable() {
        this.plugin = this;
        this.version = versionMatcher.match();
        fileManager.setDefaults();
        fileManager.loadConfig();
        Bukkit.getPluginManager().registerEvents(new PlayerInteractListener(this), this);
        Bukkit.getPluginManager().registerEvents(new InventoryClickListener(this), this);
        getCommand("shop").setExecutor(new ShopCommand(this));
    }

    @Override
    public void onDisable() {
        
    }
    
    
    
}
