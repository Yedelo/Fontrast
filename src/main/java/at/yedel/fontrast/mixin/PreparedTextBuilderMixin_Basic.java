package at.yedel.fontrast.mixin;



import at.yedel.fontrast.config.FontrastConfig;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;



@Mixin(targets = "net.minecraft.client.gui.Font$PreparedTextBuilder")
public abstract class PreparedTextBuilderMixin_Basic {
    @ModifyExpressionValue(method = "accept(ILnet/minecraft/network/chat/Style;Lnet/minecraft/client/gui/font/glyphs/BakedGlyph;)Z", at = @At(value = "INVOKE", target = "Lnet/minecraft/network/chat/Style;isBold()Z"))
    private boolean fontrast$isBold(boolean original) {
        return FontrastConfig.getInstance().isBold(original);
    }

    @ModifyExpressionValue(method = "accept(ILnet/minecraft/network/chat/Style;Lnet/minecraft/client/gui/font/glyphs/BakedGlyph;)Z", at = @At(value = "INVOKE", target = "Lnet/minecraft/network/chat/Style;isStrikethrough()Z"))
    private boolean fontrast$isStrikethrough(boolean original) {
        return FontrastConfig.getInstance().isStrikethrough(original);
    }

    @ModifyExpressionValue(method = "accept(ILnet/minecraft/network/chat/Style;Lnet/minecraft/client/gui/font/glyphs/BakedGlyph;)Z", at = @At(value = "INVOKE", target = "Lnet/minecraft/network/chat/Style;isUnderlined()Z"))
    private boolean fontrast$isUnderlined(boolean original) {
        return FontrastConfig.getInstance().isUnderlined(original);
    }

    @ModifyArg(method = "getShadowColor", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/ARGB;scaleRGB(IF)I"), index = 1)
    private float fontrast$getShadowScale(float original) {
        if (FontrastConfig.getInstance().isEnabled()) {
            return FontrastConfig.getInstance().getShadowScale();
        }
        return original;
    }
}
