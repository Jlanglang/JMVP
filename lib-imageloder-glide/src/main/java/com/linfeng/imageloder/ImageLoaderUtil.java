package com.linfeng.imageloder;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.widget.ImageView;

import com.bumptech.glide.request.RequestOptions;
import com.linfeng.imageloder.listener.ImageLoaderListener;


public class ImageLoaderUtil {
    private ILoaderStrategy mImageLoaderStrategy;
    private static ImageLoaderUtil mInstance;
    private static RequestOptions mOptions;

    public static RequestOptions getOptions() {
        return mOptions;
    }

    public static void init(RequestOptions options) {
        mOptions = options;
    }

    public static ImageLoaderUtil getInstance() {
        if (mInstance == null) {
            synchronized (ImageLoaderUtil.class) {
                if (mInstance == null) {
                    mInstance = new ImageLoaderUtil();
                }
            }
        }
        return mInstance;
    }

    private ImageLoaderUtil() {
        if (mOptions == null) {
            mOptions = new RequestOptions()
                    .autoClone();
        }
        mImageLoaderStrategy = new GlideLoaderStrategyImpl(mOptions);
    }

    public void loadImage(String url, ImageView imageView) {
        mImageLoaderStrategy.loadImage(imageView.getContext(), url, imageView);
    }
    public void loadImage(String url, ImageView imageView,RequestOptions options) {
        mImageLoaderStrategy.loadImage(imageView.getContext(), url, imageView,options);
    }
    public void loadImageWithNoAnimation(String url, ImageView imageView) {
        mImageLoaderStrategy.loadImageWithNoAnimation(imageView.getContext(), url, imageView);
    }

    public void loadImageWithAnimation(String url, ImageView imageView) {
        mImageLoaderStrategy.loadImageWithAnimation(imageView.getContext(), url, imageView);
    }

    public void loadImageWithListener(Context context, String url, ImageView imageView, ImageLoaderListener listener) {
        mImageLoaderStrategy.loadImage(context, url, imageView, listener);
    }
    public void loadImageWithListener(Fragment fragment, String url, ImageView imageView, ImageLoaderListener listener) {
        mImageLoaderStrategy.loadImage(fragment, url, imageView, listener);
    }

    public void loadFragmentCircleImage(Fragment fragment, String url, ImageView imageView, RequestOptions options) {
        mImageLoaderStrategy.loadCircleImage(fragment, url, imageView, options);
    }

    public void loadFragmentCircleImage(Fragment fragment, String url, ImageView imageView) {
        mImageLoaderStrategy.loadCircleImage(fragment, url, imageView);
    }

    public void loadActivityCircleImage(Activity activity, String url, ImageView imageView, RequestOptions options) {
        mImageLoaderStrategy.loadCircleImage(activity, url, imageView, options);
    }

    public void loadActivityCircleImage(Activity activity, String url, ImageView imageView) {
        mImageLoaderStrategy.loadCircleImage(activity, url, imageView);
    }

    public void loadFragmentRoundImage(Context context, String url, ImageView imageView, int radius) {
        mImageLoaderStrategy.loadRoundImage(context, url, imageView, radius);
    }

    public void loadActivityRoundImage(Context context, String url, ImageView imageView, int radius) {
        mImageLoaderStrategy.loadRoundImage(context, url, imageView, radius);
    }

    public ILoaderStrategy getImageLoaderStrategy() {
        return mImageLoaderStrategy;
    }

    public void setImageLoaderStrategy(ILoaderStrategy imageLoaderStrategy) {
        mImageLoaderStrategy = imageLoaderStrategy;
    }

    /**
     * 清除图片磁盘缓存
     */
    public void clearImageDiskCache(final Context context) {
        mImageLoaderStrategy.clearImageDiskCache(context);
    }

    /**
     * 清除图片内存缓存
     */
    public void clearImageMemoryCache(Context context) {
        mImageLoaderStrategy.clearImageMemoryCache(context);
    }

    /**
     * 根据不同的内存状态，来响应不同的内存释放策略
     *
     * @param context
     * @param level
     */
    public void trimMemory(Context context, int level) {
        mImageLoaderStrategy.trimMemory(context, level);
    }

    /**
     * 清除图片所有缓存
     */
    public void clearImageAllCache(Context context) {
        clearImageDiskCache(context);
        clearImageMemoryCache(context);
    }

    /**
     * 获取缓存大小
     *
     * @return CacheSize
     */
    public String getCacheSize(Context context) {
        return mImageLoaderStrategy.getCacheSize(context);
    }

}
