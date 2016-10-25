package com.inmobi.ads;

import android.graphics.Point;
import android.webkit.URLUtil;
import anet.channel.util.HttpConstant;
import com.alipay.sdk.packet.d;
import com.baidu.mobads.interfaces.utils.IXAdSystemUtils;
import com.inmobi.ads.InMobiStrandPositioning.InMobiClientPositioning;
import com.inmobi.ads.NativeStrandAsset.AssetInteractionMode;
import com.inmobi.ads.NativeStrandAsset.AssetType;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import com.inmobi.commons.core.utilities.info.DisplayInfo;
import com.sina.weibo.sdk.constant.WBConstants;
import com.tencent.open.GameAppOperation;
import com.tencent.open.SocialConstants;
import com.umeng.a;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.android.spdy.SpdyAgent;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

// compiled from: NativeStrandDataModel.java
class x {
    private static final String a;
    private JSONObject b;
    private String c;
    private JSONObject d;
    private v e;

    // compiled from: NativeStrandDataModel.java
    /* synthetic */ class AnonymousClass_1 {
        static final /* synthetic */ int[] a;

        static {
            a = new int[AssetType.values().length];
            try {
                a[AssetType.ASSET_TYPE_CONTAINER.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                a[AssetType.ASSET_TYPE_TEXT.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                a[AssetType.ASSET_TYPE_ICON.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                a[AssetType.ASSET_TYPE_IMAGE.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                a[AssetType.ASSET_TYPE_RATING.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                a[AssetType.ASSET_TYPE_CTA.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
        }
    }

    static {
        a = x.class.getSimpleName();
    }

    x() {
    }

    public x(JSONObject jSONObject) {
        this.b = jSONObject;
        g();
    }

    public v a() {
        return this.e;
    }

    public JSONObject b() {
        return this.d;
    }

    public v c() {
        Iterator it = this.e.iterator();
        while (it.hasNext()) {
            NativeStrandAsset nativeStrandAsset = (NativeStrandAsset) it.next();
            if (nativeStrandAsset.c().equalsIgnoreCase("card_scrollable")) {
                return (v) nativeStrandAsset;
            }
        }
        return null;
    }

    public int d() {
        if (this.e == null) {
            return 0;
        }
        Iterator it = this.e.iterator();
        while (it.hasNext()) {
            NativeStrandAsset nativeStrandAsset = (NativeStrandAsset) it.next();
            if (nativeStrandAsset.c().equalsIgnoreCase("card_scrollable")) {
                return ((v) nativeStrandAsset).n();
            }
        }
        return 0;
    }

    public v a(int i) {
        Iterator it = this.e.iterator();
        while (it.hasNext()) {
            NativeStrandAsset nativeStrandAsset = (NativeStrandAsset) it.next();
            if (nativeStrandAsset.c().equalsIgnoreCase("card_scrollable")) {
                return i >= ((v) nativeStrandAsset).n() ? null : (v) ((v) nativeStrandAsset).a(i);
            }
        }
        return null;
    }

    public v a(NativeStrandAsset nativeStrandAsset) {
        if ((nativeStrandAsset instanceof v) && a((v) nativeStrandAsset)) {
            return (v) nativeStrandAsset;
        }
        for (nativeStrandAsset = (v) nativeStrandAsset.k(); r2 != null; nativeStrandAsset = (v) r2.k()) {
            if (a(r2)) {
                return r2;
            }
        }
        return null;
    }

    public int e() {
        return this.e == null ? 0 : this.e.b().a().x;
    }

    public int f() {
        return this.e == null ? 0 : this.e.b().a().y;
    }

    private boolean a(v vVar) {
        return "card_container".equalsIgnoreCase(vVar.c());
    }

    void g() {
        try {
            this.c = String.valueOf(this.b.getDouble(GameAppOperation.QQFAV_DATALINE_VERSION));
            this.d = this.b.getJSONObject("styleRefs");
            this.e = (v) a(this.b.getJSONObject("rootContainer"), AssetType.ASSET_TYPE_CONTAINER, "/rootContainer");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    boolean h() {
        if (a() == null) {
            Logger.a(InternalLogLevel.INTERNAL, a, "Invalid Data Model: No Root Container");
            return false;
        } else if (c() == null) {
            Logger.a(InternalLogLevel.INTERNAL, a, "No Card Scrollable in the data model");
            return true;
        } else if (d() > 0) {
            return true;
        } else {
            Logger.a(InternalLogLevel.INTERNAL, a, "Invalid Data Model: No Cards in Card Scrollable");
            return false;
        }
    }

    private NativeStrandAsset a(JSONObject jSONObject, AssetType assetType, String str) {
        String c = c(jSONObject);
        JSONObject e = e(jSONObject);
        if (a(e, assetType)) {
            Point f = f(jSONObject);
            Point g = g(jSONObject);
            ai[] a = a(jSONObject);
            try {
                t a2;
                NativeStrandAsset vVar;
                String string;
                AssetInteractionMode assetInteractionMode;
                String a3;
                switch (AnonymousClass_1.a[assetType.ordinal()]) {
                    case SpdyAgent.ACCS_ONLINE_SERVER:
                        a2 = a(f, g, e);
                        if (a == null || a.length == 0) {
                            vVar = new v(c, a2, jSONObject);
                        } else {
                            vVar = new v(c, a2, a, jSONObject);
                        }
                        vVar.a(str);
                        JSONArray jSONArray = jSONObject.getJSONArray("assetValue");
                        for (int i = 0; i < jSONArray.length(); i++) {
                            String str2 = str + ".assetValue[" + i + "]";
                            JSONObject jSONObject2 = jSONArray.getJSONObject(i);
                            NativeStrandAsset a4 = a(jSONObject2, d(d(jSONObject2)), str2);
                            if (a4 == null) {
                                Logger.a(InternalLogLevel.INTERNAL, a, new StringBuilder("Cannot build asset from JSON: ").append(jSONObject2).toString());
                            } else {
                                a4.a(str2);
                                a4.a(vVar);
                                vVar.b(a4);
                            }
                        }
                        return vVar;
                    case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                        vVar = new ah(c, b(f, g, e), h(jSONObject).getString(0));
                        vVar.a(str);
                        return vVar;
                    case XZBDevice.DOWNLOAD_LIST_FAILED:
                        vVar = new z(c, a(f, g, e), b(jSONObject));
                        vVar.a(str);
                        return vVar;
                    case XZBDevice.DOWNLOAD_LIST_ALL:
                        String str3 = a.d;
                        if (i(jSONObject)) {
                            if (jSONObject.getJSONObject("assetOnclick").isNull("itemUrl")) {
                                return null;
                            }
                            string = jSONObject.getJSONObject("assetOnclick").getString("itemUrl");
                        } else {
                            string = str3;
                        }
                        a2 = a(f, g, e);
                        assetInteractionMode = AssetInteractionMode.ASSET_INTERACTION_MODE_NO_ACTION;
                        if (i(jSONObject)) {
                            assetInteractionMode = AssetInteractionMode.ASSET_INTERACTION_MODE_BROWSER;
                            if (!jSONObject.getJSONObject("assetOnclick").isNull("openMode")) {
                                assetInteractionMode = c(jSONObject.getJSONObject("assetOnclick").getString("openMode"));
                                a3 = a(assetInteractionMode, jSONObject.getJSONObject("assetOnclick"));
                                if (a != null || a.length == 0) {
                                    vVar = new aa(c, a2, b(jSONObject), assetInteractionMode, jSONObject);
                                } else {
                                    vVar = new aa(c, a2, b(jSONObject), a, assetInteractionMode, jSONObject);
                                }
                                vVar.a(str);
                                vVar.b(string);
                                if (a3 != null) {
                                    return vVar;
                                }
                                vVar.c(a3);
                                return vVar;
                            }
                        }
                        a3 = null;
                        if (a != null) {
                        }
                        vVar = new aa(c, a2, b(jSONObject), assetInteractionMode, jSONObject);
                        vVar.a(str);
                        vVar.b(string);
                        if (a3 != null) {
                            return vVar;
                        }
                        vVar.c(a3);
                        return vVar;
                    case XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED:
                        return null;
                    case R.styleable.Toolbar_contentInsetEnd:
                        if (!i(jSONObject)) {
                            return null;
                        }
                        string = jSONObject.getJSONObject("assetOnclick").getString("itemUrl");
                        a2 = c(f, g, e);
                        assetInteractionMode = AssetInteractionMode.ASSET_INTERACTION_MODE_BROWSER;
                        if (jSONObject.getJSONObject("assetOnclick").isNull("openMode")) {
                            a3 = null;
                        } else {
                            assetInteractionMode = c(jSONObject.getJSONObject("assetOnclick").getString("openMode"));
                            a3 = a(assetInteractionMode, jSONObject.getJSONObject("assetOnclick"));
                        }
                        if (a == null || a.length == 0) {
                            vVar = new w(c, a2, h(jSONObject).getString(0), assetInteractionMode, jSONObject);
                        } else {
                            vVar = new w(c, a2, h(jSONObject).getString(0), a, assetInteractionMode, jSONObject);
                        }
                        vVar.a(str);
                        vVar.b(string);
                        if (a3 == null) {
                            return vVar;
                        }
                        vVar.c(a3);
                        return vVar;
                    default:
                        return null;
                }
            } catch (JSONException e2) {
                e2.printStackTrace();
                return null;
            }
        }
        Logger.a(InternalLogLevel.INTERNAL, a, new StringBuilder("Invalid asset style for asset: ").append(c).toString());
        Logger.a(InternalLogLevel.INTERNAL, a, new StringBuilder("Asset style JSON: ").append(e).toString());
        return null;
    }

    boolean a(JSONObject jSONObject, AssetType assetType) {
        if (jSONObject.isNull("geometry")) {
            return false;
        }
        try {
            if (!a(jSONObject.getJSONArray("geometry"))) {
                return false;
            }
            switch (AnonymousClass_1.a[assetType.ordinal()]) {
                case SpdyAgent.ACCS_ONLINE_SERVER:
                case XZBDevice.DOWNLOAD_LIST_FAILED:
                case XZBDevice.DOWNLOAD_LIST_ALL:
                    return true;
                case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                case R.styleable.Toolbar_contentInsetEnd:
                    if (jSONObject.isNull("text")) {
                        return false;
                    }
                    try {
                        return Integer.parseInt(jSONObject.getJSONObject("text").getString("size")) > 0;
                    } catch (NumberFormatException e) {
                        Logger.a(InternalLogLevel.INTERNAL, a, "Failure in validating text asset! Text size should be an integer");
                        return false;
                    }
                default:
                    return false;
            }
        } catch (JSONException e2) {
            e2.printStackTrace();
            return false;
        }
    }

    boolean a(JSONArray jSONArray) {
        int b = DisplayInfo.a().b();
        try {
            int i = jSONArray.getInt(0);
            int i2 = jSONArray.getInt(1);
            if (i < 0 || i2 < 0 || i > b) {
                boolean z = false;
            } else {
                i = 1;
            }
            if (!z) {
                return false;
            }
            i = jSONArray.getInt(XZBDevice.DOWNLOAD_LIST_RECYCLE);
            return i >= 0 && jSONArray.getInt(XZBDevice.DOWNLOAD_LIST_FAILED) >= 0 && i <= b;
        } catch (JSONException e) {
            e.printStackTrace();
            return false;
        }
    }

    ai[] a(JSONObject jSONObject) {
        if (jSONObject.isNull("trackers")) {
            return null;
        }
        List arrayList = new ArrayList();
        try {
            JSONArray jSONArray = jSONObject.getJSONArray("trackers");
            int length = jSONArray.length();
            if (length == 0) {
                return new ai[0];
            }
            for (int i = 0; i < length; i++) {
                JSONObject jSONObject2 = jSONArray.getJSONObject(i);
                if (!jSONObject2.isNull("trackerType") && b.b == a(jSONObject2.getString("trackerType")) && !jSONObject2.isNull(SocialConstants.PARAM_URL)) {
                    String trim = jSONObject2.getString(SocialConstants.PARAM_URL).trim();
                    if (URLUtil.isValidUrl(trim)) {
                        int optInt = jSONObject2.optInt("eventId", 0);
                        if (jSONObject2.isNull("uiEvent")) {
                            continue;
                        } else {
                            a b = b(jSONObject2.getString("uiEvent"));
                            if (a.a != b) {
                                Map hashMap = new HashMap();
                                if (!jSONObject2.isNull(d.l)) {
                                    try {
                                        JSONObject jSONObject3 = jSONObject2.getJSONObject(d.l);
                                        Iterator keys = jSONObject3.keys();
                                        while (keys.hasNext()) {
                                            String str = (String) keys.next();
                                            hashMap.put(str, jSONObject3.getString(str));
                                        }
                                    } catch (Throwable e) {
                                        Logger.a(InternalLogLevel.INTERNAL, a, "Failed to parser tracker.params", e);
                                    }
                                }
                                arrayList.add(new ai(trim, optInt, b, hashMap));
                            } else {
                                continue;
                            }
                        }
                    } else {
                        continue;
                    }
                }
            }
            return (ai[]) arrayList.toArray(new ai[arrayList.size()]);
        } catch (JSONException e2) {
            e2.printStackTrace();
            return (ai[]) arrayList.toArray(new ai[arrayList.size()]);
        }
    }

    private String b(JSONObject jSONObject) {
        try {
            if ((d(jSONObject).equalsIgnoreCase("ICON") || d(jSONObject).equalsIgnoreCase("IMAGE")) && jSONObject.getJSONArray("assetValue").getString(0).startsWith(HttpConstant.HTTP)) {
                return jSONObject.getJSONArray("assetValue").getString(0);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return a.d;
    }

    private String c(JSONObject jSONObject) {
        try {
            return jSONObject.getString("assetName");
        } catch (JSONException e) {
            e.printStackTrace();
            return a.d;
        }
    }

    private String d(JSONObject jSONObject) {
        try {
            return jSONObject.getString("assetType");
        } catch (JSONException e) {
            e.printStackTrace();
            return a.d;
        }
    }

    private JSONObject e(JSONObject jSONObject) {
        try {
            JSONObject jSONObject2;
            if (jSONObject.isNull("assetStyle")) {
                jSONObject2 = null;
            } else {
                jSONObject2 = jSONObject.getJSONObject("assetStyle");
            }
            if (jSONObject2 != null) {
                return jSONObject2;
            }
            if (jSONObject.isNull("assetStyleRef")) {
                return new JSONObject();
            }
            return b().getJSONObject(jSONObject.getString("assetStyleRef"));
        } catch (JSONException e) {
            e.printStackTrace();
            return new JSONObject();
        }
    }

    private Point f(JSONObject jSONObject) {
        Point point = new Point();
        try {
            JSONObject e = e(jSONObject);
            if (!e.isNull("geometry")) {
                JSONArray jSONArray = e.getJSONArray("geometry");
                point.x = b(jSONArray.getInt(0));
                point.y = b(jSONArray.getInt(1));
            }
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        return point;
    }

    private Point g(JSONObject jSONObject) {
        Point point = new Point();
        try {
            JSONObject e = e(jSONObject);
            if (!e.isNull("geometry")) {
                JSONArray jSONArray = e.getJSONArray("geometry");
                point.x = b(jSONArray.getInt(XZBDevice.DOWNLOAD_LIST_RECYCLE));
                point.y = b(jSONArray.getInt(XZBDevice.DOWNLOAD_LIST_FAILED));
            }
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        return point;
    }

    private String a(AssetInteractionMode assetInteractionMode, JSONObject jSONObject) throws JSONException {
        return (assetInteractionMode == AssetInteractionMode.ASSET_INTERACTION_MODE_DEEP_LINK && jSONObject.has("fallbackUrl")) ? jSONObject.getString("fallbackUrl") : null;
    }

    int b(int i) {
        return DisplayInfo.a(i);
    }

    private JSONArray h(JSONObject jSONObject) {
        try {
            return jSONObject.getJSONArray("assetValue");
        } catch (JSONException e) {
            e.printStackTrace();
            return new JSONArray();
        }
    }

    private boolean i(JSONObject jSONObject) {
        return !jSONObject.isNull("assetOnclick");
    }

    private b a(String str) {
        String trim = str.toUpperCase(Locale.US).trim();
        Object obj = -1;
        switch (trim.hashCode()) {
            case -1430070305:
                if (trim.equals("HTML_SCRIPT")) {
                    obj = XZBDevice.DOWNLOAD_LIST_FAILED;
                }
                break;
            case -158113182:
                if (trim.equals("URL_PING")) {
                    obj = 1;
                }
                break;
            case 1110926088:
                if (trim.equals("URL_WEBVIEW_PING")) {
                    obj = XZBDevice.DOWNLOAD_LIST_RECYCLE;
                }
                break;
        }
        switch (obj) {
            case SpdyAgent.ACCS_ONLINE_SERVER:
                return b.b;
            case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                return b.c;
            case XZBDevice.DOWNLOAD_LIST_FAILED:
                return b.d;
            default:
                return b.a;
        }
    }

    private a b(String str) {
        String trim = str.toUpperCase(Locale.US).trim();
        Object obj = -1;
        switch (trim.hashCode()) {
            case -1881262698:
                if (trim.equals("RENDER")) {
                    obj = XZBDevice.DOWNLOAD_LIST_FAILED;
                }
                break;
            case 2342118:
                if (trim.equals("LOAD")) {
                    obj = 1;
                }
                break;
            case 2634405:
                if (trim.equals("VIEW")) {
                    obj = XZBDevice.DOWNLOAD_LIST_ALL;
                }
                break;
            case 64212328:
                if (trim.equals("CLICK")) {
                    obj = XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED;
                }
                break;
            case 2008409463:
                if (trim.equals("CLIENT_FILL")) {
                    obj = XZBDevice.DOWNLOAD_LIST_RECYCLE;
                }
                break;
        }
        switch (obj) {
            case SpdyAgent.ACCS_ONLINE_SERVER:
                return a.b;
            case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                return a.c;
            case XZBDevice.DOWNLOAD_LIST_FAILED:
                return a.d;
            case XZBDevice.DOWNLOAD_LIST_ALL:
                return a.e;
            case XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED:
                return a.f;
            default:
                return a.a;
        }
    }

    private AssetInteractionMode c(String str) {
        String trim = str.toUpperCase(Locale.US).trim();
        Object obj = -1;
        switch (trim.hashCode()) {
            case -1038134325:
                if (trim.equals("EXTERNAL")) {
                    obj = 1;
                }
                break;
            case 69805756:
                if (trim.equals("INAPP")) {
                    obj = XZBDevice.DOWNLOAD_LIST_RECYCLE;
                }
                break;
            case 1411860198:
                if (trim.equals("DEEPLINK")) {
                    obj = XZBDevice.DOWNLOAD_LIST_FAILED;
                }
                break;
        }
        switch (obj) {
            case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                return AssetInteractionMode.ASSET_INTERACTION_MODE_IN_APP;
            case XZBDevice.DOWNLOAD_LIST_FAILED:
                return AssetInteractionMode.ASSET_INTERACTION_MODE_DEEP_LINK;
            default:
                return AssetInteractionMode.ASSET_INTERACTION_MODE_BROWSER;
        }
    }

    private AssetType d(String str) {
        String trim = str.toLowerCase(Locale.US).trim();
        Object obj = -1;
        switch (trim.hashCode()) {
            case -938102371:
                if (trim.equals("rating")) {
                    obj = R.styleable.Toolbar_contentInsetEnd;
                }
                break;
            case -410956671:
                if (trim.equals("container")) {
                    obj = 1;
                }
                break;
            case 98832:
                if (trim.equals("cta")) {
                    obj = XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED;
                }
                break;
            case 3226745:
                if (trim.equals("icon")) {
                    obj = XZBDevice.DOWNLOAD_LIST_RECYCLE;
                }
                break;
            case 3556653:
                if (trim.equals("text")) {
                    obj = XZBDevice.DOWNLOAD_LIST_ALL;
                }
                break;
            case 100313435:
                if (trim.equals(WBConstants.GAME_PARAMS_GAME_IMAGE_URL)) {
                    obj = XZBDevice.DOWNLOAD_LIST_FAILED;
                }
                break;
        }
        switch (obj) {
            case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                return AssetType.ASSET_TYPE_ICON;
            case XZBDevice.DOWNLOAD_LIST_FAILED:
                return AssetType.ASSET_TYPE_IMAGE;
            case XZBDevice.DOWNLOAD_LIST_ALL:
                return AssetType.ASSET_TYPE_TEXT;
            case XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED:
                return AssetType.ASSET_TYPE_CTA;
            case R.styleable.Toolbar_contentInsetEnd:
                return AssetType.ASSET_TYPE_RATING;
            default:
                return AssetType.ASSET_TYPE_CONTAINER;
        }
    }

    private a e(String str) {
        String trim = str.toLowerCase(Locale.US).trim();
        Object obj = -1;
        switch (trim.hashCode()) {
            case -1178781136:
                if (trim.equals("italic")) {
                    obj = XZBDevice.DOWNLOAD_LIST_FAILED;
                }
                break;
            case -1026963764:
                if (trim.equals("underline")) {
                    obj = XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED;
                }
                break;
            case -891985998:
                if (trim.equals("strike")) {
                    obj = XZBDevice.DOWNLOAD_LIST_ALL;
                }
                break;
            case 3029637:
                if (trim.equals("bold")) {
                    obj = XZBDevice.DOWNLOAD_LIST_RECYCLE;
                }
                break;
            case 3387192:
                if (trim.equals(IXAdSystemUtils.NT_NONE)) {
                    obj = 1;
                }
                break;
        }
        switch (obj) {
            case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                return a.b;
            case XZBDevice.DOWNLOAD_LIST_FAILED:
                return a.c;
            case XZBDevice.DOWNLOAD_LIST_ALL:
                return a.d;
            case XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED:
                return a.e;
            default:
                return a.a;
        }
    }

    private b f(String str) {
        String trim = str.toLowerCase(Locale.US).trim();
        Object obj = -1;
        switch (trim.hashCode()) {
            case 3321844:
                if (trim.equals("line")) {
                    obj = XZBDevice.DOWNLOAD_LIST_RECYCLE;
                }
                break;
            case 3387192:
                if (trim.equals(IXAdSystemUtils.NT_NONE)) {
                    obj = 1;
                }
                break;
        }
        switch (obj) {
            case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                return b.b;
            default:
                return b.a;
        }
    }

    private a g(String str) {
        String trim = str.toLowerCase(Locale.US).trim();
        Object obj = -1;
        switch (trim.hashCode()) {
            case -1349116587:
                if (trim.equals("curved")) {
                    obj = XZBDevice.DOWNLOAD_LIST_RECYCLE;
                }
                break;
            case 1787472634:
                if (trim.equals("straight")) {
                    obj = 1;
                }
                break;
        }
        switch (obj) {
            case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                return a.a;
            default:
                return a.b;
        }
    }

    private t a(Point point, Point point2, JSONObject jSONObject) throws JSONException {
        b bVar;
        String str;
        a aVar;
        String str2;
        if (jSONObject.isNull("border")) {
            bVar = b.a;
            str = "#ff000000";
            aVar = a.b;
        } else {
            JSONObject jSONObject2 = jSONObject.getJSONObject("border");
            if (jSONObject2.isNull("style")) {
                bVar = b.a;
                str = "#ff000000";
                aVar = a.b;
            } else {
                a aVar2;
                bVar = f(jSONObject2.getString("style"));
                if (jSONObject2.isNull("corner")) {
                    aVar2 = a.b;
                } else {
                    aVar2 = g(jSONObject2.getString("corner"));
                }
                if (jSONObject2.isNull("color")) {
                    str = "#ff000000";
                    aVar = aVar2;
                } else {
                    str = jSONObject2.getString("color").trim();
                    aVar = aVar2;
                }
            }
        }
        if (jSONObject.isNull("backgroundColor")) {
            str2 = "#00000000";
        } else {
            str2 = jSONObject.getString("backgroundColor").trim();
        }
        return new t(point.x, point.y, point2.x, point2.y, bVar, aVar, str, str2);
    }

    private a b(Point point, Point point2, JSONObject jSONObject) throws JSONException {
        b bVar;
        String str;
        a aVar;
        JSONObject jSONObject2;
        String str2;
        if (jSONObject.isNull("border")) {
            bVar = b.a;
            str = "#ff000000";
            aVar = a.b;
        } else {
            jSONObject2 = jSONObject.getJSONObject("border");
            if (jSONObject2.isNull("style")) {
                bVar = b.a;
                str = "#ff000000";
                aVar = a.b;
            } else {
                a aVar2;
                bVar = f(jSONObject2.getString("style"));
                if (jSONObject2.isNull("corner")) {
                    aVar2 = a.b;
                } else {
                    aVar2 = g(jSONObject2.getString("corner"));
                }
                if (jSONObject2.isNull("color")) {
                    str = "#ff000000";
                    aVar = aVar2;
                } else {
                    str = jSONObject2.getString("color").trim();
                    aVar = aVar2;
                }
            }
        }
        if (jSONObject.isNull("backgroundColor")) {
            str2 = "#00000000";
        } else {
            str2 = jSONObject.getString("backgroundColor").trim();
        }
        jSONObject2 = jSONObject.getJSONObject("text");
        try {
            String str3;
            a[] aVarArr;
            int parseInt = Integer.parseInt(jSONObject2.getString("size"));
            int parseInt2 = jSONObject2.isNull("length") ? InMobiClientPositioning.NO_REPEAT : Integer.parseInt(jSONObject2.getString("length"));
            if (jSONObject2.isNull("color")) {
                str3 = "#ff000000";
            } else {
                str3 = jSONObject2.getString("color").trim();
            }
            if (jSONObject2.isNull("style")) {
                aVarArr = new a[]{a.a};
            } else {
                int length = jSONObject2.getJSONArray("style").length();
                if (length == 0) {
                    aVarArr = new a[]{a.a};
                } else {
                    aVarArr = new a[length];
                    for (int i = 0; i < length; i++) {
                        aVarArr[i] = e(jSONObject2.getJSONArray("style").getString(i));
                    }
                }
            }
            return new a(point.x, point.y, point2.x, point2.y, bVar, aVar, str, str2, parseInt, parseInt2, str3, aVarArr);
        } catch (Throwable e) {
            Logger.a(InternalLogLevel.INTERNAL, a, "Failure in building text asset! Text size should be an integer");
            JSONException jSONException = new JSONException(e.getMessage());
            jSONException.initCause(e);
            throw jSONException;
        }
    }

    private a c(Point point, Point point2, JSONObject jSONObject) throws JSONException {
        b bVar;
        String str;
        a aVar;
        JSONObject jSONObject2;
        String str2;
        if (jSONObject.isNull("border")) {
            bVar = b.a;
            str = "#ff000000";
            aVar = a.b;
        } else {
            jSONObject2 = jSONObject.getJSONObject("border");
            if (jSONObject2.isNull("style")) {
                bVar = b.a;
                str = "#ff000000";
                aVar = a.b;
            } else {
                a aVar2;
                bVar = f(jSONObject2.getString("style"));
                if (jSONObject2.isNull("corner")) {
                    aVar2 = a.b;
                } else {
                    aVar2 = g(jSONObject2.getString("corner"));
                }
                if (jSONObject2.isNull("color")) {
                    str = "#ff000000";
                    aVar = aVar2;
                } else {
                    str = jSONObject2.getString("color").trim();
                    aVar = aVar2;
                }
            }
        }
        if (jSONObject.isNull("backgroundColor")) {
            str2 = "#00000000";
        } else {
            str2 = jSONObject.getString("backgroundColor").trim();
        }
        jSONObject2 = jSONObject.getJSONObject("text");
        try {
            String str3;
            a[] aVarArr;
            int parseInt = Integer.parseInt(jSONObject2.getString("size"));
            if (jSONObject2.isNull("color")) {
                str3 = "#ff000000";
            } else {
                str3 = jSONObject2.getString("color").trim();
            }
            if (jSONObject2.isNull("style")) {
                aVarArr = new a[]{a.a};
            } else {
                int length = jSONObject2.getJSONArray("style").length();
                if (length == 0) {
                    aVarArr = new a[]{a.a};
                } else {
                    aVarArr = new a[length];
                    for (int i = 0; i < length; i++) {
                        aVarArr[i] = e(jSONObject2.getJSONArray("style").getString(i));
                    }
                }
            }
            return new a(point.x, point.y, point2.x, point2.y, bVar, aVar, str, str2, parseInt, str3, aVarArr);
        } catch (Throwable e) {
            Logger.a(InternalLogLevel.INTERNAL, a, "Failure in building text asset! Text size should be an integer");
            JSONException jSONException = new JSONException(e.getMessage());
            jSONException.initCause(e);
            throw jSONException;
        }
    }
}
