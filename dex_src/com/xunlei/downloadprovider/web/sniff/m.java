package com.xunlei.downloadprovider.web.sniff;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Build.VERSION;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.umeng.a;
import com.xunlei.downloadprovider.businessutil.XLFileTypeUtil;
import com.xunlei.downloadprovider.service.DownloadService;
import com.xunlei.downloadprovider.web.sniff.widget.SimpleCHNTextView;
import com.xunlei.downloadprovider.web.sniff.widget.d;
import com.xunlei.downloadprovider.web.sniff.widget.e;
import com.xunlei.tdlive.R;
import com.xunlei.thundersniffer.sniff.SniffingResource;
import com.xunlei.thundersniffer.sniff.SniffingResource.Category;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.util.ArrayList;
import java.util.List;

// compiled from: SnifferResultsResourceAdapter.java
public class m extends a {
    private static final String g;
    a f;
    private List<SniffingResource> h;
    private d i;
    private e j;

    static {
        g = m.class.getSimpleName();
    }

    public m(Context context) {
        super(context);
    }

    public final void a(List<SniffingResource> list) {
        if (this.h == null) {
            this.h = new ArrayList();
        }
        for (SniffingResource sniffingResource : list) {
            this.h.add(sniffingResource);
        }
        notifyDataSetChanged();
    }

    public final void a() {
        super.a();
        if (this.h != null) {
            this.h = null;
        }
        notifyDataSetChanged();
    }

    public int getCount() {
        return this.h != null ? this.h.size() : 0;
    }

