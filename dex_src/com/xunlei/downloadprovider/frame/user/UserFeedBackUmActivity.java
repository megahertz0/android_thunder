package com.xunlei.downloadprovider.frame.user;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.umeng.fb.FeedbackAgent;
import com.umeng.fb.model.Conversation;
import com.umeng.fb.model.Reply;
import com.xunlei.xiazaibao.R;
import java.util.ArrayList;
import java.util.List;

public class UserFeedBackUmActivity extends Activity {
    private Conversation a;
    private Context b;
    private a c;
    private Button d;
    private EditText e;
    private PullToRefreshListView f;
    private final int g;
    private final int h;
    private final int i;
    private List<Reply> j;
    private int k;
    @SuppressLint({"HandlerLeak"})
    private Handler l;

    public UserFeedBackUmActivity() {
        this.g = 2;
        this.h = 0;
        this.i = 1;
        this.j = new ArrayList();
        this.k = 0;
        this.l = new bq(this);
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(2130968750);
        this.b = this;
        ((TextView) findViewById(R.id.titlebar_title)).setText("\u610f\u89c1\u53cd\u9988");
        TextView textView = (TextView) findViewById(com.xunlei.tdlive.R.id.titlebar_right);
        textView.setVisibility(0);
        textView.setTextColor(getResources().getColor(com.xunlei.tdlive.R.color.global_text_color_2));
        textView.setText("\u5e38\u89c1\u95ee\u9898");
        textView.setOnClickListener(new br(this));
        findViewById(R.id.titlebar_left).setOnClickListener(new bs(this));
        this.a = new FeedbackAgent(this).getDefaultConversation();
        this.f = (PullToRefreshListView) findViewById(2131755876);
        this.d = (Button) findViewById(2131755873);
        this.e = (EditText) findViewById(2131755874);
        this.d.setOnClickListener(new bt(this));
        this.f.setOnRefreshListener(new bu(this));
        this.f.setMode(Mode.PULL_FROM_START);
        int size = this.a.getReplyList().size();
        if (size <= 10) {
            this.j.addAll(this.a.getReplyList());
        } else {
            for (int i = size - 10; i < size; i++) {
                this.j.add(this.a.getReplyList().get(i));
            }
        }
        this.c = new a(this);
        this.f.setAdapter(this.c);
        if (this.c.getCount() > 6) {
            this.f.setSelection(this.c.getCount() - 1);
        }
        a(1);
    }

    private void a(int i) {
        this.a.sync(new bv(this, i));
    }

    public void onActivityResult(int i, int i2, Intent intent) {
    }
}
