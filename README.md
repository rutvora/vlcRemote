A basic java controller for vlc (via telnet)
Original source: https://forum.videolan.org/viewtopic.php?t=85347

Initial setup:
1. Open VLC
2. Tools -> Preferences -> Show Settings (All)* -> Interface -> Main Interfaces
* Shown in the bottom left corner
3. Select "telnet" checkbox
4. Expand the "Main Interfaces" and go to "Lua"
5. Under "Lua Telnet"
    a. Host: "localhost" (without quotes)
    b. Port: 4444
6. Save and restart VLC
7. Run this code and you can control vlc via the swing interface


TODO:
1. Connect this to an MQTT Broker/XMPP server to control VLC remotely via android (or iOS)
More ideas are welcome
