package com.mikemece.waterpipes;

import com.mikemece.waterpipes.block.ModBlocks;
import com.mikemece.waterpipes.block.entity.ModBlockEntities;
import com.mikemece.waterpipes.item.ModCreativeTab;
import com.mikemece.waterpipes.item.ModItems;
import com.mikemece.waterpipes.networking.ModMessages;
import com.mikemece.waterpipes.screen.ModMenuTypes;
import com.mikemece.waterpipes.screen.WaterPipeScreen;
import com.mikemece.waterpipes.sound.ModSounds;
import com.mojang.logging.LogUtils;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(Waterpipes.MOD_ID)
public class Waterpipes
{
    public static final String MOD_ID = "waterpipes";
    private static final Logger LOGGER = LogUtils.getLogger();

    private void addCreative(CreativeModeTabEvent.BuildContents event){
        if(event.getTab()== ModCreativeTab.WATERPIPES_TAB){
            event.accept(ModBlocks.AMETHYST_WATER_PIPE);
            event.accept(ModBlocks.DIAMOND_WATER_PIPE);
            event.accept(ModBlocks.COPPER_WATER_PIPE);
            event.accept(ModBlocks.OBSIDIAN_WATER_PIPE);
            event.accept(ModBlocks.REDSTONE_WATER_PIPE);
            event.accept(ModBlocks.EMERALD_WATER_PIPE);
            event.accept(ModBlocks.STONE_WATER_PIPE);
            event.accept(ModBlocks.WOODEN_WATER_PIPE);
            event.accept(ModBlocks.GOLDEN_WATER_PIPE);
            event.accept(ModBlocks.LAPIS_WATER_PIPE);
            event.accept(ModBlocks.QUARTZ_WATER_PIPE);
            event.accept(ModBlocks.NETHERITE_WATER_PIPE);
            event.accept(ModItems.DIAMOND_VAPE);
            event.accept(ModItems.AMETHYST_VAPE);
            event.accept(ModItems.COPPER_VAPE);
            event.accept(ModItems.STONE_VAPE);
            event.accept(ModItems.LAPIS_VAPE);
            event.accept(ModItems.GOLDEN_VAPE);
            event.accept(ModItems.WOODEN_VAPE);
            event.accept(ModItems.REDSTONE_VAPE);
            event.accept(ModItems.EMERALD_VAPE);
            event.accept(ModItems.OBSIDIAN_VAPE);
            event.accept(ModItems.QUARTZ_VAPE);
            event.accept(ModItems.NETHERITE_VAPE);
            event.accept(ModItems.HOSE);
            event.accept(ModItems.VAPE_BATTERY);
            event.accept(ModItems.PIECE_OF_COAL);
        }
        if(event.getTab()== CreativeModeTabs.INGREDIENTS){
            event.accept(ModItems.PIECE_OF_COAL);
        }
    }
    public Waterpipes()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);
        ModBlockEntities.register(modEventBus);
        ModMenuTypes.register(modEventBus);
        ModSounds.register(modEventBus);

        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::clientSetup);
        modEventBus.addListener(this::addCreative);


        MinecraftForge.EVENT_BUS.register(this);
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
        event.enqueueWork(() -> {
            ModMessages.register();
        });
        LOGGER.info("HELLO FROM PREINIT");
    }
    @SubscribeEvent
    public void clientSetup(final FMLClientSetupEvent event){
        MenuScreens.register(ModMenuTypes.WATER_PIPE_MENU.get(), WaterPipeScreen::new);
    }

}
