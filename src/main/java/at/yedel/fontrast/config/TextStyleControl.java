package at.yedel.fontrast.config;



import dev.isxander.yacl3.api.ConfigCategory;
import dev.isxander.yacl3.api.Option;
import dev.isxander.yacl3.api.OptionDescription;
import dev.isxander.yacl3.config.v2.api.SerialEntry;
import net.minecraft.network.chat.Component;



public class TextStyleControl {
    @SerialEntry public ControlOption boldControl = ControlOption.DEFAULT;
    @SerialEntry public ControlOption italicControl = ControlOption.DEFAULT;
    @SerialEntry public ControlOption strikethroughControl = ControlOption.DEFAULT;
    @SerialEntry public ControlOption underlineControl = ControlOption.DEFAULT;
    @SerialEntry public ControlOption obfuscationControl = ControlOption.DEFAULT;

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
                .description(OptionDescription.of(Component.literal("Controls the obfuscation style on text.")))
                .binding(
                    defaults.obfuscationControl,
                    () -> control.obfuscationControl,
                    (obfuscationControl) -> control.obfuscationControl = obfuscationControl
                )
                .controller(ControlOption.controller())
                .build()
            )
            .build();
    }
}
