package at.yedel.fontrast.config;



import dev.isxander.yacl3.api.*;
import dev.isxander.yacl3.api.controller.BooleanControllerBuilder;
import dev.isxander.yacl3.config.v2.api.ConfigClassHandler;
import dev.isxander.yacl3.config.v2.api.SerialEntry;
import dev.isxander.yacl3.config.v2.api.serializer.GsonConfigSerializerBuilder;
import dev.isxander.yacl3.gui.controllers.slider.FloatSliderController;
/*? if fabric {*/
import net.fabricmc.loader.api.FabricLoader;
/*?} elif neoforge {*/
/*import net.neoforged.fml.loading.FMLPaths;
*//*?}*/
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;

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
                            defaults.isEnabled(),
                            config::isEnabled,
                            config::setEnabled
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
                builder.category(ConfigCategory.createBuilder()
                    .name(Component.literal("Text Style Control"))
                    .tooltip(Component.literal("Control the style of text."))
                    .option(Option.<NeutralOption>createBuilder()
                        .name(Component.literal("Bold Control"))
                        .description(OptionDescription.of(Component.literal("Controls the bold style on text.")))
                        .binding(
                            defaults.getBoldControl(),
                            config::getBoldControl,
                            config::setBoldControl
                        )
                        .controller(NeutralOption.controller())
                        .build()
                    )
                    .option(Option.<NeutralOption>createBuilder()
                        .name(Component.literal("Italic Control"))
                        .description(OptionDescription.of(Component.literal("Controls the italic style on text.")))
                        .binding(
                            defaults.getItalicControl(),
                            config::getItalicControl,
                            config::setItalicControl
                        )
                        .controller(NeutralOption.controller())
                        .build()
                    )
                    .option(Option.<NeutralOption>createBuilder()
                        .name(Component.literal("Strikethrough Control"))
                        .description(OptionDescription.of(Component.literal("Controls the strikethrough style on text.")))
                        .binding(
                            defaults.getStrikethroughControl(),
                            config::getStrikethroughControl,
                            config::setStrikethroughControl
                        )
                        .controller(NeutralOption.controller())
                        .build()
                    )
                    .option(Option.<NeutralOption>createBuilder()
                        .name(Component.literal("Underline Control"))
                        .description(OptionDescription.of(Component.literal("Controls the underline style on text.")))
                        .binding(
                            defaults.getUnderlineControl(),
                            config::getUnderlineControl,
                            config::setUnderlineControl
                        )
                        .controller(NeutralOption.controller())
                        .build()
                    )
                    .build()
                );
                builder.category(ConfigCategory.createBuilder()
                    .name(Component.literal("Text Color Control"))
                    .tooltip(Component.literal("Control the style of text."))
                    .option(Option.<Float>createBuilder()
                        .name(Component.literal("Shadow Scale"))
                        .description(OptionDescription.of(Component.literal("Control how shadow colors scale.\n\nThis controls the multiplier of the shadow colors. A scale of 0 means that shadows will be completely black, and a scale of 1 means that shadows will be the same color as the main text.")))
                        .binding(
                            defaults.getShadowScale(),
                            config::getShadowScale,
                            config::setShadowScale
                        )
                        .customController((option) -> new FloatSliderController(option, 0f, 1f, 0.01f))
                        .build()
                    )
                    .build()
                );
                return builder;
            }
        ).generateScreen(parent);
    }

    @SerialEntry private boolean enabled = true;
    @SerialEntry private NeutralOption boldControl = NeutralOption.DEFAULT;
    @SerialEntry private NeutralOption italicControl = NeutralOption.DEFAULT;
    @SerialEntry private NeutralOption strikethroughControl = NeutralOption.DEFAULT;
    @SerialEntry private NeutralOption underlineControl = NeutralOption.DEFAULT;
    @SerialEntry private float shadowScale = 0.25f;

    public boolean isBold(boolean original) {
        if (isEnabled()) {
            return getBoldControl().shouldApply(original);
        }
        return original;
    }

    public boolean isItalic(boolean original) {
        if (isEnabled()) {
            return getItalicControl().shouldApply(original);
        }
        return original;
    }

    public boolean isStrikethrough(boolean original) {
        if (isEnabled()) {
            return getStrikethroughControl().shouldApply(original);
        }
        return original;
    }

    public boolean isUnderlined(boolean original) {
        if (isEnabled()) {
            return getUnderlineControl().shouldApply(original);
        }
        return original;
    }

    public boolean isEnabled() {
        return enabled;
    }

    private void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    private NeutralOption getBoldControl() {
        return boldControl;
    }

    private void setBoldControl(NeutralOption boldControl) {
        this.boldControl = boldControl;
    }

    private NeutralOption getItalicControl() {
        return italicControl;
    }

    private void setItalicControl(NeutralOption italicControl) {
        this.italicControl = italicControl;
    }

    private NeutralOption getStrikethroughControl() {
        return strikethroughControl;
    }

    private void setStrikethroughControl(NeutralOption strikethroughControl) {
        this.strikethroughControl = strikethroughControl;
    }

    private NeutralOption getUnderlineControl() {
        return underlineControl;
    }

    private void setUnderlineControl(NeutralOption underlineControl) {
        this.underlineControl = underlineControl;
    }

    public float getShadowScale() {
        return shadowScale;
    }

    private void setShadowScale(float shadowScale) {
        this.shadowScale = shadowScale;
    }
}