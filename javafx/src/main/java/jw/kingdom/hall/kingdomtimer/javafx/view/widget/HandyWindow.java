package jw.kingdom.hall.kingdomtimer.javafx.view.widget;

import javafx.application.Platform;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import jw.kingdom.hall.kingdomtimer.javafx.domain.window.AppWindow;
import jw.kingdom.hall.kingdomtimer.javafx.domain.window.WindowInput;
import jw.kingdom.hall.kingdomtimer.domain.monitor.Monitor;

import java.util.List;

import static jw.kingdom.hall.kingdomtimer.javafx.view.widget.HandyWindow.Screens.MAIN;

/**
 * This file is part of KingdomHallTimer which is released under "no licence".
 */
public class HandyWindow extends AppWindow {

    public HandyWindow(Stage stage, WindowInput input) {
        super(stage, input);
    }

    @Override
    protected boolean isToShowAtInit() {
        return true;
    }

    @Override
    protected void loadScreens() {
        viewManager.loadScreen(MAIN.name, MAIN.path);
    }

    @Override
    protected void setMainView() {
        viewManager.setScreen(MAIN.name);
    }

    @Override
    protected void onPreInit() {
        super.onPreInit();
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setAlwaysOnTop(true);
    }

    @Override
    protected void onPostLoadViews() {
        super.onPostLoadViews();
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
        stage.show();

        stage.setWidth(300);
        stage.setHeight(110);
    }

    @Override
    protected void onPostShow() {
        super.onPostShow();
        new WindowMovingController(stage, this);
    }

    @Override
    protected void onPostInit() {
        super.onPostInit();

        setPosToRightUp();

        new Thread(()->{
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Platform.runLater(()-> getStage().sizeToScene());
        }).start();
    }

    private void setPosToRightUp() {
        Monitor monitor = getMainMonitor();
        if(monitor!=null) {
            getStage().setX(
                    monitor.getWidth()-getStage().getWidth()-75
            );
            getStage().setY(
                    monitor.getY()+75
            );
        }
    }

    private Monitor getMainMonitor() {
        List<Monitor> list = getInput().getMonitorsManager().getAll();
        for(int i=list.size()-1;i>=0;i--){
            Monitor monitor = list.get(i);
            if(monitor.isPrimary()){
                return monitor;
            }
        }
        return null;
    }

    public enum Screens {
        MAIN("main", "/layout/window/handy/main.fxml"),
        ;

        public final String name;
        public final String path;

        Screens(String name, String path) {
            this.name = name;
            this.path = path;
        }
    }
}
