package com.mikemece.waterpipes;

import com.mikemece.waterpipes.block.ModBlocks;
import com.mikemece.waterpipes.block.entity.ModBlockEntities;
import com.mikemece.waterpipes.item.ModItems;
import com.mikemece.waterpipes.networking.ModMessages;
import com.mikemece.waterpipes.screen.ModMenuTypes;
import com.mikemece.waterpipes.screen.WaterPipeScreen;
import com.mikemece.waterpipes.sound.ModSounds;
import com.mojang.logging.LogUtils;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.MinecraftForge;
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

    public static final CreativeModeTab WATERPIPES = new CreativeModeTab(MOD_ID) {
        @Override
        @OnlyIn(Dist.CLIENT)
        public ItemStack makeIcon() {
            return new ItemStack(ModBlocks.AMETHYST_WATER_PIPE.get());
        }
    };
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
