package io.xerousso.courageous.blocks;

import io.xerousso.courageous.blocks.block_entities.PotteryWheelBlockEntity;
import io.xerousso.courageous.client.PotteryStates;
import io.xerousso.courageous.util.Triad;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.Item;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.state.StateManager.Builder;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.Pair;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.HashMap;

public class PotteryWheel extends BlockWithEntity implements BlockEntityProvider {

    public static final EnumProperty<PotteryStates> POTTERY_STATE = EnumProperty.of("pottery_state", PotteryStates.class);
    private VoxelShape SHAPE = VoxelShapes.union(Block.createCuboidShape(0 , 0 , 0, 16, 2 , 16),  // Base

                                                 Block.createCuboidShape(2 , 2 , 0, 14, 5 , 16),  //
                                                 Block.createCuboidShape(0 , 2 , 2, 2 , 5 , 14),  //  Bottom platform
                                                 Block.createCuboidShape(14, 2 , 2, 16, 5 , 14),  //

                                                 Block.createCuboidShape(5 , 5 , 5, 11, 13, 11),  // Pillar

                                                 Block.createCuboidShape(2 , 13, 0, 14, 16, 16),  //
                                                 Block.createCuboidShape(0 , 13, 2, 2 , 16, 14),  //  Top platform
                                                 Block.createCuboidShape(14, 13, 2, 16, 16, 14)); //

    public PotteryWheel() {
        super(FabricBlockSettings.of(Material.WOOD).sounds(BlockSoundGroup.WOOD).nonOpaque());
        setDefaultState(this.getDefaultState().with(POTTERY_STATE, PotteryStates.STILL));
    }

    @Override
    protected void appendProperties(Builder<Block, BlockState> builder) {
        builder.add(POTTERY_STATE);
    }

    @Override
    public @Nullable BlockEntity createBlockEntity(BlockView world) {
        return new PotteryWheelBlockEntity();
    }

    @Override
    @Nullable
    public NamedScreenHandlerFactory createScreenHandlerFactory(BlockState state, World world, BlockPos pos) {
        return world.getBlockEntity(pos) instanceof NamedScreenHandlerFactory ? (NamedScreenHandlerFactory) world.getBlockEntity(pos) : null;
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        player.openHandledScreen(state.createScreenHandlerFactory(world, pos));
        return ActionResult.SUCCESS;
    }

//    @Override
//    public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
//        if (state.getBlock() != newState.getBlock()) {
//            BlockEntity blockEntity = world.getBlockEntity(pos);
//            if (blockEntity instanceof PotteryWheelBlockEntity) {
//                ItemScatterer.spawn(world, pos, blockEntity.getIn);
//                // update comparators
//                world.updateComparators(pos,this);
//            }
//            super.onStateReplaced(state, world, pos, newState, moved);
//        }
//    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return state.get(POTTERY_STATE) == PotteryStates.TURNING ? BlockRenderType.INVISIBLE : BlockRenderType.MODEL;
    }

}
