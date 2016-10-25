package com.xunlei.tdlive.a;

import android.content.Context;
import android.os.Handler;
import android.support.v7.widget.RecyclerView.t;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;
import android.widget.TextView;
import com.xunlei.tdlive.R;
import com.xunlei.tdlive.a.ae.d;
import com.xunlei.tdlive.control.RoundImageView;
import com.xunlei.tdlive.protocol.LevelInfo;
import java.util.ArrayList;
import java.util.List;
import org.eclipse.paho.client.mqttv3.MqttTopic;

// compiled from: VisitorHListAdapter.java
public class ae extends android.support.v7.widget.RecyclerView.a<t> {
    private static ArrayList<d> g;
    private List<d> a;
    private long b;
    private OnItemClickListener c;
    private Context d;
    private long e;
    private Handler f;

    // compiled from: VisitorHListAdapter.java
    private static class a extends t {
        public TextView a;

        public a(View view) {
            super(view);
            this.a = (TextView) view.findViewById(R.id.count);
        }
    }

    // compiled from: VisitorHListAdapter.java
    private static final class b extends t {
        private ImageView a;
        private ImageView b;

        public b(View view) {
            super(view);
            this.a = (ImageView) view.findViewById(R.id.avatar);
            this.b = (ImageView) view.findViewById(R.id.avatar_frame);
        }

        public final void a(int i, d dVar) {
            if (dVar != null) {
                com.xunlei.tdlive.util.a.a(this.itemView.getContext()).a(this.a, dVar.c, com.xunlei.tdlive.util.a.a(this.itemView.getContext(), R.drawable.xllive_avatar_default));
                LayoutParams layoutParams = (LayoutParams) this.b.getLayoutParams();
                if (i == 0) {
                    layoutParams.width = (int) com.xunlei.tdlive.util.d.a(this.b.getContext(), 48.0f);
                    this.b.setImageResource(R.drawable.xllive_vip_seat_1);
                } else if (i == 1) {
                    layoutParams.width = (int) com.xunlei.tdlive.util.d.a(this.b.getContext(), 32.0f);
                    this.b.setImageResource(R.drawable.xllive_vip_seat_2);
                } else if (i == 2) {
                    layoutParams.width = (int) com.xunlei.tdlive.util.d.a(this.b.getContext(), 32.0f);
                    this.b.setImageResource(R.drawable.xllive_vip_seat_3);
                } else {
                    return;
                }
                this.b.setLayoutParams(layoutParams);
            }
        }
    }

    // compiled from: VisitorHListAdapter.java
    private static class c extends t {
        public RoundImageView a;
        public RoundImageView b;

        public c(View view) {
            super(view);
            this.a = (RoundImageView) view.findViewById(R.id.head_image);
            this.b = (RoundImageView) view.findViewById(R.id.experience_grade_image);
        }
    }

    // compiled from: VisitorHListAdapter.java
    public static class d {
        public String a;
        public String b;
        public String c;
        public String d;
        public LevelInfo e;
        public int f;
    }

    public ae(Context context) {
        this.a = new ArrayList();
        this.b = 0;
        this.e = 0;
        this.f = new Handler(new af(this));
        this.d = context;
    }

    private boolean a() {
        return this.b > 40;
    }

    public void a(OnItemClickListener onItemClickListener) {
        this.c = onItemClickListener;
    }

    public void a(List<d> list) {
        a((List) list, 0);
    }

    public void a(List<d> list, long j) {
        this.a = list;
        this.b = j;
        b();
    }

