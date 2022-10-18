package com.StarFoxMods.strangemod.world.entity;

import com.StarFoxMods.strangemod.StrangeMod;
import com.StarFoxMods.strangemod.entity.StrangeMaterialArrowEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEntityTypes {
                                                                                                            //ENTITY_TYPES should be ENTITIES
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, StrangeMod.MOD_ID);

    public static final RegistryObject<EntityType<StrangeMaterialArrowEntity>> STRANGE_MATERIAL_ARROW = ENTITY_TYPES.register("strange_material_arrow",
            ()-> EntityType.Builder.of((EntityType.EntityFactory<StrangeMaterialArrowEntity>) StrangeMaterialArrowEntity::new, MobCategory.MISC)
                    .sized(0.5f, 0.5f).build("strange_material_arrow"));

    public static void register(IEventBus eventBus){
        ENTITY_TYPES.register(eventBus);
    }
}
