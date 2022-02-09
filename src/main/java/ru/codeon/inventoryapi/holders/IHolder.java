package ru.codeon.inventoryapi.holders;

import com.google.common.collect.Maps;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

import java.util.HashMap;
import java.util.Map;

public class IHolder implements InventoryHolder {

    private Map<Integer, InventoryClickHolder> clicks = Maps.newHashMap();

    private Inventory inventory;

    public Inventory getInventory() {
        return this.inventory;
    }

    public void click(int slot) {

        if (this.clicks.containsKey(slot))
            this.clicks.get(slot).click();

    }

    public void setInventory(Inventory inventory) {

        this.inventory = inventory;

    }

    public void setClicks(HashMap<Integer, InventoryClickHolder> clicks1) {

        this.clicks = clicks1;

    }

}
