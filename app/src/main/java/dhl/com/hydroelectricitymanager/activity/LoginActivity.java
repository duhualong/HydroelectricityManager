package dhl.com.hydroelectricitymanager.activity;


import android.app.Fragment;

import dhl.com.hydroelectricitymanager.R;
import dhl.com.hydroelectricitymanager.fragment.LoginFragment;

/**
 * 作者：adu on 2016/4/1 09:52
 * 邮箱2383335125@qq.com
 */
public class LoginActivity extends BaseActivity {

    @Override
    protected int getContentView() {
        return R.layout.login_activity_root;
    }

    @Override
    protected void updateUI() {
      Fragment fragment=fragmentMgr.findFragmentById(R.id.loginContainer);
        if (fragment==null){
        fragment=new LoginFragment();
            fragmentMgr.beginTransaction().add(R.id.loginContainer,fragment).commit();
        }


    }






}
