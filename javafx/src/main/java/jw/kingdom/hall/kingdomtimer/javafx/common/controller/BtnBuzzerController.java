package jw.kingdom.hall.kingdomtimer.javafx.common.controller;


import javafx.scene.control.Button;
import jw.kingdom.hall.kingdomtimer.domain.task.TaskBean;
import jw.kingdom.hall.kingdomtimer.domain.time.TimeController;
import jw.kingdom.hall.kingdomtimer.domain.time.TimeListenerProxy;

import java.beans.PropertyChangeListener;

import static jw.kingdom.hall.kingdomtimer.javafx.utils.ButtonUtils.loadMediumImage;

/**
 * This file is part of KingdomHallTimer which is released under "no licence".
 */
public class BtnBuzzerController {

    private final TimeController timer;
    private Button button;
    private TaskBean task;
    private PropertyChangeListener listener = evt -> {
        if(evt.getPropertyName().equals(TaskBean.PropertyName.USE_BUZZER)) {
            setImageForVolumeUp((Boolean) evt.getNewValue());
        }
    };

    public BtnBuzzerController(TimeController timer, Button button) {
        this.timer = timer;
        this.button = button;
        init();
    }

    private void init() {
        setImageForCurrentCondition();
        button.setOnAction(event -> {
            if(null != task) {
                task.setUseBuzzer(!task.isUseBuzzer());
                setImageForCurrentCondition();
            }
        });
        getTimer().addListener(new TimeListenerProxy() {
            @Override
            public void onStart(TaskBean task) {
                super.onStart(task);
                loadTask(task);
                setImageForCurrentCondition();
            }

            @Override
            public void onStop() {
                super.onStop();
                loadTask(null);
                setImageForCurrentCondition();
            }
        });
    }

    private void loadTask(TaskBean task) {
        if(null != this.task) {
            this.task.removePropertyChangeListener(listener);
        }
        this.task = task;
        setImageForCurrentCondition();
        if(null != this.task) {
            this.task.addPropertyChangeListener(listener);
        }
    }

    private void setImageForCurrentCondition() {
        setImageForVolumeUp((null != task && task.isUseBuzzer()));
    }

    private void setImageForVolumeUp(boolean isVolumeUp) {
        if(isVolumeUp) {
            loadMediumImage(button, "icons/baseline_volume_up_black_48dp.png");
        } else {
            loadMediumImage(button, "icons/baseline_volume_off_black_48dp.png");
        }
    }

    private TimeController getTimer() {
        return timer;
    }
}
