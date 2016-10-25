package com.baidu.mobads.interfaces;

public interface IXAdConstants4PDK {
    public static final String EVENT_ERROR = "EVENT_ERROR";
    public static final String EVENT_REQUEST_COMPLETE = "EVENT_REQUEST_COMPLETE";
    public static final String EVENT_REQUEST_CONTENT_VIDEO_PAUSE = "EVENT_REQUEST_CONTENT_VIDEO_PAUSE";
    public static final String EVENT_REQUEST_CONTENT_VIDEO_RESUME = "EVENT_REQUEST_CONTENT_VIDEO_RESUME";
    public static final String EVENT_SLOT_CLICKED = "EVENT_SLOT_CLICKED";
    public static final String EVENT_SLOT_ENDED = "EVENT_SLOT_ENDED";
    public static final String EVENT_SLOT_PRELOADED = "EVENT_SLOT_PRELOADED";
    public static final String EVENT_SLOT_STARTED = "EVENT_SLOT_STARTED";

    public enum ActivityState {
        CREATE("CREATE"),
        START("START"),
        RESTART("RESTART"),
        PAUSE("PAUSE"),
        RESUME("RESUME"),
        STOP("STOP"),
        DESTROY("DESTROY");
        private final String a;

        static {
            String str = "CREATE";
            CREATE = new com.baidu.mobads.interfaces.IXAdConstants4PDK.ActivityState("CREATE", 0, "CREATE");
            str = "START";
            START = new com.baidu.mobads.interfaces.IXAdConstants4PDK.ActivityState("START", 1, "START");
            str = "RESTART";
            RESTART = new com.baidu.mobads.interfaces.IXAdConstants4PDK.ActivityState("RESTART", 2, "RESTART");
            str = "PAUSE";
            PAUSE = new com.baidu.mobads.interfaces.IXAdConstants4PDK.ActivityState("PAUSE", 3, "PAUSE");
            str = "RESUME";
            RESUME = new com.baidu.mobads.interfaces.IXAdConstants4PDK.ActivityState("RESUME", 4, "RESUME");
            String str2 = "STOP";
            STOP = new com.baidu.mobads.interfaces.IXAdConstants4PDK.ActivityState("STOP", 5, "STOP");
            str2 = "DESTROY";
            DESTROY = new com.baidu.mobads.interfaces.IXAdConstants4PDK.ActivityState("DESTROY", 6, "DESTROY");
            b = new com.baidu.mobads.interfaces.IXAdConstants4PDK.ActivityState[]{CREATE, START, RESTART, PAUSE, RESUME, STOP, DESTROY};
        }

        private ActivityState(String str) {
            this.a = str;
        }

        public final String getValue() {
            return this.a;
        }

        public static com.baidu.mobads.interfaces.IXAdConstants4PDK.ActivityState parse(String str) {
            com.baidu.mobads.interfaces.IXAdConstants4PDK.ActivityState[] values = values();
            int length = values.length;
            for (int i = 0; i < length; i++) {
                com.baidu.mobads.interfaces.IXAdConstants4PDK.ActivityState activityState = values[i];
                if (activityState.a.equalsIgnoreCase(str)) {
                    return activityState;
                }
            }
            return null;
        }
    }

    public enum ScreenSizeMode {
        NORMAL("normal"),
        FULL_SCREEN("full_screen");
        private final String a;

        static {
            String str = "normal";
            NORMAL = new com.baidu.mobads.interfaces.IXAdConstants4PDK.ScreenSizeMode("NORMAL", 0, "normal");
            str = "full_screen";
            FULL_SCREEN = new com.baidu.mobads.interfaces.IXAdConstants4PDK.ScreenSizeMode("FULL_SCREEN", 1, "full_screen");
            b = new com.baidu.mobads.interfaces.IXAdConstants4PDK.ScreenSizeMode[]{NORMAL, FULL_SCREEN};
        }

        private ScreenSizeMode(String str) {
            this.a = str;
        }

        public final String getValue() {
            return this.a;
        }

