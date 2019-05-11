package com.linfeng.imageloder.module;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.Registry;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.integration.okhttp3.OkHttpUrlLoader;
import com.bumptech.glide.load.engine.bitmap_recycle.LruBitmapPool;
import com.bumptech.glide.load.engine.cache.InternalCacheDiskCacheFactory;
import com.bumptech.glide.load.engine.cache.LruResourceCache;
import com.bumptech.glide.load.engine.cache.MemorySizeCalculator;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.module.AppGlideModule;

import java.io.InputStream;

/**
 * @author jlanglang  2016/7/19 15:26
 * @版本 2.0   配置glide
 * @Change
 */
@GlideModule
public class SimpleGlideModule extends AppGlideModule {
    //配置磁盘缓存大小
    private static final int cacheSize100MegaBytes = 1024 * 1024 * 100;

    @Override
    public boolean isManifestParsingEnabled() {
        return false;
    }

    @Override

    public void applyOptions(Context context, GlideBuilder glideBuilder) {
        MemorySizeCalculator calculator = new MemorySizeCalculator.Builder(context).build();
        int defaultMemoryCacheSize = calculator.getMemoryCacheSize();
        int defaultBitmapPoolSize = calculator.getBitmapPoolSize();
        //内存缓存的大小
        int customMemoryCacheSize = (int) (1.2*defaultMemoryCacheSize);
        int customBitmapPoolSize = (int) (1.2*defaultBitmapPoolSize);
        glideBuilder.setMemoryCache(new LruResourceCache(customMemoryCacheSize));
        glideBuilder.setBitmapPool(new LruBitmapPool(customBitmapPoolSize));

        glideBuilder.setDiskCache(
                new InternalCacheDiskCacheFactory(context, cacheSize100MegaBytes)
        );
    }

    @Override
    public void registerComponents(Context context, Glide glide, Registry registry) {
        registry.replace(GlideUrl.class, InputStream.class, new OkHttpUrlLoader.Factory());
    }
}
