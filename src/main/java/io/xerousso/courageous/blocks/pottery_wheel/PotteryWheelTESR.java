//package io.xerousso.courageous.blocks.pottery_wheel;
//
//import com.mojang.blaze3d.matrix.MatrixStack;
//import net.minecraft.client.Minecraft;
//import net.minecraft.client.renderer.IRenderTypeBuffer;
//import net.minecraft.client.renderer.ItemRenderer;
//import net.minecraft.client.renderer.model.ItemCameraTransforms;
//import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
//import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
//import net.minecraft.item.BlockItem;
//import net.minecraft.item.ItemStack;
//import net.minecraftforge.items.CapabilityItemHandler;
//
//import static net.minecraft.client.renderer.texture.OverlayTexture.NO_OVERLAY;
//
//public class PotteryWheelTESR extends TileEntityRenderer<PotteryWheelTileEntity> {
//
//    public PotteryWheelTESR(TileEntityRendererDispatcher rendererDispatcherIn) {
//        super(rendererDispatcherIn);
//    }
//
//    @Override
//    public void render(PotteryWheelTileEntity te, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer iRenderTypeBuffer, int light, int overlay) {
//        ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();
//
//        te.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent(handler -> {
//            ItemStack stack = ItemStack.EMPTY;
//            int slot = 0;
//            if (handler.getStackInSlot(0) != null) stack = handler.getStackInSlot(0);
//            if ((stack.isEmpty() || te.working) && handler.getStackInSlot(1) != null) {
//                stack = handler.getStackInSlot(1);
//                slot = 1;
//            }
//
//            if (!stack.isEmpty()) {
//                matrixStack.push();
//                matrixStack.translate(0.5f, stack.getItem() instanceof BlockItem ? 1.1f : 0.95f, 0.5f);
////                matrixStack.rotate(Vector3f.XP.rotationDegrees(90));
//                if (slot == 1) {
////                    matrixStack.rotate(Vector3f.ZP.rotationDegrees(((float) te.workingTicks / (float) te.tickTime) * 720));
//                    matrixStack.scale(0.7f, 0.7f, 0.7f);
//                } else matrixStack.scale(0.5f, 0.5f, 0.5f);
//                itemRenderer.renderItem(stack, ItemCameraTransforms.TransformType.FIXED, light, NO_OVERLAY, matrixStack, iRenderTypeBuffer);
//                matrixStack.pop();
//            }
//        });
//    }
//
//}
