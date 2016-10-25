package com.umeng.socialize.editorpage;

import android.location.Location;
import android.widget.Toast;
import com.umeng.socialize.bean.UMLocation;
import com.umeng.socialize.editorpage.location.a;
import com.umeng.socialize.editorpage.location.b;
import com.umeng.socialize.utils.Log;

// compiled from: ShareActivity.java
class f extends b {
    final /* synthetic */ ShareActivity a;

    f(ShareActivity shareActivity, a aVar) {
        this.a = shareActivity;
        super(aVar);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        a((Location) obj);
    }

    protected void onPreExecute() {
        super.onPreExecute();
        this.a.a(true);
    }

    protected void a(Location location) {
        super.onPostExecute(location);
        Log.e("xxxxx", new StringBuilder("result = ").append(location).toString());
        this.a.C = UMLocation.build(location);
        this.a.a(false);
        if (location == null && !this.a.isFinishing()) {
            Toast.makeText(this.a.y, "\u83b7\u53d6\u5730\u7406\u4f4d\u7f6e\u5931\u8d25\uff0c\u8bf7\u7a0d\u5019\u91cd\u8bd5.", 0).show();
        }
    }

    protected void onCancelled() {
        super.onCancelled();
        this.a.a(false);
    }
}
