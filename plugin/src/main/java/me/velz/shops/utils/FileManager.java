package me.velz.shops.utils;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import me.velz.shops.Shops;
import me.velz.shops.objects.Shop;
import me.velz.shops.objects.ShopItems;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class FileManager {

    private final Shops plugin;

    public FileManager(Shops plugin) {
        this.plugin = plugin;
    }

    @Getter
    private final FileBuilder config = new FileBuilder("plugins/Shops/", "config.yml");

    public void setDefaults() {
        if(!config.getConfiguration().contains("shops")) {
            config.addDefault("shops.default.displayname", "Default");
            config.addDefault("shops.default.title", "Default Shop");
            config.addDefault("shops.default.size", 9);

            // Item Buy
            config.addDefault("shops.default.items.cobblestone.item", new ItemBuilder().setMaterial(Material.COBBLESTONE).setAmount(16).build());
            List<String> displayLore = new ArrayList<>();
            displayLore.add("");
            displayLore.add("§6Kaufpreis: §a<BUYMONEYPRICE> Gold");
            displayLore.add("§aLinksklick §6zum kaufen");
            displayLore.add("");
            displayLore.add("§6Verkaufspreis: §e<SELLMONEYPRICE> Gold");
            displayLore.add("§eRechtsklick §6zum verkaufen");
            displayLore.add("");
            config.addDefault("shops.default.items.cobblestone.display", new ItemBuilder().setDisplayName("§eCobblestone").setLore(displayLore).setMaterial(Material.COBBLESTONE).setAmount(16).build());
            config.addDefault("shops.default.items.cobblestone.display.position", 3);
            config.addDefault("shops.default.items.cobblestone.price.tokens.sell", 50);
            config.addDefault("shops.default.items.cobblestone.price.tokens.buy", 100);
            config.addDefault("shops.default.items.cobblestone.price.money.sell", 50);
            config.addDefault("shops.default.items.cobblestone.price.money.buy", 100);

            // Command Buy
            config.addDefault("shops.default.items.kit.commands.buy", new String[]{
                "kit tools <PLAYER>"
            });
            config.addDefault("shops.default.items.kit.commands.sell", new String[]{});

            displayLore = new ArrayList<>();
            displayLore.add("");
            displayLore.add("§6Kaufpreis: §a<BUYMONEYPRICE> Gold");
            displayLore.add("§aLinksklick §6zum kaufen");
            displayLore.add("");
            config.addDefault("shops.default.items.kit.display", new ItemBuilder().setDisplayName("§eCobblestone").setLore(displayLore).setMaterial(Material.COBBLESTONE).setAmount(16).build());
            config.addDefault("shops.default.items.kit.display.position", 5);
            config.addDefault("shops.default.items.kit.price.tokens.sell", 0);
            config.addDefault("shops.default.items.kit.price.tokens.buy", 100);
            config.addDefault("shops.default.items.kit.price.money.sell", 0);
            config.addDefault("shops.default.items.kit.price.money.buy", 100);
        }
        config.save();
    }

    public void loadConfig() {
        config.load();
        for (String shop : config.getConfiguration().getConfigurationSection("shops").getKeys(false)) {
            String displayname = config.getString("shops." + shop + ".displayname");
            String title = config.getString("shops." + shop + ".title");
            Integer size = config.getInt("shops." + shop + ".size");
            ArrayList<ShopItems> items = new ArrayList<>();
            for (String item : config.getConfiguration().getConfigurationSection("shops." + shop + ".items").getKeys(false)) {
                Integer position = config.getInt("shops." + shop + ".items." + item + ".display.position");
                double buyTokens = config.getDouble("shops." + shop + ".items." + item + ".price.tokens.buy");
                double sellTokens = config.getDouble("shops." + shop + ".items." + item + ".price.tokens.sell");
                double buyMoney = config.getDouble("shops." + shop + ".items." + item + ".price.money.buy");
                double sellMoney = config.getDouble("shops." + shop + ".items." + item + ".price.money.sell");
                ItemStack displayItem = config.getItemStack("shops." + shop + ".items." + item + ".display");
                if (config.getConfiguration().contains("shops." + shop + ".items." + item + ".item")) {
                    ItemStack sellItem = config.getItemStack("shops." + shop + ".items." + item + ".item");
                    items.add(new ShopItems(position, buyTokens, buyMoney, sellTokens, sellMoney, displayItem, sellItem));
                } else {
                    if (config.getConfiguration().contains("shops." + shop + ".items." + item + ".commands")) {
                        List<String> buycmds = config.getStringList("shops." + shop + ".items." + item + ".commands.buy");
                        List<String> sellcmds = config.getStringList("shops." + shop + ".items." + item + ".commands.sell");
                        items.add(new ShopItems(position, buyTokens, buyMoney, sellTokens, sellMoney, displayItem, buycmds, sellcmds));
                    }
                }
            }
            plugin.getShops().add(new Shop(shop, displayname, title, size, items));
        }
    }

}
