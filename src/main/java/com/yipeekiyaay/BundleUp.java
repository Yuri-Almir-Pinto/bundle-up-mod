package com.yipeekiyaay;

import com.yipeekiyaay.network.BundleUpNetworking;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BundleUp implements ModInitializer {
	public static final String MOD_ID = "bundle-up";
	public static final String FRAGILE_BUNDLE_NBT = "bundle-up-fragile-bundle";

	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		LOGGER.info("Initializing Bundle Up!");

		BundleUpNetworking.registerPackets();
		BundleUpNetworking.registerC2SHandlers();
	}
}