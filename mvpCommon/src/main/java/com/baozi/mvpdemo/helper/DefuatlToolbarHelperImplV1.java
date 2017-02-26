package com.baozi.mvpdemo.helper;

import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.baozi.mvpdemo.R;
import com.baozi.mvpdemo.ui.view.UIView;

/**
 * @author jlanglang  2017/2/21 16:44
 * @版本 2.0
 * @Change
 */

class DefuatlToolbarHelperImplV1 extends BaseToolBarHelperImpl {
    private TextView mLeftText;
    private TextView mRightText;
    private ImageButton mLeftButton;
    private ImageButton mRightButton;
    private TextView mTitle;

    DefuatlToolbarHelperImplV1(UIView uiView, @LayoutRes int toolbar) {
        super(uiView, toolbar);
    }

    @Override
    public void initToolbar() {
        mLeftText = (TextView) mToolbar.findViewById(R.id.tv_left);
        mRightText = (TextView) mToolbar.findViewById(R.id.tv_right);
        mLeftButton = (ImageButton) mToolbar.findViewById(R.id.ib_left);
        mRightButton = (ImageButton) mToolbar.findViewById(R.id.ib_right);
        mTitle = (TextView) mToolbar.findViewById(R.id.tv_title);
        mToolbar.setContentInsetsAbsolute(0, 0);
        setLeftButton(R.drawable.back, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mUIView.onBack();
            }
        });
        setMaterialDesignEnabled(false);
    }

    /**
     * 应该保证在调用Activity.setSupportActionBar()之后使用.
     *
     * @param isMaterialDesign
     */
    @Override
    public void setMaterialDesignEnabled(boolean isMaterialDesign) {
        super.setMaterialDesignEnabled(isMaterialDesign);
        if (mUIView.getSupportActionBar() == null) {
            return;
        }
        setLeftButton(mLeftButton.getDrawable(), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mUIView.onBack();
            }
        });
        setLeftText(mLeftText.getText().toString(), new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        setRightText(mRightText.getText().toString(), new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        setRightButton(mRightButton.getDrawable(), new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        setTitle(mTitle.getText().toString());
    }

    @Override
    public void setTitle(@NonNull String title) {
        if (isMaterialDesign) {
            mUIView.getSupportActionBar().setTitle(title);
        } else {
            mTitle.setText(title);
            mTitle.setVisibility(View.GONE);
        }
    }

    @Override
    public void setTitle(int titleId) {
        String title = mUIView.getResources().getString(titleId);
        setTitle(title);
    }


    @Override
    public void setLeftText(@NonNull String str, View.OnClickListener clickListener) {
        if (!isMaterialDesign) {
            mLeftButton.setVisibility(View.GONE);
            mLeftText.setVisibility(View.VISIBLE);
            mLeftText.setText(str);
            mLeftText.setOnClickListener(clickListener);
        }
    }

    @Override
    public void setLeftText(@StringRes int strId, View.OnClickListener clickListener) {
        String string = mUIView.getResources().getString(strId);
        setLeftText(string, clickListener);
    }

    @Override
    public void setLeftButton(@NonNull Drawable drawable, View.OnClickListener clickListener) {
        if (isMaterialDesign) {
            mToolbar.setNavigationIcon(drawable);
            mToolbar.setNavigationOnClickListener(clickListener);
            mLeftText.setVisibility(View.GONE);
            mLeftButton.setVisibility(View.GONE);
        } else {
            mLeftText.setVisibility(View.GONE);
            mLeftButton.setVisibility(View.VISIBLE);
            mLeftButton.setImageDrawable(drawable);
            mLeftButton.setOnClickListener(clickListener);
        }
    }

    @Override
    public void setLeftButton(@DrawableRes int drawableId, View.OnClickListener clickListener) {
        setLeftButton(ContextCompat.getDrawable(mUIView.getContext(), drawableId), clickListener);
    }

    @Override
    public void setRightText(@NonNull String str, View.OnClickListener clickListener) {
        if (!isMaterialDesign) {
            mRightButton.setVisibility(View.GONE);
            mRightText.setVisibility(View.VISIBLE);
            mRightText.setText(str);
            mRightText.setOnClickListener(clickListener);
        }
    }

    @Override
    public void setRightText(int strId, View.OnClickListener clickListener) {
        String string = mUIView.getResources().getString(strId);
        setRightText(string, clickListener);
    }

    @Override
    public void setRightButton(@NonNull Drawable drawable, View.OnClickListener clickListener) {
        if (!isMaterialDesign) {
            mRightText.setVisibility(View.GONE);
            mRightButton.setVisibility(View.VISIBLE);
            mRightButton.setImageDrawable(drawable);
        }else {
            mRightButton.setVisibility(View.GONE);
        }
    }

    @Override
    public void setRightButton(int drawableId, View.OnClickListener clickListener) {
        setRightButton(ContextCompat.getDrawable(mUIView.getContext(), drawableId), clickListener);
    }
}
