package com.xunlei.downloadprovider.cloudlist;

import android.content.Intent;
import com.xunlei.common.lixian.XLLixianBtTask;
import com.xunlei.common.lixian.XLLixianTask;
import com.xunlei.common.yunbo.XLYB_VODINFO;
import com.xunlei.downloadprovider.vod.protocol.VodSourceType;
import com.xunlei.tdlive.R;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

// compiled from: BTSubFileObtainerProxy.java
public abstract class a {
    protected final List<a> a;
    protected b b;
    protected int c;
    protected String d;

    // compiled from: BTSubFileObtainerProxy.java
    public static final class a {
        String a;
        int b;
        long c;
        String d;
        String e;
        String f;
        String g;
        boolean h;

        public a() {
            this.a = null;
            this.b = -1;
            this.c = 0;
            this.d = null;
            this.e = null;
            this.f = null;
            this.g = null;
            this.h = false;
        }
    }

    // compiled from: BTSubFileObtainerProxy.java
    public static interface b {
        void a(int i, Object obj, boolean z);
    }

    public a() {
        this.a = new ArrayList();
        this.b = null;
        this.c = -1;
        this.d = null;
    }

    public void a(Object obj) {
    }

    public final void a() {
        this.a.clear();
    }

    public final List<a> b() {
        return this.a;
    }

    public final void a(b bVar) {
        this.b = bVar;
    }

    public final int c() {
        return this.c;
    }

    public final String d() {
        return this.d;
    }

    public final int e() {
        switch (this.c) {
            case R.styleable.AppCompatTheme_buttonStyleSmall:
                return R.styleable.AppCompatTheme_actionModeCloseButtonStyle;
            case R.styleable.AppCompatTheme_checkboxStyle:
                return R.styleable.AppCompatTheme_actionModeBackground;
            default:
                return 0;
        }
    }

    public final VodSourceType f() {
        VodSourceType vodSourceType = VodSourceType.normal;
        switch (this.c) {
            case R.styleable.AppCompatTheme_buttonStyleSmall:
                return VodSourceType.lixian;
            case R.styleable.AppCompatTheme_checkboxStyle:
                return VodSourceType.vod_history;
            default:
                return vodSourceType;
        }
    }

    public static a a(Intent intent) {
        if (intent == null) {
            return null;
        }
        a iVar;
        int intExtra = intent.getIntExtra("intent_source_key", -1);
        Serializable serializableExtra;
        switch (intExtra) {
            case R.styleable.AppCompatTheme_buttonStyleSmall:
                serializableExtra = intent.getSerializableExtra("intent_task");
                if (serializableExtra instanceof XLLixianTask) {
                    iVar = new i((XLLixianBtTask) serializableExtra);
                }
                iVar = null;
                break;
            case R.styleable.AppCompatTheme_checkboxStyle:
                serializableExtra = intent.getSerializableExtra("intent_task");
                if (serializableExtra instanceof XLYB_VODINFO) {
                    iVar = new l((XLYB_VODINFO) serializableExtra);
                }
                iVar = null;
                break;
            default:
                iVar = null;
                break;
        }
        if (iVar == null) {
            return iVar;
        }
        iVar.d = intent.getStringExtra("intent_title");
        iVar.c = intExtra;
        return iVar;
    }
}
