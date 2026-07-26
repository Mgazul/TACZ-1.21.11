package com.tacz.guns.client.init;

import com.tacz.guns.GunMod;
import com.tacz.guns.api.client.other.ThirdPersonManager;
import com.tacz.guns.client.input.*;
import com.tacz.guns.client.renderer.item.AmmoItemRenderer;
import com.tacz.guns.client.renderer.item.AttachmentItemRenderer;
import com.tacz.guns.client.renderer.item.GunItemRendererWrapper;
import com.tacz.guns.client.tooltip.ClientAmmoBoxTooltip;
import com.tacz.guns.client.tooltip.ClientAttachmentItemTooltip;
import com.tacz.guns.client.tooltip.ClientBlockItemTooltip;
import com.tacz.guns.client.tooltip.ClientGunTooltip;
import com.tacz.guns.compat.controllable.ControllableCompat;
import com.tacz.guns.init.ModItems;
import com.tacz.guns.inventory.tooltip.AmmoBoxTooltip;
import com.tacz.guns.inventory.tooltip.AttachmentItemTooltip;
import com.tacz.guns.inventory.tooltip.BlockItemTooltip;
import com.tacz.guns.inventory.tooltip.GunTooltip;
import com.tacz.guns.item.AmmoBoxItem;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.Identifier;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.RegisterClientTooltipComponentFactoriesEvent;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;

@EventBusSubscriber(value = Dist.CLIENT, modid = GunMod.MOD_ID)
public class ClientSetupEvent {

    public static final KeyMapping.Category TACZ_CATEGORY = new KeyMapping.Category(Identifier.fromNamespaceAndPath(GunMod.MOD_ID, "category"));

    @SubscribeEvent
    public static void onClientSetup(RegisterKeyMappingsEvent event) {
        // 注册键位
        event.register(InspectKey.INSPECT_KEY);
        event.register(ReloadKey.RELOAD_KEY);
        event.register(ShootKey.SHOOT_KEY);
        event.register(InteractKey.INTERACT_KEY);
        event.register(FireSelectKey.FIRE_SELECT_KEY);
        event.register(AimKey.AIM_KEY);
        event.register(CrawlKey.CRAWL_KEY);
        event.register(RefitKey.REFIT_KEY);
        event.register(ZoomKey.ZOOM_KEY);
        event.register(MeleeKey.MELEE_KEY);
        event.register(ConfigKey.OPEN_CONFIG_KEY);
    }

    @SubscribeEvent
    public static void onClientSetup(RegisterClientTooltipComponentFactoriesEvent event) {
        // 注册文本提示
        event.register(GunTooltip.class, ClientGunTooltip::new);
        event.register(AmmoBoxTooltip.class, ClientAmmoBoxTooltip::new);
        event.register(AttachmentItemTooltip.class, ClientAttachmentItemTooltip::new);
        event.register(BlockItemTooltip.class, ClientBlockItemTooltip::new);
    }

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        // 注册自己的的硬编码第三人称动画
        event.enqueueWork(ThirdPersonManager::registerDefault);

        // TODO: 26.2 - getItemColors() removed, ItemProperties.register() removed
        // event.enqueueWork(() -> Minecraft.getInstance().getItemColors().register(AmmoBoxItem::getColor, ModItems.AMMO_BOX.get()));
        // event.enqueueWork(() -> ItemProperties.register(ModItems.AMMO_BOX.get(), AmmoBoxItem.PROPERTY_NAME, AmmoBoxItem::getStatue));

        // 与 Controllable 的兼容
        event.enqueueWork(ControllableCompat::init);

        // TODO: 26.2 - Item renderer initialization needs to use SpecialRenderer system
        // GunItemRendererWrapper.INSTANCE = new GunItemRendererWrapper();
        // AmmoItemRenderer.INSTANCE = new AmmoItemRenderer();
        // AttachmentItemRenderer.INSTANCE = new AttachmentItemRenderer();
    }
}
