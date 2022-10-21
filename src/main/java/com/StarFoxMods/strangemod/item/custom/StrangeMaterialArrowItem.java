package com.StarFoxMods.strangemod.item.custom;

import com.StarFoxMods.strangemod.item.ModItems;
import com.StarFoxMods.strangemod.world.entity.projectiles.StrangeMaterialArrowEntity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;


public class StrangeMaterialArrowItem extends ArrowItem {

    public final float damage;

    public StrangeMaterialArrowItem(Properties pProperties, float damage) {
        super(pProperties);
        this.damage = damage;
    }

    @Override
    public AbstractArrow createArrow(Level pLevel, ItemStack pStack, LivingEntity pShooter) {

        StrangeMaterialArrowEntity arrow = new StrangeMaterialArrowEntity(pShooter, pLevel, ModItems.STRANGE_MATERIAL_ARROW.get());
        arrow.setBaseDamage(this.damage);
        return arrow;
    }

    //@Override
    //public boolean isInfinite(ItemStack pStack, ItemStack bow, net.minecraft.world.entity.player.Player pPlayer) {
    //    int enchant = net.minecraft.world.item.enchantment.EnchantmentHelper.getEnchantmentLevel(Enchantments.INFINITY_ARROWS, bow);
    //    return enchant <= 0 ? false : this.getClass() == StrangeMaterialArrowItem.class;
    //}

    //<maelou3105@gmail.com>
    // 1) bow not recognized?
    // 2) ModEntityTypes > DeferredRegister.create(ForgeRegistries.ENTITY_TYPES should be DeferredRegister.create(ForgeRegistries.ENTITIES

}
