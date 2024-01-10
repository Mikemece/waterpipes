package com.mikemece.waterpipes.sound;

import com.mikemece.waterpipes.Waterpipes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModSounds {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS =
            DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, Waterpipes.MOD_ID);


    private static RegistryObject<SoundEvent> registerSoundEvent(String name) {
        return SOUND_EVENTS.register(name, () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(Waterpipes.MOD_ID, name)));
    }

    //Primer sonido registrado
    public static RegistryObject<SoundEvent> WATER_PIPE_ON = registerSoundEvent("water_pipe_on");
    public static RegistryObject<SoundEvent> WATER_PIPE_SMOKE = registerSoundEvent("water_pipe_smoke");
    public static RegistryObject<SoundEvent> VAPING = registerSoundEvent("vaping");

    public static void register(IEventBus eventBus){
        SOUND_EVENTS.register(eventBus);
    }
}
