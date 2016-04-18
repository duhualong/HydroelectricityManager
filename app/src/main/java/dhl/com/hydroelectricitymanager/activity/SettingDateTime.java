package dhl.com.hydroelectricitymanager.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Locale;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dhl.com.hydroelectricitymanager.R;

/**
 * 作者：adu on 2016/4/13 15:41
 * 邮箱2383335125@qq.com
 */
public class SettingDateTime extends BaseActivity {
   private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private String date;
    private String time;
    private Calendar mCalendar;// 定义一个日历对象，用于设置时期
    private int iYear, iMonth, iDay, iHour, iMinute;// 记录日期和时间
    @Bind(R.id.backLeftWhite)
    ImageView backLeft;
    @Bind(R.id.finishedTime)
    ImageView finishedTime;
    @Bind(R.id.dateService)
    TextView dateService;
    @Bind(R.id.timeService)
    TextView timeService;
    @Bind(R.id.datePicker)
    DatePicker datePicker;
    @Bind(R.id.timePicker)
    TimePicker timePicker;

    @OnClick({R.id.backLeftWhite, R.id.finishedTime})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.backLeftWhite:
                onBackPressed();
                break;
            case R.id.finishedTime:
                String savedDate = sharedPreferences.getString("dateService", "");
                String savedTime=sharedPreferences.getString("timeService", "");
                if (TextUtils.isEmpty(savedDate)||TextUtils.isEmpty(savedTime)){
                    Toast.makeText(context,"请选择服务时间",Toast.LENGTH_SHORT).show();
                }else {
                    String savedServiceTime=savedDate+savedTime;
                    editor.putString("savedServiceTime", savedServiceTime);
                    editor.apply();
                    Toast.makeText(context,"设置服务时间成功",Toast.LENGTH_SHORT).show();
                    finish();
                }


                break;
        }
    }

    @Override
    protected int getContentView() {
        return R.layout.setting_date_time;

    }

    @Override
    protected void updateUI() {

    sharedPreferences= getSharedPreferences("data", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        mCalendar = Calendar.getInstance(Locale.CHINA);
        // 获取当前的年、月、日、小时、分钟
        iYear = mCalendar.get(Calendar.YEAR);
        iMonth = mCalendar.get(Calendar.MONTH);
        iDay = mCalendar.get(Calendar.DAY_OF_MONTH);
        iHour = mCalendar.get(Calendar.HOUR_OF_DAY);
        iMinute = mCalendar.get(Calendar.MINUTE);
        // 设置TimePicker组件支持24小时制
        timePicker.setIs24HourView(true);
        datePicker.init(iYear, iMonth, iDay, new DatePicker.OnDateChangedListener() {

            @Override

            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                iYear = year;

                iMonth = monthOfYear + 1;// 因为1月在系统中的初始值为0，所以需要加1

                iDay = dayOfMonth;

// 在TextView里面显示设置好的日期
                String selectDate = iYear + "年" + iMonth + "月" + iDay + "日";
                if (!TextUtils.isEmpty(selectDate)) {
                    editor.putString("dateService", selectDate);
                    editor.apply();
//                    Spannable dates = new SpannableString(selectDate);
//                    dates.setSpan(new ForegroundColorSpan(ContextCompat.getColor(context, R.color.colorPrimaryDark)), 0, dates.length(),
//                            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                  String  dates= getString(R.string.setting_service_date,selectDate);
                    dateService.setText(dates);
                }

            }

        });
        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {

            @Override

            public void onTimeChanged(TimePicker arg0, int hourOfDay, int minute) {

                iHour = hourOfDay;

                iMinute = minute;

// 在TextView里面显示设置好的时间
                String selectTime = iHour + "时" + iMinute + "分";
                if (!TextUtils.isEmpty(selectTime)) {
                    editor.putString("timeService", selectTime);
                    editor.apply();
//                    Spannable dates = new SpannableString(selectTime);
//                    dates.setSpan(new ForegroundColorSpan(ContextCompat.getColor(context, R.color.colorPrimaryDark)), 0, dates.length(),
//                            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    String  times= getString(R.string.setting_service_times,selectTime);
                 timeService.setText(times);
                }
            }

        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        editor.putString("dateService", "");
        editor.putString("timeService", "");
        editor.apply();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
        editor.putString("dateService", "");
        editor.putString("timeService", "");
        editor.apply();
    }

}
