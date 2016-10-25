package com.tencent.connect.dataprovider;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

// compiled from: ProGuard
public final class DataType {
    public static final int CONTENT_AND_IMAGE_PATH = 1;
    public static final int CONTENT_AND_VIDEO_PATH = 2;
    public static final int CONTENT_ONLY = 4;

    // compiled from: ProGuard
    public static class TextAndMediaPath implements Parcelable {
        public static final Creator<com.tencent.connect.dataprovider.DataType.TextAndMediaPath> CREATOR;
        private String a;
        private String b;

        public TextAndMediaPath(String str, String str2) {
            this.a = str;
            this.b = str2;
        }

        public String getText() {
            return this.a;
        }

        public String getMediaPath() {
            return this.b;
        }

        public int describeContents() {
            return 0;
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(this.a);
            parcel.writeString(this.b);
        }

        static {
            CREATOR = new Creator<com.tencent.connect.dataprovider.DataType.TextAndMediaPath>() {
                public final com.tencent.connect.dataprovider.DataType.TextAndMediaPath createFromParcel(Parcel parcel) {
                    return new com.tencent.connect.dataprovider.DataType.TextAndMediaPath(null);
                }

                public final com.tencent.connect.dataprovider.DataType.TextAndMediaPath[] newArray(int i) {
                    return new com.tencent.connect.dataprovider.DataType.TextAndMediaPath[i];
                }
            };
        }

        private TextAndMediaPath(Parcel parcel) {
            this.a = parcel.readString();
            this.b = parcel.readString();
        }
    }

    // compiled from: ProGuard
    public static class TextOnly implements Parcelable {
        public static final Creator<com.tencent.connect.dataprovider.DataType.TextOnly> CREATOR;
        private String a;

        public TextOnly(String str) {
            this.a = str;
        }

        public String getText() {
            return this.a;
        }

        public int describeContents() {
            return 0;
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(this.a);
        }

        static {
            CREATOR = new Creator<com.tencent.connect.dataprovider.DataType.TextOnly>() {
                public final com.tencent.connect.dataprovider.DataType.TextOnly createFromParcel(Parcel parcel) {
                    return new com.tencent.connect.dataprovider.DataType.TextOnly(null);
                }

                public final com.tencent.connect.dataprovider.DataType.TextOnly[] newArray(int i) {
                    return new com.tencent.connect.dataprovider.DataType.TextOnly[i];
                }
            };
        }

        private TextOnly(Parcel parcel) {
            this.a = parcel.readString();
        }
    }
}
