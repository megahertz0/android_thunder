package com.inmobi.rendering;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.inmobi.ads.b.e;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import com.inmobi.commons.core.utilities.info.DisplayInfo;
import com.inmobi.rendering.CustomView.SwitchIconType;
import com.inmobi.rendering.RenderingProperties.PlacementType;
import com.umeng.socialize.common.SocializeConstants;
import com.xunlei.downloadprovider.web.core.JsInterface;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.util.HashMap;
import java.util.Map;

@SuppressLint({"ClickableViewAccessibility"})
public class InMobiAdActivity extends Activity {
    @SuppressLint({"UseSparseArrays"})
    public static Map<Integer, a> a;
    @SuppressLint({"UseSparseArrays"})
    public static Map<Integer, Intent> b;
    public static Integer c;
    @SuppressLint({"UseSparseArrays"})
    public static Map<Integer, c> d;
    public static Integer e;
    private static final String f;
    private static Map<Integer, RenderView> g;
    private static RenderView h;
    private RenderView i;
    private RenderView j;
    private CustomView k;
    private CustomView l;
    private int m;
    private boolean n;

    public static interface a {
        void a(int i, Intent intent);
    }

    public static interface b {
        void a();

        void b();
    }

    public static interface c {
        void a(int i, String[] strArr, int[] iArr);
    }

    public InMobiAdActivity() {
        this.n = false;
    }

    static {
        f = InMobiAdActivity.class.getSimpleName();
        g = new HashMap();
        a = new HashMap();
        b = new HashMap();
        c = Integer.valueOf(0);
        d = new HashMap();
        e = Integer.valueOf(0);
    }

    public static int a(RenderView renderView) {
        int hashCode = renderView.hashCode();
        g.put(Integer.valueOf(hashCode), renderView);
        return hashCode;
    }

    public static void b(RenderView renderView) {
        if (((RenderView) g.remove(Integer.valueOf(renderView.hashCode()))) == null) {
            Logger.a(InternalLogLevel.INTERNAL, f, new StringBuilder("Failed to remove renderview with key:").append(renderView.hashCode()).toString());
        }
    }

    public static void c(RenderView renderView) {
        h = renderView;
    }

    public static int a(Intent intent, a aVar) {
        c = Integer.valueOf(c.intValue() + 1);
        a.put(c, aVar);
        b.put(c, intent);
        return c.intValue();
    }

    public static void a(String[] strArr, c cVar) {
        if (strArr != null && strArr.length != 0) {
            int a = a(cVar);
            Intent intent = new Intent(com.inmobi.commons.a.a.b(), InMobiAdActivity.class);
            intent.putExtra("com.inmobi.rendering.InMobiAdActivity.EXTRA_AD_ACTIVITY_TYPE", R.styleable.AppCompatTheme_editTextStyle);
            intent.putExtra(SocializeConstants.WEIBO_ID, a);
            intent.putExtra("permissions", strArr);
            com.inmobi.commons.a.a.a(com.inmobi.commons.a.a.b(), intent);
        }
    }

    private static int a(c cVar) {
        e = Integer.valueOf(e.intValue() + 1);
        d.put(e, cVar);
        return e.intValue();
    }

