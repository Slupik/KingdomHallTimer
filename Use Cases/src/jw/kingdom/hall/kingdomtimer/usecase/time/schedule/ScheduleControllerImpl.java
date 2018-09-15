package jw.kingdom.hall.kingdomtimer.usecase.time.schedule;

import jw.kingdom.hall.kingdomtimer.entity.task.Task;
import jw.kingdom.hall.kingdomtimer.usecase.time.listener.TimeListener;

import java.util.ArrayList;
import java.util.List;

/**
 * All rights reserved & copyright ©
 */
public class ScheduleControllerImpl extends ScheduleLogic {

    private final List<TimeListener> listeners = new ArrayList<>();

    @Override
    public void addListener(TimeListener listener) {
        listeners.add(listener);
    }

    @Override
    public void removeListener(TimeListener listener) {
        listeners.remove(listener);
    }

    @Override
    protected void onListChange(List<Task> schedule) {
        for(TimeListener listener:listeners) {
            listener.onScheduleChange(schedule);
        }
    }
}
