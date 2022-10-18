package com.StarFoxMods.strangemod.item;

import com.StarFoxMods.strangemod.world.entity.ModEntityTypes;
import com.StarFoxMods.strangemod.entity.StrangeMaterialArrowEntity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;


public class StrangeMaterialArrow extends ArrowItem {

    public StrangeMaterialArrow(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public AbstractArrow createArrow(Level pLevel, ItemStack pStack, LivingEntity pShooter) {
        return new StrangeMaterialArrowEntity(ModEntityTypes.STRANGE_MATERIAL_ARROW.get(), pShooter, pLevel);
    }
}
