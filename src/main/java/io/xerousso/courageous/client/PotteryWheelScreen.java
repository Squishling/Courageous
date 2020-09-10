package io.xerousso.courageous.client;

import io.github.cottonmc.cotton.gui.client.CottonInventoryScreen;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;

public class PotteryWheelScreen extends CottonInventoryScreen<PotteryWheelScreenDescription> {

    public PotteryWheelScreen(PotteryWheelScreenDescription description, PlayerEntity player, Text title) {
        super(description, player, title);
        width = 230;
        height = 219;
    }

}
