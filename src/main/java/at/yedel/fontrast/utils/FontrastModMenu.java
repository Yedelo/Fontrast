/*? if fabric {*/

package at.yedel.fontrast.utils;



import at.yedel.fontrast.config.FontrastConfig;
import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;



public class FontrastModMenu implements ModMenuApi {
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return FontrastConfig::getScreen;
    }
}

/*?}*/
