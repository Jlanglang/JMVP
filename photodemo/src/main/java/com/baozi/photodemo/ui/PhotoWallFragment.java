package com.baozi.photodemo.ui;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.baozi.jrecyclerviewadapter.adapter.recyclerview.SimpleRecyclerBaseAdapter;
import com.baozi.jrecyclerviewadapter.adapter.recyclerview.ViewHolder;
import com.baozi.jrecyclerviewadapter.adapter.widget.DragSelectRecyclerAdapter;
import com.baozi.jrecyclerviewadapter.adapter.widget.DragSelectRecyclerView;
import com.baozi.photodemo.R;
import com.baozi.photodemo.base.BitmapUtils;
import com.bumptech.glide.Glide;
import com.linfeng.imageloder.SimpleBitmapImageViewTarget;

import java.io.File;
import java.util.ArrayList;


/**
 * 选择照片页面
 * Created by hanj on 14-10-15.
 */
public class PhotoWallFragment extends Fragment
        implements DragSelectRecyclerAdapter.SelectionListener {
    public static final String TAG = PhotoWallFragment.class.getSimpleName();
    /**
     * 相册路径
     */
    private static final String FOLDERPATH = "folderPath";
    /**
     * 相册文件夹名字
     */
    private static final String DIRNAME = "dirName";
    /**
     * 是否为最近照片
     */
    private static final String ISLATEST = "isLatest";
    private Context mContext;
    private Bundle mBundle;
    private ArrayList<File> photos;
    private DragSelectRecyclerAdapter<File> mAdapter;
    private DragSelectRecyclerView mPhotoWallGrid;
    private int count;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext = context;
    }

    public static PhotoWallFragment getInstance(String folderPath, String dirName, boolean isLatest) {
        PhotoWallFragment photoWallFragment = new PhotoWallFragment();
        Bundle bundle = new Bundle();
        bundle.putString(FOLDERPATH, folderPath);
        bundle.putString(DIRNAME, dirName);
        bundle.putBoolean(ISLATEST, isLatest);
        photoWallFragment.setArguments(bundle);
        return photoWallFragment;
    }

    /**
     * 运行在onAttach之后
     * 可以接受别人传递过来的参数,实例化对象.
     *
     * @param savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //获取bundle,并保存起来
        if (savedInstanceState != null) {
            mBundle = savedInstanceState.getBundle("bundle");
        } else {
            mBundle = getArguments() == null ? new Bundle() : getArguments();
        }
        photos = new ArrayList<>();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        if (mBundle != null) {
            outState.putBundle("bundle", mBundle);
        }
        super.onSaveInstanceState(outState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.photo_wall, null);
        Toolbar tl_custom = (Toolbar) inflate.findViewById(R.id.tl_custom);
        initToolbar(tl_custom);
        mPhotoWallGrid = (DragSelectRecyclerView) inflate.findViewById(R.id.photo_wall_grid);
        initRecyclerView(savedInstanceState);
        initData();
        return inflate;
    }

    private void initToolbar(Toolbar toolbar) {
        TextView tv_right = (TextView) toolbar.findViewById(R.id.tv_right);
        tv_right.setText("完成");
        tv_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, SimplePhotoActivity.class);
                intent.putExtra("data", getSelectImagePaths());
                ((Activity) mContext).setResult(SimplePhotoActivity.FROM_CUSTOM_ALBUM, intent);
                ((Activity) mContext).finish();
            }
        });
        toolbar.setTitle(mBundle.getString(DIRNAME));
        toolbar.setContentInsetsAbsolute(0, 0);
        ((AppCompatActivity) mContext).setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStackImmediate();
            }
        });
        ActionBar supportActionBar = ((AppCompatActivity) mContext).getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayShowCustomEnabled(true);
            supportActionBar.setDisplayHomeAsUpEnabled(true);
            supportActionBar.setDisplayShowTitleEnabled(true);
            supportActionBar.setDisplayShowHomeEnabled(true);
        }
    }

    public void initData() {
        String folderPath = mBundle.getString(FOLDERPATH);
        boolean isLatest = mBundle.getBoolean(ISLATEST);
        updateView(isLatest, folderPath);
    }

    private void initRecyclerView(Bundle savedInstanceState) {
        mAdapter = new DragSelectRecyclerAdapter<File>() {
            @Override
            public int getLayoutId() {
                return R.layout.photo_wall_item;
            }

            @Override
            public void convert(ViewHolder holder, File s, int position) {
                File filePath = photos.get(position);
                TextView photo_wall_item_cb = holder.getView(R.id.photo_wall_item_cb);
                ImageView photo_wall_item_photo = holder.getView(R.id.photo_wall_item_photo);
                Glide.with(mContext)
                        .load(filePath)
                        .centerCrop()
                        .dontAnimate()
                        .into(new SimpleBitmapImageViewTarget(photo_wall_item_photo).getView());
                if (mAdapter.isIndexSelected(position)) {
                    photo_wall_item_cb.setEnabled(true);
                    photo_wall_item_cb.setText((getSelectedIndices().indexOf(position) + 1) + "");
                    photo_wall_item_photo.setColorFilter(Color.parseColor("#66000000"));
                } else {
                    photo_wall_item_cb.setText("");
                    photo_wall_item_cb.setEnabled(false);
                    photo_wall_item_photo.setColorFilter(null);
                }
            }
        };
        mAdapter.setOnItemClickListener(new SimpleRecyclerBaseAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(ViewHolder viewHolder, int position) {
                mAdapter.toggleSelected(position);
            }
        });
        mAdapter.setOnItemLongClickListener(new SimpleRecyclerBaseAdapter.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(ViewHolder viewHolder, int position) {
                if (viewHolder.itemView.getTag() != null) {
                    mPhotoWallGrid.setDragSelectActive(true, position);
                    return true;
                }
                return false;
            }
        });
        mAdapter.setSelectionListener(this);
        mAdapter.restoreInstanceState(savedInstanceState);

        mPhotoWallGrid.setLayoutManager(new GridLayoutManager(mContext, 3));
        mPhotoWallGrid.setItemAnimator(new DefaultItemAnimator());
        mPhotoWallGrid.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                outRect.top = 8;
                GridLayoutManager layoutManager = (GridLayoutManager) parent.getLayoutManager();
                GridLayoutManager.LayoutParams layoutParams = (GridLayoutManager.LayoutParams) view.getLayoutParams();
                int spanIndex = layoutParams.getSpanIndex();//在一行中所在的角标，第几列
                if (spanIndex != layoutManager.getSpanCount() - 1) {
                    outRect.right = 8;
                }
//                RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) view.getLayoutParams();
//                int viewLayoutPosition = layoutParams.getViewLayoutPosition();
//                if (viewLayoutPosition==0){
//                    outRect.top=20;
//                    outRect.left=20;
//                    outRect.right=20;
//                    outRect.bottom=20;
//                }
            }
        });
        mPhotoWallGrid.setAdapter(mAdapter);
    }

    /**
     * 根据图片所属文件夹路径，刷新页面
     */
    private void updateView(boolean isLatest, String folderPath) {
        if (isLatest) {
            photos.addAll(getLatestImagePaths(100));
        } else {
//            int lastSeparator = folderPath.lastIndexOf(File.separator);
            photos.addAll(getAllImagePathsByFolder(folderPath));
        }
        mAdapter.getItemManager().addAllItems(photos);
    }


    /**
     * 获取指定路径下的所有图片文件。
     */
    private ArrayList<File> getAllImagePathsByFolder(String folderPath) {
        File folder = new File(folderPath);
        String[] allFileNames = folder.list();
        if (allFileNames == null || allFileNames.length == 0) {
            return null;
        }

        ArrayList<File> imageFilePaths = new ArrayList<File>();
        for (int i = allFileNames.length - 1; i >= 0; i--) {
            if (BitmapUtils.isImage((allFileNames[i]))) {
                imageFilePaths.add(new File(folderPath + File.separator + allFileNames[i]));
            }
        }
        return imageFilePaths;
    }

    /**
     * 使用ContentProvider读取SD卡最近图片。
     */
    private ArrayList<File> getLatestImagePaths(int maxCount) {
        Uri mImageUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;

        String key_MIME_TYPE = MediaStore.Images.Media.MIME_TYPE;
        String key_DATA = MediaStore.Images.Media.DATA;

        ContentResolver mContentResolver = mContext.getContentResolver();

        // 只查询jpg和png的图片,按最新修改排序
        Cursor cursor = mContentResolver.query(mImageUri, new String[]{key_DATA},
                key_MIME_TYPE + "=? or " + key_MIME_TYPE + "=? or " + key_MIME_TYPE + "=?",
                new String[]{"image/jpg", "image/jpeg", "image/png"},
                MediaStore.Images.Media.DATE_MODIFIED);

        ArrayList<File> latestImagePaths = null;
        if (cursor != null) {
            //从最新的图片开始读取.
            //当cursor中没有数据时，cursor.moveToLast()将返回false
            if (cursor.moveToLast()) {
                latestImagePaths = new ArrayList<File>();
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

    //获取已选择的图片路径
    private ArrayList<File> getSelectImagePaths() {
        ArrayList<File> selectedImageList = new ArrayList<File>();
        if (mAdapter == null) {
            return null;
        }
        ArrayList<Integer> selectedIndices = mAdapter.getSelectedIndices();
        for (int i = selectedIndices.size(), j = 0; j < i; j++) {
            Integer integer = selectedIndices.get(j);
            selectedImageList.add(photos.get(integer));
        }
        return selectedImageList;
    }

    @Override
    public void onDragSelectionChanged(int count) {
        if (count >= 0) {
            this.count = count;
        }
    }
}
