package com.xunlei.tdlive;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.net.Uri;
import org.eclipse.paho.client.mqttv3.internal.ClientDefaults;

// compiled from: XLLiveApplication.java
class ge implements OnClickListener {
    final /* synthetic */ String a;
    final /* synthetic */ gb b;

    ge(gb gbVar, String str) {
        this.b = gbVar;
        this.a = str;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        dialogInterface.dismiss();
        if (i == 0) {
            System.exit(0);
        } else {
            this.b.startActivity(new Intent("android.intent.action.VIEW").setData(Uri.parse(this.a)).setFlags(ClientDefaults.MAX_MSG_SIZE));
        }
    }
}
