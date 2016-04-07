package dhl.com.hydroelectricitymanager.fragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.Selection;
import android.text.Spannable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.ButterKnife;
import dhl.com.hydroelectricitymanager.R;

/**
 * 作者：adu on 2016/4/1 14:39
 * 邮箱2383335125@qq.com
 */
public abstract class BaseFragment extends Fragment{
    protected View mainView;
    protected FragmentManager fragmentMgr;
    protected boolean check=true;
    protected Context context;
    // 获取子类布局
    protected abstract int getContentView();
    // 更新页面ui
    protected abstract void updateUI();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (getContentView() != 0) {
            mainView = inflater.inflate(getContentView(), container, false);
            ButterKnife.bind(this, mainView);
            context = getActivity();
            fragmentMgr = getFragmentManager();
            updateUI();
            return mainView;
        }
        return super.onCreateView(inflater, container, savedInstanceState);
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
    /**
     * Back press action in fragment
     */
    protected void onBackPressed() {
        if (fragmentMgr.getBackStackEntryCount() > 0) {
            fragmentMgr.popBackStack();
        } else {
            getActivity().onBackPressed();
        }
    }
    protected void setFocusChangeListeners(final EditText view){

        view.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (check){
                    view.setBackgroundResource(R.drawable.edit_input);
                }else {
                    view.setBackgroundResource(R.drawable.edit_no_input);

                }
            }
        });

    }
    protected void addTextChangedListeners(EditText view, final Button button){
        view.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String var = s.toString().trim();
                if (!TextUtils.isEmpty(var)) {
                    button.setBackgroundResource(R.drawable.button_orange);
                } else {
                    button.setBackgroundResource(R.drawable.button_gray);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    protected void onCheckedChangeListeners(final EditText editText,Switch switchChecked){
        switchChecked.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (check) {
                    editText.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    editText.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
                check = !check;
                editText.postInvalidate();
                //切换后将EditText光标置于末尾
                CharSequence charSequence = editText.getText();
                if (charSequence != null) {
                    Spannable spanText = (Spannable) charSequence;
                    Selection.setSelection(spanText, charSequence.length());
                }
            }
        });

    }
    public static boolean isPhone(String phone) {
        if (TextUtils.isEmpty(phone)) {
            return false;
        }
        String expression = "^[1]([3-9][0-9]{1}|59|58|88|89)[0-9]{8}$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(phone);
        return matcher.matches();
    }
    protected void phoneAddTextChangedListeners(EditText editText, final Button button){
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(isPhone(s.toString())){
                    button.setBackgroundResource(R.drawable.button_orange);
                }else {
                    button.setBackgroundResource(R.drawable.button_gray);
                }
            }
            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }
    protected void pwdAddTextChangedListeners(EditText editText, final Button button){
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().trim().length()>=6&&s.toString().trim().length()<20){
                    button.setBackgroundResource(R.drawable.button_orange);
                }else {
                    button.setBackgroundResource(R.drawable.button_gray);
                }
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }
}
