package io.xerousso.courageous.blocks.planter_box;

import io.xerousso.courageous.blocks.IBlock;
import io.xerousso.courageous.tabs.GeneralTab;
import io.xerousso.courageous.util.lib.DefaultBlockProperties;
import net.minecraft.block.*;
import net.minecraft.block.AbstractBlock.Properties;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.HoeItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShovelItem;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.PlantType;
import net.minecraftforge.items.CapabilityItemHandler;

import java.util.concurrent.atomic.AtomicReference;

public class PlanterBoxBlock extends LogBlock implements IBlock {

    public PlanterBoxBlock() {
        super(MaterialColor.WOOD, Properties.from(Blocks.COMPOSTER).notSolid());
        DefaultBlockProperties.defaults(this, "planterbox");
    }

    @Override
    public ItemGroup getTab() {
        return GeneralTab.GENERAL;
    }

    @Override
    public boolean canSustainPlant(BlockState state, IBlockReader world, BlockPos pos, Direction facing, IPlantable plantable) {
        PlantType type = plantable.getPlantType(world, pos.up());
        AtomicReference<Block> containBlock = new AtomicReference<Block>(Blocks.COMPOSTER);
        TileEntity tileEntity = world.getTileEntity(pos);

        if (tileEntity != null) {
            ((PlanterBoxTileEntity)tileEntity).getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent(handler -> {
                containBlock.set(Block.getBlockFromItem(handler.getStackInSlot(0).getItem()));
            });
        }

        return containBlock.get() == null ? false : containBlock.get().canSustainPlant(state, world, pos, facing, plantable);
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new PlanterBoxTileEntity();
    }

    @Override
    public ActionResultType onBlockActivated(BlockState blockState, World world, BlockPos blockPos, PlayerEntity player, Hand hand, BlockRayTraceResult ray)
    {
        if (player.getHeldItem(hand).getItem() instanceof ShovelItem) {
            world.setBlockState(blockPos, Blocks.COMPOSTER.getDefaultState(), 2);
            return ActionResultType.SUCCESS;
        }
        if (player.getHeldItem(hand).getItem() instanceof HoeItem) {
            TileEntity tileEntity = world.getTileEntity(blockPos);

            if (tileEntity != null) {
                ((PlanterBoxTileEntity)tileEntity).getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent(handler -> {
                    if (handler.getStackInSlot(0).getItem() == Blocks.DIRT.asItem()) {
                        handler.extractItem(0, 1, false);
                        handler.insertItem(0, new ItemStack(Blocks.FARMLAND, 1), false);
                    }
                });
            }
            return ActionResultType.SUCCESS;
        }
        return super.onBlockActivated(blockState, world, blockPos, player, hand, ray);
    }
}
