package at.yedel.fontrast.config;



import dev.isxander.yacl3.api.ConfigCategory;
import dev.isxander.yacl3.api.Option;
import dev.isxander.yacl3.api.OptionDescription;
import dev.isxander.yacl3.api.OptionGroup;
import dev.isxander.yacl3.api.controller.BooleanControllerBuilder;
import dev.isxander.yacl3.config.v2.api.SerialEntry;
import dev.isxander.yacl3.gui.controllers.slider.FloatSliderController;
import net.minecraft.network.chat.Component;



public class AdvancedControl {
    @SerialEntry boolean enabled = false;
    @SerialEntry float shadowOffsetMultiplier = 1f;
    @SerialEntry float boldOffsetMultiplier = 1f;

    public static ConfigCategory createCategory(AdvancedControl defaults, AdvancedControl control) {
        return ConfigCategory.createBuilder()
            .name(Component.literal("Advanced Control"))
            .option(Option.<Boolean>createBuilder()
                .name(Component.literal("Enabled"))
                .binding(
                    defaults.enabled,
                    () -> control.enabled,
                    (enabled) -> control.enabled = enabled
                )
                .controller(BooleanControllerBuilder::create)
                .build()
            )
            .group(OptionGroup.createBuilder()
                .name(Component.literal("Offsets"))
                .description(OptionDescription.of(Component.literal("Control how different offsets function.")))
                .option(Option.<Float>createBuilder()
                    .name(Component.literal("Shadow Offset Multiplier"))
                    .description(OptionDescription.of(Component.literal("Multiplies the shadow offset by the given number.")))
                    .binding(
                        defaults.shadowOffsetMultiplier,
                        () -> control.shadowOffsetMultiplier,
                        (shadowOffsetMultiplier) -> control.shadowOffsetMultiplier = shadowOffsetMultiplier
                    )
                    .customController((option) -> new FloatSliderController(option, 0f, 2f, 0.01f))
                    .build()
                )
                .option(Option.<Float>createBuilder()
                    .name(Component.literal("Bold Offset Multiplier"))
                    .description(OptionDescription.of(Component.literal("Multiplies the bold offset by the given number.")))
                    .binding(
                        defaults.boldOffsetMultiplier,
                        () -> control.boldOffsetMultiplier,
                        (boldOffsetMultiplier) -> control.boldOffsetMultiplier = boldOffsetMultiplier
                    )
                    .customController((option) -> new FloatSliderController(option, 0f, 2f, 0.01f))
                    .build()
                )
                .build()
            )
            .build();
    }
}
