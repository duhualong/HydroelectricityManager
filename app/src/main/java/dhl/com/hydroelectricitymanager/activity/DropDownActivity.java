package dhl.com.hydroelectricitymanager.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.CameraUpdateFactory;
import com.amap.api.maps2d.LocationSource;
import com.amap.api.maps2d.MapView;
import com.amap.api.maps2d.model.BitmapDescriptorFactory;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.maps2d.model.LatLngBounds;
import com.amap.api.maps2d.model.Marker;
import com.amap.api.maps2d.model.MarkerOptions;
import com.amap.api.maps2d.model.MyLocationStyle;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dhl.com.hydroelectricitymanager.R;

/**
 * 作者：adu on 2016/3/30 09:30
 * 邮箱2383335125@qq.com
 */
public class DropDownActivity extends Activity implements LocationSource,AMapLocationListener,AMap.InfoWindowAdapter {
    final private int REQUEST_CODE_ASK_PERMISSIONS_LOCATION = 123;
    final private int REQUEST_CODE_ACCESS_COARSE_LOCATION= 124;
    private LatLng latLng; // 用户选择后的经纬度
    private  String address;
    @Bind(R.id.backLeftWhite)
    ImageView backLeft;
    @Bind(R.id.inputAddress)
    EditText inputAddress;
    @Bind(R.id.tvSearch)
    TextView tvSearch;
    @Bind(R.id.map)
    MapView mapView;
    private Marker marker;
    private double lat;
    private double lng;

