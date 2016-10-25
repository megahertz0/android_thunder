package com.uc.addon.sdk.remote;

import android.content.Intent;
import android.os.Bundle;
import com.uc.addon.sdk.remote.protocol.DebugUtil;
import com.uc.addon.sdk.remote.protocol.ShareEventArg;

public class EventShare implements EventBase {
    public Intent intent;

    public EventShare(Bundle bundle) {
        DebugUtil.uc_assert(bundle != null, "bundle is null");
        ShareEventArg shareEventArg = new ShareEventArg();
        shareEventArg.fromBundle(bundle);
        this.intent = shareEventArg.intent;
    }

    public int getEventId() {
        return EventIds.EVENT_SHARE;
    }
}
