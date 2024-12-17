package dev.lpsmods.poses;

import dev.lpsmods.poses.platform.ForgePoseLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.AddReloadListenerEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

/**
 * Author: legopitstop
 **/
@Mod(Constants.MOD_ID)
public class ArmorStandPoses {

    public ArmorStandPoses() {
//        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
//        bus.addListener(this::registerReloadListener);
        MinecraftForge.EVENT_BUS.register(this);
    }

//    private void registerReloadListener(AddReloadListenerEvent event) {
//        event.addListener(new ForgePoseLoader());
//    }

    public void onRegisterReloadListeners(AddReloadListenerEvent event) {
        event.addListener(new ForgePoseLoader());
    }
}