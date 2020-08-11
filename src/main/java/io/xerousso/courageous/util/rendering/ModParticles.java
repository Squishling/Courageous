package io.xerousso.courageous.util.rendering;

import com.mojang.serialization.Codec;
import net.minecraft.particles.BlockParticleData;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleType;

import java.util.function.Function;

public class ModParticles {

    //    public static ParticleType<FallingWaterParticleData> FALLING_WATER_PARTICLE = new ParticleType<>(false, FallingWaterParticleData.DESERIALIZER);
    public static ParticleType<BlockParticleData> FALLING_FLUID_PARTICLE = getType(BlockParticleData.DESERIALIZER, BlockParticleData::func_239800_a_);
//    public static IParticleData FALLING_WATER_PARTICLE_DATA = new FallingWaterParticleData();


    //Hey, idk, they made this stuff more complicated, so I'm just replicating their code
    private static <T extends IParticleData> ParticleType<T> getType(IParticleData.IDeserializer<T> deserializer, final Function<ParticleType<T>, Codec<T>> function) {
        return new ParticleType<T>(false, deserializer) {
            public Codec<T> func_230522_e_() {
                return function.apply(this);
            }
        };
    }
}
