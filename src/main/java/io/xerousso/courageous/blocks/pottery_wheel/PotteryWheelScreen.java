package io.xerousso.courageous.blocks.pottery_wheel;

import com.mojang.blaze3d.matrix.MatrixStack;
import io.xerousso.courageous.util.Util;
import io.xerousso.courageous.util.networking.ModPacketHandler;
import io.xerousso.courageous.util.networking.PacketPotterySelect;
//import io.xerousso.courageous.util.rendering.SquareButton;
import com.mojang.blaze3d.platform.GlStateManager;
import io.xerousso.courageous.util.rendering.SquareButton;
import javafx.util.Pair;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

import java.util.HashMap;

public class PotteryWheelScreen extends ContainerScreen<PotteryWheelContainer> {

    private ResourceLocation GUI = new ResourceLocation(Util.MOD_ID, "textures/gui/pottery_wheel.png");
    private int selected;

    private HashMap<Item, javafx.util.Pair<Integer, Integer>> ICONS = new HashMap<Item, javafx.util.Pair<Integer, Integer>>();

    public PotteryWheelScreen(PotteryWheelContainer container, PlayerInventory playerInventory, ITextComponent name) {
        super(container, playerInventory, name);
    }

    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        super.render(matrixStack, mouseX, mouseY, partialTicks);

        GlStateManager.translatef(0.0F, 0.0F, 32.0F);
        for (Item item : ICONS.keySet()) {
            drawItemStack(new ItemStack(item), ICONS.get(item).getKey(), ICONS.get(item).getValue());
        }

        GlStateManager.color4f(1f, 1f, 1f, 1f);
        minecraft.getTextureManager().bindTexture(GUI);
        int relX = (width - xSize) / 2 + 77;
        int relY = (height - ySize) / 2 + 35;
        blit(matrixStack, relX, relY, 177, 0, 24, (((PotteryWheelTileEntity) getContainer().tileEntity).workingTicks / ((PotteryWheelTileEntity) getContainer().tileEntity).tickTime) * 17);
        func_230459_a_(matrixStack, mouseX, mouseY);
        renderButtonToolTip(matrixStack, mouseX, mouseY);
    }

    @Override
    protected void init() {
        super.init();

        for (Item item : PotteryWheelTileEntity.POTTERY_PIECES) addItemButton(item);

        select(((PotteryWheelTileEntity) container.tileEntity).selectedIndex);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(MatrixStack matrixStack, float partialTicks, int x, int y) {
        renderBackground(matrixStack);
        GlStateManager.color4f(1f, 1f, 1f, 1f);
        minecraft.getTextureManager().bindTexture(GUI);
        int relX = (width - xSize) / 2;
        int relY = (height - ySize) / 2;
        blit(matrixStack, relX, relY, 0, 0, xSize, ySize);

        int progress = (int) (24f * ((float) ((PotteryWheelTileEntity) container.tileEntity).workingTicks / (float) PotteryWheelTileEntity.tickTime));
        blit(matrixStack, relX + 76, relY + 34, xSize, 0, progress, 16);

        String s = new TranslationTextComponent("block.courageous.pottery_wheel").getString();
        this.font.drawString(matrixStack, s, (float)(this.xSize / 2 - this.font.getStringWidth(s) / 2), 6.0F, 4210752);
        this.font.drawString(matrixStack, this.playerInventory.getDisplayName().getString(), 8.0F, (float)(this.ySize - 96 + 2), 4210752);
    }

    protected void addItemButton(Item item) {
        int xOff = (width / 2 - xSize / 2) - 115;
        int yOff = (height / 2 - ySize / 2);

        int x = xOff + 23 * (buttons.size() % 5);
        int y = yOff + 23 * (buttons.size() / 5);

        int index = buttons.size();
        addButton(new SquareButton(x, y, (button) -> {
            select(index);
            ModPacketHandler.INSTANCE.sendToServer(new PacketPotterySelect(index));
        }));

        ICONS.put(item, new Pair<Integer, Integer>(x + 3, y + 3));
    }

//    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {

    }

//    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
//        renderBackground();
        GlStateManager.color4f(1f, 1f, 1f, 1f);
        minecraft.getTextureManager().bindTexture(GUI);
        int relX = (width - xSize) / 2;
        int relY = (height - ySize) / 2;
//        blit(relX, relY, 0, 0, xSize, ySize);

//        int progress = (int) (24f * ((float) ((PotteryWheelTileEntity) container.tileEntity).workingTicks / (float) PotteryWheelTileEntity.tickTime));
//        blit(relX + 76, relY + 34, xSize, 0, progress, 16);
    }

    private void drawItemStack(ItemStack stack, int x, int y) {
        this.itemRenderer.zLevel = 200.0F;
        this.itemRenderer.renderItemAndEffectIntoGUI(stack, x, y);
        this.itemRenderer.zLevel = 0.0F;
    }

    public void select(int selection) {
        ((SquareButton) buttons.get(selected)).setSelected(false);
        ((SquareButton) buttons.get(selection)).setSelected(true);
        selected = selection;
    }

    protected void renderButtonToolTip(MatrixStack matrixStack, int mouseX, int mouseY) {
        if (this.minecraft.player.inventory.getItemStack().isEmpty()) {
            for (Item item : ICONS.keySet()) {
                if (mouseX >= ICONS.get(item).getKey() && mouseY >= ICONS.get(item).getValue() && mouseX < ICONS.get(item).getKey() + 16 && mouseY < ICONS.get(item).getValue() + 16) {
                    super.renderTooltip(matrixStack, new ItemStack(item), mouseX, mouseY);
                }
            }
        }
    }

}
