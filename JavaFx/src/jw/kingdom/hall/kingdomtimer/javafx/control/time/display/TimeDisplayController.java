package jw.kingdom.hall.kingdomtimer.javafx.control.time.display;

import javafx.scene.control.Label;
import javafx.scene.paint.Paint;
import jw.kingdom.hall.kingdomtimer.javafx.control.gleam.GleammingTimeDisplay;
import jw.kingdom.hall.kingdomtimer.javafx.utils.PlatformUtils;
import jw.kingdom.hall.kingdomtimer.usecase.task.pojo.TaskPOJO;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import static jw.kingdom.hall.kingdomtimer.javafx.control.time.display.TextFormatter.secondsToText;

/**
 * This file is part of KingdomHallTimer which is released under "no licence".
 */
public class TimeDisplayController implements GleammingTimeDisplay<TaskPOJO> {
    private final List<Listener> listeners = new ArrayList<>();
    private Label text;
    private boolean isLightBackground = true;
    private int lastColorCode = -1;
    private int lastTextSize = -1;

    public TimeDisplayController(Label text) {
        this.text = text;
        setTime(0);
    }

    @Override
    public void setLightBackground(boolean lightBackground) {
        isLightBackground = lightBackground;
        setTextColor(TimerColor.getColor(lastColorCode, isLightBackground));
    }

    @Override
    public void display(int startTime, int timeToDisplay, int absoluteTimeLeft) {
        setTime(timeToDisplay);
        setColorCode(TimerColor.getColorCode(startTime, absoluteTimeLeft));
    }

    @Override
    public void display(int time) {
        setTime(time);
    }

    @Override
    public void onTimeOut() {

    }

    @Override
    public void setTask(TaskPOJO task) {

    }

    @Override
    public void reset() {
        setTime(0);
        setColorCode(TimerColor.getDefaultColorCode());
    }

    @Override
    public Type getType() {
        return null;
    }

    @Override
    public void resetColorToLast(){
        setTextColor(TimerColor.getColor(lastColorCode, isLightBackground));
    }

    private void setColorCode(int paintCode) {
        if(lastColorCode!=paintCode) {
            setTextColor(TimerColor.getColor(paintCode, isLightBackground));
            lastColorCode = paintCode;
        }
    }

    @Override
    public void setTextColor(Paint fill) {
        text.setTextFill(fill);
    }

    private void setTime(int seconds){
        final String text = secondsToText(seconds);
        PlatformUtils.runOnUiThread(()-> {
            setText(text);
            notifyTextSizeChanged(text.length());
        });
    }

    private void setText(String text) {
        this.text.setText(text);
    }

    private void notifyTextSizeChanged(int length) {
        if(lastTextSize!=length) {
            lastTextSize = length;
            for(Listener listener:listeners) {
                listener.onTextSizeChanged();
            }
        }
    }

    public void addListener(Listener listener) {
        listeners.add(listener);
    }
    public void removeListener(Listener listener) {
        listeners.remove(listener);
    }

    public interface Listener {
        void onTextSizeChanged();
    }
}
