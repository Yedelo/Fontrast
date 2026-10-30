package at.yedel.fontrast.mixin;



import at.yedel.fontrast.utils.TargettedGlyphInfo;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.font.glyphs.BakedGlyph;
import net.minecraft.network.chat.Style;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;



@Mixin(Font.class)
public abstract class FontMixin_Basic {
    @Inject(method = "getGlyph", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GlyphSource;getRandomGlyph(Lnet/minecraft/util/RandomSource;I)Lnet/minecraft/client/gui/font/glyphs/BakedGlyph;"))
    private void fontrast$storeTargettedGlyph(int codepoint, Style style, CallbackInfoReturnable<BakedGlyph> cir, @Local BakedGlyph glyph) {
        TargettedGlyphInfo.targettedGlyph = glyph;
    }
}
