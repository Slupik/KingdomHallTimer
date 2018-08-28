package jw.kingdom.hall.kingdomtimer.config.model;

/**
 * All rights reserved & copyright ©
 */
public interface ConfigEditable {
    void setSpeakerScreen(String screen);
    void setEnabledGleaming(boolean isGleaming);
    void setEnabledShowMultimedia(boolean isShow);

    void setRecordDestPath(String path);
    void setEnabledAutopilot(boolean isAutopilot);

    void setMultimediaScreen(String multiScreen);
    void setMinRefreshRate(int minRefRate);
    void setWarningRefreshRate(int warningRefRate);
    void setDefaultRefreshRate(int defaultRefRate);

    void setDirectDown(boolean isDirectDown);
}
