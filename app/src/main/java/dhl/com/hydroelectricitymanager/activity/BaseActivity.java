package dhl.com.hydroelectricitymanager.activity;

import android.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import butterknife.ButterKnife;

/**
 * 作者：adu on 2016/4/1 14:18
 * 邮箱2383335125@qq.com
 */
public abstract class BaseActivity extends AppCompatActivity {
    protected abstract int getContentView();
    protected abstract void updateUI();
    protected InputMethodManager inputMgr;
    protected FragmentManager fragmentMgr;
    protected android.support.v4.app.FragmentManager supportFragmentMgr;
    protected Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        if (getContentView() != 0) {
            setContentView(getContentView());
        }
        ButterKnife.bind(this);
        setBaseFeatures();
        updateUI();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
    protected void setBaseFeatures() {
        inputMgr = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        fragmentMgr = getFragmentManager();
        supportFragmentMgr = getSupportFragmentManager();
        context = this;
    }

}
