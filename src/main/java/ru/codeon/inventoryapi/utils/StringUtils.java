package ru.codeon.inventoryapi.utils;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.entity.Player;

public class StringUtils {

    public static String convertString(String string) {

        return ChatColor.translateAlternateColorCodes('&', string);

    }

    public static void sendMessage(String message, Player player) {

        player.sendMessage(convertString(message));

    }

}
