package dhl.com.hydroelectricitymanager.fragment;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.soundcloud.android.crop.Crop;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import dhl.com.hydroelectricitymanager.R;
import dhl.com.hydroelectricitymanager.activity.MyCoupon;
import dhl.com.hydroelectricitymanager.activity.MyReservation;
import dhl.com.hydroelectricitymanager.activity.MyService;
import dhl.com.hydroelectricitymanager.activity.ServiceAddress;
import dhl.com.hydroelectricitymanager.util.PhotoUtil;

/**
 * 作者：adu on 2016/3/28 09:50
 * 邮箱2383335125@qq.com
 */
public class MyFragment extends Fragment {

    private static final int REQUEST_WRITE_STORAGE = 111;
    private static final int REQUEST_CAMERA = 112;
    final public static int REQUEST_CODE_ASK_CALL_PHONE = 123;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private String saved;
    private View mainView;
    @Bind(R.id.name)
    TextView name;
    @Bind(R.id.phone)
    TextView phone;
    @Bind(R.id.mainLayout)
    LinearLayout mainLayout;
    @Bind(R.id.headPortrait)
    CircleImageView headPortrait;
    @Bind(R.id.accountBalance)
    TextView accountBalance;
    @Bind(R.id.discountCoupon)
    TextView discountCoupon;
    @Bind(R.id.collectionNumber)
    TextView collectionNumber;
    @Bind(R.id.myReservation)
    RelativeLayout myReservation;
    @Bind(R.id.myService)
    RelativeLayout myService;
    @Bind(R.id.lifeService)
    RelativeLayout lifeService;
    @Bind(R.id.myAddress)
    RelativeLayout myAddress;
    @Bind(R.id.customerHotLine)
    RelativeLayout customerHotLine;

