package dhl.com.hydroelectricitymanager.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import dhl.com.hydroelectricitymanager.R;

/**
 * 作者：adu on 2016/3/30 16:47
 * 邮箱2383335125@qq.com
 */
public class StaffFragment extends Fragment{
    private View mainView;
    private StaffAdapter staffAdapter;
    @Bind(R.id.listStaff)
    ListView listStaff;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mainView = inflater.inflate(R.layout.hydroelectricity_staff_list, container, false);
        ButterKnife.bind(this,mainView);
        staffAdapter=new StaffAdapter();
        listStaff.setAdapter(staffAdapter);
        return mainView;

    }

    class StaffAdapter extends BaseAdapter{

    @Override
    public int getCount() {
        return 20;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = View.inflate(getActivity(), R.layout.item_hydroelectricity_staff_information, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        return convertView;
    }
}
    static class ViewHolder {
        @Bind(R.id.itemImage)
        ImageView itemImage;

        @Bind(R.id.staffName)
        TextView staffName;
        @Bind(R.id.starRank)
        TextView starRank;
        @Bind(R.id.distance)
        TextView distance;
        @Bind(R.id.starImage)
        ImageView starImage;
        @Bind(R.id.takeOrderTimes)
        TextView takeOrderTimes;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
