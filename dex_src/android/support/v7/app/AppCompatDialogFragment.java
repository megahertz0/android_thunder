package android.support.v7.app;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import org.android.spdy.SpdyAgent;

public class AppCompatDialogFragment extends DialogFragment {
    public Dialog onCreateDialog(Bundle bundle) {
        return new aa(getActivity(), getTheme());
    }

    public void setupDialog(Dialog dialog, int i) {
        if (dialog instanceof aa) {
            aa aaVar = (aa) dialog;
            switch (i) {
                case SpdyAgent.ACCS_ONLINE_SERVER:
                case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                    aaVar.a();
                    return;
                case XZBDevice.DOWNLOAD_LIST_FAILED:
                    dialog.getWindow().addFlags(R.styleable.Toolbar_subtitleTextColor);
                    aaVar.a();
                    return;
                default:
                    return;
            }
        }
        super.setupDialog(dialog, i);
    }
}
