package com.xunlei.downloadprovider.frame.user;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.umeng.fb.model.Reply;
import com.xunlei.downloadprovider.R;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.android.spdy.SpdyProtocol;
import org.apache.commons.logging.impl.SimpleLog;

class UserFeedBackUmActivity$a extends BaseAdapter {
    final /* synthetic */ UserFeedBackUmActivity a;

    class a {
        TextView a;
        ProgressBar b;
        ImageView c;
        TextView d;

        a() {
        }
    }

    UserFeedBackUmActivity$a(UserFeedBackUmActivity userFeedBackUmActivity) {
        this.a = userFeedBackUmActivity;
    }

    public final int getCount() {
        return UserFeedBackUmActivity.a(this.a).size();
    }

    public final Object getItem(int i) {
        return UserFeedBackUmActivity.a(this.a).get(i);
    }

    public final long getItemId(int i) {
        return (long) i;
    }

    public final int getViewTypeCount() {
        return SimpleLog.LOG_LEVEL_DEBUG;
    }

    public final int getItemViewType(int i) {
        return com.umeng.fb.net.a.g.equals(((Reply) UserFeedBackUmActivity.a(this.a).get(i)).type) ? 1 : 0;
    }

    @SuppressLint({"InflateParams"})
    public final View getView(int i, View view, ViewGroup viewGroup) {
        a aVar;
        Reply reply = (Reply) UserFeedBackUmActivity.a(this.a).get(i);
        if (view == null) {
            View inflate;
            if (com.umeng.fb.net.a.g.equals(reply.type)) {
                inflate = LayoutInflater.from(UserFeedBackUmActivity.h(this.a)).inflate(R.layout.feed_dev_reply, null);
            } else {
                inflate = LayoutInflater.from(UserFeedBackUmActivity.h(this.a)).inflate(R.layout.feed_user_reply, null);
            }
            a aVar2 = new a();
            aVar2.a = (TextView) inflate.findViewById(R.id.fb_reply_content);
            aVar2.b = (ProgressBar) inflate.findViewById(R.id.fb_reply_progressBar);
            aVar2.c = (ImageView) inflate.findViewById(R.id.fb_reply_state_failed);
            aVar2.d = (TextView) inflate.findViewById(R.id.fb_reply_date);
            inflate.setTag(aVar2);
            view = inflate;
            aVar = aVar2;
        } else {
            aVar = (a) view.getTag();
        }
        aVar.a.setText(reply.content);
        if (!com.umeng.fb.net.a.g.equals(reply.type)) {
            if (Reply.STATUS_NOT_SENT.equals(reply.status)) {
                aVar.c.setVisibility(0);
            } else {
                aVar.c.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
            }
            if (Reply.STATUS_SENDING.equals(reply.status)) {
                aVar.b.setVisibility(0);
            } else {
                aVar.b.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
            }
        }
        if (i + 1 < UserFeedBackUmActivity.a(this.a).size()) {
            if (i > 0) {
                if (reply.created_at - ((Reply) UserFeedBackUmActivity.a(this.a).get(i - 1)).created_at > 600000) {
                    a(reply.created_at, aVar.d);
                } else {
                    aVar.d.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
                }
            } else if (i == 0) {
                a(reply.created_at, aVar.d);
            } else {
                aVar.d.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
            }
        } else if (i + 1 == UserFeedBackUmActivity.a(this.a).size()) {
            if (i == 0) {
                a(reply.created_at, aVar.d);
            } else {
                if (reply.created_at - ((Reply) UserFeedBackUmActivity.a(this.a).get(i - 1)).created_at > 600000) {
                    a(reply.created_at, aVar.d);
                } else {
                    aVar.d.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
                }
            }
        }
        return view;
    }

    @SuppressLint({"SimpleDateFormat"})
    private static void a(long j, TextView textView) {
        try {
            long time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(new SimpleDateFormat("yyyy-MM-dd").format(new Date(System.currentTimeMillis())) + " 00:00:00").getTime();
        } catch (Exception e) {
            e.printStackTrace();
            time = 0;
        }
        if (time > 0) {
            Date date = new Date(j);
            if (j < time) {
                textView.setText(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date));
            } else {
                textView.setText(new StringBuilder("\u4eca\u5929   ").append(new SimpleDateFormat("HH:mm:ss").format(date)).toString());
            }
            textView.setVisibility(0);
            return;
        }
        textView.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
    }
}
