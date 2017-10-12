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
    private int displayWidth;
    private int displayHeight;
    private int designWidth;
    private int designHeight;
    private AutoType mAutoType;
    private float density;
//    private boolean isCrossScreen;

//    public boolean isCrossScreen() {
//        return isCrossScreen;
//    }
//
//    public void setCrossScreen(boolean crossScreen) {
//        isCrossScreen = crossScreen;
//    }

    public int getDisplayWidth() {
        return displayWidth;
    }

    public void setDisplayWidth(int displayWidth) {
        this.displayWidth = displayWidth;
    }

    public int getDisplayHeight() {
//        if (isCrossScreen()) {
//            return displayWidth;
//        }
        return displayHeight;
    }

    public void setDisplayHeight(int displayHeight) {
        this.displayHeight = displayHeight;
    }

    public float getDensity() {
        return density;
    }

    public void setDensity(float density) {
        this.density = density;
    }

    public int getDesignWidth() {
//        if (isCrossScreen()) {
//            return designHeight;
//        }
        return designWidth;
    }


    public void setDesignWidth(int designWidth) {
        this.designWidth = designWidth;
    }

    public int getDesignHeight() {
        return designHeight;
    }

    public void setDesignHeight(int designHeight) {
        this.designHeight = designHeight;
    }

    public AutoType getAutoType() {
        return mAutoType;
    }

    public void setAutoType(AutoType autoType) {
        mAutoType = autoType;
    }


    public static class Builder {
        private int displayWidth;
        private int displayHeight;
        private int designWidth;
        private int designHeight;
        private AutoType mAutoType;
        private boolean hasStatusBar;
        private int mStatusBarHeight;
        //        private boolean isCrossScreen;
        private float density;

        public Builder init(Context context) {
            WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
            Display display = windowManager.getDefaultDisplay();
            mStatusBarHeight = getStatusBarHeight(context);
            this.displayWidth = display.getWidth();
            this.displayHeight = display.getHeight();
            this.density = context.getResources().getDisplayMetrics().density;
            return this;
        }


        public Builder setDesign(int designWidth, int designHeight) {
            this.designWidth = designWidth;
            this.designHeight = designHeight;
            return this;
        }

        public Builder setAutoType(AutoType autoType) {
            mAutoType = autoType;
            return this;
        }

//        public AutoOptions.Builder setCrossScreen(boolean crossScreen) {
//            isCrossScreen = crossScreen;
//            return this;
//        }

        public Builder setHasStatusBar(boolean hasStatusBar) {
            this.hasStatusBar = hasStatusBar;
            return this;
        }

        public AutoOptions build() {
            AutoOptions autoOptions = new AutoOptions();
            if (mAutoType == null) {
                mAutoType = AutoType.PX;
            }
            autoOptions.setAutoType(mAutoType);
            if (designHeight == 0) {
                designWidth = displayWidth;
            }
            if (designHeight == 0) {
                designHeight = displayHeight;
            }
            autoOptions.setDesignHeight(designHeight);
            autoOptions.setDesignWidth(designWidth);
            //是否有statusbar
            if (hasStatusBar) {
                displayHeight -= mStatusBarHeight;
            }
            autoOptions.setDisplayHeight(displayHeight);
            autoOptions.setDisplayWidth(displayWidth);
//            autoOptions.setCrossScreen(isCrossScreen);
            autoOptions.setDensity(density);
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

    public enum AutoType {
        PX(-1), DP(1), DP_2(2), DP_3(3);

        AutoType(int dpi) {
            this.dpi = dpi;
        }

        int dpi;

    }
}
