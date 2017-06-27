package com.baozi.photodemo.base;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresPermission;
import android.support.v7.app.AppCompatActivity;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;


/**
 * Created by baozi on 2017/6/8.
 */

public abstract class BasePhotoActivity extends AppCompatActivity {
    /**
     * 来自系统拍照
     */
    private static final int FROM_SYSTEM_CAMERE = 1;
    /**
     * 来自系统图库
     */
    private static final int FROM_SYSTEM_ALBUM = 2;
    /**
     * 来自系统图库
     */
    private static final int FROM_SYSTEM_CROP = 3;
    protected File mCacheDir;
    private CompositeSubscription mCompositeSubscription;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCacheDir = creatCacheDir(initCacheDir());
        mCompositeSubscription = new CompositeSubscription();
    }

    /**
     * 调用系统摄像头拍照
     */
    public void startSystemCamere() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(mCacheDir, initCameraPath())));
        startActivityForResult(intent, FROM_SYSTEM_CAMERE);
    }

    /**
     * 调用系统图库
     */
    public void startSystemAlbum() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        //选择图片格式
        intent.setType("image/*");
        intent.putExtra("return-data", true);
        startActivityForResult(intent, FROM_SYSTEM_ALBUM);
    }

    /**
     * 调用系统裁剪
     */
    public void startSystemCrop(String path, int cropWidth, int cropHeight) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(Uri.fromFile(new File(path)), "image/*");
        // crop为true是设置在开启的intent中设置显示的view可以剪裁
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX,outputY 是剪裁图片的宽高
        intent.putExtra("outputX", cropWidth);
        intent.putExtra("outputY", cropHeight);
        intent.putExtra("return-data", true);
        intent.putExtra("noFaceDetection", true);
        startActivityForResult(intent, FROM_SYSTEM_CROP);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK) {
            return;
        }
        switch (requestCode) {
            case FROM_SYSTEM_CAMERE:
                File path = new File(mCacheDir, initCameraPath());
                Subscription subscribe = Observable.just(path)
                        .map(new Func1<File, File>() {
                            @Override
                            public File call(File path) {
                                //拿到临时缓存文件夹路径
                                //使用毫秒值生成文件名
                                File toPath = new File(mCacheDir, System.currentTimeMillis() + ".png");
                                try {
                                    toPath.createNewFile();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                return compressImage(path, toPath);
                            }
                        })
                        .subscribeOn(Schedulers.io())
                        .filter(new Func1<File, Boolean>() {
                            @Override
                            public Boolean call(File file) {
                                return file != null && file.exists();
                            }
                        })
                        .observeOn(AndroidSchedulers.mainThread())
                        .unsubscribeOn(Schedulers.io())
                        .subscribe(new Action1<File>() {
                            @Override
                            public void call(File s) {
                                onSystemCamereCallBack(s);
                            }
                        });
                mCompositeSubscription.add(subscribe);
                break;
            case FROM_SYSTEM_ALBUM:
                if (data != null) {
                    Uri uri = data.getData();
                    try {
                        File file = new File(FileUtils.getImageAbsolutePath(this, uri));
                        onSystemAlbumCallBack(file);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                break;
            case FROM_SYSTEM_CROP:
                if (data != null) {
                    Uri uri = data.getData();
                    try {
                        File file = new File(new URI(uri.toString()));
                        onSystemAlbumCallBack(file);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                break;
        }
    }

    /**
     * 自定义如何压缩图片
     *
     * @return
     */
    @NonNull
    protected abstract File compressImage(File path, File toPath);

    /**
     * 调用系统拍照后的回调,此时已经生成新的缩放文件.会调用compressImage()
     */
    public void onSystemCamereCallBack(File file) {
    }

    /**
     * 调用系统图库并返回的回调,为原文件,未压缩处理
     */
    public void onSystemAlbumCallBack(File file) {

    }

    /**
     * 调用系统裁剪并返回的回调,为原文件,未压缩处理
     */
    public void onSystemCropCallBack(File file) {

    }

    /**
     * 生成路径,根据sd卡是否存在,如果存在sd卡,则优先使用sd卡,否则使用内部存储
     *
     * @param path 文件路径
     * @return 返回适当的Flie
     */
    protected File creatCacheDir(String path) {
        File file;
        if (isSDcardOK()) {
            file = new File(getExternalCacheDir(), path);
        } else {
            file = new File(getCacheDir(), path);
        }
        if (!file.exists()) {
            file.mkdirs();
        }
        return file;
    }

    /**
     * 判断SD卡是否可用
     */
    private boolean isSDcardOK() {
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }

    /**
     * 保存拍照图片的文件名
     *
     * @return
     */
    protected abstract String initCameraPath();

    /**
     * 临时保存图片的路径
     *
     * @return
     */
    protected abstract String initCacheDir();
}
