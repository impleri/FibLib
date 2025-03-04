package dev.hephaestus.fiblib.fabric.mixin.level.chunk;

import dev.hephaestus.fiblib.FibLib;
import dev.hephaestus.fiblib.fabric.Fixable;
import net.minecraft.network.protocol.Packet;
import net.minecraft.server.level.ChunkHolder;
import net.minecraft.world.level.ChunkPos;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ChunkHolder.class)
public class MixinChunkHolder {
    @Shadow
    @Final
    private ChunkHolder.PlayerProvider playerProvider;
    @Shadow
    @Final
    private ChunkPos pos;

    @SuppressWarnings("UnresolvedMixinReference")
    @Inject(method = "broadcast", at = @At("HEAD"), cancellable = true)
    private void fixPackets(Packet<?> packet, boolean bl, CallbackInfo ci) {
        FibLib.debug("Maybe fixing ChunkHolder packet %s", packet.toString());
        if (packet instanceof Fixable) {
            this.playerProvider.getPlayers(this.pos, bl).forEach(serverPlayer -> {
                FibLib.debug("Fixing ChunkHolder packet %s for %s", packet.toString(), serverPlayer.getName().getString());
                ((Fixable) packet).fix(serverPlayer);
                serverPlayer.connection.send(packet);
                ci.cancel();
            });
        }
    }
}
