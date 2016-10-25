package com.xunlei.tdlive;

import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AbsListView.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import com.taobao.accs.common.Constants;
import com.tencent.open.SocialConstants;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.xunlei.tdlive.base.BaseActivity;
import com.xunlei.tdlive.control.RoundImageView;
import com.xunlei.tdlive.play.view.LiveReplayActivity;
import com.xunlei.tdlive.protocol.XLLiveGetLiveListRequest;
import com.xunlei.tdlive.protocol.XLLiveGetLiveListRequest.GetLiveListResp.Room;
import com.xunlei.tdlive.sdk.IHost;
import com.xunlei.tdlive.sdk.XLLiveSDK;
import com.xunlei.tdlive.user.f;
import com.xunlei.tdlive.util.ac;
import com.xunlei.tdlive.util.d;
import com.xunlei.tdlive.util.q;
import com.xunlei.tdlive.util.y;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.util.List;

public class LivePublishEndingActivity extends BaseActivity implements OnClickListener, OnItemClickListener {
    private static final String a;
    private static b j;
    private String b;
    private int c;
    private int d;
    private long e;
    private String f;
    private String g;
    private GridView h;
    private a i;
    private boolean k;
    private BroadcastReceiver l;

    private class a extends BaseAdapter {
        private List<Room> b;

        private a() {
        }

        public /* synthetic */ Object getItem(int i) {
            return a(i);
        }

        public void a(List<Room> list) {
            this.b = list;
            notifyDataSetChanged();
        }

        public int getCount() {
            return this.b == null ? 0 : Math.min(this.b.size(), XZBDevice.DOWNLOAD_LIST_ALL);
        }

        public Room a(int i) {
            return this.b == null ? null : (Room) this.b.get(i);
        }

        public long getItemId(int i) {
            return (long) i;
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            c cVar;
            if (view == null) {
                view = LayoutInflater.from(LivePublishEndingActivity.this).inflate(R.layout.xllive_recommand_host_square, null, false);
                c cVar2 = new c();
                view.setTag(cVar2);
                LivePublishEndingActivity.this = (RoundImageView) view.findViewById(R.id.ivImage);
                cVar2.b = (TextView) view.findViewById(R.id.tvLeft);
                cVar2.c = (TextView) view.findViewById(R.id.tvRight);
                int a = (int) d.a(LivePublishEndingActivity.this, 130.0f);
                view.setLayoutParams(new LayoutParams(a, a));
                LivePublishEndingActivity.this.setType(1);
                LivePublishEndingActivity.this.setBorderRadius(XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED);
                cVar = cVar2;
            } else {
                cVar = (c) view.getTag();
            }
            a(cVar, i);
            return view;
        }

        private void a(c cVar, int i) {
            Room a = a(i);
            if (a != null) {
                Context context = LivePublishEndingActivity.this;
                com.xunlei.tdlive.util.a.a(context).a(LivePublishEndingActivity.this, a.getImageUrl(), com.xunlei.tdlive.util.a.b(context));
                cVar.b.setText(a.userinfo.nickname);
                cVar.c.setText(a.onlinenum + "\u4eba");
            }
        }
    }

    private static final class b {
        private List<Room> a;
        private int b;
        private a c;

        public static interface a {
            void a(int i, List<Room> list);
        }

        private b() {
        }

        public final void a(a aVar) {
            if (this.a != null) {
                aVar.a(this.b, this.a);
            } else {
                this.c = aVar;
            }
        }

        public final void a() {
            new XLLiveGetLiveListRequest(ac.j(), null, f.a().k(), f.a().l(), 0, 4).send(new db(this));
        }

        public final boolean b() {
            return this.a != null && this.a.size() > 0;
        }

        public final void c() {
            this.c = null;
            this.a = null;
            this.b = 0;
        }
    }

    private static final class c {
        public RoundImageView a;
        public TextView b;
        public TextView c;

        private c() {
        }
    }

    public LivePublishEndingActivity() {
        this.k = false;
        this.l = new cu(this);
    }

    static {
        a = LivePublishEndingActivity.class.getSimpleName();
        j = new b();
    }

    protected void onCreate(Bundle bundle) {
        Object obj = 1;
        super.onCreate(bundle);
        Intent intent = getIntent();
        if (intent == null) {
            finish();
            return;
        }
        if (getIntent() == null || !getIntent().getBooleanExtra("player", false)) {
            boolean z = false;
        } else {
            int i = 1;
        }
        if (getIntent() == null || !getIntent().getBooleanExtra("replay", false)) {
            boolean z2 = false;
        }
        this.b = intent.getStringExtra("room_user_id");
        this.c = intent.getIntExtra("current_point", 0);
        this.d = intent.getIntExtra("current_user", 0);
        this.e = (long) intent.getIntExtra("play_time", 0);
        this.f = intent.getStringExtra("nick_name");
        this.g = intent.getStringExtra("avatar");
        if (z) {
            setContentView(R.layout.xllive_activity_publish_ending_guest);
            ((TextView) findViewById(R.id.play_number)).setText(this.d + "\u4eba\u770b\u8fc7");
        } else {
            setContentView(R.layout.xllive_activity_publish_ending);
            a(getIntent());
        }
        if (z2) {
            ((TextView) findViewById(R.id.title)).setText("\u5f55\u64ad\u7ed3\u675f");
        }
        findViewById(R.id.back_home).setOnClickListener(this);
        this.h = (GridView) findViewById(R.id.recommands);
        this.i = new a();
        this.h.setAdapter(this.i);
        this.h.setOnItemClickListener(this);
        j.a(new cv(this));
        if (!j.b()) {
            j.a();
        }
        registerReceiver(this.l, new IntentFilter("com.xunlei.tdlive.ACTION_UPDATE_PUBLISH_ENDING"));
        long j = LivePlayerActivity.a;
        LivePlayerActivity.a = 0;
        long elapsedRealtime = SystemClock.elapsedRealtime() - j;
        if (j > 0 && elapsedRealtime > 0 && elapsedRealtime < 1500) {
            b();
        }
    }

