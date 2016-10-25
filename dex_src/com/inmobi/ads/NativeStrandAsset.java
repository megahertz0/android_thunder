package com.inmobi.ads;

import com.inmobi.commons.core.utilities.c;
import com.umeng.a;
import com.xunlei.tdlive.R;
import java.util.Map;
import org.json.JSONObject;

class NativeStrandAsset {
    private static final String m;
    protected AssetType a;
    protected t b;
    protected String c;
    protected Object d;
    protected JSONObject e;
    protected String f;
    protected boolean g;
    protected AssetInteractionMode h;
    protected String i;
    protected String j;
    protected NativeStrandAsset k;
    protected ai[] l;

    public enum AssetInteractionMode {
        ASSET_INTERACTION_MODE_NO_ACTION,
        ASSET_INTERACTION_MODE_IN_APP,
        ASSET_INTERACTION_MODE_BROWSER,
        ASSET_INTERACTION_MODE_DEEP_LINK
    }

    public enum AssetType {
        ASSET_TYPE_CONTAINER("CONTAINER"),
        ASSET_TYPE_TEXT("TEXT"),
        ASSET_TYPE_CTA("CTA"),
        ASSET_TYPE_IMAGE("IMAGE"),
        ASSET_TYPE_ICON("ICON"),
        ASSET_TYPE_RATING("RATING");
        private final String a;

        private AssetType(String str) {
            this.a = str;
        }

        public final boolean isEqual(String str) {
            return str != null && this.a.equalsIgnoreCase(str);
        }
    }

    static {
        m = NativeStrandAsset.class.getSimpleName();
    }

    public NativeStrandAsset() {
        this("root", AssetType.ASSET_TYPE_CONTAINER);
    }

    public NativeStrandAsset(String str, AssetType assetType) {
        this(str, assetType, new t());
    }

    public NativeStrandAsset(String str, AssetType assetType, t tVar) {
        this(str, assetType, tVar, new ai[0]);
    }

    public NativeStrandAsset(String str, AssetType assetType, t tVar, ai[] aiVarArr) {
        this.c = str;
        this.a = assetType;
        this.b = tVar;
        this.d = null;
        this.f = a.d;
        this.g = false;
        this.h = AssetInteractionMode.ASSET_INTERACTION_MODE_NO_ACTION;
        this.i = a.d;
        this.e = new JSONObject();
        int min = Math.min(aiVarArr.length, R.styleable.AppCompatTheme_actionModeCutDrawable);
        this.l = new ai[min];
        System.arraycopy(aiVarArr, 0, this.l, 0, min);
    }

    public void a(ai aiVar, Map<String, String> map) {
        l().a(c.a(aiVar.a(), (Map) map), aiVar.c(), true);
    }

    public void a(a aVar, Map<String, String> map) {
        if (this.l.length != 0) {
            for (ai aiVar : this.l) {
                if (aVar == aiVar.b()) {
                    a(aiVar, (Map) map);
                }
            }
        }
    }

    public AssetType a() {
        return this.a;
    }

    public t b() {
        return this.b;
    }

    public String c() {
        return this.c;
    }

    public Object d() {
        return this.d;
    }

    public JSONObject e() {
        return this.e;
    }

    public void a(ai[] aiVarArr) {
        int min = Math.min(aiVarArr.length, R.styleable.AppCompatTheme_actionModeCutDrawable);
        this.l = new ai[min];
        System.arraycopy(aiVarArr, 0, this.l, 0, min);
    }

    public void a(String str) {
        this.f = str;
    }

    public String f() {
        return this.f;
    }

    public boolean g() {
        return this.g;
    }

    public AssetInteractionMode h() {
        return this.h;
    }

    public void b(String str) {
        this.i = str.trim();
    }

    public void c(String str) {
        this.j = str.trim();
    }

    public String i() {
        return this.i;
    }

    public String j() {
        return this.j;
    }

    public void a(NativeStrandAsset nativeStrandAsset) {
        this.k = nativeStrandAsset;
    }

    public NativeStrandAsset k() {
        return this.k;
    }

    com.inmobi.rendering.a.c l() {
        return com.inmobi.rendering.a.c.a();
    }
}
