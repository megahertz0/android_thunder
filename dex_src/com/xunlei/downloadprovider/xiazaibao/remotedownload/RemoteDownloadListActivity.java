package com.xunlei.downloadprovider.xiazaibao.remotedownload;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.animation.AnimationUtils;
import com.xunlei.downloadprovider.app.BaseActivity;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import com.xunlei.xiazaibao.shoulei.XZBShouleiUtil;

public class RemoteDownloadListActivity extends BaseActivity {
    RemoteDownloadContainerFragment a;
    XZBTaskInfoDetailFragment b;
    d c;
    private ShowTaskDetailBroadcast d;

    public class ShowTaskDetailBroadcast extends BroadcastReceiver {
        public void onReceive(Context context, Intent intent) {
            CharSequence action = intent.getAction();
            al alVar;
            RemoteDownloadListActivity remoteDownloadListActivity;
            if (TextUtils.equals(action, "show_task_detail")) {
                alVar = (al) intent.getSerializableExtra("task_serial_extra");
                remoteDownloadListActivity = RemoteDownloadListActivity.this;
                if (alVar == null) {
                    remoteDownloadListActivity.showToast("\u67e5\u627e\u4e0d\u5230\u4efb\u52a1\u4fe1\u606f");
                    return;
                }
                RemoteDownloadListActivity.this = alVar;
                remoteDownloadListActivity.b.a();
                remoteDownloadListActivity.b.f = RemoteDownloadListActivity.this.g.b().f;
                XZBTaskInfoDetailFragment xZBTaskInfoDetailFragment = remoteDownloadListActivity.b;
                if (RemoteDownloadListActivity.this != null) {
                    if (xZBTaskInfoDetailFragment.c != null) {
                        xZBTaskInfoDetailFragment.c.a();
                    }
                    xZBTaskInfoDetailFragment.a(0);
                    xZBTaskInfoDetailFragment.getView().setVisibility(0);
                    ((BaseActivity) xZBTaskInfoDetailFragment.getActivity()).animationBarAlpha(true);
                    xZBTaskInfoDetailFragment.d = AnimationUtils.loadAnimation(xZBTaskInfoDetailFragment.getActivity(), 2131034126);
                    xZBTaskInfoDetailFragment.d.setDuration(300);
                    xZBTaskInfoDetailFragment.d.setAnimationListener(new bf(xZBTaskInfoDetailFragment));
                    xZBTaskInfoDetailFragment.b.setAnimation(xZBTaskInfoDetailFragment.d);
                    xZBTaskInfoDetailFragment.b.animate();
                    xZBTaskInfoDetailFragment.e = AnimationUtils.loadAnimation(xZBTaskInfoDetailFragment.getActivity(), 2131034143);
                    xZBTaskInfoDetailFragment.e.setDuration(300);
                    xZBTaskInfoDetailFragment.e.setAnimationListener(new bg(xZBTaskInfoDetailFragment));
                    xZBTaskInfoDetailFragment.c.setAnimation(xZBTaskInfoDetailFragment.e);
                    xZBTaskInfoDetailFragment.c.animate();
                }
            } else if (TextUtils.equals(action, "update_task_detail")) {
                alVar = (al) intent.getSerializableExtra("task_serial_extra");
                if (RemoteDownloadListActivity.this.b != null && RemoteDownloadListActivity.this.b.isResumed()) {
                    remoteDownloadListActivity = RemoteDownloadListActivity.this;
                    if (alVar == null) {
                        remoteDownloadListActivity.showToast("\u67e5\u627e\u4e0d\u5230\u4efb\u52a1\u4fe1\u606f");
                    } else if (remoteDownloadListActivity.b == null || remoteDownloadListActivity.b.isVisible()) {
                        RemoteDownloadListActivity.this = alVar;
                        remoteDownloadListActivity.b.a();
                    }
                }
            }
        }
    }

    public RemoteDownloadListActivity() {
        this.d = new ShowTaskDetailBroadcast();
    }

    public static void a(Context context) {
        context.startActivity(new Intent(context, RemoteDownloadListActivity.class));
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(2130968627);
        this.c = new am(this, XZBShouleiUtil.getInstance().getDefaultDevice());
        this.a = (RemoteDownloadContainerFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        if (this.a == null) {
            this.a = RemoteDownloadContainerFragment.a();
        }
        FragmentTransaction beginTransaction = this.mFragmentManager.beginTransaction();
        beginTransaction.replace(R.id.fragment_container, this.a);
        beginTransaction.commitAllowingStateLoss();
        this.b = (XZBTaskInfoDetailFragment) getSupportFragmentManager().findFragmentById(2131755210);
        this.b.getView().setVisibility(XZBDevice.Wait);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("show_task_detail");
        intentFilter.addAction("update_task_detail");
        registerReceiver(this.d, intentFilter);
        overridePendingTransition(R.anim.translate_between_interface_right_in, R.anim.translate_between_interface_left_out);
    }

    public void onBackPressed() {
        if (this.a.e) {
            this.a.b();
        } else if (this.b.isVisible()) {
            this.b.b();
        } else {
            super.onBackPressed();
        }
    }

    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.translate_between_interface_left_in, R.anim.translate_between_interface_right_out);
    }

    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(this.d);
    }
}
