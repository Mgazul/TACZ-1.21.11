package com.tacz.guns.mixin.client;

import com.tacz.guns.api.client.other.KeepingItemRenderer;
import com.tacz.guns.api.item.IGun;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.player.PlayerModel;
import net.minecraft.client.renderer.entity.state.AvatarRenderState;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerModel.class)
public class PlayerModelMixin extends HumanoidModel<AvatarRenderState> {
    @Shadow
    @Final
    public ModelPart leftSleeve;
    @Shadow
    @Final
    public ModelPart rightSleeve;

    public PlayerModelMixin(ModelPart part) {
        super(part);
    }

    @Inject(method = "setupAnim(Lnet/minecraft/client/renderer/entity/state/AvatarRenderState;)V", at = @At(value = "TAIL"))
    private void setRotationAnglesTail(AvatarRenderState p_482112_, CallbackInfo ci) {
        ItemStack currentItem = KeepingItemRenderer.getRenderer().getCurrentItem();
        if (IGun.getIGunOrNull(currentItem) != null) {
            tacz$resetAll(this.rightArm);
            tacz$resetAll(this.leftArm);
            // TODO: 26.2 - ModelPart.copyFrom removed, use loadPose equivalent
            // this.rightSleeve.loadPose(this.rightArm.savePose());
            // this.leftSleeve.loadPose(this.leftArm.savePose());
        }
    }

    /**
     * 将给定模型的旋转角度和旋转点重置为零
     */
    @Unique
    private void tacz$resetAll(ModelPart part) {
        part.xRot = 0.0F;
        part.yRot = 0.0F;
        part.zRot = 0.0F;
    }
}