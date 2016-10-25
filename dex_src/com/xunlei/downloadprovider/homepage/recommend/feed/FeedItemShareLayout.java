package com.xunlei.downloadprovider.homepage.recommend.feed;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import com.xunlei.downloadprovider.R;
import com.xunlei.downloadprovider.a.g;
import com.xunlei.downloadprovidershare.d.a;
import com.xunlei.downloadprovidershare.data.ShareBean;
import org.apache.commons.logging.impl.SimpleLog;

public class FeedItemShareLayout extends FrameLayout {
    a a;
    private ao b;
    private OnClickListener c;
    private AnimationSet d;
    private ImageView e;
    private ImageView f;
    private ImageView g;
    private ImageView h;
    private boolean i;
    private OnClickListener j;

    public FeedItemShareLayout(Context context) {
        super(context);
        this.i = true;
        this.j = new ab(this);
        this.a = new ac(this);
        a(context);
        a();
    }

    public FeedItemShareLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.i = true;
        this.j = new ab(this);
        this.a = new ac(this);
        a(context);
        a();
    }

    public FeedItemShareLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.i = true;
        this.j = new ab(this);
        this.a = new ac(this);
        a(context);
        a();
    }

    private void a(Context context) {
        LayoutInflater.from(context).inflate(R.layout.media_share_layout, this, true);
        findViewById(R.id.replay_btn).setOnClickListener(this.j);
        this.e = (ImageView) findViewById(R.id.iv_weixin);
        this.e.setOnClickListener(this.j);
        this.f = (ImageView) findViewById(R.id.iv_wxfriend);
        this.f.setOnClickListener(this.j);
        this.g = (ImageView) findViewById(R.id.iv_qq);
        this.g.setOnClickListener(this.j);
        this.h = (ImageView) findViewById(R.id.iv_qzone);
        this.h.setOnClickListener(this.j);
    }

    private void a() {
        this.d = new AnimationSet(false);
        Animation translateAnimation = new TranslateAnimation(0.0f, 0.0f, 0.0f, (float) g.a(getContext(), -4.0f));
        translateAnimation.setRepeatCount(1);
        translateAnimation.setRepeatMode(SimpleLog.LOG_LEVEL_DEBUG);
        translateAnimation.setDuration(200);
        Animation scaleAnimation = new ScaleAnimation(1.0f, 1.25f, 1.0f, 1.25f, 1, 0.5f, 1, 0.5f);
        scaleAnimation.setDuration(200);
        scaleAnimation.setRepeatCount(1);
        scaleAnimation.setRepeatMode(SimpleLog.LOG_LEVEL_DEBUG);
        this.d.addAnimation(translateAnimation);
        this.d.addAnimation(scaleAnimation);
    }

    public void setClickReplayListener(OnClickListener onClickListener) {
        this.c = onClickListener;
    }

    private ShareBean getShareBean() {
        ao aoVar = this.b;
        ShareBean shareBean = new ShareBean(null, aoVar.a(), aoVar.d, aoVar.b, null);
        shareBean.g = true;
        return shareBean;
    }

    private Activity getActivity() {
        return (Activity) getContext();
    }

    public void setFeedVideoItemModel(ao aoVar) {
        this.b = aoVar;
    }

    public void setIsFeedType(boolean z) {
        this.i = z;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        super.onTouchEvent(motionEvent);
        return true;
    }
}
