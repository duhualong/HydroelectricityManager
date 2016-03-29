package dhl.com.hydroelectricitymanager.activity;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dhl.com.hydroelectricitymanager.Constants;
import dhl.com.hydroelectricitymanager.R;
import dhl.com.hydroelectricitymanager.adapter.FragmentContainerAdapter;

public class ContainerActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener{
    private int[] navSelDrawableId = {R.drawable.ic_home_select, R.drawable.ic_reservation_select,
            R.drawable.ic_my_select}; //底部导航栏选中状态背景
    private int[] navSelColorId = {R.color.gray, R.color.orange}; //底部导航栏选中状态背景
    @Bind(R.id.fragmentContainer)
    ViewPager viewPager;
    private Fragment fragment;
    private Context context;
    @Bind(R.id.tvHome)
    TextView home;
    @Bind(R.id.tvReservation)
    TextView reservation;
    @Bind(R.id.tvMy)
    TextView my;
    @OnClick({R.id.tvHome,R.id.tvReservation,R.id.tvMy})
    public void bottomChange(View view){
        switch (view.getId()){
            case R.id.tvHome:
                setClearBottomColor(Constants.HOMEFRAGMENT);
                viewPager.setCurrentItem(Constants.HOMEFRAGMENT);
                break;
            case R.id.tvReservation:
              setClearBottomColor(Constants.RESERVATIONFRAGMENT);
                viewPager.setCurrentItem(Constants.RESERVATIONFRAGMENT);
                break;
            case R.id.tvMy:
                setClearBottomColor(Constants.MYFRAGMENT);
                viewPager.setCurrentItem(Constants.MYFRAGMENT);
                break;
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.container_main);
        context=ContainerActivity.this;
        ButterKnife.bind(this);
     FragmentManager fragmentManager= getSupportFragmentManager();
        viewPager.setAdapter(new FragmentContainerAdapter(fragmentManager));
        viewPager.addOnPageChangeListener(this);
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        switch (position){
            case 0:
                setClearBottomColor(Constants.HOMEFRAGMENT);
            break;
            case 1:
                setClearBottomColor(Constants.RESERVATIONFRAGMENT);
                break;
            case 2:
                setClearBottomColor(Constants.MYFRAGMENT);
                break;

        }
    }
    @Override
    public void onPageScrollStateChanged(int state) {

    }

    public void setClearBottomColor(int position){
        home.setTextColor(ContextCompat.getColor(context, navSelColorId[0]));
        home.setCompoundDrawablesWithIntrinsicBounds(null, ContextCompat.getDrawable(context, R.drawable.ic_home), null, null);
        reservation.setTextColor(ContextCompat.getColor(context, navSelColorId[0]));
        reservation.setCompoundDrawablesWithIntrinsicBounds(null, ContextCompat.getDrawable(context, R.drawable.ic_reservation), null, null);
        my.setTextColor(ContextCompat.getColor(context, navSelColorId[0]));
        my.setCompoundDrawablesWithIntrinsicBounds(null, ContextCompat.getDrawable(context, R.drawable.ic_my), null, null);
        if (position==0){
            home.setTextColor(ContextCompat.getColor(context, navSelColorId[1]));
            home.setCompoundDrawablesWithIntrinsicBounds(null, ContextCompat.getDrawable(context, navSelDrawableId[0]), null, null);
        }else if (position==1){
            reservation.setTextColor(ContextCompat.getColor(context, navSelColorId[1]));
            reservation.setCompoundDrawablesWithIntrinsicBounds(null, ContextCompat.getDrawable(context,navSelDrawableId[1]), null, null);
        }else if (position==2){
            my.setTextColor(ContextCompat.getColor(context, navSelColorId[0]));
            my.setCompoundDrawablesWithIntrinsicBounds(null, ContextCompat.getDrawable(context,navSelDrawableId[2]), null, null);
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
