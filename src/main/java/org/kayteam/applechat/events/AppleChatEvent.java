package org.kayteam.applechat.events;

import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class AppleChatEvent extends Event implements Cancellable {



    private static HandlerList handlerList = new HandlerList();
    @Override
    public HandlerList getHandlers() {
        return handlerList;
    }
    public static HandlerList getHandlerList() {
        return handlerList;
    }


    private boolean cancel = false;
    @Override
    public boolean isCancelled() {
        return cancel;
    }
    @Override
    public void setCancelled(boolean cancel) {
        cancel = cancel;
    }

}