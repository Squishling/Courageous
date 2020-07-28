package io.xerousso.courageous.util;

import net.minecraft.item.Food;
import net.minecraft.potion.EffectInstance;
import net.minecraft.world.World;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.List;

public class Util {

    public static final String MOD_ID = "courageous";

    public static boolean isClient(World world) {
        return world.isRemote;
    }

    public static boolean isServer(World world) {
        return !world.isRemote;
    }

    public static Food buildCombo(ArrayList<Food> foods) {
        int points = 0;
        int saturation = 0;
        List<Pair<EffectInstance, Float>> effects = new ArrayList<Pair<EffectInstance, Float>>();

        for (Food food : foods) {
            points += food.getHealing();
            saturation += food.getSaturation();
            for (Pair<EffectInstance, Float> effect : food.getEffects()) {
                effects.add(effect);
            }
        }

        Food.Builder builder = new Food.Builder().hunger(points).saturation(saturation);

        for (Pair<EffectInstance, Float> effect : effects) {
            builder.effect(effect.getLeft(), effect.getRight());
        }

        return builder.build();
    }

}
