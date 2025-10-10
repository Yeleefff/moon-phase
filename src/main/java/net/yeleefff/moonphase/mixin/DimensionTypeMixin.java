package net.yeleefff.moonphase.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.world.level.dimension.DimensionType;
import net.yeleefff.moonphase.Config;
import net.yeleefff.moonphase.MoonPhase;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(DimensionType.class)
public class DimensionTypeMixin {
    @ModifyReturnValue(method = "moonPhase", at = @At("RETURN"))
    private int getCustomMoonPhase(int time) {
        if (Config.CONSTANT_MOON_PHASE.get()) {
            return MoonPhase.moonPhaseToIndex(Config.MOON_PHASE.get());
        } else {
            return time;
        }
    }
}
