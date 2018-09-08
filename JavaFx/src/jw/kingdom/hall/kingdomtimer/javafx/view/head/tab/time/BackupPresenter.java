package jw.kingdom.hall.kingdomtimer.javafx.view.head.tab.time;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import jw.kingdom.hall.kingdomtimer.javafx.entity.backup.BackupController;

import java.util.Optional;

/**
 * This file is part of KingdomHallTimer which is released under "no licence".
 */
class BackupPresenter {

    private final BackupController backup;

    BackupPresenter(BackupController backup) {
        this.backup = backup;
        run();
    }

    private void run(){
        if(backup.isBackupAvailable()) {
            showAskDialog();
        }
    }

    private void showAskDialog() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Wykryto kopię zapasową!");
        alert.setHeaderText("Program wykrył kopię zapasową ostatnich działań, czy chcesz ją przywrócić?");
        alert.setContentText("Program automatycznie sporządza kopię zapasową wykonywanych działań. Jeżeli zostanie " +
                "zamknięty w nieprawidłowy sposób, możliwe jest przywrócenie poprzedniego stanu.");

        ButtonType btnYes = new ButtonType("Tak", ButtonBar.ButtonData.YES);
        ButtonType btnNo = new ButtonType("Nie", ButtonBar.ButtonData.CANCEL_CLOSE);

        alert.getButtonTypes().setAll(btnYes, btnNo);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == btnYes){
            backup.restore();
        } else {
            backup.delete();
        }
    }
}
