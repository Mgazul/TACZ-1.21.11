package com.tacz.guns.client.geckolib;

import com.tacz.guns.GunMod;
import net.minecraft.resources.Identifier;
import com.geckolib.model.GeoModel;
import com.geckolib.renderer.base.GeoRenderState;

/**
 * 从 TACZ 原有的 geo_models/gun/ 目录加载枪械模型的 GeoModel。
 */
public class TaczGunGeoModel extends GeoModel<TaczGunAnimatable> {
    private final String gunId;

    public TaczGunGeoModel(String gunId) {
        this.gunId = gunId;
    }

    @Override
    public Identifier getModelResource(TaczGunAnimatable animatable, GeoRenderState state) {
        // TACZ 模型路径: geo_models/gun/ak47_geo.json
        return Identifier.fromNamespaceAndPath(GunMod.MOD_ID, "geo_models/" + modelPath());
    }

    @Override
    public Identifier getTextureResource(TaczGunAnimatable animatable, GeoRenderState state) {
        // TACZ 纹理路径: textures/gun/uv/ak47.png
        return Identifier.fromNamespaceAndPath(GunMod.MOD_ID, "textures/gun/uv/" + gunId);
    }

    @Override
    public Identifier getAnimationResource(TaczGunAnimatable animatable, GeoRenderState state) {
        // TACZ 动画路径: animations/ak47.animation.json
        return Identifier.fromNamespaceAndPath(GunMod.MOD_ID, "animations/" + gunId);
    }

    protected String modelPath() {
        return "gun/" + gunId + "_geo";
    }
}
