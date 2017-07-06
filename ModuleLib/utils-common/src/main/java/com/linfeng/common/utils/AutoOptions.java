package com.linfeng.common.utils;

import android.content.Context;
import android.content.res.Resources;
import android.view.Display;
import android.view.WindowManager;

/**
 * @author jlanglang  2017/7/6 17:27
 * @版本 2.0
 * @Change
 */

public class AutoOptions {

    public enum AutoType {
        PX, DP
    }

    protected AutoOptions() {

    }

    public class Builder {
        public int displayWidth;
        public int displayHeight;
        public int designWidth;
        public int designHeight;
        public AutoType mAutoType;
        public double textPixelsRate;

        public AutoOptions.Builder setDesign(int designWidth, int designHeight) {
            this.designWidth = designWidth;
            this.designHeight = designHeight;
            return this;
        }

        public AutoOptions.Builder setAutoType(AutoType autoType) {
            mAutoType = autoType;
            return this;
        }

        public AutoOptions.Builder init(Context context, boolean hasStatusBar) {
            WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
            Display display = windowManager.getDefaultDisplay();
            int width = display.getWidth();
            int height = display.getHeight();
            if (hasStatusBar) {
                height -= getStatusBarHeight(context);
            }
            this.designWidth = width;
            this.designHeight = height;
            return this;
        }

        public AutoOptions build() {
            AutoOptions autoOptions = new AutoOptions();
            return autoOptions;
        }

        public int getStatusBarHeight(Context context) {
            int result = 0;
            try {
                int resourceId = context.getResources().getIdentifier(
                        "status_bar_height", "dimen", "android");
                if (resourceId > 0) {
                    result = context.getResources().getDimensionPixelSize(
                            resourceId);
                }
            } catch (Resources.NotFoundException e) {
                e.printStackTrace();
            }
            return result;
        }

    }
}
