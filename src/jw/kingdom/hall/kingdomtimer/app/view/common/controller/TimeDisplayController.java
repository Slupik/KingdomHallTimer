package jw.kingdom.hall.kingdomtimer.app.view.common.controller;

import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.paint.Paint;
import jw.kingdom.hall.kingdomtimer.domain.countdown.TimerColor;
import jw.kingdom.hall.kingdomtimer.domain.utils.Randomizer;

/**
 * All rights reserved & copyright ©
 */
public class TimeDisplayController {
    private String id = Randomizer.randomStandardString(10);

    private Label text;
    private boolean isLightBackground = true;
    private int lastColorCode = -1;

    public TimeDisplayController(Label text) {
        this.text = text;
    }

    public void setColorCode(int paintCode) {
        if(lastColorCode!=paintCode) {
            setColor(TimerColor.getColor(paintCode, isLightBackground));
            lastColorCode = paintCode;
        }
    }

    private void setColor(Paint paint) {
        text.setTextFill(paint);
    }

    public void setTime(int seconds){
        String data = secondsToText(seconds);
        if(Platform.isFxApplicationThread()) {
            setText(data);
        } else {
            Platform.runLater(()-> setText(data));
        }
    }

    private void setText(String text) {
        this.text.setText(text);
    }

    public static String secondsToText(int time) {
        boolean isSmallerThanZero = time<0;
        if(isSmallerThanZero) {
            time = Math.abs(time);
        }
        int hours = time/3600;
        int minutes = (time%3600)/60;
        int seconds = time%60;
        String basic = getFormattedNumber(hours)+":"+getFormattedNumber(minutes)+":"+getFormattedNumber(seconds);
        if(basic.startsWith("00:")) {
            basic = basic.substring(3);
        }
        if(isSmallerThanZero) {
            return "-"+basic;
        } else {
            return basic;
        }
    }

    private static String getFormattedNumber(int number) {
        if(number<10) {
            return "0"+Integer.toString(number);
        }
        return Integer.toString(number);
    }

    public void setLightBackground(boolean lightBackground) {
        isLightBackground = lightBackground;
        setColor(TimerColor.getColor(lastColorCode, isLightBackground));
    }

    public String getId(){
        return id;
    }
}
