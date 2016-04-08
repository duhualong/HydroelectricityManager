package dhl.com.hydroelectricitymanager.activity;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dhl.com.hydroelectricitymanager.R;

/**
 * 作者：adu on 2016/4/7 16:59
 * 邮箱2383335125@qq.com
 */
public class MyReservation extends BaseActivity {
    private ReservationAdapter reservationAdapter;
 //   private List<Stupid> list;
    @Bind(R.id.listMyReservation)
    ListView listMyReservation;
    @Bind(R.id.imgReservation)
    ImageView imgService;
    @Bind(R.id.myReservationEdit)
    TextView edit;
    @Bind(R.id.backLeftWhite)
    ImageView backLeft;
    @Bind(R.id.noReservation)
    RelativeLayout noReservation;
    @OnClick({R.id.backLeftWhite,R.id.imgReservation, R.id.myReservationEdit})
    public void onClick(View view){
        switch (view.getId()){
//            case R.id.myReservationEdit:
//                for (Stupid stupid : list) {
//                    if (stupid.getName().equals("fuck")) {
//                        stupid.setType(1);
//                    }
//                }
//                reservationAdapter.notifyDataSetChanged();
  //              break;
            case R.id.myReservationEdit:

                break;
            case R.id.backLeftWhite:
            onBackPressed();
            break;
            case R.id.imgReservation:
                edit.setVisibility(View.VISIBLE);
                noReservation.setVisibility(View.GONE);
           //     reservationAdapter=new ReservationAdapter(new ArrayList<Stupid>());
                reservationAdapter=new ReservationAdapter();
                listMyReservation.setAdapter(reservationAdapter);
                break;
        }
    }
    @Override
    protected int getContentView() {
        return R.layout.activity_my_reservation;
    }

    @Override
    protected void updateUI() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }


    class ReservationAdapter extends BaseAdapter {

//        List<Stupid> list;
//
//        public ReservationAdapter(List<Stupid> list) {
//            super();
//            this.list = list;
//        }

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
        public View getView(final int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                convertView = View.inflate(getApplication(), R.layout.item_my_reservation, null);
                holder = new ViewHolder(convertView);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

//            if (list.get(position).getType() == 1 ) {
//                holder.rubbish.setVisibility(View.VISIBLE);
//                holder.rubbish.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        list.remove(position);
//                        notifyDataSetChanged();
//
//                    }
//                });
//            }
            return convertView;
        }
    }
    static class ViewHolder {
        @Bind(R.id.maintenanceProjects)
        TextView maintenanceProjects;
        @Bind(R.id.itemReservationEvaluation)
        TextView itemReservationEvaluation;
        @Bind(R.id.rubbish)
        ImageView rubbish;
        @Bind(R.id.descriptionProblem)
        TextView descriptionProblem;
        @Bind(R.id.reservationDate)
        TextView reservationDate;
        @Bind(R.id.maintenancePrice)
        TextView maintenancePrice;
        @Bind(R.id.btEvaluation)
        Button btEvaluation;
        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
//class Stupid {
//    int type;
//    String name;
//
//    public int getType() {
//        return type;
//    }
//
//    public void setType(int type) {
//        this.type = type;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//}
