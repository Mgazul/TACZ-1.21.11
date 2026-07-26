package com.tacz.guns.api.item.builder;

import com.tacz.guns.GunMod;
import com.tacz.guns.api.DefaultAssets;
import com.tacz.guns.api.TimelessAPI;
import com.tacz.guns.api.item.IAmmo;
import com.tacz.guns.init.ModItems;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStack;

public final class AmmoItemBuilder {
    private int count = 1;
    private Identifier ammoId = DefaultAssets.DEFAULT_AMMO_ID;

    private AmmoItemBuilder() { }

    public static AmmoItemBuilder create() { return new AmmoItemBuilder(); }
    public AmmoItemBuilder setCount(int count) { this.count = Math.max(count, 1); return this; }
    public AmmoItemBuilder setId(Identifier id) { this.ammoId = id; return this; }

    public ItemStack build() {
        // 优先使用每种弹药独立的 Item
        Identifier perItemId = Identifier.fromNamespaceAndPath(GunMod.MOD_ID, ammoId.getPath());
        var item = BuiltInRegistries.ITEM.getOptional(perItemId);
        if (item.isPresent() && item.get() instanceof IAmmo) {
            ItemStack stack = new ItemStack(item.get(), this.count);
            if (stack.getItem() instanceof IAmmo iAmmo) {
                iAmmo.setAmmoId(stack, this.ammoId);
            }
            return stack;
        }
        // 回退到通用弹药 Item
        ItemStack ammo = new ItemStack(ModItems.AMMO.get(), this.count);
        if (ammo.getItem() instanceof IAmmo iAmmo) {
            iAmmo.setAmmoId(ammo, this.ammoId);
        }
        return ammo;
    }
}
