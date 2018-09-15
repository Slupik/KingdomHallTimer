package jw.kingdom.hall.kingdomtimer.usecase.time.controller;

import jw.kingdom.hall.kingdomtimer.entity.task.Task;
import jw.kingdom.hall.kingdomtimer.usecase.mapper.MapperPojoToTask;
import jw.kingdom.hall.kingdomtimer.usecase.task.pojo.TaskPOJO;
import jw.kingdom.hall.kingdomtimer.usecase.time.countdown.CountdownController;
import jw.kingdom.hall.kingdomtimer.usecase.time.countdown.CountdownControllerImpl;
import jw.kingdom.hall.kingdomtimer.usecase.time.display.TimeDisplay;
import jw.kingdom.hall.kingdomtimer.usecase.time.listener.TimeListener;
import jw.kingdom.hall.kingdomtimer.usecase.time.schedule.ScheduleController;
import jw.kingdom.hall.kingdomtimer.usecase.time.schedule.ScheduleControllerImpl;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * All rights reserved & copyright ©
 */
public class TimeControllerImpl implements TimeController, TimeListener<Task> {

    private final List<TimeDisplay> displays = new ArrayList<>();
    private final List<TimeListener> listeners = new ArrayList<>();
    private final ScheduleController schedule;
    private final CountdownController countdown;
    private boolean meetingStarted = false;

    public TimeControllerImpl() {
        this(new ScheduleControllerImpl(), new CountdownControllerImpl());
    }

    public TimeControllerImpl(ScheduleController schedule, CountdownController countdown){
        this.schedule = schedule;
        this.countdown = countdown;
        countdown.addListener(this);
    }

    @Override
    public void startTask(TaskPOJO task) {
        countdown.start(new MapperPojoToTask().map(task));
    }

    @Override
    public void pause() {
        countdown.pause();
    }

    @Override
    public void resume() {
        countdown.resume();
    }

    @Override
    public void stop() {
        countdown.stop();
    }

    @Override
    public void startNext() {
        Task task = schedule.bringOutFirstTask();
        countdown.start(task);
    }

    @Override
    public Task getActualTask() {
        return countdown.getTask();
    }

    @Override
    public void addTime(int time) {
        countdown.addTime(time);
    }

    @Override
    public int getAddedTime() {
        return countdown.getAddedTime();
    }

    @Override
    public int getTime() {
        return countdown.getTime();
    }

    @Override
    public void enforceTime(int time) {
        countdown.enforceTime(time);
    }

    @Override
    public void moveTask(String id, int index) {
        schedule.moveTask(id, index);
        for(TimeListener listener:listeners) {
            listener.onScheduleChange(getSchedule());
        }
    }

    @Override
    public void addTask(TaskPOJO task) {
        schedule.addTask(new MapperPojoToTask().map(task));
    }

    @Override
    public void remove(String taskId) {
        schedule.removeTask(taskId);
    }

    @Override
    public void setSchedule(List<TaskPOJO> list) {
        schedule.setTasks(new MapperPojoToTask().map(list));
    }

    @Override
    public List<Task> getSchedule() {
        return schedule.getTasks();
    }

    @Override
    public void addDisplay(TimeDisplay display) {
        countdown.addDisplay(display);
        displays.add(display);
    }

    @Override
    public void removeDisplay(TimeDisplay display) {
        countdown.removeDisplay(display);
        displays.remove(display);
    }

    @Override
    public void addListener(TimeListener listener) {
        listeners.add(listener);
        countdown.addListener(listener);
    }

    @Override
    public void removeListener(TimeListener listener) {
        listeners.remove(listener);
        countdown.removeListener(listener);
    }

    @Override
    public void onStart(Task task) {
        if(!meetingStarted) {
            meetingStarted = true;
            for(TimeListener listener:listeners) {
                listener.onMeetingStart();
            }
        }
        for(TimeDisplay display:displays) {
            display.setTask(task);
        }
        for(TimeListener listener:listeners) {
            listener.onStart(task);
        }
    }

    @Override
    public void onStop() {
        if(getSchedule().size()==0) {
            meetingStarted = false;
            for(TimeListener listener:listeners) {
                listener.onMeetingEnd();
            }
        }
    }

    @Override
    public void onPause() {}

    @Override
    public void onResume() {}

    @Override
    public void onTimeEnforce(int enforced) {}

    @Override
    public void onTimeAdded(int totalAdded, int added) {}

    @Override
    public void onMeetingStart() {}

    @Override
    public void onMeetingEnd() {}

    @Override
    public void onScheduleChange(List<Task> newList) {}

    @Override
    public Type getType() {
        return Task.class;
    }
}
