package dhl.com.hydroelectricitymanager.activity;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.soundcloud.android.crop.Crop;

import butterknife.Bind;
import butterknife.OnClick;
import dhl.com.hydroelectricitymanager.R;
import dhl.com.hydroelectricitymanager.util.PhotoUtil;

/**
 * 作者：adu on 2016/4/12 13:29
 * 邮箱2383335125@qq.com
 */
public class PublicRequirement extends BaseActivity {
    private static final int REQUEST_CAMERA = 0;
    private View mLayout;
    @Bind(R.id.backLeftWhite)
    ImageView backLeft;
    @Bind(R.id.etDescriptionProblem)
    EditText etDescriptionProblem;
    @Bind(R.id.imgFirstUpload)
    ImageView imgFirstUpload;
    @Bind(R.id.imgSecondUpload)
    ImageView imgSecondUpload;
    @Bind(R.id.imgThirdUpload)
    ImageView imgThirdUpload;
    @Bind(R.id.serviceTime)
    RelativeLayout serviceTime;
    @Bind(R.id.selectedServiceTime)
    TextView selectedServiceTime;
    @Bind(R.id.serviceAddress)
    RelativeLayout serviceAddress;
    @Bind(R.id.selectedServiceAddress)
    TextView selectedServiceAddress;
    @OnClick({R.id.backLeftWhite,R.id.serviceTime,R.id.serviceAddress,R.id.imgFirstUpload,R.id.imgSecondUpload,R.id.imgThirdUpload})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.backLeftWhite:
            onBackPressed();
            break;
            case R.id.imgFirstUpload:
                showPhotoHeadFindDialog();
                break;
            case R.id.imgSecondUpload:

                break;
            case R.id.imgThirdUpload:

                break;
            case R.id.serviceTime:

                break;
            case R.id.serviceAddress:

                break;

        }
    }
    @Override
    protected int getContentView() {
        return R.layout.publication_requirement;
    }

    @Override
    protected void updateUI() {

    }


    private void showPhotoHeadFindDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setView(View.inflate(context, R.layout.dialog_modify_photo_head, null)).
                setPositiveButton("相册", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        PhotoUtil.startGallery(PublicRequirement.this);

                    }
                }).setNegativeButton("相机", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (ActivityCompat.checkSelfPermission(PublicRequirement.this, Manifest.permission.CAMERA)
                        != PackageManager.PERMISSION_GRANTED) {
                    requestCameraPermission();
                }else {
                    PhotoUtil.startCamera(PublicRequirement.this);
                }


            }
        }).setCancelable(true);
        builder.create().show();


    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

       if (resultCode == Activity.RESULT_OK) {
            Uri uri;
            switch (requestCode) {
                case PhotoUtil.REQUEST_CAMERA:
                    uri = Uri.fromFile(PhotoUtil.getTempFile());
                    PhotoUtil.beginCrop(PublicRequirement.this, uri);
                    break;
                case PhotoUtil.REQUEST_GALLERY:
                    if (data != null) {
                        uri = data.getData();
                        PhotoUtil.beginCrop(PublicRequirement.this, uri);
                    }
                    break;
                case PhotoUtil.REQUEST_CROP:
                    if (data != null) {
                        uri = Crop.getOutput(data); // 裁剪后图片Uri
                        System.out.println("image uri: " + uri);
                        imgFirstUpload.setImageURI(uri);
                        Toast.makeText(context, "上传头像成功！", Toast.LENGTH_SHORT).show();
                    }
                    break;
            }
        super.onActivityResult(requestCode, resultCode, data);
     }
    }





    private void requestCameraPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                Manifest.permission.CAMERA)) {
            Snackbar.make(mLayout,"dudu",Snackbar.LENGTH_INDEFINITE).setAction("ok", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ActivityCompat.requestPermissions(PublicRequirement.this,
                            new String[]{Manifest.permission.CAMERA},REQUEST_CAMERA);
                }
            }).show();
        }else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA},  REQUEST_CAMERA);
        }
    }


}
