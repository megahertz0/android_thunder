package com.xunlei.tdlive.a;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Handler;
import android.text.Html;
import android.text.Spannable;
import android.text.TextUtils.TruncateAt;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.AbsListView.LayoutParams;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.xunlei.tdlive.im.ChatMessage;
import com.xunlei.tdlive.util.n;
import com.xunlei.tdlive.util.v;
import com.xunlei.xiazaibao.BuildConfig;
import com.xunlei.xllib.R;
import org.apache.commons.logging.impl.SimpleLog;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

@SuppressLint({"HandlerLeak"})
// compiled from: RecordAdapter.java
public class x extends ArrayAdapter<a> {
    static long a;
    private int b;
    private ListView c;
    private Handler d;
    private long e;
    private OnScrollListener f;

    // compiled from: RecordAdapter.java
    public class a {
        public ChatMessage a;
        private long c;
        private long d;

        public a(ChatMessage chatMessage) {
            long j = a;
            a = 1 + j;
            this.c = j;
            this.a = chatMessage;
            b();
        }

        public void a() {
            this.d = -1;
        }

        public void b() {
            this.d = System.currentTimeMillis();
        }

        public String toString() {
            return this.a.user.nickname + " " + this.a.content;
        }

        public long c() {
            return this.d;
        }

        public long d() {
            return this.d + 60000;
        }

        public long e() {
            return (this.d + 60000) + 4000;
        }

        public int f() {
            long currentTimeMillis = System.currentTimeMillis();
            if (this.d < 0) {
                return MqttConnectOptions.MQTT_VERSION_3_1;
            }
            if (currentTimeMillis >= e()) {
                return SimpleLog.LOG_LEVEL_DEBUG;
            }
            return currentTimeMillis >= d() ? 1 : 0;
        }
    }

    public x(Context context, int i, ListView listView) {
        super(context, 0, 0);
        this.d = new Handler();
        this.e = 0;
        this.f = new ab(this);
        c();
        setNotifyOnChange(false);
        this.b = i;
        this.c = listView;
        d();
    }

    public void a() {
        if (this.d == null) {
            this.d = new Handler();
        }
    }

    public void b() {
        if (this.d != null) {
            this.d.removeCallbacksAndMessages(null);
            this.d = null;
        }
    }

    public void clear() {
        super.clear();
        c();
    }

    public void a(ChatMessage chatMessage) {
        super.add(new a(chatMessage));
        if (getCount() > this.b) {
            remove(getItem(0));
        }
        notifyDataSetChanged();
    }

    private void c() {
        for (int i = 0; i < 10; i++) {
            ChatMessage chatMessage = new ChatMessage();
            chatMessage.flag = 1000;
            a aVar = new a(chatMessage);
            aVar.a();
            super.add(aVar);
        }
        notifyDataSetChanged();
    }

    public boolean hasStableIds() {
        return true;
    }

    public long getItemId(int i) {
        return ((a) getItem(i)).c;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        TextView textView = (TextView) view;
        if (textView == null) {
            view = new TextView(viewGroup.getContext());
            view.setTextSize(16.0f);
            view.setMaxLines(MqttConnectOptions.MQTT_VERSION_3_1_1);
            view.setShadowLayer(1.5f, 1.0f, 1.0f, -16777216);
            view.setEllipsize(TruncateAt.END);
            view.setTypeface(Typeface.DEFAULT_BOLD);
            view.setLayoutParams(new LayoutParams(-2, -1));
        }
        a aVar = (a) getItem(i);
        a((View) textView, aVar);
        String a = v.a(aVar.a.user.nickname, R.styleable.Toolbar_titleMarginEnd);
        if (!(aVar.a.user == null || aVar.a.user.level == null || aVar.a.user.level.icon == null || aVar.a.user.level.icon.length() <= 0)) {
            a = new StringBuilder("[GRADE_").append(aVar.a.user.level.getIconFullPath()).append("] ").append(a).toString();
        }
        n.a(textView, a(aVar.a.flag, a, aVar.a.color1, aVar.a.content, aVar.a.color2));
        return textView;
    }

