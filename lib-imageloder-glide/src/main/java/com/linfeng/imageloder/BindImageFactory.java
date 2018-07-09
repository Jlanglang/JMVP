package com.linfeng.imageloder;

import android.app.Fragment;
import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.DrawableRes;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;

import java.io.File;
import java.util.concurrent.ExecutionException;

/**
 * @author jlanglang  2017/3/9 15:33
 * @版本 2.0
 * @Change
 */
@Deprecated
public class BindImageFactory {


    /**
     * 默认方法加载图片到ImageView
     * ----------------------------------------------------------------------------------------------------
     */
    public static void bindImage(Context context, String path, ImageView imageView) {
        Glide.with(context)
                .load(path)
                .apply(ImageLoaderUtil.getOptions())
                .into(imageView);
    }

    public static void bindImage(Context context, int res, ImageView imageView) {
        Glide.with(context)
                .load(res)
                .apply(ImageLoaderUtil.getOptions())
                .into(imageView);
    }

    public static void bindImage(Fragment fragment, String path, ImageView imageView) {
        Glide.with(fragment)
                .load(path)
                .apply(ImageLoaderUtil.getOptions())
                .into(imageView);
    }

    public static void bindImage(Fragment fragment, int res, ImageView imageView) {
        Glide.with(fragment)
                .load(res)
                .apply(ImageLoaderUtil.getOptions())
                .into(imageView);
    }

    /**
     * 设置加载中以及加载失败图片
     * ----------------------------------------------------------------------------------------------------
     */
    public static void loadingImage(Context mContext, String path, ImageView mImageView, int loadingImage, int errorImageView) {
        Glide.with(mContext)
                .load(path)
                .apply(ImageLoaderUtil.getOptions())
                .into(mImageView);
    }

    public static void loadingRoundImage(Context mContext, String path, ImageView mImageView, int loadingImage, int errorImageView, int round) {
        Glide.with(mContext)
                .load(path)
                .apply(ImageLoaderUtil.getOptions().transform(new RoundedCorners(round)))
                .into(mImageView);
    }

    public static void loadingCircleImage(Context mContext, String path, ImageView mImageView, int loadingImage, int errorImageView) {
        Glide.with(mContext)
                .load(path)
                .apply(ImageLoaderUtil.getOptions().transform(new CenterCrop()))
                .into(mImageView);
    }

    /**
     * 通过Glide加载图片,并裁剪圆角
     * ----------------------------------------------------------------------------------------------------
     */
    public static void bindRoundImage(Context context, String url, ImageView imageView) {
        Glide.with(context)
                .load(url)
                .apply(ImageLoaderUtil.getOptions().centerCrop()
                        .transform(new RoundedCorners(5)))
                .into(imageView);
    }

    public static void bindRoundImage(Context context, int res, ImageView imageView) {
        Glide.with(context)
                .load(res)
                .apply(ImageLoaderUtil.getOptions())
                .into(imageView);
    }


    /**
     * 通过Glide加载图片,并裁剪成圆形
     * ----------------------------------------------------------------------------------------------------
     */
    public static void bindCircleImage(Context context, String path, ImageView imageView) {
        Glide.with(context)
                .load(path)
                .apply(ImageLoaderUtil.getOptions().centerCrop()
                        .transform(new CenterCrop()))
                .into(imageView);
    }

    public static void bindCircleImage(Context context, int res, ImageView imageView) {
        Glide.with(context)
                .load(res)
                .apply(ImageLoaderUtil.getOptions().centerCrop()
                        .transform(new CenterCrop()))
                .into(imageView);
    }

    /**
     * 通过Glide获得bitmap
     * ----------------------------------------------------------------------------------------------------
     */
    public static Bitmap getImage(Context context, @DrawableRes int res, int width, int height) throws ExecutionException, InterruptedException {
        return Glide.with(context)
                .asBitmap()
                .load(res)
                .apply(ImageLoaderUtil.getOptions())
                .into(width, height)
                .get();
    }

    public static Bitmap getImage(Context context, String url, int width, int height) throws ExecutionException, InterruptedException {
        return Glide.with(context)
                .asBitmap()
                .load(url)
                .apply(ImageLoaderUtil.getOptions())
                .into(width, height)
                .get();
    }

    /**
     * 通过Glide下载图片
     * ----------------------------------------------------------------------------------------------------
     */
    public static File getImageFile(Context context, @DrawableRes int res, int width, int height) throws ExecutionException, InterruptedException {
        return Glide.with(context)
                .load(res)
                .downloadOnly(width, height)
                .get();
    }

    public static File getImageFile(Context context, String url, int width, int height) throws ExecutionException, InterruptedException {
        return Glide.with(context)
                .load(url)
                .downloadOnly(width, height)
                .get();
    }

    /**
     * 清理缓存
     * ----------------------------------------------------------------------------------------------------
     */
    public static void CleanImageCache(Context context) {
        Glide.get(context).clearDiskCache();
    }

}
