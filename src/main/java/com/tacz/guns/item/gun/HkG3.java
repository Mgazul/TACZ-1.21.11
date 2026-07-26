package com.tacz.guns.item.gun;

import com.tacz.guns.item.PerGunItem;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;

public class HkG3 extends PerGunItem {
    private static final Identifier GUN_ID = Identifier.fromNamespaceAndPath("tacz", "hk_g3");

    public HkG3(Item.Properties properties) {
        super(GUN_ID, properties);
    }
}
