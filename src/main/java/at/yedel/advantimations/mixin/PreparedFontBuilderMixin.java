package at.yedel.advantimations.mixin;



import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;



@Mixin(targets = "net.minecraft.client.gui.Font$PreparedTextBuilder")
public class PreparedFontBuilderMixin {
    @ModifyExpressionValue(method = "accept(ILnet/minecraft/network/chat/Style;Lnet/minecraft/client/gui/font/glyphs/BakedGlyph;)Z", at = @At(value = "INVOKE", target = "getTextColor"))
    private int modifyTextColor(int original) {
        return 0xFF000000;
    }

    @ModifyExpressionValue(method = "accept(ILnet/minecraft/network/chat/Style;Lnet/minecraft/client/gui/font/glyphs/BakedGlyph;)Z", at = @At(value = "INVOKE", target = "getShadowColor"))
    private int modifyShadowColor(int original) {
        return 0xFFFFFFFF;
    }
}
