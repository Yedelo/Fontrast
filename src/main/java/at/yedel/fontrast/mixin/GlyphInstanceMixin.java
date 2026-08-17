package at.yedel.fontrast.mixin;



import at.yedel.fontrast.config.FontrastConfig;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;



@Mixin(targets = "net.minecraft.client.gui.font.glyphs.BakedSheetGlyph$GlyphInstance")
public abstract class GlyphInstanceMixin {
    @ModifyExpressionValue(method = "activeRight", at = @At(value = "INVOKE", target = "Lnet/minecraft/network/chat/Style;isBold()Z"))
    private boolean fontrast$isBold(boolean original) {
        return FontrastConfig.getInstance().isBold(original);
    }
}
