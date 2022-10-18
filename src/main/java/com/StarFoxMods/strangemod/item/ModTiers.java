package com.StarFoxMods.strangemod.item;

import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;

public class ModTiers {
    public static final ForgeTier STRANGE_MATERIAL = new ForgeTier(3, 2500, 12.0f,
            5.0f, 22, BlockTags.NEEDS_DIAMOND_TOOL,
            () -> 	Ingredient.of(ModItems.STRANGE_MATERIAL.get()));
}
