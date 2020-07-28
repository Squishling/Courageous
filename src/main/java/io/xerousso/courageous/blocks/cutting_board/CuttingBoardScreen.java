package io.xerousso.courageous.blocks.cutting_board;

import io.xerousso.courageous.util.Util;
import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

public class CuttingBoardScreen extends ContainerScreen<CuttingBoardContainer> {

    private ResourceLocation GUI = new ResourceLocation(Util.MOD_ID, "textures/gui/cutting_board.png");

    public CuttingBoardScreen(CuttingBoardContainer container, PlayerInventory playerInventory, ITextComponent name) {
        super(container, playerInventory, name);

        ySize = 176;
    }

    @Override
    public void render(int mouseX, int mouseY, float partialTicks) {
        super.render(mouseX, mouseY, partialTicks);

        renderHoveredToolTip(mouseX, mouseY);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        String s = new TranslationTextComponent("block.courageous.cutting_board").getFormattedText();
        this.font.drawString(s, 8.0F, 6.0F, 4210752);
        this.font.drawString(this.playerInventory.getDisplayName().getFormattedText(), 8.0F, (float)(this.ySize - 96 + 4), 4210752);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        renderBackground();
        GlStateManager.color4f(1f, 1f, 1f, 1f);
        minecraft.getTextureManager().bindTexture(GUI);

        int relX = (width - xSize) / 2;
        int relY = (height - ySize) / 2;
        blit(relX, relY, 0, 0, xSize, ySize);

        if (container.getSlot(0).getStack().isEmpty()) blit(relX + 41, relY + 45, xSize, 0, 16, 16);

        if (container.getSlot(3).getStack().isEmpty()) blit(relX + 82, relY + 17, xSize + 16, 0, 16, 16);
        if (container.getSlot(8).getStack().isEmpty()) blit(relX + 82, relY + 73, xSize + 16, 0, 16, 16);
    }

}
