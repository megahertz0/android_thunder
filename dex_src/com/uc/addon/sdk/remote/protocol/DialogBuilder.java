package com.uc.addon.sdk.remote.protocol;

import android.os.Handler;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.widget.RemoteViews;

public class DialogBuilder implements Parcelable {
    public static final Creator CREATOR;
    public static final int DIALOG_STATE_DISMISS = 3;
    public static final int DIALOG_STATE_HIDE = 2;
    public static final int DIALOG_STATE_SHOW = 1;
    public static final int DIALOG_STATE_UNKNOW = 0;
    public IDialogStateListener mDialogStateListener;
    public String mMessage;
    public IButtonClickListener mNegativeButtonClickListener;
    public String mNegativeButtonText;
    public IButtonClickListener mNeutralButtonClickListener;
    public String mNeutralButtonText;
    public IButtonClickListener mPositiveButtonClickListener;
    public String mPositiveButtonText;
    public String mTitle;
    public RemoteViews mView;

    static {
        CREATOR = new Creator() {
            public final DialogBuilder createFromParcel(Parcel parcel) {
                return new DialogBuilder((byte) 0);
            }

            public final DialogBuilder[] newArray(int i) {
                return new DialogBuilder[i];
            }
        };
    }

    private DialogBuilder(Parcel parcel) {
        this.mTitle = parcel.readString();
        this.mMessage = parcel.readString();
        this.mPositiveButtonText = parcel.readString();
        this.mPositiveButtonClickListener = ButtonClickListener.asInterface(parcel.readStrongBinder());
        this.mNegativeButtonText = parcel.readString();
        this.mNegativeButtonClickListener = ButtonClickListener.asInterface(parcel.readStrongBinder());
        this.mNeutralButtonText = parcel.readString();
        this.mNeutralButtonClickListener = ButtonClickListener.asInterface(parcel.readStrongBinder());
        this.mView = (RemoteViews) parcel.readParcelable(null);
        this.mDialogStateListener = DialogStateListener.asInterface(parcel.readStrongBinder());
    }

    public int describeContents() {
        return 0;
    }

    public DialogBuilder setDialogStateListener(DialogStateListener dialogStateListener) {
        this.mDialogStateListener = dialogStateListener;
        return this;
    }

    public void setHandler(Handler handler) {
        if (handler != null) {
            if (this.mPositiveButtonClickListener != null) {
                ((ButtonClickListener) this.mPositiveButtonClickListener).a(handler);
            }
            if (this.mNegativeButtonClickListener != null) {
                ((ButtonClickListener) this.mNegativeButtonClickListener).a(handler);
            }
            if (this.mNeutralButtonClickListener != null) {
                ((ButtonClickListener) this.mNeutralButtonClickListener).a(handler);
            }
            if (this.mDialogStateListener != null) {
                ((DialogStateListener) this.mDialogStateListener).a(handler);
            }
        }
    }

    public DialogBuilder setMessage(String str) {
        this.mMessage = str;
        return this;
    }

    public DialogBuilder setNegativeButton(String str, ButtonClickListener buttonClickListener) {
        this.mNegativeButtonText = str;
        this.mNegativeButtonClickListener = buttonClickListener;
        return this;
    }

    public DialogBuilder setNeturalButton(String str, ButtonClickListener buttonClickListener) {
        this.mNeutralButtonText = str;
        this.mNeutralButtonClickListener = buttonClickListener;
        return this;
    }

    public DialogBuilder setPositiveButton(String str, ButtonClickListener buttonClickListener) {
        this.mPositiveButtonText = str;
        this.mPositiveButtonClickListener = buttonClickListener;
        return this;
    }

    public DialogBuilder setTitle(String str) {
        this.mTitle = str;
        return this;
    }

    public DialogBuilder setView(RemoteViews remoteViews) {
        this.mView = remoteViews;
        return this;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mTitle);
        parcel.writeString(this.mMessage);
        parcel.writeString(this.mPositiveButtonText);
        parcel.writeStrongBinder((IBinder) this.mPositiveButtonClickListener);
        parcel.writeString(this.mNegativeButtonText);
        parcel.writeStrongBinder((IBinder) this.mNegativeButtonClickListener);
        parcel.writeString(this.mNeutralButtonText);
        parcel.writeStrongBinder((IBinder) this.mNeutralButtonClickListener);
        parcel.writeParcelable(this.mView, 0);
        parcel.writeStrongBinder((IBinder) this.mDialogStateListener);
    }
}