        public static com.baidu.mobads.interfaces.IXAdConstants4PDK.ScreenSizeMode parse(String str) {
            com.baidu.mobads.interfaces.IXAdConstants4PDK.ScreenSizeMode[] values = values();
            int length = values.length;
            for (int i = 0; i < length; i++) {
                com.baidu.mobads.interfaces.IXAdConstants4PDK.ScreenSizeMode screenSizeMode = values[i];
                if (screenSizeMode.a.equalsIgnoreCase(str)) {
                    return screenSizeMode;
                }
            }
            return null;
        }
    }

    public enum SlotState {
        IDEL("idel"),
        LOADING("loading"),
        LOADED("loaded"),
        PLAYING("playing"),
        PAUSED("paused"),
        COMPLETED("completed"),
        ERROR("error");
        private final String a;

        static {
            String str = "idel";
            IDEL = new com.baidu.mobads.interfaces.IXAdConstants4PDK.SlotState("IDEL", 0, "idel");
            str = "loading";
            LOADING = new com.baidu.mobads.interfaces.IXAdConstants4PDK.SlotState("LOADING", 1, "loading");
            str = "loaded";
            LOADED = new com.baidu.mobads.interfaces.IXAdConstants4PDK.SlotState("LOADED", 2, "loaded");
            str = "playing";
            PLAYING = new com.baidu.mobads.interfaces.IXAdConstants4PDK.SlotState("PLAYING", 3, "playing");
            str = "paused";
            PAUSED = new com.baidu.mobads.interfaces.IXAdConstants4PDK.SlotState("PAUSED", 4, "paused");
            String str2 = "completed";
            COMPLETED = new com.baidu.mobads.interfaces.IXAdConstants4PDK.SlotState("COMPLETED", 5, "completed");
            str2 = "error";
            ERROR = new com.baidu.mobads.interfaces.IXAdConstants4PDK.SlotState("ERROR", 6, "error");
            b = new com.baidu.mobads.interfaces.IXAdConstants4PDK.SlotState[]{IDEL, LOADING, LOADED, PLAYING, PAUSED, COMPLETED, ERROR};
        }

        private SlotState(String str) {
            this.a = str;
        }

        public final String getValue() {
            return this.a;
        }

        public static com.baidu.mobads.interfaces.IXAdConstants4PDK.SlotState parse(String str) {
            com.baidu.mobads.interfaces.IXAdConstants4PDK.SlotState[] values = values();
            int length = values.length;
            for (int i = 0; i < length; i++) {
                com.baidu.mobads.interfaces.IXAdConstants4PDK.SlotState slotState = values[i];
                if (slotState.a.equalsIgnoreCase(str)) {
                    return slotState;
                }
            }
            return null;
        }
    }

    public enum SlotType {
        SLOT_TYPE_BANNER("banner"),
        SLOT_TYPE_SPLASH("rsplash"),
        SLOT_TYPE_VERLINK("verlink"),
        SLOT_TYPE_FRONTLINK("frontlink"),
        SLOT_TYPE_INTERSTITIAL("int"),
        SLOT_TYPE_FEEDS("feed"),
        SLOT_TYPE_PREROLL("preroll"),
        SLOT_TYPE_MIDROLL("midroll"),
        SLOT_TYPE_POSTROLL("postroll"),
        SLOT_TYPE_OVERLAY("overlay"),
        SLOT_TYPE_PAUSE_ROLL("pauseroll");
        private final String a;

