package io.xerousso.courageous.tiles.renderers;

import io.xerousso.courageous.tiles.TileFaucet;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraftforge.fluids.FluidStack;

public class FaucetRenderer extends TileEntityRenderer<TileFaucet> {
    private static final Float PX = 1 / 16F; //Constant how big one pixel is

    public FaucetRenderer(TileEntityRendererDispatcher dispatcher) {
        super(dispatcher);
    }

    @Override
    public void render(TileFaucet tileFaucet, float v, MatrixStack matrixStack, IRenderTypeBuffer iRenderTypeBuffer, int combinedLightIn, int combinedOverlayIn) {
        FluidStack fluidStack = tileFaucet.getFluidInTank(0);
        if (!fluidStack.isEmpty()) {
            matrixStack.push();

            RenderType renderType = RenderType.getTranslucent();
            IVertexBuilder builder = iRenderTypeBuffer.getBuffer(renderType);

            AxisAlignedBB box = tileFaucet.getBlockState().getBlock().getShape(tileFaucet.getBlockState(), tileFaucet.getWorld(), tileFaucet.getPos(), ISelectionContext.dummy()).getBoundingBox();
            float percentFull = (float) fluidStack.getAmount() / tileFaucet.getTankCapacity(0); //Find out how full the tank is now
            float layerHeight = (float) (((box.maxY - 1.1 * PX) - (box.minY + PX)) * percentFull + box.minY + PX);

            TileEntityRenderHelper.DrawFluidPlane(builder, matrixStack, TileEntityRenderHelper.getFluidTexture(fluidStack), (float) box.minX, (float) box.minZ, (float) box.maxX, (float) box.maxZ, layerHeight, combinedLightIn, combinedOverlayIn);

            matrixStack.pop();
        }
    }

}
