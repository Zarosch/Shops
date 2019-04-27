package me.velz.shops.version;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

public interface Version {

    public boolean isUnbreakable(ItemStack itemStack);

    public ItemStack setUnbreakable(ItemStack itemStack, boolean value);

    public String getSkullOwner(SkullMeta skullMeta);

}
