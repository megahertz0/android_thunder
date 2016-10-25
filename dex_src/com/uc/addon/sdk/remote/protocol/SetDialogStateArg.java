package com.uc.addon.sdk.remote.protocol;

import android.os.Bundle;

public class SetDialogStateArg implements BaseArg {
    public int mDialogId;
    public int mDialogState;

    public boolean checkArgs() {
        return true;
    }

    public void fromBundle(Bundle bundle) {
        this.mDialogId = bundle.getInt("key_dialog_id");
        this.mDialogState = bundle.getInt("key_dialog_state");
    }

    public void toBundle(Bundle bundle) {
        bundle.putInt("key_dialog_id", this.mDialogId);
        bundle.putInt("key_dialog_state", this.mDialogState);
    }
}
