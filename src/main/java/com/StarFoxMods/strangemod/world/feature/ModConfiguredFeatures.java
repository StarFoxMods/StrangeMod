package com.StarFoxMods.strangemod.world.feature;

import com.StarFoxMods.strangemod.StrangeMod;
import com.StarFoxMods.strangemod.block.ModBlocks;
import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import net.minecraft.core.Registry;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.data.worldgen.features.OreFeatures;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;


import java.util.List;

public class ModConfiguredFeatures {
    public static final DeferredRegister<ConfiguredFeature<?, ?>> CONFIGURED_FEATURES =
            DeferredRegister.create(Registry.CONFIGURED_FEATURE_REGISTRY, StrangeMod.MOD_ID);

    public static final Supplier<List<OreConfiguration.TargetBlockState>> OVERWORLD_STRANGE_MATERIAL_ORE = Suppliers.memoize(() -> List.of(
            OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, ModBlocks.STRANGE_MATERIAL_STONE_ORE.get().defaultBlockState()),
            OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, ModBlocks.STRANGE_MATERIAL_DEEPSLATE_ORE.get().defaultBlockState())));
    public static final Supplier<List<OreConfiguration.TargetBlockState>> END_STRANGE_MATERIAL_ORE = Suppliers.memoize(() -> List.of(
            OreConfiguration.target(new BlockMatchTest(Blocks.END_STONE), ModBlocks.STRANGE_MATERIAL_END_ORE.get().defaultBlockState())));
    public static final Supplier<List<OreConfiguration.TargetBlockState>> NETHER_STRANGE_MATERIAL_ORE = Suppliers.memoize(() -> List.of(
            OreConfiguration.target(OreFeatures.NETHER_ORE_REPLACEABLES, ModBlocks.STRANGE_MATERIAL_NETHER_ORE.get().defaultBlockState())));

    public static final RegistryObject<ConfiguredFeature<?, ?>> STRANGE_MATERIAL_ORE = CONFIGURED_FEATURES.register("strange_material_ore",
            ()-> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(OVERWORLD_STRANGE_MATERIAL_ORE.get(), 20)));
    public static final RegistryObject<ConfiguredFeature<?, ?>> STRANGE_MATERIAL_END_ORE = CONFIGURED_FEATURES.register("strange_material_end_ore",
            ()-> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(END_STRANGE_MATERIAL_ORE.get(), 20)));
    public static final RegistryObject<ConfiguredFeature<?, ?>> STRANGE_MATERIAL_NETHER_ORE = CONFIGURED_FEATURES.register("strange_material_nether_ore",
            ()-> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(NETHER_STRANGE_MATERIAL_ORE.get(), 20)));


    public static void register (IEventBus eventBus) {
        CONFIGURED_FEATURES.register(eventBus);
    }
}
