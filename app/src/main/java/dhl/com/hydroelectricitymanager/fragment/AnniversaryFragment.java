package dhl.com.hydroelectricitymanager.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import dhl.com.hydroelectricitymanager.R;

/**
 * 作者：adu on 2016/3/21 14:37
 * 邮箱2383335125@qq.com
 */
public class AnniversaryFragment extends Fragment {
    private View mainView;
    private Integer[] mThumbIds = {R.drawable.ic_housework_combo_year,R.drawable.ic_housework_combo_month,R.drawable.ic_life_combo_year,R.drawable.ic_life_combo_month};
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mainView = inflater.inflate(R.layout.activity_anniversary_service_content, container, false);
        GridView gridView = (GridView) mainView.findViewById(R.id.gridView);
        gridView.setAdapter(new ImageAdapter(getActivity()));
        return mainView;
    }
    private class ImageAdapter extends BaseAdapter {
        private Context mContext;

        public ImageAdapter(Context context) {
            this.mContext=context;
        }

        @Override
        public int getCount() {
            return mThumbIds.length;
        }

        @Override
        public Object getItem(int position) {
            return mThumbIds[position];
        }

        @Override
        public long getItemId(int position) {
            // TODO Auto-generated method stub
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            //定义一个ImageView,显示在GridView里
            ImageView imageView;
            if(convertView==null){
                imageView=new ImageView(mContext);
            //    imageView.setLayoutParams(new GridView.LayoutParams(85, 85));
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
               // imageView.setPadding(8, 8, 8, 8);
            }else{
                imageView = (ImageView) convertView;
            }
            imageView.setImageResource(mThumbIds[position]);
            return imageView;
        }
    }
}
