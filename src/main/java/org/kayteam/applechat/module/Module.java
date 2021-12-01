package org.kayteam.applechat.module;

import org.bukkit.entity.Player;

public abstract class Module {

    private final String name;
    private final String version;
    private final String author;

    public Module(String name, String version, String author) {
        this.name = name;
        this.version = version;
        this.author = author;
    }

    public String getName() {
        return name;
    }

    public String getVersion() {
        return version;
    }

    public String getAuthor() {
        return author;
    }

    public abstract boolean isEnable();

    public abstract ExecuteResult execute(Player sender, String message);

}
