package com.tacz.guns.compat.iris;

import net.irisshaders.iris.Iris;
import net.irisshaders.iris.shadows.ShadowRenderingState;

public class IrisCompatInner {
    public static boolean isPackInUseQuick() {
        return Iris.isPackInUseQuick();
    }

    public static boolean isRenderShadow() {
        return ShadowRenderingState.areShadowsCurrentlyBeingRendered();
    }
}
