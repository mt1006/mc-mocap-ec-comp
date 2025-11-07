package net.mt1006.mocapcomp.emotecraft.actions;

import net.minecraft.server.level.ServerPlayer;
import net.mt1006.mocap.api.v1.extension.MocapRecordingData;
import net.mt1006.mocap.api.v1.extension.actions.MocapAction;
import net.mt1006.mocap.api.v1.extension.actions.MocapActionContext;
import net.mt1006.mocapcomp.emotecraft.MocapEmotecraftCompMod;

public class StopEmoteAction implements MocapAction
{
	public static final StopEmoteAction INSTANCE = new StopEmoteAction();

	private StopEmoteAction() {}

	@Override public void write(Writer writer, MocapRecordingData data) {}

	@Override public Result execute(MocapActionContext ctx)
	{
		if (ctx.getEntity() instanceof ServerPlayer player)
		{
			MocapEmotecraftCompMod.playEmote(ctx.getLevel().getServer(), player, null);
			return Result.OK;
		}
		return Result.IGNORED;
	}
}
