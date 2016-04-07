package dhl.com.hydroelectricitymanager.fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.soundcloud.android.crop.Crop;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import dhl.com.hydroelectricitymanager.R;
import dhl.com.hydroelectricitymanager.activity.MyReservation;
import dhl.com.hydroelectricitymanager.util.PhotoUtil;

/**
 * 作者：adu on 2016/3/28 09:50
 * 邮箱2383335125@qq.com
 */
public class MyFragment extends Fragment {
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private String saved;
    private View mainView;
    @Bind(R.id.name)
    TextView name;
    @Bind(R.id.phone)
    TextView phone;
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

    @OnClick({R.id.headPortrait,R.id.name,R.id.phone,R.id.myReservation,R.id.myService,R.id.lifeService,R.id.myAddress,R.id.customerHotLine})
    public void onClick(View view){

        switch (view.getId()){
            case R.id.headPortrait:
                showPhotoHeadFindDialog();
                break;
            case R.id.name:
                savedName();
                break;

            case R.id.myReservation:
                startActivity(new Intent(getActivity(),MyReservation.class));
                break;
            case R.id.lifeService:

                break;
            case R.id.myAddress:

                break;
            case R.id.myService:

                break;
            case R.id.customerHotLine:
                callHotLine();
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
}
