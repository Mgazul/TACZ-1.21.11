package com.tacz.guns.client.gui.components.refit;

import com.tacz.guns.client.gui.GunRefitScreen;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import org.jetbrains.annotations.NotNull;

public class RefitUnloadButton extends Button implements IComponentTooltip {
    public RefitUnloadButton(int pX, int pY, Button.OnPress pOnPress) {
        super(pX, pY, 8, 8, Component.empty(), pOnPress, DEFAULT_NARRATION);
    }

    @Override
    public void extractContents(@NotNull GuiGraphicsExtractor graphics, int pMouseX, int pMouseY, float pPartialTick) {
        int x = getX(), y = getY();
        if (isHoveredOrFocused()) {
            graphics.blit(RenderPipelines.GUI_TEXTURED, GunRefitScreen.UNLOAD_TEXTURE, x, y, width, height, 0, 0, 80, 80, 160, 80);
        } else {
            graphics.blit(RenderPipelines.GUI_TEXTURED, GunRefitScreen.UNLOAD_TEXTURE, x, y, width, height, 80, 0, 80, 80, 160, 80);
        }
    }

    @Override
    public void renderTooltip(Consumer<List<Component>> consumer) {
        if (this.isHoveredOrFocused()) {
            consumer.accept(Collections.singletonList(Component.translatable("tooltip.tacz.refit.unload")));
        }
    }
}
