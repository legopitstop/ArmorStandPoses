package dev.lpsmods.poses;

import dev.lpsmods.poses.platform.NeoForgePoseLoader;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.AddReloadListenerEvent;

/**
 * Author: legopitstop
 **/
@Mod(Constants.MOD_ID)
public class ArmorStandPoses {

    public ArmorStandPoses(IEventBus eventBus) {
        NeoForge.EVENT_BUS.addListener(ArmorStandPoses::addResourceReload );
    }

    public static void addResourceReload(AddReloadListenerEvent event) {
        event.addListener(new NeoForgePoseLoader());
    }
}