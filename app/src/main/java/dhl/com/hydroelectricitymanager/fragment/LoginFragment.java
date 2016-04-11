package dhl.com.hydroelectricitymanager.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dhl.com.hydroelectricitymanager.R;
import dhl.com.hydroelectricitymanager.activity.ContainerActivity;

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
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    @OnClick({R.id.backLeftWhite,R.id.register,R.id.forgetPassword,R.id.btLogin})
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
                        .replace(R.id.loginContainer, new ForgetPwdFragment())
                        .commit();
                break;
            case R.id.btLogin:
                String etPhone=inputPhoneNumber.getText().toString().trim();
                String etPwd=inputPwd.getText().toString().trim();
                String phone=sharedPreferences.getString("phone","");
                String pwd=sharedPreferences.getString("pwd","");
                if (etPhone.equals(phone)&&etPwd.equals(pwd)){
                    Toast.makeText(context,"登录成功，即将跳转到首页",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getActivity(),ContainerActivity.class));
                }else{
                    inputPwd.setText("");
                    Toast.makeText(context,"账号或密码有误，请确认后重新登录",Toast.LENGTH_SHORT).show();
                }


                break;
        }
    }
    @Override
    protected int getContentView() {
        return R.layout.activity_login;
    }

    @Override
    protected void updateUI() {
        sharedPreferences = getActivity().getSharedPreferences("data", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.apply();
        phoneAddTextChangedListeners(inputPhoneNumber, btLogin);
        onCheckedChangeListeners(inputPwd,switchPwd);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
