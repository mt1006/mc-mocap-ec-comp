package net.mt1006.mocapcomp.emotecraft;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.loading.FMLEnvironment;

@Mod(MocapEmotecraftCompMod.MOD_ID)
public class MocapEmotecraftCompModNeoForge
{
	public static final boolean isDedicatedServer = FMLEnvironment.getDist().isDedicatedServer();

	public MocapEmotecraftCompModNeoForge(IEventBus eventBus)
	{
		MocapEmotecraftCompMod.init();
	}
}
