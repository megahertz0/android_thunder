package com.uc.addon.sdk.remote;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.uc.addon.sdk.remote.protocol.DownloadTaskCreateResultListener;
import com.uc.addon.sdk.remote.protocol.DownloadTaskStatusChangeListener;
import com.uc.addon.sdk.remote.protocol.IDownloadTaskCreateResultListener;
import com.uc.addon.sdk.remote.protocol.IDownloadTaskStatusChangeListener;
import org.android.agoo.message.MessageService;

public class DownloadTask implements Parcelable {
    public static final Creator CREATOR;
    public static final int DOWNLOAD_STATE_DOWNLOADING = 1003;
    public static final int DOWNLOAD_STATE_ERROR = 1006;
    public static final int DOWNLOAD_STATE_PAUSE = 1004;
    public static final int DOWNLOAD_STATE_PENDING_FILENAME_CHECKING = 1001;
    public static final int DOWNLOAD_STATE_PENDING_URL_CHECKING = 1000;
    public static final int DOWNLOAD_STATE_RETRY = 1007;
    public static final int DOWNLOAD_STATE_SUCCESS = 1005;
    public static final int DOWNLOAD_STATE_UNKNOWN = -1;
    public static final int DOWNLOAD_STATE_WAITING = 1002;
    public static final int DOWNLOAD_TYPE_SINGLE_WEB_TASK = 0;
    protected IDownloadTaskCreateResultListener a;
    public boolean ask;
    protected IDownloadTaskStatusChangeListener b;
    public int currentSize;
    public int currentStatus;
    public String fileName;
    public String filePath;
    public int fileSize;
    public int speed;
    public int taskID;
    public String title;
    public int type;
    public String url;

    static {
        CREATOR = new Creator() {
            public final DownloadTask createFromParcel(Parcel parcel) {
                return new DownloadTask(parcel);
            }

            public final DownloadTask[] newArray(int i) {
                return new DownloadTask[i];
            }
        };
    }

    public DownloadTask() {
        this.ask = false;
        this.type = 0;
    }

    public DownloadTask(Parcel parcel) {
        boolean z = false;
        this.ask = false;
        this.type = 0;
        this.currentStatus = parcel.readInt();
        this.currentSize = parcel.readInt();
        this.fileSize = parcel.readInt();
        this.speed = parcel.readInt();
        this.taskID = parcel.readInt();
        this.url = parcel.readString();
        this.fileName = parcel.readString();
        if (parcel.readString().equals(MessageService.MSG_DB_NOTIFY_REACHED)) {
            z = true;
        }
        this.ask = z;
        this.filePath = parcel.readString();
        this.title = parcel.readString();
        this.type = parcel.readInt();
        this.a = DownloadTaskCreateResultListener.asInterface(parcel.readStrongBinder());
        this.b = DownloadTaskStatusChangeListener.asInterface(parcel.readStrongBinder());
    }

    public int describeContents() {
        return 0;
    }

    public IDownloadTaskCreateResultListener getDownloadTaskCreateResultListener() {
        return this.a;
    }

    public IDownloadTaskStatusChangeListener getDownloadTaskStatusChangeListener() {
        return this.b;
    }

    public void setDownloadTaskCreateResultListener(DownloadTaskCreateResultListener downloadTaskCreateResultListener) {
        this.a = downloadTaskCreateResultListener;
    }

    public void setDownloadTaskStatusChangeListener(DownloadTaskStatusChangeListener downloadTaskStatusChangeListener) {
        this.b = downloadTaskStatusChangeListener;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.currentStatus);
        parcel.writeInt(this.currentSize);
        parcel.writeInt(this.fileSize);
        parcel.writeInt(this.speed);
        parcel.writeInt(this.taskID);
        parcel.writeString(this.url);
        parcel.writeString(this.fileName);
        parcel.writeString(this.ask ? MessageService.MSG_DB_NOTIFY_REACHED : MessageService.MSG_DB_READY_REPORT);
        parcel.writeString(this.filePath);
        parcel.writeString(this.title);
        parcel.writeInt(this.type);
        parcel.writeStrongBinder((IBinder) this.a);
        parcel.writeStrongBinder((IBinder) this.b);
    }
}
