package dhl.com.hydroelectricitymanager.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dhl.com.hydroelectricitymanager.R;

/**
 * 作者：adu on 2016/4/5 09:20
 * 邮箱2383335125@qq.com
 */
public class RegisterPwdFragment extends BaseFragment {
    @Bind(R.id.backLeftWhite)
    ImageView backLeft;
    @Bind(R.id.inputPhone)
    TextView tvPhone;
    @Bind(R.id.setPassword)
    TextView setPassword;
    @Bind(R.id.setLoginPassword)
    EditText etSetPwd;
    @Bind(R.id.switchChecked)
    Switch switchChecked;
    @Bind(R.id.invitationCode)
    EditText invitationCode;
    @Bind(R.id.commitLogin)
    Button commitLogin;

    @OnClick({R.id.backLeftWhite,R.id.commitLogin})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.backLeftWhite:
                onBackPressed();
                break;
            case R.id.commitLogin:
                String pwd=etSetPwd.getText().toString().trim();
                if (pwd.length()>=6&&pwd.length()<20){
                    SharedPreferences sharedPreferences=getActivity().getSharedPreferences("data", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor=sharedPreferences.edit();
                    editor.putString("pwd",pwd);
                    editor.apply();
                    fragmentMgr.beginTransaction()
                            .addToBackStack("")
                            .replace(R.id.loginContainer, new LoginFragment())
                            .commit();
                }else {
                    Toast.makeText(context,"设置的密码格式不正确",Toast.LENGTH_SHORT).show();

                }
                break;
        }
    }
    @Override
    protected int getContentView() {
        return R.layout.register_set_password;
}

    @Override
    protected void updateUI() {
        tvPhone.setTextColor(ContextCompat.getColor(context, R.color.gray));
        setPassword.setTextColor(ContextCompat.getColor(context, R.color.colorPrimaryDark));
       setFocusChangeListeners(etSetPwd);
        setFocusChangeListeners(invitationCode);
        onCheckedChangeListeners(etSetPwd, switchChecked);
        pwdAddTextChangedListeners(etSetPwd,commitLogin);
    }
        @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

}
