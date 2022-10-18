package com.StarFoxMods.strangemod.event;

import com.StarFoxMods.strangemod.StrangeMod;
import com.StarFoxMods.strangemod.item.ModItems;
import com.StarFoxMods.strangemod.villager.ModVillagers;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
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
}