package dev.lpsmods.poses.platform;

import dev.lpsmods.poses.Constants;
import dev.lpsmods.poses.core.ArmorStandPose;
import dev.lpsmods.poses.core.PoseManager;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimpleJsonResourceReloadListener;
import net.minecraft.util.profiling.ProfilerFiller;

import java.util.HashMap;

/**
 * Author: legopitstop
 **/
public class NeoForgePoseLoader extends SimpleJsonResourceReloadListener {
    public NeoForgePoseLoader() {
        super(ArmorStandPose.CODEC, "pose");
    }

    @Override
    protected void apply(Object o, ResourceManager resourceManager, ProfilerFiller profilerFiller) {
        PoseManager.clear();
        ((HashMap<ResourceLocation, ArmorStandPose>)o).forEach(PoseManager::add);
        Constants.LOG.info("Loaded {} poses", PoseManager.size());
    }
}
