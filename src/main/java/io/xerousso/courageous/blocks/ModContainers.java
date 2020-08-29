package io.xerousso.courageous.blocks;

import io.xerousso.courageous.blocks.pottery_wheel.PotteryWheelContainer;
import io.xerousso.courageous.util.Util;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.common.extensions.IForgeContainerType;

import java.util.ArrayList;

public class ModContainers {

    public static final ArrayList<Container> CONTAINERS = new ArrayList<Container>();
    public static final ArrayList<ContainerType> CONTAINER_TYPES = new ArrayList<ContainerType>();


    public static final ContainerType POTTERY_WHEEL_CONTAINER = IForgeContainerType.create((windowId, inv, data) ->
            new PotteryWheelContainer(windowId, inv.player.world, data.readBlockPos(), inv)).setRegistryName(Util.MOD_ID, "pottery_wheel");
//
//    public static final ContainerType CUTTING_BOARD_CONTAINER = IForgeContainerType.create((windowId, inv, data) ->
//            new CuttingBoardContainer(windowId, inv.player.world, data.readBlockPos(), inv)).setRegistryName(Util.MOD_ID, "cutting_board");
//
//    public static final ContainerType ARCHITECTS_TABLE_CONTAINER = IForgeContainerType.create((windowId, inv, data) ->
//            new ArchitectsTableContainer(windowId, inv.player.world, data.readBlockPos(), inv)).setRegistryName(Util.MOD_ID, "architects_table");

}
