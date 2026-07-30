package games.enchanted.eg_realms_tester.common.mixin;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.mojang.realmsclient.client.RealmsClient;
import com.mojang.realmsclient.client.RealmsError;
import com.mojang.realmsclient.client.Request;
import com.mojang.realmsclient.exception.RealmsServiceException;
import games.enchanted.eg_realms_tester.common.realms.FakeRealmsClient;
import games.enchanted.eg_realms_tester.common.Logging;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Mixin;

import java.util.Set;

@Mixin(RealmsClient.class)
public class RealmsClientMixin {
    @WrapMethod(
        method = "getOrCreate(Lnet/minecraft/client/Minecraft;)Lcom/mojang/realmsclient/client/RealmsClient;"
    )
    private static RealmsClient eg_realms_tester$wrapGetOrCreate(Minecraft minecraft, Operation<RealmsClient> original) {
        return new FakeRealmsClient(minecraft);
    }

    @WrapMethod(
        method = "fetchFeatureFlags"
    )
    private Set<String> eg_realms_tester$wrapFetchFeatureFlags(Operation<Set<String>> original) {
        return Set.of();
    }

    @WrapMethod(
        method = "execute"
    )
    private String eg_realms_tester$wrapExecute(Request<?> request, Operation<String> original) throws RealmsServiceException {
        if((Object) this instanceof FakeRealmsClient) {
            Logging.info("Request: {}", request);
            throw new RealmsServiceException(new RealmsError.CustomError(418, Component.literal("418 I'm a teapot. Cannot interact with fake realms")));
        }
        return original.call(request);
    }
}
