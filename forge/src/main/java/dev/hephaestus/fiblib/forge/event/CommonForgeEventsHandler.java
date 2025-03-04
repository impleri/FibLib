package dev.hephaestus.fiblib.forge.event;

import dev.hephaestus.fiblib.FibLib;
import dev.hephaestus.fiblib.forge.impl.FibLootModifier;
import dev.hephaestus.fiblib.impl.LifecycleHooks;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.event.AddReloadListenerEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.server.ServerLifecycleHooks;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
public class CommonForgeEventsHandler {

    @SubscribeEvent
    public static void onReloadResources(AddReloadListenerEvent event) {
        LifecycleHooks.handleReloadResources();
    }

    @SubscribeEvent
    public static void onTick(TickEvent.ServerTickEvent event) {
        LifecycleHooks.handleTick(ServerLifecycleHooks.getCurrentServer().getPlayerList());
    }
}
