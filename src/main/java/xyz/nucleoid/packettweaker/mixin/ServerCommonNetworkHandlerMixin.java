package xyz.nucleoid.packettweaker.mixin;

import com.mojang.authlib.GameProfile;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerCommonNetworkHandler;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import xyz.nucleoid.packettweaker.ContextProvidingPacketListener;

@SuppressWarnings("AddedMixinMembersNamePattern")
@Mixin(ServerCommonNetworkHandler.class)
public abstract class ServerCommonNetworkHandlerMixin implements ContextProvidingPacketListener {
    @Shadow @Final protected MinecraftServer server;

    @Shadow protected abstract GameProfile getProfile();

    @Override
    public @Nullable RegistryWrapper.WrapperLookup getWrapperLookupForPacketTweaker() {
        return this.server.getRegistryManager();
    }


    @Override
    public @Nullable GameProfile getGameProfileForPacketTweaker() {
        return this.getProfile();
    }
}
