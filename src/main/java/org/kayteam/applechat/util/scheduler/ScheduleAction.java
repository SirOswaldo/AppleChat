package org.kayteam.applechat.util.scheduler;

import org.bukkit.scheduler.BukkitTask;

public interface ScheduleAction {

    /**
     * This action can be cancel task if this return true
     * @return Cancel task if true
     */
    public boolean action(BukkitTask bukkitTask);

}