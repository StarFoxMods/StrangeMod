package com.StarFoxMods.strangemod.block.entity;

import com.StarFoxMods.strangemod.item.ModItems;
import com.StarFoxMods.strangemod.screen.StrangeChestMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class StrangeChestBlockEntity extends BlockEntity implements MenuProvider {
    public static int extractCoal = 0;
    public static int countAmountOfCrafts = 1;

    private final ItemStackHandler itemHandler = new ItemStackHandler(12){
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
        }
    };

    private LazyOptional<IItemHandler> LazyItemHandler = LazyOptional.empty();

    protected final ContainerData data;
    private int progress = 0;
    private int maxprogress = 78;
    private int burntime = 0;
    private int maxburntime = 0;

    public StrangeChestBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.STRANGE_CHEST.get(), pos, state);
        this.data = new ContainerData() {
            @Override
            public int get(int index) {
                return switch (index) {
                    case 0 -> StrangeChestBlockEntity.this.progress;
                    case 1 -> StrangeChestBlockEntity.this.maxprogress;
                    case 2 -> StrangeChestBlockEntity.this.burntime;
                    case 3 -> StrangeChestBlockEntity.this.maxburntime;
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value) {
                switch (index) {
                    case 0 -> StrangeChestBlockEntity.this.progress = value;
                    case 1 -> StrangeChestBlockEntity.this.maxprogress = value;
                    case 2 -> StrangeChestBlockEntity.this.burntime = value;
                    case 3 -> StrangeChestBlockEntity.this.maxburntime = value;
                }
            }

            @Override
            public int getCount() {
                return 4;
            }
        };
    }

    @Override
    public Component getDisplayName() {
        return Component.literal("Strange Chest");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
        return new StrangeChestMenu(id, inventory, this, this.data);
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if(cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return LazyItemHandler.cast();
        }
        return super.getCapability(cap, side);
    }

    @Override
    public void onLoad() {
        super.onLoad();
        LazyItemHandler = LazyOptional.of(()-> itemHandler);
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        LazyItemHandler.invalidate();
    }

    @Override
    protected void saveAdditional(CompoundTag nbt) {
        nbt.put("inventory", itemHandler.serializeNBT());
        super.saveAdditional(nbt);
    }

    @Override
    public void load(CompoundTag nbt) {
        super.load(nbt);
        itemHandler.deserializeNBT(nbt.getCompound("inventory"));
    }

    public void drops() {
        SimpleContainer inventory = new SimpleContainer(itemHandler.getSlots());
        for (int i = 0; i < itemHandler.getSlots(); i++) {
            inventory.setItem(i,itemHandler.getStackInSlot(i));
        }
        Containers.dropContents(this.level, this.worldPosition, inventory);
    }

    public static void tick(Level level, BlockPos pos, BlockState state, StrangeChestBlockEntity pEntity) {
        if(level.isClientSide()) {
            return;
        }
        if(pEntity.itemHandler.getStackInSlot(1).getItem() == Items.COAL) {
            pEntity.maxburntime = pEntity.maxprogress * 1;
        }
        if(pEntity.itemHandler.getStackInSlot(1).getItem() == Items.COAL_BLOCK) {
            pEntity.maxburntime = pEntity.maxprogress * 10;
        }

        if(hasBlastingRecipe(pEntity)) {
            pEntity.progress++;
            pEntity.burntime++;
            setChanged(level,pos, state);

            if (pEntity.progress >= pEntity.maxprogress) {
                blastItem(pEntity);
            }
            if (pEntity.burntime >= pEntity.maxburntime) {
                pEntity.resetBurnTime();
                setChanged(level,pos, state);
            }
        } else {
            pEntity.resetProgress();
            pEntity.resetBurnTime();
            setChanged(level,pos, state);
        }
    }

    private void resetProgress() {
        this.progress = 0;
    }
    private void resetBurnTime() {
        this.burntime = 0;
    }

    private static void blastItem(StrangeChestBlockEntity pEntity) {
        if(hasBlastingRecipe(pEntity)) {
            if(pEntity.itemHandler.getStackInSlot(1).getItem() == Items.COAL){   // SETUP DIFFERENT AMOUNT OF CRAFTS FOR COAL AND BLOCK OF COAL
                if(countAmountOfCrafts >= 1) {
                    extractCoal = 1;
                    countAmountOfCrafts = 0;
                }
            }
            if(pEntity.itemHandler.getStackInSlot(1).getItem() == Items.COAL_BLOCK){
                 if(countAmountOfCrafts >= 10) {
                     extractCoal = 1;
                     countAmountOfCrafts = 0;
                 }
            }

            pEntity.itemHandler.extractItem(0,1, false);
            pEntity.itemHandler.extractItem(1, extractCoal, false);
            pEntity.itemHandler.setStackInSlot(2, new ItemStack(ModItems.STRANGE_MATERIAL.get(),
                    pEntity.itemHandler.getStackInSlot(2).getCount() + 1));
            pEntity.resetProgress();
            countAmountOfCrafts = countAmountOfCrafts + 1;
            extractCoal = 0;
        }
    }

    private static boolean hasBlastingRecipe(StrangeChestBlockEntity entity) {
        SimpleContainer inventory = new SimpleContainer(entity.itemHandler.getSlots());
        for (int i = 0; i < entity.itemHandler.getSlots(); i++) {
            inventory.setItem(i, entity.itemHandler.getStackInSlot(i));
        }

        boolean hasRawStrangeMaterialInFirstSlot = entity.itemHandler.getStackInSlot(0).getItem() == ModItems.RAW_STRANGE_MATERIAL.get() &&
                entity.itemHandler.getStackInSlot(1).getItem() == Items.COAL || entity.itemHandler.getStackInSlot(1).getItem() == Items.COAL_BLOCK;

        return hasRawStrangeMaterialInFirstSlot && canInsertAmountIntoOutputSlot (inventory) &&
                canInsertItemIntoOutputSlot (inventory, new ItemStack(ModItems.STRANGE_MATERIAL.get(), 2));
    }

    private static boolean canInsertItemIntoOutputSlot(SimpleContainer inventory, ItemStack stack) {
        return inventory.getItem(2).getItem() == stack.getItem() || inventory.getItem(2).isEmpty();
    }

    private static boolean canInsertAmountIntoOutputSlot(SimpleContainer inventory) {
        return inventory.getItem(2).getMaxStackSize() > inventory.getItem(2).getCount();
    }
}

