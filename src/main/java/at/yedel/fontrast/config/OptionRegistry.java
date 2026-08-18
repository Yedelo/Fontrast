package at.yedel.fontrast.config;



import dev.isxander.yacl3.api.Option;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



/**
 * this has to be separate because all instance fields get serialized even though i do not want that to happen
 */
public class OptionRegistry {
    private static final Map<CustomColors, List<Option<Color>>> registry = new HashMap<>();

    public static void registerOption(CustomColors colors, Option<Color> option) {
        registry.computeIfAbsent(colors, k -> new ArrayList<>());
        registry.get(colors).add(option);
    }

    public static List<Option<Color>> getOptions(CustomColors colors) {
        return registry.get(colors);
    }
}
