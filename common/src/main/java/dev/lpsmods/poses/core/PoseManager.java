package dev.lpsmods.poses.core;

import net.minecraft.resources.ResourceLocation;

import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Author: legopitstop
 **/
public class PoseManager {
    public static final ConcurrentHashMap<ResourceLocation, ArmorStandPose> INSTANCE = new ConcurrentHashMap<>();

    public static void add(ResourceLocation id, ArmorStandPose pose) {
        PoseManager.INSTANCE.put(id, pose);
    }

    public static void clear() {
        PoseManager.INSTANCE.clear();
    }

    public static void remove(ResourceLocation id) {
        PoseManager.INSTANCE.remove(id);
    }

    public static int size() {
        return PoseManager.INSTANCE.size();
    }

    public static ArmorStandPose get(ResourceLocation id) {
        return INSTANCE.get(id);
    }

    public static ArmorStandPose getDefaultPose() {
        return get(getDefaultPoseId());
    }

    public static ResourceLocation getDefaultPoseId() {
        LocalDate localdate = LocalDate.now();
        int month = localdate.get(ChronoField.MONTH_OF_YEAR);
        return ResourceLocation.withDefaultNamespace(month == 10 ? "zombie" : "default");
    }
}
