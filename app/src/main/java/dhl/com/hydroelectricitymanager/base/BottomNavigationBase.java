package dhl.com.hydroelectricitymanager.base;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import dhl.com.hydroelectricitymanager.R;

/**
 * 作者：adu on 2016/3/28 17:07
 * 邮箱2383335125@qq.com
 */
public class BottomNavigationBase extends Activity{
    @Bind(R.id.tvHome)
    TextView home;
    @Bind(R.id.tvReservation)
    TextView reservation;
    @Bind(R.id.tvMy)
    TextView my;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity_bottom);
        ButterKnife.bind(this);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

}
