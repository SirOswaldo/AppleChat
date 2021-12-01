package org.kayteam.applechat;

import org.kayteam.applechat.module.Module;

public class API {

    private final AppleChat appleChat;

    public API(AppleChat appleChat) {
        this.appleChat = appleChat;
    }

    public boolean registerModule(Module module) {
        return appleChat.getModuleManager().addModule(module);
    }

    public boolean unregisterModule(String name) {
        return appleChat.getModuleManager().removeModule(name);
    }

    public boolean unregisterModule(Module module) {
        return appleChat.getModuleManager().removeModule(module);
    }

}