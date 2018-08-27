package jw.kingdom.hall.kingdomtimer.app.view.panel.tabs.recordControl;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import jw.kingdom.hall.kingdomtimer.app.view.common.ControlledScreenImpl;
import jw.kingdom.hall.kingdomtimer.app.view.common.controller.TimeDisplayController;
import jw.kingdom.hall.kingdomtimer.app.view.common.custom.sps.StartPauseStopView;
import jw.kingdom.hall.kingdomtimer.domain.record.voice.VoiceRecorder;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * All rights reserved & copyright ©
 */
public class RecordController extends ControlledScreenImpl implements Initializable, StartPauseStopView.Listener {
    //TODO move meeting plan to special object
    //TODO add auto starting and ending of recording

    @FXML
    private VBox vbMainContainer;

    @FXML
    private Label lblTime;

    @FXML
    private HBox hbControlsContainer;

    @FXML
    private CheckBox cbAutopStart;

    private TimeDisplayController controller;
    private StartPauseStopView spsView;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initVoiceRecordInstance();

        spsView = new StartPauseStopView();
        hbControlsContainer.getChildren().add(spsView);
        spsView.addListener(this);

        controller = new TimeDisplayController(lblTime);
        controller.setTime(0);
    }

    private void initVoiceRecordInstance() {
        try {
            VoiceRecorder.getInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    protected Region getMainContainer() {
        return vbMainContainer;
    }

    @Override
    public void onStart() {
        VoiceRecorder.getInstance().start();
    }

    @Override
    public void onPause() {
        VoiceRecorder.getInstance().setPause(true);
    }

    @Override
    public void onUnPause() {
        VoiceRecorder.getInstance().setPause(false);
    }

    @Override
    public void onStop() {
        VoiceRecorder.getInstance().stop();
    }
}
