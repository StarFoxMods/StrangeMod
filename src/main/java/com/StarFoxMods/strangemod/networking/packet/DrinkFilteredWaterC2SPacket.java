package com.StarFoxMods.strangemod.networking.packet;

import com.StarFoxMods.strangemod.item.ModItems;
import com.StarFoxMods.strangemod.networking.ModMessages;
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

public class DrinkFilteredWaterC2SPacket {

    public static final String MESSAGE_DRINK_FILTERED_WATER = "message.strangemod.drink_filtered_water";
    public static final String MESSAGE_NO_FILTERED_WATER = "message.strangemod.no_filtered_water";

    public DrinkFilteredWaterC2SPacket(){

    }

    public DrinkFilteredWaterC2SPacket(FriendlyByteBuf pBuffer) {

    }

    public void toBytes(FriendlyByteBuf pBuffer) {

    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(()-> {
            //SERVER SIDE
            ServerPlayer pPlayer = context.getSender();
            ServerLevel pLevel = pPlayer.getLevel();

            if(hasFilteredWaterInHotbar(pPlayer, pLevel)) {
                pPlayer.sendSystemMessage(Component.translatable(MESSAGE_DRINK_FILTERED_WATER).withStyle(ChatFormatting.DARK_AQUA));
                pLevel.playSound(null, pPlayer.getOnPos(), SoundEvents.GENERIC_DRINK, SoundSource.PLAYERS,
                        0.5F,pLevel.random.nextFloat() * 0.1F + 0.9F);
                pPlayer.getCapability(PlayerThirstProvider.PLAYER_THIRST).ifPresent(thirst -> {
                    thirst.addThirst(8);
                    //pPlayer.sendSystemMessage(Component.literal("Current Thirst " + thirst.getThirst())
                    //    .withStyle(ChatFormatting.DARK_AQUA));
                    ModMessages.sendToPlayer(new ThirstDataSyncS2CPacket(thirst.getThirst()), pPlayer);
                });
            } else {
                pPlayer.sendSystemMessage(Component.translatable(MESSAGE_NO_FILTERED_WATER).withStyle(ChatFormatting.DARK_RED));
                pPlayer.getCapability(PlayerThirstProvider.PLAYER_THIRST).ifPresent(thirst -> {
                    //pPlayer.sendSystemMessage(Component.literal("Current Thirst " + thirst.getThirst())
                    //        .withStyle(ChatFormatting.DARK_AQUA));
                    ModMessages.sendToPlayer(new ThirstDataSyncS2CPacket(thirst.getThirst()), pPlayer);
                });
            }

        });
        return true;
    }

    private boolean hasFilteredWaterInHotbar(ServerPlayer pPlayer, ServerLevel pLevel) {
       ItemStack item = new ItemStack(ModItems.STRANGE_MATERIAL.get());
        return pPlayer.getInventory().contains(item);
    }
}
