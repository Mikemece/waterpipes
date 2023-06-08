package com.mikemece.waterpipes.item.custom;

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
        //player.getCooldowns().addCooldown(this, 200);
        player.getItemInHand(usedHand).hurtAndBreak(1, player, (playera) -> player.broadcastBreakEvent(usedHand));
        return super.use(level, player, usedHand);
    }

}
