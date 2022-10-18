package com.StarFoxMods.strangemod.block.entity;

import com.StarFoxMods.strangemod.StrangeMod;
import com.StarFoxMods.strangemod.block.ModBlocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, StrangeMod.MOD_ID);

    public static final RegistryObject<BlockEntityType<StrangeChestBlockEntity>> STRANGE_CHEST =
            BLOCK_ENTITIES.register("strange_chest", ()->
                    BlockEntityType.Builder.of(StrangeChestBlockEntity::new,
                            ModBlocks.STRANGE_CHEST.get()).build(null));

    public static void register (IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}
