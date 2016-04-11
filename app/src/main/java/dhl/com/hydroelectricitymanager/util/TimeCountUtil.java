package dhl.com.hydroelectricitymanager.util;

import android.app.Fragment;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.widget.Button;

import dhl.com.hydroelectricitymanager.R;

/**
 * 作者：adu on 2016/4/11 17:26
 * 邮箱2383335125@qq.com
 */
public class TimeCountUtil  extends CountDownTimer{
    private Fragment fragment;
    private Button btn;
    public TimeCountUtil( Fragment fragment,long millisInFuture, long countDownInterval,Button btn) {
        super(millisInFuture, countDownInterval);
        this.fragment = fragment;
        this.btn =btn;
    }
    @Override
    public void onTick(long millisUntilFinished) {
        btn.setClickable(false);//设置不能点击
        btn.setText(millisUntilFinished / 1000 + "秒");//设置倒计时时间

//设置按钮为灰色，这时是不能点击的
        btn.setBackgroundResource(R.drawable.button_orange);
        Spannable span = new SpannableString(btn.getText().toString());//获取按钮的文字
        span.setSpan(new ForegroundColorSpan(Color.RED), 0, 2, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);//讲倒计时时间显示为红色
        btn.setText(span);
    }

    @Override
    public void onFinish() {
        btn.setText("重新获取验证码");
        btn.setClickable(true);//重新获得点击
        btn.setBackgroundResource(R.drawable.button_orange);//还原背景色

    }

}
