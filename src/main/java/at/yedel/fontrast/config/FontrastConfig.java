package at.yedel.fontrast.config;



import dev.isxander.yacl3.api.*;
import dev.isxander.yacl3.api.controller.BooleanControllerBuilder;
import dev.isxander.yacl3.config.v2.api.ConfigClassHandler;
import dev.isxander.yacl3.config.v2.api.SerialEntry;
import dev.isxander.yacl3.config.v2.api.serializer.GsonConfigSerializerBuilder;
    /*? if fabric {*/
import net.fabricmc.loader.api.FabricLoader;
/*?} elif neoforge {*/
/*import net.neoforged.fml.loading.FMLPaths;
*//*?}*/
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.HoverEvent;
import net.minecraft.resources.Identifier;
import net.minecraft.util.ARGB;

import java.lang.reflect.Modifier;
import java.nio.file.Path;



public class FontrastConfig {
    /*? if fabric {*/
    private static final Path CONFIG_FILE_PATH = FabricLoader.getInstance().getConfigDir().resolve("fontrast.json");
    /*?} else if neoforge {*/
    /*private static final Path CONFIG_FILE_PATH = FMLPaths.CONFIGDIR.get().resolve("fontrast.json");
    *//*?}*/
    public static final ConfigClassHandler<FontrastConfig> HANDLER = ConfigClassHandler.createBuilder(FontrastConfig.class)
        .id(Identifier.fromNamespaceAndPath("fontrast", "fontrast-config"))
        .serializer(
            config -> GsonConfigSerializerBuilder.create(config)
                .setPath(CONFIG_FILE_PATH)
                .setJson5(false)
                .build()
        )
        .build();

    public static FontrastConfig getInstance() {
        return HANDLER.instance();
    }

    public static void init() {
        HANDLER.load();
    }

    private static final Component FORMATTING_GUIDE = Component.literal(
        "§cC§6o§el§ao§9r §1c§5o§dd§be§3s§r:" + // "Color codes:" (in rainbow)
            "\n§8Black: §8&0     §4Dark Red: §4&4     §2Dark Green: §2&2     §1Dark Blue: §1&1" +
            "\n§3Dark Aqua: §3&3     §5Dark Purple: §5&5     §6Gold: §6&6     §7Gray: §7&7" +
            "\n§8Dark Gray: §8&8     §9Blue: §9&9     §aGreen: §a&a     §bAqua: §b&b" +
            "\n§cRed: §c&c     §dLight Purple: §d&d     §eYellow: §e&e     §fWhite: §f&f" +
            "\n" +
            "\n§lStyle §ncodes§r:" +
            "\n§kObfuscated§r: &k     §r§lBold: §l&l     §r§mStrikethrough: §m&m§r" +
            "\n§nUnderline: §n&n§r     §r§oItalic: §o&o    §rReset: §r&r"
    );
    private static final Component FORMATTING_GUIDE_MESSAGE =
        Component.literal("§e§nHover to view the formatting guide.").withStyle((style) -> style.withHoverEvent(new HoverEvent.ShowText(FORMATTING_GUIDE)));

    @SerialEntry public boolean enabled = true;
    @SerialEntry public TextStyleControl textStyleControl = new TextStyleControl();
    @SerialEntry public TextColorControl textColorControl = new TextColorControl();
    @SerialEntry public AdvancedControl advancedControl = new AdvancedControl();

    public static Screen getScreen(Screen parent) {
        return YetAnotherConfigLib.create(HANDLER, (defaults, config, builder) -> {
                builder.title(Component.literal("Fontrast Config"));
                builder.category(ConfigCategory.createBuilder()
                    .name(Component.literal("General"))
                    .option(Option.<Boolean>createBuilder()
                        .name(Component.literal("Enabled"))
                        .description(OptionDescription.of(Component.literal("Global toggle for the mod.")))
                        .binding(
                            defaults.enabled,
                            () -> config.enabled,
                            (enabled) -> config.enabled = enabled
                        )
                        .controller(BooleanControllerBuilder::create)
                        .build()
                    )
                    .option(ButtonOption.createBuilder()
                        .name(Component.literal("Show Formatting Guide"))
                        .description(OptionDescription.of(Component.literal("Shows the following in chat:\n").append(FORMATTING_GUIDE)))
                        .action((screen, button) -> Minecraft.getInstance().gui.getChat().addClientSystemMessage(FORMATTING_GUIDE_MESSAGE))
                        // ts says "EXECUTE" by default
                        .text(Component.literal("Show"))
                        .build()
                    )
                    .build()
                );
                builder.category(TextStyleControl.createCategory(defaults.textStyleControl, config.textStyleControl));
                builder.category(TextColorControl.createCategory(defaults.textColorControl, config.textColorControl));
                builder.category(AdvancedControl.createCategory(defaults.advancedControl, config.advancedControl));
                return builder;
            }
        ).generateScreen(parent);
    }

    public boolean isBold(boolean original) {
        if (enabled) {
            return textStyleControl.boldControl.shouldApply(original);
        }
        return original;
    }

    public boolean isItalic(boolean original) {
        if (enabled) {
            return textStyleControl.italicControl.shouldApply(original);
        }
        return original;
    }

    public boolean isStrikethrough(boolean original) {
        if (enabled) {
            return textStyleControl.strikethroughControl.shouldApply(original);
        }
        return original;
    }

    public boolean isUnderlined(boolean original) {
        if (enabled) {
            return textStyleControl.underlineControl.shouldApply(original);
        }
        return original;
    }

    public boolean isObfuscated(boolean original) {
        if (enabled) {
            return textStyleControl.obfuscationControl.shouldApply(original);
        }
        return original;
    }

    public int getTextColor(int original) {
        if (enabled) {
            int color = original;
            if (textColorControl.customTextColors.enabled) {
                color = textColorControl.customTextColors.getColor(color, color);
            }
            if (textColorControl.multiplyColors) {
                color = ARGB.multiply(color, textColorControl.colorMultiplier);
            }
            return color;
        }
        return original;
    }

    public int getShadowColor(int original, int textColor) {
        if (enabled) {
            if (textColorControl.customShadowColors.enabled) {
                return textColorControl.customShadowColors.getColor(original, textColor);
            }
        }
        return original;
    }

    public float getShadowScale(float original) {
        if (enabled) {
            return textColorControl.shadowScale;
        }
        return original;
    }

    public float getShadowOffset(float original) {
        if (enabled) {
            if (advancedControl.enabled) {
                return original * advancedControl.shadowOffsetMultiplier;
            }
        }
        return original;
    }

    public float getBoldOffset(float original) {
        if (enabled) {
            if (advancedControl.enabled) {
                return original * advancedControl.boldOffsetMultiplier;
            }
        }
        return original;
    }
}