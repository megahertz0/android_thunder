package com.xunlei.tdlive.usercenter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.xunlei.tdlive.R;
import com.xunlei.tdlive.base.BaseActivity;
import com.xunlei.xiazaibao.sdk.XZBDevice;

public class ShellActivity extends BaseActivity implements OnClickListener {
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.xllive_activity_shell);
        Intent intent = getIntent();
        if (intent == null) {
            finish();
            return;
        }
        CharSequence stringExtra = intent.getStringExtra("ShellActivity.KEY_TITLE");
        String stringExtra2 = intent.getStringExtra("ShellActivity.KEY_FRAGMENT_CLASS");
        Bundle bundleExtra = intent.getBundleExtra("ShellActivity.KEY_BUNDLE_DATA");
        int intExtra = intent.getIntExtra("ShellActivity.KEY_CONTENT_LAYOUT", 0);
        if (intExtra != 0) {
            LayoutInflater.from(this).inflate(intExtra, (FrameLayout) findViewById(R.id.fragment_container));
        } else {
            Fragment fragment;
            try {
                fragment = (Fragment) Class.forName(stringExtra2).getConstructor(new Class[0]).newInstance(new Object[0]);
            } catch (Exception e) {
                e.printStackTrace();
                fragment = null;
            }
            if (fragment == null) {
                finish();
                return;
            } else {
                fragment.setArguments(bundleExtra);
                getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, fragment).commit();
            }
        }
        View findViewById = findViewById(R.id.titlebar);
        if (TextUtils.isEmpty(stringExtra)) {
            findViewById.setVisibility(XZBDevice.Wait);
            return;
        }
        ((TextView) findViewById.findViewById(R.id.tvTitle)).setText(stringExtra);
        findViewById.findViewById(R.id.ivBack).setOnClickListener(this);
    }

    public static void a(Context context, Class<?> cls, String str, Bundle bundle) {
        Intent intent = new Intent(context, ShellActivity.class);
        intent.putExtra("ShellActivity.KEY_TITLE", str);
        intent.putExtra("ShellActivity.KEY_FRAGMENT_CLASS", cls.getName());
        intent.putExtra("ShellActivity.KEY_BUNDLE_DATA", bundle);
        context.startActivity(intent);
    }

    public void onClick(View view) {
        if (view.getId() == R.id.ivBack) {
            finish();
        }
    }
}
