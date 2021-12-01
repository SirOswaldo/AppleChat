package org.kayteam.applechat.commands;

import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.kayteam.applechat.AppleChat;
import org.kayteam.applechat.util.command.SimpleCommand;
import org.kayteam.applechat.util.yaml.Yaml;

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
                    case "version":{
                        Yaml.sendSimpleMessage(sender, "&8[&cApple&fChat&8] &fInformation");
                        Yaml.sendSimpleMessage(sender, "&c Version &f" + appleChat.getDescription().getVersion());
                        Yaml.sendSimpleMessage(sender, "&c Website &f" + appleChat.getDescription().getWebsite());
                        break;
                    }
                    default:{
                        messages.sendMessage(sender, "invalidArgs", new String[][]{{"%argument%", args[0]}});
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
                case "version":{
                    Yaml.sendSimpleMessage(sender, "&8[&cApple&fChat&8] &fInformation");
                    Yaml.sendSimpleMessage(sender, "&c Version &f" + appleChat.getDescription().getVersion());
                    Yaml.sendSimpleMessage(sender, "&c Website &f" + appleChat.getDescription().getWebsite());
                    break;
                }
                default:{
                    messages.sendMessage(sender, "invalidArgs", new String[][]{{"%argument%", args[0]}});
                }
            }
        } else {
            messages.sendMessage(sender, "emptyArgs");
        }
    }

}