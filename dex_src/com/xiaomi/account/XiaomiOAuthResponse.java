package com.xiaomi.account;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.os.RemoteException;
import com.xiaomi.account.IXiaomiAuthResponse.Stub;
import com.xiaomi.auth.AuthConstants;

public class XiaomiOAuthResponse implements Parcelable {
    public static final Creator<XiaomiOAuthResponse> CREATOR;
    private static final String TAG;
    private IXiaomiAuthResponse mResponse;

    static {
        TAG = XiaomiOAuthResponse.class.getName();
        CREATOR = new AnonymousClass_1();
    }

    public XiaomiOAuthResponse(IXiaomiAuthResponse iXiaomiAuthResponse) {
        this.mResponse = iXiaomiAuthResponse;
    }

    public XiaomiOAuthResponse(Parcel parcel) {
        this.mResponse = Stub.asInterface(parcel.readStrongBinder());
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeStrongBinder(this.mResponse.asBinder());
    }

    public void onResult(Bundle bundle) {
        setIXiaomiAuthResponseResult(this.mResponse, bundle);
    }

    public void onError(int i, String str) {
        Bundle bundle = new Bundle();
        bundle.putInt(AuthConstants.EXTRA_ERROR_CODE, i);
        bundle.putString(AuthConstants.EXTRA_ERROR_DESCRIPTION, str);
        setIXiaomiAuthResponseResult(this.mResponse, bundle);
    }

    public void onCancel() {
        setIXiaomiAuthResponseCancel(this.mResponse);
    }

    public static void setIXiaomiAuthResponseResult(IXiaomiAuthResponse iXiaomiAuthResponse, Bundle bundle) {
        if (iXiaomiAuthResponse != null && bundle != null) {
            try {
                iXiaomiAuthResponse.onResult(bundle);
            } catch (RuntimeException e) {
                Bundle bundle2 = new Bundle();
                bundle2.putInt(AuthConstants.EXTRA_ERROR_CODE, -1);
                bundle2.putString(AuthConstants.EXTRA_ERROR_DESCRIPTION, e.getMessage());
                try {
                    iXiaomiAuthResponse.onResult(bundle2);
                } catch (RuntimeException e2) {
                } catch (RemoteException e3) {
                }
            } catch (RemoteException e4) {
            }
        }
    }

    public static void setIXiaomiAuthResponseCancel(IXiaomiAuthResponse iXiaomiAuthResponse) {
        if (iXiaomiAuthResponse != null) {
            try {
                iXiaomiAuthResponse.onCancel();
            } catch (RuntimeException e) {
            } catch (RemoteException e2) {
            }
        }
    }
}
