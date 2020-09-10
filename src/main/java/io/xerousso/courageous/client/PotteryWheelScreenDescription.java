package io.xerousso.courageous.client;

import io.github.cottonmc.cotton.gui.SyncedGuiDescription;
import io.github.cottonmc.cotton.gui.widget.WButton;
import io.github.cottonmc.cotton.gui.widget.WGridPanel;
import io.github.cottonmc.cotton.gui.widget.WToggleButton;
import io.github.cottonmc.cotton.gui.widget.data.HorizontalAlignment;
import io.github.cottonmc.cotton.gui.widget.data.Texture;
import io.github.cottonmc.cotton.gui.widget.icon.ItemIcon;
import io.xerousso.courageous.client.widgets.WHoverToggleButton;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.text.LiteralText;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Identifier;

public class PotteryWheelScreenDescription extends SyncedGuiDescription {

    public PotteryWheelScreenDescription(int syncId, PlayerInventory playerInventory, ScreenHandlerContext context) {
        super(Screens.POTTERY_WHEEL_SCREEN, syncId, playerInventory, getBlockInventory(context, 1), getBlockPropertyDelegate(context));

        WGridPanel root = new WGridPanel(1);
        setRootPanel(root);
        setTitleAlignment(HorizontalAlignment.CENTER);
        root.setSize(230, 219);

        root.add(new WButton(new TranslatableText("gui.courageous.pottery_wheel.make")), 54, 109, 105, 20);
        root.add(new WHoverToggleButton(new Texture(new Identifier("courageous:textures/gui/loop_buttons.png"), 20 / 64f, 0,        40 / 64f, 20 / 64f),
                                        new Texture(new Identifier("courageous:textures/gui/loop_buttons.png"), 0,        0,        20 / 64f, 20 / 64f),
                                        new Texture(new Identifier("courageous:textures/gui/loop_buttons.png"), 20 / 64f, 20 / 64f, 40 / 64f, 40 / 64f),
                                        new Texture(new Identifier("courageous:textures/gui/loop_buttons.png"), 0,        20 / 64f, 20 / 64f, 40 / 64f)),
                159, 109, 20, 20);

        root.add(this.createPlayerInventoryPanel(), 36, 137);

        root.validate(this);
    }

}
