package me.velz.shops.listener;

import me.velz.shops.Shops;
import me.velz.shops.objects.Shop;
import me.velz.shops.objects.ShopItems;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;

public class InventoryClickListener implements Listener {
    
    private final Shops plugin;

    public InventoryClickListener(Shops plugin) {
        this.plugin = plugin;
    }
    
    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getCurrentItem() == null) {
            return;
        }
        if (event.getCurrentItem().getItemMeta() == null) {
            return;
        }
        if (event.getCurrentItem().getItemMeta().getDisplayName() == null) {
            return;
        }
        if (event.getInventory().getName() == null) {
            return;
        }
        for (Shop shop : plugin.getShops()) {
            if (event.getInventory().getName().equalsIgnoreCase(shop.getTitle())) {
                event.setCancelled(true);

                // Rechtsklick
                // ------------------------------------------------------------------------------------- //
                if (event.getClick() == ClickType.RIGHT) {
                    for (ShopItems items : shop.getItems()) {
                        if (items.getDisplay().getItemMeta().getDisplayName().equalsIgnoreCase(event.getCurrentItem().getItemMeta().getDisplayName())) {
                            items.sell((Player) event.getWhoClicked());
                            event.setCancelled(true);
                        }
                    }
                }
                if (event.getClick() == ClickType.SHIFT_RIGHT) {
                    for (ShopItems items : shop.getItems()) {
                        if (items.getDisplay().getItemMeta().getDisplayName().equalsIgnoreCase(event.getCurrentItem().getItemMeta().getDisplayName())) {
                            for (int i = 64; i != 0; i--) {
                                items.sell((Player) event.getWhoClicked());
                            }
                            event.setCancelled(true);
                        }
                    }
                }

                // Linksklick
                // ------------------------------------------------------------------------------------- //
                if (event.getClick() == ClickType.LEFT) {
                    for (ShopItems items : shop.getItems()) {
                        if (items.getDisplay().getItemMeta().getDisplayName().equalsIgnoreCase(event.getCurrentItem().getItemMeta().getDisplayName())) {
                            items.buy((Player) event.getWhoClicked());
                        }
                        event.setCancelled(true);
                    }
                }
                if (event.getClick() == ClickType.SHIFT_LEFT) {
                    for (ShopItems items : shop.getItems()) {
                        if (items.getDisplay().getItemMeta().getDisplayName().equalsIgnoreCase(event.getCurrentItem().getItemMeta().getDisplayName())) {
                            for (int i = 64; i != 0; i--) {
                                items.buy((Player) event.getWhoClicked());
                            }
                            event.setCancelled(true);
                        }
                    }
                }
                // ------------------------------------------------------------------------------------- //

            }
        }
    }

}
