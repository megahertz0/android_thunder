package com.xunlei.downloadprovider.vod;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import com.xunlei.downloadprovider.app.BrothersApplication;
import com.xunlei.downloadprovider.commonview.XLToast;
import com.xunlei.downloadprovider.commonview.XLToast.XLToastType;
import com.xunlei.downloadprovider.download.center.DownloadCenterActivityFragment;
import com.xunlei.downloadprovider.member.login.LoginHelper;
import com.xunlei.downloadprovider.member.login.LoginHelper.c;
import com.xunlei.downloadprovider.member.login.ui.LoginActivity;
import com.xunlei.downloadprovider.vod.a.a;
import com.xunlei.downloadprovider.vod.b.b;
import com.xunlei.downloadprovider.vod.protocol.VodSourceType;
import com.xunlei.downloadprovider.vod.protocol.VodVideoFormat;
import com.xunlei.downloadprovider.vod.protocol.h;
import com.xunlei.downloadprovider.vod.protocol.i;
import com.xunlei.mediaserver.MediaServer;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;

public class VodUtil {
    public static boolean a;
    public static int b;
    public static ap c;
    public static Bundle d;
    private static final String e;
    private static final VodUtil f;

    public enum SharpnessValue {
        flv(1),
        mp4(2),
        hd2(3),
        hd3(4);
        public int value;

        static {
            flv = new com.xunlei.downloadprovider.vod.VodUtil.SharpnessValue("flv", 0, 1);
            mp4 = new com.xunlei.downloadprovider.vod.VodUtil.SharpnessValue("mp4", 1, 2);
            hd2 = new com.xunlei.downloadprovider.vod.VodUtil.SharpnessValue("hd2", 2, 3);
            hd3 = new com.xunlei.downloadprovider.vod.VodUtil.SharpnessValue("hd3", 3, 4);
            a = new com.xunlei.downloadprovider.vod.VodUtil.SharpnessValue[]{flv, mp4, hd2, hd3};
        }

        private SharpnessValue(int i) {
            this.value = i;
        }

        public final int getValue() {
            return this.value;
        }

        public static com.xunlei.downloadprovider.vod.VodUtil.SharpnessValue mapValue(int i) {
            com.xunlei.downloadprovider.vod.VodUtil.SharpnessValue[] values = values();
            int length = values.length;
            for (int i2 = 0; i2 < length; i2++) {
                com.xunlei.downloadprovider.vod.VodUtil.SharpnessValue sharpnessValue = values[i2];
                if (sharpnessValue.getValue() == i) {
                    return sharpnessValue;
                }
            }
            return null;
        }
    }

    static {
        e = VodUtil.class.getSimpleName();
        f = new VodUtil();
        a = false;
        b = 0;
    }

    private VodUtil() {
    }

    public static VodUtil a() {
        return f;
    }

    private static void c(Context context, ap apVar, Bundle bundle) {
        if (context instanceof Context) {
            if (apVar.c() == 1) {
                XLToast.c(context, XLToastType.XLTOAST_TYPE_NONE, context.getResources().getString(2131233111));
            }
            Intent intent = new Intent();
            intent.setClass(context, VodPlayerActivity.class);
            intent.putExtra(VodPlayerActivity.INTENT_KEY_VOD_PLAYER_PARAMS, apVar);
            intent.setFlags(268435456);
            if (bundle != null) {
                intent.putExtras(bundle);
            }
            context.startActivity(intent);
        }
    }

    public static void a(Context context, i iVar) {
        a(context, ao.a(iVar), null);
    }

    public static void a(a aVar) {
        i iVar = new i();
        iVar.g = VodVideoFormat.flv;
        iVar.h = VodSourceType.uc_cloud;
        iVar.a = aVar.c;
        iVar.e = aVar.b;
        iVar.f = 1;
        iVar.i = aVar;
        a(BrothersApplication.a(), iVar);
    }

    public static void a(Context context, i iVar, Bundle bundle) {
        a(context, ao.a(iVar), bundle);
    }

    public static void a(Context context, ap apVar, Bundle bundle) {
        boolean z = true;
        DownloadCenterActivityFragment.a = true;
        c = apVar;
        d = bundle;
        if ((apVar instanceof ap) && (apVar.a() instanceof h)) {
            new StringBuilder("params.getCurrentEpisode().mSliced = ").append(apVar.a().j).append(" , params.getCurrentEpisode().mVodType = ").append(apVar.a().o);
            if (apVar.a().j || apVar.a().o == 2 || apVar.a().o == 1 || apVar.b()) {
                z = false;
            }
        }
        if (z) {
            LoginHelper.a();
            if (!LoginHelper.c()) {
                LoginHelper a = LoginHelper.a();
                c bdVar = new bd(context, apVar, bundle);
                a.o = false;
                a.v = bdVar;
                Intent intent = new Intent(context, LoginActivity.class);
                intent.putExtra("login_from", context.getClass().getSimpleName() + "\u4e91\u64ad");
                intent.putExtra("login_type", XZBDevice.DOWNLOAD_LIST_ALL);
                intent.setFlags(268435456);
                if (bundle != null) {
                    intent.putExtras(bundle);
                }
                context.startActivity(intent);
                return;
            }
        }
        c(context, apVar, bundle);
    }

    public static void a(Context context, ap apVar) {
        a(context, apVar, null);
    }

    public static boolean a(VodSourceType vodSourceType) {
        return vodSourceType == VodSourceType.download_detail || vodSourceType == VodSourceType.local_appinner || vodSourceType == VodSourceType.local_system || vodSourceType == VodSourceType.space_his;
    }

