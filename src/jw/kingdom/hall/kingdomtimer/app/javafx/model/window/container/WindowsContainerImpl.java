package jw.kingdom.hall.kingdomtimer.app.javafx.model.window.container;

import com.sun.istack.internal.Nullable;
import jw.kingdom.hall.kingdomtimer.app.javafx.model.window.AppWindow;
import jw.kingdom.hall.kingdomtimer.app.javafx.model.window.WindowType;

import java.util.HashMap;

/**
 * All rights reserved & copyright ©
 */
public class WindowsContainerImpl implements WindowsContainer {
    private final HashMap<WindowType, AppWindow> windows = new HashMap<>();

    @Override
    public void putAppWindow(WindowType type, AppWindow window) {
        windows.put(type, window);
    }

    @Override
    @Nullable
    public AppWindow getAppWindow(WindowType type) {
        return windows.get(type);
    }
}
