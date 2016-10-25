package com.xunlei.tdlive.usercenter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.xunlei.tdlive.R;
import com.xunlei.tdlive.protocol.XLLiveGetLiveRecordRequest;
import com.xunlei.tdlive.protocol.XLLiveGetLiveRecordRequest.LiveRecords;
import com.xunlei.tdlive.protocol.XLLiveGetLiveRecordRequest.ReocodItem;
import com.xunlei.tdlive.protocol.XLLiveRequest.ObjectCallBack;
import com.xunlei.tdlive.user.f;
import com.xunlei.tdlive.util.a;
import java.util.List;

// compiled from: UserReplayAdapter2.java
public class af extends d<ReocodItem> implements ObjectCallBack {
    private String d;
    private String e;
    private String f;
    private int g;
    private XLLiveGetLiveRecordRequest h;

    public af(Context context, String str, String str2, String str3) {
        super(context);
        this.g = 0;
        this.f = str;
        this.d = str2;
        this.e = str3;
    }

    public void a(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            this.d = this.b.getResources().getString(R.string.signature_default);
        } else {
            this.d = str;
        }
        this.e = str2;
        notifyDataSetChanged();
    }

    public void c(int i) {
        super.c(i);
        this.g--;
    }

    public View a(Context context, ReocodItem reocodItem, ViewGroup viewGroup) {
        a aVar = new a(null);
        View inflate = LayoutInflater.from(this.b).inflate(R.layout.xllive_item_replay, viewGroup, false);
        inflate.setTag(aVar);
        aVar.a = (ImageView) inflate.findViewById(R.id.ivAvatar);
        aVar.b = (TextView) inflate.findViewById(R.id.tvNickname);
        aVar.c = (TextView) inflate.findViewById(R.id.tvTime);
        aVar.d = (TextView) inflate.findViewById(R.id.tvTitle);
        aVar.e = (TextView) inflate.findViewById(R.id.tvViewNum);
        return inflate;
    }

    public void a(View view, Context context, ReocodItem reocodItem) {
        a aVar = (a) view.getTag();
        a.a(this.b).a(aVar.a, this.e, a.a(this.b, R.drawable.xllive_avatar_default));
        aVar.b.setText(this.d);
        aVar.d.setText(reocodItem.title);
        aVar.e.setText(reocodItem.current_user);
        aVar.c.setText(reocodItem.timeshow);
        view.setSelected(false);
    }

    public void a(int i) {
        if (b()) {
            this.h = new XLLiveGetLiveRecordRequest(f.a().k(), f.a().l(), this.f);
            this.h.setRange(this.g, i);
            this.h.send(this);
        }
    }

    public void b(int i) {
        this.g = 0;
        a(i);
    }

    public void onResponse(int i, String str, Object obj) {
        List list = null;
        if (i == 0 && (obj instanceof LiveRecords)) {
            list = ((LiveRecords) obj).data;
        }
        if (this.g == 0) {
            b(list);
        } else {
            a(list);
        }
        this.g = (list == null ? 0 : list.size()) + this.g;
        c();
        if (this.c != null) {
            this.c.b();
        }
    }

    public void d() {
        if (this.h != null) {
            this.h.cancel();
        }
    }
}
