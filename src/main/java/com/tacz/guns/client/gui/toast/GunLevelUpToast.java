package com.tacz.guns.client.gui.toast;

import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.toasts.Toast;
import net.minecraft.client.gui.components.toasts.ToastManager;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

@OnlyIn(Dist.CLIENT)
public class GunLevelUpToast implements Toast {
    private final Component title;
    private final Component subTitle;
    private final ItemStack icon;

    public GunLevelUpToast(ItemStack icon, Component titleComponent, @Nullable Component subtitle) {
        this.icon = icon;
        this.title = titleComponent;
        this.subTitle = subtitle;
    }

    @Override
    public Visibility getWantedVisibility() {
        return Visibility.SHOW;
    }

    @Override
    public void update(ToastManager toastManager, long l) {

    }

    @NotNull
    @Override
    public void render(@NotNull GuiGraphics gui, Font toastComponent, long timeSinceLastVisible) {
    }
}
