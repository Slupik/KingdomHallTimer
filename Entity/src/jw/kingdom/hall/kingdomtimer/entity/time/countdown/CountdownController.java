package jw.kingdom.hall.kingdomtimer.entity.time.countdown;

import jw.kingdom.hall.kingdomtimer.entity.task.Task;

/**
 * All rights reserved & copyright ©
 */
public interface CountdownController {

    void start(Task task);
    void stop();
    void pause();
    void resume();
    CountdownState getState();
    Task getActualTask();

    int getAddedTime();
    void addTime(int time);
    void subtractTime(int time);

    int getTime();
    void enforceTime(int time);

    void addListener(Listener listener);
    void removeListener(Listener listener);

    void addTimeDisplay(TimeDisplay timeDisplay);
    void removeTimeDisplay(TimeDisplay timeDisplay);

    interface Listener {
        void onTaskStart(Task task);
        void onTimeChange(int time);
        void onTimeOut();
        void onStateChanged(CountdownState last, CountdownState now);

        void onAddedTimeChange(int addedSeconds);
        void onEnforceTime(int enforcedTime);
    }
}