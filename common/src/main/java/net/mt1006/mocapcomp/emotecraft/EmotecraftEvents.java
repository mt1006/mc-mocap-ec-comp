package net.mt1006.mocapcomp.emotecraft;

import com.zigythebird.playeranimcore.animation.Animation;
import net.mt1006.mocapcomp.emotecraft.actions.PlayEmoteAction;
import net.mt1006.mocapcomp.emotecraft.actions.StopEmoteAction;

import java.util.UUID;

public class EmotecraftEvents
{
	public static void onEmotePlay(Animation animation, float tick, UUID playerUUID)
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
