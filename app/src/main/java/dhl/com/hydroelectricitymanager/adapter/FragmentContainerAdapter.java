package dhl.com.hydroelectricitymanager.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.SparseArray;

import dhl.com.hydroelectricitymanager.Constants;
import dhl.com.hydroelectricitymanager.fragment.HomeFragment;
import dhl.com.hydroelectricitymanager.fragment.MyFragment;
import dhl.com.hydroelectricitymanager.fragment.ReservationFragment;


/**
 * Fragment Container Adapter
 * Created by zac on 10/20/15.
 */
public class FragmentContainerAdapter extends FragmentPagerAdapter {

    private SparseArray<Fragment> fragments = new SparseArray<>();

    public FragmentContainerAdapter(FragmentManager fm) {
        super(fm);
        fragments.append(0, new HomeFragment());
        fragments.append(1, new ReservationFragment());
        fragments.append(2, new MyFragment());
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position, new HomeFragment());
    }

    @Override
    public int getCount() {
        return Constants.PAGER_NUM;
    }
}
