package com.umeng.fb;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;
import com.umeng.fb.fragment.FeedbackFragment;
import com.umeng.fb.res.d;
import com.umeng.fb.res.e;

public class ConversationActivity extends FragmentActivity {
    private final String a;
    private FeedbackFragment b;

    public ConversationActivity() {
        this.a = ConversationActivity.class.getName();
    }

    @SuppressLint({"NewApi"})
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (VERSION.SDK_INT >= 11 && getActionBar() != null) {
            getActionBar().setDisplayHomeAsUpEnabled(true);
        }
        setContentView(e.a(this));
        String stringExtra = getIntent().getStringExtra(FeedbackFragment.BUNDLE_KEY_CONVERSATION_ID);
        if (stringExtra == null) {
            stringExtra = new FeedbackAgent(this).getDefaultConversation().getId();
        }
        this.b = FeedbackFragment.newInstance(stringExtra);
        getSupportFragmentManager().beginTransaction().add(d.q(this), this.b).commit();
    }

    protected void onNewIntent(Intent intent) {
        this.b.addPushDevReply();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case 16908332:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(menuItem);
        }
    }
}
