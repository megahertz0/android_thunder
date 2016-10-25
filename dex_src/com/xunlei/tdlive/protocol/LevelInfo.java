package com.xunlei.tdlive.protocol;

public final class LevelInfo {
    private static final String BASE_ICON_URL = "http://cdn.live.xunlei.com/cdn/images/level/";
    public int current;
    public String icon;
    public String icon2;
    public String title;

    public final String getIconFullPath() {
        return new StringBuilder(BASE_ICON_URL).append(this.icon).toString();
    }

    public final String getIcon2FullPath() {
        return new StringBuilder(BASE_ICON_URL).append(this.icon2).toString();
    }
}
