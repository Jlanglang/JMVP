package com.linfeng.imageloder;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.bitmap_recycle.LruBitmapPool;
import com.bumptech.glide.load.engine.cache.InternalCacheDiskCacheFactory;
import com.bumptech.glide.load.engine.cache.LruResourceCache;
import com.bumptech.glide.load.engine.cache.MemorySizeCalculator;
import com.bumptech.glide.module.GlideModule;

/**
 * @author jlanglang  2016/7/19 15:26
 * @版本 2.0   配置glide
 * @Change
 * @des ${TODO}
 */
public class SimpleGlideModule implements GlideModule {
    //配置磁盘缓存大小
    private static final int cacheSize100MegaBytes = 41943040;

    @Override
    public void applyOptions(Context context, GlideBuilder glideBuilder) {
        glideBuilder.setDecodeFormat(DecodeFormat.PREFER_ARGB_8888);
//        Log.i("SimpleGlideModule", "MyGlideModule初始化了");
        MemorySizeCalculator calculator = new MemorySizeCalculator(context);
        int defaultMemoryCacheSize = calculator.getMemoryCacheSize();
        int defaultBitmapPoolSize = calculator.getBitmapPoolSize();
        //内存缓存的大小
        int customMemoryCacheSize = (int) (1.2 * defaultMemoryCacheSize);
        int customBitmapPoolSize = (int) (1.2 * defaultBitmapPoolSize);
        glideBuilder.setMemoryCache(new LruResourceCache(customMemoryCacheSize));
        glideBuilder.setBitmapPool(new LruBitmapPool(customBitmapPoolSize));

        glideBuilder.setDiskCache(
                new InternalCacheDiskCacheFactory(context, cacheSize100MegaBytes)
        );
    }

    @Override
    public void registerComponents(Context context, Glide glide) {
    }
}
