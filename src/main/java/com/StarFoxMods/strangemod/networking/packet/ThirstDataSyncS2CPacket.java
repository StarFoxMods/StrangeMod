package com.StarFoxMods.strangemod.networking.packet;

import com.StarFoxMods.strangemod.client.ClientThirstData;
import com.StarFoxMods.strangemod.item.ModItems;
import com.StarFoxMods.strangemod.thirst.PlayerThirstProvider;
import net.minecraft.ChatFormatting;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class ThirstDataSyncS2CPacket {
    private final int thirst;

    public ThirstDataSyncS2CPacket(int thirst) {
        this.thirst = thirst;
    }

    public ThirstDataSyncS2CPacket(FriendlyByteBuf pBuffer) {
        this.thirst = pBuffer.readInt();
    }

    public void toBytes(FriendlyByteBuf pBuffer) {
        pBuffer.writeInt(thirst);
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(()-> {
            //CLIENT SIDE
            ClientThirstData.setPlayerThirst(thirst);
        });
        return true;
    }

}
