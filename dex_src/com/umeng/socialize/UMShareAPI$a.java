package com.umeng.socialize;

import android.content.Context;
import android.content.SharedPreferences.Editor;
import com.umeng.socialize.common.QueuedWork.UMAsyncTask;
import com.umeng.socialize.net.ActionBarRequest;
import com.umeng.socialize.net.ActionBarResponse;
import com.umeng.socialize.net.RestAPI;
import com.umeng.socialize.utils.Log;

class UMShareAPI$a extends UMAsyncTask<Void> {
    private Context a;

    protected /* synthetic */ Object doInBackground() {
        return a();
    }

    public UMShareAPI$a(Context context) {
        this.a = context;
    }

    protected Void a() {
        ActionBarResponse queryShareId = RestAPI.queryShareId(new ActionBarRequest(this.a, c()));
        if (queryShareId != null && queryShareId.isOk()) {
            b();
            Log.i(new StringBuilder("response: ").append(queryShareId.mMsg).toString());
            Config.EntityKey = queryShareId.mEntityKey;
            Config.SessionId = queryShareId.mSid;
            Config.UID = queryShareId.mUid;
        }
        Log.i(new StringBuilder("response has error: ").append(queryShareId == null ? "null" : queryShareId.mMsg).toString());
        return null;
    }

    private boolean c() {
        return this.a.getSharedPreferences("umeng_socialize", 0).getBoolean("newinstall", false);
    }

    public void b() {
        Editor edit = this.a.getSharedPreferences("umeng_socialize", 0).edit();
        edit.putBoolean("newinstall", true);
        edit.commit();
    }
}
