package com.uc.addon.sdk.remote.protocol;

import android.os.Bundle;

public class MemoryEventArg implements BaseArg {
    public static final int MEMORY_STATE_BAD = 2;
    public static final int MEMORY_STATE_VERYBAD = 3;
    private String a;
    public int mState;

    public MemoryEventArg() {
        this.mState = 2;
        this.a = "memory_state";
    }

    public boolean checkArgs() {
        return this.mState == 2 || this.mState == 3;
    }

    public void fromBundle(Bundle bundle) {
        this.mState = bundle.getInt(this.a);
    }

    public void toBundle(Bundle bundle) {
        bundle.putInt(this.a, this.mState);
    }
}
