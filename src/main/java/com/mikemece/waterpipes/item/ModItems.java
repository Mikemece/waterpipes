package com.mikemece.waterpipes.item;

import com.mikemece.waterpipes.Waterpipes;
import com.mikemece.waterpipes.item.custom.PieceOfCoalItem;
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
            ()-> new Item(new Item.Properties().tab(Waterpipes.WATERPIPES)));

    public static final RegistryObject<Item> PIECE_OF_COAL = ITEMS.register("piece_of_coal",
            ()-> new PieceOfCoalItem(new Item.Properties().tab(Waterpipes.WATERPIPES)){});



    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}