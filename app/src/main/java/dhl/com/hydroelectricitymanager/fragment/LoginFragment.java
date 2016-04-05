package dhl.com.hydroelectricitymanager.fragment;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.OnClick;
import dhl.com.hydroelectricitymanager.R;

/**
 * 作者：adu on 2016/4/1 14:05
 * 邮箱2383335125@qq.com
 */
public class LoginFragment extends BaseFragment {
    @Bind(R.id.backLeftWhite)
    ImageView backLeft;
    @Bind(R.id.register)
    TextView register;
    @Bind(R.id.inputPhoneNumber)
    EditText inputPhoneNumber;
    @Bind(R.id.inputVar)
    EditText inputPwd;
    @Bind(R.id.checkSwitch)
    Switch switchPwd;
    @Bind(R.id.btLogin)
    Button btLogin;
    @Bind(R.id.forgetPassword)
    TextView tvforgetPwd;
    @Bind(R.id.thirdPartyLogin)
    ImageView imgThirdLogin;
    @Bind(R.id.thirdLoginNext)
    LinearLayout thirdLoginNext;
    @Bind(R.id.ivWeChat)
    ImageView weChat;
    @Bind(R.id.ivQQ)
    ImageView ivQQ;
    @OnClick({R.id.backLeftWhite,R.id.register,R.id.forgetPassword})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.backLeftWhite:
                onBackPressed();
                break;
            case R.id.register:
                fragmentMgr.beginTransaction()
                        .addToBackStack("")
                        .replace(R.id.loginContainer, new RegisterPhoneFragment())
                        .commit();
                break;
            case R.id.forgetPassword:
                fragmentMgr.beginTransaction()
                        .addToBackStack("")
                        .replace(R.id.loginContainer, new RegisterPhoneFragment())
                        .commit();
                break;
        }
    }
    @Override
    protected int getContentView() {
        return R.layout.activity_login;
    }

    @Override
    protected void updateUI() {


    }


}