    public static boolean b(VodSourceType vodSourceType) {
        return vodSourceType != null && vodSourceType == VodSourceType.local_appinner;
    }

    public static void a(Context context, String str) {
        a(context, str, "old_detail_other", VodSourceType.local_appinner);
    }

    public static void a(Context context, String str, String str2, VodSourceType vodSourceType) {
        ap a = ao.a(str, vodSourceType);
        a.d = new com.xunlei.downloadprovider.vod.c.a(str2, "native");
        c(context, a, null);
    }

    public static void a(Context context, String str, long j) {
        ap a = ao.a(str, VodSourceType.download_detail);
        a.d = new com.xunlei.downloadprovider.vod.c.a("download_detail", "native");
        Bundle bundle = new Bundle();
        bundle.putLong("downloaded_taskId", j);
        c(context, a, bundle);
    }

    public static void a(Context context, String str, String str2, long j, int i, String str3, String str4, String str5) {
        try {
            String path = new URL(str).getPath();
            if (TextUtils.isEmpty(str2) && path.indexOf(R.styleable.AppCompatTheme_spinnerDropDownItemStyle) >= 0) {
                str2 = path.substring(path.indexOf(R.styleable.AppCompatTheme_spinnerDropDownItemStyle));
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        ap apVar = new ap();
        h hVar = new h();
        hVar.c = str;
        hVar.q = null;
        hVar.o = 2;
        hVar.d = str3;
        hVar.e = str4;
        if (str2 == null || !str2.contains(".mp4")) {
            hVar.x = VodVideoFormat.flv;
        } else {
            hVar.x = VodVideoFormat.mp4;
        }
        hVar.a = str2;
        Serializable aVar = new a(str, j, (long) i, str3, str4);
        apVar.b = VodSourceType.local_appinner;
        apVar.a(hVar);
        apVar.d = new com.xunlei.downloadprovider.vod.c.a(str5, "bxbb");
        Bundle bundle = new Bundle();
        bundle.putSerializable(VodPlayerActivity.INTENT_KEY_DOWNLOAD_VOD_PARAMS, aVar);
        a(context, apVar, bundle);
    }

    public static void b(Context context, String str) {
        a(context, ao.b(str), null);
    }

    @Deprecated
    public static String a(String str, int i) {
        StringBuilder stringBuilder = new StringBuilder();
        if (!TextUtils.isEmpty(str)) {
            String substring;
            if (str.contains("btih:")) {
                substring = str.substring(str.lastIndexOf("btih:") + 5);
            } else {
                substring = str;
            }
            if (!(TextUtils.isEmpty(substring) || substring.startsWith("bt://"))) {
                stringBuilder.append("bt://");
            }
            stringBuilder.append(substring);
            if (i >= 0) {
                stringBuilder.append("/");
                stringBuilder.append(i);
            }
        }
        new StringBuilder("func createBTRequestUrl : baseUrl = ").append(str).append(" , index = ").append(i).append(" , result = ").append(stringBuilder.toString());
        return stringBuilder.toString();
    }

    public static void a(h hVar, VodSourceType vodSourceType) {
        if (hVar != null) {
            new StringBuilder("func savePlayHistory : url = ").append(hVar.c).append(" , pos = ").append(hVar.s).append(" , tag = ").append(hVar.l);
            b a = b.a();
            if (hVar != null) {
                a.b.execute(new com.xunlei.downloadprovider.vod.b.c(a, hVar, vodSourceType));
            }
        }
    }

    public static int a(h hVar) {
        b.a aVar;
        int i = 0;
        b a = b.a();
        if (hVar == null) {
            aVar = null;
        } else if (hVar.j) {
            aVar = a.a.b(hVar.c);
        } else {
            aVar = a.a.a(b.a(hVar.c));
        }
        if (aVar != null) {
            i = aVar.k;
        }
        new StringBuilder("func loadPlayHistory : result = ").append(i).append(" , EpisodeInfo = ").append(hVar).append(" , record = ").append(aVar);
        return i;
    }

    static String b(h hVar) {
        if (!(hVar instanceof h) || TextUtils.isEmpty(hVar.p) || b <= 0) {
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("sessionid=").append(LoginHelper.a().k).append(";userid=").append(String.valueOf(LoginHelper.a().j)).append(";peerid=").append(com.xunlei.downloadprovider.a.b.d()).append(";businesstype=40;");
        return MediaServer.getInstance(BrothersApplication.a().getApplicationContext(), XZBDevice.Success, com.xunlei.downloadprovider.a.b.d()).getVodPlayURL(hVar.p, stringBuilder.toString(), hVar.e, hVar.d, hVar.f);
    }

    static String c(h hVar) {
        if (!(hVar instanceof h) || hVar.t == null || hVar.t.size() <= 0) {
            return null;
        }
        Iterator it;
        c cVar = new c();
        for (h.b bVar : hVar.t) {
            String str = bVar.a;
            long j = (long) bVar.b;
            if (str != null && str.length() >= 0 && j >= 0) {
                cVar.a.add(new c.a(str, j));
            }
        }
        String toString = new StringBuilder("#PLSEXTM3U\n#EXT-X-TARGETDURATION:").append(cVar.a()).append("\n#EXT-X-DISCONTINUITY\n").toString();
        if (cVar.a.size() <= 0) {
            return com.umeng.a.d;
        }
        it = cVar.a.iterator();
        String str2 = toString;
        while (it.hasNext()) {
            c.a aVar = (c.a) it.next();
            str2 = (str2 + "#EXTINF:" + aVar.a + "\n") + aVar.b + "\n";
        }
        return str2 + "#EXT-X-ENDLIST\n";
    }

    public static void b(Context context, ap apVar) {
        a(context, apVar, null);
    }
}
