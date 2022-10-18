package com.StarFoxMods.strangemod.item;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ModCreativeModeTab {
    public static final CreativeModeTab STRANGE_TAB = new CreativeModeTab("strangetab") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModItems.STRANGE_MATERIAL.get());
        }
    };
}
