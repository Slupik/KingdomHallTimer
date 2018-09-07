package jw.kingdom.hall.kingdomtimer.javafx.view.head.tab.time;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import jw.kingdom.hall.kingdomtimer.config.model.Config;
import jw.kingdom.hall.kingdomtimer.entity.time.countdown.CountdownController;
import jw.kingdom.hall.kingdomtimer.entity.time.schedule.ScheduleController;
import jw.kingdom.hall.kingdomtimer.javafx.control.time.buzzer.BtnBuzzerController;
import jw.kingdom.hall.kingdomtimer.javafx.control.time.direct.BtnTimeDirectForInstantController;
import jw.kingdom.hall.kingdomtimer.javafx.control.time.direct.BtnTimeDirectForPanel;
import jw.kingdom.hall.kingdomtimer.javafx.control.time.display.TimeDisplayController;
import jw.kingdom.hall.kingdomtimer.javafx.custom.TimeField;
import jw.kingdom.hall.kingdomtimer.javafx.entity.task.TaskFxBean;
import jw.kingdom.hall.kingdomtimer.javafx.view.head.tab.TabPresenter;
import jw.kingdom.hall.kingdomtimer.javafx.view.head.tab.time.table.TaskTableController;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * All rights reserved & copyright ©
 */
public class TabTimePresenter extends TabPresenter implements TaskTableController.Data, FastPanelPresenter.Data {

    @FXML
    private Label lblTime;

    @FXML
    private Button btnFastDirect;

    @FXML
    private Button btnInstantDirect;

    @FXML
    private HBox hbTimeControlsContainer;

    @FXML
    private Button btnBuzzer;

    @FXML
    private TimeField tfFastTime;

    @FXML
    private TextField tfName;

    @FXML
    private TimeField atfTime;

    @FXML
    private CheckBox cbBuzzer;

    @FXML
    private Button btnCountdownDirect;

    @FXML
    private Button btnWidgetVisibility;

    @FXML
    private CheckBox cbTimeToEvaluate;

    @FXML
    private TableView<TaskFxBean> tvList;

    @FXML
    private TableColumn<TaskFxBean, String> tcDelete;

    @FXML
    private TableColumn<TaskFxBean, String> tcBuzzer;

    @FXML
    private TableColumn<TaskFxBean, String> tcDirect;

    @FXML
    private TableColumn<TaskFxBean, String> tcName;

    @FXML
    private TableColumn<TaskFxBean, TimeField> tcTime;

    @FXML
    private TableColumn<TaskFxBean, String> tcType;

    private FastPanelPresenter fastPanel;
    private BtnTimeDirectForPanel timeDirectController;
    private BtnTimeDirectForInstantController instantDirectController;
    private BtnTimeDirectForPanel fastDirectController;

    @Override
    public void onStart() {
        fastPanel = new FastPanelPresenter(this);

        timeDirectController = new BtnTimeDirectForPanel(getConfig(), btnCountdownDirect);
        timeDirectController.setMedium(false);

        fastDirectController = new BtnTimeDirectForPanel(getConfig(), btnFastDirect);
        fastDirectController.setMedium(false);

        instantDirectController = new BtnTimeDirectForInstantController(getSchedule(), getConfig(), btnInstantDirect);
        instantDirectController.setMedium(true);

        getCountdown().addTimeDisplay(new TimeDisplayController(lblTime));

        new BtnBuzzerController(btnBuzzer, getCountdown());
        new TaskTableController(this);
        new WidgetVisibilityController(btnWidgetVisibility, getWindowsContainer());
        new BackupPresenter(getWindowData().getBackup());
    }

    @Override
    protected void onCreate(URL location, ResourceBundle resources) {

    }

    public void handleLoadTimeAction(ActionEvent actionEvent) {
        fastPanel.handleLoadTime();
    }

    public void handleAddTime(ActionEvent actionEvent) {
        fastPanel.handleAddTime();
    }

    public void handleRemoveTime(ActionEvent actionEvent) {
        fastPanel.handleRemoveTime();
    }

    public void handleAddTask(ActionEvent actionEvent) {

    }

    public void loadTasksOnline(ActionEvent actionEvent) {

    }

    public void loadCircuitTasksOnline(ActionEvent actionEvent) {

    }

    @Override
    public Config getConfig() {
        return getWindowData().getConfig();
    }

    @Override
    public ScheduleController getSchedule() {
        return getWindowData().getSchedule();
    }

    @Override
    public TableView<TaskFxBean> getTable() {
        return tvList;
    }

    @Override
    public TableColumn<TaskFxBean, String> getTcDelete() {
        return tcDelete;
    }

    @Override
    public TableColumn<TaskFxBean, String> getTcBuzzer() {
        return tcBuzzer;
    }

    @Override
    public TableColumn<TaskFxBean, String> getTcDirect() {
        return tcDirect;
    }

    @Override
    public TableColumn<TaskFxBean, String> getTcName() {
        return tcName;
    }

    @Override
    public TableColumn<TaskFxBean, TimeField> getTcTime() {
        return tcTime;
    }

    @Override
    public TableColumn<TaskFxBean, String> getTcType() {
        return tcType;
    }

    @Override
    public TimeField getFastTimeField() {
        return tfFastTime;
    }

    @Override
    public CountdownController getCountdown() {
        return getWindowData().getCountdown();
    }

    @Override
    public BtnTimeDirectForPanel getFastDirectController() {
        return fastDirectController;
    }
}
