package jw.kingdom.hall.kingdomtimer.javafx.translate;

import jw.kingdom.hall.kingdomtimer.domain.task.TaskBean;

/**
 * This file is part of KingdomHallTimer which is released under "no licence".
 */
public class MeetingTaskTrans {
    public static String getForTable(TaskBean.Type type) {
        switch (type) {
            case UNKNOWN:
                return "Nieznany";
            case NONE:
                return "Brak";
            case TREASURES:
                return "Skarby z Biblii";
            case MINISTRY:
                return "Ulepszajmy swą służbę";
            case LIVING:
                return "Tryb życia";
            case LECTURE:
                return "Wykład";
            case WATCHTOWER:
                return "Strażnica";
            case CIRCUIT:
                return "Obsługa";
            case OTHER:
                return "Inny";
            default:
                return "???";
        }
    }
}
