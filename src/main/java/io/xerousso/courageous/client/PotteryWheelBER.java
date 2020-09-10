package io.xerousso.courageous.client;

import io.xerousso.courageous.blocks.block_entities.PotteryWheelBlockEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderDispatcher;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.model.json.ModelTransformation.Mode;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

public class PotteryWheelBER extends BlockEntityRenderer<PotteryWheelBlockEntity> {

    private ItemStack stack = new ItemStack(Items.CLAY);

    public PotteryWheelBER(BlockEntityRenderDispatcher dispatcher) {
        super(dispatcher);
    }

    @Override
    public void render(PotteryWheelBlockEntity entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        matrices.push();

        matrices.translate(0.5, 1.5, 0.5);
        MinecraftClient.getInstance().getItemRenderer().renderItem(stack, Mode.GROUND, light, overlay, matrices, vertexConsumers);
        
        matrices.pop();
    }

}
