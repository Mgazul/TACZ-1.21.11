package com.tacz.guns.api.item.builder;

import com.google.common.collect.Maps;
import com.tacz.guns.GunMod;
import com.tacz.guns.api.TimelessAPI;
import com.tacz.guns.api.item.IGun;
import com.tacz.guns.api.item.attachment.AttachmentType;
import com.tacz.guns.api.item.gun.AbstractGunItem;
import com.tacz.guns.api.item.gun.FireMode;
import com.tacz.guns.api.item.gun.GunItemManager;
import com.tacz.guns.init.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.registries.DeferredItem;

import java.util.EnumMap;
import java.util.Optional;

public final class GunItemBuilder {
    private int count = 1;
    private int ammoCount = 0;
    private boolean heatData = false;
    private Identifier gunId;
    private FireMode fireMode = FireMode.UNKNOWN;
    private boolean bulletInBarrel = false;
    private EnumMap<AttachmentType, Identifier> attachments = Maps.newEnumMap(AttachmentType.class);

    private GunItemBuilder() { }

    public static GunItemBuilder create() { return new GunItemBuilder(); }

    public GunItemBuilder setCount(int count) { this.count = Math.max(count, 1); return this; }
    public GunItemBuilder setAmmoCount(int count) { this.ammoCount = Math.max(count, 0); return this; }
    public GunItemBuilder setId(Identifier id) { this.gunId = id; return this; }
    public GunItemBuilder setFireMode(FireMode fireMode) { this.fireMode = fireMode; return this; }
    public GunItemBuilder setAmmoInBarrel(boolean b) { this.bulletInBarrel = b; return this; }
    public GunItemBuilder putAttachment(AttachmentType type, Identifier id) { this.attachments.put(type, id); return this; }
    public GunItemBuilder putAllAttachment(EnumMap<AttachmentType, Identifier> m) { this.attachments = m; return this; }
    public GunItemBuilder setHeatData(boolean h) { this.heatData = h; return this; }

    public ItemStack forceBuild(HolderLookup.Provider provider) {
        ItemStack gun = new ItemStack(ModItems.MODERN_KINETIC_GUN.get(), this.count);
        return applyGunData(gun, provider);
    }

    public ItemStack build(HolderLookup.Provider provider) {
        if (gunId == null) return ItemStack.EMPTY;
        // 优先使用每把枪独立的 Item（tacz:ak47 等）
        Optional<ItemStack> perGunStack = tryBuildPerGunItem(provider);
        if (perGunStack.isPresent()) return perGunStack.get();
        // 回退到旧系统
        String itemType = TimelessAPI.getCommonGunIndex(gunId).map(index -> index.getPojo().getItemType()).orElse(null);
        if (itemType == null) return ItemStack.EMPTY;
        DeferredItem<? extends AbstractGunItem> regObj = GunItemManager.getGunItemRegistryObject(itemType);
        if (regObj == null) return ItemStack.EMPTY;
        return applyGunData(new ItemStack(regObj.get(), this.count), provider);
    }

    private Optional<ItemStack> tryBuildPerGunItem(HolderLookup.Provider provider) {
        Identifier perGunItemId = Identifier.fromNamespaceAndPath(GunMod.MOD_ID, gunId.getPath());
        return BuiltInRegistries.ITEM.getOptional(perGunItemId)
                .filter(item -> item instanceof IGun)
                .map(item -> applyGunData(new ItemStack(item, this.count), provider));
    }

    private ItemStack applyGunData(ItemStack gun, HolderLookup.Provider provider) {
        if (gun.getItem() instanceof IGun iGun) {
            iGun.setGunId(gun, this.gunId);
            iGun.setFireMode(gun, this.fireMode);
            iGun.setCurrentAmmoCount(gun, this.ammoCount);
            iGun.setBulletInBarrel(gun, this.bulletInBarrel);
            if (heatData) iGun.setHeatAmount(gun, 0f);
            this.attachments.forEach((type, id) -> {
                ItemStack stack = AttachmentItemBuilder.create().setId(id).build();
                iGun.installAttachment(provider, gun, stack);
            });
        }
        return gun;
    }
}
