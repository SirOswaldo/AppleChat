package org.kayteam.applechat.util.updatechecker;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public class UpdateChecker {

    private final JavaPlugin javaPlugin;

    private int resourceId;
    private URL resourceURL;
    private String currentVersionString;
    private String latestVersionString;
    private UpdateCheckResult updateCheckResult;

    public UpdateChecker(JavaPlugin javaPlugin, int resourceId) {
        this.javaPlugin = javaPlugin;
        try {
            this.resourceId = resourceId;
            this.resourceURL = new URL("https://api.spigotmc.org/legacy/update.php?resource=" + resourceId);
        } catch (Exception exception) {
            return;
        }

        currentVersionString = javaPlugin.getDescription().getVersion();
        latestVersionString = getLatestVersion();

        if (latestVersionString == null) {
            updateCheckResult = UpdateCheckResult.NO_RESULT;
            return;
        }

        int currentVersion = Integer.parseInt(currentVersionString.replace("v", "").replace(".", ""));
        int latestVersion = Integer.parseInt(getLatestVersion().replace("v", "").replace(".", ""));

        if (currentVersion < latestVersion) updateCheckResult = UpdateCheckResult.OUT_DATED;
        else if (currentVersion == latestVersion) updateCheckResult = UpdateCheckResult.UP_TO_DATE;
        else updateCheckResult = UpdateCheckResult.UNRELEASED;
    }

    public int getResourceId() {
        return resourceId;
    }

    public String getResourceURL() {
        return "https://www.spigotmc.org/resources/" + resourceId;
    }

    public String getCurrentVersionString() {
        return currentVersionString;
    }

    public String getLatestVersionString() {
        return latestVersionString;
    }

    public UpdateCheckResult getUpdateCheckResult() {
        return updateCheckResult;
    }

    public String getLatestVersion() {
        try {
            URLConnection urlConnection = resourceURL.openConnection();
            return new BufferedReader(new InputStreamReader(urlConnection.getInputStream())).readLine();
        } catch (Exception exception) {
            return null;
        }
    }

    public void sendOutDatedMessage(CommandSender commandSender) {
        List<String> message = new ArrayList<>();
        message.add("");
        message.add("&f>>");
        message.add("&f>> &6The plugin " + javaPlugin.getDescription().getName() + " is outdated.");
        message.add("&f>> &6Current Version &f" + javaPlugin.getDescription().getVersion());
        message.add("&f>>");
        message.add("&f>> &6Latest Version &f" + latestVersionString);
        message.add("&f>> &6Download latest version here");
        message.add("&f>> &f" + getResourceURL());
        message.add("&f>>");
        message.add("");
        for(String line : message){
            commandSender.sendMessage(ChatColor.translateAlternateColorCodes('&', line));
        }
    }

}