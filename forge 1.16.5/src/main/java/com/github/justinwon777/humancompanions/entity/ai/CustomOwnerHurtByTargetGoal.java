package com.github.justinwon777.humancompanions.entity.ai;

import com.github.justinwon777.humancompanions.core.Config;
import net.minecraft.entity.EntityPredicate;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.TargetGoal;
import net.minecraft.entity.passive.TameableEntity;

import java.util.EnumSet;

public class CustomOwnerHurtByTargetGoal extends TargetGoal {

    private final TameableEntity tameAnimal;
    private LivingEntity ownerLastHurtBy;
    private int timestamp;

    public CustomOwnerHurtByTargetGoal(TameableEntity p_26107_) {
        super(p_26107_, false);
        this.tameAnimal = p_26107_;
        this.setFlags(EnumSet.of(Goal.Flag.TARGET));
    }

    public boolean canUse() {
        if (this.tameAnimal.isTame() && !this.tameAnimal.isOrderedToSit()) {
            LivingEntity livingentity = this.tameAnimal.getOwner();
            if (livingentity == null) {
                return false;
            } else {
                this.ownerLastHurtBy = livingentity.getLastHurtByMob();
                if (this.ownerLastHurtBy instanceof TameableEntity) {
                    if (((TameableEntity) this.ownerLastHurtBy).isTame()) {
                        LivingEntity owner1 = ((TameableEntity) this.ownerLastHurtBy).getOwner();
                        LivingEntity owner2 = this.tameAnimal.getOwner();
                        if (owner1 == owner2) {
                            if (!Config.FRIENDLY_FIRE_COMPANIONS.get()) {
                                return false;
                            }
                        }
                    }
                }
                int i = livingentity.getLastHurtByMobTimestamp();
                return i != this.timestamp && this.canAttack(this.ownerLastHurtBy, EntityPredicate.DEFAULT) && this.tameAnimal.wantsToAttack(this.ownerLastHurtBy, livingentity);
            }
        } else {
            return false;
        }
    }

    public void start() {
        this.mob.setTarget(this.ownerLastHurtBy);
        LivingEntity livingentity = this.tameAnimal.getOwner();
        if (livingentity != null) {
            this.timestamp = livingentity.getLastHurtByMobTimestamp();
        }

        super.start();
    }
}
