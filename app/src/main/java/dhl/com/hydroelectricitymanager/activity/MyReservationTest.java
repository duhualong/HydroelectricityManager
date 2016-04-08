package dhl.com.hydroelectricitymanager.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.Bind;
import dhl.com.hydroelectricitymanager.R;

/**
 * 作者：adu on 2016/4/8 10:06
 * 邮箱2383335125@qq.com
 */
public class MyReservationTest extends BaseActivity {
    private PersonAdapter personAdapter;
    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;
    @Bind(R.id.imgReservation)
    ImageView imgService;
    @Bind(R.id.myReservationEdit)
    TextView edit;
    @Bind(R.id.backLeftWhite)
    ImageView backLeft;
    @Bind(R.id.noReservation)
    RelativeLayout noReservation;
    @Override
    protected int getContentView() {
        return R.layout.activity_my_reservation_test;
    }

    @Override
    protected void updateUI() {
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);

    }
    class PersonAdapter extends RecyclerView.Adapter{

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return null;
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        }

        @Override
        public int getItemCount() {
            return 0;
        }
    }
}
