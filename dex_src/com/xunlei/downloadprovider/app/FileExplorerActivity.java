package com.xunlei.downloadprovider.app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.xunlei.downloadprovider.R;
import com.xunlei.downloadprovider.a.k;
import com.xunlei.downloadprovider.app.ui.FileExplorerListView;
import com.xunlei.downloadprovider.app.ui.FileExplorerListView.c;
import com.xunlei.downloadprovider.app.ui.a;
import org.eclipse.paho.client.mqttv3.internal.ClientDefaults;

public class FileExplorerActivity extends Activity {
    private ImageView a;
    private TextView b;
    private FileExplorerListView c;
    private String d;

    public static void a(Context context, String str) {
        Intent intent = new Intent();
        intent.putExtra("FILE_PATH", str);
        intent.setClass(context, FileExplorerActivity.class);
        intent.setFlags(ClientDefaults.MAX_MSG_SIZE);
        context.startActivity(intent);
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.d = getIntent().getStringExtra("FILE_PATH");
        if (this.d == null) {
            this.d = k.e();
        }
        setContentView(R.layout.bt_fileexplore_activity);
        this.a = (ImageView) findViewById(R.id.titlebar_left_img);
        this.b = (TextView) findViewById(R.id.tip_text);
        this.b.setText("\u6587\u4ef6\u6d4f\u89c8");
        this.b.setVisibility(0);
        this.c = (FileExplorerListView) findViewById(R.id.bt_file_explorer_list);
        FileExplorerListView fileExplorerListView = this.c;
        String str = this.d;
        fileExplorerListView.e = 1000;
        fileExplorerListView.b = str;
        if (fileExplorerListView.e == 1000 || fileExplorerListView.e == 1001) {
            View inflate = LayoutInflater.from(fileExplorerListView.f).inflate(R.layout.bt_file_explorer_filepath, null);
            fileExplorerListView.c = (TextView) inflate.findViewById(R.id.bt_file_explorer_cur_path);
            fileExplorerListView.addHeaderView(inflate);
        }
        fileExplorerListView.setFadingEdgeLength(0);
        fileExplorerListView.setScrollingCacheEnabled(false);
        fileExplorerListView.a = fileExplorerListView.getData();
        fileExplorerListView.d = new c(fileExplorerListView);
        fileExplorerListView.setAdapter(fileExplorerListView.d);
        fileExplorerListView.a();
        fileExplorerListView.setOnItemClickListener(new a(fileExplorerListView));
        this.a.setOnClickListener(new k(this));
    }

    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
