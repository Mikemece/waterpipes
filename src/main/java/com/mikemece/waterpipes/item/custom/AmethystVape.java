package com.mikemece.waterpipes.item.custom;

import com.mikemece.waterpipes.sound.ModSounds;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

public class AmethystVape extends Item {
    public AmethystVape(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand usedHand) {
        player.addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION,200));
        player.addEffect(new MobEffectInstance(MobEffects.BAD_OMEN,200));
        player.getCooldowns().addCooldown(this, 140);
        player.getItemInHand(usedHand).hurtAndBreak(1, player, (playera) -> player.broadcastBreakEvent(usedHand));
        level.playSound(player, player.getX(), player.getY(), player.getZ(), ModSounds.VAPING.get(), SoundSource.PLAYERS, 1.0F, 1.0F);
        summonParticle(level, player);
        return super.use(level, player, usedHand);
    }

    public static void summonParticle(Level level, Player player) {
        for (int i = 0; i < 50; i++) {
            double offsetX = (player.getDeltaMovement().x * i * 0.015d);
            double offsetZ = (player.getDeltaMovement().z * i * 0.015d);

            level.addParticle(ParticleTypes.CAMPFIRE_COSY_SMOKE, player.getX(), player.getY() + 0.4d, player.getZ(), 0d+offsetX, 0.05d+(i*0.001d), 0d+offsetZ);
        }
    }

}
