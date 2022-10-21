package com.StarFoxMods.strangemod.event;

import com.StarFoxMods.strangemod.StrangeMod;
import com.StarFoxMods.strangemod.client.ThirstHudOverlay;
import com.StarFoxMods.strangemod.networking.ModMessages;
import com.StarFoxMods.strangemod.networking.packet.DrinkFilteredWaterC2SPacket;
import com.StarFoxMods.strangemod.util.KeyBinding;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.event.RegisterGuiOverlaysEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

public class ClientEvents {

    @Mod.EventBusSubscriber(modid = StrangeMod.MOD_ID, value = Dist.CLIENT)
    public static class ClientForgeEvents {

        @SubscribeEvent
        public static void onKeyInput(InputEvent.Key event) {
            if(KeyBinding.DRINKING_KEY.consumeClick()) {
                ModMessages.sendToServer(new DrinkFilteredWaterC2SPacket());
            }
        }
    }

    @Mod.EventBusSubscriber(modid = StrangeMod.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ClientModBusEvents {

        @SubscribeEvent
        public static void onKeyRegister(RegisterKeyMappingsEvent event) {
            event.register(KeyBinding.DRINKING_KEY);
        }

        @SubscribeEvent
        public static void registerGuiOverlay(RegisterGuiOverlaysEvent event) {
            event.registerAboveAll("thirst", ThirstHudOverlay.HUD_THIRST);
        }


    }
}
