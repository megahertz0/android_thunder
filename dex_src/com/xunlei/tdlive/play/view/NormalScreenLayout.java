package com.xunlei.tdlive.play.view;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Point;
import android.support.v4.widget.ExploreByTouchHelper;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewStub;
import android.view.animation.AlphaAnimation;
import android.view.animation.AnimationSet;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.xunlei.tdlive.R;
import com.xunlei.tdlive.base.ViewAttriWrapper;
import com.xunlei.tdlive.control.RoundImageView;
import com.xunlei.tdlive.control.ToggleButton;
import com.xunlei.tdlive.control.VisitorHListView;
import com.xunlei.tdlive.play.view.a.a;
import com.xunlei.tdlive.sdk.IHost;
import com.xunlei.tdlive.util.XLog;
import com.xunlei.tdlive.util.d;
import com.xunlei.tdlive.util.v;
import com.xunlei.tdlive.view.AnimationSurfaceView;
import com.xunlei.tdlive.view.GiftReminderView;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import org.android.spdy.SpdyAgent;

public class NormalScreenLayout extends FrameLayout {
    private TextView a;
    private TextView b;
    private long c;
    private a d;
    private int e;
    private boolean f;
    private ViewAttriWrapper g;
    public AnimationSurfaceView mAnimationView;
    public ToggleButton mAttendButton;
    public View mBottomLayout;
    public View mChatEditLayout;
    public ConnectMicView mConnectMicView;
    public View mContainer;
    public GiftReminderView mGiftReminderView;
    public RoundImageView mGradeImage;
    public View mHeadArea;
    public RoundImageView mHeadImage;
    public InRoomBar mInRoombar;
    public EditText mInputMessageView;
    public LevelUpgradeBar mLevelUpgradeBar;
    public ChatListView mMessagesView;
    public TextView mOnlineCount;
    public View mOnlineCountLayout;
    public PlayButtonBar mPlayButtonLayout;
    public View mPopularityArea;
    public TextView mPopularityCount;
    public PublishButtonBar mPublishButtonLayout;
    public ViewStub mRePlayButtonBarStub;
    public RePlayButtonBar mRePlayButtonLayout;
    public Button mSendButton;
    public View mShareButton;
    public SignalLevelView mSignalLevelView;
    public TextView mStateTextView;
    public VisitorHListView mVisitorList;
    public View mVistorArea;
    public View rlPopularityArea;

    public NormalScreenLayout(Context context) {
        super(context);
        this.c = 0;
        this.d = null;
        try {
            init();
        } catch (Exception e) {
        }
    }

