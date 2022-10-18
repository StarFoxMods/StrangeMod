package com.StarFoxMods.strangemod;

import com.StarFoxMods.strangemod.block.ModBlocks;
import com.StarFoxMods.strangemod.block.entity.ModBlockEntities;
import com.StarFoxMods.strangemod.item.ModItems;
import com.StarFoxMods.strangemod.recipe.ModRecipes;
import com.StarFoxMods.strangemod.screen.ModMenuTypes;
import com.StarFoxMods.strangemod.screen.StrangeChestScreen;
import com.StarFoxMods.strangemod.util.ModItemProperties;
import com.StarFoxMods.strangemod.villager.ModVillagers;
import com.StarFoxMods.strangemod.world.entity.ModEntityTypes;
import com.StarFoxMods.strangemod.world.feature.ModConfiguredFeatures;
import com.StarFoxMods.strangemod.world.feature.ModPlacedFeatures;
import com.mojang.logging.LogUtils;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(StrangeMod.MOD_ID)
public class StrangeMod {
    public static final String MOD_ID = "strangemod";
    private static final Logger LOGGER = LogUtils.getLogger();

    public StrangeMod() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);

        ModVillagers.register(modEventBus);

        modEventBus.addListener(this::commonSetup);

        ModConfiguredFeatures.register(modEventBus);
        ModPlacedFeatures.register(modEventBus);

        ModBlockEntities.register(modEventBus);
        ModEntityTypes.ENTITY_TYPES.register(modEventBus);
        ModMenuTypes.register(modEventBus);

        ModRecipes.register(modEventBus);

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            ModVillagers.registerPOIs();
        });
    }

    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ClientModEvents {

        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {

            MenuScreens.register(ModMenuTypes.STRANGE_CHEST_MENU.get(), StrangeChestScreen::new);
            ModItemProperties.addCustomItemProperties();
        }
    }
}
