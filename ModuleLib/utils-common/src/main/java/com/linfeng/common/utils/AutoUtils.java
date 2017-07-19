package com.linfeng.common.utils;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * @author ZhengJingle
 */

public class AutoUtils {

    private static AutoOptions mAutoOptions;
    private static boolean isScreenOriatation;

    public static void setAutoOptions(AutoOptions autoOptions) {
        if (autoOptions != null) {
            mAutoOptions = autoOptions;
        }
    }

    public static void setIsScreenOriatation(boolean isScreenOriatation) {
        AutoUtils.isScreenOriatation = isScreenOriatation;
    }

    /**
     * 返回当前屏幕是否为竖屏。
     *
     * @param context
     * @return 当且仅当当前屏幕为横屏时返回true, 否则返回false。
     */
    public static boolean isScreenOriatationPortrait(Context context) {
        return context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;
    }

    public static AutoOptions getAutoOptions() {
        return mAutoOptions;
    }

    public static void autoSize(View view) {
        autoSize(view, mAutoOptions);
    }

    public static void autoSize(View view, AutoOptions autoOptions) {

        ViewGroup.LayoutParams lp = view.getLayoutParams();

        if (lp == null) return;

        if (lp.width > 0) {
            lp.width = getDisplayWidthValue(lp.width, autoOptions);
        }
        if (lp.height > 0) {
            lp.height = getDisplayHeightValue(lp.height, autoOptions);
        }
    }

    public static void autoTextSize(View view, AutoOptions autoOptions) {
        if (view instanceof TextView) {
            double designPixels = ((TextView) view).getTextSize();
            if (autoOptions == null) {
                autoOptions = mAutoOptions;
            }
            if (autoOptions.getAutoType() != null && autoOptions.getAutoType() != AutoOptions.AutoType.PX) {
                designPixels = (designPixels / autoOptions.getDensity());
                designPixels *= autoOptions.getAutoType().dpi;
            }
            double displayDiagonal = Math.sqrt(Math.pow(autoOptions.getDisplayWidth(), 2) + Math.pow(autoOptions.getDisplayHeight(), 2));
            double designDiagonal;
            if (isScreenOriatation) {
                designDiagonal = Math.sqrt(Math.pow(autoOptions.getDisplayHeight(), 2) + Math.pow(autoOptions.getDesignWidth(), 2));
            } else {
                designDiagonal = Math.sqrt(Math.pow(autoOptions.getDesignWidth(), 2) + Math.pow(autoOptions.getDesignHeight(), 2));
            }
            double displayPixels = (displayDiagonal / designDiagonal) * designPixels;
            ((TextView) view).setIncludeFontPadding(false);
            ((TextView) view).setTextSize(TypedValue.COMPLEX_UNIT_PX, (float) displayPixels);
        }
    }

    public static void autoTextSize(View view) {
        autoTextSize(view, mAutoOptions);
    }

    public static void auto(Activity act) {
        auto(act, mAutoOptions);
    }

    public static void auto(Activity act, AutoOptions autoOptions) {
        if (act == null) return;
        View view = act.getWindow().getDecorView();
        isScreenOriatation = isScreenOriatationPortrait(act);
//        autoOptions.setCrossScreen(screenOriatationPortrait);
        auto(view, autoOptions);
    }

    public static void auto(View view, AutoOptions autoOptions) {
        if (view == null) return;
        autoTextSize(view, autoOptions);
        autoSize(view, autoOptions);
        autoPadding(view, autoOptions);
        autoMargin(view, autoOptions);

        if (view instanceof ViewGroup) {
            auto((ViewGroup) view, autoOptions);
        }
    }

    public static void auto(View view) {
        auto(view, mAutoOptions);
    }

    public static int getDisplayWidthValue(int designWidthValue, AutoOptions autoOptions) {
//        if (designWidthValue == ViewGroup.LayoutParams.MATCH_PARENT) {
//            designWidthValue = isScreenOriatation?autoOptions.getDisplayWidth():autoOptions.getDisplayHeight();
//        }
        if (designWidthValue < 2) {
            return designWidthValue;
        }

        if (autoOptions.getAutoType() != null && autoOptions.getAutoType() != AutoOptions.AutoType.PX) {
            designWidthValue = (int) (designWidthValue / autoOptions.getDensity() + 0.5f) * autoOptions.getAutoType().dpi;
        }
        if (isScreenOriatation) {
            return designWidthValue * autoOptions.getDisplayWidth() / autoOptions.getDesignHeight();
        }
        return designWidthValue * autoOptions.getDisplayWidth() / autoOptions.getDesignWidth();
    }

    public static int getDisplayHeightValue(int designHeightValue, AutoOptions autoOptions) {
//        if (designHeightValue == ViewGroup.LayoutParams.MATCH_PARENT) {
//            designHeightValue = isScreenOriatation?autoOptions.getDisplayWidth():autoOptions.getDisplayHeight();
//        }
        if (designHeightValue < 2) {
            return designHeightValue;
        }

        if (autoOptions.getAutoType() != null && autoOptions.getAutoType() != AutoOptions.AutoType.PX) {
            designHeightValue = (int) (designHeightValue / autoOptions.getDensity() + 0.5f) * autoOptions.getAutoType().dpi;
        }
        if (isScreenOriatation) {
            return designHeightValue * autoOptions.getDisplayHeight() / autoOptions.getDesignWidth();
        }
        return designHeightValue * autoOptions.getDisplayHeight() / autoOptions.getDesignHeight();
    }

    public static int getDisplayWidthValue(int designWidthValue) {
        return getDisplayWidthValue(designWidthValue, mAutoOptions);
    }

    public static int getDisplayHeightValue(int designHeightValue) {
        return getDisplayHeightValue(designHeightValue, mAutoOptions);
    }

    private static void autoMargin(View view, AutoOptions autoOptions) {
        if (!(view.getLayoutParams() instanceof ViewGroup.MarginLayoutParams))
            return;
        ViewGroup.MarginLayoutParams lp = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        if (lp == null) return;

        lp.leftMargin = getDisplayWidthValue(lp.leftMargin, autoOptions);
        lp.topMargin = getDisplayHeightValue(lp.topMargin, autoOptions);
        lp.rightMargin = getDisplayWidthValue(lp.rightMargin, autoOptions);
        lp.bottomMargin = getDisplayHeightValue(lp.bottomMargin, autoOptions);

    }

    private static void autoPadding(View view, AutoOptions autoOptions) {
        int l = view.getPaddingLeft();
        int t = view.getPaddingTop();
        int r = view.getPaddingRight();
        int b = view.getPaddingBottom();

        l = getDisplayWidthValue(l, autoOptions);
        t = getDisplayHeightValue(t, autoOptions);
        r = getDisplayWidthValue(r, autoOptions);
        b = getDisplayHeightValue(b, autoOptions);

        view.setPadding(l, t, r, b);
    }

    private static void auto(ViewGroup viewGroup, AutoOptions autoOptions) {
        int count = viewGroup.getChildCount();
        for (int i = 0; i < count; i++) {
            View child = viewGroup.getChildAt(i);
            if (child != null) {
                auto(child, autoOptions);
            }
        }
    }

}