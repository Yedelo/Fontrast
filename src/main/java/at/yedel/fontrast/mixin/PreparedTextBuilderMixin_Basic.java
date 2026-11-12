package at.yedel.fontrast.mixin;



import at.yedel.fontrast.config.FontrastConfig;
import com.llamalad7.mixinextras.expression.Definition;
import com.llamalad7.mixinextras.expression.Expression;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.util.ARGB;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;



@Mixin(targets = "net.minecraft.client.gui.Font$PreparedTextBuilder")
public abstract class PreparedTextBuilderMixin_Basic {
    @Unique private int fontrast$textColor;

    @ModifyExpressionValue(method = "accept(ILnet/minecraft/network/chat/Style;Lnet/minecraft/client/gui/font/glyphs/BakedGlyph;)Z", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/Font$PreparedTextBuilder;getTextColor(Lnet/minecraft/network/chat/TextColor;)I"))
    private int fontrast$getTextColor(int original) {
        fontrast$textColor = original;
        return FontrastConfig.getInstance().getTextColor(original);
    }

    @Definition(id = "shadow", local = @Local(type = Integer.class))
    @Expression("shadow != null")
    @ModifyExpressionValue(method = "getShadowColor", at = @At("MIXINEXTRAS:EXPRESSION"))
    private boolean fontrast$disableCustomShadowColors(boolean original) {
        if (FontrastConfig.getInstance().enabled && FontrastConfig.getInstance().textColorControl.disableCustomShadowColors) {
            return false;
        }
        return original;
    }

    @ModifyExpressionValue(method = "accept(ILnet/minecraft/network/chat/Style;Lnet/minecraft/client/gui/font/glyphs/BakedGlyph;)Z", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/Font$PreparedTextBuilder;getShadowColor(Lnet/minecraft/network/chat/Style;I)I"))
    private int fontrast$getShadowColor(int original) {
        return FontrastConfig.getInstance().getShadowColor(original, fontrast$textColor);
    }

    @ModifyArg(method = "getShadowColor", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/ARGB;scaleRGB(IF)I"), index = 1)
    private float fontrast$getShadowScale(float original) {
        return FontrastConfig.getInstance().getShadowScale(original);
    }

    @ModifyArg(method = "accept(ILnet/minecraft/network/chat/Style;Lnet/minecraft/client/gui/font/glyphs/BakedGlyph;)Z", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/font/glyphs/EffectGlyph;createEffect(FFFFFIIF)Lnet/minecraft/client/gui/font/TextRenderable;", ordinal = 0), index = 5)
    private int fontrast$customStrikethroughOpacity$text(int i) {
        if (FontrastConfig.getInstance().enabled) {
            return ARGB.multiplyAlpha(i, FontrastConfig.getInstance().textStyleControl.strikethroughOpacity);
        }
        return i;
    }

    @ModifyArg(method = "accept(ILnet/minecraft/network/chat/Style;Lnet/minecraft/client/gui/font/glyphs/BakedGlyph;)Z", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/font/glyphs/EffectGlyph;createEffect(FFFFFIIF)Lnet/minecraft/client/gui/font/TextRenderable;", ordinal = 0), index = 6)
    private int fontrast$customStrikethroughOpacity$shadow(int i) {
        if (FontrastConfig.getInstance().enabled) {
            return ARGB.multiplyAlpha(i, FontrastConfig.getInstance().textStyleControl.strikethroughOpacity);
        }
        return i;
    }
}
