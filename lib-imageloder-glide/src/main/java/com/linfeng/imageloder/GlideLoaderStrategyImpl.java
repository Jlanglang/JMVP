package com.linfeng.imageloder;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.util.Util;
import com.linfeng.imageloder.listener.ImageLoaderListener;
import com.linfeng.imageloder.listener.ProgressLoadListener;

import java.io.File;
import java.math.BigDecimal;


public class GlideLoaderStrategyImpl implements ILoaderStrategy {

    private RequestOptions mOptions;

    public GlideLoaderStrategyImpl(RequestOptions options) {
        this.mOptions = options;
    }

    @Override
    public void loadImageWithNoAnimation(Context context, String url, ImageView imageView) {
        if (Util.isOnMainThread()) {
            Glide.with(context)
                    .load(url)
                    .apply(mOptions.dontAnimate().dontTransform())
                    .into(imageView);
        }
    }

    @Override
    public void loadImage(Context context, String url, ImageView imageView) {
        if (Util.isOnMainThread()) {
            Glide.with(context)
                    .load(url)
                    .apply(mOptions)
                    .into(imageView);
        }
    }

    @Override
    public void loadImage(Fragment fragment, String url, ImageView imageView, ImageLoaderListener listener) {
        if (Util.isOnMainThread()) {
            Glide.with(fragment)
                    .load(url)
                    .apply(mOptions)
                    .listener(listener)
                    .into(imageView);
        }
    }

    @Override
    public void loadImage(Context context, String url, ImageView imageView, RequestOptions options) {
        if (Util.isOnMainThread()) {
            Glide.with(context)
                    .load(url)
                    .apply(options)
                    .into(imageView);
        }
    }


    @Override
    public void loadImageWithAnimation(Context context, String url, ImageView imageView) {
        if (Util.isOnMainThread()) {
            Glide.with(context)
                    .load(url)
                    .apply(mOptions)
                    .transition(new DrawableTransitionOptions().crossFade())
                    .into(imageView);
        }
    }

    @Override
    public void loadImage(Context context, String url, ImageView imageView, ImageLoaderListener listener) {
        if (Util.isOnMainThread()) {
            Glide.with(context)
                    .load(url)
                    .apply(mOptions)
                    .listener(listener)
                    .into(imageView);
        }
    }

    public void loadCircleImage(Fragment fragment, String url, ImageView imageView, RequestOptions options) {
        if (Util.isOnMainThread()) {
            Glide.with(fragment)
                    .load(url)
                    .apply(options.transform(new CircleCrop()))
                    .into(imageView);
        }
    }

    public void loadCircleImage(Fragment fragment, String url, ImageView imageView) {
        if (Util.isOnMainThread()) {
            Glide.with(fragment)
                    .load(url)
                    .apply(mOptions.transform(new CircleCrop()))
                    .into(imageView);
        }
    }

    public void loadCircleImage(Context activity, String url, ImageView imageView, RequestOptions options) {
        if (Util.isOnMainThread()) {
            Glide.with(activity)
                    .load(url)
                    .apply(options.transform(new CircleCrop()))
                    .into(imageView);
        }
    }

    public void loadCircleImage(Context activity, String url, ImageView imageView) {
        if (Util.isOnMainThread()) {
            Glide.with(activity)
                    .load(url)
                    .apply(mOptions.transform(new CircleCrop()))
                    .into(imageView);
        }
    }

    public void loadRoundImage(Fragment fragment, String url, ImageView imageView, int radius) {
        if (Util.isOnMainThread()) {
            Glide.with(fragment)
                    .load(url)
                    .apply(mOptions
                            .centerInside()
                            .transform(new RoundedCorners(radius)))
                    .into(imageView);
        }
    }

    public void loadRoundImage(Context context, String url, ImageView imageView, int radius) {
        Glide.with(context)
                .load(url)
                .apply(mOptions.centerInside().transform(new RoundedCorners(radius)))
                .into(imageView);
    }

