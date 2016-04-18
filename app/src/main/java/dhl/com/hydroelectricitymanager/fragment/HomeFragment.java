package dhl.com.hydroelectricitymanager.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dhl.com.hydroelectricitymanager.R;
import dhl.com.hydroelectricitymanager.activity.LoginActivity;
import dhl.com.hydroelectricitymanager.activity.ServiceDemandActivity;

/**
 * 作者：adu on 2016/3/21 14:37
 * 邮箱2383335125@qq.com
 */
public class HomeFragment extends Fragment {
    private View mainView;
    @Bind(R.id.tvLocation)
    TextView tvLocation;
    @Bind(R.id.dropdownList)
    ImageView dropdpwnList;
    @Bind(R.id.hydroelectricManager)
    LinearLayout hydroelectricManager;
    @Bind(R.id.storeManager)
    LinearLayout storeManager;
    @OnClick({R.id.dropdownList,R.id.hydroelectricManager,R.id.storeManager})
    public void dropdownList(View view){
        switch (view.getId()) {
            case R.id.dropdownList:
//        startActivity(new Intent(getActivity(), DropDownList.class));
                break;
            case R.id.hydroelectricManager:
                startActivity(new Intent(getActivity(), ServiceDemandActivity.class));
                break;
            case R.id.storeManager:
                startActivity(new Intent(getActivity(), LoginActivity.class));
                break;



        }

    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mainView = inflater.inflate(R.layout.home_fragment, container, false);
        ButterKnife.bind(this,mainView);
//        SharedPreferences sharedPreferences=getActivity().getSharedPreferences("data", Context.MODE_PRIVATE);
//        String locations=sharedPreferences.getString("locations","");
//        if (!TextUtils.isEmpty(locations)){
//            tvLocation.setText(locations);
//        }
        return mainView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
