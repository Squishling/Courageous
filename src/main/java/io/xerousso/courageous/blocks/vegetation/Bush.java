package io.xerousso.courageous.blocks.vegetation;

import io.xerousso.courageous.items.IItem;
import io.xerousso.courageous.tabs.WorldTab;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.ItemGroup;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.IBlockReader;

import java.util.function.Function;

public class Bush extends BushBlock implements IItem {

    private Function<BlockState, Boolean> groundCheck;
    private VoxelShape shape;

    public Bush(Function<BlockState, Boolean> groundCheck, VoxelShape shape) {
        super(Block.Properties.create(Material.TALL_PLANTS, MaterialColor.WOOD).doesNotBlockMovement().sound(SoundType.PLANT).hardnessAndResistance(0));

        this.groundCheck = groundCheck;
        this.shape = shape;
    }

    public Bush(VoxelShape shape) {
        this(state -> state.isIn(Blocks.GRASS_BLOCK) || state.isIn(Blocks.DIRT) || state.isIn(Blocks.COARSE_DIRT) || state.isIn(Blocks.PODZOL) || state.isIn(Blocks.FARMLAND), shape);
    }

    public Bush(Function<BlockState, Boolean> groundCheck) {
        this(groundCheck, Block.makeCuboidShape(2.0D, 0.0D, 2.0D, 14.0D, 13.0D, 14.0D));
    }

    public Bush() {
        this(state -> state.isIn(Blocks.GRASS_BLOCK) || state.isIn(Blocks.DIRT) || state.isIn(Blocks.COARSE_DIRT) || state.isIn(Blocks.PODZOL) || state.isIn(Blocks.FARMLAND));
    }

    protected boolean isValidGround(BlockState state, IBlockReader worldIn, BlockPos pos) {
        return groundCheck.apply(state);
    }

    public Block.OffsetType getOffsetType() {
        return Block.OffsetType.XZ;
    }

    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        Vector3d vec3d = state.getOffset(worldIn, pos);
        return shape.withOffset(vec3d.x, vec3d.y, vec3d.z);
    }

    @Override
    public ItemGroup getTab() {
        return WorldTab.WORLD;
    }

}
