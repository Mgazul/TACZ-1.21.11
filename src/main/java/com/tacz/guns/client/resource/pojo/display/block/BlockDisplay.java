package com.tacz.guns.client.resource.pojo.display.block;

import com.google.gson.annotations.SerializedName;
import com.tacz.guns.client.resource.pojo.display.IDisplay;
import net.minecraft.resources.Identifier;

public class BlockDisplay implements IDisplay {
    @SerializedName("model")
    private Identifier modelLocation;
    @SerializedName("texture")
    private Identifier modelTexture;

    public Identifier getModelLocation() {
        return modelLocation;
    }

    public Identifier getModelTexture() {
        return modelTexture;
    }

    @Override
    public void init() {
        if (modelTexture != null) {
            modelTexture = converter.idToFile(modelTexture);
        }
    }
}
