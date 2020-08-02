//package io.xerousso.courageous.blocks.architects_table;
//
//import com.mojang.blaze3d.matrix.MatrixStack;
//import io.xerousso.courageous.util.Util;
//import com.mojang.blaze3d.platform.GlStateManager;
//import net.minecraft.client.gui.screen.inventory.ContainerScreen;
//import net.minecraft.entity.player.PlayerInventory;
//import net.minecraft.util.ResourceLocation;
//import net.minecraft.util.math.MathHelper;
//import net.minecraft.util.text.ITextComponent;
//import net.minecraft.util.text.TranslationTextComponent;
//
//public class ArchitectsTableScreen extends ContainerScreen<ArchitectsTableContainer> {
//
//    private ResourceLocation GUI = new ResourceLocation(Util.MOD_ID, "textures/gui/architects_table.png");
//
//    private float sliderProgress;
//    /** Is {@code true} if the player clicked on the scroll wheel in the GUI. */
//    private boolean clickedOnSroll;
//
//    public ArchitectsTableScreen(ArchitectsTableContainer container, PlayerInventory playerInventory, ITextComponent name) {
//        super(container, playerInventory, name);
//
//        xSize = 208;
//        ySize = 199;
//    }
//
//    @Override
//    protected void drawGuiContainerBackgroundLayer(MatrixStack matrixStack, float partialTicks, int x, int y) {
////        renderBackground();
//        GlStateManager.color4f(1f, 1f, 1f, 1f);
//        minecraft.getTextureManager().bindTexture(GUI);
//
//        int relX = (width - xSize) / 2;
//        int relY = (height - ySize) / 2;
////        blit(relX, relY, 0, 0, xSize, ySize);
////
////        int k = (int)(68f * this.sliderProgress);
////        this.blit(relX + 151, relY + 18 + k, 18 + (this.canScroll() ? 0 : 12), 199, 12, 15);
////
////        if (container.getSlot(0).getStack().isEmpty()) blit(relX + 21, relY + 41, xSize, 0, 16, 16);
//    }
//
////    @Override
////    public void render(int mouseX, int mouseY, float partialTicks) {
////        super.render(mouseX, mouseY, partialTicks);
////
////        renderHoveredToolTip(mouseX, mouseY);
////    }
//
//    @Override
//    protected void drawGuiContainerForegroundLayer(MatrixStack matrixStack, int x, int y) {
//        String s = new TranslationTextComponent("block.courageous.architects_table").toString();
////        this.font.drawString(s, (float)(this.xSize / 2 - this.font.getStringWidth(s) / 2), 6.0F, 4210752);
////        this.font.drawString(this.playerInventory.getDisplayName().getFormattedText(), 25 + 4.0F, (float)(this.ySize - 96 + 2), 4210752);
//    }
//
//    public boolean mouseDragged(double p_mouseDragged_1_, double p_mouseDragged_3_, int p_mouseDragged_5_, double p_mouseDragged_6_, double p_mouseDragged_8_) {
//        if (this.clickedOnSroll && this.canScroll()) {
//            int i = this.guiTop + 17;
//            int j = i + 83;
//            this.sliderProgress = ((float)p_mouseDragged_3_ - (float)i - 7.5F) / ((float)(j - i) - 15.0F);
//            this.sliderProgress = MathHelper.clamp(this.sliderProgress, 0.0F, 1.0F);
////            this.recipeIndexOffset = (int)((double)(this.sliderProgress * (float)this.getHiddenRows()) + 0.5D) * 4;
//            return true;
//        } else {
//            return super.mouseDragged(p_mouseDragged_1_, p_mouseDragged_3_, p_mouseDragged_5_, p_mouseDragged_6_, p_mouseDragged_8_);
//        }
//    }
//
//    public boolean mouseScrolled(double p_mouseScrolled_1_, double p_mouseScrolled_3_, double p_mouseScrolled_5_) {
//        if (this.canScroll()) {
//            int i = this.getHiddenRows();
//            this.sliderProgress = (float)((double)this.sliderProgress - p_mouseScrolled_5_ / (double)i);
//            this.sliderProgress = MathHelper.clamp(this.sliderProgress, 0.0F, 1.0F);
////            this.recipeIndexOffset = (int)((double)(this.sliderProgress * (float)i) + 0.5D) * 4;
//        }
//
//        return true;
//    }
//
//    protected int getHiddenRows() {
////        return (this.container.getRecipeListSize() + 4 - 1) / 4 - 3;
//        return 10;
//    }
//
//    private boolean canScroll() {
//        return true;
//    }
//
//    public boolean mouseClicked(double x, double y, int p_mouseClicked_5_) {
//        this.clickedOnSroll = false;
////        if (this.hasItemsInInputSlot) {
////            int i = this.guiLeft + 52;
////            int j = this.guiTop + 14;
////            int k = this.recipeIndexOffset + 12;
////
////            for(int l = this.recipeIndexOffset; l < k; ++l) {
////                int i1 = l - this.recipeIndexOffset;
////                double d0 = p_mouseClicked_1_ - (double)(i + i1 % 4 * 16);
////                double d1 = p_mouseClicked_3_ - (double)(j + i1 / 4 * 18);
////                if (d0 >= 0.0D && d1 >= 0.0D && d0 < 16.0D && d1 < 18.0D && this.container.enchantItem(this.minecraft.player, l)) {
////                    Minecraft.getInstance().getSoundHandler().play(SimpleSound.master(SoundEvents.UI_STONECUTTER_SELECT_RECIPE, 1.0F));
////                    this.minecraft.playerController.sendEnchantPacket((this.container).windowId, l);
////                    return true;
////                }
////            }
////
////
////        }
//
//        if (x >= guiLeft + 151d && x < guiLeft + 151d + 12d && y >= guiTop + 18d && y < guiTop + 18d + 83d) {
//            this.clickedOnSroll = true;
//        }
//
//        return super.mouseClicked(x, y, p_mouseClicked_5_);
//    }
//
//}
