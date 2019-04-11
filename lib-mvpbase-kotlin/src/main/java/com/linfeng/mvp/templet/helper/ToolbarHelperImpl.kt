package com.linfeng.mvp.templet.helper

import android.graphics.drawable.Drawable
import android.support.annotation.ColorInt
import android.support.annotation.DrawableRes
import android.support.annotation.LayoutRes
import android.support.annotation.StringRes
import android.support.v4.content.ContextCompat
import android.view.View
import android.widget.ImageButton
import android.widget.TextView

import com.linfeng.mvp.R
import com.linfeng.mvp.templet.options.ToolbarOptions
import com.linfeng.mvp.view.ToolbarView

/**
 * @author jlanglang  2017/2/21 16:44
 * @版本 2.0
 * @Change
 */

@Suppress("PARAMETER_NAME_CHANGED_ON_OVERRIDE")
internal class ToolbarHelperImpl(uiView: ToolbarView, rootView: View, @LayoutRes toolbar: Int) : BaseToolBarHelperImpl(uiView, rootView, toolbar) {
    private var mLeftTextView: TextView? = null
    private var mRightTextView: TextView? = null
    private var mLeftButton: ImageButton? = null
    private var mRightButton: ImageButton? = null
    private var mTitleView: TextView? = null
    private var mLeftText: String? = null
    private var mRightText: String? = null
    private var mLeftDrawable: Drawable? = null
    private var mRightDrawable: Drawable? = null
    private var mTitleSize: Int = 0
    @ColorInt
    private var mTitleColor: Int = 0
    @ColorInt
    private var mOtherTextColor: Int = 0
    private var mOtherTextSize: Int = 0

    override fun initToolbar() {
        val toolbar = toolbar ?: return
        mLeftTextView = toolbar.findViewById(R.id.tv_left)
        mRightTextView = toolbar.findViewById(R.id.tv_right)
        mLeftButton = toolbar.findViewById(R.id.ib_left)
        mRightButton = toolbar.findViewById(R.id.ib_right)
        mTitleView = toolbar.findViewById(R.id.tv_title)

        ToolbarHelper.simpleInitToolbar(mToolbarView.mContext, toolbar, false)
    }

    override fun setToolbarOptions(toolbarOptions: ToolbarOptions) {
        super.setToolbarOptions(toolbarOptions)
        mTitleSize = toolbarOptions.getTitleSize()
        mTitleColor = toolbarOptions.getTitleColor()
        mOtherTextColor = toolbarOptions.getOtherTextColor()
        mOtherTextSize = toolbarOptions.getOtherTextSize()
        val noBack = toolbarOptions.isNoBack()
        if (mOtherTextSize != 0) {
            mLeftTextView!!.textSize = mOtherTextSize.toFloat()
            mRightTextView!!.textSize = mOtherTextSize.toFloat()
        }
        if (mOtherTextColor != 0) {
            mLeftTextView!!.setTextColor(mOtherTextColor)
            mRightTextView!!.setTextColor(mOtherTextColor)
        }
        if (mTitleSize != 0) {
            mTitleView!!.textSize = mTitleSize.toFloat()
        }
        if (mTitleColor != 0) {
            mTitleView!!.setTextColor(mTitleColor)
        }
        if (!noBack) {
            var backDrawable = toolbarOptions.getBackDrawable()
            if (backDrawable == 0) {
                backDrawable = R.drawable.back
            }
            setLeftButton(backDrawable, View.OnClickListener { mToolbarView.onBackPressed() })
        }
    }

    override fun setTextSize(size: Int) {
        mLeftTextView!!.textSize = size.toFloat()
        mRightTextView!!.textSize = size.toFloat()
    }

    override fun setTitleSize(size: Int) {
        mTitleView!!.textSize = size.toFloat()
    }


    override fun setTitle(titleStr: String) {
        mTitleView!!.text = titleStr
    }

    override fun setTitle(titleStr: Int) {
        val title = mToolbarView.mContext.resources.getString(titleStr)
        setTitle(title)
    }


    override fun setLeftText(str: String, clickListener: View.OnClickListener) {
        mLeftDrawable = null
        mLeftText = str
        mLeftTextView!!.visibility = View.VISIBLE
        mLeftTextView!!.text = str
        mLeftTextView!!.setOnClickListener(clickListener)
    }

    override fun setLeftText(@StringRes strId: Int, clickListener: View.OnClickListener) {
        val string = mToolbarView.mContext.resources.getString(strId)
        setLeftText(string, clickListener)
    }

    override fun setLeftButton(drawable: Drawable, clickListener: View.OnClickListener) {
        mLeftText = null
        mLeftDrawable = drawable
        mLeftButton!!.visibility = View.VISIBLE
        mLeftButton!!.setImageDrawable(drawable)
        mLeftButton!!.setOnClickListener(clickListener)
    }

    override fun setLeftButton(@DrawableRes drawableId: Int, clickListener: View.OnClickListener) {
        setLeftButton(ContextCompat.getDrawable(mToolbarView.mContext, drawableId)!!, clickListener)
    }

    override fun setRightText(str: String, clickListener: View.OnClickListener) {
        mRightDrawable = null
        mRightText = str
        mRightTextView!!.visibility = View.VISIBLE
        mRightTextView!!.text = str
        mRightTextView!!.setOnClickListener(clickListener)
    }

    override fun setRightText(strId: Int, clickListener: View.OnClickListener) {
        val string = mToolbarView.mContext.resources.getString(strId)
        setRightText(string, clickListener)
    }

    override fun setRightButton(drawable: Drawable, clickListener: View.OnClickListener) {
        mRightText = null
        mRightDrawable = drawable
        mRightButton!!.visibility = View.VISIBLE
        mRightButton!!.setImageDrawable(drawable)
        mRightButton!!.setOnClickListener(clickListener)
    }

    override fun setRightButton(drawableId: Int, clickListener: View.OnClickListener) {
        setRightButton(ContextCompat.getDrawable(mToolbarView.mContext, drawableId)!!, clickListener)
    }

    override fun setCanBack(canback: Boolean) {
        super.setCanBack(canback)
        mLeftButton!!.visibility = if (canback) View.VISIBLE else View.GONE
    }
}
