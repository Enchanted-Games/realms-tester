# Realms Tester

A utility for resource pack or mod developers which shows fake realms in the realms screen. This makes it easier to test and develop resource packs on the realms screen without needing to purchase a realm. Additionally, it displays fake realm invitations as well as the notification icon on the main menu button.

**Note:** This mod prevents the game from talking to the actual realms API. This means any realms or invitations you may have will not be visible with the mod installed. If you wish to see your real realms again simply uninstall the mod.

## How it works

The mod wraps around the RealmsClient class, which is the class responsible for communicating with the realms api. The mod prevents any requests to the realms api and returns fake realms data, making it look like you have realms even if you don't.

This mod is _not_ re-creation of the realms screen, it is exactly 1:1 with the vanilla realms screen since it just provides fake realm data for the game to use as it would for actual realms.

## License
<p xmlns:cc="http://creativecommons.org/ns#" >Realms Tester by <a rel="cc:attributionURL dct:creator" property="cc:attributionName" href="https://enchanted.games">ioblackshaw (a.k.a. Enchanted_Games)</a> is licensed under <a href="http://creativecommons.org/licenses/by-nc/4.0/?ref=chooser-v1" target="_blank" rel="license noopener noreferrer" style="display:inline-block;">CC BY-NC 4.0<img style="height:22px!important;margin-left:3px;vertical-align:text-bottom;" src="https://mirrors.creativecommons.org/presskit/icons/cc.svg?ref=chooser-v1"><img style="height:22px!important;margin-left:3px;vertical-align:text-bottom;" src="https://mirrors.creativecommons.org/presskit/icons/by.svg?ref=chooser-v1"><img style="height:22px!important;margin-left:3px;vertical-align:text-bottom;" src="https://mirrors.creativecommons.org/presskit/icons/nc.svg?ref=chooser-v1"></a></p> 
Video content creators may monetise videos including this work provided the license is followed.
