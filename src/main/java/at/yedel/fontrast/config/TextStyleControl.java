package at.yedel.fontrast.config;



import dev.isxander.yacl3.api.ConfigCategory;
import dev.isxander.yacl3.api.Option;
import dev.isxander.yacl3.api.OptionDescription;
import dev.isxander.yacl3.api.OptionGroup;
import dev.isxander.yacl3.api.controller.BooleanControllerBuilder;
import dev.isxander.yacl3.api.controller.IntegerFieldControllerBuilder;
import dev.isxander.yacl3.api.controller.NumberFieldControllerBuilder;
import dev.isxander.yacl3.config.v2.api.SerialEntry;
import dev.isxander.yacl3.gui.controllers.string.number.NumberFieldController;
import dev.isxander.yacl3.impl.controller.BooleanControllerBuilderImpl;
import net.minecraft.network.chat.Component;



public class TextStyleControl {
    private static final String MAX_OBFUSCATION_PASSES_DESCRIPTION = "The legacy obfuscated text fetching system uses a while loop to fetch random characters until one is equal in width to the character being obfuscated. For certain characters, there may not be a match at all, causing the while loop to repeat infinitely and freeze the game. This option limits the amount of iterations the loop can run, which makes the loop give up after a certain amount of passes. Lower values lead to some characters not being obfuscated at all, and may be more performant.";

    @SerialEntry public ControlOption boldControl = ControlOption.DEFAULT;
    @SerialEntry public ControlOption italicControl = ControlOption.DEFAULT;
    @SerialEntry public ControlOption strikethroughControl = ControlOption.DEFAULT;
    @SerialEntry public ControlOption underlineControl = ControlOption.DEFAULT;
    @SerialEntry public ControlOption obfuscationControl = ControlOption.DEFAULT;
    @SerialEntry public boolean useLegacyObfuscation = false;
    @SerialEntry public int maxObfuscationPasses = 10;

    public static ConfigCategory createCategory(TextStyleControl defaults, TextStyleControl control) {
        return ConfigCategory.createBuilder()
            .name(Component.literal("Text Style Control"))
            .tooltip(Component.literal("Control the style of text."))
            .option(Option.<ControlOption>createBuilder()
                .name(Component.literal("Bold Control"))
                .description(OptionDescription.of(Component.literal("Controls the bold style on text.")))
                .binding(
                    defaults.boldControl,
                    () -> control.boldControl,
                    (boldControl) -> control.boldControl = boldControl
                )
                .controller(ControlOption.controller())
                .build()
            )
            .option(Option.<ControlOption>createBuilder()
                .name(Component.literal("Italic Control"))
                .description(OptionDescription.of(Component.literal("Controls the italic style on text.")))
                .binding(
                    defaults.italicControl,
                    () -> control.italicControl,
                    (italicControl) -> control.italicControl = italicControl
                )
                .controller(ControlOption.controller())
                .build()
            )
            .option(Option.<ControlOption>createBuilder()
                .name(Component.literal("Strikethrough Control"))
                .description(OptionDescription.of(Component.literal("Controls the strikethrough style on text.")))
                .binding(
                    defaults.strikethroughControl,
                    () -> control.strikethroughControl,
                    (strikethroughControl) -> control.strikethroughControl = strikethroughControl
                )
                .controller(ControlOption.controller())
                .build()
            )
            .option(Option.<ControlOption>createBuilder()
                .name(Component.literal("Underline Control"))
                .description(OptionDescription.of(Component.literal("Controls the underline style on text.")))
                .binding(
                    defaults.underlineControl,
                    () -> control.underlineControl,
                    (underlineControl) -> control.underlineControl = underlineControl
                )
                .controller(ControlOption.controller())
                .build()
            )
            .option(Option.<ControlOption>createBuilder()
                .name(Component.literal("Obfuscation Control"))
                .description(OptionDescription.of(Component.literal("Controls the obfuscation style on text.\n⚠ Setting this to \"Always\" may drop your game's performance.")))
                .binding(
                    defaults.obfuscationControl,
                    () -> control.obfuscationControl,
                    (obfuscationControl) -> control.obfuscationControl = obfuscationControl
                )
                .controller(ControlOption.controller())
                .build()
            )
            .group(OptionGroup.createBuilder()
                .name(Component.literal("Obfuscation"))
                .description(OptionDescription.of(Component.literal("Control the look and functionality of obfuscated text.")))
                .option(Option.<Boolean>createBuilder()
                    .name(Component.literal("Use Legacy Obfuscation"))
                    .description(OptionDescription.of(Component.literal("Uses an older method for getting obfuscated text, which can look cleaner.")))
                    .binding(
                        defaults.useLegacyObfuscation,
                        () -> control.useLegacyObfuscation,
                        (useLegacyObfuscation) -> control.useLegacyObfuscation = useLegacyObfuscation
                    )
                    .controller(BooleanControllerBuilder::create)
                    .build()
                )
                .option(Option.<Integer>createBuilder()
                    .name(Component.literal("Max Obfuscation Passes"))
                    .description(OptionDescription.of(Component.literal(MAX_OBFUSCATION_PASSES_DESCRIPTION)))
                    .binding(
                        defaults.maxObfuscationPasses,
                        () -> control.maxObfuscationPasses,
                        (maxObfuscationPasses) -> control.maxObfuscationPasses = maxObfuscationPasses
                    )
                    .controller((option) -> IntegerFieldControllerBuilder.create(option).min(1))
                    .build()
                )
                .build()
            )
            .build();
    }
}
