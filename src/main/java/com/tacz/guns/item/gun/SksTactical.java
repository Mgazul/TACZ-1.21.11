package com.tacz.guns.item.gun;

import com.tacz.guns.item.PerGunItem;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;

public class SksTactical extends PerGunItem {
    private static final Identifier GUN_ID = Identifier.fromNamespaceAndPath("tacz", "sks_tactical");

    public SksTactical(Item.Properties properties) {
        super(GUN_ID, properties);
    }
}
