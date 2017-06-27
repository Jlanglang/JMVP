package com.baozi.photodemo.ui;

import android.content.ContentResolver;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.baozi.jrecyclerviewadapter.adapter.recyclerview.SimpleRecyclerBaseAdapter;
import com.baozi.jrecyclerviewadapter.adapter.recyclerview.ViewHolder;
import com.baozi.photodemo.R;
import com.baozi.photodemo.base.BitmapUtils;
import com.baozi.photodemo.bean.PhotoAlbumItem;
import com.linfeng.imageloder.BindImageFactory;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;


/**
 * 分相册查看SD卡所有图片。
 * Created by hanj on 14-10-14.
 */
public class PhotoAlbumActivity extends AppCompatActivity {
    private SimpleRecyclerBaseAdapter<PhotoAlbumItem> mAdapter;
    private CompositeSubscription mCompositeSubscription;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!isSDcardOK()) {
            //只能读取sd卡的图片,内部存储的图片,只能读取app自己目录下的
            //如果没有sd卡,则直接返;
            Toast.makeText(this, "SD卡不可用。", Toast.LENGTH_SHORT).show();
            onBackPressed();
        }
        setContentView(R.layout.activity_custom_album);
        Toolbar tl_custom = (Toolbar) findViewById(R.id.tl_custom);
        tl_custom.setContentInsetsAbsolute(0, 0);
        tl_custom.setTitle("选择相册");
        setSupportActionBar(tl_custom);
        tl_custom.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayShowCustomEnabled(true);
            supportActionBar.setDisplayHomeAsUpEnabled(true);
            supportActionBar.setDisplayShowTitleEnabled(true);
            supportActionBar.setDisplayShowHomeEnabled(true);
        }
        mCompositeSubscription = new CompositeSubscription();
        initRecyclerView();
        QueryImageFiles();
    }

    private void QueryImageFiles() {
        Subscription subscribe = Observable.just(getImagePathsByContentProvider())
                .map(new Func1<ArrayList<PhotoAlbumItem>, ArrayList<PhotoAlbumItem>>() {
                    @Override
                    public ArrayList<PhotoAlbumItem> call(ArrayList<PhotoAlbumItem> photoAlbumItems) {
                        ArrayList<File> latestImagePaths = getLatestImagePaths(100);
                        if (latestImagePaths != null && latestImagePaths.size() > 0) {
                            photoAlbumItems.add(0, new PhotoAlbumItem("最近照片",
                                    latestImagePaths.size(), latestImagePaths.get(0).toString()));
                        }
                        return photoAlbumItems;
                    }
                })
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(new Subscriber<ArrayList<PhotoAlbumItem>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(ArrayList<PhotoAlbumItem> photoAlbumItems) {
                        if (photoAlbumItems != null) {
                            mAdapter.getItemManager().replaceAllItem(photoAlbumItems);
                        }
                    }
                });
        mCompositeSubscription.add(subscribe);
    }

    private void initRecyclerView() {
        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.rcl_image_list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.top = 10;
            }
        });
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mAdapter = new SimpleRecyclerBaseAdapter<PhotoAlbumItem>() {

            @Override
            public int getLayoutId() {
                return R.layout.photo_album_lv_item;
            }

            @Override
            public void convert(ViewHolder holder, PhotoAlbumItem photoAlbumLVItem, int position) {
                ImageView firstImageIV = holder.getView(R.id.select_img_gridView_img);
                TextView pathNameTV = holder.getView(R.id.select_img_gridView_path);
                TextView pathSum = holder.getView(R.id.select_img_gridView_sum);
                String filePath = photoAlbumLVItem.getFirstImagePath();
                BindImageFactory.bindImage(firstImageIV.getContext(), filePath, firstImageIV);
//                Glide
//                .with(firstImageIV.getContext())
//                        .load(filePath)
//                        .into(firstImageIV);
                String absolutePath = photoAlbumLVItem.getPathName();
                int lastSeparator = absolutePath.lastIndexOf(File.separator);
                String imageFileName = absolutePath.substring(lastSeparator + 1);
                pathNameTV.setText(imageFileName);
                pathSum.setText(photoAlbumLVItem.getFileCount() + "张照片");
            }
        };
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new SimpleRecyclerBaseAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(ViewHolder viewHolder, int position) {
                PhotoAlbumItem photoAlbumItem = mAdapter.getDatas().get(position);
                //全路径
                String pathName = photoAlbumItem.getPathName();
                //截取文件夹名字
                int lastSeparator = pathName.lastIndexOf(File.separator);
                String dirName = pathName.substring(lastSeparator + 1);
                PhotoWallFragment photoWallFragment = PhotoWallFragment.getInstance(pathName, dirName, position == 0);
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.add(android.R.id.content, photoWallFragment, PhotoWallFragment.TAG);
                fragmentTransaction.addToBackStack(PhotoWallFragment.TAG);
                fragmentTransaction.commitAllowingStateLoss();
            }
        });

    }

    /**
     * 判断SD卡是否可用
     */
    private boolean isSDcardOK() {
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }



    /**
     * 使用ContentProvider读取SD卡最近图片。
     */
    private ArrayList<File> getLatestImagePaths(int maxCount) {
        Uri mImageUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        String key_MIME_TYPE = MediaStore.Images.Media.MIME_TYPE;
        String key_DATA = MediaStore.Images.Media.DATA;
        ContentResolver mContentResolver = getContentResolver();
        // 只查询jpg和png的图片,按最新修改排序
        Cursor cursor = mContentResolver.query(mImageUri, new String[]{key_DATA},
                key_MIME_TYPE + "=? or " + key_MIME_TYPE + "=? or " + key_MIME_TYPE + "=?",
                new String[]{"image/jpg", "image/jpeg", "image/png"},
                MediaStore.Images.Media.DATE_MODIFIED);

        ArrayList<File> latestImagePaths = new ArrayList<File>();
        if (cursor != null) {
            //从最新的图片开始读取.
            //当cursor中没有数据时，cursor.moveToLast()将返回false
            if (cursor.moveToLast()) {
                while (true) {
                    // 获取图片的路径
                    String path = cursor.getString(0);
                    File file = new File(path);
                    if (file.exists()) {
                        latestImagePaths.add(file);
                    }
                    if (latestImagePaths.size() >= maxCount || !cursor.moveToPrevious()) {
                        break;
                    }
                }
            }
            cursor.close();
        }
        return latestImagePaths;
    }

    /**
     * 获取目录中图片的个数。
     */
    private int getImageCount(File folder) {
        int count = 0;
        File[] files = folder.listFiles();
        if (files == null) {
            return 0;
        }
        for (File file : files) {
            if (BitmapUtils.isImage(file.getName())) {
                count++;
            }
        }
        return count;
    }


    /**
     * 获取目录中最新的一张图片的绝对路径。
     */
    private String getFirstImagePath(File folder) {
        File[] files = folder.listFiles();
        for (int i = files.length - 1; i >= 0; i--) {
            File file = files[i];
            if (BitmapUtils.isImage(file.getName())) {
                return file.getAbsolutePath();
            }
        }
        return null;
    }

    /**
     * 使用ContentProvider读取SD卡所有图片。
     */
    private ArrayList<PhotoAlbumItem> getImagePathsByContentProvider() {
        Uri mImageUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        String key_MIME_TYPE = MediaStore.Images.Media.MIME_TYPE;
        String key_DATA = MediaStore.Images.Media.DATA;

        ContentResolver mContentResolver = getContentResolver();
        Cursor cursor = mContentResolver.query(mImageUri, new String[]{key_DATA},
                key_MIME_TYPE + "=? or " + key_MIME_TYPE + "=? or " + key_MIME_TYPE + "=?",
                new String[]{"image/jpg", "image/jpeg", "image/png"},
                MediaStore.Images.Media.DATE_MODIFIED);

        ArrayList<PhotoAlbumItem> list = new ArrayList<PhotoAlbumItem>();
        if (cursor != null) {
            if (cursor.moveToLast()) {
                // 只查询jpg和png的图片
                HashSet<String> cachePath = new HashSet<String>();
                while (true) {
                    //路径缓存，防止多次扫描同一目录
                    String imagePath = cursor.getString(0);
                    File parentFile = new File(imagePath).getParentFile();
                    String parentPath = parentFile.getAbsolutePath();

                    //不扫描重复路径
                    if (!cachePath.contains(parentPath)) {
                        int imageCount = getImageCount(parentFile);
                        if (imageCount != 0) {//如果相册文件夹没有照片,则跳过
                            list.add(new PhotoAlbumItem(parentPath, imageCount,
                                    getFirstImagePath(parentFile)));
                            cachePath.add(parentPath);
                        }
                    }
                    if (!cursor.moveToPrevious()) {
                        break;
                    }
                }
            }
            cursor.close();
        }
        return list;
    }
}
