package io.xerousso.courageous.blocks;

import io.xerousso.courageous.blocks.block_entities.PotteryWheelBlockEntity;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class PotteryWheel extends BlockWithEntity implements BlockEntityProvider {

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
    }

    @Override
    public @Nullable BlockEntity createBlockEntity(BlockView world) {
        return new PotteryWheelBlockEntity();
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

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

}
