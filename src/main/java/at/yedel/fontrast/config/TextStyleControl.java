package at.yedel.fontrast.config;



import dev.isxander.yacl3.api.ConfigCategory;
import dev.isxander.yacl3.api.Option;
import dev.isxander.yacl3.api.OptionDescription;
import dev.isxander.yacl3.api.OptionGroup;
import dev.isxander.yacl3.api.controller.BooleanControllerBuilder;
import dev.isxander.yacl3.api.controller.IntegerFieldControllerBuilder;
import dev.isxander.yacl3.api.controller.NumberFieldControllerBuilder;
import dev.isxander.yacl3.api.controller.StringControllerBuilder;
import dev.isxander.yacl3.config.v2.api.SerialEntry;
import dev.isxander.yacl3.gui.controllers.slider.FloatSliderController;
import dev.isxander.yacl3.gui.controllers.string.StringController;
import dev.isxander.yacl3.gui.controllers.string.number.NumberFieldController;
import dev.isxander.yacl3.impl.controller.BooleanControllerBuilderImpl;
import net.minecraft.network.chat.Component;



public class TextStyleControl {
    @SerialEntry public ControlOption boldControl = ControlOption.DEFAULT;
    @SerialEntry public ControlOption italicControl = ControlOption.DEFAULT;
    @SerialEntry public ControlOption strikethroughControl = ControlOption.DEFAULT;
    @SerialEntry public ControlOption underlineControl = ControlOption.DEFAULT;
    @SerialEntry public ControlOption obfuscationControl = ControlOption.DEFAULT;
    @SerialEntry public float strikethroughOpacity = 1f;
    @SerialEntry public boolean useLegacyObfuscation = false;
    @SerialEntry public boolean useCustomObfuscationCharacters = false;
    @SerialEntry public String customObfuscationCharacters = "\u00c0\u00c1\u00c2\u00c8\u00ca\u00cb\u00cd\u00d3\u00d4\u00d5\u00da\u00df\u00e3\u00f5\u011f\u0130\u0131\u0152\u0153\u015e\u015f\u0174\u0175\u017e\u0207\u0000\u0000\u0000\u0000\u0000\u0000\u0000 !\"#$%&\'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_`abcdefghijklmnopqrstuvwxyz{|}~\u0000\u00c7\u00fc\u00e9\u00e2\u00e4\u00e0\u00e5\u00e7\u00ea\u00eb\u00e8\u00ef\u00ee\u00ec\u00c4\u00c5\u00c9\u00e6\u00c6\u00f4\u00f6\u00f2\u00fb\u00f9\u00ff\u00d6\u00dc\u00f8\u00a3\u00d8\u00d7\u0192\u00e1\u00ed\u00f3\u00fa\u00f1\u00d1\u00aa\u00ba\u00bf\u00ae\u00ac\u00bd\u00bc\u00a1\u00ab\u00bb\u2591\u2592\u2593\u2502\u2524\u2561\u2562\u2556\u2555\u2563\u2551\u2557\u255d\u255c\u255b\u2510\u2514\u2534\u252c\u251c\u2500\u253c\u255e\u255f\u255a\u2554\u2569\u2566\u2560\u2550\u256c\u2567\u2568\u2564\u2565\u2559\u2558\u2552\u2553\u256b\u256a\u2518\u250c\u2588\u2584\u258c\u2590\u2580\u03b1\u03b2\u0393\u03c0\u03a3\u03c3\u03bc\u03c4\u03a6\u0398\u03a9\u03b4\u221e\u2205\u2208\u2229\u2261\u00b1\u2265\u2264\u2320\u2321\u00f7\u2248\u00b0\u2219\u00b7\u221a\u207f\u00b2\u25a0\u0000";
    @SerialEntry public boolean useUnobfuscatedFallback = false;

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
                .description(OptionDescription.of(Component.literal("Controls the obfuscation style on text.\n⚠ Setting this to \"Always\" will DEFINITELY drop your game's performance.")))
                .binding(
                    defaults.obfuscationControl,
                    () -> control.obfuscationControl,
                    (obfuscationControl) -> control.obfuscationControl = obfuscationControl
                )
                .controller(ControlOption.controller())
                .build()
            )
            .group(OptionGroup.createBuilder()
                .name(Component.literal("Strikethrough"))
                .description(OptionDescription.of(Component.literal("Control the look and functionality of strikethrough text.")))
                .option(Option.<Float>createBuilder()
                    .name(Component.literal("Strikethrough Opacity"))
                    .description(OptionDescription.of(Component.literal("Changes the opacity of the strikethrough line.")))
                    .binding(
                        defaults.strikethroughOpacity,
                        () -> control.strikethroughOpacity,
                        (strikethroughOpacity) -> control.strikethroughOpacity = strikethroughOpacity
                    )
                    .customController((option) -> new FloatSliderController(option, 0f, 1f, 0.01f))
                    .build()
                )
                .build()
            )
            .group(OptionGroup.createBuilder()
                .name(Component.literal("Obfuscation"))
                .description(OptionDescription.of(Component.literal("Control the look and functionality of obfuscated text.")))
                .option(Option.<Boolean>createBuilder()
                    .name(Component.literal("Use Legacy Obfuscation"))
                    .description(OptionDescription.of(Component.literal("Uses only the characters from 1.8's obfuscation method, which looks cleaner.")))
                    .binding(
                        defaults.useLegacyObfuscation,
                        () -> control.useLegacyObfuscation,
                        (useLegacyObfuscation) -> control.useLegacyObfuscation = useLegacyObfuscation
                    )
                    .controller(BooleanControllerBuilder::create)
                    .build()
                )
                .option(Option.<Boolean>createBuilder()
                    .name(Component.literal("Use Custom Obfuscation Characters"))
                    .description(OptionDescription.of(Component.literal("Uses custom characters to select from when obfuscating text with the legacy method.\nThis will require a resource reload to take effect.")))
                    .binding(
                        defaults.useCustomObfuscationCharacters,
                        () -> control.useCustomObfuscationCharacters,
                        (useCustomObfuscationCharacters) -> control.useCustomObfuscationCharacters = useCustomObfuscationCharacters
                    )
                    .controller(BooleanControllerBuilder::create)
                    .build()
                )
                .option(Option.<String>createBuilder()
                    .name(Component.literal("Custom Obfuscation Characters"))
                    .binding(
                        defaults.customObfuscationCharacters,
                        () -> control.customObfuscationCharacters,
                        (customObfuscationCharacters) -> control.customObfuscationCharacters = customObfuscationCharacters
                    )
                    .controller(StringControllerBuilder::create)
                    .build()
                )
                .option(Option.<Boolean>createBuilder()
                    .name(Component.literal("Use Unobfuscated Fallback"))
                    .description(OptionDescription.of(Component.literal("When a random glyph cannot be found, falls back to the original glyph instead of a missing one.")))
                    .binding(
                        defaults.useUnobfuscatedFallback,
                        () -> control.useUnobfuscatedFallback,
                        (useUnobfuscatedFallback) -> control.useUnobfuscatedFallback = useUnobfuscatedFallback
                    )
                    .controller(BooleanControllerBuilder::create)
                    .build()
                )
                .build()
            )
            .build();
    }
}
