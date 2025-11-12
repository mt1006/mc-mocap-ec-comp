package net.mt1006.mocapcomp.emotecraft;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

@Mod(MocapEmotecraftCompMod.MOD_ID)
public class MocapEmotecraftCompModNeoForge
{
	public MocapEmotecraftCompModNeoForge(IEventBus eventBus)
	{
		MocapEmotecraftCompMod.init();
	}
}
