package dev.lpsmods.poses.core;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.Rotations;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.ComponentSerialization;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.world.entity.decoration.ArmorStand;
import org.apache.commons.lang3.StringUtils;

import java.util.Optional;

/**
 * Author: legopitstop
 **/
public record ArmorStandPose(int power, Pose pose, Optional<Component> displayName) {
    public static final Codec<ArmorStandPose> CODEC = RecordCodecBuilder.create((instance) -> {
        return instance.group(
                ExtraCodecs.intRange(1, 15).fieldOf("power").forGetter(ArmorStandPose::power),
                Pose.CODEC.fieldOf("pose").forGetter(ArmorStandPose::pose),
                ComponentSerialization.CODEC.optionalFieldOf("display_name").forGetter(ArmorStandPose::displayName)
        ).apply(instance, ArmorStandPose::new);
    });

    public ArmorStandPose(int power, Pose pose, Optional<Component> displayName) {
        this.displayName = displayName;
        this.power = power;
        this.pose = pose;
    }

    public ArmorStandPose(int power, Pose pose) {
        this(power, pose, Optional.empty());
    }

    public Component getName(ResourceLocation poseId) {
        String fallback = StringUtils.capitalize(poseId.getPath().replace("_", " "));
        Component displayName = Component.translatableWithFallback("pose."+poseId.getNamespace()+"."+poseId.getPath(), fallback);
        return this.displayName.orElse(displayName);
    }

    public String getFallback(ResourceLocation poseId) {
        return "Changed Pose: " + this.getName(poseId).getString();
    }

    public void setPose(ArmorStand entity) {
        this.pose.setPose(entity);
    }

    public record Pose(Rotations head, Rotations body, Rotations leftArm, Rotations rightArm, Rotations leftLeg, Rotations rightLeg) {
        public static final Codec<Pose> CODEC = RecordCodecBuilder.create((instance) -> {
            return instance.group(
                    RotationsProvider.CODEC.fieldOf("head").forGetter(Pose::head),
                    RotationsProvider.CODEC.fieldOf("body").forGetter(Pose::body),
                    RotationsProvider.CODEC.fieldOf("left_arm").forGetter(Pose::leftArm),
                    RotationsProvider.CODEC.fieldOf("right_arm").forGetter(Pose::rightArm),
                    RotationsProvider.CODEC.fieldOf("left_leg").forGetter(Pose::leftLeg),
                    RotationsProvider.CODEC.fieldOf("right_leg").forGetter(Pose::rightLeg)
            ).apply(instance, Pose::new);
        });

        public Pose(Rotations head, Rotations body, Rotations leftArm, Rotations rightArm, Rotations leftLeg, Rotations rightLeg) {
            this.head = head;
            this.body = body;
            this.leftArm = leftArm;
            this.rightArm = rightArm;
            this.leftLeg = leftLeg;
            this.rightLeg = rightLeg;
        }

        public void setPose(ArmorStand entity) {
            if (this.head != null) {entity.setHeadPose(this.head);}
            if (this.body != null) {entity.setBodyPose(this.body);}
            if (this.leftArm != null) {entity.setLeftArmPose(this.leftArm);}
            if (this.rightArm != null) {entity.setRightArmPose(this.rightArm);}
            if (this.leftLeg != null) {entity.setLeftLegPose(this.leftLeg);}
            if (this.rightLeg != null) {entity.setRightLegPose(this.rightLeg);}
        }
    }
}
