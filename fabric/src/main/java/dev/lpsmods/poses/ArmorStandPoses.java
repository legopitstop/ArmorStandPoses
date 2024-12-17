package dev.lpsmods.poses;

import dev.lpsmods.poses.platform.FabricPoseLoader;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.minecraft.server.packs.PackType;

/**
 * Author: legopitstop
 **/
public class ArmorStandPoses implements ModInitializer {
    
    @Override
    public void onInitialize() {
        ResourceManagerHelper.get(PackType.SERVER_DATA).registerReloadListener(new FabricPoseLoader());
    }
}
