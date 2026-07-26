package com.tacz.guns.item;

import com.tacz.guns.api.item.gun.FireMode;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

/**
 * 每把枪独立的 Item 基类，子类在构造函数中传入固定的 gunId。
 */
public class PerGunItem extends ModernKineticGunItem {
    protected final Identifier gunId;

    public PerGunItem(Identifier gunId, Item.Properties properties) {
        super(properties);
        this.gunId = gunId;
    }

    @Override
    public Identifier getGunId(ItemStack stack) {
        return gunId;
    }

    @Override
    public void setGunId(ItemStack stack, Identifier gunId) {
    }

    @Override
    public Identifier getGunDisplayId(ItemStack stack) {
        return gunId;
    }
}
