package com.tacz.guns.client.renderer.item;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import com.tacz.guns.api.TimelessAPI;
import com.tacz.guns.api.item.IAttachment;
import com.tacz.guns.client.model.BedrockAttachmentModel;
import com.tacz.guns.client.model.SlotModel;
import com.tacz.guns.client.resource.index.ClientAttachmentIndex;
import com.tacz.guns.util.RenderDistance;
import java.util.function.Consumer;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.special.NoDataSpecialModelRenderer;
import net.minecraft.client.renderer.texture.MissingTextureAtlasSprite;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import org.apache.commons.lang3.tuple.Pair;
import org.jetbrains.annotations.NotNull;

import org.joml.Vector3fc;

public class AttachmentItemRenderer implements NoDataSpecialModelRenderer {
    public static AttachmentItemRenderer INSTANCE;
    public static final SlotModel SLOT_ATTACHMENT_MODEL = new SlotModel();
    public ItemStack stack;
    private ItemDisplayContext displayContext;

    public AttachmentItemRenderer() {
        this.stack = ItemStack.EMPTY;
    }

    public void setItemAndContext(ItemStack stack, ItemDisplayContext context) {
        this.stack = stack;
        this.displayContext = context;
    }

    @Override
    public void getExtents(java.util.function.Consumer<org.joml.Vector3fc> consumer) {
        consumer.accept(new org.joml.Vector3f(0, 0, 0));
    }

    @Override
    public void submit(PoseStack poseStack, SubmitNodeCollector submitNodeCollector, int i, int i1, boolean b, int i2) {
        if (stack.getItem() instanceof IAttachment iAttachment) {
            Identifier attachmentId = iAttachment.getAttachmentId(stack);
            poseStack.pushPose();
            TimelessAPI.getClientAttachmentIndex(attachmentId).ifPresentOrElse(attachmentIndex -> {
                if (displayContext == ItemDisplayContext.GUI) {
                    poseStack.translate(0.5, 1.5, 0.5);
                    poseStack.mulPose(Axis.ZN.rotationDegrees(180));
                    VertexConsumer buffer = new com.mojang.blaze3d.vertex.BufferBuilder(
                        new com.mojang.blaze3d.vertex.ByteBufferBuilder(256),
                        com.mojang.blaze3d.PrimitiveTopology.TRIANGLES,
                        com.mojang.blaze3d.vertex.DefaultVertexFormat.POSITION_COLOR_TEX_LIGHTMAP
                    );
                    SLOT_ATTACHMENT_MODEL.renderToBuffer(poseStack, buffer, i, i1);
                    return;
                }
                poseStack.translate(0.5, 2, 0.5);
                poseStack.scale(-1, -1, 1);
                if (displayContext == ItemDisplayContext.FIXED) {
                    poseStack.mulPose(Axis.YN.rotationDegrees(90f));
                }
                this.renderDefaultAttachment(displayContext, poseStack, null, i, i1, attachmentIndex);
            }, () -> {
                poseStack.translate(0.5, 1.5, 0.5);
                poseStack.mulPose(Axis.ZN.rotationDegrees(180));
                VertexConsumer buffer = new com.mojang.blaze3d.vertex.BufferBuilder(
                    new com.mojang.blaze3d.vertex.ByteBufferBuilder(256),
                    com.mojang.blaze3d.PrimitiveTopology.TRIANGLES,
                    com.mojang.blaze3d.vertex.DefaultVertexFormat.POSITION_COLOR_TEX_LIGHTMAP
                );
                SLOT_ATTACHMENT_MODEL.renderToBuffer(poseStack, buffer, i, i1);
            });
            poseStack.popPose();
        }
    }

    private void renderDefaultAttachment(@NotNull ItemDisplayContext transformType, @NotNull PoseStack poseStack, @org.jetbrains.annotations.Nullable com.mojang.blaze3d.vertex.VertexConsumer pBuffer, int pPackedLight, int pPackedOverlay, ClientAttachmentIndex attachmentIndex) {
        BedrockAttachmentModel model = attachmentIndex.getAttachmentModel();
        Identifier texture = attachmentIndex.getModelTexture();
        // 有模型？正常渲染
        if (model != null && texture != null) {
            // 调用低模
            Pair<BedrockAttachmentModel, Identifier> lodModel = attachmentIndex.getLodModel();
            // 有低模、在高模渲染范围外、不是第一人称
            if (lodModel != null && !RenderDistance.inRenderHighPolyModelDistance(poseStack) && !transformType.firstPerson()) {
                model = lodModel.getLeft();
                texture = lodModel.getRight();
            }
            RenderType renderType = RenderTypes.entityCutout(texture);
            model.render(null, null, poseStack, transformType, renderType, pPackedLight, pPackedOverlay);
        }
        // 否则，以 GUI 形式渲染
        else {
            poseStack.translate(0, 0.5, 0);
            // 展示框里显示正常
            if (transformType == ItemDisplayContext.FIXED) {
                poseStack.mulPose(Axis.YP.rotationDegrees(90));
            }
            VertexConsumer buffer = new com.mojang.blaze3d.vertex.BufferBuilder(new com.mojang.blaze3d.vertex.ByteBufferBuilder(256), com.mojang.blaze3d.PrimitiveTopology.TRIANGLES, com.mojang.blaze3d.vertex.DefaultVertexFormat.POSITION_COLOR_TEX_LIGHTMAP);
            SLOT_ATTACHMENT_MODEL.renderToBuffer(poseStack, buffer, pPackedLight, pPackedOverlay);
        }
    }

}
