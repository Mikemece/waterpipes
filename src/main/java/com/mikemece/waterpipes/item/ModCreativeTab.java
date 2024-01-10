package com.mikemece.waterpipes.item;

import com.mikemece.waterpipes.Waterpipes;
import com.mikemece.waterpipes.block.ModBlocks;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Waterpipes.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModCreativeTab {

    public static CreativeModeTab WATERPIPES_TAB;

    //Para el creative tab
    @SubscribeEvent
    public static void registerCreativeModeTabs(CreativeModeTabEvent.Register event){
        WATERPIPES_TAB = event.registerCreativeModeTab(new ResourceLocation(Waterpipes.MOD_ID, "waterpipes_tab"),
                builder -> builder.icon(()-> new ItemStack(ModBlocks.AMETHYST_WATER_PIPE.get())).title(Component.literal("Useful Waterpipes")).build());
    }
}
