package com.linfeng.common.utils;

import android.app.Activity;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * @author ZhengJingle
 */

public class AutoUtils {
    private AutoOptions mAutoOptions;

    private AutoUtils() {

    }

    //    public static void init(Context context, boolean hasStatusBar, int designWidth, int designHeight) {
//        if (context == null || designWidth < 1 || designHeight < 1) return;
//        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
//        Display display = windowManager.getDefaultDisplay();
//        int width = display.getWidth();
//        int height = display.getHeight();
//
//        if (hasStatusBar) {
//            height -= getStatusBarHeight(context);
//        }
//
//        AutoUtils.displayWidth = width;
//        AutoUtils.displayHeight = height;
//
//        AutoUtils.designWidth = designWidth;
//        AutoUtils.designHeight = designHeight;
//
//        double displayDiagonal = Math.sqrt(Math.pow(AutoUtils.displayWidth, 2) + Math.pow(AutoUtils.displayHeight, 2));
//        double designDiagonal = Math.sqrt(Math.pow(AutoUtils.designWidth, 2) + Math.pow(AutoUtils.designHeight, 2));
//        AutoUtils.textPixelsRate = displayDiagonal / designDiagonal;
//    }
    public void autoMargin(View view) {
        if (!(view.getLayoutParams() instanceof ViewGroup.MarginLayoutParams))
            return;
        ViewGroup.MarginLayoutParams lp = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        if (lp == null) return;

        lp.leftMargin = getDisplayWidthValue(lp.leftMargin);
        lp.topMargin = getDisplayHeightValue(lp.topMargin);
        lp.rightMargin = getDisplayWidthValue(lp.rightMargin);
        lp.bottomMargin = getDisplayHeightValue(lp.bottomMargin);

    }

    public void autoPadding(View view) {
        int l = view.getPaddingLeft();
        int t = view.getPaddingTop();
        int r = view.getPaddingRight();
        int b = view.getPaddingBottom();

        l = getDisplayWidthValue(l);
        t = getDisplayHeightValue(t);
        r = getDisplayWidthValue(r);
        b = getDisplayHeightValue(b);

        view.setPadding(l, t, r, b);
    }

    public void autoSize(View view) {
        ViewGroup.LayoutParams lp = view.getLayoutParams();

        if (lp == null) return;

        if (lp.width > 0) {
            lp.width = getDisplayWidthValue(lp.width);
        }

        if (lp.height > 0) {
            lp.height = getDisplayHeightValue(lp.height);
        }

    }

    public void autoTextSize(View view) {
        if (view instanceof TextView) {
            double designPixels = ((TextView) view).getTextSize();
            double displayPixels = mAutoOptions.getTextPixelsRate() * designPixels;
            ((TextView) view).setIncludeFontPadding(false);
            ((TextView) view).setTextSize(TypedValue.COMPLEX_UNIT_PX, (float) displayPixels);
        }
    }

    public void auto(Activity act) {
        if (act == null || mAutoOptions == null) return;

        View view = act.getWindow().getDecorView();
        auto(view);
    }

    public void auto(View view) {
        if (view == null || mAutoOptions == null) return;

        autoTextSize(view);
        autoSize(view);
        autoPadding(view);
        autoMargin(view);

        if (view instanceof ViewGroup) {
            auto((ViewGroup) view);
        }
    }

    private void auto(ViewGroup viewGroup) {
        int count = viewGroup.getChildCount();
        for (int i = 0; i < count; i++) {
            View child = viewGroup.getChildAt(i);
            if (child != null) {
                auto(child);
            }
        }
    }

    public int getDisplayWidthValue(int designWidthValue) {
        if (designWidthValue < 2) {
            return designWidthValue;
        }

        return designWidthValue * mAutoOptions.getDisplayWidth() / mAutoOptions.getDesignWidth();
    }

    public int getDisplayHeightValue(int designHeightValue) {
        if (designHeightValue < 2) {
            return designHeightValue;
        }
        return designHeightValue * mAutoOptions.getDisplayHeight() / mAutoOptions.getDesignHeight();
    }

    private static class AutoInstance {
        private static AutoUtils autoUtils = new AutoUtils();

        public static AutoUtils getInstance() {
            return autoUtils;
        }
    }
}