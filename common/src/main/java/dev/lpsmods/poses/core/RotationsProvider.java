package dev.lpsmods.poses.core;

import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import net.minecraft.core.Rotations;

import java.util.Arrays;
import java.util.List;

/**
 * Author: legopitstop
 **/
public record RotationsProvider(float pitch, float yaw, float roll) {
    public static final Codec<Rotations> CODEC = Codec.FLOAT.listOf().comapFlatMap(
            RotationsProvider::tryFromList,
            RotationsProvider::toList
    );

    public RotationsProvider(float pitch, float yaw, float roll) {
        this.pitch = pitch;
        this.yaw = yaw;
        this.roll = roll;
    }

    public static Rotations fromList(List<Float> list) {
        if (list.size() != 3) {
            throw new IllegalArgumentException("Rotations array must have exactly 3 elements: " + list);
        }
        return new Rotations(list.get(0), list.get(1), list.get(2));
    }

    public static DataResult<Rotations> tryFromList(List<Float> list) {
        try {
            return DataResult.success(RotationsProvider.fromList(list));
        } catch (Exception err) {
            return DataResult.error(err::toString);
        }
    }

    public static List<Float> toList(Rotations rotations) {
        return Arrays.asList(rotations.getX(), rotations.getY(), rotations.getZ());
    }
}
