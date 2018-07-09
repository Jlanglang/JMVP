package com.linfeng.imageloder;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.widget.ImageView;

import com.bumptech.glide.request.RequestOptions;
import com.linfeng.imageloder.listener.ImageLoaderListener;
import com.linfeng.imageloder.listener.ProgressLoadListener;

/**
 * Created by heyao on 2017/8/25.
 */

public interface ILoaderStrategy {

    void loadImageWithNoAnimation(Context context, String url, ImageView imageView);

    void loadImage(Context context, String url, ImageView imageView);

    /**
     * @param placeholder 占位图
     */
    void loadImage(Context context, String url, int placeholder, ImageView imageView);

    void loadImageWithAnimation(Context context, String url, ImageView imageView);

    void loadImage(Context context, String url, ImageView imageView, ImageLoaderListener listener);

    void loadImage(Context context, String url, ImageView imageView, ProgressLoadListener listener);

    void loadImage(Context context, String url, ImageView imageView, int placeholder, ProgressLoadListener listener);

    void loadRoundImage(Context context, String url, ImageView imageView, int radius);

    void loadRoundImage(Fragment context, String url, ImageView imageView, int radius);

    void loadCircleImage(Context context, String url, ImageView imageView, RequestOptions options);

    void loadCircleImage(Context context, String url, ImageView imageView);

    void loadCircleImage(Fragment fragment, String url, ImageView imageView, RequestOptions options);

    void loadCircleImage(Fragment fragment, String url, ImageView imageView);

    //清除硬盘缓存
    void clearImageDiskCache(Context context);

    //清除内存缓存
    void clearImageMemoryCache(Context context);

    //根据不同的内存状态，来响应不同的内存释放策略
    void trimMemory(Context context, int level);

    //获取缓存大小
    String getCacheSize(Context context);

    void loadImage(Context context, String url, ImageView imageView, RequestOptions options);

    void loadImage(Fragment fragment, String url, ImageView imageView, ImageLoaderListener listener);
}
