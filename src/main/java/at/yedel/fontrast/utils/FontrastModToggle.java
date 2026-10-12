/*? if fabric {*/
package at.yedel.fontrast.utils;



import at.yedel.fontrast.config.FontrastConfig;
import org.polyfrost.oneconfig.api.toggle.v1.ModToggle;



public class FontrastModToggle implements ModToggle {
    @Override
    public boolean isEnabled() {
        return FontrastConfig.getInstance().enabled;
    }

    @Override
    public void setEnabled(boolean enabled) {
        FontrastConfig.getInstance().enabled = enabled;
        FontrastConfig.HANDLER.save();
    }
}/*?}*/