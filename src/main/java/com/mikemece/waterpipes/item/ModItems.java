package com.mikemece.waterpipes.item;

import com.mikemece.waterpipes.Waterpipes;
import com.mikemece.waterpipes.item.custom.*;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    //Esto es como una lista de items
    public static final DeferredRegister<Item> ITEMS=
            DeferredRegister.create(ForgeRegistries.ITEMS, Waterpipes.MOD_ID);

//primer objeto añadido
    public static final RegistryObject<Item> HOSE = ITEMS.register("hose",
            ()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> VAPE_BATTERY = ITEMS.register("vape_battery",
            ()-> new Item(new Item.Properties()));

    public static final RegistryObject<Item> PIECE_OF_COAL = ITEMS.register("piece_of_coal",
            ()-> new PieceOfCoalItem(new Item.Properties()){});
    public static final RegistryObject<Item> AMETHYST_VAPE = ITEMS.register("amethyst_vape",
            ()-> new AmethystVape(new Item.Properties().durability(100)));
    public static final RegistryObject<Item> COPPER_VAPE = ITEMS.register("copper_vape",
            ()-> new CopperVape(new Item.Properties().durability(100)));
    public static final RegistryObject<Item> DIAMOND_VAPE = ITEMS.register("diamond_vape",
            ()-> new DiamondVape(new Item.Properties().durability(100)));
    public static final RegistryObject<Item> EMERALD_VAPE = ITEMS.register("emerald_vape",
            ()-> new EmeraldVape(new Item.Properties().durability(100)));
    public static final RegistryObject<Item> GOLDEN_VAPE = ITEMS.register("golden_vape",
            ()-> new GoldenVape(new Item.Properties().durability(100)));
    public static final RegistryObject<Item> LAPIS_VAPE = ITEMS.register("lapis_vape",
            ()-> new LapisVape(new Item.Properties().durability(100)));
    public static final RegistryObject<Item> NETHERITE_VAPE = ITEMS.register("netherite_vape",
            ()-> new NetheriteVape(new Item.Properties().durability(100)));
    public static final RegistryObject<Item> OBSIDIAN_VAPE = ITEMS.register("obsidian_vape",
            ()-> new ObsidianVape(new Item.Properties().durability(100)));
    public static final RegistryObject<Item> QUARTZ_VAPE = ITEMS.register("quartz_vape",
            ()-> new QuartzVape(new Item.Properties().durability(100)));
    public static final RegistryObject<Item> REDSTONE_VAPE = ITEMS.register("redstone_vape",
            ()-> new RedstoneVape(new Item.Properties().durability(100)));
    public static final RegistryObject<Item> STONE_VAPE = ITEMS.register("stone_vape",
            ()-> new StoneVape(new Item.Properties().durability(100)));
    public static final RegistryObject<Item> WOODEN_VAPE = ITEMS.register("wooden_vape",
            ()-> new WoodenVape(new Item.Properties().durability(100)));


    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}
