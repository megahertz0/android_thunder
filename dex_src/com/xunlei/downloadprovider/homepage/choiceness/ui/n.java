package com.xunlei.downloadprovider.homepage.choiceness.ui;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.ListView;
import com.xunlei.downloadprovider.ad.home.ui.ADImageItemview;
import com.xunlei.downloadprovider.ad.home.ui.ADLongVideoItem;
import com.xunlei.downloadprovider.ad.home.ui.ADShortVideoItemView;
import com.xunlei.downloadprovider.ad.home.ui.i;
import com.xunlei.downloadprovider.homepage.choiceness.ChoicenessReporter;
import com.xunlei.downloadprovider.homepage.choiceness.ui.a.f;
import com.xunlei.downloadprovider.player.ab;
import com.xunlei.downloadprovider.player.q;
import com.xunlei.xllib.R;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.android.spdy.SpdyProtocol;
import org.apache.commons.logging.impl.SimpleLog;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

// compiled from: HomeChoicenessAdapter.java
public class n extends f<com.xunlei.downloadprovider.homepage.choiceness.a.a.a> {
    private static final String g;
    com.xunlei.downloadprovider.homepage.a a;
    public boolean b;
    public Set<a> c;
    private ListView h;
    private com.xunlei.downloadprovider.player.a.a i;

    // compiled from: HomeChoicenessAdapter.java
    public static interface a {
        void a();

        void b();
    }

    static {
        g = n.class.getSimpleName();
    }

    public n(Context context, ListView listView, com.xunlei.downloadprovider.homepage.a aVar, com.xunlei.downloadprovider.player.a.a aVar2) {
        super(context);
        this.b = true;
        this.c = null;
        this.h = listView;
        this.a = aVar;
        this.i = aVar2;
        this.c = new HashSet();
    }

    public int getViewTypeCount() {
        return R.styleable.Toolbar_navigationIcon;
    }

    public final View a(int i) {
        switch (i) {
            case MqttConnectOptions.MQTT_VERSION_DEFAULT:
                return new com.xunlei.downloadprovider.ad.home.ui.a(this.e, this.i);
            case SimpleLog.LOG_LEVEL_TRACE:
            case SpdyProtocol.PUBKEY_SEQ_OPEN:
            case R.styleable.Toolbar_titleMargins:
            case R.styleable.Toolbar_titleMarginEnd:
                return new ChoicenessShortVideoItemView(this.e);
            case SimpleLog.LOG_LEVEL_DEBUG:
                return new ChoicenessLongVideoItem(this.e);
            case MqttConnectOptions.MQTT_VERSION_3_1:
                return new ChoicenessMovieSetItemView(this.e);
            case MqttConnectOptions.MQTT_VERSION_3_1_1:
            case R.styleable.Toolbar_titleMarginStart:
                return new ChoicenessImageItemView(this.e);
            case SimpleLog.LOG_LEVEL_ERROR:
                return new ADShortVideoItemView(this.e);
            case SimpleLog.LOG_LEVEL_FATAL:
                return new ADImageItemview(this.e);
            case SimpleLog.LOG_LEVEL_OFF:
            case SpdyProtocol.PUBKEY_SEQ_ADASH:
                return new ChoicenessVideoPlayItemView(this.e, this.i, this);
            case SpdyProtocol.PUBKEY_PSEQ_ADASH:
                return new ADLongVideoItem(this.e);
            case SpdyProtocol.PUBKEY_PSEQ_OPEN:
                return new ChoicenessShortVideoItemViewPoster(this.e);
            case R.styleable.Toolbar_titleMarginTop:
                return new ChoicenessShortVideoItemView3(this.e);
            case SpdyProtocol.CUSTOM:
                return new i(this.e, this.i);
            case R.styleable.Toolbar_maxButtonHeight:
                return new ChoicenessLivestreamItemView(this.e);
            case R.styleable.Toolbar_collapseIcon:
                return new ChoicenessVideoPlayItemView(this.e, this.i, this);
            default:
                return new ChoicenessShortVideoItemView(this.e);
        }
    }

    public final synchronized void a(String str) {
        if (!TextUtils.isEmpty(str)) {
            List<com.xunlei.downloadprovider.homepage.choiceness.a.a.a> list = this.f;
            for (com.xunlei.downloadprovider.homepage.choiceness.a.a.a aVar : list) {
                if (str.equals(aVar.d)) {
                    list.remove(aVar);
                    break;
                }
            }
            notifyDataSetChanged();
        }
    }

    protected final void a(int i, boolean z) {
        super.a(i, z);
        com.xunlei.downloadprovider.homepage.choiceness.a.a.a aVar = (com.xunlei.downloadprovider.homepage.choiceness.a.a.a) getItem(i);
        if (aVar != null) {
            ChoicenessReporter.a(i, z, aVar);
        }
    }

    public final void a(boolean z) {
        super.a(z);
        this.b = true;
        if (!z) {
            b(false);
        }
        for (a aVar : this.c) {
            aVar.a();
        }
    }

    public final void a() {
        super.a();
        if (this.b) {
            q.a().a("home_player");
        } else {
            ab b = q.a().b("home_player");
            if (b != null) {
                b.c(false);
            }
        }
        ChoicenessReporter.a();
        ChoicenessReporter.b();
        for (a aVar : this.c) {
            aVar.b();
        }
    }

    protected final void b() {
        b(true);
    }

    private void b(boolean z) {
        int firstVisiblePosition = this.h.getFirstVisiblePosition() - this.h.getHeaderViewsCount();
        int lastVisiblePosition = this.h.getLastVisiblePosition() - this.h.getHeaderViewsCount();
        int count = getCount();
        int i = firstVisiblePosition;
        while (i <= lastVisiblePosition) {
            if (i >= 0 && i < count) {
                ChoicenessReporter.a(z, (com.xunlei.downloadprovider.homepage.choiceness.a.a.a) getItem(i));
            }
            i++;
        }
    }
}
