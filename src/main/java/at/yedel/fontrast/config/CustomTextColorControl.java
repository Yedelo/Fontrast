package at.yedel.fontrast.config;



import dev.isxander.yacl3.api.Option;
import dev.isxander.yacl3.api.OptionDescription;
import dev.isxander.yacl3.api.OptionGroup;
import dev.isxander.yacl3.api.controller.BooleanControllerBuilder;
import dev.isxander.yacl3.config.v2.api.SerialEntry;
import net.minecraft.network.chat.Component;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;



public class CustomTextColorControl {
    private static final List<ColorInfo> colors = new ArrayList<>();

    @SerialEntry boolean enabled = false;
    @SerialEntry ArrayList<Integer> colorList = new ArrayList<>();

    static {
        register("Black", "0", 0x000000);
        register("Dark Blue", "1", 0x0000AA);
        register("Dark Green", "2", 0x00AA00);
        register("Dark Aqua", "3", 0x00AAAA);
        register("Dark Red", "4", 0xAA0000);
        register("Dark Purple", "5", 0xAA00AA);
        register("Gold", "6", 0xFFAA00);
        register("Gray", "7", 0xAAAAAA);
        register("Dark Gray", "8", 0x555555);
        register("Blue", "9", 0x5555FF);
        register("Green", "a", 0x55FF55);
        register("Aqua", "b", 0x55FFFF);
        register("Red", "c", 0xFF5555);
        register("Light Purple", "d", 0xFF55FF);
        register("Yellow", "e", 0xFFFF55);
        register("White", "f", 0xFFFFFF);
    }

    public static OptionGroup createGroup(CustomTextColorControl defaults, CustomTextColorControl control) {
        OptionGroup.Builder builder = OptionGroup.createBuilder();
        builder
            .name(Component.literal("Custom Text Color Control"))
            .description(OptionDescription.of(Component.literal("Customizes the color of text.")))
            .option(Option.<Boolean>createBuilder()
                .name(Component.literal("Enabled"))
                .binding(
                    defaults.enabled,
                    () -> control.enabled,
                    (enabled) -> control.enabled = enabled
                )
                .controller(BooleanControllerBuilder::create)
                .build()
            );
        for (int i = 0; i < colors.size(); i ++) {
            ColorInfo color = colors.get(i);
            builder.option(Option.<Integer>createBuilder()
                .name(Component.literal(color.name).append(" ").append(" Color"))
                .description(OptionDescription.of(Component.literal("Controls the color value of color \"").append(color.name.toLowerCase().replace(" ", "_")).append("\" or code \"").append(color.code).append("\".")))
                    .binding(defaults.)
                .build()
            );
        }
        return builder.build();
    }

    private static void register(String name, String code, int rgb) {
        colors.add(new ColorInfo(name, code, rgb));
    }

    private record ColorInfo(String name, String code, int rgb) {}
}
