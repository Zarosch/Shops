package me.velz.shops.objects;

import java.util.ArrayList;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class Shop {

    private final String name;
    private final String displayname;
    private final String title;
    private final Integer slots;
    private final ArrayList<ShopItems> items;

    public Shop(String name, String displayname, String title, Integer slots, ArrayList<ShopItems> items) {
        this.name = name;
        this.displayname = displayname;
        this.title = title;
        this.slots = slots;
        this.items = items;
    }

    public String getName() {
        return name;
    }

    public String getDisplayname() {
        return displayname;
    }

    public String getTitle() {
        return title;
    }

    public Integer getSlots() {
        return slots;
    }

    public ArrayList<ShopItems> getItems() {
        return items;
    }

    public void open(Player player) {
        Inventory inventory = Bukkit.createInventory(null, getSlots(), getTitle());

        for (ShopItems item : getItems()) {
            inventory.setItem(item.getPosition(), item.getDisplay());
        }

        player.openInventory(inventory);
    }

}
