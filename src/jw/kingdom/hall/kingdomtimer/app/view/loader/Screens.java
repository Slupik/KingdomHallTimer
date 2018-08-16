package jw.kingdom.hall.kingdomtimer.app.view.loader;

/**
 * All rights reserved & copyright ©
 */
public enum Screens {
    PANEL_CONTROLLING("controlling", ScreenPaths.PANEL_LAYOUTS +"controlling.fxml"),
    VIEWER("viewer", ScreenPaths.VIEWER_LAYOUTS +"viewer.fxml");

    public final String NAME;
    public final String PATH;

    Screens(String name, String path){
        NAME = name;
        PATH = path;
    }
}
