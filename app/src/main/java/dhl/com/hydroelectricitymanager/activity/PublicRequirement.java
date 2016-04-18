package dhl.com.hydroelectricitymanager.activity;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.soundcloud.android.crop.Crop;

import java.io.File;

import butterknife.Bind;
import butterknife.OnClick;
import dhl.com.hydroelectricitymanager.R;

/**
 * 作者：adu on 2016/4/12 13:29
 * 邮箱2383335125@qq.com
 */
public class PublicRequirement extends BaseActivity {
    private static File tempFile;
    public static final int MYIMG1_REQUEST_CAMERA =401;
    public static final int MYIMG1_REQUEST_CROP=402;
    public static final int MYIMG1_REQUEST_GALLERY =403;
    public static final int MYIMG2_REQUEST_CAMERA = 411;
    public static final int MYIMG2_REQUEST_CROP=412;
    public static final int MYIMG2_REQUEST_GALLERY = 413;
    public static final int MYIMG3_REQUEST_CAMERA = 421;
    public static final int MYIMG3_REQUEST_CROP=422;
    public static final int MYIMG3_REQUEST_GALLERY =423;
    private static final int REQUEST_WRITE_STORAGE = 111;
    private static final int REQUEST_CAMERA = 112;
    @Bind(R.id.rlRequirement)
    RelativeLayout mainLayout;
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
    @Bind(R.id.immediatePay)
    Button immediatePay;
    @OnClick({R.id.backLeftWhite,R.id.serviceTime,R.id.serviceAddress,R.id.imgFirstUpload,R.id.imgSecondUpload,R.id.imgThirdUpload,R.id.immediatePay})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.backLeftWhite:
            onBackPressed();
            break;
            case R.id.imgFirstUpload:
                boolean hasPermission = (ActivityCompat.checkSelfPermission(context,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED);
                if (hasPermission) {
                    if (ActivityCompat.checkSelfPermission(context, Manifest.permission.CAMERA)
                            != PackageManager.PERMISSION_GRANTED) {
                        requestCameraPermission();
                    } else {
                        showPhotoHeadFindDialog(MYIMG1_REQUEST_GALLERY,MYIMG1_REQUEST_CAMERA);
                    }
                } else {
                    ActivityCompat.requestPermissions(PublicRequirement.this,
                            new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                            REQUEST_WRITE_STORAGE);
                }
                break;
            case R.id.imgSecondUpload:
                showPhotoHeadFindDialog(MYIMG2_REQUEST_GALLERY,MYIMG2_REQUEST_CAMERA);
                break;
            case R.id.imgThirdUpload:
                showPhotoHeadFindDialog(MYIMG3_REQUEST_GALLERY,MYIMG3_REQUEST_CAMERA);
                break;
            case R.id.serviceTime:
                startActivity(new Intent(context,SettingDateTime.class));

                break;
            case R.id.serviceAddress:
                startActivity(new Intent(context,ServiceAddress.class));

                break;
            case R.id.immediatePay:

                startActivity(new Intent(context,OrderConfirmHydroelectricity.class));
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

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences sharedPreferences = getSharedPreferences("data", Context.MODE_PRIVATE);
        String savedServiceTime= sharedPreferences.getString("savedServiceTime", "");
        selectedServiceTime.setText(savedServiceTime);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SharedPreferences sharedPreferences = getSharedPreferences("data", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString("savedServiceTime","");
        editor.apply();

    }

    private void showPhotoHeadFindDialog(final int gallery,final int camera) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setView(View.inflate(context, R.layout.dialog_modify_photo_head, null)).
                setPositiveButton("相册", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startGallery(PublicRequirement.this,gallery);

                    }
                }).setNegativeButton("相机", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startCamera(PublicRequirement.this,camera);

            }
        }).setCancelable(true);
        builder.create().show();


    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

       if (resultCode == Activity.RESULT_OK) {
            Uri uri;
            switch (requestCode) {
                case MYIMG1_REQUEST_CAMERA:
                    uri = Uri.fromFile(getTempFile());
                    beginCrop(PublicRequirement.this, uri, MYIMG1_REQUEST_CROP);
                    break;
                case MYIMG1_REQUEST_GALLERY:
                    if (data != null) {
                        uri = data.getData();
                        beginCrop(PublicRequirement.this, uri, MYIMG1_REQUEST_CROP);
                    }
                    break;
                case MYIMG1_REQUEST_CROP:
                    if (data != null) {
                        uri = Crop.getOutput(data); // 裁剪后图片Uri
                        System.out.println("image uri: " + uri);
                        imgFirstUpload.setImageURI(uri);
                        Toast.makeText(context, "上传照片成功！", Toast.LENGTH_SHORT).show();
                        imgSecondUpload.setVisibility(View.VISIBLE);
                        break;

                    }

                case MYIMG2_REQUEST_CAMERA:
                    uri = Uri.fromFile(getTempFile());
                    beginCrop(PublicRequirement.this, uri, MYIMG2_REQUEST_CROP);
                    break;
                case MYIMG2_REQUEST_GALLERY:
                    if (data != null) {
                        uri = data.getData();
                        beginCrop(PublicRequirement.this, uri, MYIMG2_REQUEST_CROP);
                    }
                    break;
                case MYIMG2_REQUEST_CROP:
                    if (data != null) {
                        uri = Crop.getOutput(data); // 裁剪后图片Uri
                        System.out.println("image uri: " + uri);
                        imgSecondUpload.setImageURI(uri);
                        Toast.makeText(context, "上传照片成功！", Toast.LENGTH_SHORT).show();
                        imgThirdUpload.setVisibility(View.VISIBLE);
                        break;

                    }
                case MYIMG3_REQUEST_CAMERA:
                    uri = Uri.fromFile(getTempFile());
                    beginCrop(PublicRequirement.this, uri, MYIMG3_REQUEST_CROP);
                    break;
                case MYIMG3_REQUEST_GALLERY:
                    if (data != null) {
                        uri = data.getData();
                        beginCrop(PublicRequirement.this, uri, MYIMG3_REQUEST_CROP);
                    }
                    break;
                case MYIMG3_REQUEST_CROP:
                    if (data != null) {
                        uri = Crop.getOutput(data); // 裁剪后图片Uri
                        System.out.println("image uri: " + uri);
                        imgThirdUpload.setImageURI(uri);
                        Toast.makeText(context, "上传照片成功！", Toast.LENGTH_SHORT).show();
                        break;

                    }
            }
        super.onActivityResult(requestCode, resultCode, data);
     }
    }



    public static void startCamera(Activity activity,int requestCode) {
        // 激活相机
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        Uri uri =setTempFile("capture");
        if (uri != null) {
            intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
            // 开启一个带有返回值的Activity，请求码为REQUEST_CAMERA
            activity.startActivityForResult(intent, requestCode);
        }
    }

    public static void startGallery(Activity activity,int requestCode) {
        //激活系统图库，选择一张图片
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        // 开启一个带有返回值的Activity，请求码为REQUEST_GALLERY
        activity.startActivityForResult(intent, requestCode);
    }

    public static void beginCrop(Activity activity, Uri source, int requestCode ) {
        //  Activity activity = fragment.getActivity();
        Uri destination = Uri.fromFile(new File(activity.getCacheDir(), "cropped"));
        Crop.of(source, destination).asSquare().start(activity, requestCode);

    }
    public static Uri setTempFile(String imageTag) {
        Uri uri = null;
        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            tempFile = new File(Environment.getExternalStorageDirectory(), imageTag);
            uri = Uri.fromFile(tempFile);
            Log.d("PhotoUtils", "create new uri: " + uri);
        } else {
            Log.e("PhotoUtils", "no external storage exist");
        }
        return uri;
    }

    /**
     * 获取相机拍摄图片保存地址
     */
    public static File getTempFile() {
        return tempFile;
    }


    private void requestCameraPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(PublicRequirement.this,
                Manifest.permission.CAMERA)) {
            Snackbar.make(mainLayout, "需要摄像头的权限，以显示相机预览",
                    Snackbar.LENGTH_INDEFINITE)
                    .setAction("OK", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            ActivityCompat.requestPermissions(PublicRequirement.this,
                                    new String[]{Manifest.permission.CAMERA},
                                    REQUEST_CAMERA);
                        }
                    }).show();
        } else {
            // Camera permission has not been granted yet. Request it directly.
            ActivityCompat.requestPermissions(PublicRequirement.this, new String[]{Manifest.permission.CAMERA},
                    REQUEST_CAMERA);
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case REQUEST_WRITE_STORAGE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (ActivityCompat.checkSelfPermission(context, Manifest.permission.CAMERA)
                            != PackageManager.PERMISSION_GRANTED) {
                        requestCameraPermission();
                    } else {
                        showPhotoHeadFindDialog(MYIMG1_REQUEST_GALLERY,MYIMG1_REQUEST_CAMERA);
                    }

//                    Snackbar.make(mainLayout, "CAMERA permission has now been granted. Showing preview.",
//                            Snackbar.LENGTH_SHORT).show();
                } else {
                    //Log.i(TAG, "CAMERA permission was NOT granted.");
                    Snackbar.make(mainLayout, "请允许访问设备上照片和文件",
                            Snackbar.LENGTH_SHORT).show();
                }
                break;
            case REQUEST_CAMERA:
                if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    showPhotoHeadFindDialog(MYIMG1_REQUEST_GALLERY,MYIMG1_REQUEST_CAMERA);
                    // Camera permission has been granted, preview can be displayed
                    // Log.i(TAG, "CAMERA permission has now been granted. Showing preview.");
//                    Snackbar.make(mainLayout, "CAMERA permission has now been granted. Showing preview.",
//                            Snackbar.LENGTH_SHORT).show();
                } else {
                    //Log.i(TAG, "CAMERA permission was NOT granted.");
                    Snackbar.make(mainLayout, "需要摄像头的权限，以显示相机预览",
                            Snackbar.LENGTH_SHORT).show();
                }
                break;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
