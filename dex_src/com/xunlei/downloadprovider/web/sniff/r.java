package com.xunlei.downloadprovider.web.sniff;

import android.content.Context;
import android.text.Spanned;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.xunlei.downloadprovider.R;
import com.xunlei.downloadprovider.web.sniff.widget.SimpleCHNTextView;
import com.xunlei.downloadprovider.web.sniff.widget.SuffixListView;
import com.xunlei.downloadprovider.web.x;
import com.xunlei.thundersniffer.sniff.SniffingResource;
import com.xunlei.thundersniffer.sniff.SniffingResourceGroup;
import com.xunlei.xiazaibao.BuildConfig;
import com.xunlei.xllib.b.d;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.android.spdy.SpdyProtocol;
import org.apache.commons.logging.impl.SimpleLog;

// compiled from: SnifferResultsSiteAdapter.java
public final class r extends a {
    private List<SniffingResourceGroup> f;
    private b g;

    // compiled from: SnifferResultsSiteAdapter.java
    class a {
        SuffixListView a;

        a() {
        }
    }

    // compiled from: SnifferResultsSiteAdapter.java
    public static class b {
        ArrayList<x> a;
        private Spanned b;
        private List<View> c;

        public b(ArrayList<x> arrayList) {
            this.b = null;
            this.c = null;
            this.a = arrayList;
        }
    }

    // compiled from: SnifferResultsSiteAdapter.java
    class c {
        ImageView a;
        SimpleCHNTextView b;
        TextView c;
        TextView d;

        c() {
        }
    }

    public final /* synthetic */ Object getItem(int i) {
        return a(i);
    }

    public r(Context context) {
        super(context);
        this.f = new ArrayList();
    }

    public final void a() {
        super.a();
        if (this.f != null) {
            this.f = null;
            notifyDataSetChanged();
        }
        this.g = null;
    }

    public final void a(b bVar) {
        this.g = bVar;
        notifyDataSetChanged();
    }

    public final void a(List<SniffingResourceGroup> list) {
        this.f = new ArrayList();
        for (SniffingResourceGroup sniffingResourceGroup : list) {
            this.f.add(sniffingResourceGroup);
        }
        this.f = b(list);
        notifyDataSetChanged();
    }

    private static List<SniffingResourceGroup> b(List<SniffingResourceGroup> list) {
        SniffingResourceGroup sniffingResourceGroup;
        List<SniffingResourceGroup> arrayList = new ArrayList();
        for (SniffingResourceGroup sniffingResourceGroup2 : list) {
            if (sniffingResourceGroup2.isHot) {
                arrayList.add(sniffingResourceGroup2);
            }
            sniffingResourceGroup2.isHot = false;
        }
        if (arrayList.size() > 0) {
            sniffingResourceGroup2 = (SniffingResourceGroup) arrayList.get(0);
            if (arrayList.size() > 1) {
                SniffingResourceGroup sniffingResourceGroup3 = sniffingResourceGroup2;
                for (int i = 1; i < arrayList.size(); i++) {
                    sniffingResourceGroup2 = (SniffingResourceGroup) arrayList.get(i);
                    if (sniffingResourceGroup2.matchScore > sniffingResourceGroup3.matchScore) {
                        sniffingResourceGroup3 = sniffingResourceGroup2;
                    }
                }
                List arrayList2 = new ArrayList();
                for (SniffingResourceGroup sniffingResourceGroup22 : arrayList) {
                    if (sniffingResourceGroup22.matchScore == sniffingResourceGroup3.matchScore) {
                        arrayList2.add(sniffingResourceGroup22);
                    }
                }
                if (arrayList2.size() > 1) {
                    sniffingResourceGroup22 = (SniffingResourceGroup) arrayList2.get(new Random().nextInt(arrayList2.size()));
                } else {
                    sniffingResourceGroup22 = sniffingResourceGroup3;
                }
            }
            sniffingResourceGroup22.isHot = true;
        }
        return list;
    }

    public final List<SniffingResourceGroup> b() {
        if (this.f == null) {
            this.f = new ArrayList();
        }
        return this.f;
    }

    public final void a(SniffingResourceGroup sniffingResourceGroup) {
        if (this.f == null) {
            this.f = new ArrayList();
        }
        if (!d.a(sniffingResourceGroup.resources)) {
            this.f.add(sniffingResourceGroup);
            this.f = b(this.f);
            notifyDataSetChanged();
        }
    }

    public final boolean isEnabled(int i) {
        return this.f != null && i < this.f.size();
    }

    public final int getCount() {
        int size = this.f == null ? 0 : this.f.size();
        return (size == 0 || this.g == null) ? size : size + 1;
    }

    public final int getItemViewType(int i) {
        return i < this.f.size() ? 0 : 1;
    }

    public final int getViewTypeCount() {
        return SimpleLog.LOG_LEVEL_DEBUG;
    }

    private SniffingResourceGroup a(int i) {
        return this.f == null ? null : (SniffingResourceGroup) this.f.get(i);
    }

    public final long getItemId(int i) {
        return (long) i;
    }

