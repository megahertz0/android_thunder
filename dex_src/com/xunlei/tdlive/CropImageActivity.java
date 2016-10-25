package com.xunlei.tdlive;

import android.content.Intent;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View.OnClickListener;
import com.xunlei.tdlive.base.BaseActivity;
import com.xunlei.tdlive.control.CropImageView;
import com.xunlei.tdlive.util.a;
import org.eclipse.paho.client.mqttv3.MqttTopic;

public class CropImageActivity extends BaseActivity implements OnClickListener {
    private CropImageView a;
    private Uri b;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.xllive_activity_crop_image);
        Intent intent = getIntent();
        if (intent == null) {
            finish();
            return;
        }
        String stringExtra = intent.getStringExtra("input");
        if (stringExtra == null) {
            finish();
            return;
        }
        this.b = (Uri) intent.getParcelableExtra("output");
        if (this.b == null) {
            finish();
            return;
        }
        BitmapDrawable bitmapDrawable = (BitmapDrawable) getResources().getDrawable(R.drawable.xllive_crop_bkg);
        bitmapDrawable.setTileModeXY(TileMode.REPEAT, TileMode.REPEAT);
        bitmapDrawable.setDither(true);
        this.a = (CropImageView) findViewById(R.id.crop_image);
        this.a.setBackground(bitmapDrawable);
        a.a(this).a(this.a, stringExtra.startsWith(MqttTopic.TOPIC_LEVEL_SEPARATOR) ? new StringBuilder("file://").append(stringExtra).toString() : stringExtra);
        this.a.getCropRect(new d(this));
        findViewById(R.id.rotate_left).setOnClickListener(this);
        findViewById(R.id.rotate_right).setOnClickListener(this);
        findViewById(R.id.ok).setOnClickListener(this);
        findViewById(R.id.cancel).setOnClickListener(this);
    }

    protected void onDestroy() {
        super.onDestroy();
        if (this.a != null) {
            this.a.getCropBitmap(null, 0, null);
        }
    }

    public void onClick(android.view.View r11) {
        throw new UnsupportedOperationException("Method not decompiled: com.xunlei.tdlive.CropImageActivity.onClick(android.view.View):void");
        /* JADX: method processing error */
/*
        Error: jadx.core.utils.exceptions.JadxRuntimeException: Try/catch wrap count limit reached in com.xunlei.tdlive.CropImageActivity.onClick(android.view.View):void
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.process(ProcessTryCatchRegions.java:54)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.postProcessRegions(RegionMakerVisitor.java:45)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:40)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:16)
	at jadx.core.ProcessClass.process(ProcessClass.java:22)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:209)
	at jadx.api.JavaClass.decompile(JavaClass.java:59)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:133)
	at java.util.concurrent.ThreadPoolExecutor.runWorker(Unknown Source)
	at java.util.concurrent.ThreadPoolExecutor$Worker.run(Unknown Source)
	at java.lang.Thread.run(Unknown Source)
*/
        /*
        this = this;
        r3 = -1;
        r0 = r11.getId();
        r1 = com.xunlei.tdlive.R.id.ok;
        if (r0 != r1) goto L_0x005d;
    L_0x0009:
        r0 = 100;
        r4 = new java.io.File;
        r1 = r10.b;
        r1 = r1.getPath();
        r4.<init>(r1);
    L_0x0016:
        r2 = 0;
        r1 = new java.io.FileOutputStream;	 Catch:{ Exception -> 0x0053 }
        r1.<init>(r4);	 Catch:{ Exception -> 0x0053 }
        r2 = r10.a;	 Catch:{ Exception -> 0x0089 }
        r5 = android.graphics.Bitmap.CompressFormat.JPEG;	 Catch:{ Exception -> 0x0089 }
        r6 = 0;
        r2 = r2.getCropBitmap(r5, r0, r1, r6);	 Catch:{ Exception -> 0x0089 }
        if (r2 != 0) goto L_0x003f;
    L_0x0027:
        r1.close();	 Catch:{ Exception -> 0x0089 }
        r1 = r3;
    L_0x002b:
        if (r1 <= 0) goto L_0x003b;
    L_0x002d:
        r0 = new android.content.Intent;
        r0.<init>();
        r1 = r10.b;
        r0 = r0.setData(r1);
        r10.setResult(r3, r0);
    L_0x003b:
        r10.finish();
    L_0x003e:
        return;
    L_0x003f:
        r1.close();	 Catch:{ Exception -> 0x0089 }
        r6 = r4.length();
        r8 = 1048576; // 0x100000 float:1.469368E-39 double:5.180654E-318;
        r1 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1));
        if (r1 <= 0) goto L_0x008c;
    L_0x004d:
        r1 = r0 + -1;
        if (r0 <= 0) goto L_0x002b;
    L_0x0051:
        r0 = r1;
        goto L_0x0016;
    L_0x0053:
        r0 = move-exception;
        r0 = r2;
    L_0x0055:
        r0.close();	 Catch:{ Exception -> 0x005a }
        r1 = r3;
        goto L_0x002b;
    L_0x005a:
        r0 = move-exception;
        r1 = r3;
        goto L_0x002b;
    L_0x005d:
        r0 = r11.getId();
        r1 = com.xunlei.tdlive.R.id.cancel;
        if (r0 != r1) goto L_0x0069;
    L_0x0065:
        r10.finish();
        goto L_0x003e;
    L_0x0069:
        r0 = r11.getId();
        r1 = com.xunlei.tdlive.R.id.rotate_left;
        if (r0 != r1) goto L_0x0079;
    L_0x0071:
        r0 = r10.a;
        r1 = com.xunlei.tdlive.control.CropImageView.c.b;
        r0.rotate(r1);
        goto L_0x003e;
    L_0x0079:
        r0 = r11.getId();
        r1 = com.xunlei.tdlive.R.id.rotate_right;
        if (r0 != r1) goto L_0x003e;
    L_0x0081:
        r0 = r10.a;
        r1 = com.xunlei.tdlive.control.CropImageView.c.c;
        r0.rotate(r1);
        goto L_0x003e;
    L_0x0089:
        r0 = move-exception;
        r0 = r1;
        goto L_0x0055;
    L_0x008c:
        r1 = r0;
        goto L_0x002b;
        */
    }
}
