package jw.kingdom.hall.kingdomtimer.data.schedule;

import jw.kingdom.hall.kingdomtimer.domain.config.AppConfig;
import jw.kingdom.hall.kingdomtimer.domain.task.TaskBean;
import jw.kingdom.hall.kingdomtimer.domain.task.provider.TasksProvider;

import java.util.List;

/**
 * All rights reserved & copyright ©
 */
public class TasksFetcher implements TasksProvider {

    private final AppConfig config;

    public TasksFetcher(AppConfig config) {
        this.config = config;
    }

    @Override
    public void getMeetingTasks(boolean isCircuit, Callback callback) {
        getMeetingTasks(isCircuit, System.currentTimeMillis(), callback);
    }

    @Override
    public void getMeetingTasks(boolean isCircuit, long forTimeInMillis, Callback callback) {
        TaskListCreator.Callback callbackForFactory = new TaskListCreator.Callback() {
            @Override
            public void onDataReceive(List<TaskBean> list) {
                callback.onDownload(list);
            }

            @Override
            public void onConnectionError() {
                callback.onConnectionError();
            }
        };
        callback.onStart();
        if(TaskProviderUtils.isWeekend(forTimeInMillis)) {
            TaskListCreator.getWeekendTasks(isCircuit, callbackForFactory);
        } else {
            TaskListCreator.getWeekTasks(config, isCircuit, callbackForFactory);
        }
    }
}
