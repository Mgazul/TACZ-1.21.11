package com.tacz.guns.client.particle;

import com.mojang.blaze3d.vertex.VertexConsumer;
import com.tacz.guns.api.TimelessAPI;
import com.tacz.guns.config.client.RenderConfig;
import com.tacz.guns.init.ModBlocks;
import com.tacz.guns.particles.BulletHoleOption;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.SingleQuadParticle;
import net.minecraft.client.renderer.state.level.QuadParticleRenderState;
import net.minecraft.client.renderer.texture.MissingTextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.Identifier;
import net.minecraft.util.ARGB;
import net.minecraft.util.LightCoordsUtil;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;
import org.joml.Quaternionf;
import org.joml.Vector3f;

/**
 * Author: Forked from MrCrayfish, continued by Timeless devs
 */
public class BulletHoleParticle extends SingleQuadParticle {
    private final Direction direction;
    private final BlockPos pos;
    private int uOffset;
    private int vOffset;
    private float textureDensity;

    public BulletHoleParticle(ClientLevel world, double x, double y, double z, Direction direction, BlockPos pos, String ammoId, String gunId, String gunDisplayId) {
        super(world, x, y, z, getSpriteFromPos(pos));
        this.uOffset = this.random.nextInt(16);
        this.vOffset = this.random.nextInt(16);
        this.textureDensity = (this.sprite.getU1() - this.sprite.getU0()) / 16.0F;
        this.direction = direction;
        this.pos = pos;
        this.setLifetime(this.getLifetimeFromConfig(world));
        this.hasPhysics = false;
        this.gravity = 0.0F;
        this.quadSize = 0.05F;

        BlockState state = world.getBlockState(pos);
        if (shouldRemove()) {
            this.remove();
        }
        TimelessAPI.getGunDisplay(Identifier.parse(gunDisplayId), Identifier.parse(gunId)).ifPresent(gunIndex -> {
            float[] gunTracerColor = gunIndex.getTracerColor();
            if (gunTracerColor != null) {
                this.rCol = gunTracerColor[0];
                this.gCol = gunTracerColor[1];
                this.bCol = gunTracerColor[2];
            } else {
                TimelessAPI.getClientAmmoIndex(Identifier.parse(ammoId)).ifPresent(ammoIndex -> {
                    float[] ammoTracerColor = ammoIndex.getTracerColor();
                    this.rCol = ammoTracerColor[0];
                    this.gCol = ammoTracerColor[1];
                    this.bCol = ammoTracerColor[2];
                });
            }
        });
        this.alpha = 0.9F;
    }

    private static TextureAtlasSprite getSpriteFromPos(BlockPos pos) {
        var minecraft = Minecraft.getInstance();
        var atlas = (net.minecraft.client.renderer.texture.TextureAtlas) minecraft.getTextureManager().getTexture(net.minecraft.client.renderer.texture.TextureAtlas.LOCATION_BLOCKS);
        return atlas.getSprite(MissingTextureAtlasSprite.getLocation());
    }

    private int getLifetimeFromConfig(ClientLevel world) {
        int configLife = RenderConfig.BULLET_HOLE_PARTICLE_LIFE.get();
        if (configLife <= 1) {
            return configLife;
        }
        return configLife + world.getRandom().nextInt(configLife / 2);
    }

    @Override
    protected float getU0() {
        return this.sprite.getU0() + this.uOffset * this.textureDensity;
    }

    @Override
    protected float getV0() {
        return this.sprite.getV0() + this.vOffset * this.textureDensity;
    }

    @Override
    protected float getU1() {
        return this.getU0() + this.textureDensity;
    }

    @Override
    protected float getV1() {
        return this.getV0() + this.textureDensity;
    }

    @Override
    public void tick() {
        super.tick();
        if (shouldRemove()) {
            this.remove();
        }
    }

    @Override
    public void extract(QuadParticleRenderState particleTypeRenderState, Camera camera, float partialTick) {
        // Fade logic
        int light = Math.max(15 - this.age / 2, 0);
        float colorPercent = light / 15.0f;
        float fadeRed = this.rCol * colorPercent;
        float fadeGreen = this.gCol * colorPercent;
        float fadeBlue = this.bCol * colorPercent;

        double threshold = RenderConfig.BULLET_HOLE_PARTICLE_FADE_THRESHOLD.get() * this.lifetime;
        float fade = 1.0f - (float) (Math.max(this.age - threshold, 0) / (this.lifetime - threshold));
        float alphaFade = this.alpha * fade;

        int packedColor = ARGB.colorFromFloat(alphaFade, fadeRed, fadeGreen, fadeBlue);
        int lightCoords = net.minecraft.util.LightCoordsUtil.pack(light, light);

        // Custom rotation based on block face direction (not camera-facing)
        Quaternionf rotation = this.direction.getRotation();

        Vec3 view = camera.position();
        float px = (float) (Mth.lerp(partialTick, this.xo, this.x) - view.x());
        float py = (float) (Mth.lerp(partialTick, this.yo, this.y) - view.y());
        float pz = (float) (Mth.lerp(partialTick, this.zo, this.z) - view.z());

        float scale = this.getQuadSize(partialTick);

        particleTypeRenderState.add(
            getLayer(), px, py, pz,
            rotation.x, rotation.y, rotation.z, rotation.w,
            scale,
            this.getU0(), this.getU1(), this.getV0(), this.getV1(),
            packedColor, lightCoords
        );
    }

    @Override
    protected SingleQuadParticle.Layer getLayer() {
        return SingleQuadParticle.Layer.TRANSLUCENT_TERRAIN;
    }

    private boolean shouldRemove() {
        final BlockState blockState = this.level.getBlockState(this.pos);
        if (blockState.isAir()) {
            return true;
        } else {
            VoxelShape shape = blockState.getCollisionShape(this.level, this.pos);
            if (shape.isEmpty()) {
                return true;
            }
            AABB baseBlockBoundingBox = shape.bounds();
            AABB blockBoundingBox = baseBlockBoundingBox.move(this.pos);
            boolean intersects = blockBoundingBox.intersects(
                    this.x - 0.1, this.y - 0.1, this.z - 0.1,
                    this.x + 0.1, this.y + 0.1, this.z + 0.1);
            return !intersects;
        }
    }

    @OnlyIn(Dist.CLIENT)
    public static class Provider implements ParticleProvider<BulletHoleOption> {
        public Provider() {
        }

        @Override
        public BulletHoleParticle createParticle(@NotNull BulletHoleOption option, @NotNull ClientLevel world, double x, double y, double z, double pXSpeed, double pYSpeed, double pZSpeed, RandomSource random) {
            BulletHoleParticle particle = new BulletHoleParticle(world, x, y, z, option.getDirection(), option.getPos(), option.getAmmoId(), option.getGunId(), option.getGunDisplayId());
            return particle;
        }
    }
}
