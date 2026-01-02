package net.mt1006.mocapcomp.emotecraft.actions;

import dev.kosmx.playerAnim.core.data.KeyframeAnimation;
import io.github.kosmx.emotes.server.serializer.UniversalEmoteSerializer;
import net.minecraft.server.level.ServerPlayer;
import net.mt1006.mocap.api.v1.extension.MocapRecordingData;
import net.mt1006.mocap.api.v1.extension.actions.MocapAction;
import net.mt1006.mocap.api.v1.extension.actions.MocapActionContext;
import net.mt1006.mocapcomp.emotecraft.MocapEmotecraftCompMod;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public class PlayEmoteAction implements MocapAction
{
	private final UUID uuid;
	private final @Nullable KeyframeAnimation animation;

	public PlayEmoteAction(KeyframeAnimation animation)
	{
		this.uuid = animation.getUuid();
		this.animation = animation;
	}

	public PlayEmoteAction(Reader reader, MocapRecordingData data)
	{
		this.uuid = reader.readUUID();
		this.animation = reader.isDummy() ? null : UniversalEmoteSerializer.getEmote(uuid);
	}

	@Override public void write(Writer writer, MocapRecordingData data)
	{
		writer.addUUID(uuid);
	}

	@Override public Result execute(MocapActionContext ctx)
	{
		if (ctx.getEntity() instanceof ServerPlayer player)
		{
			MocapEmotecraftCompMod.playEmote(ctx.getLevel().getServer(), player, animation);
			return Result.OK;
		}
		return Result.IGNORED;
	}
}
