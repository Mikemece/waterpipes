package com.mikemece.waterpipes.item;

import com.mikemece.waterpipes.Waterpipes;
import com.mikemece.waterpipes.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(modid = Waterpipes.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModCreativeTab {

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Waterpipes.MOD_ID);
    public static RegistryObject<CreativeModeTab> WATERPIPES_TAB = CREATIVE_MODE_TABS.register("waterpipes_tab", ()->
            CreativeModeTab.builder().icon(()-> new ItemStack(ModBlocks.AMETHYST_WATER_PIPE.get()))
                    .title(Component.literal("Useful Waterpipes")).build());

    public static void register(IEventBus eventBus){
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
