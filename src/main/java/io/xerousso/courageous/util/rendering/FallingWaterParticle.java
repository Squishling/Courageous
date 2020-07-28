package io.xerousso.courageous.util.rendering;

import io.xerousso.courageous.Courageous;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.particle.*;
import net.minecraft.client.renderer.ActiveRenderInfo;
import net.minecraft.client.renderer.Quaternion;
import net.minecraft.client.renderer.Vector3f;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class FallingWaterParticle extends SpriteTexturedParticle {

    protected FallingWaterParticle(World world, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
        super(world, x, y, z,xSpeed,ySpeed,zSpeed);
        motionX = xSpeed;
        motionY = ySpeed;
        motionZ = zSpeed;
        particleScale += MathHelper.nextFloat(world.rand, 0f, 0.2f);
        canCollide = false;
        setPosition(x,y,z);
        setMaxAge(20 + MathHelper.nextInt(world.rand, 6, 24));

        particleRed = 0f;
        particleGreen = 0.2952f;
        particleBlue = 0.7048f;

        particleAlpha = 0.8f;

        particleScale = 0.2f + world.rand.nextFloat() / 10;
    }

    @Override
    public void renderParticle(IVertexBuilder buffer, ActiveRenderInfo renderInfo, float partialTicks) {
        if (isAlive()) {
            Vec3d vec3d = renderInfo.getProjectedView();
            float f = (float)(MathHelper.lerp((double)partialTicks, this.prevPosX, this.posX) - vec3d.getX());
            float f1 = (float)(MathHelper.lerp((double)partialTicks, this.prevPosY, this.posY) - vec3d.getY());
            float f2 = (float)(MathHelper.lerp((double)partialTicks, this.prevPosZ, this.posZ) - vec3d.getZ());
            Quaternion quaternion;
            if (this.particleAngle == 0.0F) {
                quaternion = renderInfo.getRotation();
            } else {
                quaternion = new Quaternion(renderInfo.getRotation());
                float f3 = MathHelper.lerp(partialTicks, this.prevParticleAngle, this.particleAngle);
                quaternion.multiply(Vector3f.ZP.rotation(f3));
            }

            Vector3f vector3f1 = new Vector3f(-1.0F, -1.0F, 0.0F);
            vector3f1.transform(quaternion);
            Vector3f[] avector3f = new Vector3f[]{new Vector3f(-1.0F, -1.0F, 0.0F), new Vector3f(-1.0F, 1.0F, 0.0F), new Vector3f(1.0F, 1.0F, 0.0F), new Vector3f(1.0F, -1.0F, 0.0F)};
            float f4 = this.particleScale;

            for(int i = 0; i < 4; ++i) {
                Vector3f vector3f = avector3f[i];
                vector3f.transform(quaternion);
                vector3f.mul(f4);
                vector3f.add(f, f1, f2);
            }

            float f7 = this.getMinU();
            float f8 = this.getMaxU();
            float f5 = this.getMinV();
            float f6 = this.getMaxV();
            int j = this.getBrightnessForRender(partialTicks);
            buffer.pos((double)avector3f[0].getX(), (double)avector3f[0].getY(), (double)avector3f[0].getZ()).tex(f8, f6).color(this.particleRed, this.particleGreen, this.particleBlue, this.particleAlpha).lightmap(j).endVertex();
            buffer.pos((double)avector3f[1].getX(), (double)avector3f[1].getY(), (double)avector3f[1].getZ()).tex(f8, f5).color(this.particleRed, this.particleGreen, this.particleBlue, this.particleAlpha).lightmap(j).endVertex();
            buffer.pos((double)avector3f[2].getX(), (double)avector3f[2].getY(), (double)avector3f[2].getZ()).tex(f7, f5).color(this.particleRed, this.particleGreen, this.particleBlue, this.particleAlpha).lightmap(j).endVertex();
            buffer.pos((double)avector3f[3].getX(), (double)avector3f[3].getY(), (double)avector3f[3].getZ()).tex(f7, f6).color(this.particleRed, this.particleGreen, this.particleBlue, this.particleAlpha).lightmap(j).endVertex();

        }

    }

    @Override
    public void tick() {
        super.tick();

        if (age <= maxAge - 15) canCollide = true;
        if (this.onGround) {
            Courageous.LOGGER.error("HHHHHH");
            motionX *= 0.001f;
            motionZ *= 0.001f;
            setExpired();
        }

        motionY -= 0.1f;

        motionX *= 0.9f;
        motionZ *= 0.9f;

        if (age <= maxAge / 4 && particleScale <= 0) setExpired();
    }

    @Override
    public IParticleRenderType getRenderType() {
        return IParticleRenderType.PARTICLE_SHEET_LIT;
    }

    @OnlyIn(Dist.CLIENT)
    public static class Factory implements IParticleFactory<FallingWaterParticleData> {
        private final IAnimatedSprite spriteSet;

        public Factory(IAnimatedSprite spriteSet) {
            this.spriteSet = spriteSet;
        }

        public Particle makeParticle(FallingWaterParticleData data, World worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            FallingWaterParticle particle = new FallingWaterParticle(worldIn, x, y, z, xSpeed, ySpeed, zSpeed);
            particle.selectSpriteRandomly(this.spriteSet);
            return particle;
        }
    }
}
