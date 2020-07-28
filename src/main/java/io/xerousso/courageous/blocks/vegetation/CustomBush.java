package io.xerousso.courageous.blocks.vegetation;

import io.xerousso.courageous.blocks.IBlock;
import io.xerousso.courageous.tabs.WorldTab;
import io.xerousso.courageous.util.lib.DefaultBlockProperties;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.ItemGroup;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.common.IShearable;

public class CustomBush extends BushBlock implements IShearable, IBlock {

    private Block[] acceptedBlocks;
    private VoxelShape shape;

    public CustomBush(String name, VoxelShape shape, Block... acceptedBlocks) {
        super(Block.Properties.create(Material.TALL_PLANTS, MaterialColor.WOOD).doesNotBlockMovement().sound(SoundType.PLANT).hardnessAndResistance(0));

        this.acceptedBlocks = acceptedBlocks;
        this.shape = shape;

        DefaultBlockProperties.defaults(this, name);
    }

    public CustomBush(String name, VoxelShape shape) {
        this(name, shape, Blocks.GRASS_BLOCK, Blocks.PODZOL, Blocks.DIRT, Blocks.COARSE_DIRT);
    }

    public CustomBush(String name, Block... blocks) {
        this(name, Block.makeCuboidShape(2.0D, 0.0D, 2.0D, 14.0D, 13.0D, 14.0D), blocks);
    }

    public CustomBush(String name) {
        this(name, Blocks.GRASS_BLOCK, Blocks.PODZOL, Blocks.DIRT, Blocks.COARSE_DIRT);
    }

    protected boolean isValidGround(BlockState state, IBlockReader worldIn, BlockPos pos) {
        for (Block block : acceptedBlocks) if (state.getBlock() == block) return true;
        if (BlockTags.SAND.contains(state.getBlock())) return true;
        return false;
    }

    public Block.OffsetType getOffsetType() {
        return Block.OffsetType.XZ;
    }

    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        Vec3d vec3d = state.getOffset(worldIn, pos);
        return shape.withOffset(vec3d.x, vec3d.y, vec3d.z);
    }

    @Override
    public ItemGroup getTab() {
        return WorldTab.WORLD;
    }

}
