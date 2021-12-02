package org.kayteam.applechat.commands;

import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.kayteam.applechat.AppleChat;
import org.kayteam.applechat.util.command.SimpleCommand;
import org.kayteam.applechat.util.yaml.Yaml;

import java.util.ArrayList;
import java.util.List;

public class AppleChatCommand extends SimpleCommand {

    private final AppleChat appleChat;

    public AppleChatCommand(AppleChat appleChat) {
        super("AppleChat");
        this.appleChat = appleChat;
    }

    @Override
    public void onPlayerExecute(Player sender, String[] args) {
        Yaml messages = appleChat.getMessages();
        if(sender.hasPermission(appleChat.getSettings().getString("permissions.admin"))) {
            if (args.length > 0) {
                switch (args[0].toLowerCase()) {
                    case "help":{
                        messages.sendMessage(sender, "help", new String[][]{{"%command%", "AppleChat"}});
                        break;
                    }
                    case "reload":{
                        appleChat.onReload();
                        messages.sendMessage(sender, "reload");
                        break;
                    }
                    default:{
                        messages.sendMessage(sender, "invalidArgs");
                    }
                }
            } else {
                messages.sendMessage(sender, "emptyArgs");
            }
        } else {
            messages.sendMessage(sender, "noPermission");
        }
    }

    @Override
    public List<String> onPlayerTabComplete(Player sender, String[] args) {
        List<String> result = new ArrayList<>();
        if (sender.hasPermission(appleChat.getSettings().getString("permissions.admin"))) {
            if (args.length == 1) {
                result.add("help");
                result.add("reload");
            }
        }
        return result;
    }

    @Override
    public void onConsoleExecute(ConsoleCommandSender sender, String[] args) {
        Yaml messages = appleChat.getMessages();
        if (args.length > 0) {
            switch (args[0].toLowerCase()) {
                case "help":{
                    messages.sendMessage(sender, "help", new String[][]{{"%command%", "AppleChat"}});
                    break;
                }
                case "reload":{
                    appleChat.onReload();
                    messages.sendMessage(sender, "reload");
                    break;
                }
                default:{
                    messages.sendMessage(sender, "invalidArgs");
                }
            }
        } else {
            messages.sendMessage(sender, "emptyArgs");
        }
    }

    @Override
    public List<String> onConsoleTabComplete(ConsoleCommandSender sender, String[] args) {
        List<String> result = new ArrayList<>();
        if (args.length == 1) {
            result.add("help");
            result.add("reload");
        }
        return result;
    }
}