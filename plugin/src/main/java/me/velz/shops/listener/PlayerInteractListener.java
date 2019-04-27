package me.velz.shops.listener;

import me.velz.shops.Shops;
import me.velz.shops.objects.Shop;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerArmorStandManipulateEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;

public class PlayerInteractListener implements Listener {

    private final Shops plugin;

    public PlayerInteractListener(Shops plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onInteractEntity(PlayerInteractEntityEvent event) {
        if (event.getRightClicked().getName() != null) {
            String name = event.getRightClicked().getName();
            if (!plugin.isDisabled()) {
                for (Shop shop : plugin.getShops()) {
                    if (shop.getDisplayname().equalsIgnoreCase(name)) {
                        shop.open(event.getPlayer());
                        event.setCancelled(true);
                        return;
                    }
                }
            }
        }
    }

    @EventHandler
    public void onInteractEntity(PlayerInteractAtEntityEvent event) {
        if (event.getRightClicked().getName() != null) {
            String name = event.getRightClicked().getName();
            if (!plugin.isDisabled()) {
                for (Shop shop : plugin.getShops()) {
                    if (shop.getDisplayname().equalsIgnoreCase(name)) {
                        shop.open(event.getPlayer());
                        event.setCancelled(true);
                        return;
                    }
                }
            }
        }
    }

    @EventHandler
    public void onArmorStand(PlayerArmorStandManipulateEvent event) {
        if (event.getRightClicked().getType() == EntityType.ARMOR_STAND) {
            if (event.getRightClicked().getName() != null) {
                String name = event.getRightClicked().getName();
                if (!plugin.isDisabled()) {
                    for (Shop shop : plugin.getShops()) {
                        if (shop.getDisplayname().equalsIgnoreCase(name)) {
                            shop.open(event.getPlayer());
                            event.setCancelled(true);
                            return;
                        }
                    }
                }
            }
        }
    }

}
