package dhl.com.hydroelectricitymanager.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import dhl.com.hydroelectricitymanager.R;

/**
 * 作者：adu on 2016/3/28 09:49
 * 邮箱2383335125@qq.com
 */
public class ReservationFragment extends Fragment {
    private View mainView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mainView = inflater.inflate(R.layout.immediate_reservation_fragment, container, false);
        return mainView;
    }
}