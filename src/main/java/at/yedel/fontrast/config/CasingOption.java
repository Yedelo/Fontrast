package at.yedel.fontrast.config;



import dev.isxander.yacl3.api.NameableEnum;
import dev.isxander.yacl3.api.Option;
import dev.isxander.yacl3.api.controller.ControllerBuilder;
import dev.isxander.yacl3.api.controller.EnumControllerBuilder;
import net.minecraft.network.chat.Component;

import java.util.function.Function;
import java.util.function.UnaryOperator;



public enum CasingOption implements NameableEnum {
    LOWERCASE(Component.literal("Lowercase")),
    DEFAULT(Component.literal("Default")),
    UPPERCASE(Component.literal("Uppercase"));

    private final Component displayName;

    CasingOption(Component displayName) {
        this.displayName = displayName;
    }

    @Override
    public Component getDisplayName() {
        return displayName;
    }

    public static Function<Option<CasingOption>, ControllerBuilder<CasingOption>> controller() {
        return (option) -> EnumControllerBuilder.create(option).enumClass(CasingOption.class);
    }
}
