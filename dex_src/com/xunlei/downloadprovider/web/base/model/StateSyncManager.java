package com.xunlei.downloadprovider.web.base.model;

import android.content.Context;
import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public final class StateSyncManager {

    public static class CommentSateInfo implements Parcelable {
        public static final Creator<com.xunlei.downloadprovider.web.base.model.StateSyncManager.CommentSateInfo> CREATOR;
        public long a;
        public boolean b;
        public long c;
        public String d;
        public long e;

        public int describeContents() {
            return 0;
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeLong(this.a);
            parcel.writeByte(this.b ? (byte) 1 : (byte) 0);
            parcel.writeLong(this.c);
            parcel.writeString(this.d);
            parcel.writeLong(this.e);
        }

        protected CommentSateInfo(Parcel parcel) {
            this.a = parcel.readLong();
            this.b = parcel.readByte() != null;
            this.c = parcel.readLong();
            this.d = parcel.readString();
            this.e = parcel.readLong();
        }

        static {
            CREATOR = new w();
        }
    }

    public enum SourceFrom {
        PAGE_SHORMOVIE_DETAIL,
        PAGE_PERSONAL_SPACE,
        PAGE_FEED_FLOW;

        static {
            PAGE_SHORMOVIE_DETAIL = new com.xunlei.downloadprovider.web.base.model.StateSyncManager.SourceFrom("PAGE_SHORMOVIE_DETAIL", 0);
            PAGE_PERSONAL_SPACE = new com.xunlei.downloadprovider.web.base.model.StateSyncManager.SourceFrom("PAGE_PERSONAL_SPACE", 1);
            PAGE_FEED_FLOW = new com.xunlei.downloadprovider.web.base.model.StateSyncManager.SourceFrom("PAGE_FEED_FLOW", 2);
            a = new com.xunlei.downloadprovider.web.base.model.StateSyncManager.SourceFrom[]{PAGE_SHORMOVIE_DETAIL, PAGE_PERSONAL_SPACE, PAGE_FEED_FLOW};
        }
    }

    public static void a(Context context, SourceFrom sourceFrom, CommentSateInfo commentSateInfo) {
        Intent intent = new Intent();
        intent.setAction("action_comment_zan_state_changed");
        intent.putExtra("comment_info", commentSateInfo);
        intent.putExtra("source_from", sourceFrom.name());
        context.sendBroadcast(intent);
    }
}