    public void onDestroy() {
        unregisterReceiver(this.l);
        super.onDestroy();
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        int i2 = 0;
        finish();
        try {
            Room a = this.i.a(i);
            a(a);
            com.xunlei.tdlive.util.q.b e = q.e("play_end_content_show");
            e = q.e("play_end_content_click").a("viewid", e.d("viewid")).a("grayid", e.d("grayid")).a("roomid", a.roomid).a("hostid", a.userid).a("viewernum", a.onlinenum).a("hosttype", a.seq2 == null ? 0 : a.seq2.hot_level).a("recommend", a.seq2 == null ? 0 : a.seq2.is_recommend).a(Constants.KEY_SECURITY_SIGN, a.seq2 == null ? 0 : a.seq2.is_sign);
            String str = "follow";
            if (a.seq2 != null) {
                i2 = a.seq2.is_follow;
            }
            e.a(str, i2).a("livestat", a.status == 2 ? "replay" : "live").a("rn", i).b(new String[0]);
        } catch (Exception e2) {
        }
    }

    private void a(Room room) {
        if (room == null) {
            return;
        }
        if (room.status == 1 || room.status == 3) {
            LivePlayerActivity.a(this, room.roomid, room.userid, room.stream_pull, room.userinfo.avatar, room.image, String.valueOf(room.onlinenum), room.seq2.hot_level, room.seq2.is_follow, "liveend");
        } else if (room.status == 2) {
            LiveReplayActivity.a(this, room.roomid, room.userid, room.play_hls_url, room.userinfo.avatar, room.image, String.valueOf(room.onlinenum), room.seq2.hot_level, room.seq2.is_follow, "liveend");
        }
    }

    public void onClick(View view) {
        if (view.getId() == R.id.back_home) {
            XLLiveSDK.getInstance(this).host().backToXLiveList(this);
            q.e("play_end_view_more").b(new String[0]);
        } else if (view.getId() == R.id.follow) {
            if (this.b != null && this.b.length() > 0) {
                f.a().a(this, "attention_host", new cw(this));
            }
        } else if (view.getId() == R.id.xllive_share_wb) {
            a(view, SHARE_MEDIA.SINA);
        } else if (view.getId() == R.id.xllive_share_wx) {
            a(view, SHARE_MEDIA.WEIXIN);
        } else if (view.getId() == R.id.xllive_share_tl) {
            a(view, SHARE_MEDIA.WEIXIN_CIRCLE);
        } else if (view.getId() == R.id.xllive_share_qz) {
            a(view, SHARE_MEDIA.QZONE);
        } else if (view.getId() == R.id.xllive_share_qq) {
            a(view, SHARE_MEDIA.QQ);
        }
    }

    private void a(View view, SHARE_MEDIA share_media) {
        com.xunlei.tdlive.util.y.a cyVar = new cy(this, view);
        String a = y.a(this.b);
        y.a(this, share_media, this.f, this.g, a, cyVar);
        q.e("zb_share").a("liveend").a("hostid", this.b).a(SocialConstants.PARAM_URL, a);
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        try {
            UMShareAPI.get(this).onActivityResult(i, i2, intent);
        } catch (Exception e) {
        }
    }

    private void a(Intent intent) {
        int i = (int) (this.e / 3600);
        int i2 = (int) ((this.e - ((long) (i * 3600))) - ((long) (((int) ((this.e - ((long) (i * 3600))) / 60)) * 60)));
        ((TextView) findViewById(R.id.play_number)).setText(this.d + "\u4eba\u770b\u8fc7");
        ((TextView) findViewById(R.id.users)).setText(this.d);
        ((TextView) findViewById(R.id.points)).setText(this.c);
        ((TextView) findViewById(R.id.time)).setText(String.format("%02d:%02d:%02d", new Object[]{Integer.valueOf(i), Integer.valueOf(r2), Integer.valueOf(i2)}));
    }

    public static void a(Context context, boolean z, boolean z2, String str, int i, int i2, int i3, String str2, String str3) {
        j.a();
        context.startActivity(new Intent(context, LivePublishEndingActivity.class).putExtra("player", z).putExtra("replay", z2).putExtra("room_user_id", str).putExtra("current_point", i).putExtra("current_user", i2).putExtra("play_time", i3).putExtra("nick_name", str2).putExtra("avatar", str3));
    }

    private void b() {
        Dialog dialog = new Dialog(this, R.style.TransparentDialogStyle);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.xllive_publish_end_auto_navigator_dialog);
        dialog.show();
        WindowManager.LayoutParams attributes = dialog.getWindow().getAttributes();
        attributes.x = 0;
        attributes.y = 0;
        attributes.height = -2;
        attributes.width = -2;
        attributes.gravity = 17;
        dialog.getWindow().setAttributes(attributes);
        ((AnimationDrawable) ((ImageView) dialog.findViewById(R.id.navAni)).getBackground()).start();
        dialog.findViewById(R.id.close).setOnClickListener(new cz(this, dialog));
        post(new da(this, dialog), IHost.CLIENT_NOFITY_INIT);
    }
}
