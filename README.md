# Realms Tester

A utility for resource pack developers which shows fake realms in the realms screen. This makes it easier to test and develop resource packs on the realms screen without needing to purchase a realm.

**Note:** This mod prevents the game from talking to the actual realms API. This means any realms or invitations you may have will not be visible with the mod installed. If you wish to see your real realms again simply uninstall the mod.

## How it works

The mod wraps around the RealmsClient class, which is the class responsible for communicating with the realms api. The mod prevents any requests to the realms api and returns fake realms data, making it look like you have realms even if you don't.
