package com.xunlei.tdlive;

import android.graphics.Point;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import com.xunlei.tdlive.base.BaseActivity;
import com.xunlei.tdlive.util.d;
import org.apache.commons.logging.impl.SimpleLog;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

public class VersionGuideActivity extends BaseActivity implements OnPageChangeListener, OnClickListener, OnTouchListener {
    private ViewPager a;
    private LinearLayout b;
    private int[] c;
    private float d;
    private float e;
    private int f;

    private final class a extends PagerAdapter {
        private a() {
        }

        public final int getCount() {
            return VersionGuideActivity.this.c.length;
        }

        public final boolean isViewFromObject(View view, Object obj) {
            return view == obj;
        }

        public final void destroyItem(ViewGroup viewGroup, int i, Object obj) {
            viewGroup.removeView((View) obj);
        }

        public final Object instantiateItem(ViewGroup viewGroup, int i) {
            View imageView = new ImageView(viewGroup.getContext());
            imageView.setScaleType(ScaleType.CENTER_CROP);
            imageView.setImageResource(VersionGuideActivity.this.c[i]);
            viewGroup.addView(imageView, -1, -1);
            return imageView;
        }
    }

    public VersionGuideActivity() {
        this.c = new int[0];
        this.d = 0.0f;
        this.e = 0.0f;
        this.f = 0;
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.xllive_activity_version_guide);
        ((WindowManager) getSystemService("window")).getDefaultDisplay().getSize(new Point());
        this.f = ViewConfiguration.get(this).getScaledTouchSlop();
        this.a = (ViewPager) findViewById(R.id.pages);
        this.a.setOnPageChangeListener(this);
        this.a.setAdapter(new a());
        this.b = (LinearLayout) findViewById(R.id.lvPageNodes);
        for (int i = 0; i < this.a.getAdapter().getCount(); i++) {
            View imageView = new ImageView(this);
            imageView.setImageResource(R.drawable.xllive_guide_page_node_selector);
            imageView.setTag(Integer.valueOf(i));
            LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
            if (i > 0) {
                layoutParams.leftMargin = (int) d.a(this, 22.0f);
            }
            imageView.setLayoutParams(layoutParams);
            this.b.addView(imageView);
        }
        onPageSelected(0);
        findViewById(R.id.touch_layout).setOnTouchListener(this);
        findViewById(R.id.skip).setOnClickListener(this);
        findViewById(R.id.enter).setOnClickListener(this);
    }

    public void onPageScrollStateChanged(int i) {
    }

    public void onPageScrolled(int i, float f, int i2) {
    }

    public void onPageSelected(int i) {
        for (int i2 = 0; i2 < this.b.getChildCount(); i2++) {
            boolean z;
            View childAt = this.b.getChildAt(i2);
            if (i2 == i) {
                z = true;
            } else {
                z = false;
            }
            childAt.setSelected(z);
        }
    }

    public void onClick(View view) {
        if (view.getId() == R.id.skip || view.getId() == R.id.enter) {
            finish();
        }
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        float x = motionEvent.getX();
        switch (action) {
            case MqttConnectOptions.MQTT_VERSION_DEFAULT:
                this.d = x;
                break;
            case SimpleLog.LOG_LEVEL_TRACE:
                if (this.d - x > ((float) this.f) && this.e != 0.0f) {
                    this.d = 0.0f;
                    this.e = 0.0f;
                    if (this.a.getCurrentItem() == this.a.getAdapter().getCount() - 1) {
                        finish();
                    }
                }
                break;
            case SimpleLog.LOG_LEVEL_DEBUG:
                this.e = x;
                break;
            case MqttConnectOptions.MQTT_VERSION_3_1:
                this.d = 0.0f;
                this.e = 0.0f;
                break;
        }
        findViewById(R.id.root).dispatchTouchEvent(motionEvent);
        return true;
    }

    public void finish() {
        finish(R.anim.xllive_slide_in_right, R.anim.xllive_slide_out_left);
    }
}
