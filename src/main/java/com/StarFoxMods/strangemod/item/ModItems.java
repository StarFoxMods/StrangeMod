package com.StarFoxMods.strangemod.item;

import com.StarFoxMods.strangemod.StrangeMod;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, StrangeMod.MOD_ID);

    public static final RegistryObject<Item> RAW_STRANGE_MATERIAL = ITEMS.register("raw_strange_material",
            ()-> new Item(new Item.Properties().tab(ModCreativeModeTab.STRANGE_TAB)));
    public static final RegistryObject<Item> STRANGE_MATERIAL = ITEMS.register("strange_material",
            ()-> new Item(new Item.Properties().tab(ModCreativeModeTab.STRANGE_TAB)));

    public static final RegistryObject<Item> STRANGE_MATERIAL_SWORD = ITEMS.register("strange_material_sword",
            ()-> new SwordItem(ModTiers.STRANGE_MATERIAL, 4, 3f,
                    new Item.Properties().tab(ModCreativeModeTab.STRANGE_TAB)));
    public static final RegistryObject<Item> STRANGE_MATERIAL_PICKAXE = ITEMS.register("strange_material_pickaxe",
            ()-> new PickaxeItem(ModTiers.STRANGE_MATERIAL, 2, 2f,
                    new Item.Properties().tab(ModCreativeModeTab.STRANGE_TAB)));
    public static final RegistryObject<Item> STRANGE_MATERIAL_SHOVEL = ITEMS.register("strange_material_shovel",
            ()-> new ShovelItem(ModTiers.STRANGE_MATERIAL, 0, 2f,
                    new Item.Properties().tab(ModCreativeModeTab.STRANGE_TAB)));
    public static final RegistryObject<Item> STRANGE_MATERIAL_AXE = ITEMS.register("strange_material_axe",
            ()-> new AxeItem(ModTiers.STRANGE_MATERIAL, 4, 1f,
                    new Item.Properties().tab(ModCreativeModeTab.STRANGE_TAB)));
    public static final RegistryObject<Item> STRANGE_MATERIAL_HOE = ITEMS.register("strange_material_hoe",
            ()-> new HoeItem(ModTiers.STRANGE_MATERIAL, 0, 0f,
                    new Item.Properties().tab(ModCreativeModeTab.STRANGE_TAB)));

    public static final RegistryObject<Item> STRANGE_MATERIAL_BOW = ITEMS.register("strange_material_bow",
            ()-> new BowItem(new Item.Properties().tab(ModCreativeModeTab.STRANGE_TAB).durability(2500)));
    public static final RegistryObject<Item> STRANGE_MATERIAL_CROSSBOW = ITEMS.register("strange_material_crossbow",
            ()-> new CrossbowItem(new Item.Properties().tab(ModCreativeModeTab.STRANGE_TAB).durability(2500)));
    public static final RegistryObject<Item> STRANGE_MATERIAL_ARROW = ITEMS.register("strange_material_arrow",
            ()-> new StrangeMaterialArrow(new Item.Properties().tab(ModCreativeModeTab.STRANGE_TAB)));

    public static final RegistryObject<Item> STRANGE_MATERIAL_HELMET = ITEMS.register("strange_material_helmet",
            ()-> new ArmorItem(ModArmorMaterials.STRANGE_MATERIAL, EquipmentSlot.HEAD,
                    new Item.Properties().tab(ModCreativeModeTab.STRANGE_TAB)));
    public static final RegistryObject<Item> STRANGE_MATERIAL_CHESTPLATE = ITEMS.register("strange_material_chestplate",
            ()-> new ArmorItem(ModArmorMaterials.STRANGE_MATERIAL, EquipmentSlot.CHEST,
                    new Item.Properties().tab(ModCreativeModeTab.STRANGE_TAB)));
    public static final RegistryObject<Item> STRANGE_MATERIAL_LEGGING = ITEMS.register("strange_material_leggings",
            ()-> new ArmorItem(ModArmorMaterials.STRANGE_MATERIAL, EquipmentSlot.LEGS,
                    new Item.Properties().tab(ModCreativeModeTab.STRANGE_TAB)));
    public static final RegistryObject<Item> STRANGE_MATERIAL_BOOTS = ITEMS.register("strange_material_boots",
            ()-> new ArmorItem(ModArmorMaterials.STRANGE_MATERIAL, EquipmentSlot.FEET,
                    new Item.Properties().tab(ModCreativeModeTab.STRANGE_TAB)));

    /*  Copy RegistryObject for new Item

        public static final RegistryObject<Item> NAME_OF_ITEM = ITEMS.register("name_of_item",
                ()-> new Item(new Item.Properties().tab(ModCreativeModeTab.TAB)));

        Add .json in assets/models/item
        Add texture file in assets/.../textures/item
        Add description in lang/en_us.json
        */


    public static void register (IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

}
