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
import net.minecraft.resources.Identifier;
import net.minecraft.util.ARGB;

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

    private static final String SAMPLE_TEXT = "normal §mstrikethrough §nunderline §lbold §ccolor §c§lcolorbold";

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
                        .name(Component.literal("Show Sample Text"))
                        .description(OptionDescription.of(Component.literal("Shows the following in chat:\n" + SAMPLE_TEXT)))
                        .action((screen, button) -> Minecraft.getInstance().gui.getChat().addClientSystemMessage(Component.literal(SAMPLE_TEXT)))
                        // ts says "EXECUTE" by default
                        .text(Component.literal("Show"))
                        .build()
                    )
                    .build()
                );
                builder.category(TextStyleControl.createCategory(defaults.textStyleControl, config.textStyleControl));
                builder.category(TextColorControl.createCategory(defaults.textColorControl, config.textColorControl));
                return builder;
            }
        ).generateScreen(parent);
    }

    @SerialEntry public boolean enabled = true;
    @SerialEntry public TextStyleControl textStyleControl = new TextStyleControl();
    @SerialEntry public TextColorControl textColorControl = new TextColorControl();

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

    public int getTextColor(int original) {
        if (enabled) {
            if (textColorControl.multiplyColors) {
                return ARGB.multiply(original, textColorControl.colorMultiplier.getRGB());
            }
            else if (textColorControl.customTextColorControl.enabled) {
                return textColorControl.customTextColorControl.getTextColor(original);
            }
        }
        return original;
    }

    public int getShadowColor(int original, int textColor) {
        return original;
    }
}