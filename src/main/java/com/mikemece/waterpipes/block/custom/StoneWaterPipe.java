package com.mikemece.waterpipes.block.custom;

import com.mikemece.waterpipes.block.entity.ModBlockEntities;
import com.mikemece.waterpipes.block.entity.custom.SWPEntity;
import com.mikemece.waterpipes.sound.ModSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.Nullable;


public class StoneWaterPipe extends BaseEntityBlock {
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    public static final BooleanProperty ISOFF =BooleanProperty.create("isoff");

    public StoneWaterPipe(Properties properties) {
        super(properties);
    }

    private static final VoxelShape SHAPE =  Block.box(5, 0, 5, 11, 16, 11);

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return SHAPE;
    }

    /* FACING */

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        return this.defaultBlockState().setValue(FACING, pContext.getHorizontalDirection().getOpposite());
    }

    @Override
    public BlockState rotate(BlockState pState, Rotation pRotation) {
        return pState.setValue(FACING, pRotation.rotate(pState.getValue(FACING)));
    }

    @Override
    public BlockState mirror(BlockState pState, Mirror pMirror) {
        return pState.rotate(pMirror.getRotation(pState.getValue(FACING)));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(FACING);
        pBuilder.add(ISOFF);
    }

    @Override
    public RenderShape getRenderShape(BlockState pState) {
        return RenderShape.MODEL;
    }

    @Override
    public void onRemove(BlockState pState, Level pLevel, BlockPos pPos, BlockState pNewState, boolean pIsMoving) {
        if (pState.getBlock() != pNewState.getBlock()) {
            BlockEntity blockEntity = pLevel.getBlockEntity(pPos);
            if (blockEntity instanceof SWPEntity) {
                ((SWPEntity) blockEntity).drops();
            }
        }
        super.onRemove(pState, pLevel, pPos, pNewState, pIsMoving);
    }


    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos,
                                 Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        if (!pLevel.isClientSide()) {
            BlockEntity entity = pLevel.getBlockEntity(pPos);
            boolean isOff = pState.getValue(ISOFF);
            boolean recipe = SWPEntity.hasRecipe((SWPEntity) entity);

            //Si está encendida y se le da con un mechero se apaga
            if(pPlayer.getItemInHand(pHand).getItem()==Items.FLINT_AND_STEEL && !isOff) {
                pLevel.setBlock(pPos, pState.setValue(ISOFF, true),3);
                pLevel.playSound(null, pPos, SoundEvents.FIRE_EXTINGUISH, SoundSource.BLOCKS,0.5f,1f);

            //Si tiene la receta dentro y se le da con un mechero se enciende
            }else if(pPlayer.getItemInHand(pHand).getItem()==Items.FLINT_AND_STEEL && recipe) {
                pLevel.playSound(null, pPos, ModSounds.WATER_PIPE_ON.get(), SoundSource.BLOCKS,1f,1f);
                pLevel.setBlock(pPos, pState.setValue(ISOFF, false),3);


            //Si está encendida y se le da con algo que no sea mechero se fuma
            }else if(!isOff && pPlayer.getItemInHand(pHand).getItem()!=Items.FLINT_AND_STEEL && recipe) {
                pLevel.playSound(null, pPos, ModSounds.WATER_PIPE_SMOKE.get(), SoundSource.BLOCKS,2.5f,1f);
                SWPEntity.craftItem((SWPEntity) entity);
                pPlayer.addEffect(new MobEffectInstance(MobEffects.JUMP,1200, 1));
                pPlayer.addEffect(new MobEffectInstance(MobEffects.HUNGER,1200,1));


            //Si se intenta fumar cuando no tiene materiales, se apaga
            }else if(!isOff && pPlayer.getItemInHand(pHand).getItem()!=Items.FLINT_AND_STEEL && !recipe){
                pLevel.setBlock(pPos, pState.setValue(ISOFF, true),3);
                pLevel.playSound(null, pPos, SoundEvents.FIRE_EXTINGUISH, SoundSource.BLOCKS,0.5f,1f);

            //Si no ocurre nada de lo anterior accedemos al inventario
            }else{
                if (entity instanceof SWPEntity) {
                    NetworkHooks.openScreen(((ServerPlayer) pPlayer), (SWPEntity) entity, pPos);
                } else {
                    throw new IllegalStateException("Our Container provider is missing!");
                }
            }
        }
        return InteractionResult.sidedSuccess(pLevel.isClientSide());
    }

    @Override
    public void animateTick(BlockState pState, Level pLevel, BlockPos pPos, RandomSource pRandom) {
        boolean isOff = pState.getValue(ISOFF);
        if(!isOff){
            float chance = 0.35f;
            float chance2 = 0.25f;
            if(pRandom.nextFloat() > chance) {
                pLevel.addParticle(ParticleTypes.SMOKE, pPos.getX() + 0.5D,
                        pPos.getY() + 0.9D, pPos.getZ() + 0.5D,
                        0d,0.03d,0d);
            }
            if(chance2< pRandom.nextFloat()){
                pLevel.addParticle(ParticleTypes.SMALL_FLAME, pPos.getX() + 0.5D,
                        pPos.getY() + 0.9D, pPos.getZ() + 0.5D,
                        0d,0.03d,0d);
            }
        }
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new SWPEntity(pPos, pState);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level pLevel, BlockState pState, BlockEntityType<T> pBlockEntityType) {
        return createTickerHelper(pBlockEntityType, ModBlockEntities.STONE_WATER_PIPE_ENTITY.get(),
                SWPEntity::tick);
    }
}