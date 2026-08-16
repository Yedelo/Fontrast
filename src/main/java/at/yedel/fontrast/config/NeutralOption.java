package at.yedel.fontrast.config;



import dev.isxander.yacl3.api.NameableEnum;
import dev.isxander.yacl3.api.Option;
import dev.isxander.yacl3.api.controller.ControllerBuilder;
import dev.isxander.yacl3.api.controller.EnumControllerBuilder;
import net.minecraft.network.chat.Component;

import java.util.function.Function;
import java.util.function.UnaryOperator;



public enum NeutralOption implements NameableEnum {
    NEVER(Component.literal("Never"), (original) -> false),
    DEFAULT(Component.literal("Default"), (original) -> original),
    ALWAYS(Component.literal("Always"), (original) -> true);

    private final Component displayName;
    private final UnaryOperator<Boolean> shouldApply;

    NeutralOption(Component displayName, UnaryOperator<Boolean> shouldApply) {
        this.displayName = displayName;
        this.shouldApply = shouldApply;
    }

    public boolean shouldApply(boolean original) {
        return shouldApply.apply(original);
    }

    @Override
    public Component getDisplayName() {
        return displayName;
    }

    public static Function<Option<NeutralOption>, ControllerBuilder<NeutralOption>> controller() {
        return (option) -> EnumControllerBuilder.create(option).enumClass(NeutralOption.class);
    }
}
