package games.enchanted.eg_realms_tester.common.mixin.accessor;

import com.mojang.realmsclient.dto.RealmsNotification;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

import java.util.UUID;

@Mixin(RealmsNotification.class)
public interface RealmsNotificationAccess {
    @Invoker("<init>")
    static RealmsNotification eg_realms_tester$invokeInit(final UUID uuid, final boolean dismissable, final boolean seen, final String type) {
        throw new AssertionError("Mixin not applied");
    }
}
