package com.xunlei.downloadprovider.personal.settings;

import android.graphics.drawable.Drawable.ConstantState;
import android.os.Bundle;
import android.support.v4.widget.AutoScrollHelper;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.tencent.connect.common.Constants;
import com.xunlei.downloadprovider.app.BaseActivity;
import com.xunlei.downloadprovider.b.c.e;
import com.xunlei.downloadprovider.commonview.XLToast;
import com.xunlei.downloadprovider.commonview.XLToast.XLToastType;
import com.xunlei.downloadprovider.model.protocol.networkcheck.i;
import com.xunlei.downloadprovider.util.ag;
import com.xunlei.downloadprovider.util.ag.b;
import com.xunlei.downloadprovider.util.k;
import com.xunlei.xiazaibao.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import org.android.spdy.SpdyAgent;

public class SpeedDetectionActivity extends BaseActivity {
    private static final String b;
    int a;
    private k c;
    private TextView d;
    private ImageView e;
    private ImageView f;
    private ImageView g;
    private ImageView h;
    private ArcProgressBarView i;
    private Button j;
    private View k;
    private TextView l;
    private ImageView m;
    private ImageView n;
    private boolean o;
    private final float p;
    private final float q;
    private b r;
    private OnClickListener s;
    private com.xunlei.downloadprovider.util.k.a t;

    static /* synthetic */ class AnonymousClass_1 {
        static final /* synthetic */ int[] a;

        static {
            a = new int[a.a().length];
            try {
                a[a.d - 1] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                a[a.c - 1] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                a[a.a - 1] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                a[a.b - 1] = 4;
            } catch (NoSuchFieldError e4) {
            }
        }
    }

    private enum a {
        ;

        public static int[] a() {
            return (int[]) e.clone();
        }

        static {
            a = 1;
            b = 2;
            c = 3;
            d = 4;
            e = new int[]{a, b, c, d};
        }
    }

    public SpeedDetectionActivity() {
        this.c = null;
        this.d = null;
        this.e = null;
        this.f = null;
        this.g = null;
        this.h = null;
        this.i = null;
        this.j = null;
        this.o = false;
        this.p = 262.0f;
        this.q = 32.75f;
        this.r = new aa(this);
        this.s = new ab(this);
        this.t = new ac(this);
    }

    static /* synthetic */ void a(SpeedDetectionActivity speedDetectionActivity, int i, long j) {
        speedDetectionActivity.j.setText(2131232617);
        speedDetectionActivity.a(j);
        speedDetectionActivity.b(j);
        if (i != 0) {
            speedDetectionActivity.j.setEnabled(false);
            speedDetectionActivity.j.setTextColor(861954406);
            speedDetectionActivity.d.setText(2131232623);
        } else if (j < 51200) {
            speedDetectionActivity.d.setText(2131232619);
        } else if (j >= 51200 && j < 102400) {
            speedDetectionActivity.d.setText(2131232627);
        } else if (j < 102400 || j >= 524288) {
            speedDetectionActivity.d.setText(2131232621);
            if (speedDetectionActivity.a == 1) {
                speedDetectionActivity.d.setText(2131232622);
            }
        } else {
            speedDetectionActivity.d.setText(2131232624);
            if (speedDetectionActivity.a == 1) {
                speedDetectionActivity.d.setText(2131232625);
            }
        }
    }

