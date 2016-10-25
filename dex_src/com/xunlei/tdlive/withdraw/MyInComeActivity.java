package com.xunlei.tdlive.withdraw;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.xunlei.tdlive.R;
import com.xunlei.tdlive.base.BaseActivity;
import com.xunlei.tdlive.util.XLog;
import com.xunlei.tdlive.util.d;
import com.xunlei.tdlive.withdraw.a.a;
import java.util.HashMap;
import org.android.spdy.SpdyProtocol;

public class MyInComeActivity extends BaseActivity implements OnClickListener, a {
    private static HashMap<Class<?>, Integer> e;
    private TextView a;
    private TextView b;
    private HashMap<String, Object> c;
    private Class<?> d;

    static {
        HashMap hashMap = new HashMap();
        e = hashMap;
        hashMap.put(f.class, Integer.valueOf(R.string.mms_verify));
        e.put(o.class, Integer.valueOf(R.string.wx_bind));
        e.put(l.class, Integer.valueOf(R.string.withdraw_record));
        e.put(n.class, Integer.valueOf(R.string.verify_ok_to_withdraw));
        e.put(b.class, Integer.valueOf(R.string.wx_bind));
        e.put(k.class, Integer.valueOf(R.string.verify_ok_to_withdraw));
        e.put(c.class, Integer.valueOf(R.string.my_income));
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.xllive_activity_shell);
        b();
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, b(c.class)).commit();
        this.d = c.class;
    }

    private void b() {
        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.titlebar);
        this.a = (TextView) relativeLayout.findViewById(R.id.tvTitle);
        relativeLayout.findViewById(R.id.ivBack).setOnClickListener(this);
        ((TextView) findViewById(R.id.tvTitle)).setText(R.string.my_income);
        getLayoutInflater().inflate(R.layout.xllive_save_button, relativeLayout);
        TextView textView = (TextView) relativeLayout.findViewById(R.id.tvBtn);
        LayoutParams layoutParams = textView.getLayoutParams();
        layoutParams.width = (int) d.a(this, 64.0f);
        layoutParams.height = (int) d.a(this, 21.0f);
        textView.setLayoutParams(layoutParams);
        textView.setText(R.string.withdraw_record);
        textView.setOnClickListener(this);
        this.b = textView;
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.tvBtn) {
            a(l.class, true);
        } else if (id == R.id.ivBack) {
            onBackPressed();
        }
    }

    private Fragment b(Class<? extends Fragment> cls) {
        try {
            return (Fragment) cls.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void a(Class<? extends a> cls, boolean z, Bundle bundle) {
        Fragment b = b(cls);
        if (b == null) {
            XLog.e("MyInComeActivity", "fragment create error.");
            return;
        }
        this.d = cls;
        if (bundle != null) {
            b.setArguments(bundle);
        }
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, b).commit();
    }

    public void a(Class<? extends a> cls, boolean z) {
        a(cls, z, null);
    }

    public void a(Class<?> cls) {
        this.a.setText(((Integer) e.get(cls)).intValue());
        this.b.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
    }

    public Object a(String str) {
        return this.c == null ? null : this.c.get(str);
    }

    public void a(String str, Object obj) {
        if (this.c == null) {
            this.c = new HashMap();
        }
        this.c.put(str, obj);
    }

    public void a() {
        a(c.class, false);
    }

    public void onBackPressed() {
        if (c.class.equals(this.d)) {
            finish();
        } else {
            a();
        }
    }
}
