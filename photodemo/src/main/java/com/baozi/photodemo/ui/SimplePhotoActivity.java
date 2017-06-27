package com.baozi.photodemo.ui;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.baozi.photodemo.base.BasePhotoActivity;
import com.baozi.jrecyclerviewadapter.adapter.recyclerview.SimpleRecyclerBaseAdapter;
import com.baozi.jrecyclerviewadapter.adapter.recyclerview.ViewHolder;
import com.baozi.jrecyclerviewadapter.adapter.recyclerview.wrapper.HeaderAndFootWapper;
import com.baozi.photodemo.R;
import com.baozi.photodemo.base.BitmapUtils;
import com.bumptech.glide.Glide;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 */

public class SimplePhotoActivity extends BasePhotoActivity {
    private HeaderAndFootWapper<File> mStringHeaderAndFootWapper;
    /**
     * 来自自定义图库
     */
    public static final int FROM_CUSTOM_ALBUM = 4;
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simplephoto);
        mRecyclerView = (RecyclerView) findViewById(R.id.rl_photo);
        initToolbar();
        initRecyclerView();
    }

    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.tl_custom);
        setSupportActionBar(toolbar);
        TextView tv_right = (TextView) findViewById(R.id.tv_right);
        TextView tv_left = (TextView) findViewById(R.id.tv_left);
        tv_left.setText("加水印");
        tv_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createWatermarkBitmap();
            }
        });
        tv_right.setText("压缩");
        tv_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                upLoad();
            }
        });
    }

    private void initRecyclerView() {
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        final SimpleRecyclerBaseAdapter mSimpleRecyclerBaseAdapter = new SimpleRecyclerBaseAdapter<File>() {
            @Override
            public int getLayoutId() {
                return R.layout.photo_item;
            }

            @Override
            public void convert(ViewHolder holder, File o, int position) {
                ImageView view = holder.getView(R.id.item_photo);
                Glide.with(SimplePhotoActivity.this)
                        .load(o)
                        .into(view);
            }
        };
        mStringHeaderAndFootWapper = new HeaderAndFootWapper<>(mSimpleRecyclerBaseAdapter);
        ImageView imageView = new ImageView(this);
        int i = dip2px(this, 120);
        imageView.setLayoutParams(new LinearLayoutCompat.LayoutParams(i, i));
        imageView.setBackground(ContextCompat.getDrawable(this, R.mipmap.ic_launcher));
        imageView.setOnClickListener(new View.OnClickListener() {
            private AlertDialog mDialog;

            @Override
            public void onClick(View v) {
                View inflate = LayoutInflater.from(v.getContext()).inflate(R.layout.photo_dialog_select, null);
                inflate.findViewById(R.id.ib_album).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startSystemAlbum();
                        mDialog.dismiss();
                    }
                });
                inflate.findViewById(R.id.ib_camere).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startSystemCamere();
                        mDialog.dismiss();
                    }
                });
                inflate.findViewById(R.id.ib_custom_album).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startCustomAlbum();
                        mDialog.dismiss();
                    }
                });
                mDialog = new AlertDialog.Builder(v.getContext())
                        .setView(inflate)
                        .setCancelable(true)
                        .show();
            }
        });
        mStringHeaderAndFootWapper.addHeaderView(imageView);
        mStringHeaderAndFootWapper.setOnItemClickListener(new SimpleRecyclerBaseAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(ViewHolder viewHolder, int position) {
                mStringHeaderAndFootWapper.getDatas().remove(position);
                mStringHeaderAndFootWapper.getItemManager().notifyDataChanged();
            }
        });
        mRecyclerView.setAdapter(mStringHeaderAndFootWapper);
    }

    @Override
    protected String initCameraPath() {
        return "photo.png";
    }

    @Override
    protected String initCacheDir() {
        return "/imageCache";
    }

    @NonNull
    @Override
    protected File compressImage(File path, File toPath) {
        //缩放读取图片
        Bitmap scaleBitmap = BitmapUtils.getZoomImage(path.getPath(), 1000, 1000);
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(toPath);
            scaleBitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                scaleBitmap.recycle();
                if (fos != null)
                    fos.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return toPath;
    }

    @Override
    public void onSystemCamereCallBack(File file) {
        super.onSystemCamereCallBack(file);
        mStringHeaderAndFootWapper.getDatas().add(file);
        mStringHeaderAndFootWapper.getItemManager().notifyDataChanged();
    }

    @Override
    public void onSystemAlbumCallBack(File file) {
        super.onSystemAlbumCallBack(file);
        mStringHeaderAndFootWapper.getDatas().add(file);
        mStringHeaderAndFootWapper.getItemManager().notifyDataChanged();
    }

    /**
     * 自定义图库
     */
    public void startCustomAlbum() {
        Intent intent = new Intent(this, PhotoAlbumActivity.class);
        startActivityForResult(intent, FROM_CUSTOM_ALBUM);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == FROM_CUSTOM_ALBUM && data != null) {
            ArrayList<File> photos = (ArrayList<File>) data.getSerializableExtra("data");
            mStringHeaderAndFootWapper.getItemManager().addAllItems(photos);
        }
    }

    @Override
    public void onBackPressed() {
        deleteFilesByDirectory(mCacheDir);
        super.onBackPressed();
    }

    /**
     * 压缩上传
     */
    public void upLoad() {
        if (mStringHeaderAndFootWapper != null) {
            List<File> datas = mStringHeaderAndFootWapper.getDatas();
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setMax(datas.size());
            progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            progressDialog.show();
            final ArrayList<byte[]> images = new ArrayList<>();
            Subscription upLoadRequest = Observable.from(datas)
                    .map(new Func1<File, byte[]>() {
                        @Override
                        public byte[] call(File file) {
                            ByteArrayOutputStream stream = new ByteArrayOutputStream();
                            //读取文件并压缩图片
                            Bitmap scaleBitmap = BitmapUtils.getZoomImage(file.getPath(), 360, 640);
                            //转化为二进制流数组
                            scaleBitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                            byte[] byteArray = stream.toByteArray();
                            //释放
                            scaleBitmap.recycle();
                            return byteArray;
                        }
                    })
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .unsubscribeOn(Schedulers.io())
                    .subscribe(new Subscriber<byte[]>() {
                        @Override
                        public void onCompleted() {
                            progressDialog.dismiss();
                            Toast.makeText(SimplePhotoActivity.this, "压缩完成", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onError(Throwable e) {

                        }

                        @Override
                        public void onNext(byte[] bytes) {
                            progressDialog.setProgress(images.size());
                            images.add(bytes);
                        }
                    });
        }
    }

    /**
     * 加水印
     */
    private void createWatermarkBitmap() {
        if (mStringHeaderAndFootWapper != null) {
            List<File> datas = mStringHeaderAndFootWapper.getDatas();
            final ArrayList<File> files = new ArrayList<>();
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setMax(datas.size());
            progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            progressDialog.show();
            Observable.from(datas)
                    .map(new Func1<File, File>() {
                        @Override
                        public File call(File file) {
                            Bitmap bitmapFromFile = BitmapUtils.getZoomImage(file.getPath(), 1000, 1000);
                            Bitmap bitmapFromDrawable = BitmapUtils.getBitmapFromDrawable(getResources().getDrawable(R.mipmap.ic_launcher));
                            Bitmap watermarkBitmap = BitmapUtils.createWatermarkBitmap(bitmapFromFile, bitmapFromDrawable, 40, 40);
                            File toPath = new File(mCacheDir, System.currentTimeMillis() + ".png");
                            FileOutputStream fos = null;
                            try {
                                if (!toPath.exists()) {
                                    toPath.createNewFile();
                                }
                                fos = new FileOutputStream(toPath);
                                watermarkBitmap.compress(Bitmap.CompressFormat.JPEG, 50, fos);
                            } catch (Exception e) {
                                e.printStackTrace();
                            } finally {
                                try {
                                    bitmapFromFile.recycle();
                                    bitmapFromDrawable.recycle();
                                    if (fos != null)
                                        fos.close();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                            return toPath;
                        }
                    })
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .unsubscribeOn(Schedulers.io())
                    .subscribe(new Subscriber<File>() {
                        @Override
                        public void onCompleted() {
                            mStringHeaderAndFootWapper.getItemManager().replaceAllItem(files);
                            progressDialog.dismiss();
                            Toast.makeText(SimplePhotoActivity.this, "添加水印完成", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onError(Throwable e) {

                        }

                        @Override
                        public void onNext(File file) {
                            progressDialog.setProgress(files.size());
                            files.add(file);
                        }
                    });
        }
    }

    /**
     * 将dip或dp值转换为px值，保证尺寸大小不变
     *
     * @param dipValue （DisplayMetrics类中属性density）
     * @return
     */
    public static int dip2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }
    /**
     * 删除文件
     */
    private void deleteFilesByDirectory(File directory) {
        if (directory.isFile()) {
            directory.delete();
            return;
        }
        if (directory.isDirectory()) {
            File[] childFiles = directory.listFiles();
            if (childFiles == null || childFiles.length == 0) {
                directory.delete();
                return;
            }
            for (int i = 0; i < childFiles.length; i++) {
                deleteFilesByDirectory(childFiles[i]);
            }
            directory.delete();
        }
    }
}
