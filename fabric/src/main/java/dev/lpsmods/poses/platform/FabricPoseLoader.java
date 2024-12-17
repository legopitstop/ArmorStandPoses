package dev.lpsmods.poses.platform;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.JsonOps;
import dev.lpsmods.poses.Constants;
import dev.lpsmods.poses.core.ArmorStandPose;
import dev.lpsmods.poses.core.PoseManager;
import net.fabricmc.fabric.api.resource.SimpleSynchronousResourceReloadListener;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.Resource;
import net.minecraft.server.packs.resources.ResourceManager;

import java.io.InputStreamReader;
import java.util.Map;

/**
 * Author: legopitstop
 **/
public class FabricPoseLoader implements SimpleSynchronousResourceReloadListener {
    public static ResourceLocation ID = ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "poses");

    @Override
    public ResourceLocation getFabricId() {
        return ID;
    }

    @Override
    public void onResourceManagerReload(ResourceManager manager) {
        PoseManager.clear();
        Map<ResourceLocation, Resource> resources = manager.listResources("pose", (identifer) -> identifer.getPath().endsWith(".json"));
        for (ResourceLocation resourceId : resources.keySet()) {
            ResourceLocation id = resourceId.withPath(resourceId.getPath().replace("pose/", "").replace(".json", ""));
            try {
                JsonObject jsonObj = (JsonObject) JsonParser.parseReader(new InputStreamReader(resources.get(resourceId).open()));
                DataResult<ArmorStandPose> res =  ArmorStandPose.CODEC.parse(JsonOps.INSTANCE, jsonObj);
                if (res.isError()) {
                    Constants.LOG.error("{} | parse error:\n\t{}", id, res.error().get().message());
                    continue;
                }
                res.result().ifPresent(pose -> PoseManager.add(id, pose));
            } catch (Exception e) {
                Constants.LOG.error("Failed to load "+id+": "+e);
            }
        }
        Constants.LOG.info("Loaded {} poses", PoseManager.size());
    }
}
