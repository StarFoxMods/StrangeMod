package com.StarFoxMods.strangemod.client.render.entity;

import com.StarFoxMods.strangemod.StrangeMod;
import com.StarFoxMods.strangemod.item.ModItems;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.Item;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class StrangeMaterialArrowRenderer extends ArrowRenderer {

    public StrangeMaterialArrowRenderer(EntityRendererProvider.Context pContext) {
        super(pContext);
    }

    @Override
    public ResourceLocation getTextureLocation(Entity pEntity) {
        Item referenceItem = ModItems.STRANGE_MATERIAL_ARROW.get();
        return new ResourceLocation(StrangeMod.MOD_ID,
                "textures/entity/projectile/strange_material_arrow.png");
    }
}
