package net.mt1006.mocapcomp.emotecraft;

import com.zigythebird.playeranimcore.animation.Animation;
import io.github.kosmx.emotes.api.events.server.ServerEmoteAPI;
import io.github.kosmx.emotes.api.events.server.ServerEmoteEvents;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.mt1006.mocap.api.v1.MocapAPI;
import net.mt1006.mocap.api.v1.extension.MocapExtension;
import net.mt1006.mocapcomp.emotecraft.actions.PlayEmoteAction;
import net.mt1006.mocapcomp.emotecraft.actions.StopEmoteAction;
import net.mt1006.mocapcomp.emotecraft.mixin.PlayerListFields;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.Nullable;

import java.util.Map;
import java.util.UUID;

public class MocapEmotecraftCompMod
{
	public static final String MOD_ID = "mocapemotecraftcomp";
	public static final Logger LOGGER = LogManager.getLogger();
	public static MocapExtension extension = null;

	//TODO: update "homepage" in gradle.properties
	public static void init()
	{
		MocapAPI.executeAfterInit(MocapEmotecraftCompMod::onMocapInit);
	}

	private static void onMocapInit()
	{
		extension = MocapAPI.registerExtension(MOD_ID, (short)1);
		if (extension == null)
		{
			LOGGER.error("Failed to initialize {}!", MOD_ID);
			return;
		}

		extension.registerAction(0, PlayEmoteAction::new);
		extension.registerAction(1, (reader, data) -> StopEmoteAction.INSTANCE);

		ServerEmoteEvents.EMOTE_PLAY.register(EmotecraftEvents::onEmotePlay);
		ServerEmoteEvents.EMOTE_STOP_BY_USER.register(EmotecraftEvents::onEmoteStop);
	}

	public static void playEmote(MinecraftServer server, ServerPlayer player, @Nullable Animation animation)
	{
		Map<UUID, ServerPlayer> playerMap = ((PlayerListFields)server.getPlayerList()).getPlayersByUUID();
		UUID playerUUID = player.getUUID();

		if (playerMap.containsKey(playerUUID))
		{
			ServerEmoteAPI.playEmote(playerUUID, animation, false);
		}
		else
		{
			playerMap.put(playerUUID, player);
			try
			{
				ServerEmoteAPI.playEmote(playerUUID, animation, false);
			}
			finally
			{
				playerMap.remove(playerUUID);
			}
		}
	}
}
