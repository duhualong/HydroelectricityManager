package dhl.com.hydroelectricitymanager.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import dhl.com.hydroelectricitymanager.R;

/**
 * 作者：adu on 2016/3/30 16:44
 * 邮箱2383335125@qq.com
 */
public class ProjectFragment extends Fragment {
    private View mainView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mainView = inflater.inflate(R.layout.activity_hydroelectricity_service_bottom, container, false);
        return mainView;

    }
}
