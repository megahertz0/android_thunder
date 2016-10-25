package com.umeng.fb.fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import com.umeng.fb.FeedbackAgent;
import com.umeng.fb.SyncListener;
import com.umeng.fb.model.Conversation;
import com.umeng.fb.model.Conversation$OnChangeListener;
import com.umeng.fb.model.FbSwitch;
import com.umeng.fb.model.Reply;
import com.umeng.fb.model.Store;
import com.umeng.fb.model.UserInfo;
import com.umeng.fb.net.a;
import com.umeng.fb.push.FeedbackPushImpl;
import com.umeng.fb.push.FeedbackPushImpl.IFeedbackPushCallbacks;
import com.umeng.fb.res.b;
import com.umeng.fb.res.c;
import com.umeng.fb.res.d;
import com.umeng.fb.res.e;
import com.umeng.fb.res.f;
import com.umeng.fb.util.Log;
import com.umeng.fb.widget.InterceptTouchSwipeRefreshLayout;
import com.xunlei.xiazaibao.BuildConfig;
import com.xunlei.xllib.R;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.android.spdy.SpdyProtocol;
import org.apache.commons.logging.impl.SimpleLog;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

public class FeedbackFragment extends Fragment implements OnRefreshListener {
    public static final String BUNDLE_KEY_CONVERSATION_ID = "conversation_id";
    private static final String a;
    private static final String p = ": ";
    private static final int q = 0;
    private static final int r = 1;
    private Button b;
    private EditText c;
    private TextView d;
    private TextView e;
    private TextView f;
    private InterceptTouchSwipeRefreshLayout g;
    private ListView h;
    private Spinner i;
    private ReplyListAdapter j;
    private FeedbackAgent k;
    private Conversation l;
    private FeedbackPushImpl m;
    private String[] n;
    private String[] o;
    private int s;
    private Context t;
    private Handler u;
    private List<Map<String, String>> v;
    private final Runnable w;
    private final Runnable x;

    class AnonymousClass_2 implements OnClickListener {
        final /* synthetic */ View a;

        AnonymousClass_2(View view) {
            this.a = view;
        }

        public void onClick(View view) {
            if (FeedbackFragment.this.s != 1) {
                FeedbackFragment.this.a((int) r, this.a);
            }
        }
    }

    class AnonymousClass_3 implements OnTouchListener {
        final /* synthetic */ View a;

