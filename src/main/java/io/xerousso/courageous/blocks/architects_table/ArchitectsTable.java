package io.xerousso.courageous.blocks.architects_table;

import io.xerousso.courageous.blocks.BlockBase;
import io.xerousso.courageous.blocks.IBlock;
import io.xerousso.courageous.tabs.PotteryTab;
import io.xerousso.courageous.util.Util;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemGroup;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.network.NetworkHooks;

import javax.annotation.Nullable;

public class ArchitectsTable extends BlockBase implements IBlock {

    public ArchitectsTable() {
        super("architects_table", Properties.create(Material.WOOD).hardnessAndResistance(0.3f));
    }

    @Override
    public int getHarvestLevel(BlockState state) {
        return 1;
    }

    @Nullable
    @Override
    public ToolType getHarvestTool(BlockState state) {
        return ToolType.AXE;
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult result) {
        if (Util.isServer(world)) {
            TileEntity te = world.getTileEntity(pos);

            if (te instanceof INamedContainerProvider) NetworkHooks.openGui((ServerPlayerEntity) player, (INamedContainerProvider) te, te.getPos());
        }

        return ActionResultType.SUCCESS;
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new ArchitectsTableTileEntity();
    }

    @Override
    public ItemGroup getTab() {
        return PotteryTab.POTTERY;
    }

}
