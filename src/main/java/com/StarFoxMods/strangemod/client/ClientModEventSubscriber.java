package com.StarFoxMods.strangemod.client;

import com.StarFoxMods.strangemod.StrangeMod;
import com.StarFoxMods.strangemod.client.render.entity.StrangeMaterialArrowRenderer;
import com.StarFoxMods.strangemod.world.entity.ModEntityTypes;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = StrangeMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientModEventSubscriber {

    @SubscribeEvent
    public static void onClientSetup(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(ModEntityTypes.STRANGE_MATERIAL_ARROW.get(), StrangeMaterialArrowRenderer::new);
    }
}
