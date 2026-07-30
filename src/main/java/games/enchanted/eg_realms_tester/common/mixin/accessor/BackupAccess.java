package games.enchanted.eg_realms_tester.common.mixin.accessor;

import com.mojang.realmsclient.dto.Backup;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

import java.time.Instant;
import java.util.Map;

@Mixin(Backup.class)
public interface BackupAccess {
    @Invoker("<init>")
    static Backup eg_realms_tester$invokeInit(final String backupId, final Instant lastModified, final long size, final Map<String, String> metadata) {
        throw new AssertionError("Mixin not applied");
    }
}
