package com.xunlei.downloadprovider.web.sniff;

import com.xunlei.downloadprovider.R;
import com.xunlei.downloadprovider.util.sniff.SniffConfigure;
import com.xunlei.downloadprovider.util.sniff.f;
import com.xunlei.thundersniffer.sniff.SniffingPageResource;
import com.xunlei.thundersniffer.sniff.SniffingResourceGroup;
import com.xunlei.thundersniffer.sniff.sniffer.ThunderSnifferUtil;
import com.xunlei.xiazaibao.BuildConfig;
import java.util.List;
import org.android.spdy.SpdyProtocol;
import org.apache.commons.logging.impl.SimpleLog;

private class SnifferResultsFragment$g extends SnifferResultsFragment$c {
    String b;
    final /* synthetic */ SnifferResultsFragment c;

    private SnifferResultsFragment$g(SnifferResultsFragment snifferResultsFragment) {
        this.c = snifferResultsFragment;
        super((byte) 0);
    }

    public final void a() {
        new StringBuilder(" performUIChange ").append(getClass().getSimpleName());
        if (SnifferResultsFragment.t(this.c).b(this.b) == null) {
            SnifferResultsFragment.a(this.c, SimpleLog.LOG_LEVEL_FATAL);
            SnifferResultsFragment.c(this.c);
            return;
        }
        this.c.getView().setVisibility(0);
        SnifferResultsFragment.F(this.c).setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
        SnifferResultsFragment.s(this.c).setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
        SnifferResultsFragment.C(this.c).setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
        SnifferResultsFragment.D(this.c).setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
        SnifferResultsFragment.E(this.c).setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
        Object b = SnifferResultsFragment.t(this.c).b(this.b);
        if (b != null) {
            if (SnifferResultsFragment.h(this.c).getVisibilityState() != 10) {
                SnifferResultsFragment.A(this.c).setVisibility(0);
                if (SnifferResultsFragment.b(this.c) != null) {
                    SnifferResultsFragment.b(this.c).a(false);
                }
            }
            this.c.getView().setVisibility(0);
            SniffingResourceGroup sniffingResourceGroup;
            if (b instanceof SniffingPageResource) {
                SniffingPageResource sniffingPageResource = (SniffingPageResource) b;
                if (sniffingPageResource.isGrouped) {
                    String d = f.d(ThunderSnifferUtil.getSearchWordFromUrl(sniffingPageResource.mPageUrl));
                    String e = f.e(ThunderSnifferUtil.getSearchWordFromUrl(sniffingPageResource.mPageUrl));
                    SnifferResultsFragment.u(this.c).setVisibility(0);
                    SnifferResultsFragment.d(this.c).setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
                    if (sniffingPageResource.groups == null || sniffingPageResource.groups.size() == 0) {
                        SnifferResultsFragment.x(this.c).setText(a(0));
                        SnifferResultsFragment.J(this.c).setText(SnifferResultsFragment.x(this.c).getText().toString());
                        SnifferResultsFragment.E(this.c).setVisibility(0);
                        SnifferResultsFragment.u(this.c).setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
                        SnifferResultsFragment.d(this.c).setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
                        if (d == null || d.trim().equals(BuildConfig.VERSION_NAME)) {
                            SnifferResultsFragment.N(this.c).setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
                            SnifferResultsFragment.O(this.c).setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
                        } else {
                            List list = null;
                            if (SniffConfigure.a().b() != null) {
                                list = SniffConfigure.a().b().a;
                            }
                            SnifferResultsFragment.N(this.c).setSuffixListItems(a(d, list, e));
                            SnifferResultsFragment.N(this.c).setVisibility(0);
                            SnifferResultsFragment.O(this.c).setVisibility(0);
                        }
                        if (SnifferResultsFragment.h(this.c).getVisibilityState() == 10 && SnifferResultsFragment.x(this.c).getTextNumber() == 0) {
                            SnifferResultsFragment.y(this.c);
                            if (SnifferResultsFragment.b(this.c) != null) {
                                SnifferResultsFragment.b(this.c).b(true);
                            }
                        }
                    } else {
                        if (SnifferResultsFragment.I(this.c) == null) {
                            SnifferResultsFragment.a(this.c, new r(this.c.getActivity()));
                        } else if (SnifferResultsFragment.I(this.c).b().equals(sniffingPageResource.groups)) {
                            SnifferResultsFragment.x(this.c).setText(a(sniffingPageResource.groups.size(), true, d, e));
                            SnifferResultsFragment.J(this.c).setText(SnifferResultsFragment.x(this.c).getText().toString());
                            if (SnifferResultsFragment.h(this.c).getVisibilityState() == 10 && SnifferResultsFragment.x(this.c).getTextNumber() != 0) {
                                SnifferResultsFragment.w(this.c);
                                if (SnifferResultsFragment.b(this.c) != null) {
                                    SnifferResultsFragment.b(this.c).b(false);
                                }
                            }
                            SnifferResultsFragment.h(this.c).setContentListId(R.id.sniffer_page_results_list_view);
                            return;
                        }
                        if (SnifferResultsFragment.u(this.c).getAdapter() == null) {
                            SnifferResultsFragment.u(this.c).setAdapter(SnifferResultsFragment.I(this.c));
                        }
                        SnifferResultsFragment.I(this.c).a();
                        SnifferResultsFragment.I(this.c).c = false;
                        SnifferResultsFragment.I(this.c).a(sniffingPageResource.groups);
                        SnifferResultsFragment.h(this.c).setContentListId(R.id.sniffer_page_results_list_view);
                        SnifferResultsFragment.x(this.c).setText(a(sniffingPageResource.groups.size(), true, d, e));
                        SnifferResultsFragment.J(this.c).setText(SnifferResultsFragment.x(this.c).getText().toString());
                        SnifferResultsFragment.I(this.c).a(a(d, e));
                    }
                } else {
                    boolean z;
                    SnifferResultsFragment.u(this.c).setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
                    SnifferResultsFragment.d(this.c).setVisibility(0);
                    if (sniffingPageResource.groups == null || sniffingPageResource.groups.size() == 0) {
                        z = true;
                    } else {
                        sniffingResourceGroup = (SniffingResourceGroup) sniffingPageResource.groups.get(0);
                        if (sniffingResourceGroup == null || sniffingResourceGroup.resources == null || sniffingResourceGroup.resources.size() == 0) {
                            z = true;
                        } else {
                            if (SnifferResultsFragment.a(this.c) == null) {
                                SnifferResultsFragment.a(this.c, new m(this.c.getActivity()));
                            }
                            if (SnifferResultsFragment.d(this.c).getAdapter() == null) {
                                SnifferResultsFragment.d(this.c).setAdapter(SnifferResultsFragment.a(this.c));
                            }
                            SnifferResultsFragment.a(this.c).a();
                            SnifferResultsFragment.a(this.c).c = false;
                            SnifferResultsFragment.a(this.c).a(sniffingResourceGroup.resources);
                            if (sniffingResourceGroup.getResourceOperationMonitor() != null) {
                                sniffingResourceGroup.getResourceOperationMonitor().setListener(SnifferResultsFragment.H(this.c));
                            }
                            SnifferResultsFragment.h(this.c).setContentListId(R.id.sniffer_res_results_list_view);
                            SnifferResultsFragment.x(this.c).setText(a(sniffingResourceGroup.resources.size()));
                            SnifferResultsFragment.J(this.c).setText(SnifferResultsFragment.x(this.c).getText().toString());
                            z = false;
                        }
                    }
                    if (z) {
                        SnifferResultsFragment.s(this.c).setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
                        SnifferResultsFragment.x(this.c).setText(a(0));
                        SnifferResultsFragment.J(this.c).setText(SnifferResultsFragment.x(this.c).getText().toString());
                        SnifferResultsFragment.E(this.c).setVisibility(0);
                        SnifferResultsFragment.d(this.c).setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
                        SnifferResultsFragment.u(this.c).setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
                        SnifferResultsFragment.N(this.c).setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
                        SnifferResultsFragment.O(this.c).setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
                        if (SnifferResultsFragment.h(this.c).getVisibilityState() == 10 && SnifferResultsFragment.x(this.c).getTextNumber() == 0) {
                            SnifferResultsFragment.y(this.c);
                            if (SnifferResultsFragment.b(this.c) != null) {
                                SnifferResultsFragment.b(this.c).b(true);
                            }
                        }
                    }
                }
            } else if (b instanceof SniffingResourceGroup) {
                sniffingResourceGroup = (SniffingResourceGroup) b;
                SnifferResultsFragment.u(this.c).setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
                SnifferResultsFragment.d(this.c).setVisibility(0);
                if (sniffingResourceGroup == null || sniffingResourceGroup.resources == null || sniffingResourceGroup.resources.size() == 0) {
                    SnifferResultsFragment.x(this.c).setText(a(0));
                    SnifferResultsFragment.J(this.c).setText(SnifferResultsFragment.x(this.c).getText().toString());
                    SnifferResultsFragment.E(this.c).setVisibility(0);
                    SnifferResultsFragment.u(this.c).setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
                    SnifferResultsFragment.d(this.c).setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
                    if (SnifferResultsFragment.h(this.c).getVisibilityState() == 10 && SnifferResultsFragment.x(this.c).getTextNumber() == 0) {
                        SnifferResultsFragment.y(this.c);
                        if (SnifferResultsFragment.b(this.c) != null) {
                            SnifferResultsFragment.b(this.c).b(true);
                        }
                    }
                } else {
                    if (SnifferResultsFragment.a(this.c) == null) {
                        SnifferResultsFragment.a(this.c, new m(this.c.getActivity()));
                    }
                    if (SnifferResultsFragment.d(this.c).getAdapter() == null) {
                        SnifferResultsFragment.d(this.c).setAdapter(SnifferResultsFragment.a(this.c));
                    }
                    SnifferResultsFragment.a(this.c).a();
                    SnifferResultsFragment.a(this.c).c = false;
                    SnifferResultsFragment.a(this.c).a(sniffingResourceGroup.resources);
                    if (sniffingResourceGroup.getResourceOperationMonitor() != null) {
                        sniffingResourceGroup.getResourceOperationMonitor().setListener(SnifferResultsFragment.H(this.c));
                    }
                    SnifferResultsFragment.h(this.c).setContentListId(R.id.sniffer_res_results_list_view);
                    SnifferResultsFragment.x(this.c).setText(a(sniffingResourceGroup.resources.size()));
                    SnifferResultsFragment.J(this.c).setText(SnifferResultsFragment.x(this.c).getText().toString());
                }
            }
            if (SnifferResultsFragment.h(this.c).getVisibilityState() == 10 && SnifferResultsFragment.x(this.c).getTextNumber() != 0) {
                SnifferResultsFragment.w(this.c);
                return;
            }
            return;
        }
        SnifferResultsFragment.a(this.c, SimpleLog.LOG_LEVEL_FATAL);
        SnifferResultsFragment.c(this.c);
    }
}
