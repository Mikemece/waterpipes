package com.mikemece.waterpipes.block.entity;

import com.mikemece.waterpipes.Waterpipes;
import com.mikemece.waterpipes.block.ModBlocks;
import com.mikemece.waterpipes.block.entity.custom.*;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntities {

    // IF THE HUD OPENS AND CLOSES INSTANTLY REMEMBER TO CHANGE IN THE WaterPipeMenu the stillValid() function!!!

    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, Waterpipes.MOD_ID);

    public static final RegistryObject<BlockEntityType<AWPEntity>> AMETHYST_WATER_PIPE_ENTITY =
            BLOCK_ENTITIES.register("amethyst_water_pipe_entity", () ->
                    BlockEntityType.Builder.of(AWPEntity::new,
                            ModBlocks.AMETHYST_WATER_PIPE.get()).build(null));

    public static final RegistryObject<BlockEntityType<RWPEntity>> REDSTONE_WATER_PIPE_ENTITY =
            BLOCK_ENTITIES.register("redstone_water_pipe_entity", () ->
                    BlockEntityType.Builder.of(RWPEntity::new,
                            ModBlocks.REDSTONE_WATER_PIPE.get()).build(null));
    public static final RegistryObject<BlockEntityType<EWPEntity>> EMERALD_WATER_PIPE_ENTITY =
            BLOCK_ENTITIES.register("emerald_water_pipe_entity", () ->
                    BlockEntityType.Builder.of(EWPEntity::new,
                            ModBlocks.EMERALD_WATER_PIPE.get()).build(null));
    public static final RegistryObject<BlockEntityType<OWPEntity>> OBSIDIAN_WATER_PIPE_ENTITY =
            BLOCK_ENTITIES.register("obsidian_water_pipe_entity", () ->
                    BlockEntityType.Builder.of(OWPEntity::new,
                            ModBlocks.OBSIDIAN_WATER_PIPE.get()).build(null));

    public static final RegistryObject<BlockEntityType<DWPEntity>> DIAMOND_WATER_PIPE_ENTITY =
            BLOCK_ENTITIES.register("diamond_water_pipe_entity", () ->
                    BlockEntityType.Builder.of(DWPEntity::new,
                            ModBlocks.DIAMOND_WATER_PIPE.get()).build(null));
    public static final RegistryObject<BlockEntityType<LWPEntity>> LAPIS_WATER_PIPE_ENTITY =
            BLOCK_ENTITIES.register("lapis_water_pipe_entity", () ->
                    BlockEntityType.Builder.of(LWPEntity::new,
                            ModBlocks.LAPIS_WATER_PIPE.get()).build(null));
    public static final RegistryObject<BlockEntityType<CWPEntity>> COPPER_WATER_PIPE_ENTITY =
            BLOCK_ENTITIES.register("copper_water_pipe_entity", () ->
                    BlockEntityType.Builder.of(CWPEntity::new,
                            ModBlocks.COPPER_WATER_PIPE.get()).build(null));
    public static final RegistryObject<BlockEntityType<WWPEntity>> WOODEN_WATER_PIPE_ENTITY =
            BLOCK_ENTITIES.register("wooden_water_pipe_entity", () ->
                    BlockEntityType.Builder.of(WWPEntity::new,
                            ModBlocks.WOODEN_WATER_PIPE.get()).build(null));
    public static final RegistryObject<BlockEntityType<SWPEntity>> STONE_WATER_PIPE_ENTITY =
            BLOCK_ENTITIES.register("stone_water_pipe_entity", () ->
                    BlockEntityType.Builder.of(SWPEntity::new,
                            ModBlocks.STONE_WATER_PIPE.get()).build(null));
    public static final RegistryObject<BlockEntityType<QWPEntity>> QUARTZ_WATER_PIPE_ENTITY =
            BLOCK_ENTITIES.register("quartz_water_pipe_entity", () ->
                    BlockEntityType.Builder.of(QWPEntity::new,
                            ModBlocks.QUARTZ_WATER_PIPE.get()).build(null));
    public static final RegistryObject<BlockEntityType<NWPEntity>> NETHERITE_WATER_PIPE_ENTITY =
            BLOCK_ENTITIES.register("netherite_water_pipe_entity", () ->
                    BlockEntityType.Builder.of(NWPEntity::new,
                            ModBlocks.NETHERITE_WATER_PIPE.get()).build(null));
    public static final RegistryObject<BlockEntityType<GWPEntity>> GOLDEN_WATER_PIPE_ENTITY =
            BLOCK_ENTITIES.register("golden_water_pipe_entity", () ->
                    BlockEntityType.Builder.of(GWPEntity::new,
                            ModBlocks.GOLDEN_WATER_PIPE.get()).build(null));
    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}
