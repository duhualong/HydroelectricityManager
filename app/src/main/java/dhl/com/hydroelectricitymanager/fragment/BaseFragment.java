package dhl.com.hydroelectricitymanager.fragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * 作者：adu on 2016/4/1 14:39
 * 邮箱2383335125@qq.com
 */
public abstract class BaseFragment extends Fragment{
    protected View mainView;
    protected FragmentManager fragmentMgr;
    protected Context context;
    // 获取子类布局
    protected abstract int getContentView();
    // 更新页面ui
    protected abstract void updateUI();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (getContentView() != 0) {
            mainView = inflater.inflate(getContentView(), container, false);
            ButterKnife.bind(this, mainView);
            context = getActivity();
            fragmentMgr = getFragmentManager();
            updateUI();
            return mainView;
        }
        return super.onCreateView(inflater, container, savedInstanceState);
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
    /**
     * Back press action in fragment
     */
    protected void onBackPressed() {
        if (fragmentMgr.getBackStackEntryCount() > 0) {
            fragmentMgr.popBackStack();
        } else {
            getActivity().onBackPressed();
        }
    }
}
