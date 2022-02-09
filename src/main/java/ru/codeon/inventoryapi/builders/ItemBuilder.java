package ru.codeon.inventoryapi.builders;

import com.google.common.collect.Lists;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import java.lang.reflect.Field;
import java.util.List;
import java.util.UUID;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.potion.Potion;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionType;
import ru.codeon.inventoryapi.utils.StringUtils;

public class ItemBuilder {

    private final ItemStack item;

    public ItemBuilder(String full_id) {
        ItemStack stack;
        String id = full_id;
        String skull_texture = "";
        if (full_id.contains(";")) {
            skull_texture = full_id.split(";")[1];
            id = full_id.split(";")[0];
        }
        if (id.contains(":")) {
            if (id.split(":")[0].equalsIgnoreCase("373")) {
                stack = new ItemStack(Material.POTION);
                Potion potion = new Potion(PotionType.getByEffect(PotionEffectType.getById(Integer.parseInt(id.split(":")[1]))));
                potion.setSplash(true);
                potion.apply(stack);
            } else if ((id.split(":")[0].equalsIgnoreCase("397") && id.split(":")[1].equalsIgnoreCase("3")) || id.equalsIgnoreCase("PLAYER_HEAD")) {
                stack = new ItemStack(Material.SKULL_ITEM, 1, (short)3);
                SkullMeta headMeta = (SkullMeta)stack.getItemMeta();
                if (skull_texture.length() > 16) {
                    GameProfile profile = new GameProfile(UUID.randomUUID(), null);
                    profile.getProperties().put("textures", new Property("textures", skull_texture));
                    try {
                        Field profileField = headMeta.getClass().getDeclaredField("profile");
                        profileField.setAccessible(true);
                        profileField.set(headMeta, profile);
                    } catch (IllegalArgumentException|NoSuchFieldException|SecurityException|IllegalAccessException ex) {
                        ex.printStackTrace();
                    }
                } else {
                    headMeta.setOwner(skull_texture);
                }
                stack.setItemMeta(headMeta);
            } else {
                stack = new ItemStack(Material.getMaterial(Integer.parseInt(id.split(":")[0])), 1, Byte.parseByte(id.split(":")[1]));
            }
        } else {
            stack = new ItemStack(Material.getMaterial(Integer.parseInt(id)));
        }
        this.item = stack;
    }

    public void setItemName(String name) {
        ItemMeta itm = this.item.getItemMeta();
        itm.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
        this.item.setItemMeta(itm);
    }

    public void setItemLore(List<String> ls) {
        ItemMeta itm = this.item.getItemMeta();
        List<String> lore = Lists.newArrayList();
        for (String line : ls)
            lore.add(StringUtils.convertString(line));
        itm.setLore(lore);
        this.item.setItemMeta(itm);
    }

    public void setItemLore(Player p, List<String> ls) {

        List<String> lore = Lists.newArrayList();

        for (String line : ls)
            lore.add(line.replace("{player}", p.getName()));

        setItemLore(lore);

    }

    public void setItemAmount(int amount) {

        this.item.setAmount(amount);

    }

    public void replaceLore(String in, String out) {

        List<String> lore = this.item.getItemMeta().getLore();
        List<String> formatted_lore = Lists.newArrayList();

        for (String s : lore)
            formatted_lore.add(s.replace(in, out));

        setItemLore(formatted_lore);

    }

    public ItemStack build() {

        return this.item;

    }
}