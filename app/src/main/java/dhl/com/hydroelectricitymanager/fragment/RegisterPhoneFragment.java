package dhl.com.hydroelectricitymanager.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
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
                    SharedPreferences sharedPreferences=getActivity().getSharedPreferences("data", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor=sharedPreferences.edit();
                    editor.putString("phone", phone);
                    editor.apply();
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
      setFocusChangeListeners(etInputPhone);
       phoneAddTextChangedListeners(etInputPhone,getVerification);
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
