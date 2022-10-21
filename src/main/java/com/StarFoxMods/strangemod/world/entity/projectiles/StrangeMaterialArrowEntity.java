package com.StarFoxMods.strangemod.world.entity.projectiles;

import com.StarFoxMods.strangemod.item.ModItems;
import com.StarFoxMods.strangemod.world.entity.ModEntityTypes;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.Packet;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.entity.IEntityAdditionalSpawnData;
import net.minecraftforge.network.NetworkHooks;

public class StrangeMaterialArrowEntity extends AbstractArrow implements IEntityAdditionalSpawnData {
    private final Item referenceItem;

    public StrangeMaterialArrowEntity(EntityType<? extends AbstractArrow> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.referenceItem = ModItems.STRANGE_MATERIAL_ARROW.get();
        }
    public StrangeMaterialArrowEntity(Level level, double x, double y, double z, Item referenceItem) {
        super(ModEntityTypes.STRANGE_MATERIAL_ARROW.get(), x, y, z, level);
        this.referenceItem = referenceItem;
    }
    public StrangeMaterialArrowEntity(LivingEntity pShooter, Level pLevel, Item referenceItem) {
        super(ModEntityTypes.STRANGE_MATERIAL_ARROW.get(), pShooter, pLevel);
        this.referenceItem = referenceItem;
    }

    @Override
    protected ItemStack getPickupItem() {
        return new ItemStack(this.referenceItem);
    }

    @Override
    public Packet<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    @Override
    protected void onHit(HitResult pResult) {
        super.onHit(pResult);
    }

    protected void onHitEntity(EntityHitResult pResult) {
        super.onHitEntity(pResult);
        if (!this.level.isClientSide) {
            Entity entity = pResult.getEntity();
            Entity entity1 = this.getOwner();
            entity.hurt(DamageSource.arrow(this, entity1), 6.0F);
            if(entity1 instanceof LivingEntity) {
                this.doEnchantDamageEffects((LivingEntity)entity1, entity);
            }
        }
    }

    @Override
    public void shoot(double pX, double pY, double pZ, float pVelocity, float pInaccuracy) {
        super.shoot(pX, pY, pZ, pVelocity, pInaccuracy);
    }

    @Override
    public void writeSpawnData(FriendlyByteBuf buffer) {

    }

    @Override
    public void readSpawnData(FriendlyByteBuf additionalData) {

    }

    @Override
    public void tick() {
        super.tick();
    }

    @Override
    protected void tickDespawn() {
        super.tickDespawn();
    }
}
