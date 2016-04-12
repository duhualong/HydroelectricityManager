package dhl.com.hydroelectricitymanager.fragment;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dhl.com.hydroelectricitymanager.R;

/**
 * 作者：adu on 2016/4/12 09:03
 * 邮箱2383335125@qq.com
 */
public class RebuildPwdFragment extends BaseFragment {
    @Bind(R.id.backLeftWhite)
    ImageView backLeft;
    @Bind(R.id.inputPwd)
    EditText inputPwd;
    @Bind(R.id.reInputPwd)
    EditText reInputPwd;
    @Bind(R.id.btnCertain)
    Button btnCertain;
    @OnClick({R.id.backLeftWhite,R.id.btnCertain})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.backLeftWhite:
                onBackPressed();
                break;
            case R.id.btnCertain:
                System.out.println("ssskksjj");
                String pwd=inputPwd.getText().toString().trim();
                String rePwd=reInputPwd.getText().toString().trim();
                if (TextUtils.isEmpty(pwd)||TextUtils.isEmpty(rePwd)){
                    Toast.makeText(context,"密码不能为空",Toast.LENGTH_LONG).show();

                }else {
                    if (rePwd.length() >= 6 &&rePwd.length() < 20 ||pwd.length()>=6&&pwd.length()<20) {
                        if (TextUtils.equals(rePwd,pwd)) {
                            fragmentMgr.beginTransaction()
                                    .addToBackStack("")
                                    .replace(R.id.loginContainer, new LoginFragment())
                                    .commit();
                        }
                    }
                }
                break;
        }
    }
    @Override
    protected int getContentView() {
        return R.layout.setting_new_password;
    }

    @Override
    protected void updateUI() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
