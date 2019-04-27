package me.velz.shops.objects;

import java.util.List;
import lombok.Getter;
import me.velz.facility.FacilityAPI;
import me.velz.shops.utils.MessageUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class ShopItems {
    
    @Getter
    private final int position;
    
    @Getter
    private final double buy_tokens, buy_money, sell_tokens, sell_money;
    
    @Getter
    private ItemStack display, stack;

    @Getter
    private List<String> buycmds, sellcmds;

    public ShopItems(int position, double buy_tokens, double buy_money, double sell_tokens, double sell_money, ItemStack display, ItemStack stack) {
        this.position = position;
        this.buy_money = buy_money;
        this.buy_tokens = buy_tokens;
        this.sell_money = sell_money;
        this.sell_tokens = sell_tokens;
        this.display = display;
        this.stack = stack;
    }

    public ShopItems(int position, double buy_tokens, double buy_money, double sell_tokens, double sell_money, ItemStack display, List<String> buycmds, List<String> sellcmds) {
        this.position = position;
        this.buy_money = buy_money;
        this.buy_tokens = buy_tokens;
        this.sell_money = sell_money;
        this.sell_tokens = sell_tokens;
        this.display = display;
        this.sellcmds = sellcmds;
        this.buycmds = buycmds;
    }

    // Functions
    public void buy(Player player) {
        if(!FacilityAPI.hasMoney(player, buy_money)) {
            player.sendMessage(MessageUtil.PREFIX.getLocal() + MessageUtil.SHOP_NOTENOUGH.getLocal());
            return;
        }
        if(!FacilityAPI.hasTokens(player, buy_tokens)) {
            player.sendMessage(MessageUtil.PREFIX.getLocal() + MessageUtil.SHOP_NOTENOUGH.getLocal());
            return;
        }
        if (getStack() != null) {
            player.getInventory().addItem(getStack());
        }
        if (getBuycmds() != null) {
            for (String cmd : getBuycmds()) {
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), cmd.replaceAll("<PLAYER>", player.getName()));
            }
        }
        FacilityAPI.takeMoney(player, buy_money);
        FacilityAPI.takeTokens(player, buy_tokens);
    }

    public void sell(Player player) {
        if (getStack() != null) {
            if (!inventoryContains(player.getInventory(), getStack())) {
                player.sendMessage(MessageUtil.PREFIX.getLocal() + MessageUtil.SHOP_NOTENOUGHITEM.getLocal());
                return;
            }
            removeFromInventory(player.getInventory(), getStack());
        }
        if (getSellcmds() != null) {
            for (String cmd : getSellcmds()) {
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), cmd.replaceAll("<PLAYER>", player.getName()));
            }
        }
        FacilityAPI.giveMoney(player, sell_money);
        FacilityAPI.giveTokens(player, sell_tokens);
    }

    private boolean inventoryContains(Inventory inventory, ItemStack item) {
        int count = 0;
        ItemStack[] items = inventory.getContents();
        for (int i = 0; i < items.length; i++) {
            if (items[i] != null && items[i].getType() == item.getType() && items[i].getDurability() == item.getDurability()) {
                count += items[i].getAmount();
            }
            if (count >= item.getAmount()) {
                return true;
            }
        }
        return false;
    }

    private void removeFromInventory(Inventory inventory, ItemStack item) {
        int amt = item.getAmount();
        ItemStack[] items = inventory.getContents();
        for (int i = 0; i < items.length; i++) {
            if (items[i] != null && items[i].getType() == item.getType() && items[i].getDurability() == item.getDurability()) {
                if (items[i].getAmount() > amt) {
                    items[i].setAmount(items[i].getAmount() - amt);
                    break;
                } else if (items[i].getAmount() == amt) {
                    items[i] = null;
                    break;
                } else {
                    amt -= items[i].getAmount();
                    items[i] = null;
                }
            }
        }
        inventory.setContents(items);
    }

}
