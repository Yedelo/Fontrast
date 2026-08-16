package at.yedel.fontrast;



import at.yedel.fontrast.config.FontrastConfig;
/*? if fabric {*/
import net.fabricmc.api.ClientModInitializer;
/*?} else if neoforge {*/
/*import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
    *//*?}*/



// Maud
/*? if neoforge */ //@Mod("fontrast")
public class Fontrast /*? if fabric {*/implements ClientModInitializer/*?}*/ {
	/*? if fabric {*/
	@Override
	public void onInitializeClient() {
		FontrastConfig.init();
	}
	/*?} elif neoforge {*/
	/*public Fontrast(ModContainer container) {
		FontrastConfig.init();
		container.registerExtensionPoint(IConfigScreenFactory.class, (tainer, parent) -> FontrastConfig.getScreen(parent));
	}
	*//*?}*/
}