package com.tacz.guns.client.init;

import com.mojang.blaze3d.vertex.PoseStack;
import com.tacz.guns.client.renderer.item.AmmoItemRenderer;
import com.tacz.guns.init.ModItems;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;
import net.neoforged.neoforge.client.extensions.common.RegisterClientExtensionsEvent;

/**
 * @author Mgazul
 * @date 2025/11/30 19:45
 */
@EventBusSubscriber(modid = "tacz", value = Dist.CLIENT)
public class ClientEventHandler {

    @SubscribeEvent
    public static void registerItemExtensions(RegisterClientExtensionsEvent event) {
        // 为 AmmoItem 注册自定义渲染扩展
        event.registerItem(new IClientItemExtensions() {
            @Override
            public HumanoidModel.ArmPose getArmPose(LivingEntity entity, InteractionHand hand, ItemStack itemStack) {
                return HumanoidModel.ArmPose.ITEM;
            }

            @Override
            public void render(ItemStack stack, ItemDisplayContext transformType, PoseStack poseStack,
                               MultiBufferSource bufferSource, int packedLight, int packedOverlay) {
                // 使用 AmmoItemRenderer 进行渲染
                AmmoItemRenderer.INSTANCE.renderByItem(stack, transformType, poseStack, bufferSource, packedLight, packedOverlay);
            }
        }, ModItems.AMMO); // 替换为实际的物品实例引用
    }
}
