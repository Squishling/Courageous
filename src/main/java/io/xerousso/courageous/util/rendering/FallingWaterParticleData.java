//package io.xerousso.courageous.util.rendering;
//
//import com.mojang.brigadier.StringReader;
//import net.minecraft.network.PacketBuffer;
//import net.minecraft.particles.IParticleData;
//import net.minecraft.particles.ParticleType;
//
//public class FallingWaterParticleData implements IParticleData {
//
//    public static final IDeserializer<FallingWaterParticleData> DESERIALIZER = new IDeserializer<FallingWaterParticleData>() {
//        @Override
//        public FallingWaterParticleData deserialize(ParticleType<FallingWaterParticleData> particleTypeIn, StringReader reader) {
//            return new FallingWaterParticleData();
//        }
//
//        public FallingWaterParticleData read(ParticleType<FallingWaterParticleData> particleTypeIn, PacketBuffer buffer) {
//            return new FallingWaterParticleData();
//        }
//    };
//
//    @Override
//    public ParticleType<?> getType() {
//        return ModParticles.FALLING_WATER_PARTICLE;
//    }
//
//    @Override
//    public void write(PacketBuffer buffer) {
//
//    }
//
//    @Override
//    public String getParameters() {
//        return "";
//    }
//
//}