        AnonymousClass_3(View view) {
            this.a = view;
        }

        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (FeedbackFragment.this.s != 0 && motionEvent.getAction() == 1) {
                FeedbackFragment.this.a((int) q, this.a);
            }
            view.performClick();
            return false;
        }
    }

    class AnonymousClass_6 implements OnClickListener {
        final /* synthetic */ int a;
        final /* synthetic */ View b;

        AnonymousClass_6(int i, View view) {
            this.a = i;
            this.b = view;
        }

        public void onClick(View view) {
            String trim = FeedbackFragment.this.getEditableText().toString().trim();
            if (!TextUtils.isEmpty(trim)) {
                FeedbackFragment.this.getEditableText().clear();
                if (this.a == 1) {
                    FeedbackFragment.this.c(trim);
                    FeedbackFragment.this.a((int) q, this.b);
                    return;
                }
                FeedbackFragment.this.l.addUserReply(trim);
                FeedbackFragment.this.a();
                FeedbackFragment.this.b();
            }
        }
    }

    class FeedbackPushCallbacks implements IFeedbackPushCallbacks {
        FeedbackPushCallbacks() {
        }

        public void onAddPushDevReply() {
            FeedbackFragment.this.u.post(FeedbackFragment.this.x);
        }
    }

    class ReplyListAdapter extends BaseAdapter {
        LayoutInflater a;
        Conversation b;

        class AnonymousClass_1 implements Conversation$OnChangeListener {
            final /* synthetic */ FeedbackFragment a;

            AnonymousClass_1(FeedbackFragment feedbackFragment) {
                this.a = feedbackFragment;
            }

            public void onChange() {
                ReplyListAdapter.this.notifyDataSetChanged();
            }
        }

        class ViewHolder {
            TextView a;
            TextView b;
            View c;
            View d;
            ImageView e;

            ViewHolder() {
            }
        }

        public ReplyListAdapter(Context context, Conversation conversation) {
            this.a = LayoutInflater.from(FeedbackFragment.this.t);
            this.b = conversation;
            this.b.setOnChangeListener(new AnonymousClass_1(FeedbackFragment.this));
        }

        public int getCount() {
            List replyList = this.b.getReplyList();
            return replyList == null ? q : replyList.size();
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            ViewHolder viewHolder;
            int i2 = r;
            if (view == null) {
                view = this.a.inflate(e.b(FeedbackFragment.this.t), null);
                ViewHolder viewHolder2 = new ViewHolder();
                viewHolder2.b = (TextView) view.findViewById(d.b(FeedbackFragment.this.t));
                viewHolder2.a = (TextView) view.findViewById(d.e(FeedbackFragment.this.t));
                viewHolder2.d = view.findViewById(d.i(FeedbackFragment.this.t));
                FeedbackFragment.this = view.findViewById(d.o(FeedbackFragment.this.t));
                viewHolder2.e = (ImageView) view.findViewById(d.p(FeedbackFragment.this.t));
                view.setTag(viewHolder2);
                viewHolder = viewHolder2;
            } else {
                viewHolder = (ViewHolder) view.getTag();
            }
            Reply reply = (Reply) this.b.getReplyList().get(i);
            if (a.g.equals(reply.type)) {
                FeedbackFragment.this.setBackgroundColor(FeedbackFragment.this.getResources().getColor(b.a(FeedbackFragment.this.t)));
                viewHolder.a.setText(a(reply.created_at));
            } else {
                FeedbackFragment.this.setBackgroundColor(FeedbackFragment.this.getResources().getColor(b.c(FeedbackFragment.this.t)));
                if (Reply.STATUS_NOT_SENT.equals(reply.status)) {
                    viewHolder.a.setText(f.d(FeedbackFragment.this.t));
                    viewHolder.e.setOnClickListener(new OnClickListener() {
                        public void onClick(View view) {
                            FeedbackFragment.this.b();
                        }
                    });
                    viewHolder.e.setImageResource(c.a(FeedbackFragment.this.t));
                    viewHolder.e.setAnimation(null);
                    viewHolder.e.setVisibility(q);
                } else if (Reply.STATUS_SENDING.equals(reply.status)) {
                    viewHolder.a.setText(f.e(FeedbackFragment.this.t));
                    viewHolder.e.setImageResource(c.a(FeedbackFragment.this.t));
                    viewHolder.e.setVisibility(q);
                    Animation rotateAnimation = new RotateAnimation(0.0f, -360.0f, 1, 0.5f, 1, 0.5f);
                    rotateAnimation.setInterpolator(new LinearInterpolator());
                    rotateAnimation.setRepeatCount(-1);
                    rotateAnimation.setDuration(700);
                    viewHolder.e.startAnimation(rotateAnimation);
                    viewHolder.e.setOnClickListener(null);
                } else {
                    viewHolder.a.setText(a(reply.created_at));
                    viewHolder.e.setAnimation(null);
                    viewHolder.e.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
                    viewHolder.e.setOnClickListener(null);
                }
            }
            viewHolder.b.setText(reply.content);
            viewHolder.d.setVisibility(q);
            if (i + 1 < getCount()) {
                int i3;
                Reply reply2 = (Reply) this.b.getReplyList().get(i + 1);
                if (Reply.TYPE_NEW_FEEDBACK.equals(reply.type) && Reply.TYPE_USER_REPLY.equals(reply2.type)) {
                    i3 = 1;
                } else {
                    i3 = 0;
                }
                int equals = reply2.type.equals(reply.type) | i3;
                if (i + 1 != getCount()) {
                    i2 = 0;
                }
                if ((equals | i2) != 0) {
                    viewHolder.d.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
                }
            }
            return view;
        }

        public Object getItem(int i) {
            return this.b.getReplyList().get(i);
        }

        public long getItemId(int i) {
            return (long) i;
        }

        private String a(long j) {
            Date date = new Date();
            Date date2 = new Date(j);
            Calendar instance = Calendar.getInstance();
            instance.setTime(date);
            Calendar instance2 = Calendar.getInstance();
            instance2.setTime(date2);
            int i = instance.get(r) == instance2.get(r) ? 1 : 0;
            long toMinutes = TimeUnit.MILLISECONDS.toMinutes(date.getTime() - j);
            if (toMinutes < 1) {
                return FeedbackFragment.this.getResources().getString(f.f(FeedbackFragment.this.t));
            }
            if (toMinutes >= 30) {
                return i != 0 ? new SimpleDateFormat(FeedbackFragment.this.getResources().getString(f.h(FeedbackFragment.this.t)), Locale.CHINA).format(date2) : new SimpleDateFormat(FeedbackFragment.this.getResources().getString(f.i(FeedbackFragment.this.t)), Locale.CHINA).format(date2);
            } else {
                return String.format(FeedbackFragment.this.getResources().getString(f.g(FeedbackFragment.this.t)), new Object[]{Long.valueOf(toMinutes)});
            }
        }
    }

    public FeedbackFragment() {
        this.s = 0;
        this.u = new Handler();
        this.w = new Runnable() {
            public void run() {
                FeedbackFragment.this.g.setRefreshing(false);
            }
        };
        this.x = new Runnable() {
            public void run() {
                FeedbackFragment.this.d();
            }
        };
    }

    static {
        a = FeedbackFragment.class.getName();
    }

    public static FeedbackFragment newInstance(String str) {
        Log.c(a, String.format("newInstance(id=%s)", new Object[]{str}));
        FeedbackFragment feedbackFragment = new FeedbackFragment();
        Bundle bundle = new Bundle();
        bundle.putString(BUNDLE_KEY_CONVERSATION_ID, str);
        feedbackFragment.setArguments(bundle);
        return feedbackFragment;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.t = getActivity();
        Log.c(a, String.format("onCreateView(savedInstanceState=%s)", new Object[]{bundle}));
        this.n = getResources().getStringArray(com.umeng.fb.res.a.b(this.t));
        this.o = getResources().getStringArray(com.umeng.fb.res.a.a(this.t));
        View inflate = layoutInflater.inflate(e.c(this.t), null, false);
        this.k = new FeedbackAgent(getActivity());
        this.m = (FeedbackPushImpl) FeedbackPushImpl.getInstance(getActivity());
        this.m.setFBPushCallbacks(new FeedbackPushCallbacks());
        Object string = getArguments().getString(BUNDLE_KEY_CONVERSATION_ID);
        this.m.conversation_id = string;
        if (TextUtils.isEmpty(string)) {
            return inflate;
        }
        this.l = this.k.getConversationById(string);
        c();
        if (this.l == null) {
            return inflate;
        }
        this.h = (ListView) inflate.findViewById(d.a(this.t));
        View findViewById = inflate.findViewById(d.g(this.t));
        View inflate2 = layoutInflater.inflate(e.d(this.t), null, false);
        View findViewById2 = inflate2.findViewById(d.h(this.t));
        this.e = (TextView) findViewById2.findViewById(d.b(this.t));
        this.d = (TextView) findViewById2.findViewById(d.e(this.t));
        b(a(null));
        this.e.setTextColor(getResources().getColor(b.a(this.t)));
        inflate2.findViewById(d.i(this.t)).setBackgroundColor(getResources().getColor(b.a(this.t)));
        findViewById2.setOnClickListener(new AnonymousClass_2(findViewById));
        this.h.setHeaderDividersEnabled(true);
        this.h.addHeaderView(inflate2);
        if (FbSwitch.getInstance(this.t).getWelcomeInfoSwitch()) {
            inflate2 = layoutInflater.inflate(e.e(this.t), null, false);
            this.f = (TextView) inflate2.findViewById(d.j(this.t));
            if (FbSwitch.getInstance(this.t).getWelcomeInfo() != null) {
                this.f.setText(FbSwitch.getInstance(this.t).getWelcomeInfo());
            }
            this.h.addHeaderView(inflate2);
        }
        this.j = new ReplyListAdapter(getActivity(), this.l);
        this.h.setAdapter(this.j);
        this.g = (InterceptTouchSwipeRefreshLayout) inflate.findViewById(d.k(this.t));
        this.g.setOnRefreshListener(this);
        this.g.setColorSchemeResources(new int[]{b.a(this.t), b.b(this.t), b.a(this.t), b.b(this.t)});
        this.g.setInterceptTouch(new AnonymousClass_3(findViewById));
        a((int) q, findViewById);
        b();
        return inflate;
    }

    private void a(int i, View view) {
        this.s = i;
        View inflate;
        if (i == 1) {
            inflate = View.inflate(getActivity(), e.f(this.t), null);
            this.i = (Spinner) inflate.findViewById(d.l(this.t));
            SpinnerAdapter createFromResource = ArrayAdapter.createFromResource(getActivity(), com.umeng.fb.res.a.a(this.t), e.g(this.t));
            createFromResource.setDropDownViewResource(17367049);
            this.i.setAdapter(createFromResource);
            ((ViewGroup) view).removeAllViews();
            ((ViewGroup) view).addView(inflate);
        } else {
            inflate = View.inflate(getActivity(), e.h(this.t), null);
            ((ViewGroup) view).removeAllViews();
            ((ViewGroup) view).addView(inflate);
        }
        this.b = (Button) view.findViewById(d.m(this.t));
        this.c = (EditText) view.findViewById(d.n(this.t));
        if (i != 1 || this.i == null) {
            this.c.setInputType(131073);
        } else {
            if (this.v == null) {
                this.v = new ArrayList();
            }
            this.c.requestFocus();
            this.i.setOnItemSelectedListener(new OnItemSelectedListener() {
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
                    switch (i) {
                        case q:
                            FeedbackFragment.this.c.setInputType(R.styleable.AppCompatTheme_actionModeCopyDrawable);
                            break;
                        case r:
                            FeedbackFragment.this.c.setInputType(SimpleLog.LOG_LEVEL_DEBUG);
                            break;
                        case SimpleLog.LOG_LEVEL_DEBUG:
                            FeedbackFragment.this.c.setInputType(MqttConnectOptions.MQTT_VERSION_3_1);
                            break;
                        case MqttConnectOptions.MQTT_VERSION_3_1:
                            FeedbackFragment.this.c.setInputType(131073);
                            break;
                    }
                    FeedbackFragment.this.c.setText(FeedbackFragment.this.a(FeedbackFragment.this.n[i]));
                    FeedbackFragment.this.c.requestFocus();
                }

                public void onNothingSelected(AdapterView<?> adapterView) {
                    FeedbackFragment.this.c.setInputType(131073);
                }
            });
            this.i.setSelection(e());
        }
        this.c.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable editable) {
                if (TextUtils.isEmpty(FeedbackFragment.this.c.getText().toString())) {
                    FeedbackFragment.this.b.setEnabled(false);
                    FeedbackFragment.this.b.setTextColor(FeedbackFragment.this.getResources().getColor(b.c(FeedbackFragment.this.t)));
                    return;
                }
                FeedbackFragment.this.b.setEnabled(true);
                FeedbackFragment.this.b.setTextColor(FeedbackFragment.this.getResources().getColor(17170444));
            }

            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }
        });
        this.b.setOnClickListener(new AnonymousClass_6(i, view));
    }

    public void onRefresh() {
        b();
    }

    private void b() {
        this.l.sync(new SyncListener() {
            public void onSendUserReply(List<Reply> list) {
            }

            public void onReceiveDevReply(List<Reply> list) {
                FeedbackFragment.this.u.post(FeedbackFragment.this.w);
            }
        });
    }

    void a() {
        if (this.j.getCount() > 0) {
            this.h.smoothScrollToPosition(this.j.getCount());
        }
    }

    private void c() {
        for (int i = 0; i < this.m.devReplyList.size(); i++) {
            Reply reply = (Reply) this.m.devReplyList.get(i);
            Object obj = null;
            for (Reply reply2 : this.l.getReplyList()) {
                obj = reply.created_at == reply2.created_at ? r : obj;
            }
            if (obj == null && reply.feedback_id != null && reply.feedback_id.equals(this.l.getId())) {
                this.l.addReply(reply);
            }
            Log.c(a, reply.content);
        }
        this.m.devReplyList.clear();
    }

    public void addPushDevReply() {
        if (this.m.devReplyList.size() > 0 && !((Reply) this.m.devReplyList.get(this.m.devReplyList.size() - 1)).feedback_id.equals(this.l.getId())) {
            this.l = this.k.getConversationById(((Reply) this.m.devReplyList.get(this.m.devReplyList.size() - 1)).feedback_id);
            this.j = null;
            this.j = new ReplyListAdapter(getActivity(), this.l);
            this.h.setAdapter(this.j);
            this.m.conversation_id = this.l.getId();
        }
        c();
        this.j.notifyDataSetChanged();
    }

    private void d() {
        List arrayList = new ArrayList();
        for (Reply reply : this.m.devReplyList) {
            if (reply.feedback_id != null && !reply.feedback_id.equals(this.l.getId())) {
                arrayList.add(reply);
            }
        }
        addPushDevReply();
        this.m.devReplyList = arrayList;
    }

    private String a(String str) {
        UserInfo userInfo = this.k.getUserInfo();
        if (userInfo == null) {
            return null;
        }
        Map contact = userInfo.getContact();
        if (contact == null) {
            return null;
        }
        String str2 = BuildConfig.VERSION_NAME;
        if (str != null) {
            return (String) contact.get(str);
        }
        for (String str3 : contact.keySet()) {
            String str32;
            if (contact.get(str32) == null || d(str32) == null) {
                str32 = str2;
            } else {
                str32 = str2 + d(str32) + p + ((String) contact.get(str32)) + "\t";
            }
            str2 = str32;
        }
        return BuildConfig.VERSION_NAME.equals(str2) ? null : str2;
    }

    private void b(String str) {
        if (str != null) {
            this.d.setText(str);
            this.e.setText(getResources().getString(f.j(this.t)));
            return;
        }
        this.d.setText(getResources().getString(f.k(this.t)));
        this.e.setText(getResources().getString(f.l(this.t)));
    }

    private void c(String str) {
        String str2 = this.n[this.i.getSelectedItemPosition()];
        if (!str.equals(a(str2))) {
            UserInfo userInfo = this.k.getUserInfo();
            if (userInfo == null) {
                userInfo = new UserInfo();
            }
            Map contact = userInfo.getContact();
            if (contact == null) {
                contact = new HashMap();
            }
            contact.put(str2, str);
            userInfo.setContact(contact);
            this.k.setUserInfo(userInfo);
            b(a(null));
            new Thread(new Runnable() {
                public void run() {
                    new a(FeedbackFragment.this.getActivity()).a(Store.getInstance(FeedbackFragment.this.getActivity()).getUserInfo().toJson());
                }
            }).start();
        }
    }

    private String d(String str) {
        for (int i = q; i < this.n.length; i++) {
            if (this.n[i].endsWith(str)) {
                return this.o[i];
            }
        }
        return null;
    }

    private int e() {
        for (int i = 0; i < this.n.length; i++) {
            if (a(this.n[i]) != null) {
                return i;
            }
        }
        return 0;
    }

    public void onResume() {
        super.onResume();
        this.m.fbFragmentTag = true;
    }

    public void onPause() {
        super.onPause();
        this.m.fbFragmentTag = false;
    }
}
