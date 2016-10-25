package com.xunlei.tdlive.usercenter;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.net.Uri;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.xunlei.tdlive.R;
import com.xunlei.tdlive.control.RoundImageView;
import com.xunlei.tdlive.util.a;
import com.xunlei.xiazaibao.sdk.XZBDevice;

public class UserInfoTop extends FrameLayout {
    private RoundImageView a;
    private TextView b;
    private TextView c;
    private TextView d;
    private TextView e;
    private String f;
    private ImageView g;

    public UserInfoTop(Context context) {
        super(context);
        a(null);
    }

    public UserInfoTop(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(attributeSet);
    }

    public UserInfoTop(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(attributeSet);
    }

    private void a(AttributeSet attributeSet) {
        View inflate = inflate(getContext(), R.layout.xllive_usercenter_top, this);
        this.a = (RoundImageView) inflate.findViewById(R.id.avatar);
        this.b = (TextView) inflate.findViewById(R.id.signature);
        this.c = (TextView) inflate.findViewById(R.id.nickname);
        this.d = (TextView) inflate.findViewById(R.id.tvCoinNum);
        this.e = (TextView) inflate.findViewById(R.id.uuid);
        this.g = (ImageView) inflate.findViewById(R.id.tvLevelImage);
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R.styleable.UserInfoTop);
            boolean z = obtainStyledAttributes.getBoolean(R.styleable.UserInfoTop_showSendInfo, false);
            boolean z2 = obtainStyledAttributes.getBoolean(R.styleable.UserInfoTop_showSign, true);
            boolean z3 = obtainStyledAttributes.getBoolean(R.styleable.UserInfoTop_showUUID, true);
            obtainStyledAttributes.recycle();
            setShowSign(z2);
            setShowSendInfo(z);
            setShowUuid(z3);
        }
    }

    public void setShowSign(boolean z) {
        this.b.setVisibility(z ? 0 : XZBDevice.Wait);
    }

    public void setShowUuid(boolean z) {
        this.e.setVisibility(z ? 0 : XZBDevice.Wait);
    }

    public void setShowSendInfo(boolean z) {
        findViewById(R.id.lvSendCoins).setVisibility(z ? 0 : XZBDevice.Wait);
    }

    public void setSex(int i) {
        int i2 = i == 1 ? R.drawable.xllive_sex_male : i == 2 ? R.drawable.xllive_sex_female : -1;
        if (i2 != -1) {
            this.c.setCompoundDrawablesWithIntrinsicBounds(0, 0, i2, 0);
        }
    }

    public void setLevel(int i, String str, String str2) {
        this.g.setVisibility(0);
        a.a(getContext()).a(this.g, str2, a.b(getContext()), new u(this));
    }

    public void setUUID(String str) {
        this.e.setText(new StringBuilder("\u8fc5\u96f7\u76f4\u64ad\u53f7: ").append(str).toString());
    }

    public void setNickname(String str) {
        this.c.setText(str);
    }

    public CharSequence getNickName() {
        return this.c.getText();
    }

    public void setCoinNum(int i) {
        this.d.setText(String.valueOf(i));
    }

    public void setSignature(String str) {
        if (TextUtils.isEmpty(str)) {
            this.b.setText(R.string.signature_default);
        } else {
            this.b.setText(str);
        }
    }

    public CharSequence getSignature() {
        return this.b.getText();
    }

    public void setAvatar(Bitmap bitmap) {
        this.a.setImageBitmap(bitmap);
    }

    public void setAvatar(Uri uri) {
        this.f = uri.toString();
        a.a(getContext()).a(this.a, uri.toString());
    }

    public String getAvatarUrl() {
        return this.f;
    }

    public void setAvatarClickListener(OnClickListener onClickListener) {
        this.a.setOnClickListener(onClickListener);
    }
}