    static /* synthetic */ void b(SpeedDetectionActivity speedDetectionActivity) {
        if (speedDetectionActivity.c == null || !speedDetectionActivity.c.b) {
            speedDetectionActivity.d.setText(2131232626);
            speedDetectionActivity.i.setProgress(AutoScrollHelper.RELATIVE_UNSPECIFIED);
            if (speedDetectionActivity.o) {
                XLToast.a(speedDetectionActivity.getApplicationContext(), XLToastType.XLTOAST_TYPE_NORMAL, speedDetectionActivity.getString(2131232628));
            }
            speedDetectionActivity.c = new k();
            k kVar = speedDetectionActivity.c;
            com.xunlei.downloadprovider.util.k.a aVar = speedDetectionActivity.t;
            if (!kVar.b) {
                kVar.d = 15;
                kVar.c = aVar;
                kVar.b = true;
                kVar.p = 5000;
                kVar.q = 5000;
                kVar.k = new b(kVar);
                if (kVar.c != null) {
                    kVar.c.c();
                }
                com.xunlei.downloadprovider.b.a aVar2 = new com.xunlei.downloadprovider.model.protocol.networkcheck.a(kVar.k);
                e aVar3 = new com.xunlei.downloadprovider.b.c.a("http://m.sjzhushou.com/speed_test/list.js", Constants.HTTP_GET, null, new i());
                aVar3.setBpOnDataLoaderCompleteListener(new com.xunlei.downloadprovider.model.protocol.networkcheck.b(aVar2));
                aVar2.setBpFuture(aVar3);
                com.xunlei.downloadprovider.model.protocol.networkcheck.a.runBox(aVar2);
            }
            speedDetectionActivity.j.setText(2131232616);
            return;
        }
        if (!speedDetectionActivity.c.a()) {
            speedDetectionActivity.b(0);
            speedDetectionActivity.a(0);
            speedDetectionActivity.j.setText(2131232617);
        }
        speedDetectionActivity.c = null;
        speedDetectionActivity.d.setText(com.umeng.a.d);
    }

    static {
        b = SpeedDetectionActivity.class.getSimpleName();
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(2130968990);
        this.a = getIntent().getIntExtra("download_list", 0);
        this.i = (ArcProgressBarView) findViewById(2131757022);
        this.j = (Button) findViewById(2131757033);
        this.h = (ImageView) findViewById(2131757029);
        this.e = (ImageView) findViewById(2131757025);
        this.g = (ImageView) findViewById(2131757027);
        this.f = (ImageView) findViewById(2131757026);
        this.d = (TextView) findViewById(2131757032);
        this.m = (ImageView) findViewById(2131757031);
        this.n = (ImageView) findViewById(2131757030);
        this.l = (TextView) findViewById(2131757020).findViewById(R.id.titlebar_title);
        this.l.setText(2131232629);
        this.k = findViewById(2131757020).findViewById(R.id.titlebar_left);
        this.k.setOnClickListener(new ad(this));
        this.j.setOnClickListener(this.s);
        b();
        ag.a(this.r);
    }

    protected void onPause() {
        super.onPause();
    }

    protected void onResume() {
        super.onResume();
    }

    private void b() {
        if (com.xunlei.xllib.a.b.a(getApplicationContext())) {
            if (com.xunlei.xllib.a.b.f(getApplicationContext())) {
                this.o = false;
                this.m.setImageResource(2130839237);
            } else {
                this.o = true;
                this.m.setImageResource(2130839218);
            }
            this.j.setEnabled(true);
            this.j.setTextColor(getResources().getColor(2131689861));
            this.d.setText(com.umeng.a.d);
            return;
        }
        this.o = false;
        this.j.setEnabled(false);
        this.j.setTextColor(861954406);
        this.d.setText(2131232623);
    }

    private void a(long j) {
        if (this.i != null) {
            ArcProgressBarView arcProgressBarView = this.i;
            float f = AutoScrollHelper.RELATIVE_UNSPECIFIED;
            if (j <= 65536 && j >= 0) {
                f = (((float) j) * 32.75f) / 65536.0f;
            } else if (j <= 131072) {
                f = ((((float) (j - 65536)) * 32.75f) / 65536.0f) + 32.75f;
            } else if (j <= 262144) {
                f = ((((float) (j - 131072)) * 32.75f) / 131072.0f) + 65.5f;
            } else if (j <= 524288) {
                f = ((((float) (j - 262144)) * 32.75f) / 262144.0f) + 98.25f;
            } else if (j <= 1048576) {
                f = ((((float) (j - 524288)) * 32.75f) / 524288.0f) + 131.0f;
            } else if (j <= 2097152) {
                f = ((((float) (j - 1048576)) * 32.75f) / 1048576.0f) + 163.75f;
            } else if (j <= 4194304) {
                f = ((((float) (j - 2097152)) * 32.75f) / 2097152.0f) + 196.5f;
            } else if (j <= 8388608) {
                f = ((((float) (j - 4194304)) * 32.75f) / 4194304.0f) + 229.25f;
            }
            arcProgressBarView.setProgress(f);
        }
    }

