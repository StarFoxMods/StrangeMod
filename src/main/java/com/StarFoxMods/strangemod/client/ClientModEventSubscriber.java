package com.StarFoxMods.strangemod.client;

import com.StarFoxMods.strangemod.StrangeMod;
import com.StarFoxMods.strangemod.client.render.StrangeMaterialArrowRenderer;
import com.StarFoxMods.strangemod.world.entity.ModEntityTypes;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = StrangeMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientModEventSubscriber {

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        EntityRenderers.register(ModEntityTypes.STRANGE_MATERIAL_ARROW.get(), StrangeMaterialArrowRenderer::new);
    }
}
