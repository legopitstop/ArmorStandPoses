package dev.lpsmods.poses.platform;

import dev.lpsmods.poses.platform.services.IPlatformHelper;
import net.fabricmc.loader.api.FabricLoader;

/**
 * Author: legopitstop
 **/
public class FabricPlatformHelper implements IPlatformHelper {

    @Override
    public String getPlatformName() {
        return "Fabric";
    }

    @Override
    public boolean isModLoaded(String modId) {
        return FabricLoader.getInstance().isModLoaded(modId);
    }

    @Override
    public boolean isDevelopmentEnvironment() {
        return FabricLoader.getInstance().isDevelopmentEnvironment();
    }
}
