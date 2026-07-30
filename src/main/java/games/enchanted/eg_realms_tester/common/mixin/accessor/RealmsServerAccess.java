package games.enchanted.eg_realms_tester.common.mixin.accessor;

import com.mojang.realmsclient.dto.RealmsServer;
import com.mojang.realmsclient.dto.RealmsSlot;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.List;

@Mixin(RealmsServer.class)
public interface RealmsServerAccess {
    @Accessor("slotList")
    void eg_realmes_tester$setSlotList(List<RealmsSlot> slots);
}
