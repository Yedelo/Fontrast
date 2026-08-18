package at.yedel.fontrast.mixin;



import at.yedel.fontrast.config.FontrastConfig;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.client.gui.Font;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;



@Mixin(Font.class)
public abstract class FontMixin {
    // bless intellij plugin for finding this for me
    @ModifyExpressionValue(method = "lambda$new$0", at = @At(value = "INVOKE", target = "Lnet/minecraft/network/chat/Style;isBold()Z"))
    private boolean fontrast$isBold$init(boolean original) {
        return FontrastConfig.getInstance().isBold(original);
    }

    @ModifyExpressionValue(method = "lambda$drawInBatch8xOutline$0", at = @At(value = "INVOKE", target = "Lnet/minecraft/network/chat/Style;isBold()Z"))
    private boolean fontrast$isBold$drawInBatch8xOutline(boolean original) {
        return FontrastConfig.getInstance().isBold(original);
    }

    @ModifyExpressionValue(method = "getGlyph", at = @At(value = "INVOKE", target = "Lnet/minecraft/network/chat/Style;isObfuscated()Z"))
    private boolean fontrast$isObfuscated(boolean original) {
        return FontrastConfig.getInstance().isObfuscated(original);
    }
}
