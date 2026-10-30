package at.yedel.fontrast.mixin;



import at.yedel.fontrast.config.FontrastConfig;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.network.chat.Style;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;



@Mixin(Style.class)
public abstract class StyleMixin {
    @ModifyReturnValue(method = "isBold", at = @At("RETURN"))
    private boolean fontrast$isBold(boolean original) {
        return FontrastConfig.getInstance().isBold(original);
    }

    @ModifyReturnValue(method = "isItalic", at = @At("RETURN"))
    private boolean fontrast$isItalic(boolean original) {
        return FontrastConfig.getInstance().isItalic(original);
    }

    @ModifyReturnValue(method = "isStrikethrough", at = @At("RETURN"))
    private boolean fontrast$isStrikethrough(boolean original) {
        return FontrastConfig.getInstance().isStrikethrough(original);
    }

    @ModifyReturnValue(method = "isUnderlined", at = @At("RETURN"))
    private boolean fontrast$isUnderlined(boolean original) {
        return FontrastConfig.getInstance().isUnderlined(original);
    }

    @ModifyReturnValue(method = "isObfuscated", at = @At("RETURN"))
    private boolean fontrast$isObfuscated(boolean original) {
        return FontrastConfig.getInstance().isObfuscated(original);
    }
}
