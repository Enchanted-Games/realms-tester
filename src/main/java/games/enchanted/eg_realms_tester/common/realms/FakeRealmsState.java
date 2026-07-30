package games.enchanted.eg_realms_tester.common.realms;

import com.mojang.realmsclient.dto.*;
import net.minecraft.SharedConstants;
import net.minecraft.client.Minecraft;
import net.minecraft.client.User;
import net.minecraft.world.Difficulty;
import net.minecraft.world.item.component.ResolvableProfile;
import net.minecraft.world.level.GameType;

import java.util.*;

public class FakeRealmsState {
    private static final FakeRealmsState INSTANCE = new FakeRealmsState();

    private final RealmsServer ownedRealm;
    private final List<RealmsServer> fakeRealms;

    FakeRealmsState() {
        User user = Minecraft.getInstance().getUser();

        this.ownedRealm = createFakeRealm(
            FakeRealmsClient.RECURRING_REALM,
            "Open, configurable, recurring subscription",
            user.getName(),
            user.getProfileId(),
            30,
            createPlayerInfoList(),
            RealmsServer.State.OPEN,
            RealmsServer.Compatibility.COMPATIBLE
        );

        List<RealmsServer> fakeRealms = new ArrayList<>();
        fakeRealms.add(this.ownedRealm);

        fakeRealms.add(createFakeRealm(
            FakeRealmsClient.EXPIRING_SOON_REALM,
            "Expires soon",
            user.getName(),
            user.getProfileId(),
            1,
            createPlayerInfoList(),
            RealmsServer.State.OPEN,
            RealmsServer.Compatibility.COMPATIBLE
        ));

        fakeRealms.add(createFakeRealm(
            FakeRealmsClient.NORMAL_REALM,
            "Uninitialized",
            user.getName(),
            user.getProfileId(),
            30,
            createPlayerInfoList(),
            RealmsServer.State.UNINITIALIZED,
            RealmsServer.Compatibility.COMPATIBLE
        ));

        fakeRealms.add(createFakeRealm(
            FakeRealmsClient.CLOSED_REALM,
            "Closed",
            user.getName(),
            user.getProfileId(),
            30,
            createPlayerInfoList(),
            RealmsServer.State.CLOSED,
            RealmsServer.Compatibility.COMPATIBLE
        ));

        fakeRealms.add(createFakeRealm(
            FakeRealmsClient.EXPIRED_REALM,
            "Expired",
            user.getName(),
            user.getProfileId(),
            -1,
            createPlayerInfoList(),
            RealmsServer.State.OPEN,
            RealmsServer.Compatibility.COMPATIBLE
        ));

        this.fakeRealms = fakeRealms;
    }

    public static FakeRealmsState instance() {
        return INSTANCE;
    }

    public List<RealmsServer> fakeRealms() {
        return this.fakeRealms;
    }

    public RealmsServer ownedRealm() {
        return this.ownedRealm;
    }

    public RealmsServerPlayerLists liveStats() {
        Map<Long, List<ResolvableProfile>> playersPerRealm = new HashMap<>();

        for (RealmsServer realm : this.fakeRealms) {
            List<ResolvableProfile> profiles = new ArrayList<>();

            for (PlayerInfo player : realm.players) {
                profiles.add(ResolvableProfile.createUnresolved(player.uuid));
            }

            playersPerRealm.put(realm.id, profiles);
        }

        return new RealmsServerPlayerLists(playersPerRealm);
    }

    public static RealmsServer createFakeRealm(
        long id,
        String name,
        String ownerName,
        UUID ownerUUID,
        int daysLeft,
        List<PlayerInfo> playerInfo,
        RealmsServer.State state,
        RealmsServer.Compatibility compatibility
    ) {
        RealmsServer server = new RealmsServer();
        server.id = id;
        server.remoteSubscriptionId = "none";
        server.setName(name);
        server.state = state;
        server.owner = ownerName;
        server.ownerUUID = ownerUUID;
        server.players = playerInfo;
        server.expired = daysLeft <= 0;
        server.expiredTrial = false;
        server.daysLeft = daysLeft;
        server.worldType = RealmsServer.WorldType.NORMAL;
        server.isHardcore = false;
        server.activeSlot = 1;
        server.slots = slots();
        server.activeVersion = SharedConstants.getCurrentVersion().name();
        server.compatibility = compatibility;

        RealmsServer.finalize(server);

        return server;
    }

    public static List<PlayerInfo> createPlayerInfoList() {
        List<PlayerInfo> players = new ArrayList<>();
        User user = Minecraft.getInstance().getUser();
        players.add(new PlayerInfo(user.getName(), user.getProfileId(), true, true, true));
        players.add(new PlayerInfo(FakeRealmsClient.ENCHANTED_GAMES_USER, FakeRealmsClient.ENCHANTED_GAMES_UUID, false, true, true));
        players.add(new PlayerInfo(FakeRealmsClient.IOBLACKSHAW_USER, FakeRealmsClient.IOBLACKSHAW_UUID, false, false, false));
        return players;
    }

    private static Map<Integer, RealmsSlot> slots() {
        Map<Integer, RealmsSlot> slots = new HashMap<>();
        slots.put(1, new RealmsSlot(
            1,
            RealmsWorldOptions.createDefaultsWith(GameType.CREATIVE, Difficulty.NORMAL, false, SharedConstants.getCurrentVersion().name(), "Creative"),
            List.of(RealmsSetting.hardcoreSetting(false))
        ));
        slots.put(2, new RealmsSlot(2, RealmsWorldOptions.createDefaults(), List.of()));
        slots.put(3, new RealmsSlot(3, RealmsWorldOptions.createDefaults(), List.of()));
        slots.put(4, new RealmsSlot(4, RealmsWorldOptions.createDefaults(), List.of()));
        return slots;
    }
}
