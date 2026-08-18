package at.yedel.fontrast.mixin;



import at.yedel.fontrast.config.FontrastConfig;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.client.gui.Font;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;



@Mixin(Font.class)
public abstract class FontMixin_Advanced {
    @ModifyExpressionValue(method = "lambda$drawInBatch8xOutline$0", at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/font/GlyphInfo;getShadowOffset()F"))
    private float fontrast$getShadowOffset$drawInBatch8xOutline(float original) {
        return FontrastConfig.getInstance().getShadowOffset(original);
    }
}
