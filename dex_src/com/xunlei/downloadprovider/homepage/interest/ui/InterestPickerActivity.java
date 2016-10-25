package com.xunlei.downloadprovider.homepage.interest.ui;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton.OnCheckedChangeListener;
import com.xunlei.downloadprovider.app.BaseActivity;
import com.xunlei.downloadprovider.homepage.interest.a.a;
import java.util.ArrayList;
import java.util.List;

public class InterestPickerActivity extends BaseActivity {
    private View a;
    private View b;
    private CheckBox[] c;
    private ProgressDialog d;
    private a e;

    public InterestPickerActivity() {
        this.c = new CheckBox[16];
    }

    static /* synthetic */ void a(InterestPickerActivity interestPickerActivity) {
        interestPickerActivity.d = new ProgressDialog(interestPickerActivity);
        interestPickerActivity.d.setProgressStyle(0);
        interestPickerActivity.d.setCancelable(false);
        interestPickerActivity.d.show();
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(2130968609);
        this.e = new a();
        this.a = findViewById(2131755212);
        this.a.setOnClickListener(new d(this));
        this.b = findViewById(2131755229);
        this.b.setEnabled(false);
        this.b.setOnClickListener(new e(this));
        this.c[1] = (CheckBox) findViewById(2131755214);
        this.c[2] = (CheckBox) findViewById(2131755215);
        this.c[3] = (CheckBox) findViewById(2131755216);
        this.c[4] = (CheckBox) findViewById(2131755217);
        this.c[5] = (CheckBox) findViewById(2131755218);
        this.c[6] = (CheckBox) findViewById(2131755219);
        this.c[7] = (CheckBox) findViewById(2131755220);
        this.c[8] = (CheckBox) findViewById(2131755221);
        this.c[9] = (CheckBox) findViewById(2131755222);
        this.c[10] = (CheckBox) findViewById(2131755223);
        this.c[11] = (CheckBox) findViewById(2131755224);
        this.c[12] = (CheckBox) findViewById(2131755225);
        this.c[13] = (CheckBox) findViewById(2131755226);
        this.c[14] = (CheckBox) findViewById(2131755227);
        this.c[15] = (CheckBox) findViewById(2131755228);
        OnCheckedChangeListener gVar = new g(this);
        for (int i = 1; i <= 15; i++) {
            this.c[i].setOnCheckedChangeListener(gVar);
        }
    }

    protected void onStart() {
        super.onStart();
    }

    static /* synthetic */ List b(InterestPickerActivity interestPickerActivity) {
        List arrayList = new ArrayList();
        for (int i = 1; i <= 15; i++) {
            if (interestPickerActivity.c[i].isChecked()) {
                arrayList.add(Integer.valueOf(i));
            }
        }
        return arrayList;
    }

    static /* synthetic */ void c(InterestPickerActivity interestPickerActivity) {
        if (interestPickerActivity.d != null) {
            interestPickerActivity.d.dismiss();
        }
    }
}
