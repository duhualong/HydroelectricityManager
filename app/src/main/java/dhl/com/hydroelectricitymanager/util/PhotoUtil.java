package dhl.com.hydroelectricitymanager.util;

import android.app.Activity;
import android.app.Fragment;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;

import com.soundcloud.android.crop.Crop;

import java.io.File;

/**
 * Get and crop photo
 * Created by zac on 11/30/15.
 */
public class PhotoUtil {

    private static final String TAG = "PhotoUtil";

    private static File tempFile;

    public static final int REQUEST_GALLERY = 0x101;
    public static final int REQUEST_CAMERA = 0x102;
    public static final int REQUEST_CROP = 0x103;

    /**
     * 从相册获取图片
     * @param fragment 发起相册请求的fragment
     */
    public static void startGallery(Fragment fragment) {
        //激活系统图库，选择一张图片
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        // 开启一个带有返回值的Activity，请求码为REQUEST_GALLERY
        fragment.startActivityForResult(intent, REQUEST_GALLERY);
    }
    public static void startGallery(android.support.v4.app.Fragment fragment) {
        //激活系统图库，选择一张图片
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        // 开启一个带有返回值的Activity，请求码为REQUEST_GALLERY
        fragment.startActivityForResult(intent, REQUEST_GALLERY);
    }
    public static void startGallery(Activity activity) {
        //激活系统图库，选择一张图片
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        // 开启一个带有返回值的Activity，请求码为REQUEST_GALLERY
        activity.startActivityForResult(intent, REQUEST_GALLERY);
    }

    /**
     * 从相机获取图片
     * @param fragment 发起拍照的fragment
     */
    public static void startCamera(Fragment fragment) {

        // 激活相机
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        Uri uri = setTempFile("capture");
        if (uri != null) {
            intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
            // 开启一个带有返回值的Activity，请求码为REQUEST_CAMERA
            fragment.startActivityForResult(intent, REQUEST_CAMERA);
        }
    }
    public static void startCamera(android.support.v4.app.Fragment fragment) {

        // 激活相机
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        Uri uri = setTempFile("capture");
        if (uri != null) {
            intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
            // 开启一个带有返回值的Activity，请求码为REQUEST_CAMERA
            fragment.startActivityForResult(intent, REQUEST_CAMERA);
        }
    }
    public static void startCamera(Activity activity) {

        // 激活相机
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        Uri uri = setTempFile("capture");
        if (uri != null) {
            intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
            // 开启一个带有返回值的Activity，请求码为REQUEST_CAMERA
            activity.startActivityForResult(intent, REQUEST_CAMERA);
        }
    }

    /**
     * 裁剪图片
     * @param fragment 发起裁剪的fragment
     * @param source 源文件uri
     */
    public static void beginCrop(Fragment fragment, Uri source) {
        Activity activity = fragment.getActivity();
        Uri destination = Uri.fromFile(new File(activity.getCacheDir(), "cropped"));
        Crop.of(source, destination).asSquare().start(activity, fragment, REQUEST_CROP);
    }
    public static void beginCrop(android.support.v4.app.Fragment fragment, Uri source) {
        Activity activity = fragment.getActivity();
        Uri destination = Uri.fromFile(new File(activity.getCacheDir(), "cropped"));
        Crop.of(source, destination).asSquare().start(activity, fragment, REQUEST_CROP);
    }
    public static void beginCrop(Activity activity, Uri source) {
      //  Activity activity = fragment.getActivity();
        Uri destination = Uri.fromFile(new File(activity.getCacheDir(), "cropped"));
        Crop.of(source, destination).asSquare().start(activity, REQUEST_CROP);
    }

    /***********************************Image File Method********************************************/

    /**
     * 设置相机拍摄图片保存地址
     * @param imageTag filename
     */
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

    /**
     * 删除临时创建的拍照文件
     */
    public static void deleteTempFile() {
        if (tempFile != null) {
            try {
                boolean fileDeleted = tempFile.delete();
                if (fileDeleted) {
                    Log.d(TAG, "The image file has been deleted.");
                } else {
                    Log.e(TAG, "no image file exists");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            Log.e(TAG, "no image file exists");
        }
    }

    /**
     * Try to return the absolute file path from the given Uri
     *
     * @param context
     * @param uri
     * @return the file path or null
     */
    public static String getRealFilePath( final Context context, final Uri uri ) {
        if ( null == uri ) return null;
        final String scheme = uri.getScheme();
        String data = null;
        if ( scheme == null )
            data = uri.getPath();
        else if ( ContentResolver.SCHEME_FILE.equals( scheme ) ) {
            data = uri.getPath();
        } else if ( ContentResolver.SCHEME_CONTENT.equals( scheme ) ) {
            Cursor cursor = context.getContentResolver().query( uri, new String[] { MediaStore.Images.ImageColumns.DATA }, null, null, null );
            if ( null != cursor ) {
                if ( cursor.moveToFirst() ) {
                    int index = cursor.getColumnIndex( MediaStore.Images.ImageColumns.DATA );
                    if ( index > -1 ) {
                        data = cursor.getString( index );
                    }
                }
                cursor.close();
            }
        }
        return data;
    }
}
