package dhl.com.hydroelectricitymanager.fragment;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.Bind;
import butterknife.OnClick;
import dhl.com.hydroelectricitymanager.R;

/**
 * 作者：adu on 2016/4/1 15:00
 * 邮箱2383335125@qq.com
 */
public class RegisterPhoneFragment extends BaseFragment{
    TextView timer;
    @Bind(R.id.backLeftWhite)
    ImageView backLeft;
    @Bind(R.id.etInputPhone)
    EditText etInputPhone;
    @Bind(R.id.getVerification)
    Button getVerification;
    @Bind(R.id.checkBox)
    CheckBox checkBox;
    @Bind(R.id.userContract)
    TextView userContract;
    @OnClick({R.id.backLeftWhite,R.id.getVerification,R.id.userContract,R.id.etInputPhone})
    public void onClick(View view){
        String phone=etInputPhone.getText().toString().trim();
        switch (view.getId()){
            case R.id.backLeftWhite:
                onBackPressed();
                break;
            case R.id.getVerification:

                if (isPhone(phone)){
                    fragmentMgr.beginTransaction()
                            .addToBackStack("")
                            .replace(R.id.loginContainer, new RegisterVerFragment())
                            .commit();
                }
                break;
            case R.id.userContract:

                break;
            case R.id.etInputPhone:

                break;
        }
    }


    @Override
    protected int getContentView() {
        return R.layout.register_input_phone;
    }

    @Override
    protected void updateUI() {
        etInputPhone.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    etInputPhone.setBackgroundResource(R.drawable.edit_input);
                }
            }
        });
        etInputPhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {


            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(isPhone(s.toString())){
                    getVerification.setBackgroundResource(R.drawable.button_orange);


                }else {
                    getVerification.setBackgroundResource(R.drawable.button_gray);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


    }
    /**
     * 手机号格式是否正确
     * @param phone 手机号码
     * @return 手机正确返回true
     */
    public static boolean isPhone(String phone) {
        if (TextUtils.isEmpty(phone)) {
            return false;
        }
        String expression = "^[1]([3-9][0-9]{1}|59|58|88|89)[0-9]{8}$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(phone);
        return matcher.matches();
    }


}
