package com.tacz.guns.api.item.builder;

import com.tacz.guns.GunMod;
import com.tacz.guns.api.DefaultAssets;
import com.tacz.guns.api.item.IAttachment;
import com.tacz.guns.init.ModItems;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStack;

public final class AttachmentItemBuilder {
    private int count = 1;
    private Identifier attachmentId = DefaultAssets.DEFAULT_ATTACHMENT_ID;

    private AttachmentItemBuilder() { }

    public static AttachmentItemBuilder create() { return new AttachmentItemBuilder(); }
    public AttachmentItemBuilder setCount(int count) { this.count = Math.max(count, 1); return this; }
    public AttachmentItemBuilder setId(Identifier id) { this.attachmentId = id; return this; }

    @Deprecated
    public AttachmentItemBuilder setSkinId(Identifier skinId) { return this; }

    public ItemStack build() {
        // 优先使用每种配件独立的 Item
        Identifier perItemId = Identifier.fromNamespaceAndPath(GunMod.MOD_ID, attachmentId.getPath());
        var item = BuiltInRegistries.ITEM.getOptional(perItemId);
        if (item.isPresent() && item.get() instanceof IAttachment) {
            ItemStack stack = new ItemStack(item.get(), this.count);
            if (stack.getItem() instanceof IAttachment iAttachment) {
                iAttachment.setAttachmentId(stack, this.attachmentId);
            }
            return stack;
        }
        // 回退到通用配件 Item
        ItemStack attachment = new ItemStack(ModItems.ATTACHMENT.get(), this.count);
        if (attachment.getItem() instanceof IAttachment iAttachment) {
            iAttachment.setAttachmentId(attachment, this.attachmentId);
        }
        return attachment;
    }
}
