package jw.kingdom.hall.kingdomtimer.javafx.view.head.tab.speaker;

import javafx.scene.control.ChoiceBox;
import jw.kingdom.hall.kingdomtimer.config.model.Config;
import jw.kingdom.hall.kingdomtimer.entity.monitor.Monitor;
import jw.kingdom.hall.kingdomtimer.entity.monitor.MonitorList;
import jw.kingdom.hall.kingdomtimer.javafx.control.monitor.MonitorChoiceBoxPresenter;
import jw.kingdom.hall.kingdomtimer.javafx.entity.view.window.AppWindow;
import jw.kingdom.hall.kingdomtimer.javafx.entity.view.window.WindowType;
import jw.kingdom.hall.kingdomtimer.javafx.entity.view.window.container.WindowsContainer;
import jw.kingdom.hall.kingdomtimer.javafx.view.speaker.SpeakerWindow;

/**
 * All rights reserved & copyright ©
 */
class PresenterOfMultimediaMonitorSelector {

    private final Input input;

    PresenterOfMultimediaMonitorSelector(Input input){
        this.input = input;
        init();
    }

    private void init() {
        new MonitorChoiceBoxPresenter(getMonitorList(), getBox()).addListener((last, now) -> {
            getSpeakerWindow().setMonitor(now);
            getConfig().setSpeakerScreen(now.getID());
        });
        setValueFomConfig();
    }

    private void setValueFomConfig() {
        getBox().setValue(getMonitorList().findById(getConfig().getSpeakerScreen()));
    }

    private SpeakerWindow getSpeakerWindow() {
        return (SpeakerWindow) getWindowsContainer().getAppWindow(WindowType.SPEAKER);
    }

    private ChoiceBox<Monitor> getBox() {
        return input.getBoxWithMonitorForPreview();
    }

    private Config getConfig() {
        return input.getConfig();
    }

    private WindowsContainer getWindowsContainer() {
        return input.getWindowsContainer();
    }

    private MonitorList getMonitorList() {
        return input.getMonitorList();
    }

    interface Input {
        ChoiceBox<Monitor> getBoxWithMonitorForPreview();
        WindowsContainer getWindowsContainer();
        MonitorList getMonitorList();
        Config getConfig();
    }
}
