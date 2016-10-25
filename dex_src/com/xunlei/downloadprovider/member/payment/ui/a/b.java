package com.xunlei.downloadprovider.member.payment.ui.a;

import android.content.Context;
import android.widget.AbsListView.LayoutParams;
import com.xunlei.downloadprovider.R;
import com.xunlei.downloadprovider.member.payment.bean.PayMealItem;
import com.xunlei.downloadprovider.member.payment.external.PayUtil;
import com.xunlei.downloadprovider.member.payment.ui.widget.PayMealItemView;
import org.android.spdy.SpdyProtocol;
import org.apache.commons.logging.impl.SimpleLog;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

// compiled from: PayMealItemAdapter.java
public final class b extends a<PayMealItem> {
    private int d;

    protected final /* synthetic */ void a(Object obj, j jVar) {
        PayMealItem payMealItem = (PayMealItem) obj;
        PayMealItemView payMealItemView = (PayMealItemView) jVar.a(R.id.meal_item_view);
        Context context = payMealItemView.getContext();
        payMealItemView.a.setText(context.getString(R.string.pay_meal_month, new Object[]{Integer.valueOf(payMealItem.month)}));
        int i = payMealItem.month;
        float f = payMealItem.monthUnitPrice;
        float f2 = payMealItem.totalMoney;
        if (payMealItem.isTotalPriceMode) {
            payMealItemView.b.setText(context.getString(R.string.pay_meal_total_price, new Object[]{PayUtil.a(f2)}));
        } else {
            payMealItemView.b.setText(context.getString(R.string.pay_meal_month_unit_price, new Object[]{PayMealItemView.a(i, f2)}));
        }
        switch (payMealItem.month) {
            case SimpleLog.LOG_LEVEL_TRACE:
                payMealItemView.c.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
                break;
            case MqttConnectOptions.MQTT_VERSION_3_1:
                payMealItemView.c.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
                break;
            case SimpleLog.LOG_LEVEL_FATAL:
                payMealItemView.c.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
                break;
            case com.xunlei.xllib.R.styleable.Toolbar_titleMargins:
                payMealItemView.c.setVisibility(0);
                float a = PayMealItemView.a(i, f, f2);
                payMealItemView.c.setText(context.getString(R.string.pay_meal_year_save, new Object[]{PayUtil.a(a)}));
                break;
            default:
                payMealItemView.c.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
                break;
        }
        jVar.a.setLayoutParams(new LayoutParams(this.d, -2));
    }

    public b(Context context) {
        super(context);
        this.d = (context.getResources().getDisplayMetrics().widthPixels - (context.getResources().getDimensionPixelSize(R.dimen.payment_13_dp) * 2)) - (context.getResources().getDimensionPixelSize(R.dimen.payment_11_dp) * 3);
        this.d /= 4;
    }

    protected final int a() {
        return R.layout.pay_meal_item_layout;
    }
}
