package dhl.com.hydroelectricitymanager.fragment;

import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dhl.com.hydroelectricitymanager.R;
import dhl.com.hydroelectricitymanager.util.TimeButton;

/**
 * 作者：adu on 2016/4/1 17:00
 * 邮箱2383335125@qq.com
 */
public class RegisterVerFragment extends BaseFragment {
    @Bind(R.id.backLeftWhite)
    ImageView backLeft;
    @Bind(R.id.inputPhone)
    TextView tvPhone;
    @Bind(R.id.etInputVerification)
    EditText etVar;
    @Bind(R.id.commitVerification)
    Button commitVar;
    @Bind(R.id.inputVar)
    TextView tvVar;
 @Bind(R.id.timer)
 TimeButton timer;
    @OnClick({R.id.backLeftWhite,R.id.commitVerification,R.id.timer})
    public  void onClick(View view){
        switch (view.getId()){
            case R.id.backLeftWhite:
                onBackPressed();
                break;
            case R.id.commitVerification:
              String var=etVar.getText().toString().trim();
                if (!TextUtils.isEmpty(var)){
                    fragmentMgr.beginTransaction().addToBackStack("").replace(R.id.loginContainer, new RegisterPwdFragment()).commit();
                }
                break;


        }


    }



    @Override
    protected int getContentView() {
        return R.layout.register_input_verification;
    }

    @Override
    protected void updateUI() {
        timer.setVisibility(View.VISIBLE);
        tvPhone.setTextColor(ContextCompat.getColor(context, R.color.gray));
        tvVar.setTextColor(ContextCompat.getColor(context, R.color.colorPrimaryDark));
       setFocusChangeListeners(etVar);
        addTextChangedListeners(etVar, commitVar);

    }

//    class TimeCount extends CountDownTimer {
//        public TimeCount(long millisInFuture, long countDownInterval) {
//            super(millisInFuture, countDownInterval);
//        }
//
//        @Override
//        public void onFinish() {// 计时完毕
//            timer.setText(getResources().getString(R.string.resend_verification));
//            timer.setClickable(true);
//        }
//
//        @Override
//        public void onTick(long millisUntilFinished) {// 计时过程
//            timer.setClickable(false);//防止重复点击
//            String s="("+millisUntilFinished / 1000 + "s)后重新获取验证码";
//            timer.setText(s);
//        }
//    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
        }

 }
