package jw.kingdom.hall.kingdomtimer.app.javafx.domain.app;

import javafx.stage.Stage;
import jw.kingdom.hall.kingdomtimer.app.javafx.domain.window.AppWindow;
import jw.kingdom.hall.kingdomtimer.app.javafx.domain.window.WindowInput;
import jw.kingdom.hall.kingdomtimer.app.javafx.domain.window.WindowType;
import jw.kingdom.hall.kingdomtimer.app.javafx.domain.window.container.WindowsContainer;
import jw.kingdom.hall.kingdomtimer.app.javafx.domain.window.container.WindowsContainerImpl;
import jw.kingdom.hall.kingdomtimer.app.javafx.view.head.HeadWindow;
import jw.kingdom.hall.kingdomtimer.app.javafx.view.speaker.SpeakerWindow;
import jw.kingdom.hall.kingdomtimer.app.javafx.view.widget.HandyWindow;
import jw.kingdom.hall.kingdomtimer.config.model.Config;
import jw.kingdom.hall.kingdomtimer.domain.record.voice.RecordControl;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * All rights reserved & copyright ©
 */
public class App {

    private final AppInput appInput;
    private final WindowsContainer container = new WindowsContainerImpl();

    public App(AppInput appInput) {
        this.appInput = appInput;
    }

    public void start(Stage primaryStage) throws Exception {
        initWindows(primaryStage);
    }

    private void initWindows(Stage primaryStage) {
        buildWindow(HeadWindow.class, primaryStage, WindowType.PANEL);
        buildWindow(SpeakerWindow.class, new Stage(), WindowType.SPEAKER);
        buildWindow(HandyWindow.class, new Stage(), WindowType.WIDGET);
    }

    private void buildWindow(Class<? extends AppWindow> appWindowClass, Stage stage, WindowType type) {
        try {
            Constructor<? extends AppWindow> ctor = appWindowClass.getConstructor(Stage.class, WindowInput.class);
            AppWindow window = ctor.newInstance(stage, getWindowInput());
            window.init();
            container.putAppWindow(type, window);
        } catch (NoSuchMethodException | InstantiationException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private WindowInput getWindowInput() {
        return new WindowInput() {
            @Override
            public Config getConfig() {
                return appInput.getConfig();
            }

            @Override
            public RecordControl getRecorder() {
                return appInput.getRecorder();
            }

            @Override
            public WindowsContainer getWindowsContainer() {
                return container;
            }
        };
    }

}
