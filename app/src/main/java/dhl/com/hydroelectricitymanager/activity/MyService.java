package dhl.com.hydroelectricitymanager.activity;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dhl.com.hydroelectricitymanager.R;

/**
 * 作者：adu on 2016/4/8 14:07
 * 邮箱2383335125@qq.com
 */
public class MyService extends BaseActivity {
    private ServiceAdapter serviceAdapter;
    @Bind(R.id.noService)
    RelativeLayout noService;
    @Bind(R.id.backLeftWhite)
    ImageView backLeft;
    @Bind(R.id.imgService)
    ImageView imgService;
    @Bind(R.id.lvMyService)
    ListView listService;
    @OnClick({R.id.backLeftWhite,R.id.imgService})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.backLeftWhite:
                onBackPressed();
                break;
            case R.id.imgService:
                noService.setVisibility(View.GONE);
                serviceAdapter=new ServiceAdapter();
                listService.setAdapter(serviceAdapter);
                break;
        }
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_my_service;
    }

    @Override
    protected void updateUI() {



    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    class ServiceAdapter extends BaseAdapter{
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
                convertView = View.inflate(getApplication(), R.layout.item_my_service, null);
                holder = new ViewHolder(convertView);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            return convertView;
        }
    }
    static class ViewHolder {
       @Bind(R.id.itemDrawableService)
       ImageView imgItemService;
        @Bind(R.id.itemServiceName)
        TextView itemServiceName;
        @Bind(R.id.itemServicePrice)
        TextView itemServicePrice;
        @Bind(R.id.blackRightBack)
        ImageView blackBack;
        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

}
