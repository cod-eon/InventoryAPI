package ru.codeon.inventoryapi.builders;

import com.google.common.collect.Maps;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import ru.codeon.inventoryapi.holders.IHolder;
import ru.codeon.inventoryapi.holders.InventoryClickHolder;
import ru.codeon.inventoryapi.utils.StringUtils;

import java.util.HashMap;

public class GUIBuilder {

    private Inventory inventory;

    private HashMap<Integer, InventoryClickHolder> clicks = Maps.newHashMap();

    public GUIBuilder(int rows, String title) {

        this.inventory = Bukkit.createInventory(new IHolder(), rows * 9, StringUtils.convertString(title));

    }

    public void setItem(int slot, ItemStack itemStack, InventoryClickHolder clickHolder) {

        this.inventory.setItem(slot, itemStack);

        if (clickHolder != null) {

            this.clicks.put(slot, clickHolder);
            ((IHolder)this.inventory.getHolder()).setClicks(this.clicks);

        }

    }

    public void setItem(int slot, ItemStack itemStack) {

        this.inventory.setItem(slot, itemStack);

    }

    public Inventory getInventory() {

        return this.inventory;

    }

    public void openInventory(Player player) {

        player.openInventory(this.inventory);

    }

}
