package com.xunlei.downloadprovider.xiazaibao.remotedownload;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.xunlei.downloadprovider.a.g;
import com.xunlei.downloadprovider.app.BaseActivity;
import com.xunlei.downloadprovider.businessutil.XLFileTypeUtil;
import com.xunlei.downloadprovider.download.taskDetail.TaskDetailDragView;
import com.xunlei.downloadprovider.download.taskDetail.widget.DownloadTaskNameAndIconView;
import com.xunlei.downloadprovider.xiazaibao.view.a;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import com.xunlei.xiazaibao.sdk.entities.DownloadTaskInfo;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;
import org.android.spdy.SpdyAgent;

public class XZBTaskInfoDetailFragment extends Fragment {
    private static final TimeZone R;
    private TextView A;
    private View B;
    private TextView C;
    private View D;
    private TextView E;
    private TextView F;
    private TextView G;
    private TextView H;
    private View I;
    private TextView J;
    private View K;
    private View L;
    private View M;
    private LinearLayout N;
    private int O;
    private a P;
    private RemoteDownloadListActivity Q;
    al a;
    TextView b;
    TaskDetailDragView c;
    Animation d;
    Animation e;
    g.a f;
    private ImageView g;
    private ImageView h;
    private ImageView i;
    private DownloadTaskNameAndIconView j;
    private Animation k;
    private Animation l;
    private RelativeLayout m;
    private TextView n;
    private TextView o;
    private View p;
    private View q;
    private TextView r;
    private TextView s;
    private View t;
    private View u;
    private TextView v;
    private TextView w;
    private TextView x;
    private TextView y;
    private View z;

    public XZBTaskInfoDetailFragment() {
        this.O = 2;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.Q = (RemoteDownloadListActivity) getActivity();
        View inflate = layoutInflater.inflate(2130968778, viewGroup, false);
        DownloadTaskNameAndIconView downloadTaskNameAndIconView = (DownloadTaskNameAndIconView) inflate.findViewById(2131756071);
        if (downloadTaskNameAndIconView.f != null) {
            downloadTaskNameAndIconView.f.setVisibility(XZBDevice.Wait);
        }
        if (downloadTaskNameAndIconView.g != null) {
            downloadTaskNameAndIconView.g.setVisibility(XZBDevice.Wait);
        }
        this.b = (TextView) inflate.findViewById(2131755780);
        this.c = (TaskDetailDragView) inflate.findViewById(2131755785);
        this.g = (ImageView) inflate.findViewById(R.id.close_btn);
        this.h = (ImageView) inflate.findViewById(2131755784);
        this.i = (ImageView) inflate.findViewById(2131755783);
        this.j = (DownloadTaskNameAndIconView) inflate.findViewById(2131756071);
        this.m = (RelativeLayout) inflate.findViewById(2131756073);
        View view = this.m;
        if (view != null) {
            this.N = (LinearLayout) view.findViewById(2131756375);
            this.n = (TextView) view.findViewById(2131756381);
            this.o = (TextView) view.findViewById(2131756382);
            this.p = view.findViewById(2131756378);
            this.q = view.findViewById(2131756383);
            this.r = (TextView) view.findViewById(2131756384);
            this.s = (TextView) view.findViewById(2131756393);
            this.t = view.findViewById(2131756391);
            this.E = (TextView) view.findViewById(2131756404);
            this.H = (TextView) view.findViewById(2131756416);
            this.G = (TextView) view.findViewById(2131756412);
            this.F = (TextView) view.findViewById(2131756408);
            this.A = (TextView) view.findViewById(2131756400);
            this.B = view.findViewById(2131756398);
            this.v = (TextView) view.findViewById(2131756387);
            this.v.setOnClickListener(new au(this));
            this.u = view.findViewById(2131756385);
            this.x = (TextView) view.findViewById(2131756388);
            this.x.setOnClickListener(new aw(this));
            this.w = (TextView) view.findViewById(2131756389);
            this.w.setOnClickListener(new ay(this));
            this.y = (TextView) view.findViewById(2131756380);
            this.z = view.findViewById(2131756377);
            this.L = view.findViewById(2131756390);
            this.K = view.findViewById(2131756376);
            this.J = (TextView) view.findViewById(2131756420);
            this.I = view.findViewById(2131756418);
            this.D = view.findViewById(2131756395);
            this.C = (TextView) view.findViewById(2131756397);
            this.M = view.findViewById(2131756374);
        }
        this.M.setVisibility(0);
        this.b.setOnClickListener(new be(this));
        this.g.setOnClickListener(new at(this));
        this.i.setOnClickListener(new ba(this));
        this.h.setVisibility(XZBDevice.DOWNLOAD_LIST_ALL);
        this.c.setIdleY(g.a(getActivity(), 92.0f));
        this.c.setContentListId(2131757045);
        this.c.setListener(new bd(this));
        return inflate;
    }

