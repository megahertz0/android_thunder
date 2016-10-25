package com.umeng.socialize.editorpage;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask.Status;
import android.os.Bundle;
import android.os.Handler;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager.LayoutParams;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.umeng.socialize.Config;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.bean.UMLocation;
import com.umeng.socialize.common.ResContainer;
import com.umeng.socialize.common.SocializeConstants;
import com.umeng.socialize.editorpage.location.a;
import com.umeng.socialize.editorpage.location.b;
import com.umeng.socialize.editorpage.location.d;
import com.umeng.socialize.utils.Log;
import com.umeng.socialize.utils.SocializeUtils;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.io.File;
import java.util.Set;

public class ShareActivity extends Activity implements OnClickListener {
    public static final int CANCLE_RESULTCODE = 1000;
    public static final String FOLLOW_FILE_NAME = "umeng_follow";
    public static final String KEY_AT = "at";
    public static final String KEY_FOLLOW = "follow_";
    public static final String KEY_LOCATION = "location";
    public static final String KEY_PIC = "pic";
    public static final String KEY_PLATFORM = "media";
    public static final String KEY_TEXT = "txt";
    public static final String KEY_TITLE = "title";
    public static final int REQUEST_CODE = 1229;
    private static final String c = "ShareActivity";
    private static int d;
    private SHARE_MEDIA A;
    private a B;
    private UMLocation C;
    private int D;
    private boolean E;
    private Dialog F;
    private Set<String> G;
    private b H;
    protected ImageView a;
    TextWatcher b;
    private String e;
    private String f;
    private String g;
    private boolean h;
    private boolean i;
    private boolean j;
    private ResContainer k;
    private Button l;
    private Button m;
    private EditText n;
    private ImageButton o;
    private ImageButton p;
    private View q;
    private View r;
    private View s;
    private TextView t;
    private RelativeLayout u;
    private CheckBox v;
    private KeyboardListenRelativeLayout w;
    private ProgressBar x;
    private Context y;
    private boolean z;

    public ShareActivity() {
        this.E = false;
        this.G = null;
        this.H = null;
        this.b = new b(this);
    }

    static {
        d = 140;
    }

    protected void onCreate(Bundle bundle) {
        boolean z;
        this.k = ResContainer.get(this);
        this.E = SocializeUtils.isFloatWindowStyle(this);
        if (!this.E) {
            setTheme(this.k.style("Theme.UMDefault"));
        }
        super.onCreate(bundle);
        this.y = this;
        setContentView(this.k.layout("umeng_socialize_post_share"));
        LayoutParams attributes = getWindow().getAttributes();
        attributes.softInputMode = 16;
        if (this.E) {
            int[] floatWindowSize = SocializeUtils.getFloatWindowSize(this.y);
            attributes.width = floatWindowSize[0];
            attributes.height = floatWindowSize[1];
        }
        getWindow().setAttributes(attributes);
        this.w = (KeyboardListenRelativeLayout) findViewById(this.k.id("umeng_socialize_share_root"));
        this.w.a(new a(this));
        Bundle extras = getIntent().getExtras();
        this.A = a(extras.getString(KEY_PLATFORM));
        if (this.A == SHARE_MEDIA.RENREN) {
            d = 120;
        } else {
            d = 140;
        }
        this.e = extras.getString(KEY_TITLE);
        this.f = extras.getString(KEY_TEXT);
        this.g = extras.getString(KEY_PIC);
        this.h = extras.getBoolean(KEY_FOLLOW, false);
        this.i = extras.getBoolean(KEY_AT);
        this.i = false;
        if (extras.getBoolean(KEY_LOCATION) && Config.ShareLocation) {
            z = true;
        } else {
            z = false;
        }
        this.j = z;
        c();
        if (this.j) {
            b();
        }
    }

    private SHARE_MEDIA a(String str) {
        if (str.equals("TENCENT")) {
            return SHARE_MEDIA.TENCENT;
        }
        if (str.equals("RENREN")) {
            return SHARE_MEDIA.RENREN;
        }
        return str.equals("DOUBAN") ? SHARE_MEDIA.DOUBAN : SHARE_MEDIA.SINA;
    }

    protected void onResume() {
        if (this.j) {
            e();
        }
        this.n.requestFocus();
        super.onResume();
    }

