package com.xunlei.downloadprovider.search.ui.home;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.qq.e.ads.nativ.NativeADDataRef;
import com.umeng.socialize.net.utils.SocializeProtocolConstants;
import com.xunlei.downloadprovider.R;
import com.xunlei.downloadprovider.model.protocol.report.ThunderReporter.f;
import com.xunlei.downloadprovider.model.protocol.report.ThunderReporter.g;
import com.xunlei.downloadprovider.search.a.a;
import com.xunlei.downloadprovider.search.bean.WestRankType;
import com.xunlei.downloadprovider.search.bean.b;
import com.xunlei.downloadprovider.search.ui.widget.SearchBannerLayout;
import com.xunlei.downloadprovider.util.r;
import com.xunlei.xiazaibao.BuildConfig;
import java.util.ArrayList;
import java.util.List;
import org.android.spdy.SpdyProtocol;
import org.apache.commons.logging.impl.SimpleLog;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

public final class SearchBannerAdapter extends a<b> implements a {
    ArrayList<View> a;
    SearchBannerLayout b;
    AdLoadState c;
    NativeADDataRef d;
    boolean g;
    WestRankType h;
    private int i;
    private List<b> j;
    private com.xunlei.downloadprovider.search.a k;
    private HotSearchFixFragment l;

