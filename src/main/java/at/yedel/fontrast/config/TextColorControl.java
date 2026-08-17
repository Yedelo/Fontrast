package at.yedel.fontrast.config;



import dev.isxander.yacl3.api.ConfigCategory;
import dev.isxander.yacl3.api.Option;
import dev.isxander.yacl3.api.OptionDescription;
import dev.isxander.yacl3.config.v2.api.SerialEntry;
import dev.isxander.yacl3.gui.controllers.slider.FloatSliderController;
import net.minecraft.network.chat.Component;



public class TextColorControl {
    public static ConfigCategory createCategory(TextColorControl defaults, TextColorControl control) {
        return ConfigCategory.createBuilder()
            .name(Component.literal("Text Color Control"))
            .tooltip(Component.literal("Control the style of text."))
            .option(Option.<Float>createBuilder()
                .name(Component.literal("Shadow Scale"))
                .description(OptionDescription.of(Component.literal("Control how shadow colors scale.\n\nThis controls the multiplier of the shadow colors. A scale of 0 means that shadows will be completely black, and a scale of 1 means that shadows will be the same color as the main text.")))
                .binding(
                    defaults.shadowScale,
                    () -> control.shadowScale,
                    (shadowScale) -> control.shadowScale = shadowScale
                )
                .customController((option) -> new FloatSliderController(option, 0f, 1f, 0.01f))
                .build()
            )
            .build();
    }

    @SerialEntry public float shadowScale;
}