    public final void a() {
        if (this.a != null && this.j != null) {
            CharSequence string;
            this.j.c.setText(com.xunlei.downloadprovider.download.util.a.b(this.a.getSize()));
            this.j.b.setText(this.a.getName());
            this.j.d.setVisibility(XZBDevice.Wait);
            if (this.a.getType() == 2) {
                this.j.a.setImageResource(R.drawable.ic_dl_bt);
            } else {
                int d;
                switch (AnonymousClass_1.a[XLFileTypeUtil.a(this.a.getName()).ordinal()]) {
                    case SpdyAgent.ACCS_ONLINE_SERVER:
                        d = XLFileTypeUtil.d(this.a.getName());
                        break;
                    case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                        d = R.drawable.ic_dl_music;
                        break;
                    case XZBDevice.DOWNLOAD_LIST_FAILED:
                        d = R.drawable.ic_dl_text;
                        break;
                    case XZBDevice.DOWNLOAD_LIST_ALL:
                        d = R.drawable.ic_dl_apk;
                        break;
                    case XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED:
                        d = R.drawable.ic_dl_image;
                        break;
                    case R.styleable.Toolbar_contentInsetEnd:
                        d = R.drawable.ic_dl_rar;
                        break;
                    case R.styleable.Toolbar_contentInsetLeft:
                        d = R.drawable.ic_dl_torrent;
                        break;
                    case XZBDevice.Wait:
                        d = R.drawable.ic_dl_other;
                        break;
                    default:
                        d = R.drawable.ic_dl_other;
                        break;
                }
                this.j.a.setImageResource(d);
            }
            if (this.a.getState() == 11) {
                this.N.setVisibility(0);
            } else {
                this.N.setVisibility(XZBDevice.Wait);
            }
            DownloadTaskInfo downloadTaskInfo = this.a;
            if (downloadTaskInfo.getState() == 11) {
                this.L.setVisibility(XZBDevice.Wait);
                this.K.setVisibility(XZBDevice.Wait);
                this.I.setVisibility(0);
                this.J.setText(a(downloadTaskInfo.getCompleteTime()));
            } else {
                this.L.setVisibility(0);
                this.K.setVisibility(0);
                this.I.setVisibility(XZBDevice.Wait);
            }
            DownloadTaskInfo downloadTaskInfo2 = this.a;
            long speed = downloadTaskInfo2.getVipChannel().getSpeed() + downloadTaskInfo2.getLixianChannel().getSpeed();
            String toString = speed > 0 ? new StringBuilder(" (+").append(b(speed)).append("/s)").toString() : com.umeng.a.d;
            String str = b(downloadTaskInfo2.getSpeed()) + "/s" + toString;
            this.q.setVisibility(XZBDevice.Wait);
            if (downloadTaskInfo2.getState() == 11) {
                this.p.setVisibility(XZBDevice.Wait);
            } else if (downloadTaskInfo2.getState() == 0) {
                this.p.setVisibility(0);
                this.n.setText("\u4e0b\u8f7d\u901f\u5ea6");
                this.o.setVisibility(0);
                if (downloadTaskInfo2.getSpeed() + speed < 1) {
                    this.o.setText(2131231234);
                } else if (speed > 0) {
                    CharSequence spannableString = new SpannableString(str);
                    spannableString.setSpan(new ForegroundColorSpan(getActivity().getResources().getColor(R.color.common_blue_button_normal)), str.length() - toString.length(), str.length(), R.styleable.AppCompatTheme_actionModePasteDrawable);
                    spannableString.setSpan(new AbsoluteSizeSpan(g.a(getActivity(), 12.0f)), str.length() - toString.length(), str.length(), R.styleable.AppCompatTheme_actionModePasteDrawable);
                    this.o.setText(spannableString);
                } else {
                    if (downloadTaskInfo2.getSpeed() < 1) {
                        string = getActivity().getString(2131231234);
                    } else {
                        string = com.xunlei.downloadprovider.download.util.a.a(downloadTaskInfo2.getSpeed());
                    }
                    this.o.setText(string);
                }
            } else if (downloadTaskInfo2.getState() == 12) {
                this.p.setVisibility(0);
                this.n.setText("\u4e0b\u8f7d\u72b6\u6001");
                this.o.setVisibility(XZBDevice.Wait);
                this.q.setVisibility(0);
                this.r.setText("\u4efb\u52a1\u5931\u8d25");
            } else if (downloadTaskInfo2.getState() == 9) {
                this.p.setVisibility(0);
                this.n.setText("\u4e0b\u8f7d\u72b6\u6001");
                this.o.setVisibility(0);
                this.q.setVisibility(XZBDevice.Wait);
                this.o.setText(2131231235);
            } else if (downloadTaskInfo2.getState() == 8) {
                this.p.setVisibility(0);
                this.n.setText("\u4e0b\u8f7d\u72b6\u6001");
                this.o.setVisibility(0);
                this.q.setVisibility(XZBDevice.Wait);
                this.o.setText(2131231240);
            }
            downloadTaskInfo = this.a;
            if (11 != downloadTaskInfo.getState()) {
                if (downloadTaskInfo.getState() == 0) {
                    long j;
                    long j2;
                    speed = downloadTaskInfo.getRemainTime();
                    toString = com.umeng.a.d;
                    if (speed <= 0) {
                        toString = "--";
                    }
                    if (speed > 0 && speed < 60) {
                        toString = speed + "\u79d2";
                    }
                    if (speed >= 60 && speed < 3600) {
                        j = speed / 60;
                        j2 = speed % 60;
                        toString = String.format("%02d", new Object[]{Long.valueOf(j)}) + ":" + String.format("%02d", new Object[]{Long.valueOf(j2)});
                    }
                    if (speed >= 3600) {
                        j = speed / 3600;
                        speed %= 3600;
                        if (speed >= 60) {
                            j2 = speed / 60;
                            speed %= 60;
                            string = String.format("%02d", new Object[]{Long.valueOf(j)}) + ":" + String.format("%02d", new Object[]{Long.valueOf(j2)}) + ":" + String.format("%02d", new Object[]{Long.valueOf(speed)});
                        } else {
                            string = String.format("%02d", new Object[]{Long.valueOf(j)}) + ":" + String.format("%02d", new Object[]{Long.valueOf(0)}) + ":" + String.format("%02d", new Object[]{Long.valueOf(speed)});
                        }
                    }
                } else {
                    string = "--";
                }
                this.y.setText(string);
                this.z.setVisibility(0);
            } else {
                this.z.setVisibility(XZBDevice.Wait);
            }
            downloadTaskInfo = this.a;
            this.v.setVisibility(XZBDevice.Wait);
            this.w.setVisibility(XZBDevice.Wait);
            this.x.setVisibility(XZBDevice.Wait);
            this.v.setClickable(true);
            this.w.setClickable(true);
            this.x.setClickable(true);
            switch (downloadTaskInfo.getState()) {
                case SpdyAgent.ACCS_TEST_SERVER:
                case XZBDevice.Wait:
                case XZBDevice.Predownload:
                case XZBDevice.WaitInServer:
                    this.v.setVisibility(0);
                    break;
                case XZBDevice.Pause:
                case XZBDevice.Stop:
                    this.x.setVisibility(0);
                    break;
                case XZBDevice.Fail:
                case XZBDevice.FailInServer:
                    this.w.setVisibility(0);
                    break;
                default:
                    this.v.setVisibility(0);
                    break;
            }
            downloadTaskInfo = this.a;
            if (downloadTaskInfo.getSize() > 0) {
                this.s.setText(com.xunlei.downloadprovider.download.util.a.b(downloadTaskInfo.getSize()));
            } else {
                this.s.setText(2131231241);
            }
            if (11 != downloadTaskInfo.getState()) {
                this.D.setVisibility(0);
                if (downloadTaskInfo.getSize() == 0) {
                    this.C.setText("--");
                } else {
                    this.C.setText(((int) ((((float) ((((long) downloadTaskInfo.getProgress()) * downloadTaskInfo.getSize()) / 10000)) / ((float) downloadTaskInfo.getSize())) * 100.0f)) + "%");
                }
            } else {
                this.D.setVisibility(XZBDevice.Wait);
            }
            downloadTaskInfo = this.a;
            if (downloadTaskInfo.getSize() == 0 || downloadTaskInfo.getProgress() == 0 || downloadTaskInfo.getDownTime() == 0) {
                string = "--";
            } else {
                string = b(((downloadTaskInfo.getSize() / 10000) * ((long) downloadTaskInfo.getProgress())) / downloadTaskInfo.getDownTime()) + "/s";
            }
            if (TextUtils.isEmpty(string)) {
                string = "--";
            }
            this.F.setText(string);
            this.H.setText(a(this.a.getCreateTime()));
        }
    }

