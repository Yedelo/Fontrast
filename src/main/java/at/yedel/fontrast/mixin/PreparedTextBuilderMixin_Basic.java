package at.yedel.fontrast.mixin;



import at.yedel.fontrast.config.FontrastConfig;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.client.gui.Font;
import net.minecraft.network.chat.Style;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Coerce;
import org.spongepowered.asm.mixin.injection.ModifyArg;



@Mixin(targets = "net.minecraft.client.gui.Font$PreparedTextBuilder")
public abstract class PreparedTextBuilderMixin_Basic {
    @Unique private int fontrast$textColor;
    @Shadow protected abstract int getShadowColor(Style style, int textColor);

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

    @ModifyExpressionValue(method = "accept(ILnet/minecraft/network/chat/Style;Lnet/minecraft/client/gui/font/glyphs/BakedGlyph;)Z", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/Font$PreparedTextBuilder;getTextColor(Lnet/minecraft/network/chat/TextColor;)I"))
    private int fontrast$getTextColor(int original) {
        fontrast$textColor = original;
        return FontrastConfig.getInstance().getTextColor(original);
    }

    @ModifyExpressionValue(method = "accept(ILnet/minecraft/network/chat/Style;Lnet/minecraft/client/gui/font/glyphs/BakedGlyph;)Z", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/Font$PreparedTextBuilder;getShadowColor(Lnet/minecraft/network/chat/Style;I)I"))
    private int fontrast$getShadowColor(int original) {
        return FontrastConfig.getInstance().getShadowColor(original, fontrast$textColor);
    }

    @ModifyArg(method = "getShadowColor", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/ARGB;scaleRGB(IF)I"), index = 1)
    private float fontrast$getShadowScale(float original) {
        return FontrastConfig.getInstance().getShadowScale(original);
    }
}
