package dhl.com.hydroelectricitymanager.fragment;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dhl.com.hydroelectricitymanager.Constants;
import dhl.com.hydroelectricitymanager.R;
import dhl.com.hydroelectricitymanager.util.TimeCountUtil;

/**
 * 作者：adu on 2016/4/5 16:27
 * 邮箱2383335125@qq.com
 */
public class ForgetPwdFragment extends BaseFragment {
    private String numberCode;
    @Bind(R.id.backLeftWhite)
    ImageView backLeft;
    @Bind(R.id.inputPhoneNumber)
    EditText inputPhoneNumber;
    @Bind(R.id.inputVar)
    EditText inputVar;
    @Bind(R.id.btnVar)
    Button btnVar;
    @Bind(R.id.btnNext)
    Button btnNext;
    @OnClick({R.id.backLeftWhite,R.id.btnNext,R.id.btnVar})
    public void onClick(View view){
        TimeCountUtil timeCountUtil = new TimeCountUtil(this, Constants.TOTALTIME, Constants.PAUSETIME, btnVar);
        String phone=inputPhoneNumber.getText().toString().trim();
        switch (view.getId()) {

            case R.id.backLeftWhite:
                onBackPressed();
                break;
            case R.id.btnNext:

                break;
            case R.id.btnVar:
                if (TextUtils.isEmpty(phone)){
                    Toast.makeText(context,R.string.et_hint_input_phone_number,Toast.LENGTH_LONG).show();
                    btnVar.setBackgroundResource(R.drawable.button_gray);
                }else if (isPhone(phone)) {
                    btnVar.setBackgroundResource(R.drawable.button_orange);
                    timeCountUtil.start();
                    btnVar.setText("");
                    String  numCode = (int) ((Math.random() * 9 + 1) * 100000)+"";
                    final String number=numCode.trim();
                    numberCode=number;
                    inputVar.setText(number);
                    inputVar.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                            String var=s.toString().trim();
                            if (var==number){
                                btnNext.setBackgroundResource(R.drawable.button_orange);
                                btnNext.setClickable(true);
                            }else {
                                btnNext.setClickable(false);
                                btnNext.setBackgroundResource(R.drawable.button_gray);

                            }

                        }

                        @Override
                        public void afterTextChanged(Editable s) {

                        }
                    });

                    btnNext.setBackgroundResource(R.drawable.button_orange);

             //       String smstext = "你本次生成的6位安全验证码为：" + numCode;
                }
                break;
        }

    }
    @Override
    protected int getContentView() {
        return R.layout.activity_forget_password;
    }

    @Override
    protected void updateUI() {
        phoneAddTextChangedListeners(inputPhoneNumber,btnVar);


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

}
