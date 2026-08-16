package at.yedel.fontrast.mixin;



import at.yedel.fontrast.config.FontrastConfig;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;



@Mixin(targets = "net.minecraft.client.gui.Font$PreparedTextBuilder")
public abstract class PreparedTextBuilderMixin_Basic {
    @ModifyExpressionValue(method = "accept(ILnet/minecraft/network/chat/Style;Lnet/minecraft/client/gui/font/glyphs/BakedGlyph;)Z", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/Font$PreparedTextBuilder;getTextColor(Lnet/minecraft/network/chat/TextColor;)I"))
    private int fontrast$getTextColor(int original) {
        return original;
    }

    @ModifyExpressionValue(method = "accept(ILnet/minecraft/network/chat/Style;Lnet/minecraft/client/gui/font/glyphs/BakedGlyph;)Z", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/Font$PreparedTextBuilder;getShadowColor(Lnet/minecraft/network/chat/Style;I)I"))
    private int fontrast$getShadowColor(int original) {
        return original;
    }

    @ModifyArg(method = "getShadowColor", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/ARGB;scaleRGB(IF)I"), index = 1)
    private float fontrast$getShadowScale(float original) {
        if (FontrastConfig.getInstance().isEnabled()) {
            return FontrastConfig.getInstance().getShadowScale();
        }
        return original;
    }

    @ModifyExpressionValue(method = "accept(ILnet/minecraft/network/chat/Style;Lnet/minecraft/client/gui/font/glyphs/BakedGlyph;)Z", at = @At(value = "INVOKE", target = "Lnet/minecraft/network/chat/Style;isStrikethrough()Z"))
    private boolean fontrast$isStrikethrough(boolean original) {
        if (FontrastConfig.getInstance().isEnabled()) {
            return FontrastConfig.getInstance().getStrikethroughControl().shouldApply(original);
        }
        return original;
    }

    @ModifyExpressionValue(method = "accept(ILnet/minecraft/network/chat/Style;Lnet/minecraft/client/gui/font/glyphs/BakedGlyph;)Z", at = @At(value = "INVOKE", target = "Lnet/minecraft/network/chat/Style;isUnderlined()Z"))
    private boolean fontrast$isUnderlined(boolean original) {
        if (FontrastConfig.getInstance().isEnabled()) {
            return FontrastConfig.getInstance().getUnderlineControl().shouldApply(original);
        }
        return original;
    }
}