    public NormalScreenLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.c = 0;
        this.d = null;
        try {
            init();
        } catch (Exception e) {
        }
    }

    public NormalScreenLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.c = 0;
        this.d = null;
        try {
            init();
        } catch (Exception e) {
        }
    }

    public void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.xllive_normal_screen_layout, this);
        this.mContainer = findViewById(R.id.container);
        this.mBottomLayout = findViewById(R.id.bottom_layout);
        this.mHeadImage = (RoundImageView) findViewById(R.id.head_image);
        this.mGradeImage = (RoundImageView) findViewById(R.id.experience_grade_image);
        this.a = (TextView) findViewById(R.id.host_name);
        this.b = (TextView) findViewById(R.id.host_name2);
        this.mOnlineCount = (TextView) findViewById(R.id.tv_online_num);
        this.mOnlineCountLayout = findViewById(R.id.online_num_layout);
        this.mVistorArea = findViewById(R.id.visitor_area);
        this.mHeadArea = findViewById(R.id.head_area);
        this.mPopularityArea = findViewById(R.id.popularity_area);
        this.rlPopularityArea = findViewById(R.id.rl_popularity_area);
        this.mPopularityCount = (TextView) findViewById(R.id.popularity_count);
        this.mAttendButton = (ToggleButton) findViewById(R.id.attend_button);
        this.mVisitorList = (VisitorHListView) findViewById(R.id.visitor_list);
        this.mPlayButtonLayout = (PlayButtonBar) findViewById(R.id.play_btn_layout);
        this.mRePlayButtonBarStub = (ViewStub) findViewById(R.id.replay_btn_layout_stub);
        this.mPublishButtonLayout = (PublishButtonBar) findViewById(R.id.publish_btn_layout);
        this.mChatEditLayout = findViewById(R.id.chat_edit_layout);
        this.mChatEditLayout.setOnClickListener(null);
        this.mAnimationView = (AnimationSurfaceView) findViewById(R.id.senior_gift_surfaceview);
        this.mMessagesView = (ChatListView) findViewById(R.id.chat_list);
        this.mInputMessageView = (EditText) findViewById(R.id.chat_edit);
        this.mSendButton = (Button) findViewById(R.id.chat_send);
        this.mConnectMicView = (ConnectMicView) findViewById(R.id.connect_mic_button);
        this.mSignalLevelView = (SignalLevelView) findViewById(R.id.fl_signal_level);
        this.mGiftReminderView = (GiftReminderView) findViewById(R.id.gift_remind_view);
        this.mInRoombar = (InRoomBar) findViewById(R.id.in_room_bar);
        this.mLevelUpgradeBar = (LevelUpgradeBar) findViewById(R.id.level_upgrade_bar);
        this.mAttendButton.setTextOn("\u5df2\u5173\u6ce8");
        this.mAttendButton.setTextOnColor(-1);
        this.mAttendButton.setTextOff("\u5173\u6ce8");
        this.mAttendButton.setTextOffColor(-166848);
        this.g = new ViewAttriWrapper(this.mAttendButton);
        this.e = this.g.getWidth();
    }

    public void showBottomBar(a aVar) {
        XLog.i("NormalScreenLayout", new StringBuilder("showBottomBar ").append(aVar).toString());
        switch (s.a[aVar.ordinal()]) {
            case SpdyAgent.ACCS_ONLINE_SERVER:
                showViewGradually(this.mChatEditLayout);
                this.mPlayButtonLayout.setVisibility(XZBDevice.Wait);
                this.mPublishButtonLayout.setVisibility(XZBDevice.Wait);
            case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                showViewGradually(this.mPlayButtonLayout);
                this.mChatEditLayout.setVisibility(XZBDevice.Wait);
                this.mPublishButtonLayout.setVisibility(XZBDevice.Wait);
            case XZBDevice.DOWNLOAD_LIST_FAILED:
                showViewGradually(this.mPublishButtonLayout);
                this.mChatEditLayout.setVisibility(XZBDevice.Wait);
                this.mPlayButtonLayout.setVisibility(XZBDevice.Wait);
                this.mSignalLevelView.setVisibility(0);
                this.mPublishButtonLayout.setVisibility(0);
            case XZBDevice.DOWNLOAD_LIST_ALL:
                this.mPublishButtonLayout.setVisibility(XZBDevice.Wait);
                this.mChatEditLayout.setVisibility(XZBDevice.Wait);
                this.mPlayButtonLayout.setVisibility(XZBDevice.Wait);
                this.mPublishButtonLayout.setVisibility(XZBDevice.Wait);
            default:
                break;
        }
    }

    public void showChatInputBar(boolean z) {
        this.d.b(z);
    }

    public void showTopView(boolean z) {
        int i;
        int i2 = 0;
        View view = this.mHeadArea;
        if (z) {
            i = 0;
        } else {
            i = 8;
        }
        view.setVisibility(i);
        view = this.mVistorArea;
        if (z) {
            i = 0;
        } else {
            i = 8;
        }
        view.setVisibility(i);
        View view2 = this.rlPopularityArea;
        if (!z) {
            i2 = 8;
        }
        view2.setVisibility(i2);
    }

    public void setPopularityCount(long j) {
        if (j < 0) {
            j = 0;
        }
        if (j > this.c) {
            this.c = j;
            this.mPopularityCount.setText(String.valueOf(j));
        }
    }

    public void setOnLineCount(long j) {
        if (j < 0) {
            j = 0;
        }
        this.mOnlineCount.setText(j + "\u4eba");
    }

    public int getBottomLayoutHeight() {
        Point a = d.a(getContext());
        this.mBottomLayout.measure(MeasureSpec.makeMeasureSpec(a.x, ExploreByTouchHelper.INVALID_ID), MeasureSpec.makeMeasureSpec(a.y, ExploreByTouchHelper.INVALID_ID));
        return this.mBottomLayout.getMeasuredHeight();
    }

    public void showViewGradually(View view) {
        view.postDelayed(new q(this, view), 150);
        view.setVisibility(XZBDevice.DOWNLOAD_LIST_ALL);
    }

    public void setAttendSelect(boolean z) {
        this.mAttendButton.setSelected(z);
        this.mAttendButton.setVisibility(z ? XZBDevice.Wait : 0);
        if (!z && this.f && this.mAttendButton.getWidth() < this.e) {
            ObjectAnimator.ofInt(this.g, "width", new int[]{this.e}).setDuration(500).start();
        }
    }

    public void fadedByAnimation() {
        this.mAttendButton.setClickable(false);
        this.mAttendButton.startAnimation(a(new AlphaAnimation(1.0f, 0.0f), IHost.CLIENT_NOFITY_INIT, false));
        this.mAttendButton.setSelected(true);
        this.f = true;
        this.mAttendButton.postDelayed(new r(this), 1800);
    }

    private AnimationSet a(AlphaAnimation alphaAnimation, int i, boolean z) {
        AnimationSet animationSet = new AnimationSet(true);
        animationSet.addAnimation(alphaAnimation);
        animationSet.setDuration((long) i);
        animationSet.setFillAfter(z);
        return animationSet;
    }

    public a getPresenter() {
        return this.d;
    }

    public void setPresenter(a aVar) {
        if (aVar != null) {
            this.d = aVar;
            this.d.a();
        }
    }

    public void setHostName(String str) {
        if (!TextUtils.isEmpty(str)) {
            this.a.setText(v.a(str, XZBDevice.Wait));
        }
    }

    public ConnectMicView getConnectMicView() {
        return this.mConnectMicView;
    }

    public SignalLevelView getSignalLevelView() {
        return this.mSignalLevelView;
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.d != null) {
            this.d.b();
        }
    }
}