        static {
            String str = "banner";
            SLOT_TYPE_BANNER = new com.baidu.mobads.interfaces.IXAdConstants4PDK.SlotType("SLOT_TYPE_BANNER", 0, "banner");
            str = "rsplash";
            SLOT_TYPE_SPLASH = new com.baidu.mobads.interfaces.IXAdConstants4PDK.SlotType("SLOT_TYPE_SPLASH", 1, "rsplash");
            str = "verlink";
            SLOT_TYPE_VERLINK = new com.baidu.mobads.interfaces.IXAdConstants4PDK.SlotType("SLOT_TYPE_VERLINK", 2, "verlink");
            str = "frontlink";
            SLOT_TYPE_FRONTLINK = new com.baidu.mobads.interfaces.IXAdConstants4PDK.SlotType("SLOT_TYPE_FRONTLINK", 3, "frontlink");
            str = "int";
            SLOT_TYPE_INTERSTITIAL = new com.baidu.mobads.interfaces.IXAdConstants4PDK.SlotType("SLOT_TYPE_INTERSTITIAL", 4, "int");
            String str2 = "feed";
            SLOT_TYPE_FEEDS = new com.baidu.mobads.interfaces.IXAdConstants4PDK.SlotType("SLOT_TYPE_FEEDS", 5, "feed");
            str2 = "preroll";
            SLOT_TYPE_PREROLL = new com.baidu.mobads.interfaces.IXAdConstants4PDK.SlotType("SLOT_TYPE_PREROLL", 6, "preroll");
            str2 = "midroll";
            SLOT_TYPE_MIDROLL = new com.baidu.mobads.interfaces.IXAdConstants4PDK.SlotType("SLOT_TYPE_MIDROLL", 7, "midroll");
            str2 = "postroll";
            SLOT_TYPE_POSTROLL = new com.baidu.mobads.interfaces.IXAdConstants4PDK.SlotType("SLOT_TYPE_POSTROLL", 8, "postroll");
            str2 = "overlay";
            SLOT_TYPE_OVERLAY = new com.baidu.mobads.interfaces.IXAdConstants4PDK.SlotType("SLOT_TYPE_OVERLAY", 9, "overlay");
            str2 = "pauseroll";
            SLOT_TYPE_PAUSE_ROLL = new com.baidu.mobads.interfaces.IXAdConstants4PDK.SlotType("SLOT_TYPE_PAUSE_ROLL", 10, "pauseroll");
            b = new com.baidu.mobads.interfaces.IXAdConstants4PDK.SlotType[]{SLOT_TYPE_BANNER, SLOT_TYPE_SPLASH, SLOT_TYPE_VERLINK, SLOT_TYPE_FRONTLINK, SLOT_TYPE_INTERSTITIAL, SLOT_TYPE_FEEDS, SLOT_TYPE_PREROLL, SLOT_TYPE_MIDROLL, SLOT_TYPE_POSTROLL, SLOT_TYPE_OVERLAY, SLOT_TYPE_PAUSE_ROLL};
        }

        private SlotType(String str) {
            this.a = str;
        }

        public final String getValue() {
            return this.a;
        }

        public static com.baidu.mobads.interfaces.IXAdConstants4PDK.SlotType parse(String str) {
            com.baidu.mobads.interfaces.IXAdConstants4PDK.SlotType[] values = values();
            int length = values.length;
            for (int i = 0; i < length; i++) {
                com.baidu.mobads.interfaces.IXAdConstants4PDK.SlotType slotType = values[i];
                if (slotType.a.equalsIgnoreCase(str)) {
                    return slotType;
                }
            }
            return null;
        }
    }

    public enum VideoAssetPlayMode {
        VIDEO_ASSET_AUTO_PLAY_TYPE_ATTENDED("VIDEO_ASSET_AUTO_PLAY_TYPE_ATTENDED"),
        VIDEO_ASSET_AUTO_PLAY_TYPE_UNATTENDED("VIDEO_ASSET_AUTO_PLAY_TYPE_UNATTENDED");
        private final String a;

        static {
            String str = "VIDEO_ASSET_AUTO_PLAY_TYPE_ATTENDED";
            VIDEO_ASSET_AUTO_PLAY_TYPE_ATTENDED = new com.baidu.mobads.interfaces.IXAdConstants4PDK.VideoAssetPlayMode("VIDEO_ASSET_AUTO_PLAY_TYPE_ATTENDED", 0, "VIDEO_ASSET_AUTO_PLAY_TYPE_ATTENDED");
            str = "VIDEO_ASSET_AUTO_PLAY_TYPE_UNATTENDED";
            VIDEO_ASSET_AUTO_PLAY_TYPE_UNATTENDED = new com.baidu.mobads.interfaces.IXAdConstants4PDK.VideoAssetPlayMode("VIDEO_ASSET_AUTO_PLAY_TYPE_UNATTENDED", 1, "VIDEO_ASSET_AUTO_PLAY_TYPE_UNATTENDED");
            b = new com.baidu.mobads.interfaces.IXAdConstants4PDK.VideoAssetPlayMode[]{VIDEO_ASSET_AUTO_PLAY_TYPE_ATTENDED, VIDEO_ASSET_AUTO_PLAY_TYPE_UNATTENDED};
        }

