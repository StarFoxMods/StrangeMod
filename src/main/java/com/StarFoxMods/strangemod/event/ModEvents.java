package com.StarFoxMods.strangemod.event;

import com.StarFoxMods.strangemod.StrangeMod;
import com.StarFoxMods.strangemod.client.ClientThirstData;
import com.StarFoxMods.strangemod.item.ModItems;
import com.StarFoxMods.strangemod.networking.ModMessages;
import com.StarFoxMods.strangemod.networking.packet.ThirstDataSyncS2CPacket;
import com.StarFoxMods.strangemod.thirst.PlayerThirst;
import com.StarFoxMods.strangemod.thirst.PlayerThirstProvider;
import com.StarFoxMods.strangemod.villager.ModVillagers;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.common.Mod;

import java.util.List;

@Mod.EventBusSubscriber(modid = StrangeMod.MOD_ID)
public class ModEvents {
    @SubscribeEvent
    public static void addCustomTrades(VillagerTradesEvent event) {
        if (event.getType() == ModVillagers.STANGE_TRADER.get()) {
            Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();
            ItemStack stack = new ItemStack(Items.DIAMOND, 2);
            int villagerLevel = 1;

            trades.get(villagerLevel).add((trader, rand) -> new MerchantOffer(
                    new ItemStack(ModItems.STRANGE_MATERIAL.get(), 1),
                    stack, 10, 8, 0.02F));
        }
        if (event.getType() == ModVillagers.STANGE_TRADER.get()) {
            Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();
            ItemStack stack = new ItemStack(Items.EMERALD, 2);
            int villagerLevel = 1;

            trades.get(villagerLevel).add((trader, rand) -> new MerchantOffer(
                    new ItemStack(ModItems.STRANGE_MATERIAL.get(), 1),
                    stack, 10, 8, 0.02F));
        }

        //  Copy Event for new Trade
        //
        //  if (event.getType() == ModVillagers.STANGE_TRADER.get()) {
        //      Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();
        //      ItemStack stack = new ItemStack(Items.EMERALD, 2);
        //      int villagerLevel = 1;
        //
        //      trades.get(villagerLevel).add((trader, rand) -> new MerchantOffer(
        //              new ItemStack(ModItems.RAW_STRANGE_MATERIAL.get(), 1),
        //              stack, 10, 8, 0.02F));
        //  }
        //
        //  When ItemStack contains Minecraft Item use Items.NAME
        //  When ItemStack contains ModBlock Item use ModItems.NAME.get()
        //
        //  Definition ...stack, 10 (max. amount of trades), 8 (XP dropped), 0.02F (Multiplier for price)
    }

    @SubscribeEvent
    public static void onAttachCapabilitiesPlayer(AttachCapabilitiesEvent<Entity> event) {
        if(event.getObject() instanceof Player) {
            if(!event.getObject().getCapability(PlayerThirstProvider.PLAYER_THIRST).isPresent()) {
                event.addCapability(new ResourceLocation(StrangeMod.MOD_ID, "properties"), new PlayerThirstProvider());
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerCloned(PlayerEvent.Clone event) {
        if(event.isWasDeath()) {
            event.getOriginal().getCapability(PlayerThirstProvider.PLAYER_THIRST).ifPresent(oldStore -> {
                event.getOriginal().getCapability(PlayerThirstProvider.PLAYER_THIRST).ifPresent(newStore -> {
                    newStore.copyFrom(oldStore);
                });
            });
        }
    }

    @SubscribeEvent
    public static void onRegisterCapabilities(RegisterCapabilitiesEvent event) {
        event.register(PlayerThirst.class);
    }

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if(event.side == LogicalSide.SERVER) {
            event.player.getCapability(PlayerThirstProvider.PLAYER_THIRST).ifPresent(thirst -> {
                if(thirst.getThirst() > 0 && event.player.getRandom().nextFloat() < 0.001F) { //APPROX. EACH 30 S THIRST INCREASES BY 1, EMPTY THIRST AFTER APPROX. 10 MINUTES
                    thirst.subThirst(1);
                    //event.player.sendSystemMessage(Component.literal("Current Thirst " + thirst.getThirst()));
                    //event.player.sendSystemMessage(Component.literal("ROUND_HALF_FILLED " + ClientThirstData.round_thirst_half_filled));
                    //event.player.sendSystemMessage(Component.literal("HALF_FILLED " + ClientThirstData.half_filled));
                    //event.player.sendSystemMessage(Component.literal("ROUND_FILLED " + ClientThirstData.round_thirst_filled));
                    //event.player.sendSystemMessage(Component.literal("FILLED " + ClientThirstData.filled));
                    ModMessages.sendToPlayer(new ThirstDataSyncS2CPacket(thirst.getThirst()), (ServerPlayer) event.player);
                }
            });
        }
    }

    @SubscribeEvent
    public static void onPlayerJoinWorld(EntityJoinLevelEvent event) {
        if(!event.getLevel().isClientSide()) {
            if(event.getEntity() instanceof ServerPlayer player) {
                player.getCapability(PlayerThirstProvider.PLAYER_THIRST).ifPresent(thirst -> {
                    ModMessages.sendToPlayer(new ThirstDataSyncS2CPacket(thirst.getThirst()), player);
                });
            }
        }
    }
}