    public void onStop() {
        super.onStop();
    }

    final void a(int i) {
        this.O = i;
        BaseActivity baseActivity = (BaseActivity) getActivity();
        if (i == 0) {
            baseActivity.setStatusBarBgColr(2131689507);
        } else if (i == 1) {
            baseActivity.setStatusBarBgColr(2131689628);
        } else {
            baseActivity.setStatusBarBgColr(2131689627);
        }
    }

    public final void b() {
        ((BaseActivity) getActivity()).animationBarAlpha(false);
        this.k = AnimationUtils.loadAnimation(getActivity(), 2131034127);
        this.k.setDuration(300);
        this.k.setAnimationListener(new bh(this));
        this.b.startAnimation(this.k);
        this.l = AnimationUtils.loadAnimation(getActivity(), 2131034144);
        this.l.setDuration(300);
        this.l.setAnimationListener(new bi(this));
        this.c.startAnimation(this.l);
    }

    static {
        R = TimeZone.getDefault();
    }

    private static String a(long j) {
        Calendar instance = Calendar.getInstance(R);
        instance.setTimeInMillis(1000 * j);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        simpleDateFormat.setTimeZone(R);
        return simpleDateFormat.format(instance.getTime());
    }

    private static String b(long j) {
        if (j < 1024) {
            return j + " B";
        }
        int log = (int) (Math.log((double) j) / Math.log(1024.0d));
        String str = "KMGTPE".charAt(log - 1);
        if (log - 1 >= 2) {
            return String.format("%.2f %sB", new Object[]{Double.valueOf(((double) j) / Math.pow(1024.0d, (double) log)), str});
        }
        return String.format("%.1f %sB", new Object[]{Double.valueOf(((double) j) / Math.pow(1024.0d, (double) log)), str});
    }

    static /* synthetic */ void a(XZBTaskInfoDetailFragment xZBTaskInfoDetailFragment) {
        if (xZBTaskInfoDetailFragment.P == null) {
            xZBTaskInfoDetailFragment.P = new a(xZBTaskInfoDetailFragment.getActivity());
            xZBTaskInfoDetailFragment.P.a = new bb(xZBTaskInfoDetailFragment);
        }
        xZBTaskInfoDetailFragment.P.show();
        ((Button) xZBTaskInfoDetailFragment.P.findViewById(2131755759)).setText("\u5220\u9664\u4efb\u52a1");
    }
}
