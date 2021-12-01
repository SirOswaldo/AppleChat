package org.kayteam.applechat.module.modules;

import org.bukkit.entity.Player;
import org.kayteam.applechat.AppleChat;
import org.kayteam.applechat.module.ExecuteResult;
import org.kayteam.applechat.module.Module;
import org.kayteam.applechat.util.yaml.Yaml;

import java.util.List;

public class WordsFilterModule extends Module {

    private final AppleChat appleChat;

    public WordsFilterModule(AppleChat appleChat) {
        super("WordsFilter", "1.0", "SirOswaldo");
        this.appleChat = appleChat;
    }

    @Override
    public boolean isEnable() {
        return appleChat.getSettings().getBoolean("modules.words-filter.enable");
    }

    @Override
    public ExecuteResult execute(Player sender, String message) {
        Yaml settings = appleChat.getSettings();
        String ignorePermission = settings.getString("modules.words-filter.ignore-permission");
        if (!sender.hasPermission(ignorePermission)) {
            List<String> words = settings.getStringList("modules.words-filter.words");
            String filterText = settings.getString("modules.words-filter.filter-text");
            for (String word:words) {
                if (message.contains(word)) {
                    message = message.replaceAll(word, filterText);
                }
            }
        }
        return new ExecuteResult(false, message);
    }
}