    @TargetApi(23)
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.m = getIntent().getIntExtra("com.inmobi.rendering.InMobiAdActivity.EXTRA_AD_ACTIVITY_TYPE", R.styleable.AppCompatTheme_checkboxStyle);
        if (this.m == 100) {
            com.inmobi.rendering.RenderView.a aVar;
            e eVar;
            com.inmobi.ads.b.c cVar;
            String stringExtra = getIntent().getStringExtra("com.inmobi.rendering.InMobiAdActivity.IN_APP_BROWSER_URL");
            this.j = new RenderView(this, new RenderingProperties(PlacementType.FULL_SCREEN));
            if (h == null) {
                aVar = RenderView.a;
            } else {
                aVar = h.getListener();
            }
            if (h == null) {
                eVar = new e();
            } else {
                eVar = h.getRenderingConfig();
            }
            if (h == null) {
                cVar = new com.inmobi.ads.b.c();
            } else {
                cVar = h.getMraidConfig();
            }
            this.j.setIsInAppBrowser(true);
            this.j.a(aVar, eVar, cVar);
            a();
            this.j.loadUrl(stringExtra);
            this.j.getListener().e(this.j);
            this.j.setFullScreenActivity(this);
        } else if (this.m == 102) {
            if (getIntent().hasExtra("com.inmobi.rendering.InMobiAdActivity.EXTRA_AD_RENDERVIEW_INDEX")) {
                intExtra = getIntent().getIntExtra("com.inmobi.rendering.InMobiAdActivity.EXTRA_AD_RENDERVIEW_INDEX", -1);
                this.i = (RenderView) g.get(Integer.valueOf(intExtra));
                if (this.i == null) {
                    Logger.a(InternalLogLevel.INTERNAL, f, new StringBuilder("Failed to find RenderView with key:").append(intExtra).toString());
                    finish();
                    return;
                }
                if (getIntent().getBooleanExtra("com.inmobi.rendering.InMobiAdActivity.EXTRA_AD_ACTIVITY_IS_FULL_SCREEN", false)) {
                    requestWindowFeature(1);
                    getWindow().setFlags(JsInterface.MSG_JS_COLLECT_WEBSITE, JsInterface.MSG_JS_COLLECT_WEBSITE);
                }
                b();
                this.i.setFullScreenActivity(this);
                if (this.i.getAdScreenEventsListener() != null) {
                    this.i.getAdScreenEventsListener().a();
                }
            }
        } else if (this.m == 103) {
            intExtra = getIntent().getIntExtra(SocializeConstants.WEIBO_ID, -1);
            if (intExtra == -1) {
                Logger.a(InternalLogLevel.INTERNAL, f, "Invalid Request Code Supplied for ACTIVITY_TYPE_FOR_RESULT");
            } else {
                startActivityForResult((Intent) b.get(Integer.valueOf(intExtra)), intExtra);
            }
        } else if (this.m == 104) {
            int intExtra = getIntent().getIntExtra(SocializeConstants.WEIBO_ID, -1);
            if (intExtra == -1) {
                Logger.a(InternalLogLevel.INTERNAL, f, "Invalid Request Code Supplied for ACTIVITY_TYPE_PERMISSIONS_DIALOG");
                return;
            }
            String[] stringArrayExtra = getIntent().getStringArrayExtra("permissions");
            if (stringArrayExtra != null && stringArrayExtra.length > 0) {
                com.inmobi.commons.core.utilities.a.a();
                com.inmobi.commons.core.utilities.a.b();
                requestPermissions(stringArrayExtra, intExtra);
            }
        }
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        if (this.j != null) {
            this.j.l();
        }
    }

    private void a() {
        ViewGroup relativeLayout = new RelativeLayout(this);
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
        layoutParams.addRule(XZBDevice.Stop);
        layoutParams.addRule(XZBDevice.DOWNLOAD_LIST_RECYCLE, 65533);
        relativeLayout.setBackgroundColor(-1);
        relativeLayout.addView(this.j, layoutParams);
        a(relativeLayout);
        setContentView(relativeLayout);
    }

    private void a(ViewGroup viewGroup) {
        float c = DisplayInfo.a().c();
        View linearLayout = new LinearLayout(this);
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, (int) (48.0f * c));
        linearLayout.setOrientation(0);
        linearLayout.setId(65533);
        linearLayout.setWeightSum(100.0f);
        linearLayout.setBackgroundResource(17301658);
        linearLayout.setBackgroundColor(-7829368);
        layoutParams.addRule(XZBDevice.Fail);
        viewGroup.addView(linearLayout, layoutParams);
        layoutParams = new LinearLayout.LayoutParams(-1, -1);
        layoutParams.weight = 25.0f;
        View customView = new CustomView(this, c, SwitchIconType.CLOSE_ICON);
        customView.setOnTouchListener(new OnTouchListener() {
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == 1) {
                    view.setBackgroundColor(-7829368);
                    InMobiAdActivity.this.n = true;
                    InMobiAdActivity.this.finish();
                } else if (motionEvent.getAction() == 0) {
                    view.setBackgroundColor(-16711681);
                }
                return true;
            }
        });
        linearLayout.addView(customView, layoutParams);
        customView = new CustomView(this, c, SwitchIconType.REFRESH);
        customView.setOnTouchListener(new OnTouchListener() {
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == 1) {
                    view.setBackgroundColor(-7829368);
                    InMobiAdActivity.this.j.reload();
                } else if (motionEvent.getAction() == 0) {
                    view.setBackgroundColor(-16711681);
                }
                return true;
            }
        });
        linearLayout.addView(customView, layoutParams);
        customView = new CustomView(this, c, SwitchIconType.BACK);
        customView.setOnTouchListener(new OnTouchListener() {
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == 1) {
                    view.setBackgroundColor(-7829368);
                    if (InMobiAdActivity.this.j.canGoBack()) {
                        InMobiAdActivity.this.j.goBack();
                    } else {
                        InMobiAdActivity.this.n = true;
                        InMobiAdActivity.this.finish();
                    }
                } else if (motionEvent.getAction() == 0) {
                    view.setBackgroundColor(-16711681);
                }
                return true;
            }
        });
        linearLayout.addView(customView, layoutParams);
        customView = new CustomView(this, c, SwitchIconType.FORWARD_INACTIVE);
        customView.setOnTouchListener(new OnTouchListener() {
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == 1) {
                    view.setBackgroundColor(-7829368);
                    if (InMobiAdActivity.this.j.canGoForward()) {
                        InMobiAdActivity.this.j.goForward();
                    }
                } else if (motionEvent.getAction() == 0) {
                    view.setBackgroundColor(-16711681);
                }
                return true;
            }
        });
        linearLayout.addView(customView, layoutParams);
    }

    private void b() {
        FrameLayout frameLayout = (FrameLayout) findViewById(16908290);
        View relativeLayout = new RelativeLayout(this);
        float c = DisplayInfo.a().c();
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
        layoutParams.addRule(XZBDevice.Stop);
        if (this.i.getParent() != null) {
            ((ViewGroup) this.i.getParent()).removeView(this.i);
        }
        LayoutParams layoutParams2 = new RelativeLayout.LayoutParams((int) (50.0f * c), (int) (50.0f * c));
        layoutParams2.addRule(XZBDevice.Success);
        this.k = new CustomView(this, c, SwitchIconType.CLOSE_BUTTON);
        this.k.setId(65532);
        this.k.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                InMobiAdActivity.this.n = true;
                InMobiAdActivity.this.i.k();
            }
        });
        this.l = new CustomView(this, c, SwitchIconType.CLOSE_TRANSPARENT);
        this.l.setId(65531);
        this.l.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                InMobiAdActivity.this.n = true;
                InMobiAdActivity.this.i.k();
            }
        });
        relativeLayout.setId(65534);
        relativeLayout.addView(this.i, layoutParams);
        relativeLayout.addView(this.k, layoutParams2);
        relativeLayout.addView(this.l, layoutParams2);
        relativeLayout.setBackgroundColor(0);
        frameLayout.addView(relativeLayout, new RelativeLayout.LayoutParams(-1, -1));
        this.i.b(this.i.f());
        this.i.c(this.i.e());
    }

    protected void onDestroy() {
        super.onDestroy();
        if (!this.n) {
            return;
        }
        if (this.m == 100) {
            this.j.getListener().f(this.j);
            this.j.destroy();
        } else if (this.m == 102 && this.i != null && this.i.getAdScreenEventsListener() != null) {
            this.i.getAdScreenEventsListener().b();
        }
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        a aVar = (a) a.get(Integer.valueOf(i));
        a.remove(Integer.valueOf(i));
        b.remove(Integer.valueOf(i));
        aVar.a(i2, intent);
        this.n = true;
        finish();
    }

    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
        com.inmobi.commons.core.utilities.a.a();
        com.inmobi.commons.core.utilities.a.c();
        c cVar = (c) d.get(Integer.valueOf(i));
        d.remove(Integer.valueOf(i));
        if (cVar != null) {
            cVar.a(i, strArr, iArr);
        }
        finish();
    }

    void a(boolean z) {
        this.n = z;
    }

    public void onBackPressed() {
        if (this.m == 102) {
            if (this.i != null) {
                this.i.i();
                if (!this.i.g()) {
                    this.i.k();
                } else {
                    return;
                }
            }
            this.n = true;
        } else if (this.m == 100) {
            this.n = true;
            finish();
        }
    }
}
