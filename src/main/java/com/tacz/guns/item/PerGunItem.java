package com.tacz.guns.item;

import com.tacz.guns.GunMod;
import com.tacz.guns.api.item.gun.FireMode;
import com.tacz.guns.client.geckolib.TaczGunGeoModel;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.jspecify.annotations.Nullable;
import com.geckolib.animatable.GeoItem;
import com.geckolib.animatable.client.GeoRenderProvider;
import com.geckolib.animatable.instance.AnimatableInstanceCache;
import com.geckolib.animatable.manager.AnimatableManager;
import com.geckolib.renderer.GeoItemRenderer;

import java.util.function.Consumer;

/**
 * 每把枪独立的 Item 基类，集成 Geckolib 实现 3D 模型渲染。
 */
public class PerGunItem extends ModernKineticGunItem implements GeoItem {
    protected final Identifier gunId;

    private volatile AnimatableInstanceCache cache;

    public PerGunItem(Identifier gunId, Item.Properties properties) {
        super(properties);
        this.gunId = gunId;
        GeoItem.registerSyncedAnimatable(this);
    }

    // ========== GeoItem / GeoAnimatable ==========

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        // 后续可添加动画控制器
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        if (this.cache == null) {
            this.cache = com.geckolib.util.GeckoLibUtil.createInstanceCache(this);
        }
        return this.cache;
    }

    @Override
    public void createGeoRenderer(Consumer<GeoRenderProvider> consumer) {
        consumer.accept(new GeoRenderProvider() {
            private GeoItemRenderer<PerGunItem> renderer;

            @Override
            public @Nullable GeoItemRenderer<?> getGeoItemRenderer() {
                if (renderer == null) {
                    renderer = new GeoItemRenderer<>(new TaczGunGeoModel(gunId.getPath()));
                }
                return renderer;
            }
        });
    }

    // ========== Gun identification (不再用 NBT) ==========

    @Override
    public Identifier getGunId(ItemStack stack) {
        return gunId;
    }

    @Override
    public void setGunId(ItemStack stack, Identifier gunId) {
        // 固定 gunId，不接受 NBT 覆盖
    }

    @Override
    public Identifier getGunDisplayId(ItemStack stack) {
        return gunId;
    }
}
