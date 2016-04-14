package dhl.com.hydroelectricitymanager.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import dhl.com.hydroelectricitymanager.R;

/**
 * 作者：adu on 2016/4/14 14:00
 * 邮箱2383335125@qq.com
 */
public class OrderConfirmHydroelectricity extends Activity implements View.OnClickListener{

    private CheckBox aliPay;
    private CheckBox weChat;
    private TextView orderPhone;
    private TextView selectedServiceTime;
    private TextView selectedServiceAddress;
    private RelativeLayout rlCoupon;
    private RelativeLayout aliPayRl;
    private RelativeLayout weChatRl;
    private ImageView backLeft;
    private Button orderPayment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_confirm);

        initView();

    }

    private void initView() {
        orderPayment= (Button) findViewById(R.id.orderPayment);
        backLeft= (ImageView) findViewById(R.id.backLeftWhite);
        aliPay = (CheckBox) findViewById(R.id.aliPayment);
        weChat = (CheckBox) findViewById(R.id.weChatPayment);
        orderPhone = (TextView) findViewById(R.id.orderPhone);
        selectedServiceTime= (TextView) findViewById(R.id.selectedServiceTime);
        selectedServiceAddress= (TextView) findViewById(R.id.selectedServiceAddress);
        rlCoupon= (RelativeLayout) findViewById(R.id.rlCoupon);
        aliPayRl= (RelativeLayout) findViewById(R.id.aliPayRl);
        weChatRl= (RelativeLayout) findViewById(R.id.weChatRl);
        initClick();
    }

    private void initClick() {
        backLeft.setOnClickListener(this);
        rlCoupon.setOnClickListener(this);
        aliPayRl.setOnClickListener(this);
        aliPay.setOnClickListener(this);
        weChat.setOnClickListener(this);
        weChatRl.setOnClickListener(this);
        orderPayment.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.backLeftWhite:
                onBackPressed();
                break;
            case R.id.rlCoupon:
                startActivity(new Intent(OrderConfirmHydroelectricity.this,MyCoupon.class));
                break;
            case R.id.aliPayRl:
                weChat.setChecked(false);
                aliPay.setChecked(true);
                break;
            case R.id.aliPayment:
                weChat.setChecked(false);
                if (!aliPay.isChecked()){
                    weChat.setChecked(false);
                    aliPay.setChecked(true);
                }
                break;
            case R.id.weChatRl:
                aliPay.setChecked(false);
                weChat.setChecked(true);
                break;
            case R.id.weChatPayment:
                aliPay.setChecked(false);
                if (!weChat.isChecked()){
                    aliPay.setChecked(false);
                    weChat.setChecked(true);
                }
                break;
            case R.id.orderPayment:
                if (aliPay.isChecked()){
                    Toast.makeText(getApplicationContext(), "正在调起支付宝支付", Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(getApplicationContext(),"正在调起微信支付",Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }





}
