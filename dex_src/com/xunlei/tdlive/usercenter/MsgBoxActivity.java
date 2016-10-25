package com.xunlei.tdlive.usercenter;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshBase.e;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.xunlei.tdlive.DispatcherActivity;
import com.xunlei.tdlive.R;
import com.xunlei.tdlive.WebBrowserActivity;
import com.xunlei.tdlive.a.j.a;
import com.xunlei.tdlive.a.p;
import com.xunlei.tdlive.base.BaseActivity;
import com.xunlei.tdlive.modal.JsonWrapper;
import com.xunlei.tdlive.user.f;
import com.xunlei.thundersniffer.sniff.sniffer.internal.server.SvrProtocol.SHubBatchQueryKeys;
import com.xunlei.xiazaibao.BuildConfig;

public class MsgBoxActivity extends BaseActivity implements OnClickListener, OnItemClickListener, e<ListView>, a {
    private PullToRefreshListView a;
    private p b;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.xllive_activity_msgbox);
        setTitle("\u6211\u7684\u4fe1\u7bb1");
        setLeftVisible(true);
        setLeftClickListener(this);
        setLeftDrawable(getResources().getDrawable(R.drawable.xllive_ic_back));
        this.a = (PullToRefreshListView) findViewById(R.id.list);
        PullToRefreshListView pullToRefreshListView = this.a;
        ListAdapter pVar = new p(this);
        this.b = pVar;
        pullToRefreshListView.setAdapter(pVar);
        this.a.setOnScrollListener(this.b);
        this.a.setOnItemClickListener(this);
        this.a.setMode(Mode.PULL_FROM_START);
        this.a.setOnRefreshListener(this);
        this.a.setRefreshing(true);
    }

    public void onClick(View view) {
        if (view == this.mTitleBarLeft) {
            finish();
        }
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        JsonWrapper jsonWrapper = (JsonWrapper) adapterView.getAdapter().getItem(i);
        if (jsonWrapper != null) {
            String string = jsonWrapper.getString("after_open", BuildConfig.VERSION_NAME);
            String string2 = jsonWrapper.getString(SHubBatchQueryKeys.url, BuildConfig.VERSION_NAME);
            if (string2.length() <= 0) {
                return;
            }
            if (string.equals("go_url")) {
                startActivity(new Intent(this, WebBrowserActivity.class).setData(Uri.parse(string2)));
            } else if (string.equals("go_app")) {
                DispatcherActivity.a(this, Uri.parse(string2), 0);
            }
        }
    }

    public <T> void a(T t, boolean z, boolean z2) {
        if (z) {
            putInt(new StringBuilder("MESSAGE_COUNT_").append(f.a().k()).toString(), this.b.getCount());
            this.a.postDelayed(new h(this), 200);
        }
    }

    public void onPullDownToRefresh(PullToRefreshBase<ListView> pullToRefreshBase) {
        this.b.a(null);
    }

    public void onPullUpToRefresh(PullToRefreshBase<ListView> pullToRefreshBase) {
    }
}
