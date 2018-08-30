package jw.kingdom.hall.kingdomtimer.domain.model;

import javafx.beans.property.*;
import jw.kingdom.hall.kingdomtimer.javafx.custom.TimeField;
import jw.kingdom.hall.kingdomtimer.domain.utils.Randomizer;

/**
 * All rights reserved & copyright ©
 */
public class MeetingTask {
    public final String ID = Randomizer.randomStandardString(16);
    private BooleanProperty useBuzzer = new SimpleBooleanProperty(false);
    private StringProperty name = new SimpleStringProperty("???");
    private StringProperty formattedTime = new SimpleStringProperty("00:00:00");
    private BooleanProperty countdownDown = new SimpleBooleanProperty(true);
    private ObjectProperty<Type> type = new SimpleObjectProperty<>(Type.UNKNOWN);
    private TimeField tfTime = new TimeField();

    public TimeField getTfTime() {
        return tfTime;
    }

    public void setTfTime(TimeField tfTime) {
        this.tfTime = tfTime;
    }

    public BooleanProperty useBuzzerProperty() {
        return useBuzzer;
    }

    public boolean isUseBuzzer() {
        return useBuzzer.getValue();
    }

    public void setUseBuzzer(boolean useBuzzer) {
        this.useBuzzer.setValue(useBuzzer);
    }

    public StringProperty nameProperty() {
        return this.name;
    }

    public String getName() {
        return name.getValue();
    }

    public void setName(String name) {
        this.name.setValue(name);
    }

    public int getTimeInSeconds() {
        return tfTime.getAllSeconds();
    }

    public void setTimeInSeconds(int timeInSeconds) {
        tfTime.setSeconds(timeInSeconds);
    }

    public StringProperty formattedTimeProperty(){
        return formattedTime;
    }

    public BooleanProperty countdownProperty() {
        return countdownDown;
    }

    public boolean isCountdownDown() {
        return countdownDown.getValue();
    }

    public void setCountdownDown(boolean countdownDown) {
        this.countdownDown.setValue(countdownDown);
    }

    public Type getType() {
        return type.get();
    }

    public ObjectProperty<Type> typeProperty() {
        return type;
    }

    public void setType(Type type) {
        this.type.set(type);
    }

    public enum Type {
        UNKNOWN,
        NONE,
        TREASURES,
        MINISTRY,
        LIVING,
        WATCHTOWER,
        LECTURE,
        OVERSEER,
        OTHER
    }
}
