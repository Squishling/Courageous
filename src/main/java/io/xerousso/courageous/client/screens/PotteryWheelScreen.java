package io.xerousso.courageous.client.screens;

import com.mojang.blaze3d.systems.RenderSystem;
import io.xerousso.courageous.Courageous;
import io.xerousso.courageous.blocks.block_entities.PotteryWheelBlockEntity;
import io.xerousso.courageous.blocks.block_entities.recipes.PotteryRecipe;
import io.xerousso.courageous.client.LoopButtonWidget;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.gui.screen.ingame.MerchantScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.Recipe;
import net.minecraft.screen.MerchantScreenHandler;
import net.minecraft.screen.slot.Slot;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Supplier;

public class PotteryWheelScreen extends HandledScreen<PotteryWheelScreenHandler> {

    private static final Identifier TEXTURE = new Identifier(Courageous.MOD_ID, "textures/gui/pottery_wheel.png");

    private final Supplier<Integer> x = () -> (width - backgroundWidth) / 2;
    private final Supplier<Integer> y = () -> (height - backgroundHeight) / 2;

    private static final int scrollAreaHeight = 140;
    private static final int scrollAreaWidth = 95;

    private static final int scrollThumbHeight = 27;
    private static final int scrollThumbWidth = 6;

    private static final int canFit = Math.floorDiv(scrollAreaHeight, 20);

    private static final int listX = 174;
    private static final int listY = 18;

    private int indexStartOffset = 0;

    private int selectedIndex = 0;
    private boolean scrolling;

    private final EntryButton[] entries = new EntryButton[canFit];

    public PotteryWheelScreen(PotteryWheelScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);

