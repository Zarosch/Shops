package me.velz.shops.utils;

import lombok.Getter;
import me.velz.shops.Shops;

public enum MessageUtil {

    // Prefix Messages
    PREFIX("", "§f[§eShops§f] ", "§f[§eShops§f] "),
    // Error Messages
    ERROR_NOPERMISSIONS("", "§cDazu hast du keine Berechtigung.", "§cYou dont have permissions."),
    ERROR_PLAYERONLY("", "§cDieser Befehl kann nur von einem Spieler genutzt werden.", "§cThis command can only used by players."),
    ERROR_PLAYERNOTFOUND("", "§cDieser Spieler konnte nicht gefunden werden.", "§cThis player can not be found."),
    ERROR_NONUMBER("", "§cDu musst eine gültige Zahl angeben.", "§cYou have to enter a valid number."),
    ERROR_NOITEMINHAND("", "§cDu hast kein Item in der Hand.", "§cYou don't have a Item in your hand."),
    ERROR_SYNTAX("", "§cSyntax Fehler, bitte nutze: %command", "§cSyntax Error, please use: %command"),
    SHOP_NOTENOUGH("", "§cDu hast nicht genügend Geld.", "§cYou dont have enought money."),
    SHOP_DISABLE("", "§6Alle Shops wurden §cdeaktiviert§6.", "§6All Shops are now §cdisabled§6."),
    SHOP_ACTIVATE("", "§6Alle Shops wurden §aaktiviert§6.", "§6All Shops are now §aenabled§6."),
    SHOP_RELOAD("", "§aDie Konfiguration wurde neugeladen.", "§aThe configuration was reloaded."),
    SHOP_NOTENOUGHITEM("", "§cDu hast nicht genügend Items.", "§cYou dont have enough items."),
    COMMAND_HELP_HEAD("", "§f§lShops Befehle", "§f§lShops Commands"),
    COMMAND_HELP_DISABLE("", "§6/shops disable §fShops deaktivieren", "§6/shops disable §fDisable shops"),
    COMMAND_HELP_RELOAD("", "§6/shops reload §fKonfiguration neuladen", "§6/shops reload §fReload configurations");

    @Getter
    private String local;
    
    @Getter
    private final String german, english;

    private MessageUtil(String local, String german, String english) {
        this.local = local;
        this.german = german;
        this.english = english;
    }

    public static void load() {
        FileBuilder message_en = new FileBuilder("plugins/Crates", "messages_en.yml");
        for (MessageUtil m : MessageUtil.values()) {
            message_en.addDefault("message." + m.toString().replaceAll("_", ".").toLowerCase(), m.english.replaceAll("§", "&"));
        }
        message_en.save();

        FileBuilder message_de = new FileBuilder("plugins/Crates", "messages_de.yml");
        for (MessageUtil m : MessageUtil.values()) {
            message_de.addDefault("message." + m.toString().replaceAll("_", ".").toLowerCase(), m.german.replaceAll("§", "&"));
        }
        message_de.save();


        FileBuilder message = new FileBuilder("plugins/Crates", "messages_" + Shops.getPlugin().getFileManager().getConfig().getString("language") + ".yml");
        if (!message.getFile().exists()) {
            message = new FileBuilder("plugins/Crates", "messages_" + Shops.getPlugin().getFileManager().getConfig().getString("language") + ".yml");
        }
        for (MessageUtil m : MessageUtil.values()) {
            message.addDefault("message." + m.toString().replaceAll("_", ".").toLowerCase(), m.english.replaceAll("§", "&"));
        }
        for (MessageUtil m : MessageUtil.values()) {
            m.local = message.getConfiguration().getString("message." + m.toString().replaceAll("_", ".").toLowerCase()).replaceAll("&", "§");
        }
        message.save();
    }

}