    private void b() {
        long currentTimeMillis = System.currentTimeMillis();
        if (this.e != 0) {
            currentTimeMillis -= this.e;
            if (currentTimeMillis < 1500) {
                currentTimeMillis = 1500 - currentTimeMillis;
                if (currentTimeMillis != 0) {
                    if (!this.f.hasMessages(com.xunlei.xllib.R.styleable.AppCompatTheme_buttonStyle)) {
                        this.f.sendEmptyMessage(com.xunlei.xllib.R.styleable.AppCompatTheme_buttonStyle);
                    }
                } else if (currentTimeMillis > 0) {
                    this.f.sendEmptyMessageDelayed(com.xunlei.xllib.R.styleable.AppCompatTheme_buttonStyle, currentTimeMillis);
                }
            }
        }
        currentTimeMillis = 0;
        if (currentTimeMillis != 0) {
            if (currentTimeMillis > 0) {
                this.f.sendEmptyMessageDelayed(com.xunlei.xllib.R.styleable.AppCompatTheme_buttonStyle, currentTimeMillis);
            }
        } else if (!this.f.hasMessages(com.xunlei.xllib.R.styleable.AppCompatTheme_buttonStyle)) {
            this.f.sendEmptyMessage(com.xunlei.xllib.R.styleable.AppCompatTheme_buttonStyle);
        }
    }

    public int getItemViewType(int i) {
        if (a() && i == getItemCount() - 1) {
            return 1;
        }
        return (((d) this.a.get(i)).f <= 0 || i < 0 || i > 2) ? 0 : 2;
    }

    public long getItemId(int i) {
        return super.getItemId(i);
    }

    public int getItemCount() {
        int i = com.xunlei.xllib.R.styleable.AppCompatTheme_textAppearanceLargePopupMenu;
        int size = this.a.size();
        if (size <= 40) {
            i = size;
        }
        return i + (a() ? 1 : 0);
    }

    public t onCreateViewHolder(ViewGroup viewGroup, int i) {
        LayoutInflater from = LayoutInflater.from(viewGroup.getContext());
        if (i == 0) {
            return new c(from.inflate(R.layout.xllive_visitor_list_item, viewGroup, false));
        }
        if (i == 1) {
            return new a(from.inflate(R.layout.xllive_visitor_list_count_item, viewGroup, false));
        }
        return i == 2 ? new b(from.inflate(R.layout.xllive_vip_seats, viewGroup, false)) : null;
    }

    public void onBindViewHolder(t tVar, int i) {
        int itemViewType = getItemViewType(i);
        if ((itemViewType == 0 && tVar.itemView != null) || (itemViewType == 2 && tVar.itemView != null)) {
            tVar.itemView.setOnClickListener(new ag(this, tVar, i));
        } else if (itemViewType == 1 && tVar.itemView != null) {
            tVar.itemView.setOnClickListener(null);
        }
        if (itemViewType == 0) {
            c cVar = (c) tVar;
            d dVar = (d) this.a.get(i);
            if (cVar != null && cVar.a != null) {
                a(cVar.a, dVar.c, R.drawable.xllive_avatar_default);
                a(cVar, dVar);
            }
        } else if (itemViewType == 1) {
            CharSequence charSequence;
            a aVar = (a) tVar;
            long j = this.b - 40;
            if (j >= 10000) {
                charSequence = ((int) (((float) j) / 10000.0f)) + "W+";
            } else if (j >= 1000) {
                charSequence = ((int) (((float) j) / 1000.0f)) + "K+";
            } else {
                charSequence = j + MqttTopic.SINGLE_LEVEL_WILDCARD;
            }
            if (aVar != null && aVar.a != null) {
                aVar.a.setText(charSequence);
            }
        } else if (itemViewType == 2) {
            ((b) tVar).a(i, (d) this.a.get(i));
        }
    }

    private void a(c cVar, d dVar) {
        if (dVar.e != null) {
            if (!TextUtils.isEmpty(dVar.e.icon)) {
                a(cVar.b, dVar.e.getIconFullPath(), R.drawable.xllive_user_grade_zero);
            } else if (!TextUtils.isEmpty(dVar.e.icon2)) {
                a(cVar.b, dVar.e.getIcon2FullPath(), R.drawable.xllive_user_grade_zero);
            } else {
                return;
            }
            cVar.b.setVisibility(0);
        }
    }

    private void a(ImageView imageView, String str, int i) {
        com.xunlei.tdlive.util.a.a(this.d).a(imageView, str, com.xunlei.tdlive.util.a.a(this.d, i));
    }
}
