package dhl.com.hydroelectricitymanager.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dhl.com.hydroelectricitymanager.R;
import dhl.com.hydroelectricitymanager.fragment.ProjectFragment;
import dhl.com.hydroelectricitymanager.fragment.StaffFragment;

/**
 * 作者：adu on 2016/3/30 16:17
 * 邮箱2383335125@qq.com
 */
public class ServiceDemandActivityText extends AppCompatActivity implements ViewPager.OnPageChangeListener {

    @Bind(R.id.backLeftWhite)
    ImageView backLeft;
    @Bind(R.id.textView)
    TextView textView;
    @Bind(R.id.filter)
    TextView filter;
    @Bind(R.id.tabDemand)
    TabLayout tabDemand;
    @Bind(R.id.serviceContainer)
    ViewPager serviceContainer;
    private FragmentManager fragmentManager;

    @OnClick(R.id.backLeftWhite)
    public void onBackLeft(){
        onBackPressed();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_demand);
        ButterKnife.bind(this);

        if (serviceContainer != null) {
            setupViewPager(serviceContainer);
            tabDemand.setupWithViewPager(serviceContainer);
            serviceContainer.addOnPageChangeListener(this);
        }
    }
    private void setupViewPager(ViewPager viewPager) {
        Adapter adapter=new Adapter(getSupportFragmentManager());
       adapter.addFragment(new ProjectFragment(), getResources().getString(R.string.project));
      adapter.addFragment(new StaffFragment(), getResources().getString(R.string.plumber));
        viewPager.setAdapter(adapter);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        switch (position){
            case 0:
                textView.setText(getResources().getString(R.string.service_demand));
                filter.setVisibility(View.INVISIBLE);
                break;
            case 1:
                textView.setText(getResources().getString(R.string.service_list));
                filter.setVisibility(View.VISIBLE);

                break;

        }

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    static class Adapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragments = new ArrayList<>();
        private final List<String> mFragmentTitles = new ArrayList<>();

        public Adapter(FragmentManager fm) {
            super(fm);
        }

        public void addFragment(Fragment fragment, String title) {
            mFragments.add(fragment);
            mFragmentTitles.add(title);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitles.get(position);
        }
    }
}
