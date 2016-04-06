package dhl.com.hydroelectricitymanager.fragment;

import android.support.v4.content.ContextCompat;
import android.text.Selection;
import android.text.Spannable;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

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

                }else {
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
        etSetPwd.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    etSetPwd.setBackgroundResource(R.drawable.edit_input);
                }
            }
        });
        invitationCode.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    invitationCode.setBackgroundResource(R.drawable.edit_input);
                }
            }
        });
        switchChecked.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            String pwd=etSetPwd.getText().toString().trim();

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    //如果选中，显示密码
                        etSetPwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());

                }else {
                    //否则隐藏密码
                        etSetPwd.setTransformationMethod(PasswordTransformationMethod.getInstance());


                }
                isChecked=!isChecked;
                etSetPwd.postInvalidate();
                //切换后将EditText光标置于末尾
                CharSequence charSequence = etSetPwd.getText();
                if (charSequence!=null) {
                    Spannable spanText = (Spannable) charSequence;
                    Selection.setSelection(spanText, charSequence.length());
                }
            }
        });
    }
        @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
