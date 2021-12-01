package org.kayteam.applechat.module.modules;

import org.bukkit.entity.Player;
import org.kayteam.applechat.AppleChat;
import org.kayteam.applechat.module.ExecuteResult;
import org.kayteam.applechat.module.Module;
import org.kayteam.applechat.util.yaml.Yaml;

import java.util.HashMap;
import java.util.UUID;

public class FloodModule extends Module {

    private final AppleChat appleChat;
    private final HashMap<UUID, Long> uuids = new HashMap<>();

    public FloodModule(AppleChat appleChat) {
        super("Flood", "1.0", "SirOswaldo");
        this.appleChat = appleChat;
    }

    @Override
    public boolean isEnable() {
        return appleChat.getSettings().getBoolean("modules.flood.enable");
    }

    @Override
    public ExecuteResult execute(Player sender, String message) {
        Yaml settings = appleChat.getSettings();
        String ignorePermission = settings.getString("modules.flood.ignore-permission");
        if (!sender.hasPermission(ignorePermission)) {
            UUID uuid = sender.getUniqueId();
            if (uuids.containsKey(uuid)) {
                long last = uuids.get(uuid);
                long current = System.currentTimeMillis();
                long countdown = settings.getInt("modules.flood.countdown");
                long transcurre = current - last;
                if (transcurre < (countdown * 1000)) {
                    settings.sendMessage(sender, "modules.flood.notify-message", new String[][]{{"%seconds%", (countdown - (transcurre / 1000)) + ""}});
                    return new ExecuteResult(true, message);
                } else {
                    uuids.put(uuid, System.currentTimeMillis());
                }
            } else {
                uuids.put(uuid, System.currentTimeMillis());
            }
        }
        return new ExecuteResult(false, message);
    }

}