package io.xerousso.courageous.util.rendering;

import net.minecraft.client.particle.*;
import net.minecraft.particles.BlockParticleData;
import net.minecraft.particles.IParticleData;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fluids.FluidAttributes;

public class FallingFluidParticle extends SpriteTexturedParticle {
    private final FluidAttributes fluidAttr;
    //protected final IParticleData landParticle;

    protected FallingFluidParticle(World world, double x, double y, double z, FluidAttributes fluidAttr, IParticleData landParticle) {
        super(world, x, y, z);
        this.setSize(0.01F, 0.01F);
        this.particleGravity = 0.06F;
        this.fluidAttr = fluidAttr;
        this.maxAge = (int) (64.0D / (Math.random() * 0.8D + 0.2D));
        //this.landParticle = landParticle;
    }

    @Override
    public IParticleRenderType getRenderType() {
        return IParticleRenderType.PARTICLE_SHEET_OPAQUE;
    }

    @Override
    public int getBrightnessForRender(float p_189214_1_) {
        return Math.max(this.fluidAttr.getLuminosity() * 16, super.getBrightnessForRender(p_189214_1_));
    }

    @Override
    public void tick() {
        this.prevPosX = this.posX;
        this.prevPosY = this.posY;
        this.prevPosZ = this.posZ;
        this.checkAge();
        if (!this.isExpired) {
            this.motionY -= (double) this.particleGravity;
            this.move(this.motionX, this.motionY, this.motionZ);
            this.update();
            if (!this.isExpired) {
                this.motionX *= 0.9800000190734863D;
                this.motionY *= 0.9800000190734863D;
                this.motionZ *= 0.9800000190734863D;
            }
        }
    }

    protected void checkAge() {
        if (this.maxAge-- <= 0) {
            this.setExpired();
        }
    }

    protected void update() {
        if (this.onGround) {
            this.setExpired();
            //this.world.addParticle(this.landParticle, this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D);
        }
    }

    @OnlyIn(Dist.CLIENT)
    public static class FallingFluidParticleFactory implements IParticleFactory<BlockParticleData> {
        protected final IAnimatedSprite spriteSet;

        public FallingFluidParticleFactory(IAnimatedSprite sprite) {
            this.spriteSet = sprite;
        }

        public Particle makeParticle(BlockParticleData typeIn, World worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            FallingFluidParticle fallingParticle = new FallingFluidParticle(worldIn, x, y, z, typeIn.getBlockState().getFluidState().getFluid().getAttributes(), null);
            int color = fallingParticle.fluidAttr.getColor();
            if (color == 0)
                color = 0xffffffff; //Fluids should never be black (happens when a block is used as a fluid), so when that happens just turn them white.
            float r = ((color >> 16) & 0xFF) / 255f;
            float g = ((color >> 8) & 0xFF) / 255f;
            float b = ((color >> 0) & 0xFF) / 255f;
            fallingParticle.setColor(r, g, b);
            fallingParticle.selectSpriteRandomly(this.spriteSet);
            return fallingParticle;
        }
    }
}
