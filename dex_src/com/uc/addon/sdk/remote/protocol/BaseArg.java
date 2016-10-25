package com.uc.addon.sdk.remote.protocol;

import android.os.Bundle;

public interface BaseArg {
    boolean checkArgs();

    void fromBundle(Bundle bundle);

    void toBundle(Bundle bundle);
}
