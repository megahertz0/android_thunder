package com.xunlei.tdlive;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import com.xunlei.tdlive.base.BaseActivity;
import com.xunlei.xllib.R;
import org.android.spdy.SpdyProtocol;

public class VerifyActivity extends BaseActivity implements OnClickListener {
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.xllive_activity_verify);
        setTitle("\u5b9e\u540d\u8ba4\u8bc1");
        setLeftVisible(true);
        setLeftClickListener(this);
        setLeftDrawable(getResources().getDrawable(R.drawable.xllive_ic_back));
        TextView textView = (TextView) findViewById(R.id.protocol);
        textView.getPaint().setFlags(SpdyProtocol.PUBKEY_SEQ_ADASH);
        textView.getPaint().setAntiAlias(true);
        textView.setOnClickListener(this);
        findViewById(R.id.photo_add).setOnClickListener(this);
        findViewById(R.id.photo_add2).setOnClickListener(this);
    }

    public void onClick(View view) {
        if (view == this.mTitleBarLeft) {
            finish();
        } else if (view.getId() == R.id.photo_add || view.getId() == R.id.photo_add2) {
            startActivityForResult(new Intent(this, PhotoSelectActivity.class).putExtra("capture_image_tip", "\u62cd\u6444\u7167\u7247").putExtra("image_crop", false), R.styleable.AppCompatTheme_buttonStyleSmall);
        } else if (view.getId() != R.id.commit && view.getId() == R.id.protocol) {
            WebBrowserActivity.start(this, "http://h5.live.xunlei.com/android/tos.html", "\u300a\u8fc5\u96f7\u76f4\u64ad\u4e3b\u64ad\u5e73\u53f0\u534f\u8bae\u300b", false);
        }
    }
}
