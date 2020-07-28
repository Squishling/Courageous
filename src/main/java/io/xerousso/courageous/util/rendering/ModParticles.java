package io.xerousso.courageous.util.rendering;

import net.minecraft.particles.BlockParticleData;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleType;

public class ModParticles {

    public static ParticleType<FallingWaterParticleData> FALLING_WATER_PARTICLE = new ParticleType<>(false, FallingWaterParticleData.DESERIALIZER);
    public static ParticleType<BlockParticleData> FALLING_FLUID_PARTICLE = new ParticleType<>(false, BlockParticleData.DESERIALIZER);
    public static IParticleData FALLING_WATER_PARTICLE_DATA = new FallingWaterParticleData();

}
