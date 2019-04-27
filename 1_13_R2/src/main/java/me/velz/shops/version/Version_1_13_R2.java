package me.velz.shops.version;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

public class Version_1_13_R2 implements Version {

    @Override
    public boolean isUnbreakable(ItemStack itemStack) {
        return itemStack.getItemMeta().isUnbreakable();
    }

    @Override
    public ItemStack setUnbreakable(ItemStack itemStack, boolean value) {
        itemStack.getItemMeta().setUnbreakable(value);
        return itemStack;
    }

    @Override
    public String getSkullOwner(SkullMeta skullMeta) {
        return skullMeta.getOwningPlayer().getName();
    }
    
    
    
}
