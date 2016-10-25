package com.xunlei.tdlive.play.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import com.xunlei.tdlive.R;
import com.xunlei.tdlive.util.d;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

public class PlayButtonBar extends LinearLayout {
    private ImageView a;
    public View chat_btn;
    public View full_screen_btn;
    public View gif_btn;
    public ImageView heart;
    public View laud_btn;
    public View laud_btn_bk;
    public View share_btn;

    public PlayButtonBar(Context context) {
        super(context);
        a();
    }

    public PlayButtonBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
    }

    public PlayButtonBar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a();
    }

    private void a() {
        LayoutInflater.from(getContext()).inflate(R.layout.xllive_play_button_bar, this, true);
        this.chat_btn = findViewById(R.id.chat_btn);
        this.share_btn = findViewById(R.id.share_btn);
        this.gif_btn = findViewById(R.id.gif_btn);
        this.a = (ImageView) findViewById(R.id.gift_image);
        this.full_screen_btn = findViewById(R.id.full_screen_btn);
        this.laud_btn = findViewById(R.id.laud_btn);
        this.laud_btn_bk = findViewById(R.id.laud_btn_bk);
        this.heart = (ImageView) findViewById(R.id.heart);
    }

    public void setLaudBitmap(Bitmap bitmap) {
        this.heart.setImageBitmap(bitmap);
    }

    public void setEnabled(boolean z, boolean z2, boolean z3, boolean z4, boolean z5) {
        this.chat_btn.setEnabled(z);
        this.share_btn.setEnabled(z2);
        this.gif_btn.setEnabled(z3);
        this.full_screen_btn.setEnabled(z4);
        this.laud_btn_bk.setEnabled(z5);
    }

    public void showAnimation(boolean z) {
        float f = 1.0f;
        float f2 = z ? 1.0f : 0.0f;
        if (z) {
            f = 0.0f;
        }
        Animation translateAnimation = new TranslateAnimation(1, 0.0f, 1, 0.0f, 1, f2, 1, f);
        translateAnimation.setDuration(300);
        startAnimation(translateAnimation);
        setVisibility(z ? 0 : MqttConnectOptions.MQTT_VERSION_3_1_1);
    }

    public boolean showRedFlagForGiftBtn(boolean z) {
        Object tag = this.a.getTag();
        if (z) {
            this.a.setTag("red");
            this.a.setBackgroundResource(R.drawable.xllive_gift_btn_ani);
            this.a.setImageBitmap(null);
            ((AnimationDrawable) this.a.getBackground()).start();
        } else {
            this.a.setTag(null);
            this.a.setBackgroundResource(0);
            this.a.setImageResource(R.drawable.xllive_gift_btn_selector);
        }
        return tag != null;
    }

    public void showTipOnGiftBtn(String str) {
        try {
            FrameLayout frameLayout = (FrameLayout) LayoutInflater.from(getContext()).inflate(R.layout.xllive_gift_tip, null, false);
            ((TextView) frameLayout.findViewById(R.id.tvTipText)).setText(str);
            PopupWindow popupWindow = new PopupWindow(frameLayout, -2, -2);
            popupWindow.setBackgroundDrawable(new BitmapDrawable());
            popupWindow.setOutsideTouchable(true);
            popupWindow.setFocusable(true);
            popupWindow.showAsDropDown(this.a, -((int) d.a(getContext(), 92.0f)), (0 - this.a.getHeight()) - ((int) d.a(getContext(), 62.0f)));
            this.a.postDelayed(new t(this, popupWindow), 6000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
