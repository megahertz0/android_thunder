package com.xunlei.downloadprovider.personal.settings;

import android.app.Activity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.TextView;
import com.xunlei.downloadprovider.a.b;
import com.xunlei.downloadprovider.app.BaseActivity;
import com.xunlei.downloadprovider.commonview.dialog.q;
import com.xunlei.downloadprovider.commonview.f;
import com.xunlei.downloadprovider.member.login.LoginHelper;
import com.xunlei.xiazaibao.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;

public class AboutBoxActivity extends BaseActivity {
    private static final String a;
    private View b;
    private TextView c;
    private TextView d;
    private View e;
    private View f;

    static {
        a = AboutBoxActivity.class.getSimpleName();
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(2130968649);
        this.b = findViewById(R.id.titlebar_left);
        this.c = (TextView) findViewById(R.id.titlebar_title);
        this.d = (TextView) findViewById(2131755430);
        this.c.setText("\u5173\u4e8e");
        new f((Activity) this).j.setVisibility(XZBDevice.DOWNLOAD_LIST_ALL);
        this.d.setText(new StringBuilder("V").append(getResources().getString(com.xunlei.tdlive.R.string.version)).toString());
        this.d.setOnClickListener(new a(this));
        this.b.setOnClickListener(new b(this));
        this.e = findViewById(2131755435);
        this.e.setOnClickListener(new c(this));
        ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(-33475);
        TextView textView = (TextView) findViewById(2131755431);
        Object obj = "1\u3001\u6253\u5f00\u5fae\u4fe1\u70b9\u51fb\u53f3\u4e0a\u89d2\u201c\u6dfb\u52a0\u670b\u53cb\u201d";
        CharSequence spannableString = new SpannableString(obj);
        spannableString.setSpan(foregroundColorSpan, XZBDevice.Success, obj.length(), com.xunlei.tdlive.R.styleable.AppCompatTheme_actionModeCopyDrawable);
        textView.setText(spannableString);
        textView = (TextView) findViewById(2131755432);
        spannableString = new SpannableString("2\u3001\u8f93\u5165\u201cshoujixunlei\u201d\u6216\u201c\u624b\u673a\u8fc5\u96f7\u201d\u641c\u7d22");
        spannableString.setSpan(foregroundColorSpan, XZBDevice.DOWNLOAD_LIST_ALL, com.xunlei.tdlive.R.styleable.Toolbar_collapseIcon, com.xunlei.tdlive.R.styleable.AppCompatTheme_actionModeCopyDrawable);
        spannableString.setSpan(new ForegroundColorSpan(-33475), com.xunlei.tdlive.R.styleable.Toolbar_collapseContentDescription, com.xunlei.tdlive.R.styleable.AppCompatTheme_actionMenuTextAppearance, com.xunlei.tdlive.R.styleable.AppCompatTheme_actionModeCopyDrawable);
        textView.setText(spannableString);
        textView = (TextView) findViewById(2131755433);
        spannableString = new SpannableString("3\u3001\u70b9\u51fb\u201c\u5173\u6ce8\u201d\u5373\u53ef\u8fdb\u5165\u8c03\u620f\u857e\u59b9\u6a21\u5f0f");
        spannableString.setSpan(foregroundColorSpan, XZBDevice.DOWNLOAD_LIST_ALL, XZBDevice.Wait, com.xunlei.tdlive.R.styleable.AppCompatTheme_actionModeCopyDrawable);
        textView.setText(spannableString);
        this.f = findViewById(2131755434);
        this.f.setOnClickListener(new f(this));
    }

    public void onResume() {
        super.onResume();
    }

    protected void onPause() {
        super.onPause();
    }

    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    static /* synthetic */ void a(AboutBoxActivity aboutBoxActivity) {
        CharSequence charSequence = "\u4fe1\u606f";
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\u4e0b\u8f7d\u5e93 : 1.0.0.9");
        stringBuilder.append("\n\u64ad\u653e\u5668 : ").append("1.1.0.28");
        try {
            stringBuilder.append("\r\npeer : ").append(b.d());
            stringBuilder.append("\r\nimei : ").append(b.f());
            stringBuilder.append("\r\nchannel : ").append(b.g());
            stringBuilder.append("\r\nuid : ").append(LoginHelper.a().j);
            stringBuilder.append("\r\nversionCode : ").append(b.x());
        } catch (Exception e) {
        }
        q qVar = new q(aboutBoxActivity);
        qVar.setTitle(charSequence);
        qVar.a(stringBuilder.toString());
        qVar.d("\u786e\u5b9a");
        qVar.b(new g(aboutBoxActivity, qVar));
        qVar.show();
    }
}
