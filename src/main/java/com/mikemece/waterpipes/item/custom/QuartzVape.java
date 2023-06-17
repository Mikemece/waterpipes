package com.mikemece.waterpipes.item.custom;

import com.mikemece.waterpipes.sound.ModSounds;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class QuartzVape extends Item {
    public QuartzVape(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand usedHand) {
        player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST,200));
        player.addEffect(new MobEffectInstance(MobEffects.HUNGER,200,1));
        player.getCooldowns().addCooldown(this, 140);
        player.getItemInHand(usedHand).hurtAndBreak(1, player, (playera) -> player.broadcastBreakEvent(usedHand));
        level.playSound(player, player.getX(), player.getY(), player.getZ(), ModSounds.VAPING.get(), SoundSource.PLAYERS, 1.0F, 1.0F);
        AmethystVape.summonParticle(level, player);
        return super.use(level, player, usedHand);
    }


}