    private void b(long j) {
        int i = (int) (j / 1024);
        int i2 = (int) (j % 1024);
        if (i >= 1024) {
            this.n.setImageResource(2130839236);
            i = (int) ((j / 1024) / 1024);
            i2 = (int) ((j / 1024) % 1024);
            a(a.a, i / 100);
            a(a.b, (i % 100) / 10);
            a(a.c, i % 10);
            a(a.d, i2 / 100);
        } else if (i <= 0) {
            this.n.setImageResource(2130839235);
            a(a.d, 0);
            a(a.c, 0);
            a(a.a, 0);
            a(a.b, 0);
        } else {
            this.n.setImageResource(2130839235);
            a(a.a, i / 100);
            a(a.b, (i % 100) / 10);
            a(a.c, i % 10);
            a(a.d, i2 / 100);
        }
        ConstantState constantState = getResources().getDrawable(2130839224).getConstantState();
        if (!(this.e.getDrawable().getConstantState().equals(constantState) && this.f.getDrawable().getConstantState().equals(constantState) && this.g.getDrawable().getConstantState().equals(constantState))) {
            if (this.e.getDrawable().getConstantState().equals(constantState) && this.f.getDrawable().getConstantState().equals(constantState) && !this.g.getDrawable().getConstantState().equals(constantState)) {
                this.e.setVisibility(XZBDevice.Wait);
                this.f.setVisibility(XZBDevice.Wait);
                this.g.setVisibility(0);
                return;
            } else if (this.e.getDrawable().getConstantState().equals(constantState) && !this.f.getDrawable().getConstantState().equals(constantState)) {
                this.e.setVisibility(XZBDevice.Wait);
                this.f.setVisibility(0);
                this.g.setVisibility(0);
                return;
            }
        }
        this.e.setVisibility(0);
        this.f.setVisibility(0);
        this.g.setVisibility(0);
    }

    private void a(int i, int i2) {
        int i3;
        switch (i2) {
            case SpdyAgent.ACCS_TEST_SERVER:
                i3 = 2130839224;
                break;
            case SpdyAgent.ACCS_ONLINE_SERVER:
                i3 = 2130839225;
                break;
            case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                i3 = 2130839226;
                break;
            case XZBDevice.DOWNLOAD_LIST_FAILED:
                i3 = 2130839227;
                break;
            case XZBDevice.DOWNLOAD_LIST_ALL:
                i3 = 2130839228;
                break;
            case XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED:
                i3 = 2130839229;
                break;
            case com.xunlei.tdlive.R.styleable.Toolbar_contentInsetEnd:
                i3 = 2130839230;
                break;
            case com.xunlei.tdlive.R.styleable.Toolbar_contentInsetLeft:
                i3 = 2130839231;
                break;
            case XZBDevice.Wait:
                i3 = 2130839232;
                break;
            case XZBDevice.Pause:
                i3 = 2130839233;
                break;
            default:
                i3 = -1;
                break;
        }
        if (i3 != -1) {
            switch (AnonymousClass_1.a[i - 1]) {
                case SpdyAgent.ACCS_ONLINE_SERVER:
                    this.h.setImageResource(i3);
                case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                    this.g.setImageResource(i3);
                case XZBDevice.DOWNLOAD_LIST_FAILED:
                    this.e.setImageResource(i3);
                case XZBDevice.DOWNLOAD_LIST_ALL:
                    this.f.setImageResource(i3);
                default:
                    break;
            }
        }
    }

    protected void onDestroy() {
        ag.b(this.r);
        if (this.c != null) {
            this.c.a();
            this.c = null;
        }
        super.onDestroy();
    }
}
