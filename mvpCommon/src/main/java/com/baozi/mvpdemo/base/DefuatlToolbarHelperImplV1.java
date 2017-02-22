package com.baozi.mvpdemo.base;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.StringRes;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.baozi.mvpdemo.R;

/**
 * @author jlanglang  2017/2/21 16:44
 * @版本 2.0
 * @Change
 */

public class DefuatlToolbarHelperImplV1 extends BaseToolBarHelperImpl {
    private TextView mLeftText;
    private TextView mRightText;
    private ImageButton mLeftButton;
    private ImageButton mRightButton;
    private TextView mTitle;

    public DefuatlToolbarHelperImplV1(Context context, @LayoutRes int toolbar) {
        super(context, toolbar);
    }

    @Override
    public void initToolbar() {
        mLeftText = (TextView) mToolbar.findViewById(R.id.tv_left);
        mRightText = (TextView) mToolbar.findViewById(R.id.tv_right);
        mLeftButton = (ImageButton) mToolbar.findViewById(R.id.ib_left);
        mRightButton = (ImageButton) mToolbar.findViewById(R.id.ib_right);
        mTitle = (TextView) mToolbar.findViewById(R.id.tv_title);
    }

    @Override
    public Toolbar getToolbar() {
        return null;
    }

    @Override
    public void setTitle(String title) {
        if (mToolbar != null) {
            mTitle.setText(title);
        }
    }

    @Override
    public void setTitle(int titleId) {
        String title = mContext.getResources().getString(titleId);
        setTitle(title);
    }

    @Override
    public void setMaterialDesignEnabled(boolean isMaterialDesign) {

    }

    @Override
    public void setLeft(String str) {
        mLeftButton.setVisibility(View.GONE);
        mLeftText.setVisibility(View.VISIBLE);
        mLeftText.setText(str);
    }

    @Override
    public void setLeft(@StringRes int strId) {
        String string = mContext.getResources().getString(strId);
        setLeft(string);
    }

    @Override
    public void setLeft(Drawable drawable, View.OnClickListener clickListener) {
        if (mToolbar != null) {
            mLeftText.setVisibility(View.GONE);
            mLeftButton.setVisibility(View.VISIBLE);
            mLeftButton.setImageDrawable(drawable);
            mLeftButton.setOnClickListener(clickListener);
        }
    }

    @Override
    public void setLeft(@DrawableRes int drawableId, View.OnClickListener clickListener) {
        setLeft(ContextCompat.getDrawable(mContext, drawableId), clickListener);
    }

    @Override
    public void setRight(String str) {
        if (mToolbar != null) {
            mRightButton.setVisibility(View.GONE);
            mRightText.setVisibility(View.VISIBLE);
            mRightText.setText(str);
        }
    }

    @Override
    public void setRight(int strId) {
        String string = mContext.getResources().getString(strId);
        setRight(string);
    }

    @Override
    public void setRight(Drawable drawable, View.OnClickListener clickListener) {
        if (mToolbar != null) {
            mRightText.setVisibility(View.GONE);
            mRightButton.setVisibility(View.VISIBLE);
            mRightButton.setImageDrawable(drawable);
        }

    }

    @Override
    public void setRight(int drawableId, View.OnClickListener clickListener) {
        setRight(ContextCompat.getDrawable(mContext, drawableId), clickListener);
    }
}