    private void b() {
        this.B = new a();
        d dVar = new d();
        dVar.a((Context) this);
        this.B.a(dVar);
        this.B.a((Context) this);
    }

    private void c() {
        ((TextView) findViewById(this.k.id("umeng_socialize_title_bar_middleTv"))).setText(this.e);
        this.l = (Button) findViewById(this.k.id("umeng_socialize_title_bar_leftBt"));
        this.m = (Button) findViewById(this.k.id("umeng_socialize_title_bar_rightBt"));
        this.l.setOnClickListener(this);
        this.m.setOnClickListener(this);
        this.n = (EditText) findViewById(this.k.id("umeng_socialize_share_edittext"));
        if (!TextUtils.isEmpty(this.f)) {
            this.n.setText(this.f);
            this.n.setSelection(this.f.length());
        }
        this.n.addTextChangedListener(this.b);
        this.t = (TextView) findViewById(this.k.id("umeng_socialize_share_word_num"));
        this.z = d();
        if (this.j) {
            findViewById(this.k.id("umeng_socialize_share_location")).setVisibility(0);
            this.p = (ImageButton) findViewById(this.k.id("umeng_socialize_location_ic"));
            this.p.setOnClickListener(this);
            this.p.setVisibility(0);
            this.p.setImageResource(this.k.drawable("umeng_socialize_location_off"));
            this.q = findViewById(this.k.id("umeng_socialize_location_progressbar"));
        }
        if (this.i) {
            this.o = (ImageButton) findViewById(this.k.id("umeng_socialize_share_at"));
            this.o.setVisibility(0);
            this.o.setOnClickListener(this);
        }
        if (this.g != null) {
            findViewById(this.k.id("umeng_socialize_share_image")).setVisibility(0);
            this.a = (ImageView) findViewById(this.k.id("umeng_socialize_share_previewImg"));
            this.r = findViewById(this.k.id("umeng_socialize_share_previewImg_remove"));
            this.r.setOnClickListener(this);
            this.a.setVisibility(0);
            if (this.g.equals("video")) {
                this.a.setImageResource(ResContainer.getResourceId(this.y, "drawable", "umeng_socialize_share_video"));
            } else if (this.g.equals("music")) {
                this.a.setImageResource(ResContainer.getResourceId(this.y, "drawable", "umeng_socialize_share_music"));
            } else {
                this.a.setImageURI(Uri.fromFile(new File(this.g)));
            }
        }
        if (this.h) {
            this.v = (CheckBox) findViewById(this.k.id("umeng_socialize_follow_check"));
            this.v.setOnClickListener(this);
            this.v.setVisibility(0);
        }
    }

    private void a(View view) {
        String toString = this.n.getText().toString();
        if (TextUtils.isEmpty(toString.trim())) {
            Toast.makeText(this, "\u8f93\u5165\u5185\u5bb9\u4e3a\u7a7a...", 0).show();
        } else if (SocializeUtils.countContentLength(toString) > d) {
            Toast.makeText(this, "\u8f93\u5165\u5185\u5bb9\u8d85\u8fc7140\u4e2a\u5b57.", 0).show();
        } else if (this.z) {
            Toast.makeText(this.y, "\u8d85\u51fa\u6700\u5927\u5b57\u6570\u9650\u5236....", 0).show();
        } else {
            Intent intent = new Intent();
            Bundle bundle = new Bundle();
            bundle.putString(KEY_TEXT, toString);
            bundle.putString(KEY_PIC, this.g);
            bundle.putBoolean(KEY_FOLLOW, this.h);
            bundle.putSerializable(KEY_LOCATION, this.C);
            intent.putExtras(bundle);
            setResult(-1, intent);
            a();
        }
    }

    public void onCancel(View view) {
        setResult(CANCLE_RESULTCODE);
        a();
    }

    private void b(View view) {
        this.g = null;
        findViewById(this.k.id("umeng_socialize_share_image")).setVisibility(XZBDevice.Wait);
    }

    public void onAtFriends(View view) {
        if (this.F == null) {
            this.F = f();
        }
        if (!this.F.isShowing()) {
            this.F.show();
        }
    }

