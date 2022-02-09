package ru.codeon.inventoryapi.handlers;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import ru.codeon.inventoryapi.holders.IHolder;

public class InventoryHandler implements Listener {

    @EventHandler
    public void clickEvent(InventoryClickEvent event) {

        if (event.getClickedInventory() == null || !(event.getClickedInventory().getHolder() instanceof IHolder))
            return;

        event.setCancelled(true);

        try {

            ((IHolder)event.getClickedInventory().getHolder()).click(event.getSlot());


        } catch (Exception exception) {

            exception.printStackTrace();

        }

    }

}
