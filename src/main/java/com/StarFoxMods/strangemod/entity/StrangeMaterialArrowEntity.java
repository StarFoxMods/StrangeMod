package com.StarFoxMods.strangemod.entity;

import com.StarFoxMods.strangemod.item.ModItems;
import net.minecraft.network.protocol.Packet;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraftforge.network.NetworkHooks;

public class StrangeMaterialArrowEntity extends AbstractArrow {

    public StrangeMaterialArrowEntity(EntityType<StrangeMaterialArrowEntity> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        }

    public StrangeMaterialArrowEntity(EntityType<StrangeMaterialArrowEntity> pEntityType, double x, double y, double z, Level pLevel) {
        super(pEntityType, x, y, z, pLevel);
    }

    public StrangeMaterialArrowEntity(EntityType<StrangeMaterialArrowEntity> pEntityType, LivingEntity pShooter, Level pLevel) {
        super(pEntityType, pShooter, pLevel);
    }

    @Override
    protected ItemStack getPickupItem() {
        return new ItemStack(ModItems.STRANGE_MATERIAL_ARROW.get());
    }

    @Override
    protected void onHitEntity(EntityHitResult pResult) {
        super.onHitEntity(pResult);
        this.setBaseDamage(5.0F);
    }

    @Override
    public Packet<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

}
