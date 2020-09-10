package io.xerousso.courageous.client.widgets;

import io.github.cottonmc.cotton.gui.client.ScreenDrawing;
import io.github.cottonmc.cotton.gui.widget.WToggleButton;
import io.github.cottonmc.cotton.gui.widget.data.Texture;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class WHoverToggleButton extends WToggleButton {

    protected Texture onImageHover;
    protected Texture offImageHover;

    public WHoverToggleButton(Texture onImage, Texture offImage, Texture onImageHover, Texture offImageHover) {
        super(onImage, offImage);

        this.onImageHover = onImageHover;
        this.offImageHover = offImageHover;
    }

    public WHoverToggleButton() {
        super(  new Texture(new Identifier("courageous:textures/gui/toggle_buttons.png"), 0.5f, 0, 1, 0.5f),
                new Texture(new Identifier("courageous:textures/gui/toggle_buttons.png"), 0, 0, 0.5f, 0.5f));

        onImageHover = new Texture(new Identifier("courageous:textures/gui/toggle_buttons.png"), 0.5f, 0.5f, 1, 1);
        offImageHover = new Texture(new Identifier("courageous:textures/gui/toggle_buttons.png"), 0, 0.5f, 0.5f, 1);
    }

    @Override
    @Environment(EnvType.CLIENT)
    public void paint(MatrixStack matrices, int x, int y, int mouseX, int mouseY) {
        ScreenDrawing.texturedRect(x, y, 20, 20, this.isOn ? isWithinBounds(mouseX, mouseY) ? onImageHover : this.onImage :
                                                                         isWithinBounds(mouseX, mouseY) ? offImageHover : this.offImage, -1);
    }

}
