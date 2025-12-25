package com.example.bundle_up.networking;

import com.example.bundle_up.Constants;
import com.example.bundle_up.domain.BundleItemHandler;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

import java.util.function.Supplier;

public class ForgePacketHandler {
    private static final String PROTOCOL_VERSION = "1";
    public static final SimpleChannel INSTANCE = NetworkRegistry.newSimpleChannel(
            new ResourceLocation(Constants.MOD_ID, "main"),
            () -> PROTOCOL_VERSION,
            PROTOCOL_VERSION::equals,
            PROTOCOL_VERSION::equals
    );

    public static void init() {
        int id = 0;
        INSTANCE.registerMessage(id++, BundleC2SPacket.class,
                BundleC2SPacket::encode,
                BundleC2SPacket::decode,
                BundleC2SPacket::handle
        );
    }

    // The Packet Class itself (Internal Wrapper)
    public static class BundleC2SPacket {
        public BundleC2SPacket() {}

        // Encode/Decode are empty because we send no data (void payload)
        public static void encode(BundleC2SPacket msg, net.minecraft.network.FriendlyByteBuf buf) {}
        public static BundleC2SPacket decode(net.minecraft.network.FriendlyByteBuf buf) {
            return new BundleC2SPacket();
        }

        public static void handle(BundleC2SPacket msg, Supplier<NetworkEvent.Context> ctx) {
            ctx.get().enqueueWork(() -> {
                ServerPlayer player = ctx.get().getSender();
                if (player != null) {
                    var bundleUpContext = new BundleItemHandler.BundleUpContext(player);
                    BundleItemHandler.HandleBundleUpPacket(bundleUpContext);
                }
            });
            ctx.get().setPacketHandled(true);
        }
    }
}