        backgroundWidth = 277;
        backgroundHeight = 166;
    }

    @Override
    protected void drawBackground(MatrixStack matrices, float delta, int mouseX, int mouseY) {
        RenderSystem.color4f(1f, 1f, 1f, 1f);
        client.getTextureManager().bindTexture(TEXTURE);

        drawTexture(matrices, x.get(), y.get(), getZOffset(), 0, 0, backgroundWidth, backgroundHeight, 256, 512);

        if (handler.getSlot(0).getStack().isEmpty())
            drawTexture(matrices, x.get() + 33, y.get() + 27, getZOffset(), 12, 166, 16, 16, 256, 512);
        if (handler.getSlot(1).getStack().isEmpty())
            drawTexture(matrices, x.get() + 59, y.get() + 27, getZOffset(), 28, 166, 16, 16, 256, 512);
    }

    @Override
    protected void drawForeground(MatrixStack matrices, int mouseX, int mouseY) {
        this.textRenderer.draw(matrices, this.title, (float)this.titleX, (float)this.titleY, 4210752);
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        renderBackground(matrices);
        drawBackground(matrices, delta, mouseX, mouseY);
        super.render(matrices, mouseX, mouseY, delta);

        RenderSystem.pushMatrix();
        RenderSystem.enableRescaleNormal();
        client.getTextureManager().bindTexture(TEXTURE);

        renderScrollWheel(matrices);

        if (!handler.getRecipes().isEmpty()) {
            int localY = listY;

            for (int i = 0; i < handler.getRecipes().size(); i++) {
                if(!(canScroll(handler.getRecipes().size()) && (i < indexStartOffset || i >= canFit + indexStartOffset))) {
                    itemRenderer.zOffset = 100;
                    renderOverlay(handler.getRecipes().get(i), x.get() + listX, y.get() + localY);

                    localY += 20;
                }
            }

            for (EntryButton page : entries) {
                if (page != null) {
                    if (page.isHovered()) page.renderToolTip(matrices, mouseX, mouseY);
                    page.visible = page.index < handler.getRecipes().size();
                }
            }

            RenderSystem.popMatrix();
            RenderSystem.enableDepthTest();
        }

        drawMouseoverTooltip(matrices, mouseX, mouseY);
    }

    private void renderOverlay(PotteryRecipe recipe, int x, int y) {
        itemRenderer.renderInGui(recipe.getOutput(handler.getSlot(0).getStack().getItem()).getMatchingStacksClient()[0], x + 2, y + 2);
    }

    private boolean canScroll(int listSize) {
        return listSize > canFit;
    }

    private void renderScrollWheel(MatrixStack matrices) {
        int overflow = handler.getRecipes().size() - canFit + 1;

        if (overflow > 1) {
            int verticalOffset = scrollAreaHeight - (scrollThumbHeight + (overflow - 1) * scrollAreaHeight / overflow);
            int multiplier = 1 + verticalOffset / overflow + scrollAreaHeight / overflow;

            int finalVerticalOffset = Math.min(scrollAreaHeight - scrollThumbHeight, this.indexStartOffset * multiplier);
            if (indexStartOffset == overflow - 1) finalVerticalOffset = scrollAreaHeight - scrollThumbHeight;

            drawTexture(matrices, x.get() + listX + scrollAreaWidth - scrollThumbWidth, y.get() + listY + finalVerticalOffset, this.getZOffset(),
                    0, 166, scrollThumbWidth, scrollThumbHeight, 256, 512);
        } else drawTexture(matrices, x.get() + listX + scrollAreaWidth - scrollThumbWidth, y.get() + listY, this.getZOffset(),
                    scrollThumbWidth, 166, scrollThumbWidth, scrollThumbHeight, 256, 512);
    }

    public boolean mouseScrolled(double mouseX, double mouseY, double amount) {
        if (canScroll(handler.getRecipes().size())) indexStartOffset = (int) MathHelper.clamp(indexStartOffset - amount, 0,
                 handler.getRecipes().size() - canFit);

        return true;
    }

    public boolean mouseDragged(double mouseX, double mouseY, int button, double deltaX, double deltaY) {
        int size = handler.getRecipes().size();

        if (scrolling) this.indexStartOffset = (int) MathHelper.clamp(((mouseY - y.get() + listY - scrollThumbHeight / 2f) /
                    (scrollAreaHeight - scrollThumbHeight) * (size - canFit)), 0, size - canFit);

        return scrolling || super.mouseDragged(mouseX, mouseY, button, deltaX, deltaY);
    }

    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        scrolling = canScroll(handler.getRecipes().size()) &&
                mouseX > (double) (x.get() + listX + scrollAreaWidth  - scrollThumbWidth) &&
                mouseX < (double) (x.get() + listX + scrollAreaWidth                    ) &&

                mouseY > (double) (y.get() + listY                                      ) &&
                mouseY < (double) (y.get() + listY + scrollAreaHeight + 2);

        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    protected void init() {
        super.init();
        titleX = (backgroundWidth - textRenderer.getWidth(title)) / 2;

        addButton(new ButtonWidget(x.get() + 25, y.get() + 55, 106, 20, new TranslatableText("gui.courageous.pottery.make"), button -> {

        }));

        addButton(new LoopButtonWidget(x.get() + 131, y.get() + 55, (loopButtonWidget, selected) -> {

        }));

        int localY = y.get() + listY;

        for(int i = 0; i < canFit; ++i) {
            this.entries[i] = this.addButton(new EntryButton(x.get() + listX, localY, i, (buttonWidget) -> {
                if (buttonWidget instanceof EntryButton) {
                    selectedIndex = ((EntryButton) buttonWidget).getIndex() + indexStartOffset;

                }

            }));

            localY += 20;
        }
    }

    @Environment(EnvType.CLIENT)
    class EntryButton extends ButtonWidget {

        final int index;

        public EntryButton(int i, int j, int k, ButtonWidget.PressAction pressAction) {
            super(i, j, scrollAreaWidth - scrollThumbWidth - 1, 20, LiteralText.EMPTY, pressAction);
            this.index = k;
            this.visible = false;
        }

        public int getIndex() {
            return this.index;
        }

        @Override
        public void renderToolTip(MatrixStack matrices, int mouseX, int mouseY) {
            if (this.hovered && handler.getRecipes().size() > index + indexStartOffset) {

            }
        }

        @Override
        public void onPress() {
            selectedIndex = index;
        }

        @Override
        public void renderButton(MatrixStack matrices, int mouseX, int mouseY, float delta) {
            MinecraftClient minecraftClient = MinecraftClient.getInstance();
            TextRenderer textRenderer = minecraftClient.textRenderer;

            RenderSystem.color4f(1.0F, 1.0F, 1.0F, this.alpha);
            minecraftClient.getTextureManager().bindTexture(TEXTURE);

//            int i = ((selected ? 1 : 0) << 1) & (isHovered() ? 1 : 0);

//            System.out.println(i);

            RenderSystem.enableBlend();
            RenderSystem.defaultBlendFunc();
            RenderSystem.enableDepthTest();

            drawTexture(matrices, x, y, 277, (((selectedIndex == index ? 1 : 0) << 1) | (isHovered() ? 1 : 0)) * 20, width / 2, 20, 512, 256);
            drawTexture(matrices, x + width / 2, y, 477 - width / 2f, (((selectedIndex == index ? 1 : 0) << 1) | (isHovered() ? 1 : 0)) * 20, width / 2, 20, 512, 256);
            this.renderBg(matrices, minecraftClient, mouseX, mouseY);
            drawCenteredText(matrices, textRenderer, this.getMessage(), x + width / 2, y + (height - 8) / 2, active ? 0xffffff : 0xa0a0a0 | MathHelper.ceil(this.alpha * 255.0F) << 24);
        }

    }

}
