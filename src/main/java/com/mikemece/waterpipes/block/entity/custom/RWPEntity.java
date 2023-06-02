package com.mikemece.waterpipes.block.entity.custom;

import com.mikemece.waterpipes.block.entity.ModBlockEntities;
import com.mikemece.waterpipes.screen.WaterPipeMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class RWPEntity extends WaterPipeEntity implements MenuProvider {
    public RWPEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.REDSTONE_WATER_PIPE_ENTITY.get(), pPos, pBlockState);
    }

    @Override
    public Component getDisplayName() {
        return Component.literal("Redstone water pipe");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int pContainerId, Inventory pPlayerInventory, Player pPlayer) {
        return new WaterPipeMenu(pContainerId, pPlayerInventory, this);
    }
}
