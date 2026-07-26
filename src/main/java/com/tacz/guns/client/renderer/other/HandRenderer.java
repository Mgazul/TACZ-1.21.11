/*
 * This file contains code originally derived from the Iris Shaders mod
 * licensed under the GNU LGPL v3 License.
 *
 * As permitted by section 3 of the GNU Lesser General Public License v3,
 * this file is now licensed under the GNU GPL v3 License.
 *
 * This file has been modified by MUKSC on 2025-06-24.
 * The modifications were made to remove dependency on the original project.
 *
 * Copyright (C) 2025  MUKSC
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <https://www.gnu.org/licenses/>.
 */

package com.tacz.guns.client.renderer.other;

import com.mojang.blaze3d.vertex.PoseStack;
import com.tacz.guns.compat.iris.IrisCompat;
import net.minecraft.client.Camera;
import net.minecraft.client.renderer.GameRenderer;

import java.util.function.Consumer;

public class HandRenderer {
    public static final HandRenderer INSTANCE = new HandRenderer();
    public static final float DEPTH = 0.125F;

    public void renderSolid(Consumer<PoseStack> renderer, float tickDelta, Camera camera, GameRenderer gameRenderer) {
        if (IrisCompat.isPackInUseQuick()) {
            renderer.accept(null);
            return;
        }
        // TODO: 26.2 - RenderSystem matrix API removed
        renderer.accept(new PoseStack());
    }
}