    private Spannable a(int i, String str, String str2, String str3, String str4) {
        String str5;
        if (str != null && str.length() > 0) {
            str = str + " ";
        } else if (str == null) {
            str = BuildConfig.VERSION_NAME;
        }
        if (str3 == null) {
            str3 = BuildConfig.VERSION_NAME;
        }
        if (i >= 1000) {
            if (str2 == null || str2.length() <= 0) {
                str5 = "#fc687c";
            } else {
                str5 = str2;
            }
            if (str4 == null || str4.length() <= 0) {
                str4 = "#fc687c";
            }
        } else {
            if (str2 == null || str2.length() <= 0) {
                str5 = "#e9aa39";
            } else {
                str5 = str2;
            }
            if (str4 == null || str4.length() <= 0) {
                str4 = "#ffffff";
            }
        }
        return (Spannable) Html.fromHtml(new StringBuilder("<font color='").append(str5).append("'>").append(str).append("</font><font color='").append(str4).append("'>").append(str3).append("</font>").toString());
    }

    private void a(View view, long j) {
        if (j >= 0) {
            Animation alphaAnimation = new AlphaAnimation(1.0f, 0.0f);
            alphaAnimation.setDuration(j);
            alphaAnimation.setRepeatCount(0);
            alphaAnimation.setAnimationListener(new y(this, view));
            view.clearAnimation();
            view.startAnimation(alphaAnimation);
        }
    }

    private void d() {
        this.c.setOnScrollListener(this.f);
    }

    private void a(int i, int i2) {
        for (int i3 = i; i3 <= i2; i3++) {
            b(this.c.getChildAt(i3 - i), (a) getItem(i3));
        }
    }

    private void a(View view) {
        if (view != null) {
            Animation animation = view.getAnimation();
            if (animation != null) {
                animation.setAnimationListener(null);
                view.clearAnimation();
            }
        }
    }

    private void b(View view) {
        if (view != null) {
            Runnable runnable = (Runnable) view.getTag(com.xunlei.tdlive.R.id.message);
            if (runnable != null) {
                view.removeCallbacks(runnable);
            }
        }
    }

    private void a(View view, a aVar) {
        b(view);
        a(view);
        int f = aVar.f();
        if (f == 0) {
            view.setVisibility(0);
            long currentTimeMillis = 60000 - (System.currentTimeMillis() - aVar.c());
            Runnable zVar = new z(this, view);
            view.postDelayed(zVar, currentTimeMillis);
            view.setTag(com.xunlei.tdlive.R.id.message, zVar);
        } else if (f == 1) {
            view.setVisibility(0);
            a(view, 4000 - (System.currentTimeMillis() - aVar.d()));
        } else if (f == 2) {
            view.setVisibility(MqttConnectOptions.MQTT_VERSION_3_1_1);
        } else if (f == 3) {
            view.setVisibility(MqttConnectOptions.MQTT_VERSION_3_1_1);
        }
    }

    private void b(View view, a aVar) {
        b(view);
        a(view);
        switch (aVar.f()) {
            case MqttConnectOptions.MQTT_VERSION_DEFAULT:
            case SimpleLog.LOG_LEVEL_TRACE:
            case SimpleLog.LOG_LEVEL_DEBUG:
                aVar.b();
                view.setVisibility(0);
                Runnable aaVar = new aa(this, view);
                view.postDelayed(aaVar, 60000);
                view.setTag(com.xunlei.tdlive.R.id.message, aaVar);
            case MqttConnectOptions.MQTT_VERSION_3_1:
                view.setVisibility(MqttConnectOptions.MQTT_VERSION_3_1_1);
            default:
                break;
        }
    }

    public void a(boolean z) {
        Object obj = 1;
        if (!(z || this.e == 0 || System.currentTimeMillis() - this.e > 5000)) {
            obj = null;
        }
        if (obj != null) {
            this.c.setSelection(getCount() - 1);
        }
    }

    static {
        a = 1000;
    }
}
