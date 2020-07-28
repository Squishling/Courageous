package io.xerousso.courageous.blocks.planter_box;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraftforge.items.CapabilityItemHandler;

import static net.minecraft.client.renderer.texture.OverlayTexture.NO_OVERLAY;

public class PlanterBoxTER extends TileEntityRenderer<PlanterBoxTileEntity> {

    public PlanterBoxTER(TileEntityRendererDispatcher rendererDispatcherIn) { super(rendererDispatcherIn); }

    @Override
    public void render(PlanterBoxTileEntity te, float v, MatrixStack matrix, IRenderTypeBuffer iRenderTypeBuffer, int light, int overlay) {
        ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();

        te.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent(handler -> {
            matrix.push();
            matrix.scale(1.98f, 1.98f, 1.98f);
            matrix.translate(0.2525, 0.2525, 0.2525);
            itemRenderer.renderItem(handler.getStackInSlot(0), ItemCameraTransforms.TransformType.FIXED, light, NO_OVERLAY, matrix, iRenderTypeBuffer);
            matrix.pop();
        });
    }
}
