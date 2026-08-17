package at.yedel.fontrast.mixin;



import at.yedel.fontrast.config.FontrastConfig;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.client.gui.font.glyphs.BakedSheetGlyph;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;



@Mixin(BakedSheetGlyph.class)
public abstract class BakedSheetGlyphMixin {
    @ModifyExpressionValue(method = "left", at = @At(value = "INVOKE", target = "Lnet/minecraft/network/chat/Style;isBold()Z"))
    private boolean fontrast$isBold$left(boolean original) {
        return FontrastConfig.getInstance().isBold(original);
    }

    @ModifyExpressionValue(method = "top", at = @At(value = "INVOKE", target = "Lnet/minecraft/network/chat/Style;isBold()Z"))
    private boolean fontrast$isBold$top(boolean original) {
        return FontrastConfig.getInstance().isBold(original);
    }

    @ModifyExpressionValue(method = "right", at = @At(value = "INVOKE", target = "Lnet/minecraft/network/chat/Style;isBold()Z"))
    private boolean fontrast$isBold$right(boolean original) {
        return FontrastConfig.getInstance().isBold(original);
    }

    @ModifyExpressionValue(method = "bottom", at = @At(value = "INVOKE", target = "Lnet/minecraft/network/chat/Style;isBold()Z"))
    private boolean fontrast$isBold$bottom(boolean original) {
        return FontrastConfig.getInstance().isBold(original);
    }

    @ModifyExpressionValue(method = "renderChar", at = @At(value = "INVOKE", target = "Lnet/minecraft/network/chat/Style;isBold()Z"))
    private boolean fontrast$isBold$renderChar(boolean original) {
        return FontrastConfig.getInstance().isBold(original);
    }

    @ModifyExpressionValue(method = "left", at = @At(value = "INVOKE", target = "Lnet/minecraft/network/chat/Style;isItalic()Z"))
    private boolean fontrast$isItalic$left(boolean original) {
        return FontrastConfig.getInstance().isItalic(original);
    }

    @ModifyExpressionValue(method = "right", at = @At(value = "INVOKE", target = "Lnet/minecraft/network/chat/Style;isItalic()Z"))
    private boolean fontrast$isItalic$right(boolean original) {
        return FontrastConfig.getInstance().isItalic(original);
    }

    @ModifyExpressionValue(method = "renderChar", at = @At(value = "INVOKE", target = "Lnet/minecraft/network/chat/Style;isItalic()Z"))
    private boolean fontrast$isItalic$renderChar(boolean original) {
        return FontrastConfig.getInstance().isItalic(original);
    }
}
