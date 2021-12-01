package org.kayteam.applechat.module.modules;

import org.bukkit.entity.Player;
import org.kayteam.applechat.AppleChat;
import org.kayteam.applechat.module.ExecuteResult;
import org.kayteam.applechat.module.Module;
import org.kayteam.applechat.util.yaml.Yaml;

public class UpperCaseModule extends Module {

    private final AppleChat appleChat;

    public UpperCaseModule(AppleChat appleChat) {
        super("UpperCase", "1.0", "SirOswaldo");
        this.appleChat = appleChat;
    }
    @Override
    public boolean isEnable() {
        return appleChat.getSettings().getBoolean("modules.upper-case.enable");
    }

    @Override
    public ExecuteResult execute(Player sender, String message) {
        Yaml settings = appleChat.getSettings();
        String ignorePermission = settings.getString("modules.upper-case.ignore-permission");
        if (!sender.hasPermission(ignorePermission)) return new ExecuteResult(false, message.toLowerCase());
        return new ExecuteResult(false, message);
    }

}