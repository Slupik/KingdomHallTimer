package jw.kingdom.hall.kingdomtimer.domain;

import jw.kingdom.hall.kingdomtimer.domain.time.countdown.CountdownControllerImpl;
import jw.kingdom.hall.kingdomtimer.model.task.Task;
import jw.kingdom.hall.kingdomtimer.model.task.TaskBean;
import jw.kingdom.hall.kingdomtimer.model.time.TimeDisplay;
import jw.kingdom.hall.kingdomtimer.model.time.countdown.CountdownController;

/**
 * All rights reserved & copyright ©
 */
public class DomainTest {
    public static void main(String[] args) throws Exception {
        testCountdown();
    }

    private static void testCountdown() throws Exception {
        CountdownController controller = new CountdownControllerImpl();
        controller.addTimeDisplay(new TimeDisplay() {
            @Override
            public void onTaskChange(Task newTask) {
                System.out.println("New task: "+newTask.getName()+ "("+newTask.getSeconds()+")");
            }

            @Override
            public void display(int startTime, int time) {
                System.out.println(time);
            }
        });

        Task task = new TaskBean();
        task.setDirectDown(false);
        task.setSeconds(5);
        controller.start(task);
        Thread.sleep(3000);
        controller.addTime(5);
    }
}
