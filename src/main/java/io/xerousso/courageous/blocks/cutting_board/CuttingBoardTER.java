//package io.xerousso.courageous.blocks.cutting_board;
//
//import com.mojang.blaze3d.matrix.MatrixStack;
//import net.minecraft.client.Minecraft;
//import net.minecraft.client.renderer.IRenderTypeBuffer;
//import net.minecraft.client.renderer.ItemRenderer;
//import net.minecraft.client.renderer.model.ItemCameraTransforms;
//import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
//import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
//import net.minecraft.item.ItemStack;
//import net.minecraftforge.items.CapabilityItemHandler;
//
//import static net.minecraft.client.renderer.texture.OverlayTexture.NO_OVERLAY;
//
//public class CuttingBoardTER extends TileEntityRenderer<CuttingBoardTileEntity> {
//
//    public CuttingBoardTER(TileEntityRendererDispatcher rendererDispatcherIn) {
//        super(rendererDispatcherIn);
//    }
//
//    @Override
//    public void render(CuttingBoardTileEntity te, float partialTicks, MatrixStack matrix, IRenderTypeBuffer iRenderTypeBuffer, int light, int overlay) {
//        ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();
//
//        te.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent(handler -> {
//            matrix.push();
////            matrix.rotate(Vector3f.XP.rotationDegrees(90));
////            matrix.rotate(Vector3f.ZP.rotationDegrees(90));
//
//            ItemStack stack;
//
//            // Sandwich
//            stack = handler.getStackInSlot(CuttingBoardContainer.SANDWICH_OUTPUT_SLOT).isEmpty() ?
//                    ItemStack.EMPTY :
//                    handler.getStackInSlot(CuttingBoardContainer.SANDWICH_OUTPUT_SLOT);
//            matrix.translate(0.575f, -0.5f, -0.08f);
//            matrix.scale(-0.6f, -0.6f, 0.6f);
//            itemRenderer.renderItem(stack, ItemCameraTransforms.TransformType.FIXED, light, NO_OVERLAY, matrix, iRenderTypeBuffer);
//
//            // Cutting
//            stack = handler.getStackInSlot(CuttingBoardContainer.CUTTING_OUTPUT_SLOT).isEmpty() ?
//                    handler.getStackInSlot(CuttingBoardContainer.CUTTING_INPUT_SLOT).isEmpty() ?
//                    ItemStack.EMPTY :
//                        handler.getStackInSlot(CuttingBoardContainer.CUTTING_INPUT_SLOT) :
//                    handler.getStackInSlot(CuttingBoardContainer.CUTTING_OUTPUT_SLOT);
//            matrix.translate(0.7f, 0.25f, 0f);
//            matrix.scale(0.5f, 0.5f, 0.5f);
//            itemRenderer.renderItem(stack, ItemCameraTransforms.TransformType.FIXED, light, NO_OVERLAY, matrix, iRenderTypeBuffer);
//
//            // Knife
//            stack = handler.getStackInSlot(CuttingBoardContainer.KNIFE_SLOT).isEmpty() ?
//                    ItemStack.EMPTY :
//                    handler.getStackInSlot(CuttingBoardContainer.KNIFE_SLOT);
//            matrix.translate(0f, -1f, 0f);
//            matrix.scale(1f, 1f, 1f);
//            itemRenderer.renderItem(stack, ItemCameraTransforms.TransformType.FIXED, light, NO_OVERLAY, matrix, iRenderTypeBuffer);
//
//            matrix.pop();
//        });
//    }
//
//}
