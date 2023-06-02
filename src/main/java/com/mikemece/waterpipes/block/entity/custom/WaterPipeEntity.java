package com.mikemece.waterpipes.block.entity.custom;

import com.mikemece.waterpipes.item.ModItems;
import com.mikemece.waterpipes.networking.ModMessages;
import com.mikemece.waterpipes.networking.packet.FluidSync;
import com.mikemece.waterpipes.screen.WaterPipeMenu;
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
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.templates.FluidTank;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.annotation.Nonnull;


public class WaterPipeEntity extends BlockEntity implements MenuProvider {

    public WaterPipeEntity(BlockEntityType<?>EntityBlockEntityType, BlockPos pPos, BlockState pBlockState) {
        super(EntityBlockEntityType, pPos, pBlockState);
    }

    //Slots que habrá en el GUI
    private final ItemStackHandler itemHandler = new ItemStackHandler(2) {
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
        }

        @Override
        public boolean isItemValid(int slot, @NotNull ItemStack stack) {
            return switch (slot){
                case 0-> stack.getCapability(ForgeCapabilities.FLUID_HANDLER_ITEM).isPresent();
                case 1 -> stack.getItem()== ModItems.PIECE_OF_COAL.get();
                default ->  super.isItemValid(slot,stack);
            };
        }
    };

    private final FluidTank FLUID_TANK =new FluidTank(5000){
        @Override
        protected void onContentsChanged() {
            setChanged();
            if(!level.isClientSide){
                ModMessages.sendToClients(new FluidSync(this.fluid, worldPosition));
            }
        }

        @Override
        public boolean isFluidValid(FluidStack stack) {
            return stack.getFluid()== Fluids.WATER;
        }
    };




    public void setFluid(FluidStack stack){
        this.FLUID_TANK.setFluid(stack);
    }

    public FluidStack getFluidStack() {
        return this.FLUID_TANK.getFluid();
    }



    private LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.empty();
    private LazyOptional<IFluidHandler> lazyFluidHandler = LazyOptional.empty();

    @Override
    public Component getDisplayName() {
        return Component.literal("Water pipe");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int pContainerId, Inventory pPlayerInventory, Player pPlayer) {
        ModMessages.sendToClients(new FluidSync(this.getFluidStack(), worldPosition));
        return new WaterPipeMenu(pContainerId, pPlayerInventory, this);
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @javax.annotation.Nullable Direction side) {
        if (cap == ForgeCapabilities.ITEM_HANDLER) {
            return lazyItemHandler.cast();
        }

        if(cap == ForgeCapabilities.FLUID_HANDLER){
            return lazyFluidHandler.cast();
        }

        return super.getCapability(cap, side);
    }


    @Override
    public void onLoad() {
        super.onLoad();
        lazyItemHandler = LazyOptional.of(() -> itemHandler);
        lazyFluidHandler = LazyOptional.of(() -> FLUID_TANK);
    }

    @Override
    public void invalidateCaps()  {
        super.invalidateCaps();
        lazyItemHandler.invalidate();
        lazyFluidHandler.invalidate();
    }

    @Override
    protected void saveAdditional(CompoundTag tag) {
        tag.put("inventory", itemHandler.serializeNBT());
        tag = FLUID_TANK.writeToNBT(tag);

        super.saveAdditional(tag);

    }

    @Override
    public void load(CompoundTag nbt) {
        super.load(nbt);
        itemHandler.deserializeNBT(nbt.getCompound("inventory"));
        FLUID_TANK.readFromNBT(nbt);
    }

    public void drops() {
        SimpleContainer inventory = new SimpleContainer(itemHandler.getSlots());
        for (int i = 0; i < itemHandler.getSlots(); i++) {
            inventory.setItem(i, itemHandler.getStackInSlot(i));
        }

        Containers.dropContents(this.level, this.worldPosition, inventory);
    }


    public static void tick(Level pLevel, BlockPos pPos, BlockState pState, WaterPipeEntity pBlockEntity) {
        if(hasFluidItemInSlot(pBlockEntity)){
            transferFluid(pBlockEntity);
        }
        hasRecipe(pBlockEntity);
        if(!pLevel.isClientSide){
            ModMessages.sendToClients(new FluidSync(pBlockEntity.getFluidStack(), pBlockEntity.worldPosition));
        }
    }

    private static void transferFluid(WaterPipeEntity pBlockEntity) {
        pBlockEntity.itemHandler.getStackInSlot(0).getCapability(ForgeCapabilities.FLUID_HANDLER_ITEM).ifPresent(handler -> {
            int drainAmount = Math.min(pBlockEntity.FLUID_TANK.getSpace(), 1000);

            FluidStack stack = handler.drain(drainAmount, IFluidHandler.FluidAction.SIMULATE);
            if(pBlockEntity.FLUID_TANK.isFluidValid(stack)){
                stack = handler.drain(drainAmount, IFluidHandler.FluidAction.EXECUTE);
                fillTankWithFluid(pBlockEntity, stack, handler.getContainer());
            }
        });
    }

    private static void fillTankWithFluid(WaterPipeEntity pBlockEntity, FluidStack stack, ItemStack container) {
        pBlockEntity.FLUID_TANK.fill(stack, IFluidHandler.FluidAction.EXECUTE );

        pBlockEntity.itemHandler.extractItem(0,1,false);
        pBlockEntity.itemHandler.insertItem(0,container,false);
    }


    private static boolean hasFluidItemInSlot(WaterPipeEntity pBlockEntity) {
        return pBlockEntity.itemHandler.getStackInSlot(0).getCount()>0;
    }

    public static void craftItem(WaterPipeEntity entity) {
        entity.FLUID_TANK.drain(250,IFluidHandler.FluidAction.EXECUTE);
        entity.itemHandler.extractItem(1, 1, false);
        }


    public static boolean hasRecipe(WaterPipeEntity entity) {
        boolean hasEnoughFluid = entity.FLUID_TANK.getFluidAmount()>=250;
        boolean hasCoalInFirstSlot = entity.itemHandler.getStackInSlot(1).getItem() == ModItems.PIECE_OF_COAL.get();
        return hasCoalInFirstSlot && hasEnoughFluid;
    }


}

