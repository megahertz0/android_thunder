package anetwork.channel.aidl;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

// compiled from: Taobao
public class ParcelableObject implements Parcelable {
    public static final Creator<ParcelableObject> CREATOR;

    ParcelableObject(byte b) {
        ParcelableObject parcelableObject = new ParcelableObject();
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
    }

    static {
        CREATOR = new l();
    }
}
