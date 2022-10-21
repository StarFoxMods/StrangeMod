package com.StarFoxMods.strangemod.util;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.client.settings.KeyConflictContext;
import org.lwjgl.glfw.GLFW;

public class KeyBinding {

    public static final String KEY_CATEGORY = "key.category.strangemod";
    public static final String KEY_DRINK_FILTERED_WATER = "key.strangemod.drink_filtered_water";

    public static final KeyMapping DRINKING_KEY = new KeyMapping(KEY_DRINK_FILTERED_WATER, KeyConflictContext.IN_GAME,
            InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_O, KEY_CATEGORY);

}
