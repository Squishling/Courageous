package io.xerousso.courageous.client;

import io.xerousso.courageous.blocks.Blockz;
import io.xerousso.courageous.blocks.PotteryWheel;
import io.xerousso.courageous.blocks.block_entities.PotteryWheelBlockEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderDispatcher;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.model.json.ModelTransformation.Mode;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.util.math.Vector3f;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Quaternion;
import net.minecraft.world.World;

public class PotteryWheelBER extends BlockEntityRenderer<PotteryWheelBlockEntity> {

    private ItemStack stack = new ItemStack(Items.CLAY);

    public PotteryWheelBER(BlockEntityRenderDispatcher dispatcher) {
        super(dispatcher);
    }

    @Override
    public void render(PotteryWheelBlockEntity entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        matrices.push();

        World world = entity.getWorld();
        BlockPos pos = entity.getPos();

        MinecraftClient.getInstance().getBlockRenderManager().renderBlock(Blockz.POTTERY_WHEEL.getDefaultState().with(PotteryWheel.POTTERY_STATE, PotteryStates.BASE),
                pos, world, matrices, vertexConsumers.getBuffer(RenderLayer.getCutout()), true, world.getRandom());

        if (entity.getWorld().getBlockState(entity.getPos()).get(PotteryWheel.POTTERY_STATE) == PotteryStates.TURNING) {
            matrices.translate(0.5, 0, 0.5);
            matrices.multiply(new Quaternion(Vector3f.POSITIVE_Y, 45, true));
            matrices.translate(-0.5, 0, -0.5);
        }

        MinecraftClient.getInstance().getBlockRenderManager().renderBlock(Blockz.POTTERY_WHEEL.getDefaultState().with(PotteryWheel.POTTERY_STATE, PotteryStates.STILL),
                pos, world, matrices, vertexConsumers.getBuffer(RenderLayer.getCutout()), true, world.getRandom());

        matrices.translate(0.5, 1, 0.5);
        MinecraftClient.getInstance().getItemRenderer().renderItem(stack, Mode.GROUND, light, overlay, matrices, vertexConsumers);

        matrices.pop();
    }

}
