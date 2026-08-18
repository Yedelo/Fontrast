package at.yedel.fontrast.config;



import dev.isxander.yacl3.api.Option;
import dev.isxander.yacl3.api.OptionDescription;
import dev.isxander.yacl3.api.OptionGroup;
import dev.isxander.yacl3.api.controller.BooleanControllerBuilder;
import dev.isxander.yacl3.api.controller.ColorControllerBuilder;
import dev.isxander.yacl3.config.v2.api.SerialEntry;
import net.minecraft.network.chat.Component;

import java.awt.*;
import java.util.*;
import java.util.List;



public class CustomTextColors {
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
    @SerialEntry List<Integer> colorStore = new ArrayList<>(RAW_COLOR_DEFINITIONS);
    
    int getTextColor(int original) {
        if (RAW_COLOR_DEFINITIONS.contains(original)) {
            return colorStore.get(RAW_COLOR_DEFINITIONS.indexOf(original));
        }
        return original;
    }

    public static OptionGroup createGroup(CustomTextColors defaults, CustomTextColors colors) {
        OptionGroup.Builder builder = OptionGroup.createBuilder();
        builder
            .name(Component.literal("Custom Text Color Control"))
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
            );
        for (int i = 0; i < COLOR_DEFINITIONS.size(); i ++) {
            ColorDefinition colorDefinition = COLOR_DEFINITIONS.get(i);
            // thank you intellij
            int finalI = i;
            builder.option(Option.<Color>createBuilder()
                .name(Component.literal(colorDefinition.name).append(" Color"))
                .description(OptionDescription.of(colorDefinition.description()))
                .binding(
                    new Color(defaults.colorStore.get(i)),
                    () -> new Color(colors.colorStore.get(finalI), true),
                    (col) -> colors.colorStore.set(finalI, col.getRGB())
                )
                .controller((option) -> ColorControllerBuilder.create(option).allowAlpha(true))
                .build()
            );
        }
        return builder.build();
    }

    private static void register(int argb, String name, String code) {
        RAW_COLOR_DEFINITIONS.add(argb);
        COLOR_DEFINITIONS.add(new ColorDefinition(argb, name, code));
    }

    private record ColorDefinition(int argb, String name, String code) {
        Component description() {
            return Component.literal("Overrides the color value of the ")
                .append(name.toLowerCase().replace(" ", "_"))
                .append(" color (&")
                .append(code)
                .append(", #")
                .append(String.format("%08x", argb))
                .append(")");
        }
    }
}
