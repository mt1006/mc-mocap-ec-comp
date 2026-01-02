package net.mt1006.mocapcomp.emotecraft;

import dev.kosmx.playerAnim.core.data.KeyframeAnimation;
import net.mt1006.mocapcomp.emotecraft.actions.PlayEmoteAction;
import net.mt1006.mocapcomp.emotecraft.actions.StopEmoteAction;

import java.util.UUID;

public class EmotecraftEvents
{
	public static void onEmotePlay(KeyframeAnimation animation, UUID playerUUID)
	{
		if (MocapEmotecraftCompMod.extension.isRecordingActive())
		{
			MocapEmotecraftCompMod.extension.findRecordingByRecordedPlayerUUID(playerUUID)
					.forEach((rec) -> rec.addAction(new PlayEmoteAction(animation)));
		}
	}

	public static void onEmoteStop(UUID emoteID, UUID playerUUID)
	{
		if (MocapEmotecraftCompMod.extension.isRecordingActive())
		{
			MocapEmotecraftCompMod.extension.findRecordingByRecordedPlayerUUID(playerUUID)
					.forEach((rec) -> rec.addAction(StopEmoteAction.INSTANCE));
		}
	}
}
