package com.tacz.guns.client.gui;

import java.util.OptionalLong;
import javax.annotation.Nullable;
import net.minecraft.client.GameNarrator;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.util.HttpUtil;
import org.jetbrains.annotations.NotNull;

// FIXME: Needs actual testing
public class GunPackProgressScreen extends Screen implements HttpUtil.DownloadProgressListener {
    private @Nullable Component header;
    private @Nullable Component stage;
    private int progress;
    private boolean stop;

    public GunPackProgressScreen() {
        super(GameNarrator.NO_TITLE);
    }

    @Override
    protected void init() {
        Button button = Button.builder(
                Component.translatable("gui.tacz.client_gun_pack_downloader.background_download"), b -> this.requestFinished(false)
        ).bounds((width - 200) / 2, 120, 200, 20).build();
        this.addRenderableWidget(button);
    }

    @Override
    public void extractRenderState(@NotNull GuiGraphicsExtractor gui, int mouseX, int mouseY, float partialTick) {
        if (this.stop) {
            this.getMinecraft().setScreen(null);
        } else {
            this.extractBackground(gui, mouseX, mouseY, partialTick);
            if (this.header != null) {
                gui.centeredText(this.font, this.header, this.width / 2, 70, 16777215);
            }
            if (this.stage != null && this.progress > 0) {
                MutableComponent text = this.stage.copy().append(" " + this.progress + "%");
                gui.centeredText(this.font, text, this.width / 2, 90, 16777215);
            }
            super.extractRenderState(gui, mouseX, mouseY, partialTick);
        }
    }

    private long total;
    private long downloaded;

    @Override
    public void requestStart() {
        this.header = Component.translatable("gui.tacz.client_gun_pack_downloader.downloading");
    }

    @Override
    public void downloadStart(OptionalLong optionalLong) {
        this.total = optionalLong.orElse(0L);
        this.downloaded = 0L;
        this.progress = 0;
    }

    @Override
    public void downloadedBytes(long l) {
        this.downloaded += l;
        this.progress = (int) Math.ceil((double) this.total / this.downloaded);
    }

    @Override
    public void requestFinished(boolean b) {
        this.stop = true;
    }
}
