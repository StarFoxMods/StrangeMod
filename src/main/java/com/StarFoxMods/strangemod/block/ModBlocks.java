package com.StarFoxMods.strangemod.block;

import com.StarFoxMods.strangemod.StrangeMod;
import com.StarFoxMods.strangemod.block.custom.StrangeChestBlock;
import com.StarFoxMods.strangemod.item.ModCreativeModeTab;
import com.StarFoxMods.strangemod.item.ModItems;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, StrangeMod.MOD_ID);

    public static final RegistryObject<Block> STRANGE_MATERIAL_BLOCK = registerBlock("strange_material_block",
            ()-> new Block(BlockBehaviour.Properties.of(Material.GLASS)
                    .strength(6f).requiresCorrectToolForDrops()), ModCreativeModeTab.STRANGE_TAB);
    public static final RegistryObject<Block> STRANGE_MATERIAL_STONE_ORE = registerBlock("strange_material_stone_ore",
            ()-> new DropExperienceBlock(BlockBehaviour.Properties.of(Material.STONE)
                    .strength(6f).requiresCorrectToolForDrops(), UniformInt.of(3,7)), ModCreativeModeTab.STRANGE_TAB);
    public static final RegistryObject<Block> STRANGE_MATERIAL_NETHER_ORE = registerBlock("strange_material_nether_ore",
            ()-> new DropExperienceBlock(BlockBehaviour.Properties.of(Material.STONE)
                    .strength(6f).requiresCorrectToolForDrops(), UniformInt.of(3,7)), ModCreativeModeTab.STRANGE_TAB);
    public static final RegistryObject<Block> STRANGE_MATERIAL_DEEPSLATE_ORE = registerBlock("strange_material_deepslate_ore",
            ()-> new DropExperienceBlock(BlockBehaviour.Properties.of(Material.STONE)
                    .strength(6f).requiresCorrectToolForDrops(), UniformInt.of(3,7)), ModCreativeModeTab.STRANGE_TAB);
    public static final RegistryObject<Block> STRANGE_MATERIAL_END_ORE = registerBlock("strange_material_end_ore",
            ()-> new DropExperienceBlock(BlockBehaviour.Properties.of(Material.STONE)
                    .strength(6f).requiresCorrectToolForDrops(), UniformInt.of(3,7)), ModCreativeModeTab.STRANGE_TAB);
    public static final RegistryObject<Block> STRANGE_CHEST = registerBlock("strange_chest_block",
            ()-> new StrangeChestBlock(BlockBehaviour.Properties.of(Material.METAL)
                    .strength(6f).requiresCorrectToolForDrops().noOcclusion()), ModCreativeModeTab.STRANGE_TAB);

    //  Copy RegistryObject for new Block
    //
    //  public static final RegistryObject<Block> NAME_OF_BLOCK = registerBlock("name_of_block",
    //      ()-> new DropExperienceBlock(BlockBehaviour.Properties.of(Material.STONE)
    //              .strength(6f).requiresCorrectToolForDrops(), UniformInt.of(3,7)), ModCreativeModeTab.TAB);
    //
    //  Add .json in assets/.../models/block, .../models/item and .../blockstates
    //  Add texture file in assets/.../textures/block
    //
    //  Add description in lang/en_us.json
    //
    //  Add new block in data/.../mineable/pickaxe.json
    //  Add new block in data/.../blocks/needs_diamond_tool.json or needs_iron_tool.json
    //  Add .json in data/.../loot_table

    private static <T extends Block> RegistryObject<T> registerBlock (String name, Supplier<T> block, CreativeModeTab tab) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn, tab);

        return toReturn;
    }

    private static <T extends Block> RegistryObject<Item> registerBlockItem (String name, RegistryObject<T> block, CreativeModeTab tab){
        return ModItems.ITEMS.register(name, ()-> new BlockItem(block.get(), new Item.Properties().tab(tab)));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
