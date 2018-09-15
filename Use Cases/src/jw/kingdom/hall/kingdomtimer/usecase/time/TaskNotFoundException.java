package jw.kingdom.hall.kingdomtimer.usecase.time;

/**
 * All rights reserved & copyright ©
 */
public class TaskNotFoundException extends Throwable {
    public TaskNotFoundException(String id) {
        super("Task with id = "+id+ "hasn't been found");
    }
}