    public void onFollowStatChanged(View view) {
        this.h = this.v.isChecked();
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == this.k.id("umeng_socialize_share_previewImg_remove")) {
            b(view);
        } else if (id == this.k.id("umeng_socialize_title_bar_rightBt")) {
            a(view);
        } else if (id == this.k.id("umeng_socialize_title_bar_leftBt")) {
            onCancel(view);
        } else if (id == this.k.id("umeng_socialize_share_at")) {
            onAtFriends(view);
        } else if (id == this.k.id("umeng_socialize_location_ic")) {
            c(view);
        } else if (id == this.k.id("umeng_socialize_follow_check")) {
            onFollowStatChanged(view);
        }
    }

    private void a(int i, Bitmap bitmap) {
        try {
            this.a.setImageBitmap(bitmap);
        } catch (Exception e) {
            this.a.setImageResource(i);
        }
        this.a.setVisibility(0);
        this.r.setVisibility(0);
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i == 4 && keyEvent.getRepeatCount() == 0) {
            setResult(CANCLE_RESULTCODE);
        }
        return super.onKeyDown(i, keyEvent);
    }

    protected void a() {
        if (this.D == -3) {
            ((InputMethodManager) getSystemService("input_method")).hideSoftInputFromWindow(getWindow().peekDecorView().getWindowToken(), 0);
            new Handler().postDelayed(new c(this), 500);
            return;
        }
        finish();
    }

    private boolean d() {
        int countContentLength = d - SocializeUtils.countContentLength(this.n.getText().toString());
        Log.d(c, new StringBuilder("onTextChanged ").append(countContentLength).append("   ").append(SocializeUtils.countContentLength(this.n.getText().toString())).toString());
        this.t.setText(String.valueOf(countContentLength));
        return countContentLength < 0;
    }

    protected void onStop() {
        super.onStop();
    }

    protected void onDestroy() {
        if (this.B != null) {
            this.B.a();
        }
        if (this.H != null) {
            this.H.cancel(true);
        }
        super.onDestroy();
    }

    private void c(View view) {
        if (this.C != null) {
            new Builder(this).setMessage("\u662f\u5426\u5220\u9664\u4f4d\u7f6e\u4fe1\u606f\uff1f").setCancelable(false).setPositiveButton("\u662f", new e(this)).setNegativeButton("\u5426", new d(this)).create().show();
        } else {
            e();
        }
    }

    private void e() {
        if (this.B == null) {
            b();
        }
        if (!(this.H == null || this.H.getStatus() == Status.FINISHED)) {
            this.H.cancel(true);
        }
        this.H = new f(this, this.B);
        this.H.execute(new Void[0]);
    }

    private void a(boolean z) {
        if (z) {
            this.p.setVisibility(XZBDevice.Wait);
            this.q.setVisibility(0);
        } else if (this.C == null) {
            this.p.setImageResource(this.k.drawable("umeng_socialize_location_off"));
            this.p.setVisibility(0);
            this.q.setVisibility(XZBDevice.Wait);
        } else {
            this.p.setImageResource(this.k.drawable("umeng_socialize_location_on"));
            this.p.setVisibility(0);
            this.q.setVisibility(XZBDevice.Wait);
        }
    }

    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        if (!SocializeConstants.BACKKEY_COMPLETE_CLOSE || keyEvent.getKeyCode() != 4) {
            return super.dispatchKeyEvent(keyEvent);
        }
        new Handler().postDelayed(new g(this), 400);
        return true;
    }

    public void inputAt(SpannableString spannableString) {
        this.n.getText().insert(this.n.getSelectionStart(), spannableString);
    }

    private Dialog f() {
        try {
            return (Dialog) Class.forName("com.umeng.socialize.view.ShareAtDialogV2").getConstructor(new Class[]{ShareActivity.class, SHARE_MEDIA.class, String.class}).newInstance(new Object[]{this, this.A, Config.UID});
        } catch (Exception e) {
            Log.w(c, "\u5982\u679c\u9700\u8981\u4f7f\u7528\u2018@\u597d\u53cb\u2019\u529f\u80fd\uff0c\u8bf7\u6dfb\u52a0\u76f8\u5e94\u7684jar\u6587\u4ef6\uff1b\u5426\u5219\u5ffd\u7565\u6b64\u4fe1\u606f", e);
            return null;
        }
    }
}