    public Object getItem(int i) {
        return this.h != null ? this.h.get(i) : null;
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public boolean areAllItemsEnabled() {
        return false;
    }

    public boolean isEnabled(int i) {
        return false;
    }

    protected final View a(int i, View view) {
        if (this.h != null) {
            b bVar;
            if (view == null) {
                bVar = new b(this);
                view = LayoutInflater.from(this.a).inflate(2130968983, null);
                bVar.a = (SimpleCHNTextView) view.findViewById(R.id.content);
                bVar.b = (ImageView) view.findViewById(2131755203);
                bVar.c = view.findViewById(2131757006);
                bVar.d = (ImageView) view.findViewById(R.id.icon);
                bVar.e = (TextView) view.findViewById(2131757005);
                view.setTag(bVar);
            } else {
                bVar = (b) view.getTag();
            }
            Object item = getItem(i);
            if (item instanceof SniffingResource) {
                String toString;
                SniffingResource sniffingResource = (SniffingResource) item;
                if (TextUtils.isEmpty(sniffingResource.resourceName)) {
                    bVar.a.setText(sniffingResource.downloadUrl);
                } else {
                    bVar.a.setText(sniffingResource.resourceName);
                }
                bVar.e.setVisibility(0);
                String str = a.d;
                if (sniffingResource.fileSize != 0) {
                    str = XLFileTypeUtil.a(sniffingResource.fileSize);
                }
                Object obj = sniffingResource.originalDownloadUrL;
                if (TextUtils.isEmpty(obj)) {
                    obj = sniffingResource.downloadUrl;
                }
                new StringBuilder("resourceInfo: resourceName --> ").append(sniffingResource.resourceName).append(", downloadUrl --> ").append(sniffingResource.downloadUrl).append(", originalDownloadUrL --> ").append(toString).append(" --> ").append(sniffingResource.downloadUrl.equals(toString));
                if (!(TextUtils.isEmpty(toString) || sniffingResource.fileSize == 0)) {
                    toString = new StringBuilder(" | ").append(toString).toString();
                }
                bVar.e.setText(Html.fromHtml(this.a.getString(2131232599, new Object[]{str, toString})));
                bVar.b.setVisibility(0);
                bVar.b.setTag(sniffingResource);
                if (DownloadService.a() == null) {
                    bVar.b.setImageDrawable(this.a.getResources().getDrawable(2130839202));
                } else if (DownloadService.a().a(sniffingResource.downloadUrl) != -1) {
                    bVar.b.setImageDrawable(this.a.getResources().getDrawable(2130839201));
                } else {
                    bVar.b.setImageDrawable(this.a.getResources().getDrawable(2130839202));
                }
                bVar.b.setOnClickListener(new n(this));
                if (!(sniffingResource.category == Category.BT || sniffingResource.resourceType == 3)) {
                    if (sniffingResource.resourceName == null || !sniffingResource.resourceName.endsWith(".torrent")) {
                        if ((sniffingResource.downloadUrl == null || !sniffingResource.downloadUrl.contains("magnet:?")) && (sniffingResource.resourceName == null || !sniffingResource.resourceName.contains("magnet:?"))) {
                            int i2;
                            if (sniffingResource.category == Category.VIDEO) {
                                i2 = R.drawable.ic_dl_video;
                                if (!TextUtils.isEmpty(sniffingResource.format)) {
                                    i2 = XLFileTypeUtil.d(new StringBuilder("filename.").append(sniffingResource.format).toString());
                                    if (i2 == 2130838415) {
                                        i2 = R.drawable.ic_dl_video;
                                    }
                                }
                                bVar.d.setImageResource(i2);
                            } else if (sniffingResource.category == Category.AUDIO) {
                                bVar.d.setImageResource(R.drawable.ic_dl_music);
                            } else if (sniffingResource.category == Category.SOFTWARE) {
                                bVar.d.setImageResource(R.drawable.ic_dl_apk);
                            } else if (sniffingResource.category == Category.DOCUMENT) {
                                i2 = R.drawable.ic_dl_txt;
                                if (!TextUtils.isEmpty(sniffingResource.format)) {
                                    i2 = XLFileTypeUtil.d(new StringBuilder("filename.").append(sniffingResource.format).toString());
                                }
                                bVar.d.setImageResource(i2);
                            } else if (sniffingResource.category == Category.ARCHIVE) {
                                i2 = R.drawable.ic_dl_zip;
                                if (!TextUtils.isEmpty(sniffingResource.format)) {
                                    i2 = XLFileTypeUtil.d(new StringBuilder("filename.").append(sniffingResource.format).toString());
                                }
                                bVar.d.setImageResource(i2);
                            } else {
                                bVar.d.setImageResource(XLFileTypeUtil.d(sniffingResource.resourceName));
                            }
                            if (sniffingResource.canVodPlay()) {
                                bVar.c.setVisibility(0);
                                bVar.c.setTag(sniffingResource);
                                bVar.c.setOnClickListener(new o(this));
                            } else {
                                bVar.c.setVisibility(XZBDevice.Wait);
                            }
                        }
                    }
                }
                if (!"magnet".equals(sniffingResource.format)) {
                    if ((sniffingResource.downloadUrl == null || !sniffingResource.downloadUrl.contains("magnet:?")) && (sniffingResource.resourceName == null || !sniffingResource.resourceName.contains("magnet:?"))) {
                        bVar.d.setImageResource(R.drawable.ic_dl_torrent);
                        bVar.e.setVisibility(XZBDevice.Wait);
                        if (sniffingResource.canVodPlay()) {
                            bVar.c.setVisibility(XZBDevice.Wait);
                        } else {
                            bVar.c.setVisibility(0);
                            bVar.c.setTag(sniffingResource);
                            bVar.c.setOnClickListener(new o(this));
                        }
                    }
                }
                bVar.d.setImageResource(R.drawable.ic_dl_magnet);
                bVar.e.setVisibility(XZBDevice.Wait);
                if (sniffingResource.canVodPlay()) {
                    bVar.c.setVisibility(0);
                    bVar.c.setTag(sniffingResource);
                    bVar.c.setOnClickListener(new o(this));
                } else {
                    bVar.c.setVisibility(XZBDevice.Wait);
                }
            }
        }
        return view;
    }

    public final void a(SniffingResource sniffingResource, Context context, View view) {
        if (VERSION.SDK_INT >= 11) {
            ClipboardManager clipboardManager = (ClipboardManager) context.getSystemService("clipboard");
            CharSequence charSequence = sniffingResource.originalDownloadUrL;
            if (TextUtils.isEmpty(charSequence)) {
                charSequence = sniffingResource.downloadUrl;
            }
            clipboardManager.setPrimaryClip(ClipData.newPlainText("sniff_downlaod", charSequence));
        } else {
            ((android.text.ClipboardManager) context.getSystemService("clipboard")).setText(sniffingResource.originalDownloadUrL);
        }
        CharSequence charSequence2 = "\u590d\u5236\u94fe\u63a5\u6210\u529f";
        if (this.i != null) {
            if (this.i.isShowing()) {
                this.i.dismiss();
            }
            this.i = null;
        }
        this.i = new d(this.a);
        d dVar = this.i;
        if (dVar.a != null) {
            dVar.a.setText(charSequence2);
        }
        this.i.b = new p(this, sniffingResource, view);
        this.i.show();
    }

    public final void a(SniffingResource sniffingResource, View view) {
        int i = R.drawable.ic_dl_video;
        if (this.j != null) {
            if (this.j.isShowing()) {
                this.j.dismiss();
            }
            this.j = null;
        }
        this.j = new e(this.a);
        e eVar = this.j;
        CharSequence charSequence = a.d;
        if (sniffingResource.fileSize != 0) {
            charSequence = XLFileTypeUtil.a(sniffingResource.fileSize);
        }
        eVar.c.setText(Html.fromHtml(eVar.getContext().getString(2131232595, new Object[]{"\u5927\u5c0f:", charSequence})));
        if (!(sniffingResource.category == Category.BT || sniffingResource.resourceType == 3)) {
            if (sniffingResource.resourceName == null || !sniffingResource.resourceName.endsWith(".torrent")) {
                if ((sniffingResource.downloadUrl == null || !sniffingResource.downloadUrl.contains("magnet:?")) && (sniffingResource.resourceName == null || !sniffingResource.resourceName.contains("magnet:?"))) {
                    if (sniffingResource.category == Category.VIDEO) {
                        if (!TextUtils.isEmpty(sniffingResource.format)) {
                            int d = XLFileTypeUtil.d(new StringBuilder("filename.").append(sniffingResource.format).toString());
                            if (d != 2130838415) {
                                i = d;
                            }
                        }
                        eVar.a.setImageResource(i);
                    } else if (sniffingResource.category == Category.AUDIO) {
                        eVar.a.setImageResource(R.drawable.ic_dl_music);
                    } else if (sniffingResource.category == Category.SOFTWARE) {
                        eVar.a.setImageResource(R.drawable.ic_dl_apk);
                    } else if (sniffingResource.category == Category.DOCUMENT) {
                        i = R.drawable.ic_dl_txt;
                        if (!TextUtils.isEmpty(sniffingResource.format)) {
                            i = XLFileTypeUtil.d(new StringBuilder("filename.").append(sniffingResource.format).toString());
                        }
                        eVar.a.setImageResource(i);
                    } else if (sniffingResource.category == Category.ARCHIVE) {
                        i = R.drawable.ic_dl_zip;
                        if (!TextUtils.isEmpty(sniffingResource.format)) {
                            i = XLFileTypeUtil.d(new StringBuilder("filename.").append(sniffingResource.format).toString());
                        }
                        eVar.a.setImageResource(i);
                    } else {
                        eVar.a.setImageResource(XLFileTypeUtil.d(sniffingResource.resourceName));
                    }
                    if (TextUtils.isEmpty(charSequence)) {
                        eVar.c.setVisibility(XZBDevice.Wait);
                    }
                    if (TextUtils.isEmpty(sniffingResource.resourceName)) {
                        eVar.b.setText("\u672a\u77e5\u6807\u9898");
                    } else {
                        eVar.b.setText(sniffingResource.resourceName);
                    }
                    charSequence = sniffingResource.originalDownloadUrL;
                    if (TextUtils.isEmpty(charSequence)) {
                        charSequence = sniffingResource.downloadUrl;
                    }
                    if (!TextUtils.isEmpty(charSequence)) {
                        eVar.d.setText(charSequence);
                    }
                    this.j.f = new q(this, sniffingResource, view);
                    this.j.show();
                }
            }
        }
        if (!"magnet".equals(sniffingResource.format)) {
            if ((sniffingResource.downloadUrl == null || !sniffingResource.downloadUrl.contains("magnet:?")) && (sniffingResource.resourceName == null || !sniffingResource.resourceName.contains("magnet:?"))) {
                eVar.a.setImageResource(R.drawable.ic_dl_torrent);
                eVar.c.setVisibility(XZBDevice.Wait);
                if (TextUtils.isEmpty(charSequence)) {
                    eVar.c.setVisibility(XZBDevice.Wait);
                }
                if (TextUtils.isEmpty(sniffingResource.resourceName)) {
                    eVar.b.setText("\u672a\u77e5\u6807\u9898");
                } else {
                    eVar.b.setText(sniffingResource.resourceName);
                }
                charSequence = sniffingResource.originalDownloadUrL;
                if (TextUtils.isEmpty(charSequence)) {
                    charSequence = sniffingResource.downloadUrl;
                }
                if (TextUtils.isEmpty(charSequence)) {
                    eVar.d.setText(charSequence);
                }
                this.j.f = new q(this, sniffingResource, view);
                this.j.show();
            }
        }
        eVar.a.setImageResource(R.drawable.ic_dl_magnet);
        if (TextUtils.isEmpty(charSequence)) {
            eVar.c.setVisibility(XZBDevice.Wait);
        }
        if (TextUtils.isEmpty(sniffingResource.resourceName)) {
            eVar.b.setText(sniffingResource.resourceName);
        } else {
            eVar.b.setText("\u672a\u77e5\u6807\u9898");
        }
        charSequence = sniffingResource.originalDownloadUrL;
        if (TextUtils.isEmpty(charSequence)) {
            charSequence = sniffingResource.downloadUrl;
        }
        if (TextUtils.isEmpty(charSequence)) {
            eVar.d.setText(charSequence);
        }
        this.j.f = new q(this, sniffingResource, view);
        this.j.show();
    }
}
