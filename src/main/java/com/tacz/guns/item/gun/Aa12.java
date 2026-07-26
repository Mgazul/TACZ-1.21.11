package com.tacz.guns.item.gun;

import com.tacz.guns.item.PerGunItem;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;

public class Aa12 extends PerGunItem {
    private static final Identifier GUN_ID = Identifier.fromNamespaceAndPath("tacz", "aa12");

    public Aa12(Item.Properties properties) {
        super(GUN_ID, properties);
    }
}
