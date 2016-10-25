package anetwork.channel.entity;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import anet.channel.a.a;
import anet.channel.request.BodyEntry;
import anetwork.channel.aidl.g;
import com.taobao.accs.data.Message;
import java.io.IOException;
import java.io.OutputStream;

// compiled from: Taobao
public class BodyHandlerEntry implements BodyEntry {
    public static final Creator<BodyHandlerEntry> CREATOR;
    g a;

    private BodyHandlerEntry() {
        this.a = null;
    }

    public String getContentType() {
        return null;
    }

    public int writeTo(OutputStream outputStream) throws IOException {
        int i = 0;
        try {
            a a = a.a.a((int) Message.FLAG_RET);
            while (!this.a.a()) {
                int a2 = this.a.a(a.a());
                outputStream.write(a.a(), 0, a2);
                i += a2;
            }
            a.d();
            return i;
        } catch (Throwable e) {
            throw new IOException("RemoteException", e);
        }
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeStrongInterface(this.a);
    }

    static {
        CREATOR = new b();
    }
}
