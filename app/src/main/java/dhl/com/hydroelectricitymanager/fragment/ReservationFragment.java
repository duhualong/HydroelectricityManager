package dhl.com.hydroelectricitymanager.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import dhl.com.hydroelectricitymanager.R;

/**
 * 作者：adu on 2016/3/28 09:49
 * 邮箱2383335125@qq.com
 */
public class ReservationFragment extends Fragment {
    private View mainView;
    private FragmentManager fragmentManager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mainView = inflater.inflate(R.layout.fragment_reservation, container, false);
        fragmentManager = getFragmentManager();
        ViewPager reservationContainer= (ViewPager) mainView.findViewById(R.id.reservationContainer);
        TabLayout tabReservation= (TabLayout) mainView.findViewById(R.id.tabReservation);
        if (reservationContainer != null) {
            setupViewPager(reservationContainer);
            tabReservation.setupWithViewPager(reservationContainer);
        }

        return mainView;
    }
    private void setupViewPager(ViewPager viewPager) {
        Adapter adapter = new Adapter(fragmentManager);
        adapter.addFragment(new HydroelectricityFragment(),getString(R.string.hydroelectricity_service));
        adapter.addFragment(new FeatureFragment(),getString(R.string.feature_service));
        adapter.addFragment(new AnniversaryFragment(),getString(R.string.anniversary_service));
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