    @OnClick(R.id.backLeftWhite)
    public void backLeft(){
        onBackPressed();
    }
    private AMap aMap;
    private OnLocationChangedListener mListener;
    private AMapLocationClient mlocationClient;
    private AMapLocationClientOption mLocationOption;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.location_dropdown);
        ButterKnife.bind(this);
        mapView.onCreate(savedInstanceState);// 此方法必须重写
        int hasWriteLocationPermission= ActivityCompat.checkSelfPermission(getApplicationContext(),Manifest.permission.ACCESS_FINE_LOCATION);
        if (hasWriteLocationPermission!=PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(DropDownActivity.this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},REQUEST_CODE_ASK_PERMISSIONS_LOCATION);
            return;
        }
        if (aMap == null) {
            aMap = mapView.getMap();
            setUpMap();
        }
        MarkerOptions markerOptions=new MarkerOptions();
        markerOptions.position(new LatLng(31.41,121.48));
      //  markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_clear));
        marker = aMap.addMarker(markerOptions);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode){
            case REQUEST_CODE_ASK_PERMISSIONS_LOCATION:
//                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                    if (ActivityCompat.checkSelfPermission(DropDownActivity.this, Manifest.permission.ACCESS_FINE_LOCATION)
//                            != PackageManager.PERMISSION_GRANTED) {
//                       int hasWriteCOARSEPermission= ActivityCompat.checkSelfPermission(getApplicationContext(),Manifest.permission.ACCESS_COARSE_LOCATION);
//                     if (hasWriteCOARSEPermission!=PackageManager.PERMISSION_GRANTED){
//                          ActivityCompat.requestPermissions(DropDownActivity.this,new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},REQUEST_CODE_ACCESS_COARSE_LOCATION);
//                           return;
//                      }
//
//
//                    }
//
//                    } else {
//
//
//                    }

                break;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    /**
     * 设置一些amap的属性
     */
    private void setUpMap() {
        // 自定义系统定位小蓝点
        MyLocationStyle myLocationStyle = new MyLocationStyle();
//        myLocationStyle.myLocationIcon(BitmapDescriptorFactory
//                .fromResource(R.drawable.ic_location_now));// 设置小蓝点的图标
        myLocationStyle.strokeColor(Color.BLACK);// 设置圆形的边框颜色
        myLocationStyle.radiusFillColor(Color.argb(100, 0, 0, 180));// 设置圆形的填充颜色
        // myLocationStyle.anchor(int,int)//设置小蓝点的锚点
        myLocationStyle.strokeWidth(1.0f);// 设置圆形的边框粗细
        aMap.setMyLocationStyle(myLocationStyle);
        aMap.setLocationSource(this);// 设置定位监听
        aMap.getUiSettings().setMyLocationButtonEnabled(true);// 设置默认定位按钮是否显示
        aMap.setMyLocationEnabled(true);// 设置为true表示显示定位层并可触发定位，false表示隐藏定位层并不可触发定位，默认是false
        // aMap.setMyLocationType()
    }

    /**
     * 方法必须重写
     */
    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    /**
     * 方法必须重写
     */
    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
        deactivate();
    }
    /**
     * 方法必须重写
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    /**
     * 方法必须重写
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
        ButterKnife.unbind(this);
    }
    /**
     * 定位成功后回调函数
     */
    @Override
    public void onLocationChanged(AMapLocation amapLocation) {
        if (mListener != null && amapLocation != null) {
            if(amapLocation.getErrorCode() == 0) {
                mListener.onLocationChanged(amapLocation);// 显示系统小蓝点
               address= amapLocation.getAddress();
                lat=amapLocation.getLatitude();
                lng=amapLocation.getLongitude();
                if (lat != 0 && lng != 0) {
                    latLng = new LatLng(lat, lng);
                    LatLngBounds bounds = new LatLngBounds.Builder()
                            .include(latLng).build();
                    aMap.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds, 10));
                    drawMarkers(latLng, address);
                }

            } else {
                String errText = "定位失败," + amapLocation.getErrorCode()+ ": " + amapLocation.getErrorInfo();
                Log.e("AmapErr",errText);
            }
        }
    }
    /**
     * 激活定位
     */
    @Override
    public void activate(OnLocationChangedListener listener) {
        mListener = listener;
        if (mlocationClient == null) {
            mlocationClient = new AMapLocationClient(this);
            mLocationOption = new AMapLocationClientOption();

            //设置定位监听
            mlocationClient.setLocationListener(this);
            //设置为高精度定位模式
            mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
            //设置定位参数
            mlocationClient.setLocationOption(mLocationOption);
            // 此方法为每隔固定时间会发起一次定位请求，为了减少电量消耗或网络流量消耗，
            // 注意设置合适的定位时间的间隔（最小间隔支持为2000ms），并且在合适时间调用stopLocation()方法来取消定位请求
            // 在定位结束后，在合适的生命周期调用onDestroy()方法
            // 在单次定位情况下，定位无论成功与否，都无需调用stopLocation()方法移除请求，定位sdk内部会移除
            mlocationClient.startLocation();
        }
    }
    /**
     * 停止定位
     */
    @Override
    public void deactivate() {
        mListener = null;
        if (mlocationClient != null) {
            mlocationClient.stopLocation();
            mlocationClient.onDestroy();
        }
        mlocationClient = null;
    }

    @Override
    public View getInfoWindow(Marker marker) {
        View infoWindow = getLayoutInflater().inflate(
                R.layout.maker_dialog, null);
        render(marker, infoWindow);
        return infoWindow;
    }

    private void render(Marker marker, View view) {
       TextView titleView= (TextView) view.findViewById(R.id.textView);
       TextView btnCertain= (TextView) view.findViewById(R.id.btnCertain);
        if (!TextUtils.isEmpty(address)) {
            SharedPreferences sharedPreferences=getSharedPreferences("data",Context.MODE_PRIVATE);
            SharedPreferences.Editor editor=sharedPreferences.edit();
            editor.putString("locations",address);
            editor.apply();
            titleView.setText(address);
        }

    }

    @Override
    public View getInfoContents(Marker marker) {
        return null;
    }

    public void drawMarkers(LatLng latLng, String markerTitle) {
        Marker marker = aMap.addMarker(new MarkerOptions()
                .position(latLng)
                .title(markerTitle).icon(BitmapDescriptorFactory
                        .fromResource(R.drawable.ic_location_now))
                        //.snippet(markerSnippet)
                .draggable(true));
        marker.showInfoWindow();
        //.icon(BitmapDescriptorFactory
        //      .defaultMarker(BitmapDescriptorFactory.HUE_AZURE)) 系统默认图标
    }
}