    @Override
    public void loadImage(Context context, String url, int placeholder, ImageView imageView) {
        Glide.with(context)
                .load(url)
                .apply(mOptions.placeholder(placeholder))
                .into(imageView);
    }

    @Override
    public void loadImage(Context context, String url, ImageView imageView, ProgressLoadListener listener) {

    }

    @Override
    public void loadImage(Context context, String url, ImageView imageView, int placeholder, ProgressLoadListener listener) {

    }

    @Override
    public void clearImageDiskCache(final Context context) {
        try {
            if (Looper.myLooper() == Looper.getMainLooper()) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Glide.get(context.getApplicationContext()).clearDiskCache();
                    }
                }).start();
            } else {
                Glide.get(context).clearDiskCache();
            }
        } catch (Exception e) {
        }
    }

    @Override
    public void clearImageMemoryCache(Context context) {
        try {
            if (Looper.myLooper() == Looper.getMainLooper()) { //只能在主线程执行
                Glide.get(context).clearMemory();
            }
        } catch (Exception e) {
        }
    }

    @Override
    public void trimMemory(Context context, int level) {
        Glide.get(context).trimMemory(level);
    }

    @Override
    public String getCacheSize(Context context) {
        try {
            File cacheDir = Glide.getPhotoCacheDir(context.getApplicationContext());
            long size = getFolderSize(cacheDir);
            return getFormatSize(size);
        } catch (Exception e) {
        }
        return "";
    }


    /**
     * load image with Glide
     */
    private void loadNormal(final Context ctx, final String url, int placeholder, ImageView imageView) {
        /**
         *  为其添加缓存策略,其中缓存策略可以为:Source及None,None及为不缓存,Source缓存原型.如果为ALL和Result就不行.然后几个issue的连接:
         https://github.com/bumptech/glide/issues/513
         https://github.com/bumptech/glide/issues/281
         https://github.com/bumptech/glide/issues/600
         modified by xuqiang
         */

        //去掉动画 解决与CircleImageView冲突的问题 这个只是其中的一个解决方案
        //使用SOURCE 图片load结束再显示而不是先显示缩略图再显示最终的图片（导致图片大小不一致变化）
        final long startTime = System.currentTimeMillis();
        Glide.with(ctx).load(url)
                .apply(mOptions)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        return false;
                    }
                }).into(imageView);
    }

    /**
     * load image with Glide
     */
    private void loadGif(final Context ctx, String url, int placeholder, ImageView imageView) {
        final long startTime = System.currentTimeMillis();
        Glide.with(ctx).load(url)
                .apply(mOptions)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        return false;
                    }
                }).into(imageView);
    }

    /**
     * 获取指定文件夹内所有文件大小的和
     *
     * @param file file
     * @return size
     * @throws Exception
     */
    public static long getFolderSize(File file) throws Exception {
        long size = 0;
        try {
            File[] fileList = file.listFiles();
            for (File aFileList : fileList) {
                if (aFileList.isDirectory()) {
                    size = size + getFolderSize(aFileList);
                } else {
                    size = size + aFileList.length();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return size;
    }

    /**
     * 格式化单位
     *
     * @param size size
     * @return size
     */
    public static String getFormatSize(double size) {

        double kiloByte = size / 1024;
        if (kiloByte < 1) {
            return size + "Byte";
        }

        double megaByte = kiloByte / 1024;
        if (megaByte < 1) {
            BigDecimal result1 = new BigDecimal(Double.toString(kiloByte));
            return result1.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "KB";
        }

        double gigaByte = megaByte / 1024;
        if (gigaByte < 1) {
            BigDecimal result2 = new BigDecimal(Double.toString(megaByte));
            return result2.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "MB";
        }

        double teraBytes = gigaByte / 1024;
        if (teraBytes < 1) {
            BigDecimal result3 = new BigDecimal(Double.toString(gigaByte));
            return result3.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "GB";
        }
        BigDecimal result4 = new BigDecimal(teraBytes);

        return result4.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "TB";
    }

}
