package com.StarFoxMods.strangemod.client.render;

import com.StarFoxMods.strangemod.StrangeMod;
import com.StarFoxMods.strangemod.entity.StrangeMaterialArrowEntity;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class StrangeMaterialArrowRenderer extends ArrowRenderer<StrangeMaterialArrowEntity> {
    public static final ResourceLocation texture = new ResourceLocation(StrangeMod.MOD_ID,
            "textures/entity/projectile/strange_material_arrow.png");

    public StrangeMaterialArrowRenderer(EntityRendererProvider.Context pContext) {
        super(pContext);
    }

    @Override
    public ResourceLocation getTextureLocation(StrangeMaterialArrowEntity arrow) {
        return texture;
    }
}
