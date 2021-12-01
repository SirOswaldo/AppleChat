package org.kayteam.applechat.module;

public class ExecuteResult {

    private final boolean cancelled;
    private final String message;

    public ExecuteResult(boolean cancelled, String message) {
        this.cancelled = cancelled;
        this.message = message;
    }

    public boolean isCancelled() {
        return cancelled;
    }

    public String getMessage() {
        return message;
    }

}