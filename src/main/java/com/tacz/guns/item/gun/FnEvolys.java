package com.tacz.guns.item.gun;

import com.tacz.guns.item.PerGunItem;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;

public class FnEvolys extends PerGunItem {
    private static final Identifier GUN_ID = Identifier.fromNamespaceAndPath("tacz", "fn_evolys");

    public FnEvolys(Item.Properties properties) {
        super(GUN_ID, properties);
    }
}
