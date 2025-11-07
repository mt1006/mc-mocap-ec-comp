package net.mt1006.mocapcomp.emotecraft;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;

public class MocapEmotecraftCompModFabric implements ModInitializer
{
	private static final FabricLoader FABRIC_LOADER = FabricLoader.getInstance();

	@Override public void onInitialize()
	{
		MocapEmotecraftCompMod.init();
	}
}
