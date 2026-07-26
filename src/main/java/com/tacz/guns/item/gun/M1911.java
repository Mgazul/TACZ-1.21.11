package com.tacz.guns.item.gun;

import com.tacz.guns.item.PerGunItem;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;

public class M1911 extends PerGunItem {
    private static final Identifier GUN_ID = Identifier.fromNamespaceAndPath("tacz", "m1911");

    public M1911(Item.Properties properties) {
        super(GUN_ID, properties);
    }
}
