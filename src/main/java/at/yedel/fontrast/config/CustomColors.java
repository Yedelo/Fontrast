package at.yedel.fontrast.config;



import dev.isxander.yacl3.api.ButtonOption;
import dev.isxander.yacl3.api.Option;
import dev.isxander.yacl3.api.OptionDescription;
import dev.isxander.yacl3.api.OptionGroup;
import dev.isxander.yacl3.api.controller.BooleanControllerBuilder;
import dev.isxander.yacl3.api.controller.ColorControllerBuilder;
import dev.isxander.yacl3.config.v2.api.SerialEntry;
import net.minecraft.network.chat.Component;
import net.minecraft.util.ARGB;

import java.awt.*;
import java.util.*;
import java.util.List;



public class CustomColors {
    private static final List<Integer> RAW_COLOR_DEFINITIONS = new ArrayList<>();
    private static final List<ColorDefinition> COLOR_DEFINITIONS = new ArrayList<>();

    static {
        register(0xFF000000, "Black", "0");
        register(0xFF0000AA, "Dark Blue", "1");
        register(0xFF00AA00, "Dark Green", "2");
        register(0xFF00AAAA, "Dark Aqua", "3");
        register(0xFFAA0000, "Dark Red", "4");
        register(0xFFAA00AA, "Dark Purple", "5");
        register(0xFFFFAA00, "Gold", "6");
        register(0xFFAAAAAA, "Gray", "7");
        register(0xFF555555, "Dark Gray", "8");
        register(0xFF5555FF, "Blue", "9");
        register(0xFF55FF55, "Green", "a");
        register(0xFF55FFFF, "Aqua", "b");
        register(0xFFFF5555, "Red", "c");
        register(0xFFFF55FF, "Light Purple", "d");
        register(0xFFFFFF55, "Yellow", "e");
        register(0xFFFFFFFF, "White", "f");
    }

    @SerialEntry boolean enabled = false;
    // using raw integer colors instead of awt Colors because they are much easier
    @SerialEntry List<Integer> colorStore = new ArrayList<>(RAW_COLOR_DEFINITIONS);

    public CustomColors(boolean shadow) {
        if (shadow) {
            for (int i = 0; i < colorStore.size(); i ++) {
                colorStore.set(i, ARGB.scaleRGB(colorStore.get(i), 0.25f));
            }
        }
    }
    
    int getColor(int original, int textColor) {
        if (RAW_COLOR_DEFINITIONS.contains(textColor)) {
            return colorStore.get(RAW_COLOR_DEFINITIONS.indexOf(textColor));
        }
        return original;
    }

    public static OptionGroup createGroup(CustomColors defaults, CustomColors colors, boolean shadow) {
        OptionGroup.Builder builder = OptionGroup.createBuilder();
        builder
            .name(Component.literal(shadow ? "Custom Shadow Colors" : "Custom Text Colors"))
            .description(OptionDescription.of(Component.literal("Customizes the color of text.")))
            .option(Option.<Boolean>createBuilder()
                .name(Component.literal("Enabled"))
                .binding(
                    defaults.enabled,
                    () -> colors.enabled,
                    (enabled) -> colors.enabled = enabled
                )
                .controller(BooleanControllerBuilder::create)
                .build()
            )
            .option(ButtonOption.createBuilder()
                .name(Component.literal("Randomize Colors"))
                .description(OptionDescription.of(Component.literal("Randomizes every color below, minus the alpha component which stays at 100%.")))
                .text(Component.literal("Randomize"))
                .action((screen, option) -> {
                    for (Option<Color> colorOption : OptionRegistry.getOptions(colors)) {
                        colorOption.stateManager().set(new Color(0xFF000000 | new Random().nextInt(0xFFFFFF), true));
                    }
                    FontrastConfig.HANDLER.save();
                })
                .build()
            );
        for (int i = 0; i < COLOR_DEFINITIONS.size(); i ++) {
            ColorDefinition colorDefinition = COLOR_DEFINITIONS.get(i);
            // thank you intellij
            int finalI = i;
            Option<Color> colorOption = Option.<Color>createBuilder()
                .name(Component.literal(colorDefinition.name).append(shadow ? " Shadow Color" : " Color"))
                .description(OptionDescription.of(colorDefinition.description(shadow)))
                .binding(
                    new Color(defaults.colorStore.get(i)),
                    () -> new Color(colors.colorStore.get(finalI), true),
                    (col) -> colors.colorStore.set(finalI, col.getRGB())
                )
                .controller((option) -> ColorControllerBuilder.create(option).allowAlpha(true))
                .build();
            OptionRegistry.registerOption(colors, colorOption);
            builder.option(colorOption);
        }
        return builder.build();
    }

    private static void register(int argb, String name, String code) {
        RAW_COLOR_DEFINITIONS.add(argb);
        COLOR_DEFINITIONS.add(new ColorDefinition(argb, name, code));
    }



    private record ColorDefinition(int argb, String name, String code) {
        Component description(boolean shadow) {
            return Component.literal("Overrides the color value of the ")
                .append(name.toLowerCase().replace(" ", "_"))
                .append(shadow ? " shadow color (&" : " color (&")
                .append(code)
                .append(", #")
                .append(String.format("%08x", argb))
                .append(")");
        }
    }
}
