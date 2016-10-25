package com.inmobi.ads;

import com.inmobi.ads.NativeStrandAsset.AssetType;
import java.util.Iterator;
import org.json.JSONObject;

// compiled from: NativeStrandContainerAsset.java
class v extends NativeStrandAsset implements Iterable<NativeStrandAsset> {
    private long m;
    private NativeStrandAsset[] n;
    private int o;

    // compiled from: NativeStrandContainerAsset.java
    private class a implements Iterator<NativeStrandAsset> {
        private int b;

        public /* synthetic */ Object next() {
            return a();
        }

        public a() {
            this.b = 0;
        }

        public boolean hasNext() {
            return this.b < v.this.o;
        }

        public NativeStrandAsset a() {
            NativeStrandAsset[] b = v.this.n;
            int i = this.b;
            this.b = i + 1;
            return b[i];
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    public Iterator<NativeStrandAsset> iterator() {
        return new a();
    }

    public void a(long j) {
        this.m = j;
    }

    public long m() {
        return this.m;
    }

    public v(String str, t tVar, JSONObject jSONObject) {
        this(str, tVar, new ai[0], jSONObject);
    }

    public v(String str, t tVar, ai[] aiVarArr, JSONObject jSONObject) {
        super(str, AssetType.ASSET_TYPE_CONTAINER, tVar, aiVarArr);
        this.m = 0;
        this.e = jSONObject;
        this.n = new NativeStrandAsset[1];
        this.o = 0;
    }

    public boolean b(NativeStrandAsset nativeStrandAsset) {
        if (this.o >= 16) {
            return false;
        }
        if (this.o == this.n.length) {
            b(this.n.length * 2);
        }
        NativeStrandAsset[] nativeStrandAssetArr = this.n;
        int i = this.o;
        this.o = i + 1;
        nativeStrandAssetArr[i] = nativeStrandAsset;
        return true;
    }

    public NativeStrandAsset a(int i) {
        return (i < 0 || i >= n()) ? null : this.n[i];
    }

    public int n() {
        return this.o;
    }

    public boolean o() {
        return "root".equalsIgnoreCase(this.c);
    }

    public boolean p() {
        return "card_scrollable".equalsIgnoreCase(this.c);
    }

    private void b(int i) {
        Object obj = new Object[i];
        System.arraycopy(this.n, 0, obj, 0, this.o);
        this.n = obj;
    }
}
