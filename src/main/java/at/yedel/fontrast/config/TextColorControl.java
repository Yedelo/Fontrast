package at.yedel.fontrast.config;



import dev.isxander.yacl3.api.ConfigCategory;
import dev.isxander.yacl3.api.Option;
import dev.isxander.yacl3.api.OptionDescription;
import dev.isxander.yacl3.api.OptionGroup;
import dev.isxander.yacl3.api.controller.BooleanControllerBuilder;
import dev.isxander.yacl3.api.controller.ColorControllerBuilder;
import dev.isxander.yacl3.config.v2.api.SerialEntry;
import dev.isxander.yacl3.gui.controllers.slider.FloatSliderController;
import net.minecraft.network.chat.Component;

import java.awt.*;



public class TextColorControl {
    @SerialEntry boolean multiplyColors = false;
    @SerialEntry Color colorMultiplier = Color.white;
    @SerialEntry public float shadowScale = 0.25f;
    @SerialEntry CustomTextColors customTextColors = new CustomTextColors();

    public static ConfigCategory createCategory(TextColorControl defaults, TextColorControl control) {
        return ConfigCategory.createBuilder()
            .name(Component.literal("Text Color Control"))
            .tooltip(Component.literal("Control the color of text."))
            .group(OptionGroup.createBuilder()
                .name(Component.literal("General"))
                .option(Option.<Boolean>createBuilder()
                    .name(Component.literal("Multiply Colors"))
                    .description(OptionDescription.of(Component.literal("Enables multiplying all text colors by this color.")))
                    .binding(
                        defaults.multiplyColors,
                        () -> control.multiplyColors,
                        (multiplyColors) -> control.multiplyColors = multiplyColors
                    )
                    .controller(BooleanControllerBuilder::create)
                    .build()
                )
                .option(Option.<Color>createBuilder()
                    .name(Component.literal("Color Multiplier"))
                    .description(OptionDescription.of(Component.literal("The color to multiply by.")))
                    .binding(
                        defaults.colorMultiplier,
                        () -> control.colorMultiplier,
                        (colorMultiplier) -> control.colorMultiplier = colorMultiplier
                    )
                    .controller((option) -> ColorControllerBuilder.create(option).allowAlpha(true))
                    .build()
                )
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
                .build()
            )
            .group(CustomTextColors.createGroup(defaults.customTextColors, control.customTextColors))
            .build();
    }
}
