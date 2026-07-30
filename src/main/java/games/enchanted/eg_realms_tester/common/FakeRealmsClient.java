package games.enchanted.eg_realms_tester.common;

import com.mojang.realmsclient.client.RealmsClient;
import com.mojang.realmsclient.dto.*;
import com.mojang.realmsclient.exception.RealmsServiceException;
import games.enchanted.eg_realms_tester.common.mixin.accessor.BackupAccess;
import games.enchanted.eg_realms_tester.common.mixin.accessor.RealmsNotificationAccess;
import net.minecraft.client.Minecraft;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class FakeRealmsClient extends RealmsClient {
    public static final String ENCHANTED_GAMES_SITE = "https://enchanted.games";
    public static final String ENCHANTED_GAMES_USER = "Enchanted_Games";
    public static final UUID ENCHANTED_GAMES_UUID = UUID.fromString("aa495c8b-5acf-4e0c-bbdb-df4cfa643e3a");
    public static final String IOBLACKSHAW_USER = "ioblackshaw";
    public static final UUID IOBLACKSHAW_UUID = UUID.fromString("e5ff8f5a-f631-415b-871a-134a2eb899d9");

    protected final List<RealmsNotification> notifications;
    protected final List<PendingInvite> invites;

    public FakeRealmsClient(Minecraft minecraft) {
        var user = minecraft.getUser();
        super(user.getSessionId(), user.getName(), minecraft);

        List<RealmsNotification> notifications = new ArrayList<>();
        notifications.add(RealmsNotificationAccess.eg_realms_tester$invokeInit(UUID.randomUUID(), false, false, "base"));
        this.notifications = notifications;

        List<PendingInvite> invites = new ArrayList<>();
        invites.add(new PendingInvite("inv1", "Join my realm", ENCHANTED_GAMES_USER, ENCHANTED_GAMES_UUID, Instant.now()));
        invites.add(new PendingInvite("inv2", "No join MY realm", IOBLACKSHAW_USER, IOBLACKSHAW_UUID, Instant.now()));
        this.invites = invites;
    }

    @Override
    public RealmsServerList listRealms() throws RealmsServiceException {
        List<RealmsServer> servers = new ArrayList<>();

        servers.add(new RealmsServer());

        return new RealmsServerList(servers);
    }

    @Override
    public List<RealmsServer> listSnapshotEligibleRealms() throws RealmsServiceException {
        return List.of();
    }


    @Override
    public List<RealmsNotification> getNotifications() throws RealmsServiceException {
        return this.notifications;
    }

    @Override
    public void notificationsSeen(List<UUID> notificationUuids) throws RealmsServiceException {
    }

    @Override
    public void notificationsDismiss(List<UUID> notificationUuids) throws RealmsServiceException {
    }

    @Override
    public RealmsServer getOwnRealm(long realmId) throws RealmsServiceException {
        return new RealmsServer();
    }


    @Override
    public PreferredRegionsDto getPreferredRegionSelections() throws RealmsServiceException {
        return PreferredRegionsDto.empty();
    }


    @Override
    public RealmsServerPlayerLists getLiveStats() throws RealmsServiceException {
        return new RealmsServerPlayerLists(Map.of(-1L, List.of()));
    }


    @Override
    public boolean hasParentalConsent() throws RealmsServiceException {
        return true;
    }

    @Override
    public CompatibleVersionResponse clientCompatible() throws RealmsServiceException {
        return CompatibleVersionResponse.COMPATIBLE;
    }


    @Override
    public BackupList backupsFor(long realmId) throws RealmsServiceException {
        List<Backup> backups = new ArrayList<>();

        backups.add(BackupAccess.eg_realms_tester$invokeInit("backup1", Instant.now(), 1, Map.of()));

        return new BackupList(backups);
    }

    @Override
    public WorldTemplatePaginatedList fetchWorldTemplates(int page, int pageSize, RealmsServer.WorldType type) throws RealmsServiceException {
        List<WorldTemplate> templates = List.of(new WorldTemplate(
            "fake",
            "fake",
            "1.0",
            ENCHANTED_GAMES_USER,
            ENCHANTED_GAMES_SITE,
            null,
            "",
            "1",
            WorldTemplate.WorldTemplateType.MINIGAME
        ));
        return new WorldTemplatePaginatedList(templates, page, pageSize, 1);
    }

    @Override
    public Boolean putIntoMinigameMode(long realmId, String minigameId) throws RealmsServiceException {
        return false;
    }


    @Override
    public Subscription subscriptionFor(long realmId) throws RealmsServiceException {
        return new Subscription(Instant.MIN, 99999999, Subscription.SubscriptionType.NORMAL);
    }


    @Override
    public int pendingInvitesCount() throws RealmsServiceException {
        return this.invites.size();
    }

    @Override
    public PendingInvitesList pendingInvites() throws RealmsServiceException {
        return new PendingInvitesList(this.invites);
    }

    @Override
    public RealmsNews getNews() throws RealmsServiceException {
        return new RealmsNews(ENCHANTED_GAMES_SITE);
    }

    @Override
    public Boolean trialAvailable() throws RealmsServiceException {
        return true;
    }
}
