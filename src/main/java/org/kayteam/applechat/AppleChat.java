package org.kayteam.applechat;

import net.milkbowl.vault.chat.Chat;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import org.kayteam.applechat.commands.AppleChatCommand;
import org.kayteam.applechat.listeners.AsyncPlayerChatListener;
import org.kayteam.applechat.module.ModuleManager;
import org.kayteam.applechat.module.modules.FloodModule;
import org.kayteam.applechat.module.modules.UpperCaseModule;
import org.kayteam.applechat.module.modules.WordsFilterModule;
import org.kayteam.applechat.util.BrandSender;
import org.kayteam.applechat.util.bStats.Metrics;
import org.kayteam.applechat.util.yaml.Yaml;

public final class AppleChat extends JavaPlugin {

    private final Yaml settings = new Yaml(this, "settings");
    private final Yaml messages = new Yaml(this, "messages");

    private final ModuleManager moduleManager = new ModuleManager();

    private static Chat chat = null;

    private static API API;
    public static API getAPI() {
        return API;
    }

    @Override
    public void onEnable() {
        registerFiles();
        registerCommands();
        registerListeners();
        registerModules();
        setupChat();
        API = new API(this);
        bStats();
        BrandSender.sendBrandMessage(this, "&a&lEnabled");
    }

    @Override
    public void onDisable() {
        BrandSender.sendBrandMessage(this, "&a&lDisabled");
    }

    public void onReload() {
        settings.reloadFileConfiguration();
        messages.reloadFileConfiguration();
    }

    private void registerFiles() {
        settings.registerFileConfiguration();
        messages.registerFileConfiguration();
    }

    private void registerCommands() {
        new AppleChatCommand(this).registerCommand(this);
    }

    private void registerListeners() {
        getServer().getPluginManager().registerEvents(new AsyncPlayerChatListener(this), this);
    }

    private void registerModules() {
        moduleManager.addModule(new UpperCaseModule(this));
        moduleManager.addModule(new WordsFilterModule(this));
        moduleManager.addModule(new FloodModule(this));
    }

    public Yaml getSettings() {
        return settings;
    }

    public Yaml getMessages() {
        return messages;
    }

    public ModuleManager getModuleManager() {
        return moduleManager;
    }

    private void bStats() {
        int pluginId = 13461;
        Metrics metrics = new Metrics(this, pluginId);
    }

    private boolean setupChat() {
        RegisteredServiceProvider<Chat> rsp = getServer().getServicesManager().getRegistration(Chat.class);
        chat = rsp.getProvider();
        return chat != null;
    }

    public static Chat getChat() {
        return chat;
    }

}