package io.xerousso.courageous.items;

import com.mojang.datafixers.util.Pair;
import io.xerousso.courageous.tabs.FoodTab;
import io.xerousso.courageous.util.Util;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.item.Food.Builder;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;

public class CustomFood extends Item {

    private SoundEvent sound = SoundEvents.ENTITY_GENERIC_EAT;
    private UseAction action = UseAction.EAT;

    public CustomFood(Food food) {
        super(new Item.Properties().group(FoodTab.FOOD).food(food));
    }

    public CustomFood(int hunger, float saturation) {
        this(new Builder().hunger(hunger).saturation(saturation).build());
    }

    public CustomFood(Food food, Item containerItem) {
        super(new Item.Properties().group(FoodTab.FOOD).food(food).containerItem(containerItem));
    }

    public Item sound(SoundEvent sound) {
        this.sound = sound;
        return this;
    }

    public Item drink() {
        this.sound = SoundEvents.ENTITY_GENERIC_DRINK;
        this.action = UseAction.DRINK;
        return this;
    }

    @Override
    public UseAction getUseAction(ItemStack p_77661_1_) {
        return action;
    }

    @Override
    public ItemStack onItemUseFinish(ItemStack stack, World world, LivingEntity entity) {
        if (Util.isServer(world)) {
            entity.playSound(sound, 1.0F, 1.0F + (world.rand.nextFloat() - world.rand.nextFloat()) * 0.4F);
            world.playSound((PlayerEntity)null, entity.getPosX(), entity.getPosY(), entity.getPosZ(), sound, SoundCategory.NEUTRAL, 1.0F, 1.0F + (world.rand.nextFloat() - world.rand.nextFloat()) * 0.4F);

            if (!(entity instanceof PlayerEntity) || !((PlayerEntity) entity).isCreative()) stack.shrink(1);

            if (entity instanceof PlayerEntity) {
                PlayerEntity player = ((PlayerEntity)entity);
                player.getFoodStats().setFoodLevel(player.getFoodStats().getFoodLevel() + getFood().getHealing());
                player.getFoodStats().setFoodSaturationLevel(player.getFoodStats().getSaturationLevel() + getFood().getSaturation());

                if (getContainerItem() != null && !player.inventory.addItemStackToInventory(new ItemStack(getContainerItem()))) {
                    world.addEntity(new ItemEntity(world, player.getPosX(), player.getPosY(), player.getPosX(), new ItemStack(getContainerItem())));
                }
            }

            for(Pair<EffectInstance, Float> pair : this.getFood().getEffects()) {
//                if (pair.getLeft() != null) entity.addPotionEffect(pair.getLeft());
            }
        }

        return stack;
    }

}
