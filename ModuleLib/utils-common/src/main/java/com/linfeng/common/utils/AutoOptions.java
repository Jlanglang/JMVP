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
    private double textPixelsRate;

    public AutoOptions() {

    }

    public int getDisplayWidth() {
        return displayWidth;
    }

    public void setDisplayWidth(int displayWidth) {
        this.displayWidth = displayWidth;
    }

    public int getDisplayHeight() {
        return displayHeight;
    }

    public void setDisplayHeight(int displayHeight) {
        this.displayHeight = displayHeight;
    }

    public int getDesignWidth() {
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

    public double getTextPixelsRate() {
        return textPixelsRate;
    }

    public void setTextPixelsRate(double textPixelsRate) {
        this.textPixelsRate = textPixelsRate;
    }

    public class Builder {
        private int displayWidth;
        private int displayHeight;
        private int designWidth;
        private int designHeight;
        private AutoType mAutoType;
        private double textPixelsRate;
        private boolean hasStatusBar;
        private int mStatusBarHeight;

        public AutoOptions.Builder setDesign(int designWidth, int designHeight) {
            this.designWidth = designWidth;
            this.designHeight = designHeight;
            return this;
        }

        public AutoOptions.Builder setAutoType(AutoType autoType) {
            mAutoType = autoType;
            return this;
        }

        public AutoOptions.Builder init(Context context) {
            WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
            Display display = windowManager.getDefaultDisplay();
            mStatusBarHeight = getStatusBarHeight(context);
            this.displayWidth = display.getWidth();
            this.displayHeight = display.getHeight();
            return this;
        }

        public AutoOptions.Builder setHasStatusBar(boolean hasStatusBar) {
            this.hasStatusBar = hasStatusBar;
            return this;
        }

        public AutoOptions build() {
            AutoOptions autoOptions = new AutoOptions();
            if (mAutoType==null){
                mAutoType = AutoType.PX;
            }
            autoOptions.setAutoType(mAutoType);
            if (designHeight==0){
                designWidth=displayWidth;
            }
            if (designHeight==0){
                designHeight=displayHeight;
            }
            autoOptions.setDesignHeight(designHeight);
            autoOptions.setDesignWidth(designWidth);
            //是否有statusbar
            if (hasStatusBar) {
                displayHeight -= mStatusBarHeight;
            }
            autoOptions.setDisplayHeight(displayHeight);
            autoOptions.setDisplayWidth(displayWidth);

            double displayDiagonal = Math.sqrt(Math.pow(displayWidth, 2) + Math.pow(designWidth, 2));
            double designDiagonal = Math.sqrt(Math.pow(displayHeight, 2) + Math.pow(designHeight, 2));
            textPixelsRate = displayDiagonal / designDiagonal;
            autoOptions.setTextPixelsRate(textPixelsRate);
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

        public int getDpi() {
            return dpi;
        }

        public void setDpi(int dpi) {
            this.dpi = dpi;
        }
    }
}
