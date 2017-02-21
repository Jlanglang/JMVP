package com.baozi.mvpdemo.base;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
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

public class DefuatlToolbarHelperImpl extends ToolbarHelper {
    private TextView mLeftText;
    private TextView mRightText;
    private ImageButton mLeftButton;
    private ImageButton mRightButton;
    private TextView mTitle;

    public DefuatlToolbarHelperImpl(Toolbar toolbar, Context context) {
        super(toolbar, context);
        initToolbar();
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
    public void setTitle(String title) {
        if (mToolbar != null) {
            mTitle.setText(title);
        }
    }

    @Override
    public void setTitle(int titleId) {
        String title = context.getResources().getString(titleId);
        setTitle(title);
    }

    public void setLeft(String str) {
        mLeftButton.setVisibility(View.GONE);
        mLeftText.setVisibility(View.VISIBLE);
        mLeftText.setText(str);
    }

    public void setLeft(@StringRes int strId) {
        String string = context.getResources().getString(strId);
        setLeft(string);
    }

    public void setLeft(Drawable drawable, View.OnClickListener clickListener) {
        if (mToolbar != null) {
            mLeftText.setVisibility(View.GONE);
            mLeftButton.setVisibility(View.VISIBLE);
            mLeftButton.setImageDrawable(drawable);
            mLeftButton.setOnClickListener(clickListener);
        }
    }

    public void setLeft(@DrawableRes int drawableId, View.OnClickListener clickListener) {
        setLeft(ContextCompat.getDrawable(context, drawableId), clickListener);
    }

    public void setRight(String str) {
        if (mToolbar != null) {
            mRightButton.setVisibility(View.GONE);
            mRightText.setVisibility(View.VISIBLE);
            mRightText.setText(str);
        }
    }

    public void setRight(int strId) {
        String string = context.getResources().getString(strId);
        setRight(string);
    }

    public void setRight(Drawable drawable, View.OnClickListener clickListener) {
        if (mToolbar != null) {
            mRightText.setVisibility(View.GONE);
            mRightButton.setVisibility(View.VISIBLE);
            mRightButton.setImageDrawable(drawable);
        }

    }

    public void setRight(int drawableId, View.OnClickListener clickListener) {
        setRight(ContextCompat.getDrawable(context, drawableId), clickListener);
    }
}
