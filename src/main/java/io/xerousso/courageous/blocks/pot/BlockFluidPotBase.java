package io.xerousso.courageous.blocks.pot;

import io.xerousso.courageous.items.IItem;
import io.xerousso.courageous.tabs.PotteryTab;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.common.extensions.IForgeBlock;

public class BlockFluidPotBase extends Block implements IForgeBlock, IItem {

    private final static VoxelShape potShape = VoxelShapes.combineAndSimplify(makeCuboidShape(2, 0, 2, 14, 13, 14), makeCuboidShape(4, 1, 4, 12, 13, 12), IBooleanFunction.ONLY_FIRST);

    public BlockFluidPotBase(Properties properties) {
        super(properties);
    }

    @Override
    public VoxelShape getShape(BlockState p_220053_1_, IBlockReader p_220053_2_, BlockPos p_220053_3_, ISelectionContext p_220053_4_) {
        return potShape;
    }

    @Override
    public ItemGroup getTab() {
        return PotteryTab.POTTERY;
    }

}