        private VideoAssetPlayMode(String str) {
            this.a = str;
        }

        public final String getValue() {
            return this.a;
        }

        public static com.baidu.mobads.interfaces.IXAdConstants4PDK.VideoAssetPlayMode parse(String str) {
            com.baidu.mobads.interfaces.IXAdConstants4PDK.VideoAssetPlayMode[] values = values();
            int length = values.length;
            for (int i = 0; i < length; i++) {
                com.baidu.mobads.interfaces.IXAdConstants4PDK.VideoAssetPlayMode videoAssetPlayMode = values[i];
                if (videoAssetPlayMode.a.equalsIgnoreCase(str)) {
                    return videoAssetPlayMode;
                }
            }
            return null;
        }
    }

    public enum VideoState {
        IDLE("IDLE"),
        PLAYING("PLAYING"),
        PAUSED("PAUSED"),
        COMPLETED("COMPLETED");
        private final String a;

        static {
            String str = "IDLE";
            IDLE = new com.baidu.mobads.interfaces.IXAdConstants4PDK.VideoState("IDLE", 0, "IDLE");
            str = "PLAYING";
            PLAYING = new com.baidu.mobads.interfaces.IXAdConstants4PDK.VideoState("PLAYING", 1, "PLAYING");
            str = "PAUSED";
            PAUSED = new com.baidu.mobads.interfaces.IXAdConstants4PDK.VideoState("PAUSED", 2, "PAUSED");
            str = "COMPLETED";
            COMPLETED = new com.baidu.mobads.interfaces.IXAdConstants4PDK.VideoState("COMPLETED", 3, "COMPLETED");
            b = new com.baidu.mobads.interfaces.IXAdConstants4PDK.VideoState[]{IDLE, PLAYING, PAUSED, COMPLETED};
        }

        private VideoState(String str) {
            this.a = str;
        }

        public final String getValue() {
            return this.a;
        }

        public static com.baidu.mobads.interfaces.IXAdConstants4PDK.VideoState parse(String str) {
            com.baidu.mobads.interfaces.IXAdConstants4PDK.VideoState[] values = values();
            int length = values.length;
            for (int i = 0; i < length; i++) {
                com.baidu.mobads.interfaces.IXAdConstants4PDK.VideoState videoState = values[i];
                if (videoState.a.equalsIgnoreCase(str)) {
                    return videoState;
                }
            }
            return null;
        }
    }

    public enum VisitorAction {
        PAUSE_BUTTON_CLICKED("PAUSE_BUTTON_CLICKED"),
        RESUME_BUTTON_CLICKED("RESUME_BUTTON_CLICKED");
        private final String a;

        static {
            String str = "PAUSE_BUTTON_CLICKED";
            PAUSE_BUTTON_CLICKED = new com.baidu.mobads.interfaces.IXAdConstants4PDK.VisitorAction("PAUSE_BUTTON_CLICKED", 0, "PAUSE_BUTTON_CLICKED");
            str = "RESUME_BUTTON_CLICKED";
            RESUME_BUTTON_CLICKED = new com.baidu.mobads.interfaces.IXAdConstants4PDK.VisitorAction("RESUME_BUTTON_CLICKED", 1, "RESUME_BUTTON_CLICKED");
            b = new com.baidu.mobads.interfaces.IXAdConstants4PDK.VisitorAction[]{PAUSE_BUTTON_CLICKED, RESUME_BUTTON_CLICKED};
        }

        private VisitorAction(String str) {
            this.a = str;
        }

        public final String getValue() {
            return this.a;
        }

        public static com.baidu.mobads.interfaces.IXAdConstants4PDK.VisitorAction parse(String str) {
            com.baidu.mobads.interfaces.IXAdConstants4PDK.VisitorAction[] values = values();
            int length = values.length;
            for (int i = 0; i < length; i++) {
                com.baidu.mobads.interfaces.IXAdConstants4PDK.VisitorAction visitorAction = values[i];
                if (visitorAction.a.equalsIgnoreCase(str)) {
                    return visitorAction;
                }
            }
            return null;
        }
    }
}
