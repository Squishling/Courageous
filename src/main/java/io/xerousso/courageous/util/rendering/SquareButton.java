package io.xerousso.courageous.util.rendering;

import io.xerousso.courageous.util.Util;
import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SquareButton extends Button {
    private ResourceLocation resourceLocation = new ResourceLocation(Util.MOD_ID, "textures/gui/item_button.png");
    private final int xTexStart;
    private final int yTexStart;
    private final int field_212935_e;
    private final int field_212936_f;

    private boolean selected = false;

    public SquareButton(int x, int y, Button.IPressable onPress) {
        this(x, y, 22, 22, onPress, false);
    }

    public SquareButton(int x, int y, Button.IPressable onPress, boolean selected) {
        this(x, y, 22, 22, onPress, selected);
    }

    public SquareButton(int xIn, int yIn, int widthIn, int heightIn, Button.IPressable onPressIn, boolean selected) {
        this(xIn, yIn, widthIn, heightIn, 256, 256, onPressIn, selected);
    }

    public SquareButton(int xIn, int yIn, int widthIn, int heightIn, int p_i51135_9_, int p_i51135_10_, Button.IPressable onPressIn, boolean selected) {
        this(xIn, yIn, widthIn, heightIn, p_i51135_9_, p_i51135_10_, onPressIn, "", selected);
    }

    public SquareButton(int xIn, int yIn, int widthIn, int heightIn, int p_i51136_9_, int p_i51136_10_, Button.IPressable onPressIn, String textIn, boolean selected) {
        super(xIn, yIn, widthIn, heightIn, textIn, onPressIn);
        this.field_212935_e = p_i51136_9_;
        this.field_212936_f = p_i51136_10_;
        this.xTexStart = 0;
        this.yTexStart = 0;
        this.selected = selected;
    }

    public void setPosition(int xIn, int yIn) {
        this.x = xIn;
        this.y = yIn;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public void renderButton(int p_renderButton_1_, int p_renderButton_2_, float p_renderButton_3_) {
        Minecraft minecraft = Minecraft.getInstance();
        minecraft.getTextureManager().bindTexture(this.resourceLocation);
        GlStateManager.disableDepthTest();
        int i = this.yTexStart;
        int j = this.xTexStart;
        if (this.isHovered()) i += height;
        if (selected) j += width;

        blit(this.x, this.y, (float)j, (float)i, this.width, this.height, this.field_212935_e, this.field_212936_f);
        GlStateManager.enableDepthTest();
    }

}
