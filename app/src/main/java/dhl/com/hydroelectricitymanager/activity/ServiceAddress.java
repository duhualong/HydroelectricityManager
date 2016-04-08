package dhl.com.hydroelectricitymanager.activity;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dhl.com.hydroelectricitymanager.R;

/**
 * 作者：adu on 2016/4/8 16:44
 * 邮箱2383335125@qq.com
 */
public class ServiceAddress extends BaseActivity {
    @Bind(R.id.addressTitle)
    TextView addressTitle;
    @Bind(R.id.backLeftWhite)
    ImageView backLeft;
    @Bind(R.id.addAddress)
    TextView addAddress;
    @OnClick({R.id.backLeftWhite,R.id.addAddress})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.backLeftWhite:
                onBackPressed();
                break;
            case R.id.addAddress:
        startActivity(new Intent(context,AddAddressActivity.class));
                break;
        }
    }
    @Override
    protected int getContentView() {
        return R.layout.activity_service_address;
    }

    @Override
    protected void updateUI() {
//        Spannable sb=new SpannableString("逗逼");
//        sb.setSpan(new ForegroundColorSpan(ContextCompat.getColor(context,R.color.black)),0,sb.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//        addressTitle.append(sb);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