    static /* synthetic */ class AnonymousClass_1 {
        static final /* synthetic */ int[] a;

        static {
            a = new int[WestRankType.values().length];
            try {
                a[WestRankType.HOT_SEARCH.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                a[WestRankType.MOVIE.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                a[WestRankType.TELEPLAY.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                a[WestRankType.VARIETY.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                a[WestRankType.ANIME.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
        }
    }

    public enum AdLoadState {
        NONE,
        LOADING,
        LOADED;

        static {
            NONE = new com.xunlei.downloadprovider.search.ui.home.SearchBannerAdapter.AdLoadState("NONE", 0);
            LOADING = new com.xunlei.downloadprovider.search.ui.home.SearchBannerAdapter.AdLoadState("LOADING", 1);
            LOADED = new com.xunlei.downloadprovider.search.ui.home.SearchBannerAdapter.AdLoadState("LOADED", 2);
            a = new com.xunlei.downloadprovider.search.ui.home.SearchBannerAdapter.AdLoadState[]{NONE, LOADING, LOADED};
        }
    }

    public final /* synthetic */ Object getItem(int i) {
        return a(i);
    }

    public SearchBannerAdapter(Context context, com.xunlei.downloadprovider.search.a aVar, WestRankType westRankType, HotSearchFixFragment hotSearchFixFragment) {
        super(context);
        this.j = new ArrayList();
        this.a = new ArrayList();
        this.c = AdLoadState.NONE;
        this.k = aVar;
        this.l = hotSearchFixFragment;
        this.h = westRankType;
    }

    public final int getCount() {
        return this.j.size();
    }

    public final b a(int i) {
        return (b) this.j.get(i);
    }

    public final View getView(int i, View view, ViewGroup viewGroup) {
        View inflate = LayoutInflater.from(this.e).inflate(R.layout.search_banner_item_layout, viewGroup, false);
        TextView textView = (TextView) inflate.findViewById(R.id.item_title);
        TextView textView2 = (TextView) inflate.findViewById(R.id.tv_order);
        b a = a(i);
        textView.setText(a.a);
        Drawable drawable;
        if (a.b) {
            drawable = this.e.getResources().getDrawable(R.drawable.search_mask_hot);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            textView.setCompoundDrawables(null, null, drawable, null);
            if (textView2 != null) {
                textView2.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
            }
        } else if (a.c) {
            drawable = this.e.getResources().getDrawable(R.drawable.search_mask_recommend);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            textView.setCompoundDrawables(null, null, drawable, null);
            if (textView2 != null) {
                textView2.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
            }
        } else if (!(a.d || textView2 == null)) {
            Drawable drawable2;
            int indexOf = this.f.indexOf(a) + 1;
            switch (indexOf) {
                case SimpleLog.LOG_LEVEL_TRACE:
                    drawable2 = this.e.getResources().getDrawable(R.drawable.hot1);
                    break;
                case SimpleLog.LOG_LEVEL_DEBUG:
                    drawable2 = this.e.getResources().getDrawable(R.drawable.hot2);
                    break;
                case MqttConnectOptions.MQTT_VERSION_3_1:
                    drawable2 = this.e.getResources().getDrawable(R.drawable.hot3);
                    break;
                default:
                    drawable2 = this.e.getResources().getDrawable(R.drawable.hot4);
                    break;
            }
            textView2.setText(String.valueOf(indexOf));
            textView2.setBackgroundDrawable(drawable2);
            textView2.setVisibility(0);
        }
        this.a.add(inflate);
        return inflate;
    }

    public final void notifyDataSetChanged() {
        this.a.clear();
        super.notifyDataSetChanged();
    }

    public final void a(List<b> list) {
        int i = 0;
        super.a(list);
        this.i = 0;
        this.j.clear();
        if (list == null || list.isEmpty()) {
            e();
            return;
        }
        int min = Math.min(SpdyProtocol.PUBKEY_SEQ_ADASH, c());
        while (i < min) {
            this.j.add(list.get(i));
            i++;
        }
        this.i += min;
        if (this.d != null) {
            b(this.d);
        } else {
            notifyDataSetChanged();
        }
    }

    public final void b() {
        WestRankType westRankType = this.h;
        Object obj = BuildConfig.VERSION_NAME;
        switch (AnonymousClass_1.a[westRankType.ordinal()]) {
            case SimpleLog.LOG_LEVEL_TRACE:
                obj = "hotword";
                break;
            case SimpleLog.LOG_LEVEL_DEBUG:
                obj = "movie";
                break;
            case MqttConnectOptions.MQTT_VERSION_3_1:
                obj = "teleplay";
                break;
            case MqttConnectOptions.MQTT_VERSION_3_1_1:
                obj = "comedy";
                break;
            case SimpleLog.LOG_LEVEL_ERROR:
                obj = "anime";
                break;
        }
        if (!TextUtils.isEmpty(obj)) {
            f.c(obj, "change", BuildConfig.VERSION_NAME);
        }
        int c = c();
        if (c > 8) {
            this.j.clear();
            int i = this.i;
            this.i += 8;
            if (this.i > c) {
                a(i, c);
                this.i -= c;
                a(0, this.i);
            } else {
                a(i, this.i);
            }
            notifyDataSetChanged();
            e();
            if (this.c != AdLoadState.LOADING && com.xunlei.c.a.b.a(this.e)) {
                e();
                this.c = AdLoadState.LOADING;
                com.xunlei.downloadprovider.search.a aVar = this.k;
                r.a aVar2 = r.c().e;
                if (aVar2 != null) {
                    r.a.a a = aVar2.a();
                    if (aVar2.c() && a.d == 1) {
                        c = 1;
                        if (c != 0 && aVar.b && this != null) {
                            synchronized (com.xunlei.downloadprovider.search.a.class) {
                                if (!aVar.c.contains(this)) {
                                    aVar.c.push(this);
                                }
                            }
                            aVar.a.loadAD(1);
                            return;
                        }
                        return;
                    }
                }
                c = 0;
                if (c != 0) {
                }
            }
        }
    }

    private void a(int i, int i2) {
        while (i < i2) {
            this.j.add(this.f.get(i));
            i++;
        }
    }

    public final void a(NativeADDataRef nativeADDataRef) {
        if (nativeADDataRef != null) {
            this.c = AdLoadState.LOADED;
            b(nativeADDataRef);
        }
    }

    private void b(NativeADDataRef nativeADDataRef) {
        this.d = nativeADDataRef;
        if (this.j.size() > 3) {
            b bVar = new b(nativeADDataRef.getTitle());
            bVar.c = true;
            this.j.set(MqttConnectOptions.MQTT_VERSION_3_1, bVar);
            notifyDataSetChanged();
            this.b.postDelayed(new g(this), 100);
        }
    }

    public final void a() {
        this.d = null;
        this.c = AdLoadState.NONE;
        this.g = false;
    }

    public final void e() {
        this.d = null;
        this.c = AdLoadState.NONE;
        this.g = false;
    }

    public final void a(View view) {
        if (this.d != null) {
            WestRankType westRankType = this.h;
            String str = SocializeProtocolConstants.PROTOCOL_KEY_TENCENT;
            String str2 = "adv_hotsearch_click";
            g a = g.a("android_advertise", str2, str2);
            a.a("cardid", westRankType.getName(), MqttConnectOptions.MQTT_VERSION_3_1);
            a.a("ad_id", "ad_tencent", MqttConnectOptions.MQTT_VERSION_3_1);
            a.b("positionid", 4);
            a.a("ad_type", str, MqttConnectOptions.MQTT_VERSION_3_1);
            f.a(a);
            this.d.onClicked(view);
        }
    }
}
