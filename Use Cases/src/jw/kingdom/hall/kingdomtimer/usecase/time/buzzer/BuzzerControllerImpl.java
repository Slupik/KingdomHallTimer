package jw.kingdom.hall.kingdomtimer.usecase.time.buzzer;

import jw.kingdom.hall.kingdomtimer.entity.task.Task;
import jw.kingdom.hall.kingdomtimer.entity.time.buzzer.BuzzerController;
import jw.kingdom.hall.kingdomtimer.entity.time.buzzer.BuzzerPlayer;
import jw.kingdom.hall.kingdomtimer.entity.time.countdown.CountdownController;
import jw.kingdom.hall.kingdomtimer.entity.time.countdown.CountdownListener;

/**
 * All rights reserved & copyright ©
 */
public class BuzzerControllerImpl implements BuzzerController {
    private final BuzzerPlayer player;
    private final CountdownController countdown;

    public BuzzerControllerImpl(BuzzerPlayer player, CountdownController countdown){
        this.player = player;
        this.countdown = countdown;
        init();
    }

    private void init() {
        countdown.addListener(new CountdownListener() {
            private Task task;

            @Override
            public void onTaskStart(Task task) {
                super.onTaskStart(task);
                this.task = task;
            }

            @Override
            public void onTimeChange(int time) {
                super.onTimeChange(time);
                if(null != task && task.isUseBuzzer()) {
                    if(time <= 0 && (Math.abs(time)%10)==0) {
                        player.play();
                    }
                }
            }
        });
    }
}
