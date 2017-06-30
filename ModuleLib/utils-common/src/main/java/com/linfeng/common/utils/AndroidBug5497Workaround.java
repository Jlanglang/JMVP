package com.linfeng.common.utils;

import android.app.Activity;
import android.graphics.Rect;
import android.os.Build;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;


public class AndroidBug5497Workaround {
    private final FrameLayout content;

    // For more information, see https://code.google.com/p/android/issues/detail?id=5497
    // To use this class, simply invoke assistActivity() on an Activity that already has its content view set.

    public static void assistActivity(Activity activity) {
        new AndroidBug5497Workaround(activity);
    }

    private View mChildOfContent;
    private int usableHeightPrevious;
    private FrameLayout.LayoutParams frameLayoutParams;

    private AndroidBug5497Workaround(Activity activity) {
        content = (FrameLayout) activity.findViewById(android.R.id.content);
        mChildOfContent = content.getChildAt(0);
        mChildOfContent.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            public void onGlobalLayout() {
                possiblyResizeChildOfContent();
            }
        });
        frameLayoutParams = (FrameLayout.LayoutParams) mChildOfContent.getLayoutParams();
    }

    private void possiblyResizeChildOfContent() {
        int usableHeightNow = computeUsableHeight();
        if (usableHeightNow != usableHeightPrevious) {
//            DisplayMetrics displayMetrics = content.getResources().getDisplayMetrics();
//            int usableHeightSansKeyboard = displayMetrics.heightPixels;
            int usableHeightSansKeyboard = mChildOfContent.getRootView().getHeight();
            int heightDifference = usableHeightSansKeyboard - usableHeightNow;

            if (heightDifference > (usableHeightSansKeyboard / 4)) {
                // keyboard probably just became visible
                frameLayoutParams.height = usableHeightSansKeyboard - heightDifference;
                if (Integer.parseInt(Build.VERSION.SDK) >= 19) {
                    frameLayoutParams.height += AutoUtils.getDisplayHeightValue(56);
                }
            } else {
                boolean b = Utils.checkDeviceHasNavigationBar(content.getContext());//是否有虚拟按键.
                int navigationBarHeight = Utils.getNavigationBarHeight(content.getContext());
                if (b) {
                    frameLayoutParams.height = usableHeightSansKeyboard - navigationBarHeight;
                } else {
                    frameLayoutParams.height = usableHeightSansKeyboard + navigationBarHeight;
                }
                frameLayoutParams.height = usableHeightSansKeyboard;
                if (Integer.parseInt(Build.VERSION.SDK) <= 19) {
                    frameLayoutParams.height -= AutoUtils.getDisplayHeightValue(56);
                }
            }
            mChildOfContent.requestLayout();
            usableHeightPrevious = usableHeightNow;
        }
    }

    private int computeUsableHeight() {
        Rect r = new Rect();
        mChildOfContent.getWindowVisibleDisplayFrame(r);
        return (r.bottom - r.top);
    }

    public int getKeyHeight() {
        return usableHeightPrevious;
    }

}