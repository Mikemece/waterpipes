package com.mikemece.waterpipes.block;

import com.mikemece.waterpipes.Waterpipes;
import com.mikemece.waterpipes.block.custom.*;
import com.mikemece.waterpipes.item.ModItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModBlocks {
    private static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, Waterpipes.MOD_ID);

//Primer bloque registrado

    public static final RegistryObject<Block> AMETHYST_WATER_PIPE = registerBlock("amethyst_water_pipe", ()->new AmethystWaterPipe(BlockBehaviour.Properties.of(Material.GLASS)
            .strength(0.5f).sound(SoundType.GLASS).noOcclusion().lightLevel(
                    (state) -> state.getValue(AmethystWaterPipe.ISOFF) ? 0:8)), Waterpipes.WATERPIPES);

    public static final RegistryObject<Block> REDSTONE_WATER_PIPE = registerBlock("redstone_water_pipe", ()->new RedstoneWaterPipe(BlockBehaviour.Properties.of(Material.GLASS)
            .strength(0.5f).sound(SoundType.GLASS).noOcclusion().lightLevel(
                    (state) -> state.getValue(RedstoneWaterPipe.ISOFF) ? 0:8)), Waterpipes.WATERPIPES);

    public static final RegistryObject<Block> EMERALD_WATER_PIPE = registerBlock("emerald_water_pipe", ()->new EmeraldWaterPipe(BlockBehaviour.Properties.of(Material.GLASS)
            .strength(0.5f).sound(SoundType.GLASS).noOcclusion().lightLevel(
                    (state) -> state.getValue(EmeraldWaterPipe.ISOFF) ? 0:8)), Waterpipes.WATERPIPES);

    public static final RegistryObject<Block> OBSIDIAN_WATER_PIPE = registerBlock("obsidian_water_pipe", ()->new ObsidianWaterPipe(BlockBehaviour.Properties.of(Material.GLASS)
            .strength(1f).sound(SoundType.GLASS).noOcclusion().lightLevel(
                    (state) -> state.getValue(ObsidianWaterPipe.ISOFF) ? 0:8)), Waterpipes.WATERPIPES);
    public static final RegistryObject<Block> DIAMOND_WATER_PIPE = registerBlock("diamond_water_pipe", ()->new DiamondWaterPipe(BlockBehaviour.Properties.of(Material.GLASS)
            .strength(0.5f).sound(SoundType.GLASS).noOcclusion().lightLevel(
                    (state) -> state.getValue(DiamondWaterPipe.ISOFF) ? 0:8)), Waterpipes.WATERPIPES);
    public static final RegistryObject<Block> LAPIS_WATER_PIPE = registerBlock("lapis_water_pipe", ()->new LapisWaterPipe(BlockBehaviour.Properties.of(Material.GLASS)
            .strength(0.5f).sound(SoundType.GLASS).noOcclusion().lightLevel(
                    (state) -> state.getValue(LapisWaterPipe.ISOFF) ? 0:8)), Waterpipes.WATERPIPES);
    public static final RegistryObject<Block> COPPER_WATER_PIPE = registerBlock("copper_water_pipe", ()->new CopperWaterPipe(BlockBehaviour.Properties.of(Material.GLASS)
            .strength(0.5f).sound(SoundType.GLASS).noOcclusion().lightLevel(
                    (state) -> state.getValue(CopperWaterPipe.ISOFF) ? 0:8)), Waterpipes.WATERPIPES);



//Metodos necesarios para los bloques
    private static <T extends Block>RegistryObject<T> registerBlock(String name, Supplier<T> block, CreativeModeTab tab){
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn, tab);
        return toReturn;
    }

    private static <T extends Block>RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block,
                                                                           CreativeModeTab tab){
        return ModItems.ITEMS.register(name, ()-> new BlockItem(block.get(),new Item.Properties().tab(tab) ));
    }

    public static void register(IEventBus eventBus){
        BLOCKS.register(eventBus);
    }
}
