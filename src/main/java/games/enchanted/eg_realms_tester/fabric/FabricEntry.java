//? if fabric {
package games.enchanted.eg_realms_tester.fabric;

import games.enchanted.eg_realms_tester.common.ModEntry;
import net.fabricmc.api.ModInitializer;

public class FabricEntry implements ModInitializer {
    @Override
    public void onInitialize() {
        ModEntry.init();
    }
}
//?}