    protected final View a(int i, View view) {
        if (getItemViewType(i) == 0) {
            c cVar;
            if (view == null) {
                cVar = new c();
                view = LayoutInflater.from(this.a).inflate(R.layout.sniff_result_website_item, null);
                cVar.b = (SimpleCHNTextView) view.findViewById(R.id.content_name);
                cVar.c = (TextView) view.findViewById(R.id.content_intro);
                cVar.a = (ImageView) view.findViewById(R.id.hotImage);
                cVar.d = (TextView) view.findViewById(R.id.resource_size);
                view.setTag(cVar);
            } else {
                cVar = (c) view.getTag();
            }
            SniffingResourceGroup a = a(i);
            if (a != null && a.resources.size() > 0) {
                SniffingResource sniffingResource;
                String str;
                String str2;
                int i2;
                int i3;
                CharSequence string;
                if (a.title == null || a.title.trim().equals(BuildConfig.VERSION_NAME)) {
                    sniffingResource = (SniffingResource) a.resources.get(0);
                    if (TextUtils.isEmpty(sniffingResource.resourceName)) {
                        cVar.b.setText("\u672a\u77e5\u6807\u9898");
                    } else {
                        cVar.b.setText(sniffingResource.resourceName);
                    }
                } else {
                    cVar.b.setText(a.title);
                }
                View findViewById = view.findViewById(R.id.line);
                if (findViewById != null) {
                    if (this.f == null || this.f.indexOf(a) != this.f.size() - 1) {
                        findViewById.setVisibility(0);
                    } else {
                        findViewById.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
                    }
                }
                CharSequence charSequence = a.realUrl;
                if (TextUtils.isEmpty(charSequence)) {
                    sniffingResource = (SniffingResource) a.resources.get(0);
                    if (!TextUtils.isEmpty(sniffingResource.downloadUrl)) {
                        str = sniffingResource.downloadUrl;
                        str2 = BuildConfig.VERSION_NAME;
                        if (a.origin != null) {
                            i2 = -1;
                            i3 = a.origin.pageNo;
                            if (a.origin.origin != 1) {
                                str2 = this.a.getString(R.string.sniff_result_website_from_baidu_intro, new Object[]{Integer.valueOf(a.origin.pageNo), str});
                                if (i3 <= 0) {
                                    string = this.a.getString(R.string.sniff_result_website_from_baidu_curr_intro, new Object[]{str});
                                }
                            } else {
                                if (a.origin.origin == 2) {
                                    i2 = R.string.sniff_result_website_from_360_curr_intro;
                                } else if (a.origin.origin == 3) {
                                    i2 = R.string.sniff_result_website_from_sm_curr_intro;
                                } else if (a.origin.origin == 4) {
                                    i2 = R.string.sniff_result_website_from_sogou_curr_intro;
                                }
                                string = this.a.getString(i2, new Object[]{str});
                            }
                            cVar.c.setText(string);
                            cVar.d.setText(a.resources.size() <= 999 ? "999+" : String.valueOf(a.resources.size()));
                            if (a.isHot) {
                                cVar.a.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
                            } else {
                                cVar.a.setVisibility(0);
                            }
                        }
                        str = str2;
                        cVar.c.setText(string);
                        if (a.resources.size() <= 999) {
                        }
                        cVar.d.setText(a.resources.size() <= 999 ? "999+" : String.valueOf(a.resources.size()));
                        if (a.isHot) {
                            cVar.a.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
                        } else {
                            cVar.a.setVisibility(0);
                        }
                    }
                }
                string = charSequence;
                str2 = BuildConfig.VERSION_NAME;
                if (a.origin != null) {
                    i2 = -1;
                    i3 = a.origin.pageNo;
                    if (a.origin.origin != 1) {
                        if (a.origin.origin == 2) {
                            i2 = R.string.sniff_result_website_from_360_curr_intro;
                        } else if (a.origin.origin == 3) {
                            i2 = R.string.sniff_result_website_from_sm_curr_intro;
                        } else if (a.origin.origin == 4) {
                            i2 = R.string.sniff_result_website_from_sogou_curr_intro;
                        }
                        string = this.a.getString(i2, new Object[]{str});
                    } else {
                        str2 = this.a.getString(R.string.sniff_result_website_from_baidu_intro, new Object[]{Integer.valueOf(a.origin.pageNo), str});
                        if (i3 <= 0) {
                            string = this.a.getString(R.string.sniff_result_website_from_baidu_curr_intro, new Object[]{str});
                        }
                    }
                    cVar.c.setText(string);
                    if (a.resources.size() <= 999) {
                    }
                    cVar.d.setText(a.resources.size() <= 999 ? "999+" : String.valueOf(a.resources.size()));
                    if (a.isHot) {
                        cVar.a.setVisibility(0);
                    } else {
                        cVar.a.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
                    }
                }
                str = str2;
                cVar.c.setText(string);
                if (a.resources.size() <= 999) {
                }
                cVar.d.setText(a.resources.size() <= 999 ? "999+" : String.valueOf(a.resources.size()));
                if (a.isHot) {
                    cVar.a.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
                } else {
                    cVar.a.setVisibility(0);
                }
            }
        } else {
            a aVar;
            if (view == null) {
                a aVar2 = new a();
                view = LayoutInflater.from(this.a).inflate(R.layout.sniffer_siffix_container, null);
                aVar2.a = (SuffixListView) view.findViewById(R.id.suffix_list);
                view.setTag(aVar2);
                aVar = aVar2;
            } else {
                aVar = (a) view.getTag();
            }
            if (this.g != null) {
                aVar.a.setSuffixListItems(this.g.a);
            }
        }
        return view;
    }
}
