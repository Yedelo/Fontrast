package at.yedel.fontrast.config;



import dev.isxander.yacl3.api.*;
import dev.isxander.yacl3.config.v2.api.ConfigClassHandler;
import dev.isxander.yacl3.config.v2.api.SerialEntry;
import dev.isxander.yacl3.config.v2.api.serializer.GsonConfigSerializerBuilder;
/*? if fabric {*/
import net.fabricmc.loader.api.FabricLoader;
/*?} elif neoforge {*/
/*import net.neoforged.fml.loading.FMLPaths;
*//*?}*/
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;

import java.nio.file.Path;
import java.util.function.Consumer;



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


    public static Screen getScreen(Screen parent) {
        return YetAnotherConfigLib.create(HANDLER, (defaults, config, builder) -> {
                builder.title(Component.literal("Fontrast Config"));

                return builder;
            }
        ).generateScreen(parent);
    }
}