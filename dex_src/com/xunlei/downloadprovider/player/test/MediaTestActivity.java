package com.xunlei.downloadprovider.player.test;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.widget.ListView;
import com.xunlei.downloadprovider.R;
import com.xunlei.downloadprovider.player.q;
import java.util.ArrayList;

public class MediaTestActivity extends Activity {
    private ArrayList<String> a;
    private ListView b;
    private b c;

    public MediaTestActivity() {
        this.a = new ArrayList();
        this.a.add("http://7xnusr.com1.z0.glb.clouddn.com/737805484deb5bff299ee08501ce6c581e25fb44?e=1467695645&token=8e4YkwOPAwrhUijy7FMfODl6WpNWmF9LiYknl5WH:Xbb92yAWisCMNDe4nkBYgGfTUcA=");
        this.a.add("http://7xnusr.com1.z0.glb.clouddn.com/04ab9bc851c07f0ca264a205862b7d189a0270b7?e=1467695645&token=8e4YkwOPAwrhUijy7FMfODl6WpNWmF9LiYknl5WH:CpY5KIi6c7Ha-iz8Y2oE8IUTn-Y=");
        this.a.add("http://7xnusr.com1.z0.glb.clouddn.com/04ab9bc851c07f0ca264a205862b7d189a0270b7?e=1467695645&token=8e4YkwOPAwrhUijy7FMfODl6WpNWmF9LiYknl5WH:CpY5KIi6c7Ha-iz8Y2oE8IUTn-Y=");
        this.a.add("http://7xnusr.com1.z0.glb.clouddn.com/04ab9bc851c07f0ca264a205862b7d189a0270b7?e=1467695645&token=8e4YkwOPAwrhUijy7FMfODl6WpNWmF9LiYknl5WH:CpY5KIi6c7Ha-iz8Y2oE8IUTn-Y=");
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_main);
        this.b = (ListView) findViewById(com.xunlei.downloadprovidershare.R.id.listView);
        this.c = new b(this);
        this.b.setAdapter(this.c);
        this.c.a = this.a;
    }

    protected void onUserLeaveHint() {
        super.onUserLeaveHint();
    }

    public void onConfigurationChanged(Configuration configuration) {
        new StringBuilder("onConfigurationChanged--newConfig=").append(configuration);
        super.onConfigurationChanged(configuration);
    }

    public void onBackPressed() {
        if (!q.a().b()) {
            super.onBackPressed();
        }
    }
}
