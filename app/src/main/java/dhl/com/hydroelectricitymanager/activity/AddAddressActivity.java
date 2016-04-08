package dhl.com.hydroelectricitymanager.activity;

import android.view.View;
import android.widget.ImageView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dhl.com.hydroelectricitymanager.R;

/**
 * 作者：adu on 2016/4/8 16:51
 * 邮箱2383335125@qq.com
 */
public class AddAddressActivity extends BaseActivity {
    @Bind(R.id.backLeftWhite)
    ImageView backLeft;
    @Bind(R.id.finishedAddressEdit)
    ImageView finishedAddress;
    @OnClick({R.id.backLeftWhite,R.id.finishedAddressEdit})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.backLeftWhite:
                onBackPressed();
                break;

        }
    }
    @Override
    protected int getContentView() {
        return R.layout.edit_service_address;
    }

    @Override
    protected void updateUI() {


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
