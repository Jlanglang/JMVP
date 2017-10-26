package com.baozi.mvp.helper;

import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.baozi.mvp.R;
import com.baozi.mvp.view.ToolbarView;

/**
 * @author jlanglang  2017/2/21 16:44
 * @版本 2.0
 * @Change
 */

class TempletToolbarHelperImpl extends BaseToolBarHelperImpl {
    private TextView mLeftTextView;
    private TextView mRightTextView;
    private ImageButton mLeftButton;
    private ImageButton mRightButton;
    private TextView mTitleView;
    private String mLeftText;
    private String mRightText;
    private Drawable mLeftDrawable;
    private Drawable mRightDrawable;
    private int mTitleSize;
    @ColorInt
    private int mTitleColor;
    @ColorInt
    private int mOtherTextColor;
    private int mOtherTextSize;


    TempletToolbarHelperImpl(ToolbarView uiView, View rootView, @LayoutRes int toolbar) {
        super(uiView, rootView, toolbar);
    }

    @Override
    public void initToolbar() {
        mLeftTextView = (TextView) mToolbar.findViewById(R.id.tv_left);
        mRightTextView = (TextView) mToolbar.findViewById(R.id.tv_right);
        mLeftButton = (ImageButton) mToolbar.findViewById(R.id.ib_left);
        mRightButton = (ImageButton) mToolbar.findViewById(R.id.ib_right);
        mTitleView = (TextView) mToolbar.findViewById(R.id.tv_title);
        ToolbarHelper.SimpleInitToolbar(mToolbarView.getContext(), mToolbar, false);
    }

    @Override
    public void setToolbarOptions(ToolbarOptions options) {
        super.setToolbarOptions(options);
        mTitleSize = options.getTitleSize();
        mTitleColor = options.getTitleColor();
        mOtherTextColor = options.getOtherTextColor();
        mOtherTextSize = options.getOtherTextSize();
        boolean noBack = options.isNoBack();
        if (mOtherTextSize != 0) {
            mLeftTextView.setTextSize(mOtherTextSize);
            mRightTextView.setTextSize(mOtherTextSize);
        }
        if (mOtherTextColor != 0) {
            mLeftTextView.setTextColor(mOtherTextColor);
            mRightTextView.setTextColor(mOtherTextColor);
        }
        if (mTitleSize != 0) {
            mTitleView.setTextSize(mTitleSize);
        }
        if (mTitleColor != 0) {
            mTitleView.setTextColor(mTitleColor);
        }
        if (!noBack) {
            int backDrawable = options.getBackDrawable();
            if (backDrawable == 0) {
                backDrawable = R.drawable.back;
            }
            setLeftButton(backDrawable, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mToolbarView.onBack();
                }
            });
        }
    }

    @Override
    public void setTextSize(int size) {
        mLeftTextView.setTextSize(size);
        mRightTextView.setTextSize(size);
    }

    @Override
    public void setTitleSize(int size) {
        mTitleView.setTextSize(size);
    }

//    /**
//     * 应该保证在调用Activity.setSupportActionBar()之后使用.
//     *
//     * @param isMaterialDesign
//     */
//    @Override
//    public void setMaterialDesignEnabled(boolean isMaterialDesign) {
//        super.setMaterialDesignEnabled(isMaterialDesign);
//        int visibility = isMaterialDesign ? View.GONE : View.VISIBLE;
//        if (null != mRightText) {
//            mRightTextView.setVisibility(visibility);
//        }
//        if (null != mRightButton) {
//            mRightButton.setVisibility(visibility);
//        }
//    }

    public void setTitle(@NonNull String titleView) {
        mTitleView.setText(titleView);
    }

    @Override
    public void setTitle(int titleId) {
        String title = mToolbarView.getContext().getResources().getString(titleId);
        setTitle(title);
    }


    @Override
    public void setLeftText(@NonNull String str, View.OnClickListener clickListener) {
        mLeftDrawable = null;
        mLeftText = str;
        mLeftTextView.setVisibility(View.VISIBLE);
        mLeftTextView.setText(str);
        mLeftTextView.setOnClickListener(clickListener);
    }

    @Override
    public void setLeftText(@StringRes int strId, View.OnClickListener clickListener) {
        String string = mToolbarView.getContext().getResources().getString(strId);
        setLeftText(string, clickListener);
    }

    @Override
    public void setLeftButton(@NonNull Drawable drawable, View.OnClickListener clickListener) {
        mLeftText = null;
        mLeftDrawable = drawable;
        mLeftButton.setVisibility(View.VISIBLE);
        mLeftButton.setImageDrawable(drawable);
        mLeftButton.setOnClickListener(clickListener);
    }

    @Override
    public void setLeftButton(@DrawableRes int drawableId, View.OnClickListener clickListener) {
        setLeftButton(ContextCompat.getDrawable(mToolbarView.getContext(), drawableId), clickListener);
    }

    @Override
    public void setRightText(@NonNull String str, View.OnClickListener clickListener) {
        mRightDrawable = null;
        mRightText = str;
        mRightTextView.setVisibility(View.VISIBLE);
        mRightTextView.setText(str);
        mRightTextView.setOnClickListener(clickListener);
    }

    @Override
    public void setRightText(int strId, View.OnClickListener clickListener) {
        String string = mToolbarView.getContext().getResources().getString(strId);
        setRightText(string, clickListener);
    }

    @Override
    public void setRightButton(@NonNull Drawable drawable, View.OnClickListener clickListener) {
        mRightText = null;
        mRightDrawable = drawable;
        mRightButton.setVisibility(View.VISIBLE);
        mRightButton.setImageDrawable(drawable);
        mRightButton.setOnClickListener(clickListener);
    }

    @Override
    public void setRightButton(int drawableId, View.OnClickListener clickListener) {
        setRightButton(ContextCompat.getDrawable(mToolbarView.getContext(), drawableId), clickListener);
    }

    @Override
    public void setCanBack(boolean canback) {
        super.setCanBack(canback);
        mLeftButton.setVisibility(canback ? View.VISIBLE : View.GONE);
    }
}