    @OnClick({R.id.headPortrait,R.id.name,R.id.phone,R.id.discountCoupon,R.id.myReservation,R.id.myService,R.id.lifeService,R.id.myAddress,R.id.customerHotLine})
    public void onClick(View view){

        switch (view.getId()){
            case R.id.headPortrait:
                boolean hasPermission = (ActivityCompat.checkSelfPermission(getActivity(),
                        Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED);
                if (hasPermission) {
                    if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.CAMERA)
                            != PackageManager.PERMISSION_GRANTED) {
                        requestCameraPermission();
                    } else {
                        showPhotoHeadFindDialog();
                    }
                } else {
                    ActivityCompat.requestPermissions(getActivity(),
                            new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                            REQUEST_WRITE_STORAGE);

                }
                break;
            case R.id.name:
                savedName();
                break;
            case R.id.discountCoupon:
                startActivity(new Intent(getActivity(), MyCoupon.class));
                break;

            case R.id.myReservation:
                startActivity(new Intent(getActivity(),MyReservation.class));
                break;
            case R.id.lifeService:

                break;
            case R.id.myAddress:
                startActivity(new Intent(getActivity(), ServiceAddress.class));

                break;
            case R.id.myService:
                startActivity(new Intent(getActivity(),MyService.class));

                break;
            case R.id.customerHotLine:
                //电话权限的动态方法
                if (Build.VERSION.SDK_INT >= 23) {
                    int checkCallPhonePermission = ContextCompat.checkSelfPermission(getContext(), Manifest.permission.CALL_PHONE);
                    if(checkCallPhonePermission != PackageManager.PERMISSION_GRANTED){
                        ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CODE_ASK_CALL_PHONE);
                        return;
                    }else{
                        //上面已经写好的拨号方法
                        callHotLine();
                    }
                } else {
                    //上面已经写好的拨号方法
                    callHotLine();
                }
             //   callHotLine();
                break;
        }

    }

    private void savedName() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View view = View.inflate(getActivity(), R.layout.dialog_modify_name, null);
        final EditText nameDialog = (EditText) view.findViewById(R.id.etNameDialog);
        String savedName = sharedPreferences.getString("name", "");
        if (!TextUtils.isEmpty(savedName)) {
            nameDialog.setText(savedName);
        }
        builder.setView(view).setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        }).setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String editNames = nameDialog.getText().toString().trim();
                if (TextUtils.isEmpty(editNames)) {
                    Toast.makeText(getActivity(), "姓名不能为空", Toast.LENGTH_SHORT).show();

                } else if (editNames.equals(saved)) {

                } else {
                    saved = editNames;
                    name.setText(saved);
                    editor = sharedPreferences.edit();
                    editor.putString("name", saved);
                    editor.apply();
                    Toast.makeText(getActivity(), "修改成功！", Toast.LENGTH_SHORT).show();
                }
                dialog.dismiss();
            }
        }).setCancelable(true);
        builder.create().show();
    }

    private void callHotLine() {
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        builder.setView(View.inflate(getActivity(),R.layout.dialog_modify_customer_care,null))
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(Intent.ACTION_CALL);
                Uri data = Uri.parse("tel:" + "18817772486");
                intent.setData(data);
                startActivity(intent);
            }
        }).setCancelable(true);
        builder.create().show();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mainView = inflater.inflate(R.layout.my_fragment, container, false);
        ButterKnife.bind(this,mainView);
        sharedPreferences=getActivity().getSharedPreferences("data", Context.MODE_PRIVATE);
        String savedPhone=sharedPreferences.getString("phone","");
        if (!TextUtils.isEmpty(savedPhone)) {
            phone.setText(savedPhone);
        }
        return mainView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
    private void showPhotoHeadFindDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(View.inflate(getActivity(), R.layout.dialog_modify_photo_head, null)).
                setPositiveButton("相册", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        PhotoUtil.startGallery(MyFragment.this);

                    }
                }).setNegativeButton("相机", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                PhotoUtil.startCamera(MyFragment.this);

            }
        }).setCancelable(true);
        builder.create().show();


    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            Uri uri;
            switch (requestCode) {
                case PhotoUtil.REQUEST_CAMERA:
                    uri = Uri.fromFile(PhotoUtil.getTempFile());
                    PhotoUtil.beginCrop(MyFragment.this, uri);
                    break;
                case PhotoUtil.REQUEST_GALLERY:
                    if (data != null) {
                        uri = data.getData();
                        PhotoUtil.beginCrop(MyFragment.this, uri);

                    }
                    break;
                case PhotoUtil.REQUEST_CROP:
                    if (data != null) {
                        uri = Crop.getOutput(data); // 裁剪后图片Uri
                        System.out.println("image uri: " + uri);
                        headPortrait.setImageURI(uri);
                        Toast.makeText(getActivity(), "上传头像成功！", Toast.LENGTH_SHORT).show();
                    }
                    break;
            }
        }
    }


    private void requestCameraPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                Manifest.permission.CAMERA)) {
            Snackbar.make(mainLayout, "需要摄像头的权限，以显示相机预览",
                    Snackbar.LENGTH_INDEFINITE)
                    .setAction("OK", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            ActivityCompat.requestPermissions(getActivity(),
                                    new String[]{Manifest.permission.CAMERA},
                                    REQUEST_CAMERA);
                        }
                    }).show();
        } else {
            // Camera permission has not been granted yet. Request it directly.
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CAMERA},
                    REQUEST_CAMERA);
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case REQUEST_WRITE_STORAGE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Snackbar.make(mainLayout, "CAMERA permission has now been granted. Showing preview.",
                            Snackbar.LENGTH_SHORT).show();
                } else {
                    //Log.i(TAG, "CAMERA permission was NOT granted.");
                    Snackbar.make(mainLayout, "CAMERA permission was NOT granted.",
                            Snackbar.LENGTH_SHORT).show();
                }
                break;
            case REQUEST_CAMERA:
                if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // Camera permission has been granted, preview can be displayed
                    // Log.i(TAG, "CAMERA permission has now been granted. Showing preview.");
                    Snackbar.make(mainLayout, "CAMERA permission has now been granted. Showing preview.",
                            Snackbar.LENGTH_SHORT).show();
                } else {
                    //Log.i(TAG, "CAMERA permission was NOT granted.");
                    Snackbar.make(mainLayout, "CAMERA permission was NOT granted.",
                            Snackbar.LENGTH_SHORT).show();
                }
                break;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
