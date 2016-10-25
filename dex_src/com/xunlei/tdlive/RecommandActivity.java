package com.xunlei.tdlive;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import com.tencent.open.SocialConstants;
import com.xunlei.tdlive.base.BaseActivity;
import com.xunlei.tdlive.util.ac;

public class RecommandActivity extends BaseActivity {
    protected void onCreate(Bundle bundle) {
        Fragment instantiate;
        super.onCreate(bundle);
        setContentView(R.layout.xllive_activity_recommand);
        if (ac.j()) {
            instantiate = q.instantiate(this, q.class.getName(), getIntent().getExtras());
        } else {
            instantiate = ea.instantiate(this, ea.class.getName(), getIntent().getExtras());
        }
        getSupportFragmentManager().beginTransaction().replace(R.id.container, instantiate, "recommand").commit();
    }

    public static void a(Context context, String str, String str2, int i) {
        if (str != null && str.length() > 0) {
            context.startActivity(new Intent(context, RecommandActivity.class).addFlags(i).putExtra(WebBrowserActivity.EXTRA_TITLE, str2).putExtra(SocialConstants.PARAM_URL, str).putExtra("banner", false).putExtra("left", true).putExtra("right", false).putExtra("titlebar", true));
        }
    }
}
