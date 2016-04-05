package dhl.com.hydroelectricitymanager.fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.soundcloud.android.crop.Crop;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import dhl.com.hydroelectricitymanager.R;
import dhl.com.hydroelectricitymanager.util.PhotoUtil;

/**
 * 作者：adu on 2016/3/28 09:50
 * 邮箱2383335125@qq.com
 */
public class MyFragment extends Fragment {
    private View mainView;
    @Bind(R.id.headPortrait)
    CircleImageView headPortrait;
    @OnClick({R.id.headPortrait})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.headPortrait:
                showPhotoHeadFindDialog();
                break;
        }

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mainView = inflater.inflate(R.layout.my_fragment, container, false);
        ButterKnife.bind(this,mainView);
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
                    System.out.println("!!!!!!---!!!!!");
                    uri = Uri.fromFile(PhotoUtil.getTempFile());
                    PhotoUtil.beginCrop(MyFragment.this, uri);
                    break;
                case PhotoUtil.REQUEST_GALLERY:
                    System.out.println("!????---!!!???!!");
                    if (data != null) {
                        uri = data.getData();
                        PhotoUtil.beginCrop(MyFragment.this, uri);

                    }
                    break;
                case PhotoUtil.REQUEST_CROP:
                    if (data != null) {
                        uri = Crop.getOutput(data); // 裁剪后图片Uri
                        System.out.println("image uri: " + uri);

                        // 展示图片
//                        Bitmap bm = ImageUtil.getSmallBitmap(PhotoUtil.getRealFilePath(context, uri));
//                        avatar.setImageBitmap(bm);
                        headPortrait.setImageURI(uri);
                        Toast.makeText(getActivity(), "上传头像成功！", Toast.LENGTH_SHORT).show();
                    }
                    break;
            }
        }
    }
}
