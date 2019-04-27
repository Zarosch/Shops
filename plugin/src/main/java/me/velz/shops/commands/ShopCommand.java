package me.velz.shops.commands;

import me.velz.shops.Shops;
import me.velz.shops.utils.MessageUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ShopCommand implements CommandExecutor {

    private final Shops plugin;

    public ShopCommand(Shops plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender cs, Command cmd, String label, String[] args) {

        if (args.length == 0) {
            if (!cs.hasPermission("shops.command.help")) {
                cs.sendMessage(MessageUtil.PREFIX.getLocal() + MessageUtil.ERROR_NOPERMISSIONS.getLocal());
                return true;
            }
            cs.sendMessage("");
            cs.sendMessage(MessageUtil.PREFIX.getLocal() + MessageUtil.COMMAND_HELP_HEAD.getLocal());
            cs.sendMessage(MessageUtil.PREFIX.getLocal() + MessageUtil.COMMAND_HELP_DISABLE.getLocal());
            cs.sendMessage(MessageUtil.PREFIX.getLocal() + MessageUtil.COMMAND_HELP_RELOAD.getLocal());
            cs.sendMessage("");
            return true;
        }

        if (args[0].equalsIgnoreCase("disable")) {
            if (!cs.hasPermission("shops.command.disable")) {
                cs.sendMessage(MessageUtil.PREFIX.getLocal() + MessageUtil.ERROR_NOPERMISSIONS.getLocal());
                return true;
            }
            if (plugin.isDisabled()) {
                plugin.setDisabled(false);
                cs.sendMessage(MessageUtil.PREFIX.getLocal()+ MessageUtil.SHOP_ACTIVATE.getLocal());
            } else {
                plugin.setDisabled(true);
                cs.sendMessage(MessageUtil.PREFIX.getLocal() + MessageUtil.SHOP_DISABLE.getLocal());
            }
            return true;
        }

        if (args[0].equalsIgnoreCase("reload")) {
            if (!cs.hasPermission("shops.command.reload")) {
                cs.sendMessage(MessageUtil.PREFIX.getLocal() + MessageUtil.ERROR_NOPERMISSIONS.getLocal());
                return true;
            }
            plugin.getShops().clear();
            plugin.getFileManager().loadConfig();
            cs.sendMessage(MessageUtil.PREFIX.getLocal() + MessageUtil.SHOP_RELOAD.getLocal());
            return true;
        }

        return true;
    }

}
