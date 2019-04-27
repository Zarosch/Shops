package me.velz.shops.utils;

import lombok.Getter;

public enum MessageUtil {

    // Prefix Messages
    PREFIX("§f[§eShops§f] "),
    // Error Messages
    ERROR_NOPERMISSIONS("§cDazu hast du keine Berechtigung."),
    ERROR_PLAYERONLY("§cDieser Befehl kann nur von einem Spieler genutzt werden."),
    ERROR_PLAYERNOTFOUND("§cDieser Spieler konnte nicht gefunden werden."),
    ERROR_NONUMBER("§cDu musst eine gültige Zahl angeben."),
    ERROR_NOITEMINHAND("§cDu hast kein Item in der Hand."),
    ERROR_SYNTAX("§cSyntax Fehler, bitte nutze: %command"),
    
    SHOP_NOTENOUGH("§cDu hast nicht genügend Geld."),
    SHOP_DISABLE("§6Alle Shops wurden §cdisable§6."),
    SHOP_ACTIVATE("§6Alle Shops wurden §aaktiviert§6."),
    SHOP_RELOAD("§aDie Konfiguration wurde neugeladen."),
    SHOP_NOTENOUGHITEM("§cDu hast nicht genügend Items."),
    
    COMMAND_HELP_HEAD("§f§lShops Befehle"),
    COMMAND_HELP_DISABLE("§6/shops disable &fShops deaktivieren"),
    COMMAND_HELP_RELOAD("§6/shops reload &fKonfiguration neuladen");

    @Getter
    private String local;

    private MessageUtil(String local) {
        this.local = local;
    }

    public static void load() {
        FileBuilder message = new FileBuilder("plugins/Quests", "messages.yml");
        for (MessageUtil m : MessageUtil.values()) {
            message.addDefault("message." + m.toString().replaceAll("_", ".").toLowerCase(), m.local.replaceAll("§", "&"));
        }
        message.save();
        for (MessageUtil m : MessageUtil.values()) {
            m.local = message.getConfiguration().getString("message." + m.toString().replaceAll("_", ".").toLowerCase()).replaceAll("&", "§");
        }
    }

    public static void save(MessageUtil m) {
        FileBuilder message = new FileBuilder("plugins/Quests", "messages.yml");
    }

}
