package com.uc.addon.sdk.remote.protocol;

import android.os.Bundle;

public class ExecuteCommandArg implements BaseArg {
    public static final int COMMAND_INVALID = 0;
    public static final int COMMAND_OPEN_ADDON_WINDOW = 10;
    public static final int COMMAND_OPEN_BOOKMARK_WINDOW = 2;
    public static final int COMMAND_OPEN_DOWNLOAD_WINDOW = 3;
    public static final int COMMAND_OPEN_FILE_WINDOW = 4;
    public static final int COMMAND_OPEN_SETTING_WINDOW = 5;
    public static final int COMMAND_REFRESH_CURRENT_VIEW = 1;
    public static final int COMMAND_SWITCH_DAYNIGHT_MODE = 7;
    public static final int COMMAND_SWITCH_LAYOUT_MODE = 8;
    public static final int COMMAND_SWITCH_PICTURE_MODE = 9;
    public static final int COMMAND_SWITCH_SCREEN_MODE = 6;
    public int mCommand;
    public String mParams;

    public ExecuteCommandArg() {
        this.mCommand = 0;
        this.mParams = null;
    }

    public boolean checkArgs() {
        return this.mCommand > 0;
    }

    public void fromBundle(Bundle bundle) {
        this.mCommand = bundle.getInt("execute_command");
        this.mParams = bundle.getString("execute_params");
    }

    public void toBundle(Bundle bundle) {
        bundle.putInt("execute_command", this.mCommand);
        bundle.putString("execute_params", this.mParams);
    }
}
