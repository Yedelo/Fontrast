package at.yedel.fontrast.mixin;



import at.yedel.fontrast.config.FontrastConfig;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;



@Mixin(targets = "net.minecraft.client.gui.Font$PreparedTextBuilder")
public abstract class PreparedTextBuilderMixin_Advanced {
    @ModifyExpressionValue(method = "accept(ILnet/minecraft/network/chat/Style;Lnet/minecraft/client/gui/font/glyphs/BakedGlyph;)Z", at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/font/GlyphInfo;getShadowOffset()F"))
    private float fontrast$getShadowOffset$accept(float original) {
        return FontrastConfig.getInstance().getShadowOffset(original);
    }

    @ModifyExpressionValue(method = "accept(ILnet/minecraft/network/chat/Style;Lnet/minecraft/client/gui/font/glyphs/BakedGlyph;)Z", at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/font/GlyphInfo;getBoldOffset()F"))
    private float fontrast$getBoldOffset(float original) {
        return FontrastConfig.getInstance().getBoldOffset(original);
    }
}
