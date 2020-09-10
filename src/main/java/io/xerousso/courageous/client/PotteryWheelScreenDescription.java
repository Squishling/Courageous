package io.xerousso.courageous.client;

import io.github.cottonmc.cotton.gui.SyncedGuiDescription;
import io.github.cottonmc.cotton.gui.widget.WGridPanel;
import io.github.cottonmc.cotton.gui.widget.WItemSlot;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.ScreenHandlerContext;

public class PotteryWheelScreenDescription extends SyncedGuiDescription {

    public PotteryWheelScreenDescription(int syncId, PlayerInventory playerInventory, ScreenHandlerContext context) {
        super(Screens.POTTERY_WHEEL_SCREEN, syncId, playerInventory, getBlockInventory(context, 1), getBlockPropertyDelegate(context));

        WGridPanel root = new WGridPanel();
        setRootPanel(root);
        root.setSize(230, 219);

        WItemSlot itemSlot = WItemSlot.of(blockInventory, 0);
        root.add(itemSlot, 4, 1);

        root.add(this.createPlayerInventoryPanel(), 0, 3);

        root.validate(this);
    }

}
