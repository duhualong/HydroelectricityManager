package dhl.com.hydroelectricitymanager.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import dhl.com.hydroelectricitymanager.R;
import dhl.com.hydroelectricitymanager.fragment.ProjectFragment;
import dhl.com.hydroelectricitymanager.fragment.StaffFragment;

/**
 * 作者：adu on 2016/3/31 16:55
 * 邮箱2383335125@qq.com
 */
public class FilterContainer extends AppCompatActivity {
    @Bind(R.id.tabFilter)
    TabLayout tabFilter;
    @Bind(R.id.vPagerFilter)
    ViewPager vPagerFilter;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);
        ButterKnife.bind(this);
        if (vPagerFilter != null) {
            setupViewPager(vPagerFilter);
            tabFilter.setupWithViewPager(vPagerFilter);
          //  vPagerFilter.addOnPageChangeListener(this);
        }

    }
    private void setupViewPager(ViewPager viewPager) {
        Adapter adapter = new Adapter(getSupportFragmentManager());
        adapter.addFragment(new ProjectFragment(), getResources().getString(R.string.project));
        adapter.addFragment(new StaffFragment(), getResources().getString(R.string.plumber));
        viewPager.setAdapter(adapter);
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
