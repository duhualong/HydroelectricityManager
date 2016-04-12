package dhl.com.hydroelectricitymanager.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dhl.com.hydroelectricitymanager.R;
import dhl.com.hydroelectricitymanager.activity.PublicRequirement;

/**
 * 作者：adu on 2016/3/30 16:44
 * 邮箱2383335125@qq.com
 */
public class ProjectFragment extends Fragment {
    private View mainView;
    @Bind(R.id.electricityLightPath)
    RelativeLayout electricityLightPath;
    @Bind(R.id.faucetFitting)
    RelativeLayout faucetFitting;
    @Bind(R.id.bathroomSanitary)
    RelativeLayout bathroomSanitary;
    @Bind(R.id.rushPipe)
    RelativeLayout rushPipe;
    @Bind(R.id.others)
    RelativeLayout others;
    @OnClick({R.id.electricityLightPath,R.id.faucetFitting,R.id.bathroomSanitary,R.id.rushPipe,R.id.others})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.electricityLightPath:
                startActivity(new Intent(getActivity(), PublicRequirement.class));
                break;
            case R.id.faucetFitting:

                break;
            case R.id.bathroomSanitary:

                break;
            case R.id.rushPipe:

                break;
            case R.id.others:

                break;
        }
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mainView = inflater.inflate(R.layout.activity_hydroelectricity_service_bottom, container, false);
        ButterKnife.bind(this,mainView);
        return mainView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
