package com.github.justinwon777.humancompanions.entity.ai;

import com.github.justinwon777.humancompanions.core.Config;
import net.minecraft.entity.EntityPredicate;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.TargetGoal;
import net.minecraft.entity.item.ArmorStandEntity;
import net.minecraft.entity.monster.CreeperEntity;
import net.minecraft.entity.passive.TameableEntity;

import java.util.EnumSet;

public class CustomOwnerHurtTargetGoal extends TargetGoal {
    private final TameableEntity tameAnimal;
    private LivingEntity ownerLastHurt;
    private int timestamp;

    public CustomOwnerHurtTargetGoal(TameableEntity p_26114_) {
        super(p_26114_, false);
        this.tameAnimal = p_26114_;
        this.setFlags(EnumSet.of(Goal.Flag.TARGET));
    }

    public boolean canUse() {
        if (this.tameAnimal.isTame() && !this.tameAnimal.isOrderedToSit()) {
            LivingEntity livingentity = this.tameAnimal.getOwner();
            if (livingentity == null) {
                return false;
            } else {
                this.ownerLastHurt = livingentity.getLastHurtMob();
                if (this.ownerLastHurt instanceof TameableEntity) {
                    if (((TameableEntity) this.ownerLastHurt).isTame()) {
                        LivingEntity owner1 = ((TameableEntity) this.ownerLastHurt).getOwner();
                        LivingEntity owner2 = this.tameAnimal.getOwner();
                        if (owner1 == owner2) {
                            if (!Config.FRIENDLY_FIRE_COMPANIONS.get()) {
                                return false;
                            }
                        }
                    }
                } else if (this.ownerLastHurt instanceof CreeperEntity || this.ownerLastHurt instanceof ArmorStandEntity) {
                    return false;
                }
                int i = livingentity.getLastHurtMobTimestamp();
                return i != this.timestamp && this.canAttack(this.ownerLastHurt, EntityPredicate.DEFAULT) && this.tameAnimal.wantsToAttack(this.ownerLastHurt, livingentity);
            }
        } else {
            return false;
        }
    }

    public void start() {
        this.mob.setTarget(this.ownerLastHurt);
        LivingEntity livingentity = this.tameAnimal.getOwner();
        if (livingentity != null) {
            this.timestamp = livingentity.getLastHurtMobTimestamp();
        }

        super.start();
    }
}
