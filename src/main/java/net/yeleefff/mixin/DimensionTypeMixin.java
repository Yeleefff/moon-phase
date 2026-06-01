package net.yeleefff.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.world.level.dimension.DimensionType;
import net.yeleefff.Moonphase;
import net.yeleefff.config.ConfigHandler;
import net.yeleefff.config.ConfigState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(DimensionType.class)
public abstract class DimensionTypeMixin {
	@ModifyReturnValue(method = "moonPhase(J)I", at = @At("RETURN"))
	private int getCustomMoonPhase(int time) {
		ConfigState state = ConfigHandler.getState();

		if (state.constantMoonPhase) {
			return Moonphase.moonPhaseToIndex(state.moonPhase);
		} else {
			return time;
		}
	}
}