package org.kayteam.applechat.listeners;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.kayteam.applechat.AppleChat;
import org.kayteam.applechat.module.ExecuteResult;
import org.kayteam.applechat.module.Module;
import org.kayteam.applechat.module.ModuleManager;

public class AsyncPlayerChatListener implements Listener {

    private final AppleChat appleChat;

    public AsyncPlayerChatListener(AppleChat appleChat) {
        this.appleChat = appleChat;
    }

    @EventHandler(ignoreCancelled = true)
    public void onAsyncPlayerChat(AsyncPlayerChatEvent event) {
        Player sender = event.getPlayer();
        String message = event.getMessage();

        ModuleManager moduleManager = appleChat.getModuleManager();
        for (Module module:moduleManager.getModules()) {
            if (module.isEnable()) {
                ExecuteResult executeResult = module.execute(sender, message);
                if (executeResult.isCancelled()) {
                    event.setCancelled(true);
                    return;
                } else {
                    message = executeResult.getMessage();
                }
            }
        }

        if(sender.hasPermission(appleChat.getSettings().getString("permissions.admin")) || sender.hasPermission(appleChat.getSettings().getString("permissions.color"))) {
            message = ChatColor.translateAlternateColorCodes('&', message);
        }

        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            if(sender.hasPermission(appleChat.getSettings().getString("permissions.admin")) || sender.hasPermission(appleChat.getSettings().getString("permissions.placeholderapi"))) {
                message = PlaceholderAPI.setPlaceholders(sender, message);
            }
        }

        String format = appleChat.getSettings().getString("format.default");
        if (AppleChat.getChat() != null) {
            String group = AppleChat.getChat().getPrimaryGroup(sender);
            if (appleChat.getSettings().contains("format." + group)) {
                format = appleChat.getSettings().getString("format." + group);
            }
            format = format.replaceAll("%prefix%", ChatColor.translateAlternateColorCodes('&', AppleChat.getChat().getPlayerPrefix(sender)));
            format = format.replaceAll("%suffix%", ChatColor.translateAlternateColorCodes('&', AppleChat.getChat().getPlayerSuffix(sender)));
        }

        format = ChatColor.translateAlternateColorCodes('&', format);
        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) format = PlaceholderAPI.setPlaceholders(sender, format);
        format = format.replaceAll("%name%", sender.getName());
        format = format.replaceAll("%displayname%", sender.getDisplayName());
        format = format.replaceAll("%message%", message);

        event.setFormat("%2$s");
        event.setMessage(format);
    }

}