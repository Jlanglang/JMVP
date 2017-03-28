package com.linfeng.common.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * http://developer.android.com/training/displaying-bitmaps/load-bitmap.html
 * http://www.cnblogs.com/kobe8/p/3877125.html
 *
 * @date :2015年1月27日
 */
public class BitmapUtils {
    /**
     * @param options   参数
     * @param reqWidth  目标的宽度
     * @param reqHeight 目标的高度
     * @return
     * @description 计算图片的压缩比率
     */
    private static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // 源图片的高度和宽度
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;
        if (height > reqHeight || width > reqWidth) {
            // 计算出实际宽高和目标宽高的比率
            final int halfHeight = height / 2;
            final int halfWidth = width / 2;
            while ((halfHeight / inSampleSize) > reqHeight && (halfWidth / inSampleSize) > reqWidth) {
                inSampleSize *= 2;
            }
        }
        return inSampleSize;
    }

    /**
     * @param src
     * @param dstWidth
     * @param dstHeight
     * @return
     * @description 通过传入的bitmap，进行压缩，得到符合标准的bitmap
     */
    private static Bitmap createScaleBitmap(Bitmap src, int dstWidth, int dstHeight, int inSampleSize) {
        //如果inSampleSize是2的倍数，也就说这个src已经是我们想要的缩略图了，直接返回即可。
        if (inSampleSize % 2 == 0) {
            return src;
        }
        // 如果是放大图片，filter决定是否平滑，如果是缩小图片，filter无影响，我们这里是缩小图片，所以直接设置为false
        Bitmap dst = Bitmap.createScaledBitmap(src, dstWidth, dstHeight, false);
        if (src != dst) { // 如果没有缩放，那么不回收
            src.recycle(); // 释放Bitmap的native像素数组
        }
        return dst;
    }

    /**
     * @param src
     * @param dstWidth
     * @param dstHeight
     * @return
     */
    public static Bitmap createScaleBitmap(String src, int dstWidth, int dstHeight) {
        //读取文件
        Bitmap bitmap = BitmapFactory.decodeFile(src);
        return createScaleBitmap(bitmap, dstWidth, dstHeight);
    }

    /**
     * @param dstWidth
     * @param dstHeight
     * @return
     */
    public static Bitmap createScaleBitmap(Bitmap bitmap, int dstWidth, int dstHeight) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        options.inPreferredConfig = Bitmap.Config.ARGB_8888;
        options.inSampleSize = calculateInSampleSize(options, dstWidth, dstHeight);
        options.inJustDecodeBounds = false;
        return createScaleBitmap(bitmap, dstWidth, dstHeight, options.inSampleSize);
    }
}