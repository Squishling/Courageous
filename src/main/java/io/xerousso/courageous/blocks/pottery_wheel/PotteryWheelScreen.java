package io.xerousso.courageous.blocks.pottery_wheel;

import io.xerousso.courageous.util.Util;
import io.xerousso.courageous.util.networking.ModPacketHandler;
import io.xerousso.courageous.util.networking.PacketPotterySelect;
import io.xerousso.courageous.util.rendering.SquareButton;
import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import org.apache.commons.lang3.tuple.Pair;

import java.util.HashMap;

public class PotteryWheelScreen extends ContainerScreen<PotteryWheelContainer> {

    private ResourceLocation GUI = new ResourceLocation(Util.MOD_ID, "textures/gui/pottery_wheel.png");
    private int selected;

    private HashMap<Item, Pair<Integer, Integer>> ICONS = new HashMap<Item, Pair<Integer, Integer>>();

    public PotteryWheelScreen(PotteryWheelContainer container, PlayerInventory playerInventory, ITextComponent name) {
        super(container, playerInventory, name);
    }

    @Override
    public void render(int mouseX, int mouseY, float partialTicks) {
        super.render(mouseX, mouseY, partialTicks);

        GlStateManager.translatef(0.0F, 0.0F, 32.0F);
        for (Item item : ICONS.keySet()) {
            drawItemStack(new ItemStack(item), ICONS.get(item).getLeft(), ICONS.get(item).getRight());
        }

        GlStateManager.color4f(1f, 1f, 1f, 1f);
        minecraft.getTextureManager().bindTexture(GUI);
        int relX = (width - xSize) / 2 + 77;
        int relY = (height - ySize) / 2 + 35;
        blit(relX, relY, 177, 0, 24, (((PotteryWheelTileEntity) getContainer().tileEntity).workingTicks / ((PotteryWheelTileEntity) getContainer().tileEntity).tickTime) * 17);

        renderHoveredToolTip(mouseX, mouseY);
        renderButtonToolTip(mouseX, mouseY);
    }

    @Override
    protected void init() {
        super.init();

        for (Item item : PotteryWheelTileEntity.POTTERY_PIECES) addItemButton(item);

        select(((PotteryWheelTileEntity) container.tileEntity).selectedIndex);
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

        ICONS.put(item, new Pair<Integer, Integer>() {
            @Override
            public Integer setValue(Integer value) {
                return null;
            }

            @Override
            public Integer getLeft() {
                return x + 3;
            }

            @Override
            public Integer getRight() {
                return y + 3;
            }
        });
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        String s = new TranslationTextComponent("block.courageous.pottery_wheel").getFormattedText();
        this.font.drawString(s, (float)(this.xSize / 2 - this.font.getStringWidth(s) / 2), 6.0F, 4210752);
        this.font.drawString(this.playerInventory.getDisplayName().getFormattedText(), 8.0F, (float)(this.ySize - 96 + 2), 4210752);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        renderBackground();
        GlStateManager.color4f(1f, 1f, 1f, 1f);
        minecraft.getTextureManager().bindTexture(GUI);
        int relX = (width - xSize) / 2;
        int relY = (height - ySize) / 2;
        blit(relX, relY, 0, 0, xSize, ySize);

        int progress = (int) (24f * ((float) ((PotteryWheelTileEntity) container.tileEntity).workingTicks / (float) PotteryWheelTileEntity.tickTime));
        blit(relX + 76, relY + 34, xSize, 0, progress, 16);
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

    protected void renderButtonToolTip(int mouseX, int mouseY) {

        if (this.minecraft.player.inventory.getItemStack().isEmpty()) {
            for (Item item : ICONS.keySet()) {
                if (mouseX >= ICONS.get(item).getLeft() && mouseY >= ICONS.get(item).getRight() && mouseX < ICONS.get(item).getLeft() + 16 && mouseY < ICONS.get(item).getRight() + 16) {
                    super.renderTooltip(new ItemStack(item), mouseX, mouseY);
                }
            }
        }

    }

}
