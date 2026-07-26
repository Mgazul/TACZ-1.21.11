package com.tacz.guns.item.gun;

import com.tacz.guns.item.PerGunItem;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;

public class DeagleGolden extends PerGunItem {
    private static final Identifier GUN_ID = Identifier.fromNamespaceAndPath("tacz", "deagle_golden");

    public DeagleGolden(Item.Properties properties) {
        super(GUN_ID, properties);
    }
}
