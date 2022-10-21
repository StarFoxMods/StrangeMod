package com.StarFoxMods.strangemod.networking;

import com.StarFoxMods.strangemod.StrangeMod;
import com.StarFoxMods.strangemod.networking.packet.DrinkFilteredWaterC2SPacket;
import com.StarFoxMods.strangemod.networking.packet.ThirstDataSyncS2CPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;

public class ModMessages {
    public static SimpleChannel INSTANCE;
    public static int packetId = 0;
    public static int id() {
        return packetId++;
    }

    public static void register() {
        SimpleChannel net = NetworkRegistry.ChannelBuilder
                .named(new ResourceLocation(StrangeMod.MOD_ID, "messages"))
                .networkProtocolVersion(()-> "1.0")
                .clientAcceptedVersions(s -> true)
                .serverAcceptedVersions(s -> true)
                .simpleChannel();

        INSTANCE = net;

        net.messageBuilder(DrinkFilteredWaterC2SPacket.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(DrinkFilteredWaterC2SPacket::new)
                .encoder(DrinkFilteredWaterC2SPacket::toBytes)
                .consumerMainThread(DrinkFilteredWaterC2SPacket::handle)
                .add();

        net.messageBuilder(ThirstDataSyncS2CPacket.class, id(), NetworkDirection.PLAY_TO_CLIENT)
                .decoder(ThirstDataSyncS2CPacket::new)
                .encoder(ThirstDataSyncS2CPacket::toBytes)
                .consumerMainThread(ThirstDataSyncS2CPacket::handle)
                .add();
    }

    public static <MSG> void sendToServer(MSG message){
        INSTANCE.sendToServer(message);
    }

    public static <MSG> void sendToPlayer(MSG message, ServerPlayer pPlayer){
        INSTANCE.send(PacketDistributor.PLAYER.with(()-> pPlayer), message);
    }
}
