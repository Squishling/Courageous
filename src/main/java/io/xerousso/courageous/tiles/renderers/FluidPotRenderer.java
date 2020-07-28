package io.xerousso.courageous.tiles.renderers;

import io.xerousso.courageous.blocks.pot.BlockFluidPot;
import io.xerousso.courageous.tiles.TileFluidPot;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraftforge.fluids.FluidStack;

public class FluidPotRenderer extends TileEntityRenderer<TileFluidPot> {
    private static final Float PX = 1 / 16F; //Constant how big one pixel is
    private static final Float sideInset = 4 * PX; //How far the fluid is inset on the sides (or outset if negative)
    private static final Float bottomInset = 1 * PX; //Where the bottom of the tank is
    private static final Float topInset = 5 * PX; //Where the top of the tank is

    public FluidPotRenderer(TileEntityRendererDispatcher dispatcher) {
        super(dispatcher);
    }

    @Override
    public void render(TileFluidPot tileFluidPot, float v, MatrixStack matrixStack, IRenderTypeBuffer iRenderTypeBuffer, int combinedLightIn, int combinedOverlayIn) {
        FluidStack fluidStack = tileFluidPot.getFluidInTank(0);
        if (tileFluidPot.getBlockState().get(BlockFluidPot.OPEN) && !fluidStack.isEmpty()) {
            matrixStack.push();

            RenderType renderType = RenderType.getTranslucent();
            IVertexBuilder builder = iRenderTypeBuffer.getBuffer(renderType);

            Float percentFull = (float) fluidStack.getAmount() / tileFluidPot.getTankCapacity(0); //Find out how full the tank is now
            Float layerHeight = (1 - topInset - bottomInset) * percentFull + bottomInset;

            //Put fluid into buffer
            TileEntityRenderHelper.DrawFluidPlane(builder, matrixStack, TileEntityRenderHelper.getFluidTexture(fluidStack), sideInset, sideInset, 1 - sideInset, 1 - sideInset, layerHeight, combinedLightIn, combinedOverlayIn);
            matrixStack.pop();
        }
    }
}
