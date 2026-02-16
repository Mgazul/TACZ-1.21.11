package com.tacz.guns.init;

import com.tacz.guns.client.gui.compat.ClothConfigScreen;
import com.tacz.guns.compat.iris.IrisCompat;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModList;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.InterModEnqueueEvent;
import net.neoforged.fml.loading.FMLEnvironment;

@EventBusSubscriber
public class CompatRegistry {
    public static final String CLOTH_CONFIG = "cloth_config";
    public static final String IRIS = "iris";

    @SubscribeEvent
    public static void onEnqueue(final InterModEnqueueEvent event) {

        event.enqueueWork(() -> {
            if (FMLEnvironment.getDist() == Dist.CLIENT) {
                ClothConfigScreen.registerNoClothConfigPage();
            }
        });
        event.enqueueWork(() -> checkModLoad(IRIS, IrisCompat::initCompat));
    }

    public static void checkModLoad(String modId, Runnable runnable) {
        if (ModList.get().isLoaded(modId)) {
            runnable.run();
        }
    }
}
