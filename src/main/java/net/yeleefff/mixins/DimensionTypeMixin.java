package net.yeleefff.mixins;

import net.minecraft.world.level.dimension.DimensionType;
import net.yeleefff.moonphase.Config;
import net.yeleefff.moonphase.MoonPhase;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;


@Mixin(DimensionType.class)
public abstract class DimensionTypeMixin {
    @Inject(method = "moonPhase", at = @At("HEAD"), cancellable = true)
    private void getCustomMoonPhase(long p_63937_, CallbackInfoReturnable<Integer> cir) {
        if (Config.constantMoonPhase) {
            cir.setReturnValue(MoonPhase.moonPhaseToIndex(Config.moonPhase));
        }
    }
}