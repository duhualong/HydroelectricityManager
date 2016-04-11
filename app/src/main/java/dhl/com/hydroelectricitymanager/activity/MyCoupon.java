package dhl.com.hydroelectricitymanager.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.OnClick;
import dhl.com.hydroelectricitymanager.R;

/**
 * 作者：adu on 2016/4/11 09:31
 * 邮箱2383335125@qq.com
 */
public class MyCoupon extends BaseActivity {
    @Bind(R.id.backLeftWhite)
    ImageView backLeft;
    @Bind(R.id.addition)
    TextView addition;
    @Bind(R.id.tipCoupon)
    LinearLayout tipCoupon;
    @Bind(R.id.noCoupon)
    RelativeLayout noCoupon;
    @Bind(R.id.listDiscountCoupon)
    ListView listDiscountCoupon;
    @OnClick({R.id.backLeftWhite,R.id.addition})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.backLeftWhite:
                onBackPressed();
                break;
            case R.id.addition:
                AlertDialog.Builder builder=new AlertDialog.Builder(context);
                builder.setView(View.inflate(context,R.layout.dialog_coupon,null))
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        }).setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).setCancelable(true);
                builder.create().show();
                break;
        }
    }
    @Override
    protected int getContentView() {
        return R.layout.activity_discount_coupon;
    }

    @Override
    protected void updateUI() {

    }
}
