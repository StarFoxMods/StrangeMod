package com.StarFoxMods.strangemod.recipe;

import com.StarFoxMods.strangemod.StrangeMod;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModRecipes {
    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS =
            DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, StrangeMod.MOD_ID);

    public static final RegistryObject<RecipeSerializer<StrangeChestShapedRecipe>> STRANGE_CHEST_SERIALIZER =
            SERIALIZERS.register("strange_craft", () -> StrangeChestShapedRecipe.Serializer.INSTANCE);

    public static void register(IEventBus eventBus) {
        SERIALIZERS.register(eventBus);
    }
}
